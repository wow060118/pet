package com.weikun.mapper;

import com.weikun.model.Item;
import com.weikun.model.ItemExample;
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

public interface ItemMapper {
    @SelectProvider(type=ItemSqlProvider.class, method="countByExample")
    long countByExample(ItemExample example);

    @DeleteProvider(type=ItemSqlProvider.class, method="deleteByExample")
    int deleteByExample(ItemExample example);

    @Delete({
        "delete from item",
        "where itemid = #{itemid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String itemid);

    @Insert({
        "insert into item (itemid, productid, ",
        "listprice, unitcost, ",
        "status, attr1)",
        "values (#{itemid,jdbcType=VARCHAR}, #{productid,jdbcType=VARCHAR}, ",
        "#{listprice,jdbcType=DECIMAL}, #{unitcost,jdbcType=DECIMAL}, ",
        "#{status,jdbcType=VARCHAR}, #{attr1,jdbcType=VARCHAR})"
    })
    int insert(Item record);

    @InsertProvider(type=ItemSqlProvider.class, method="insertSelective")
    int insertSelective(Item record);

    @SelectProvider(type=ItemSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="itemid", property="itemid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="productid", property="productid", jdbcType=JdbcType.VARCHAR),
        @Result(column="listprice", property="listprice", jdbcType=JdbcType.DECIMAL),
        @Result(column="unitcost", property="unitcost", jdbcType=JdbcType.DECIMAL),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="attr1", property="attr1", jdbcType=JdbcType.VARCHAR)
    })
    List<Item> selectByExample(ItemExample example);

    @Select({
        "select",
        "itemid, productid, listprice, unitcost, status, attr1",
        "from item",
        "where itemid = #{itemid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="itemid", property="itemid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="productid", property="productid", jdbcType=JdbcType.VARCHAR),
        @Result(column="listprice", property="listprice", jdbcType=JdbcType.DECIMAL),
        @Result(column="unitcost", property="unitcost", jdbcType=JdbcType.DECIMAL),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="attr1", property="attr1", jdbcType=JdbcType.VARCHAR)
    })
    Item selectByPrimaryKey(String itemid);

    @UpdateProvider(type=ItemSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemExample example);

    @UpdateProvider(type=ItemSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Item record, @Param("example") ItemExample example);

    @UpdateProvider(type=ItemSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Item record);

    @Update({
        "update item",
        "set productid = #{productid,jdbcType=VARCHAR},",
          "listprice = #{listprice,jdbcType=DECIMAL},",
          "unitcost = #{unitcost,jdbcType=DECIMAL},",
          "status = #{status,jdbcType=VARCHAR},",
          "attr1 = #{attr1,jdbcType=VARCHAR}",
        "where itemid = #{itemid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Item record);
}