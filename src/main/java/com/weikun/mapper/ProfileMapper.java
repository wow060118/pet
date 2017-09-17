package com.weikun.mapper;

import com.weikun.model.Profile;
import com.weikun.model.ProfileExample;
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

public interface ProfileMapper {
    @SelectProvider(type=ProfileSqlProvider.class, method="countByExample")
    long countByExample(ProfileExample example);

    @DeleteProvider(type=ProfileSqlProvider.class, method="deleteByExample")
    int deleteByExample(ProfileExample example);

    @Delete({
        "delete from profile",
        "where username = #{username,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String username);

    @Insert({
        "insert into profile (username, lang, ",
        "catid)",
        "values (#{username,jdbcType=VARCHAR}, #{lang,jdbcType=VARCHAR}, ",
        "#{catid,jdbcType=VARCHAR})"
    })
    int insert(Profile record);

    @InsertProvider(type=ProfileSqlProvider.class, method="insertSelective")
    int insertSelective(Profile record);

    @SelectProvider(type=ProfileSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="lang", property="lang", jdbcType=JdbcType.VARCHAR),
        @Result(column="catid", property="catid", jdbcType=JdbcType.VARCHAR)
    })
    List<Profile> selectByExample(ProfileExample example);

    @Select({
        "select",
        "username, lang, catid",
        "from profile",
        "where username = #{username,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="lang", property="lang", jdbcType=JdbcType.VARCHAR),
        @Result(column="catid", property="catid", jdbcType=JdbcType.VARCHAR)
    })
    Profile selectByPrimaryKey(String username);

    @UpdateProvider(type=ProfileSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Profile record, @Param("example") ProfileExample example);

    @UpdateProvider(type=ProfileSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Profile record, @Param("example") ProfileExample example);

    @UpdateProvider(type=ProfileSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Profile record);

    @Update({
        "update profile",
        "set lang = #{lang,jdbcType=VARCHAR},",
          "catid = #{catid,jdbcType=VARCHAR}",
        "where username = #{username,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Profile record);
}