package ua.nure.mykytenko.Practice3;

public class Part5 {

	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++) {
			System.out.println(i + " ==> " + decimal2Roman(i) + " ==> " + roman2Decimal(decimal2Roman(i)));
		}

	}

	private static final Object[][] NUMBERS = { { 100, "C" }, { 90, "XC" }, { 50, "L" }, { 40, "XL" }, { 10, "X" },
			{ 9, "IX" }, { 5, "V" }, { 4, "IV" }, { 1, "I" } };

	public static String decimal2Roman(int x) {
		if (x > 100 || x < 0) {
			return null;
		}
		int decimal = x;
		StringBuilder result = new StringBuilder();
		for (Object[] n : NUMBERS) {
			while (decimal - (Integer) n[0] >= 0) {
				result.append((String) n[1]);
				decimal -= (Integer) n[0];
			}
		}
		return result.toString();
	}

	public static int roman2Decimal(String s) {
		int result = 0;
		String roman = s;
		for (Object[] n : NUMBERS) {
			while (roman.startsWith((String) n[1])) {
				result += (Integer) n[0];
				roman = roman.replaceFirst((String) n[1], "");
			}
		}
		return result;
	}

}
