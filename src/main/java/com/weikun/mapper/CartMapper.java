package com.weikun.mapper;

import com.weikun.model.Cart;
import com.weikun.model.CartExample;
import com.weikun.model.CartKey;
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

public interface CartMapper {
    @SelectProvider(type=CartSqlProvider.class, method="countByExample")
    long countByExample(CartExample example);

    @DeleteProvider(type=CartSqlProvider.class, method="deleteByExample")
    int deleteByExample(CartExample example);

    @Delete({
        "delete from cart",
        "where orderid = #{orderid,jdbcType=INTEGER}",
          "and itemid = #{itemid,jdbcType=VARCHAR}",
          "and username = #{username,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(CartKey key);

    @Insert({
        "insert into cart (orderid, itemid, ",
        "username, quantity)",
        "values (#{orderid,jdbcType=INTEGER}, #{itemid,jdbcType=VARCHAR}, ",
        "#{username,jdbcType=VARCHAR}, #{quantity,jdbcType=INTEGER})"
    })
    int insert(Cart record);

    @InsertProvider(type=CartSqlProvider.class, method="insertSelective")
    int insertSelective(Cart record);

    @SelectProvider(type=CartSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="orderid", property="orderid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="itemid", property="itemid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER)
    })
    List<Cart> selectByExample(CartExample example);

    @Select({
        "select",
        "orderid, itemid, username, quantity",
        "from cart",
        "where orderid = #{orderid,jdbcType=INTEGER}",
          "and itemid = #{itemid,jdbcType=VARCHAR}",
          "and username = #{username,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="orderid", property="orderid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="itemid", property="itemid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER)
    })
    Cart selectByPrimaryKey(CartKey key);

    @UpdateProvider(type=CartSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    @UpdateProvider(type=CartSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    @UpdateProvider(type=CartSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Cart record);

    @Update({
        "update cart",
        "set quantity = #{quantity,jdbcType=INTEGER}",
        "where orderid = #{orderid,jdbcType=INTEGER}",
          "and itemid = #{itemid,jdbcType=VARCHAR}",
          "and username = #{username,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Cart record);
}