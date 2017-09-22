package com.weikun.mapper;

import com.weikun.model.Account;
import com.weikun.model.AccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface AccountMapper {
    @SelectProvider(type=AccountSqlProvider.class, method="countByExample")
    long countByExample(AccountExample example);

    @DeleteProvider(type=AccountSqlProvider.class, method="deleteByExample")
    int deleteByExample(AccountExample example);

    @Delete({
        "delete from account",
        "where username = #{username,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String username);

    @Insert({
        "insert into account (username, password, ",
        "email, xm, address)",
        "values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{xm,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR})"
    })
    int insert(Account record);

    @InsertProvider(type=AccountSqlProvider.class, method="insertSelective")
    int insertSelective(Account record);

    @SelectProvider(type=AccountSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="xm", property="xm", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR)
    })
    List<Account> selectByExample(AccountExample example);

    @Select({
        "select",
        "username, password, email, xm, address",
        "from account",
        "where username = #{username,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="xm", property="xm", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR)
    })
    Account selectByPrimaryKey(String username);


    @Select({
            "select",
            "username, password, email, xm, address",
            "from account",
            "where username = #{username,jdbcType=VARCHAR} and ",
            "password = #{password,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="xm", property="xm", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR)
    })
    Account login(Account account);

    @UpdateProvider(type=AccountSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    @UpdateProvider(type=AccountSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    @UpdateProvider(type=AccountSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Account record);

    @Update({
        "update account",
        "set password = #{password,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "xm = #{xm,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR}",
        "where username = #{username,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Account record);
}