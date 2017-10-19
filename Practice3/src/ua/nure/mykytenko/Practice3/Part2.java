package ua.nure.mykytenko.Practice3;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
	public static void main(String[] args) {
		String path = "part2.txt";
		String input = Util.readFile(path);
		System.out.println(input);
		String conv = convert(input);
		System.out.println(conv);
	}

	public static String convert(String input) {
		Pattern pattern = Pattern.compile("(?mU)\\b(\\w+?)\\b");
		Matcher matcher = pattern.matcher(input);
		ArrayList<String> array = new ArrayList<>();
		while (matcher.find()) {
			array.add(matcher.group(1));
		}
		ArrayList<String> temp = new ArrayList<>();
		while (!array.isEmpty()) {
			String bigger = "";
			for (String s : array) {
				if (s.length() > bigger.length()) {
					bigger = s;
				}
			}
			temp.add(bigger);
			while (array.contains(bigger)) {
				array.remove(bigger);
			}
		}

		StringBuilder result = new StringBuilder();
		result.append("Min: ");
		for (String s : temp) {
			if (s.length() == temp.get(temp.size() - 2).length()) {
				result.append(s).append(", ");
			}
		}
		result.replace(result.length() - 2, result.length() - 1, "\nMax:");
		for (String s : temp) {
			if (s.length() == temp.get(0).length()) {
				result.append(s).append(", ");
			}
		}
		result.delete(result.length() - 2, result.length() - 1);
		return result.toString();
	}
}
