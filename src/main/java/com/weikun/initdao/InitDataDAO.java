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
    public  void getHashByKey() {

        initDao.getHashByKey();
    }
    public  void initAccount() {//hash
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            //1�����������ڴ�
            Class.forName("org.gjt.mm.mysql.Driver");
            //2Ӧ��������������
            conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1/mpet?useUnicode=true&characterEncoding=utf-8",
                    "root","root");
            //redis.ִ��SQL���
            String sql="select * from account ";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            //4���������
            while(rs.next()){
                Account account=new Account();
                String key=rs.getString("username");
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setEmail(rs.getString("email"));
                account.setXm(rs.getString("xm"));
                account.setAddress(rs.getString("address"));
                initDao.saveHashByRedis("account",key,account);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
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




    public  void initProfile() {//hash
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            //1�����������ڴ�
            Class.forName("org.gjt.mm.mysql.Driver");
            //2Ӧ��������������
            conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1/mpet?useUnicode=true&characterEncoding=utf-8",
                    "root","root");
            //redis.ִ��SQL���
            String sql="select * from profile ";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            //4���������
            while(rs.next()){
                Profile pro=new Profile();
                String key=rs.getString("username");
                pro.setUsername(key);
                pro.setLang(rs.getString("lang"));
                pro.setCatid(rs.getString("catid"));
                initDao.saveHashByRedis("profile",key,pro);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
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

    public  void initCategory() {//hash
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            //1�����������ڴ�
            Class.forName("org.gjt.mm.mysql.Driver");
            //2Ӧ��������������
            conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1/mpet?useUnicode=true&characterEncoding=utf-8",
                    "root","root");
            //redis.ִ��SQL���
            String sql="select * from category ";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            //4���������
            while(rs.next()){
                Category cate=new Category();
                String key=rs.getString("catid");
                cate.setCatid(key);
                cate.setName(rs.getString("name"));
                cate.setDescn(rs.getString("descn"));


                initDao.saveHashByRedis("category",key,cate);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
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
    public  void initProduct() {//set
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            //1�����������ڴ�
            Class.forName("org.gjt.mm.mysql.Driver");
            //2Ӧ��������������
            conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1/mpet?useUnicode=true&characterEncoding=utf-8",
                    "root","root");
            //redis.ִ��SQL���
            String sql="select * from product p,category c where c.catid=p.catid ";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            //4���������
            while(rs.next()){
                Category category =new Category();
                category.setCatid(rs.getString("c.catid"));
                category.setDescn(rs.getString("c.descn"));
                category.setName(rs.getString("c.name"));
                Product pro=new Product();
                String key=rs.getString("p.productid");
                pro.setCatid(rs.getString("p.catid"));
                pro.setProductid(key);
                pro.setPic(rs.getString("p.pic"));
                pro.setName(rs.getString("p.name"));
                pro.setDescn(rs.getString("p.descn"));

                pro.setCategory(category);
                initDao.saveSetByRedis(rs.getString("p.catid")+":"+key, pro);


            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
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


    //ʹ��set���Ͻ��д洢 ����ʹ��productid�ֶν��в�ѯ [productid]:[itemid]=key
    public  void initItem() {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            //1�����������ڴ�
            Class.forName("org.gjt.mm.mysql.Driver");
            //2Ӧ��������������
            conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1/mpet?useUnicode=true&characterEncoding=utf-8",
                    "root","root");
            //redis.ִ��SQL���
            String sql="select * from item i,product p,category c "
                    + "where c.catid=p.catid and "
                    + "i.productid=p.productid ";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            //4���������
            while(rs.next()){

                Category category =new Category();
                category.setCatid(rs.getString("c.catid"));
                category.setDescn(rs.getString("c.descn"));
                category.setName(rs.getString("c.name"));
                Product pro=new Product();

                pro.setCatid(rs.getString("p.catid"));
                pro.setProductid(rs.getString("p.productid"));
                pro.setPic(rs.getString("p.pic"));
                pro.setName(rs.getString("p.name"));
                pro.setDescn(rs.getString("p.descn"));

                pro.setCategory(category);

                Item item=new Item();
                String key=rs.getString("i.itemid");

                item.setItemid(key);

                item.setProductid(rs.getString("i.productid"));

                item.setListprice(rs.getBigDecimal("i.listprice"));
                item.setAttr1(rs.getString("i.attr1"));
                item.setProduct(pro);
                initDao.saveSetByRedis(rs.getString("i.productid")+":"+key, item);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
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
