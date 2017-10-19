package ua.nure.mykytenko.Practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

	public static void main(String[] args) {
		String path = "part3.txt";
		String input = Util.readFile(path);
		System.out.println(input);
		String conv1 = convert(input);
		System.out.println(conv1);
	}

	public static String convert(String string) {
		Pattern pattern = Pattern.compile("(?mU)(\\w)(\\w\\w+)");
		Matcher matcher = pattern.matcher(string);
		StringBuffer result = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(result, matcher.group(1).toUpperCase() + matcher.group(2));
		}
		matcher.appendTail(result);
		return result.toString();
	}
}
