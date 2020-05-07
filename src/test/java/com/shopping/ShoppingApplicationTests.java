package com.shopping;

import com.shopping.domain.District;
import com.shopping.domain.ProductCategory;
import com.shopping.domain.SlideShow;
import com.shopping.mapper.UserMapper;
import com.shopping.service.DistrictService;
import com.shopping.service.OrderService;
import com.shopping.service.ProductCategoryService;
import com.shopping.service.SlideShowService;
import com.shopping.utils.utils.DistrictVo;
import com.sun.org.apache.xml.internal.utils.IntVector;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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
            System.out.println("商品一级菜单：" + productCategory1.getName());
            List<ProductCategory> list2 = productCategoryService.selectByParentId(productCategory1.getId());
            for (ProductCategory productCategory2 : list2) {
                System.out.println("商品二级菜单：" + productCategory2.getName());
                List<ProductCategory> list3 = productCategoryService.selectByParentId(productCategory2.getId());
                for (ProductCategory productCategory3 : list3) {
                    System.out.println("商品三级菜单：" + productCategory3.getName());
                }
            }
        }
    }

    @Test
    void test() {
        URL url = ClassLoader.getSystemClassLoader().getResource("./");
        File file = new File(url.getPath().substring(0, url.getPath().indexOf("shopping"))
                + File.separator + "shopping-ui" + File.separator + "static" + File.separator + "images");
        System.out.println(file);
    }
}
