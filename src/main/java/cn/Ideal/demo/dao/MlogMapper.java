package cn.Ideal.demo.dao;

import cn.Ideal.demo.entity.Mlog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MlogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mlog
     *
     * @mbggenerated Fri Apr 26 20:07:20 GMT+08:00 2019
     */
    @Delete({
        "delete from mlog",
        "where lid = #{lid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer lid);
    @Delete({
            "delete from mlog"
    })
    int mlogInit( );
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mlog
     *
     * @mbggenerated Fri Apr 26 20:07:20 GMT+08:00 2019
     */
    @Insert({
        "insert into mlog (lid, ",
        "loper, lcontent,ltime)",
        "values (#{lid,jdbcType=INTEGER},  ",
        "#{loper,jdbcType=VARCHAR}, #{lcontent,jdbcType=VARCHAR},#{ltime,jdbcType=TIMESTAMP})"
    })
    int insert(Mlog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mlog
     *
     * @mbggenerated Fri Apr 26 20:07:20 GMT+08:00 2019
     */
    @InsertProvider(type=MlogSqlProvider.class, method="insertSelective")
    int insertSelective(Mlog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mlog
     *
     * @mbggenerated Fri Apr 26 20:07:20 GMT+08:00 2019
     */
    @Select({
        "select",
        "lid, ltime, loper, lcontent",
        "from mlog",
        "where lid = #{lid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="lid", property="lid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ltime", property="ltime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="loper", property="loper", jdbcType=JdbcType.VARCHAR),
        @Result(column="lcontent", property="lcontent", jdbcType=JdbcType.VARCHAR)
    })
    Mlog selectByPrimaryKey(Integer lid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mlog
     *
     * @mbggenerated Fri Apr 26 20:07:20 GMT+08:00 2019
     */
    @UpdateProvider(type=MlogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Mlog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mlog
     *
     * @mbggenerated Fri Apr 26 20:07:20 GMT+08:00 2019
     */
    @Update({
        "update mlog",
        "set ltime = #{ltime,jdbcType=TIMESTAMP},",
          "loper = #{loper,jdbcType=VARCHAR},",
          "lcontent = #{lcontent,jdbcType=VARCHAR}",
        "where lid = #{lid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Mlog record);


    @Select({
            "select",
            "lid, ltime, loper, lcontent",
            "from mlog order by ltime desc"
    })
    @Results({
            @Result(column="lid", property="lid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ltime", property="ltime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="loper", property="loper", jdbcType=JdbcType.VARCHAR),
            @Result(column="lcontent", property="lcontent", jdbcType=JdbcType.VARCHAR)
    })
   List <Mlog> selectAll();
}