package com.spark.cms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

	private CommonUtils() {

	}

	// TODO need to create this properly from just placeholder
	public static String urlGenerator(String title, Long id) {
		return title.replaceAll("[^a-zA-Z0-9 ]", "").replace(" ", "-").toLowerCase().concat("-").concat(id.toString())
				.concat(".html");
	}
	
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean looksLikeAnEmailAddress(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
	        return matcher.find();
	}
}
