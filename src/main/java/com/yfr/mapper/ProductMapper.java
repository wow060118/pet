package com.yfr.mapper;

import com.yfr.model.Product;
import com.yfr.model.ProductExample;
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

public interface ProductMapper {
    @SelectProvider(type=ProductSqlProvider.class, method="countByExample")
    long countByExample(ProductExample example);

    @DeleteProvider(type=ProductSqlProvider.class, method="deleteByExample")
    int deleteByExample(ProductExample example);

    @Delete({
        "delete from product",
        "where productid = #{productid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String productid);

    @Insert({
        "insert into product (productid, catid, ",
        "name, descn, pic)",
        "values (#{productid,jdbcType=VARCHAR}, #{catid,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{descn,jdbcType=VARCHAR}, #{pic,jdbcType=VARCHAR})"
    })
    int insert(Product record);

    @InsertProvider(type=ProductSqlProvider.class, method="insertSelective")
    int insertSelective(Product record);

    @SelectProvider(type=ProductSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="productid", property="productid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="catid", property="catid", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="descn", property="descn", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR)
    })
    List<Product> selectByExample(ProductExample example);

    @Select({
        "select",
        "productid, catid, name, descn, pic",
        "from product",
        "where productid = #{productid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="productid", property="productid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="catid", property="catid", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="descn", property="descn", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR)
    })
    Product selectByPrimaryKey(String productid);

    @UpdateProvider(type=ProductSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    @UpdateProvider(type=ProductSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    @UpdateProvider(type=ProductSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Product record);

    @Update({
        "update product",
        "set catid = #{catid,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "descn = #{descn,jdbcType=VARCHAR},",
          "pic = #{pic,jdbcType=VARCHAR}",
        "where productid = #{productid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Product record);
}