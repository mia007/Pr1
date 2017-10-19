package ua.nure.mykytenko.Practice5;

public class Part3 {
	private int counter1=0;
	private int counter2=0;

	public void output() {
		counter1++;
		try {
			Thread.sleep(10);
		} catch (InterruptedException ex) {
			System.out.println(ex.toString());
		}
		counter2++;
		System.out.println(counter1 + " == " + counter2 + " ==> " + (counter1 == counter2));
	}

	public synchronized void synchOut() {
		counter1++;
		try {
			Thread.sleep(10);
		} catch (InterruptedException ex) {
			System.out.println(ex.toString());
		}
		counter2++;
		System.out.println(counter1 + " == " + counter2 + " ==> " + (counter1 == counter2));
		}

	public static void main(String[] args) {
		final Part3 obj = new Part3();
		System.out.println("Synchronized:");
		for (int i = 0; i <= 5; i++) {
			Thread myThread = new Thread() {
				public void run() {
					obj.synchOut();
				};
			};
			myThread.start();
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException ex) {
			System.out.println(ex.toString());
		}
		System.out.println("Not synchronized:");
		for (int i = 0; i <= 5; i++) {
			Thread myThread = new Thread() {
				public void run() {
					obj.output();
				};
			};
			myThread.start();
		}
	}

}
