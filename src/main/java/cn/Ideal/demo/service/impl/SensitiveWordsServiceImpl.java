package cn.Ideal.demo.service.impl;

import cn.Ideal.demo.entity.SensitiveWords;
import cn.Ideal.demo.mapper.SensitiveWordsMapper;
import cn.Ideal.demo.service.ISensitiveWordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwwwy
 * @since 2020-02-25
 */
@Service
public class SensitiveWordsServiceImpl extends ServiceImpl<SensitiveWordsMapper, SensitiveWords> implements ISensitiveWordsService {

}
