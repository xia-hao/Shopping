package com.shopping.service;

import com.shopping.domain.District;
import com.shopping.utils.DistrictVo;

import java.util.List;

/**
 * @PACKAGE_NAME: com.shopping.service
 * @author: XIA
 * @NAME: DistrictService
 * @DATE: 2020/4/5
 **/
public interface DistrictService {
    List<District> selectDistrictByPid(Integer pid);

    List<DistrictVo> selectDistrict();

    District getDistrictById(Integer id);
}
