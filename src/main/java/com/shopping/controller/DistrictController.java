package com.shopping.controller;

import com.shopping.domain.District;
import com.shopping.service.DistrictService;
import com.shopping.utils.DistrictVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: DistrictsController
 * @DATE: 2020/4/5
 **/
@RestController
@RequestMapping("/district")
public class DistrictController {
    @Resource
    private DistrictService districtService;

    @GetMapping("/list")
    public Map list(){
        Map<String,Object> map = new HashMap<>();
        List<DistrictVo> districtList = districtService.selectDistrict();
        map.put("districtList",districtList);
        return map;
    }
}
