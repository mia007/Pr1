package ua.nure.mykytenko.Practice1;

public class Part5 {
	static final String CHARSET = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";

	public static void main(String[] args) {
		String x = " ==> ";
		System.out.println("A" + x + str2int("A") + x + int2str(1));
		System.out.println("B" + x + str2int("B") + x + int2str(2));
		System.out.println("Z" + x + str2int("Z") + x + int2str(26));
		System.out.println("AA" + x + str2int("AA") + x + int2str(27));
		System.out.println("AZ" + x + str2int("AZ") + x + int2str(52));
		System.out.println("BA" + x + str2int("BA") + x + int2str(53));
		System.out.println("ZZ" + x + str2int("ZZ") + x + int2str(702));
		System.out.println("AAA" + x + str2int("AAA") + x + int2str(703));
	}

	public static int str2int(String s) {
		int val = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int d = CHARSET.indexOf(c) + 1;
			val = 26 * val + d;
		}
		return val;
	}

	public static String int2str(int d) {
		int digit = d;
		if (digit <= 0) {
			return "0";
		}
		String char2 = "";
		while (digit > 0) {
			digit--;
			int dig = digit % 26;
			char2 = CHARSET.charAt(dig) + char2;
			digit = digit / 26;
		}
		return char2;
	}

	public static String rightColumn(String number) {
		int val = str2int(number);
		val++;
		return int2str(val);
	}
}
