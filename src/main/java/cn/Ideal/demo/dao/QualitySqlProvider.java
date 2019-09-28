package cn.Ideal.demo.dao;

import cn.Ideal.demo.entity.Quality;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class QualitySqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quality
     *
     * @mbggenerated Wed May 22 18:37:34 GMT+08:00 2019
     */
    public String insertSelective(Quality record) {
        BEGIN();
        INSERT_INTO("quality");
        
        if (record.getQid() != null) {
            VALUES("qId", "#{qid,jdbcType=INTEGER}");
        }
        
        if (record.getQone() != null) {
            VALUES("qOne", "#{qone,jdbcType=INTEGER}");
        }
        
        if (record.getQtwo() != null) {
            VALUES("qTwo", "#{qtwo,jdbcType=INTEGER}");
        }
        
        if (record.getQthree() != null) {
            VALUES("qThree", "#{qthree,jdbcType=INTEGER}");
        }
        
        if (record.getQfour() != null) {
            VALUES("qFour", "#{qfour,jdbcType=INTEGER}");
        }
        
        if (record.getQfive() != null) {
            VALUES("qFive", "#{qfive,jdbcType=INTEGER}");
        }
        
        if (record.getQsix() != null) {
            VALUES("qSix", "#{qsix,jdbcType=INTEGER}");
        }
        
        if (record.getQseven() != null) {
            VALUES("qSeven", "#{qseven,jdbcType=INTEGER}");
        }
        
        if (record.getQeight() != null) {
            VALUES("qEight", "#{qeight,jdbcType=INTEGER}");
        }
        
        if (record.getQnine() != null) {
            VALUES("qNine", "#{qnine,jdbcType=INTEGER}");
        }
        
        if (record.getUid() != null) {
            VALUES("uId", "#{uid,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quality
     *
     * @mbggenerated Wed May 22 18:37:34 GMT+08:00 2019
     */
    public String updateByPrimaryKeySelective(Quality record) {
        BEGIN();
        UPDATE("quality");
        
        if (record.getQone() != null) {
            SET("qOne = #{qone,jdbcType=INTEGER}");
        }
        
        if (record.getQtwo() != null) {
            SET("qTwo = #{qtwo,jdbcType=INTEGER}");
        }
        
        if (record.getQthree() != null) {
            SET("qThree = #{qthree,jdbcType=INTEGER}");
        }
        
        if (record.getQfour() != null) {
            SET("qFour = #{qfour,jdbcType=INTEGER}");
        }
        
        if (record.getQfive() != null) {
            SET("qFive = #{qfive,jdbcType=INTEGER}");
        }
        
        if (record.getQsix() != null) {
            SET("qSix = #{qsix,jdbcType=INTEGER}");
        }
        
        if (record.getQseven() != null) {
            SET("qSeven = #{qseven,jdbcType=INTEGER}");
        }
        
        if (record.getQeight() != null) {
            SET("qEight = #{qeight,jdbcType=INTEGER}");
        }
        
        if (record.getQnine() != null) {
            SET("qNine = #{qnine,jdbcType=INTEGER}");
        }
        
        if (record.getUid() != null) {
            SET("uId = #{uid,jdbcType=INTEGER}");
        }
        
        WHERE("qId = #{qid,jdbcType=INTEGER}");
        
        return SQL();
    }
}