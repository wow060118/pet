package com.weikun.init;

import com.weikun.model.Account;
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
    private InitDAO initDao;
    public  void getHashByKey() {

        initDao.getHashByKey();
    }
    public  void initAccount() {
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

}
