package ua.nure.mykytenko.Practice5;

public class Part1 {
	public static void main(String[] args) {
		Thread myThread = new MyThread();
		myThread.start();
		try {
			myThread.join();
		} catch (InterruptedException ex) {
			System.out.println(ex.toString());
		}
		Thread myThread2 = new Thread(new MyThread2());
		myThread2.start();
	}
}

class MyThread extends Thread {
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName());
			try {
				sleep(750);
			} catch (InterruptedException ex) {
				System.out.println(ex.toString());
			}
		}
	}
}

class MyThread2 implements Runnable {
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(750);
			} catch (InterruptedException ex) {
				System.out.println(ex.toString());
			}
		}
	}
}
