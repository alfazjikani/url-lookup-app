package com.ihusain.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihusain.common.ApplicationUtils;
import com.ihusain.model.Product;
import com.ihusain.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public String lookup(String url) {
		// try to match URL first.
		List<Product> productList = productRepository.findByUrl(url);
		if (!productList.isEmpty()) {
			System.out.println("exact URL match found.");
			return productList.get(0).getUrl();
		}

		Product maxMatchProduct = null;
		Map<String, List<String>> partsMap = ApplicationUtils.getUrlPartsMap(url);
		List<Product> partiallyMatchedProducts =
				productRepository.getPartiallyMatchedProducts(partsMap);

		if (!partiallyMatchedProducts.isEmpty()) {
			/**
			 * If list has only one record then return it.
			 */
			if (partiallyMatchedProducts.size() == 1) {
				return partiallyMatchedProducts.get(0).getUrl();

			} else {
				int maxMatchCount = 0;
				List<String> pathList = partsMap.get("path");
				final int pathLength = pathList.size();
				List<String> queryParamList = partsMap.get("queryParams");
				for (Product product: partiallyMatchedProducts) {

					int totalMatchCount = 0;
					Map<String, String> urlParts = product.getUrlParts();

					// match path parameters
					for (int index = 0, length = pathLength;
							index < length; index++) {

						if (pathList.get(index)
							.equalsIgnoreCase(
								urlParts.get("path" + (index+ 1)))) {
							totalMatchCount++;
						}
					}

					// match query parameters
					for (String param: queryParamList) {

						String[] nameValuePair = param.split("=");
						if (nameValuePair[1].equalsIgnoreCase
							(urlParts.get(nameValuePair[0]))) {
							totalMatchCount++;
						}
					}

					if (maxMatchCount < totalMatchCount) {
						maxMatchCount = totalMatchCount;
						maxMatchProduct = product;
					}
				}

				System.out.println("maxMatchCount::" + maxMatchCount);
				System.out.println("maxMatchProduct::" + maxMatchProduct);
			}
		}

		return maxMatchProduct != null
				? maxMatchProduct.getUrl() : url;
	}
}
