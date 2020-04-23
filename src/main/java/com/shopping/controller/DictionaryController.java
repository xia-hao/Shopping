package com.shopping.controller;

import com.shopping.service.DictionaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: DictionaryController
 * @DATE: 2020/4/21
 **/
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {
    @Resource
    private DictionaryService dictionaryService;

    @GetMapping("/list")
    public Map list(String type){
        return dictionaryService.selectDictionaryByType(type);
    }
}
