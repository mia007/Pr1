package ua.nure.mykytenko.Practice1;

public class Part3 {

	public static void main(String[] args) {
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		System.out.println(divider(x, y));
	}

	public static int divider(int a, int b) {
		int divider = 1;
		for (int i = 1; i <= Math.min(a, b); i++) {
			if (a % i == 0 && b % i == 0) {
				divider = i;
			}
		}
		return divider;
	}
}
