package com.shopping;

import com.shopping.domain.District;
import com.shopping.domain.ProductCategory;
import com.shopping.domain.SlideShow;
import com.shopping.service.DistrictService;
import com.shopping.service.OrderService;
import com.shopping.service.ProductCategoryService;
import com.shopping.service.SlideShowService;
import com.sun.org.apache.xml.internal.utils.IntVector;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ShoppingApplicationTests {

    @Resource
    private ProductCategoryService productCategoryService;

    @Resource
    private SlideShowService slideShowService;

    @Resource
    private DistrictService districtService;

    @Resource
    private OrderService orderService;

    @Test
    void contextLoads() {
        List<ProductCategory> list1 = productCategoryService.selectByParentId(null);
        for (ProductCategory productCategory1 : list1) {
            System.out.println("商品一级菜单："+productCategory1.getName());
            List<ProductCategory> list2 = productCategoryService.selectByParentId(productCategory1.getId());
            for(ProductCategory productCategory2:list2){
                System.out.println("商品二级菜单："+productCategory2.getName());
                List<ProductCategory> list3 = productCategoryService.selectByParentId(productCategory2.getId());
                for(ProductCategory productCategory3:list3){
                    System.out.println("商品三级菜单："+productCategory3.getName());
                }
            }
        }
    }

    @Test
    void test() {
        List<District> districtsList = districtService.selectDistrictByPid(1);
        for (District district : districtsList){
            System.out.println("==========="+district.getDistrictName());
        }
    }
    @Test
    void test1() {
        //Map<String,Object> map = orderService.getOrderByOrderNo("62D85921406D440299B4AE3036B28BB8");
    }
}
