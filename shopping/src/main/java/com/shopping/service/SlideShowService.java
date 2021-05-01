package com.shopping.service;

import com.shopping.domain.SlideShow;

import java.util.List;

/**
 * @PACKAGE_NAME: com.shopping.service
 * @author: XIA
 * @NAME: SlideShowService
 * @DATE: 2020/3/28
 **/
public interface SlideShowService {
    List<SlideShow> getBySlideShowByProductidANDType(Integer pid,String type);
}
