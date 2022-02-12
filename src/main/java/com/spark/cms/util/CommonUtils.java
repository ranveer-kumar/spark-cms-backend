package com.spark.cms.util;


public class CommonUtils {

	//TODO need to create this properly from just placeholder
	public static String urlGenerator(String title, Long id) {
		return title.replaceAll("[^a-zA-Z0-9 ]", "").replace(" ","-").toLowerCase().concat("-").concat(id.toString()).concat(".html");
	}
}
