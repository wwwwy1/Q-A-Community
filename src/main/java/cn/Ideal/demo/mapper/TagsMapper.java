package cn.Ideal.demo.mapper;

import cn.Ideal.demo.entity.Tags;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-11
 */
public interface TagsMapper extends BaseMapper<Tags> {
	@Update("UPDATE tags SET tags_count = tags_count+1 WHERE id in ( ${ids}) ")
	int useTag(@Param("ids") String ids);
}
