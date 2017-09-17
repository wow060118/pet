package com.weikun.init;

import com.weikun.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * 创建者：weikun【YST】   日期：2017/9/17
 * 说说功能：
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
            //1加载驱动到内存
            Class.forName("org.gjt.mm.mysql.Driver");
            //2应用驱动进行联接
            conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1/mpet?useUnicode=true&characterEncoding=utf-8",
                    "root","root");
            //redis.执行SQL语句
            String sql="select * from account ";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            //4遍历结果集
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
                //5关闭
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
