package ua.nure.mykytenko.Practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
	private static final String FILE_NAME = "part1.txt";

	private static final String ENCODING = "Cp1251";

	public static String convert(String string) {
		Pattern pattern = Pattern.compile("(?mU)(\\w{4,})");
		Matcher matcher = pattern.matcher(string);
		StringBuffer result = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(result, matcher.group().toUpperCase());
		}
		matcher.appendTail(result);
		return result.toString();
	}

	public static void main(String[] args) {
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(FILE_NAME), ENCODING))) {

			for (String line = null; (line = reader.readLine()) != null;) {
				System.out.println(convert(line));
			}
		} catch (IOException ex) {
			System.out.println(ex.toString());
		}
	}

}
