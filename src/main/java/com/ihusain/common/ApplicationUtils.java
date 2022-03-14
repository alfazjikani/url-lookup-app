package com.ihusain.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * It contains common methods of application..
 *
 * @author alfaz.jikani
 */
public class ApplicationUtils {

	/**
	 * get URL parts such as path list and query
	 * parameter list.
	 *
	 * @param url- "/Women/Shoes/?tag=5678"
	 *
	 * @return- {"path": ["Women", "Shoes"], "query": ["tag=5678"]}
	 */
	public static Map<String, List<String>> getUrlPartsMap(String url) {

		Map<String, List<String>> partMap = new HashMap<>();
		String[] urlArr = url.split("\\?");

		// get parts of URL path.
		String path = urlArr[0];
		List<String> pathList = new ArrayList<>();
		for (String part: path.split("/")) {
			if (!part.isEmpty()) {

				pathList.add(part);
			}
		}
		partMap.put("path", pathList);

		// get parts of URL query parameters.
		String params = urlArr[1];
		List<String> paramList = new ArrayList<>();
		for (String part: params.split("&")) {
			if (!part.isEmpty()) {

				paramList.add(part);
			}
		}
		partMap.put("queryParams", paramList);

		return partMap;
	}
}
