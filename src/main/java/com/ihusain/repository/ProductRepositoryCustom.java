package com.ihusain.repository;

import java.util.List;
import java.util.Map;

import com.ihusain.model.Product;

public interface ProductRepositoryCustom {

	List<Product> getPartiallyMatchedProducts(Map<String, List<String>> partsMap);
}
