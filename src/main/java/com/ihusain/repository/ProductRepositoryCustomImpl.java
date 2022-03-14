package com.ihusain.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ihusain.common.Constants;
import com.ihusain.model.Product;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public ProductRepositoryCustomImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<Product> getPartiallyMatchedProducts(Map<String, List<String>> partsMap) {

		// search based on URL path.
		int counter = 1;
		List<Criteria> criteriaList = new ArrayList<>();
		for (String path: partsMap.get("path")) {

			if (!path.isEmpty()) {
				criteriaList.add(
					Criteria.where(Constants.URL_PARTS_NESTED_COLLECTION + ".path" + (counter++))
					.is(path)
				);
			}
		}

		// search based on URL query parameters.
		for (String param: partsMap.get("queryParams")) {
			if (!param.isEmpty()) {

				String[] nameValueArr = param.split("=");
				criteriaList.add(
					Criteria.where(Constants.URL_PARTS_NESTED_COLLECTION + "." + nameValueArr[0])
					.is(nameValueArr[1])
				);
			}
		}

		Criteria criteriaDefinition = new Criteria();
		Query query = new Query(criteriaDefinition.orOperator(criteriaList));

		return mongoTemplate.find(query, Product.class);
	}
}
