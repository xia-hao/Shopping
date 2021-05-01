package com.shopping.service;

import com.shopping.domain.News;

import java.util.List;

/**
 * @PACKAGE_NAME: com.shopping.service
 * @author: XIA
 * @NAME: NewsService
 * @DATE: 2020/3/20
 **/
public interface NewsService {
    List<News> selectNewsList(News news);

    News getNewsById(Integer id);

    int getNewsCount();
}
