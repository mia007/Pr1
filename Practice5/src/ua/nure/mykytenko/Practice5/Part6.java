package ua.nure.mykytenko.Practice5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Part6 {
	private static final int THREADS = 10;

	private static final int REPEATS = 20;

	private static RandomAccessFile file;

	private static String fileName = "part6.txt";

	private static int n = 0;

	public static void writeFile() {
		File f = new File(fileName);
		f.delete();
		try {
			file = new RandomAccessFile(fileName, "rwd");
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		}
		for (int i = 0; i < THREADS; i++) {
			Thread myThread = new Thread() {
				public void run() {
					try {
						for (int i = 0; i < REPEATS; i++) {
							file.write(String.valueOf(n).getBytes("cp1251"));
						}
						n++;
						if (n != THREADS) {
							file.write(System.lineSeparator().getBytes("cp1251"));
						}
					} catch (IOException e) {
						System.out.println(e.toString());
					}
				}
			};
			try {
				file.seek(i * 22);
			} catch (IOException e) {
				System.out.println(e.toString());
			}
			myThread.start();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println(e.toString());
			}
		}
	}

	public static void read() {
		try {
			file.seek(0);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		StringBuilder sb = new StringBuilder();
		int b;
		try {
			b = file.read();
			while (b != -1) {
				sb.append((char) b);
				b = file.read();
			}
			file.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) {
		writeFile();
		read();
	}
}
