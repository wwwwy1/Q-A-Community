package cn.Ideal.demo.service.impl;

import cn.Ideal.demo.entity.Image;
import cn.Ideal.demo.mapper.ImageMapper;
import cn.Ideal.demo.service.IImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwwwy
 * @since 2022-05-06
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements IImageService {

}
