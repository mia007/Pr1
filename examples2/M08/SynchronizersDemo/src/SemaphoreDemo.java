import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	private static Semaphore semaphor = new Semaphore(2);

	private static class Task extends Thread {
		private int number;

		public void run() {
			try {
				semaphor.acquire();
				Thread.sleep(50);
				System.err.println(number + " starts");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaphor.release();
				System.out.println(number + " finished");
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Creating and starting tasks.");
		Thread.sleep(1000);
		for (int j = 1; j <= 4; j++) {
			Task t = new Task();
			t.number = j;
			t.start();
			Thread.sleep(1);
		}
	}

}