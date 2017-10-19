package ua.nure.mykytenko.Practice5;

public class Demo {

	public static void main(String[] args) {
		System.out.println("~~~~~~~~~~~~Part1");
		Part1.main(args);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		System.out.println("~~~~~~~~~~~~Part2");
		Part2.main(args);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		System.out.println("~~~~~~~~~~~~Part3");
		Part3.main(args);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		System.out.println("~~~~~~~~~~~~Part4");
		Part4.main(args);
		System.out.println("~~~~~~~~~~~~Part5");
		Part5.main(args);
		System.out.println("~~~~~~~~~~~~Part6");
		Part6.main(args);
	}

}
