package ua.nure.mykytenko.Practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

	private static final String ENCODING = "Cp1251";
	private static final String STRING_REGEX = "(?mU)(\\b)(\\w{1,}[^\\W+|\\d+])\\b";
	private static final String CHAR_REGEX = "(?mU)(\\b)(\\w{0}[^\\W+|\\d+])\\b";
	private static final String INTEGER_REGEX = "(^|\\s)(\\d+)(\\s|$)";
	private static final String DOUBLE_REGEX = "(^|\\s)([\\d+]*\\.[\\d+]*)(\\s|$)";

	public static String readFile(String path) {
		String str = null;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), ENCODING))) {
			for (String line = null; (line = reader.readLine()) != null;) {
				str = line;
			}
		} catch (IOException ex) {
			System.out.println(ex.toString());
		}
		return str;
	}

	public static String value(String str, String regex) {
		StringBuilder sb = new StringBuilder();
		Pattern p1 = Pattern.compile(regex);
		Matcher m = p1.matcher(str);
		while (m.find()) {
			sb.append(m.group(2) + " ");
		}
		return sb.toString().substring(0, sb.length()-1);
	}

	public static void output(String s) {
		Scanner sc = new Scanner(System.in, ENCODING);
		while (sc.hasNext()) {
			String str = sc.nextLine();
			if ("int".equals(str)) {
				System.out.println(value(s, INTEGER_REGEX));
			} else if ("double".equals(str)) {
				System.out.println(value(s, DOUBLE_REGEX));
			} else if ("char".equals(str)) {
				System.out.println(value(s, CHAR_REGEX));
			} else if ("String".equals(str)) {
				System.out.println(value(s, STRING_REGEX));
			} else if ("stop".equals(str)) {
				return;
			}
		}
		sc.close();

	}

	public static void main(String[] args) {
		output(readFile("part3.txt"));

	}
}
