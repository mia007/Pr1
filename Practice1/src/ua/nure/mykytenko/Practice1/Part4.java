package ua.nure.mykytenko.Practice1;

public class Part4 {

	public static void main(String[] args) {
		int a = Integer.parseInt(args[0]);
		System.out.println(sum(a));
	}

	public static int sum(int a) {
		int sum = 0;
		for (int i = a; i > 0; i /= 10) {
			sum += i % 10;
		}
		return sum;
	}
}
