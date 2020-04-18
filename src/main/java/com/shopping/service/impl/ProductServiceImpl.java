package com.shopping.service.impl;

import com.shopping.domain.Product;
import com.shopping.mapper.ProductMapper;
import com.shopping.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PACKAGE_NAME: com.shopping.service.impl
 * @author: XIA
 * @NAME: ProductServiceImpl
 * @DATE: 2020/3/15
 **/
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Product> selectProductlist(Product product) {
        return productMapper.selectProductlist(product.getName(),product.getCategoryId(),product.getPrice1(),product.getPrice2());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int getProductCount(Product product) {
        return productMapper.getProductCount(product.getName(),product.getCategoryId(),product.getPrice1(),product.getPrice2());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Product getProductById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }
}
