package com.shopping.controller;

import com.shopping.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: DictionaryController
 * @DATE: 2020/4/21
 **/
@Api(tags = "字典模块")
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {
    @Resource
    private DictionaryService dictionaryService;

    @ApiOperation(value = "根据字典类型查询",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type",value = "字典类型",required = true,paramType = "form")
    })
    @RequestMapping("/list")
    public Map list(HttpServletRequest request,String type){
        return dictionaryService.selectDictionaryByType(type);
    }
}
