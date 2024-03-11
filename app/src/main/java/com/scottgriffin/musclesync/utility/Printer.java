package com.scottgriffin.musclesync.utility;

import com.scottgriffin.musclesync.model.Test;

public class Printer {
	public static String toUpperSpaced(String input) {
		StringBuilder result = new StringBuilder();
		result.append(" ");
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (Character.isLetter(c)) {
				result.append(Character.toUpperCase(c));
			}
			result.append(" ");
		}

		return result.toString();
	}
}
