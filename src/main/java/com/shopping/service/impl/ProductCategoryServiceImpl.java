package com.shopping.service.impl;

import com.shopping.domain.ProductCategory;
import com.shopping.mapper.ProductCategoryMapper;
import com.shopping.service.ProductCategoryService;
import com.shopping.utils.ProductCategoryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @PACKAGE_NAME: com.shopping.service.impl
 * @author: XIA
 * @NAME: ProductCategoryServiceImpl
 * @DATE: 2020/3/14
 **/
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ProductCategory> selectByParentId(Integer parentId) {
        if(null == parentId){
            parentId = 0;
        }
        return productCategoryMapper.selectByParentId(parentId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ProductCategoryVo> selectAllProductCategoryList() {
        //查询一级分类的列表
        List<ProductCategoryVo> productCategory1VoList = new ArrayList<ProductCategoryVo>();
        //查询一级分类
        List<ProductCategory> productCategory1List = selectByParentId(null);
        //查询二级分类
        for (ProductCategory product1Category : productCategory1List) {
            //封装一级分类
            ProductCategoryVo productCategoryVo = new ProductCategoryVo();
            productCategoryVo.setProductCategory(product1Category);

            List<ProductCategoryVo> productCategoryVo1ChildList = new ArrayList<ProductCategoryVo>();
            //根据一级分类查询二级分类
            List<ProductCategory> productCategory2List = selectByParentId(product1Category.getId());
            for (ProductCategory productCategory2 : productCategory2List) {

                ProductCategoryVo productCategoryVo2 = new ProductCategoryVo();
                productCategoryVo2.setProductCategory(productCategory2);
                List<ProductCategoryVo> productCategoryVo2ChildList = new ArrayList<ProductCategoryVo>();
                productCategoryVo1ChildList.add(productCategoryVo2);

                //根据二级分类查询三级分类的列表
                List<ProductCategory> productCategory3List = selectByParentId(productCategory2.getId());
                for (ProductCategory productCategory3 : productCategory3List) {
                    ProductCategoryVo productCategoryVo3 = new ProductCategoryVo();
                    productCategoryVo3.setProductCategory(productCategory3);
                    productCategoryVo2ChildList.add(productCategoryVo3);
                    productCategoryVo2.setProductCategoryVoList(productCategoryVo2ChildList);

                }
            }
            productCategoryVo.setProductCategoryVoList(productCategoryVo1ChildList);
            productCategory1VoList.add(productCategoryVo);
        }
        return productCategory1VoList;
    }
}
