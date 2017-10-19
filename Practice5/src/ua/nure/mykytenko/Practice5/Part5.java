package ua.nure.mykytenko.Practice5;

public class Part5 {
	public static void main(String[] args) {
		System.out.println("============Part51");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		Part51.main(args);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println(e);
		}

		System.out.println("============Part52");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		Part52.main(args);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println(e);
		}

		System.out.println("============Part53");
		Part53.main(args);
	}
}
