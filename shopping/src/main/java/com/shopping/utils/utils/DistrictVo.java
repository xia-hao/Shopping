package com.shopping.utils.utils;

import java.util.List;

/**
 * @PACKAGE_NAME: com.shopping.utils
 * @author: XIA
 * @NAME: DistrictVo
 * @DATE: 2020/4/5
 **/
public class DistrictVo {
    private String label;
    private Integer value;
    private List<DistrictVo> children;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<DistrictVo> getChildren() {
        return children;
    }

    public void setChildren(List<DistrictVo> children) {
        this.children = children;
    }
}
