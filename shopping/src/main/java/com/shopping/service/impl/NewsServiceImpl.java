package com.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.shopping.domain.News;
import com.shopping.mapper.NewsMapper;
import com.shopping.service.NewsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PACKAGE_NAME: com.shopping.service.impl
 * @author: XIA
 * @NAME: NewsService
 * @DATE: 2020/3/20
 **/

@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsMapper newsMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<News> selectNewsList(News news) {
        PageHelper.startPage(news.getPageNo(),news.getPageSize());
        return newsMapper.selectNewsList();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public News getNewsById(Integer id) {
        return newsMapper.getNewsById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int getNewsCount() {
        return newsMapper.getNewsCount();
    }
}
