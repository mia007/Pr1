package ua.nure.mykytenko.Practice5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Part52 {

	private static final ReentrantLock LOCK1 = new ReentrantLock();
	private static final ReentrantLock LOCK2 = new ReentrantLock();

	private static final Condition CONDITION = LOCK2.newCondition();

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
					LOCK1.lock();
					try {
						read(getName());
					} finally {
						LOCK1.unlock();
					}
					LOCK2.lock();
					try {
						Thread.sleep(500);
						CONDITION.signal();
						CONDITION.awaitNanos(3000);
					} finally {
						LOCK2.unlock();
					}

				} catch (InterruptedException e) {
					System.out.println(e.toString());
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
					LOCK1.lock();
					try {
						write();
					} finally {
						LOCK1.unlock();
					}
					LOCK2.lock();
					try {
						CONDITION.signalAll();
						CONDITION.await();
					} finally {
						LOCK2.unlock();
					}
				} catch (InterruptedException e) {
					System.out.println(e.toString());
				} finally {
					if (++tact == ITERATION_NUMBER) {
						stop = true;
					}
				}
			}
		}
	}

	private static void read(String threadName) {
		System.out.printf("Reader %s:", threadName);
		for (int i = 0; i < BUFF_LENGTH; i++) {
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				System.out.println(e.toString());
			}
			System.out.print(BUFF.charAt(i));
		}
		System.out.println();
		try {
			Thread.sleep(PAUSE);
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
	}

	private static void write() {
		// clear buffer
		BUFF.setLength(0);

		// write to buffer
		System.err.print("Writer writes:");

		Random random = new Random();
		for (int i = 0; i < BUFF_LENGTH; i++) {
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				System.out.println(e.toString());
			}
			char ch = (char) ('A' + random.nextInt(26));
			System.err.print(ch);
			BUFF.append(ch);
		}
		System.err.println();
		try {
			Thread.sleep(PAUSE);
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
	}

	public static void main(String[] args) {
		// create writer
		Writer writer = new Writer();

		// create readers
		List<Thread> readers = new ArrayList<>();
		for (int i = 0; i < READER_NUMBER; i++) {
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
			Thread.sleep(100);
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
