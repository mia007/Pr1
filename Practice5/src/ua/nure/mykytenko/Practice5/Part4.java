package ua.nure.mykytenko.Practice5;

import java.util.Random;

public class Part4 {
	private static final Random RANDOM = new Random();
	private static final int SIZE = 4;
	private static int[][] array = new int[SIZE][100];
	private static long time = 0;

	public static void fillArray() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = RANDOM.nextInt(1000);
			}
		}
	}

	public static int findMax(int[] arr) {
		int max = arr[0];
		for (int i = 0; i < arr.length; i++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException ex) {
				System.out.println(ex.toString());
			}
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}

	public static int oneThread() {
		long temp = System.currentTimeMillis();
		int max = array[0][0];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < array[i].length; j++) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException ex) {
					System.out.println(ex.toString());
				}
				if (array[i][j] > max) {
					max = array[i][j];
				}
			}
		}
		System.out.println("Time: " + (System.currentTimeMillis() - temp));
		return max;
	}
	
	public static int[] multipleThreads() {
		final int[] result = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {
			final int count = i;
			Thread myThread = new Thread() {
				public void run() {
					long temp = System.currentTimeMillis();
					result[count] = findMax(array[count]);
					temp = System.currentTimeMillis() - temp;
					if (time < temp) {
						time = temp;
					}
				};
			};
			myThread.start();
		}
		return result;
	}


	public static void main(String[] args) {
		fillArray();
		System.out.println("One thread: ");
		System.out.println("Max value: " + oneThread());
		System.out.println("\nMultiple threads: ");
		int[] arr = multipleThreads();
		try {
			Thread.sleep(500);
		} catch (InterruptedException ex) {
			System.out.println(ex.toString());		}
		long temp = System.currentTimeMillis();
		System.out.println("Max value: " + findMax(arr));
		System.out.println("Time: " + (System.currentTimeMillis() - temp + time));
	}
	}
