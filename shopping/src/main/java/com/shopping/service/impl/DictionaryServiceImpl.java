package com.shopping.service.impl;

import com.shopping.domain.Dictionary;
import com.shopping.mapper.DictionaryMapper;
import com.shopping.service.DictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.service.impl
 * @author: XIA
 * @NAME: DictionaryServiceImpl
 * @DATE: 2020/4/21
 **/
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Resource
    private DictionaryMapper dictionaryMapper;

    @Override
    public Map selectDictionaryByType(String type) {
        Map<String,Object> map = new HashMap<>();
        map.put("code","901");
        List<Dictionary> dictionaryList = dictionaryMapper.selectDictionaryByType(type);
        if(!dictionaryList.isEmpty()){
            map.put("code","1000");
            map.put("dictionaryList",dictionaryList);
        }
        return map;
    }
}
