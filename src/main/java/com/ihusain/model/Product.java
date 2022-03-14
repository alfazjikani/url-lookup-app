package com.ihusain.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ihusain.common.ApplicationUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

	@Id
	private String id;

	private String url;

	private Map<String, String> urlParts;

	public void setUrl(String url) {
		this.url = url;
		this.setUrlParts(url);
	}

	private void setUrlParts(String url) {
		Map<String, String> urlParts = new HashMap<>();
		Map<String, List<String>> partsMap = ApplicationUtils.getUrlPartsMap(url);

		int counter = 1;
		for (String path: partsMap.get("path")) {
			urlParts.put("path" + (counter++), path);
		}
		for (String param: partsMap.get("queryParams")) {
			String[] nameValuePair = param.split("=");
			urlParts.put(nameValuePair[0], nameValuePair[1]);
		}
		this.urlParts = urlParts;
	}
}
