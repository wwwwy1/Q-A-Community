package cn.Ideal.demo.service;

import cn.Ideal.demo.entity.Statistic;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwwwy
 * @since 2020-04-22
 */
public interface IStatisticService extends IService<Statistic> {
	Integer lastWeekCountByUserId(String userId);
	Integer lastWeekCountByTagsId(Integer tagsId);
	List<Statistic> lastWeekCountUserTop();
}
