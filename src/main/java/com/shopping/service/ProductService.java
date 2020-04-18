package com.shopping.service;

import com.shopping.domain.Product;

import java.util.List;

/**
 * @PACKAGE_NAME: com.shopping.service
 * @author: XIA
 * @NAME: ProductService
 * @DATE: 2020/3/15
 **/
public interface ProductService {
   public List<Product> selectProductlist(Product product);

   public int getProductCount(Product product);

   public Product getProductById(Integer id);
}
