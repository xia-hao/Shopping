package com.shopping.service.impl;

import com.shopping.domain.District;
import com.shopping.mapper.DistrictMapper;
import com.shopping.service.DistrictService;
import com.shopping.utils.utils.DistrictVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @PACKAGE_NAME: com.shopping.service.impl
 * @author: XIA
 * @NAME: DistrictServiceImpl
 * @DATE: 2020/4/5
 **/
@Service
public class DistrictServiceImpl implements DistrictService {
    @Resource
    private DistrictMapper districtMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<District> selectDistrictByPid(Integer pid) {
        if(null == pid){
            pid = 1;
        }
        return districtMapper.selectDistrictByPid(pid);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<DistrictVo> selectDistrict() {
        List<DistrictVo> districtVos1 = new ArrayList<>();
        List<District> districtList1 = selectDistrictByPid(1);
        for (District district1 : districtList1){
            DistrictVo districtVo1 = new DistrictVo();
            districtVo1.setLabel(district1.getDistrictName());
            districtVo1.setValue(district1.getId());
            districtVos1.add(districtVo1);

            List<DistrictVo> districtVos2 = new ArrayList<>();
            List<District> districtList2 = selectDistrictByPid(district1.getId());
            for (District district2: districtList2){
                DistrictVo districtVo2 = new DistrictVo();
                districtVo2.setLabel(district2.getDistrictName());
                districtVo2.setValue(district2.getId());
                districtVos2.add(districtVo2);
                districtVo1.setChildren(districtVos2);

                List<DistrictVo> districtVos3 = new ArrayList<>();
                List<District> districtList3 = selectDistrictByPid(district2.getId());
                for (District district3 : districtList3){
                    DistrictVo districtVo3 = new DistrictVo();
                    districtVo3.setLabel(district3.getDistrictName());
                    districtVo3.setValue(district3.getId());
                    districtVos3.add(districtVo3);
                    districtVo2.setChildren(districtVos3);
                }
            }
        }
        return districtVos1;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public District getDistrictById(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }
}
