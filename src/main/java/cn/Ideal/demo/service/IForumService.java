package cn.Ideal.demo.service;

import cn.Ideal.demo.entity.Forum;
import cn.Ideal.demo.util.SolrPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-11
 */
public interface IForumService extends IService<Forum> {
	SolrPage page(String keywords,Integer current,Integer pageSize) throws Exception;
}
