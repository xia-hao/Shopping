package com.shopping.service.impl;

import com.shopping.domain.SlideShow;
import com.shopping.mapper.SlideShowMapper;
import com.shopping.service.SlideShowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PACKAGE_NAME: com.shopping.service.impl
 * @author: XIA
 * @NAME: SlideShowServiceImpl
 * @DATE: 2020/3/28
 **/
@Service
public class SlideShowServiceImpl implements SlideShowService {
    @Resource
    private SlideShowMapper slideShowMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<SlideShow> getBySlideShowByProductidANDType(Integer pid,String type) {
        return slideShowMapper.getBySlideShowByProductidANDType(pid,type);
    }
}
