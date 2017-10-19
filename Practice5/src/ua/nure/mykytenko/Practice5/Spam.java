package ua.nure.mykytenko.Practice5;

import java.io.IOException;

public class Spam extends Thread {
	private static int[] time = new int[] { 1000, 1000, 1000, 1000, 1000, 1000, 1000 };
	private static String[] messages = new String[] { "message1", "message2", "message3", "message4", "message5",
			"message6", "message7" };

	public static Thread main2() {

		// run a child thread
		final Thread child = new Thread() {
			public void run() {
				while (!isInterrupted()) {
					try {
						for (int j = 0; j < messages.length; j++) {
							System.out.println(messages[j]);
							Thread.sleep(time[j]);
						}
					} catch (InterruptedException ex) {
						break;
					}
				}
			}
		};
		child.start();

		// run a util thread
		Thread thread = new Thread() {
			public void run() {
				byte[] buff = new byte[10];
				int count;
				try {
					do {
						while ((count = System.in.read(buff)) == -1){
							;}
					} while (!System.lineSeparator().equals(new String(buff, 0, count, "cp1251")));
				} catch (IOException e) {
					System.out.println(e.toString());
				}
				System.out.println("ENTER has been obtained");
				System.out.println("Notify a child thread to terminate");
				child.interrupt();
			}
		};
		thread.start();
		return thread;
	}

	// terminates an execution when a line separator is passed
	public static void main(String[] args){
		main2();
	}
}
