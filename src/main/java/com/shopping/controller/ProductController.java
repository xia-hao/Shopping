package com.shopping.controller;

import com.github.pagehelper.PageHelper;
import com.shopping.domain.Product;
import com.shopping.service.ProductService;
import com.shopping.utils.excel.ExportExcel;
import org.apache.http.HttpResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: ProductController
 * @DATE: 2020/3/15
 **/
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @RequestMapping("/list")
    public Map list(Product product){
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(product.getPageNo(),product.getPageSize());
        List<Product> productsList = productService.selectProductlist(product);
        map.put("productList",productsList);
        map.put("productCount",productService.getProductCount(product));
        return map;
    }

    @RequestMapping("productById/{id}")
    public Map productById(@PathVariable Integer id){
        Map<String,Object> map = new HashMap<>();
        map.put("getproduct",productService.getProductById(id));
        return map;
    }

    @PostMapping("export_product")
    public Map export_product(@RequestBody Product product, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        List<Product> productsList = productService.selectProductlist(product);

        List<HashMap<String,Object>> dataMap = new ArrayList<>();
        String[] title = {"id","商品名称","商品价格","商品库存","销售量"};
        String[] dataKey = {"id","name","price","stock","salesVolume"};
        for (Product product1:productsList){
            HashMap<String,Object> map1 = new HashMap<>();
            map1.put("id",product1.getId());
            map1.put("name",product1.getName());
            map1.put("price",product1.getPrice());
            map1.put("stock",product1.getStock());
            map1.put("salesVolume",product1.getSalesVolume());
            dataMap.add(map1);
        }
        String mssage = ExportExcel.export_data(response,"商品信息",title,dataKey,dataMap);
        map.put("mssage",mssage);
        return map;
    }

    @GetMapping("getexport_product")
    public Map getexport_product(Product product, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        List<Product> productsList = productService.selectProductlist(product);

        List<HashMap<String,Object>> dataMap = new ArrayList<>();
        String[] title = {"id","商品名称","商品价格","商品库存","销售量"};
        String[] dataKey = {"id","name","price","stock","salesVolume"};
        for (Product product1:productsList){
            HashMap<String,Object> map1 = new HashMap<>();
            map1.put("id",product1.getId());
            map1.put("name",product1.getName());
            map1.put("price",product1.getPrice());
            map1.put("stock",product1.getStock());
            map1.put("salesVolume",product1.getSalesVolume());
            dataMap.add(map1);
        }
        String mssage = ExportExcel.export_data(response,"商品信息",title,dataKey,dataMap);
        map.put("mssage",mssage);
        return map;
    }
}
