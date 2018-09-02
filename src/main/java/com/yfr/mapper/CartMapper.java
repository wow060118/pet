package com.yfr.mapper;

import com.yfr.model.Cart;
import com.yfr.model.CartExample;
import com.yfr.model.CartKey;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
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

    @Insert(value= "{ CALL  addCart9( " +
            "#{in_itemid, mode=IN, jdbcType=VARCHAR}," +
            "#{in_username, mode=IN, jdbcType=VARCHAR}," +
            "#{in_quantity, mode=IN, jdbcType=VARCHAR}," +
            "#{out_oid, mode=OUT, jdbcType=VARCHAR})}")
    @Options(statementType = StatementType.CALLABLE)
    void addCart(Map map);


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