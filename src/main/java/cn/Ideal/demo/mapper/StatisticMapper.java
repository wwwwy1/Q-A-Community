package cn.Ideal.demo.mapper;

import cn.Ideal.demo.entity.Statistic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wwwwy
 * @since 2020-04-22
 */
public interface StatisticMapper extends BaseMapper<Statistic> {
	@Select("select sum(count) as res from statistic  where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(insert_date) and user_id = #{userId,jdbcType=VARCHAR}")
	Integer lastWeekCountByUserId(String userId);

	@Select("select sum(count) as res from statistic  where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(insert_date) and tags_id = #{tagsId,jdbcType=INTEGER}")
	Integer lastWeekCountByTagsId(Integer tagsId);

}
