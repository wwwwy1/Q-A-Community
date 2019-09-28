package cn.Ideal.demo.dao;

import cn.Ideal.demo.entity.Mrandom;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

@Component
public interface MrandomMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mrandom
     *
     * @mbggenerated Fri Apr 26 20:07:20 GMT+08:00 2019
     */
    @Delete({
        "delete from mrandom",
        "where rid = #{rid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer rid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mrandom
     *
     * @mbggenerated Fri Apr 26 20:07:20 GMT+08:00 2019
     */
    @Insert({
        "insert into mrandom (rid, rcontent, ",
        "ran1, ran2, ran3)",
        "values (#{rid,jdbcType=INTEGER}, #{rcontent,jdbcType=VARCHAR}, ",
        "#{ran1,jdbcType=VARCHAR}, #{ran2,jdbcType=VARCHAR}, #{ran3,jdbcType=VARCHAR})"
    })
    int insert(Mrandom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mrandom
     *
     * @mbggenerated Fri Apr 26 20:07:20 GMT+08:00 2019
     */
    @InsertProvider(type=MrandomSqlProvider.class, method="insertSelective")
    int insertSelective(Mrandom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mrandom
     *
     * @mbggenerated Fri Apr 26 20:07:20 GMT+08:00 2019
     */
    @Select({
        "select",
        "rid, rcontent, ran1, ran2, ran3",
        "from mrandom",
        "where rid = #{rid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rcontent", property="rcontent", jdbcType=JdbcType.VARCHAR),
        @Result(column="ran1", property="ran1", jdbcType=JdbcType.VARCHAR),
        @Result(column="ran2", property="ran2", jdbcType=JdbcType.VARCHAR),
        @Result(column="ran3", property="ran3", jdbcType=JdbcType.VARCHAR)
    })
    Mrandom selectByPrimaryKey(Integer rid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mrandom
     *
     * @mbggenerated Fri Apr 26 20:07:20 GMT+08:00 2019
     */
    @UpdateProvider(type=MrandomSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Mrandom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mrandom
     *
     * @mbggenerated Fri Apr 26 20:07:20 GMT+08:00 2019
     */
    @Update({
        "update mrandom",
        "set rcontent = #{rcontent,jdbcType=VARCHAR},",
          "ran1 = #{ran1,jdbcType=VARCHAR},",
          "ran2 = #{ran2,jdbcType=VARCHAR},",
          "ran3 = #{ran3,jdbcType=VARCHAR}",
        "where rid = #{rid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Mrandom record);
}