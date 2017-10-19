package ua.nure.mykytenko.Practice3;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
	public static void main(String[] args) {
		String path = "part1.txt";
		String input = Util.readFile(path);
		String conv1 = convert1(input);
		System.out.println(conv1);
		String conv2 = convert2(input);
		System.out.println(conv2);
		String conv3 = convert3(input);
		System.out.println(conv3);
		String conv4 = convert4(input);
		System.out.println(conv4);
	}

	public static String convert1(String input) {
		StringBuilder result = new StringBuilder();
		Pattern pattern = Pattern.compile("(?mU)^(.+?);(.+?);(.+@.+)");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			result.append(matcher.group(1)).append(" ==> ").append(matcher.group(3)).append(System.lineSeparator());
		}
		return result.toString();
	}

	public static String convert2(String input) {
		StringBuilder result = new StringBuilder();
		Pattern pattern = Pattern.compile("(?mU)^(.+?);(.+?);(.+@.+)$");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			result.append(matcher.group(2)).append(" (").append("email: ").append(matcher.group(3)).append(')')
					.append(System.lineSeparator());
		}
		return result.toString();
	}

	public static String convert3(String input) {
		StringBuilder result = new StringBuilder();
		Pattern pattern = Pattern.compile("(?mU)^((.+?);(.+?);(.+)@(.+))$");
		Matcher matcher = pattern.matcher(input);
		ArrayList<String> domains = new ArrayList<>();
		while (matcher.find()) {
			if (!domains.contains(matcher.group(5))) {
				domains.add(matcher.group(5));
			}
		}
		matcher.reset();
		for (String domain : domains) {
			result.append(domain).append(" ==> ");
			while (matcher.find()) {
				if (matcher.group(5).equals(domain)) {
					result.append(matcher.group(2)).append(", ");
				}
			}
			result.delete(result.length() - 2, result.length());
			result.append(System.lineSeparator());
			matcher.reset();
		}
		return result.toString();
	}

	public static String convert4(String input) {
		Pattern pattern = Pattern.compile("(?mU)^((.+?);(.+?);(.+))$");
		Matcher matcher = pattern.matcher(input);
		StringBuilder result = new StringBuilder();
		Random random = new Random();
		if (matcher.find()) {
			result.append(matcher.group(1)).append(";Password").append(System.lineSeparator());
		}
		while (matcher.find()) {
			result.append(matcher.group(1));
			result.append(";" + String.format("%04d", random.nextInt(10000)));
			result.append(System.lineSeparator());
		}
		return result.toString();
	}

}