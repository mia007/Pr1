package ua.nure.mykytenko.Practice3;

import java.security.NoSuchAlgorithmException;

public class Demo {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		String path1 = "part1.txt";
		String input1 = Util.readFile(path1);
		String conv1 = Part1.convert1(input1);
		System.out.println(conv1);
		String conv2 = Part1.convert2(input1);
		System.out.println(conv2);
		String conv3 = Part1.convert3(input1);
		System.out.println(conv3);
		String conv4 = Part1.convert4(input1);
		System.out.println(conv4);

		String path2 = "part2.txt";
		String input2 = Util.readFile(path2);
		System.out.println("\n" + input2);
		String conv = Part2.convert(input2);
		System.out.println(conv);

		String path3 = "part3.txt";
		String input3 = Util.readFile(path3);
		System.out.println(input3);
		String convert = Part3.convert(input3);
		System.out.println(convert);

		System.out.println(Part4.hash("password", "SHA-256"));
		System.out.println(Part4.hash("passwort", "SHA-512"));

		for (int i = 1; i <= 100; i++) {
			System.out.println(i + " ==> " + Part5.decimal2Roman(i) + " ==> " + Part5.roman2Decimal(Part5.decimal2Roman(i)));
		}

	}

}
