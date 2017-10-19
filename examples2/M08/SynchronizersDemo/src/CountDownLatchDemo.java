import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo extends Thread {

	private static CountDownLatch latch = new CountDownLatch(1);

	private static class Task extends Thread {
		private int number;

		public void run() {
			try {
				System.err.print(number + " ");
				latch.await();
				Thread.sleep(1000);
				System.err.println(number);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.print("Creating and starting tasks: ");
		for (int j = 1; j <= 4; j++) {
			Task t = new Task();
			t.number = j;
			t.start();
		}
		Thread.sleep(1000);
		System.out.println("\nAll the tasks are waiting for a signal.");
		Thread.sleep(1500);
		System.out.println("Give the signal to start work!");
		latch.countDown();
	}

}