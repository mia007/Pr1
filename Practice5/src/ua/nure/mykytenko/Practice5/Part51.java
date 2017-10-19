package ua.nure.mykytenko.Practice5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Part51 {

	private static final Object MONITOR1 = new Object();
	private static final Object MONITOR2 = new Object();

	private static final int ITERATION_NUMBER = 3;

	private static final int READER_NUMBER = 3;

	// shared resource (not thread-safe!!!)
	private static final StringBuilder BUFF = new StringBuilder();

	private static final int BUFF_LENGTH = 5;

	// speed parameter
	private static final int PAUSE = 5;

	// stop signal
	private static boolean stop;

	// reader
	private static class Reader extends Thread {
		public void run() {
			while (!stop) {
				try {
					synchronized (MONITOR1) {
						read(getName());
					}
					synchronized (MONITOR2) {
						Thread.sleep(1000);
						MONITOR2.notify();
						MONITOR2.wait(100);
					}

				} catch (InterruptedException e) {
					System.out.println(e);
				}
			}
		}
	}

	// writer
	private static class Writer extends Thread {
		public void run() {
			int tact = 0;
			while (!stop) {
				try {
					synchronized (MONITOR1) {
						write();
					}
					synchronized (MONITOR2) {
						MONITOR2.notify();
						MONITOR2.wait(1000);
					}
				} catch (InterruptedException e) {
					System.out.println(e);
				} finally {
					if (++tact == ITERATION_NUMBER) {
						stop = true;
					}
				}
			}
		}
	}

	private static void read(String threadName) throws InterruptedException {
		System.out.printf("Reader %s:", threadName);
		for (int j = 0; j < BUFF_LENGTH; j++) {
			Thread.sleep(PAUSE);
			System.out.print(BUFF.charAt(j));
		}
		System.out.println();
		Thread.sleep(PAUSE);
	}

	private static void write() throws InterruptedException {
		// clear buffer
		BUFF.setLength(0);

		// write to buffer
		System.err.print("Writer writes:");

		Random random = new Random();
		for (int j = 0; j < BUFF_LENGTH; j++) {
			Thread.sleep(PAUSE);
			char ch = (char) ('A' + random.nextInt(26));
			System.err.print(ch);
			BUFF.append(ch);
		}
		System.err.println();
		Thread.sleep(PAUSE);
	}

	public static void main(String[] args) {
		// create writer
		Writer writer = new Writer();

		// create readers
		List<Thread> readers = new ArrayList<>();
		for (int j = 0; j < READER_NUMBER; j++) {
			readers.add(new Reader());
		}

		// start writer
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		writer.start();

		// start readers
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		for (Thread reader : readers) {
			reader.start();
		}

		// main thread is waiting for the child threads
		try {
			writer.join();
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		for (Thread reader : readers) {
			try {
				reader.join();
			} catch (InterruptedException e) {
				System.out.println(e.toString());
			}
		}
	}

}
