package com.weikun.initdao;

import com.weikun.model.*;
import com.weikun.redis.dao.RedisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * �����ߣ�weikun��YST��   ���ڣ�2017/9/17
 * ˵˵���ܣ�
 */
@Repository
public class InitDataDAO {
    @Autowired
    private RedisDAO initDao;

    public void getHashByKey() {

        initDao.getHashByKey();
    }

    public void initAccount() {//hash
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //1�����������ڴ�
            Class.forName("org.gjt.mm.mysql.Driver");
            //2Ӧ��������������
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mpet?useUnicode=true&characterEncoding=utf-8",
                    "root", "root");
            //redis.ִ��SQL���
            String sql = "select * from account ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            //4���������
            while (rs.next()) {
                Account account = new Account();
                String key = rs.getString("username");
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setEmail(rs.getString("email"));
                account.setXm(rs.getString("xm"));
                account.setAddress(rs.getString("address"));
                initDao.saveHashByRedis("account", key, account);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                //5�ر�
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


    public void initProfile() {//hash
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //1�����������ڴ�
            Class.forName("org.gjt.mm.mysql.Driver");
            //2Ӧ��������������
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mpet?useUnicode=true&characterEncoding=utf-8",
                    "root", "root");
            //redis.ִ��SQL���
            String sql = "select * from profile ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            //4���������
            while (rs.next()) {
                Profile pro = new Profile();
                String key = rs.getString("username");
                pro.setUsername(key);
                pro.setLang(rs.getString("lang"));
                pro.setCatid(rs.getString("catid"));
                initDao.saveHashByRedis("profile", key, pro);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                //5�ر�
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void initCategory() {//hash
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //1�����������ڴ�
            Class.forName("org.gjt.mm.mysql.Driver");
            //2Ӧ��������������
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mpet?useUnicode=true&characterEncoding=utf-8",
                    "root", "root");
            //redis.ִ��SQL���
            String sql = "select * from category ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            //4���������
            while (rs.next()) {
                Category cate = new Category();
                String key = rs.getString("catid");
                cate.setCatid(key);
                cate.setName(rs.getString("name"));
                cate.setDescn(rs.getString("descn"));


                initDao.saveHashByRedis("category", key, cate);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                //5�ر�
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //ʹ��set���Ͻ��д洢 ����ʹ��catid�ֶν��в�ѯ ��ʽ�� [catid]:productid
    public void initProduct() {//set
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //1�����������ڴ�
            Class.forName("org.gjt.mm.mysql.Driver");
            //2Ӧ��������������
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mpet?useUnicode=true&characterEncoding=utf-8",
                    "root", "root");
            //redis.ִ��SQL���
            String sql = "select * from product p,category c where c.catid=p.catid ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            //4���������
            while (rs.next()) {
                Category category = new Category();
                category.setCatid(rs.getString("c.catid"));
                category.setDescn(rs.getString("c.descn"));
                category.setName(rs.getString("c.name"));
                Product pro = new Product();
                String key = rs.getString("p.productid");
                pro.setCatid(rs.getString("p.catid"));
                pro.setProductid(key);
                pro.setPic(rs.getString("p.pic"));
                pro.setName(rs.getString("p.name"));
                pro.setDescn(rs.getString("p.descn"));

                pro.setCategory(category);
                initDao.saveSetByRedis(rs.getString("p.catid") + ":" + key, pro);


            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                //5�ر�
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void initCarts() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //1�����������ڴ�
            Class.forName("org.gjt.mm.mysql.Driver");
            //2Ӧ��������������
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mpet?useUnicode=true&characterEncoding=utf-8",
                    "root", "root");
            //redis.ִ��SQL���
            String sql = "select * from cart c ,item i " +
                    "where c.itemid=i.itemid " +
                    "order by orderid asc ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            String key = "";


            while (rs.next()) {
                //orderid:itemid:productid
                key = rs.getString("c.username") + ":" + rs.getInt("c.orderid") + ":" + rs.getString("c.itemid") + ":" + rs.getString("i.productid");

                initDao.saveStringByRedis("carts:" + key, rs.getInt("quantity"));
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                //5�ر�
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void initOrders() {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        try {
            //1�����������ڴ�
            Class.forName("org.gjt.mm.mysql.Driver");
            //2Ӧ��������������
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mpet?useUnicode=true&characterEncoding=utf-8",
                    "root", "root");
                //redis.ִ��SQL���
                String sql1 = "select * from orders order by orderid asc";
                pstmt = conn.prepareStatement(sql1);

                rs1 = pstmt.executeQuery();
                String username="";
                Integer key = 0;

                boolean flag = true;//true:��orderdate�ֶ�Ϊnull ,û����ɶ���
                //false����orderdate�ֶβ�Ϊnull����ǰ�����Ѿ���ɣ���Ҫ���¿����¶�����
                //4���������
                while (rs1.next()) {
                    key = rs1.getInt("orderid");
                    username = rs1.getString("username");

                    String txt = rs1.getDate("orderdate") == null ? "null" : rs1.getString("orderdate") + ":" + rs1.getBigDecimal("totalprice");
                    //System.out.printf("%s%s","orders:"+username+":"+key,txt);

                    initDao.saveStringByRedis("orders:" + username + ":" + key, txt);
                }

                String sql2 = "select * " +
                        "from  " +
                        "(select max(orderid) mid,username uname " +
                        "from orders  " +
                        "where username in " +
                        "(select username from orders group by username) " +
                        "group by username " +
                        "order by orderdate desc ) as t,orders o " +
                        "where t.mid=o.orderid and t.uname=o.username";
                pstmt = conn.prepareStatement(sql2);


                rs1 = pstmt.executeQuery();

                while (rs1.next()) {
                    username = rs1.getString("username");
                    flag = rs1.getDate("orderdate") == null;
                    key = rs1.getInt("orderid");
                    //1:�ǳ�ʼ��ordersʱ��orderdate�ֶ�Ϊnull��Ϊ1�����׸��ַ���0�ǣ�֤���ö����Ѿ��ύ��
                    initDao.saveStringByRedis("maxid:" + username, (flag == true ? "1" : "0") + key);
                }



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                //5�ر�
                rs1.close();

                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    //ʹ��set���Ͻ��д洢 ����ʹ��productid�ֶν��в�ѯ [productid]:[itemid]=key
    public void initItem() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //1�����������ڴ�
            Class.forName("org.gjt.mm.mysql.Driver");
            //2Ӧ��������������
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mpet?useUnicode=true&characterEncoding=utf-8",
                    "root", "root");
            //redis.ִ��SQL���
            String sql = "select * from item i,product p,category c "
                    + "where c.catid=p.catid and "
                    + "i.productid=p.productid ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            //4���������
            while (rs.next()) {

                Category category = new Category();
                category.setCatid(rs.getString("c.catid"));
                category.setDescn(rs.getString("c.descn"));
                category.setName(rs.getString("c.name"));
                Product pro = new Product();

                pro.setCatid(rs.getString("p.catid"));
                pro.setProductid(rs.getString("p.productid"));
                pro.setPic(rs.getString("p.pic"));
                pro.setName(rs.getString("p.name"));
                pro.setDescn(rs.getString("p.descn"));

                pro.setCategory(category);

                Item item = new Item();
                String key = rs.getString("i.itemid");

                item.setItemid(key);

                item.setProductid(rs.getString("i.productid"));

                item.setListprice(rs.getBigDecimal("i.listprice"));
                item.setAttr1(rs.getString("i.attr1"));
                item.setProduct(pro);
                initDao.saveSetByRedis(rs.getString("i.productid") + ":" + key, item);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                //5�ر�
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
