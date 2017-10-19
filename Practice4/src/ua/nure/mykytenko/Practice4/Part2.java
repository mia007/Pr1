package ua.nure.mykytenko.Practice4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;

public class Part2 {
	private static final String RAW_DATA = "part2.txt";

	private static final String SORTED_DATA = "part2_sorted.txt";

	private static final int N = 10;

	private static final int MAX = 50;
	
	private static final String ENCODING = "Cp1251";

	public static String generateRandom() {
		Random random = new Random();
		int[] result = new int[N];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.length; i++) {
			result[i] = random.nextInt(MAX);
			sb.append(result[i] + " ");
		}
		return sb.toString().substring(0, sb.length() - 1);
	}

	public static String sortNumbers(String str) {
		String[] stringArray = str.split(" ");
		int[] arr = new int[stringArray.length];
		int temp = 0;
		for (int i = 0; i < stringArray.length; i++) {
			arr[i] = Integer.valueOf(stringArray[i]);
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 1; j < arr.length - i; j++) {
				if (arr[j - 1] > arr[j]) {
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i] + " ");
		}
		return sb.toString().substring(0, sb.length() - 1);
	}

	public static void writeData(String name, String data) {
		try {
			PrintWriter pr = new PrintWriter(new File(name), ENCODING);
			pr.write(data);
			pr.close();
		} catch (IOException ex) {
			System.out.println(ex.toString());
		}
	}

	public static String readData() {
		String line = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(RAW_DATA), ENCODING));
			line = reader.readLine();
			reader.close();
		} catch (IOException ex) {
			System.out.println(ex.toString());
		}
		return line;
	}

	public static void output(String name, String str) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(name), ENCODING));
			for (String line = null; (line = reader.readLine()) != null;) {
				System.out.println(str + line);
			}
			reader.close();
		} catch (IOException ex) {
			System.out.println(ex.toString());
		}
	}

	public static void main(String[] args) {
		writeData(RAW_DATA, generateRandom());
		writeData(SORTED_DATA, sortNumbers(readData()));
		output(RAW_DATA, "input ==> ");
		output(SORTED_DATA, "output ==> ");
	}
}
