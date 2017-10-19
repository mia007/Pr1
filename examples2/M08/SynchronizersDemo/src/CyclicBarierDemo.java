import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarierDemo {

	private static CyclicBarrier barrier = new CyclicBarrier(4);

	private static class Task extends Thread {		
		private int number;
		
		public void run() {
			try {
				System.err.print(number + " ");
				barrier.await();
				Thread.sleep(1000);
				System.out.print("\n" + number);
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.print("Creating and starting tasks: ");
		for (int j = 1; j <= 3; j++) {
			Task t = new Task();
			t.number = j;
			t.start();
		}

		Thread.sleep(1500);
		System.out.print("\nNow all thre tasks (3) await fourth: ");
		Thread.sleep(1000);
		Task t = new Task();
		t.number = 4;
		t.start();
	}

}