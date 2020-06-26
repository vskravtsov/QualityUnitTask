package com.gmail.vskravtsov992;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		WaitingTimeLineList list = new WaitingTimeLineList();
		InputAnalyzer ia = new InputAnalyzer();
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		System.out.println("Input:");
		try {
			int numberOfLines = sc.nextInt();
			sc.nextLine();
			for (int i = 1; i <= numberOfLines; i++) {
				String line = sc.nextLine();
				sb.append(line).append(System.lineSeparator());
			}
			System.out.println("Output:");
			System.out.println(ia.output(sb, list).toString());
		} catch (InputMismatchException e) {
			System.out.println("Wrong Input");
		}

	}

}
