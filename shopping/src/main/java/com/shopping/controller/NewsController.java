package com.shopping.controller;

import com.shopping.domain.News;
import com.shopping.service.NewsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: NewsController
 * @DATE: 2020/3/20
 **/

@RestController
@RequestMapping("/news")
public class NewsController {

    @Resource
    private NewsService newsService;

    @RequestMapping("/list")
    public Map list(News news){
        Map<String,Object> map = new HashMap<>();
        map.put("newsList",newsService.selectNewsList(news));
        map.put("newsCount",newsService.getNewsCount());
        return map;
    }

    @RequestMapping("/getnews/{id}")
    public Map getnews(@PathVariable Integer id){
        Map<String,Object> map = new HashMap<>();
        map.put("getnews",newsService.getNewsById(id));
        return map;
    }
}
