package com.shopping.service;

import com.shopping.domain.Dictionary;

import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.service
 * @author: XIA
 * @NAME: DictionaryService
 * @DATE: 2020/4/21
 **/
public interface DictionaryService {
    Map selectDictionaryByType(String type);
}
