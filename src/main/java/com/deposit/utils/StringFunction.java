package com.deposit.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFunction {

	public static boolean isContainRegex(String param, String expression) {
		Pattern p = Pattern.compile(expression);
		Matcher m = p.matcher(param);
		if (m.find()) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
}
