package com.shopping.domain.excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PACKAGE_NAME: com.shopping.domain.excel
 * @author: XIA
 * @NAME: ExcelHead
 * @DATE: 2020/7/4
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelHead {
    private String excelName;             //Excel名
    private String entityName;            //实体类属性名
//    private boolean required=false;      //值必填
}
