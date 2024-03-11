package com.scottgriffin.musclesync.utility;

import java.util.Scanner;

public class Input {
	private static Scanner scanner = new Scanner(System.in);

	public static int nextInt() {
		return scanner.nextInt();
	}
	public static void closeScanner() {
		scanner.close();
	}
}
