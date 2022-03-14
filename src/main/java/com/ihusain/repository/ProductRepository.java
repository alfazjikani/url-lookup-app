package com.ihusain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ihusain.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>, ProductRepositoryCustom {

	List<Product> findByUrl(String url);
}
