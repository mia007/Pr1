import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarierDemo2 extends Thread {

	private static CyclicBarrier barrier = new CyclicBarrier(4);
	private static CyclicBarrier finish = new CyclicBarrier(4, new FinishAction());

	private static class FinishAction implements Runnable {		
		public void run() {
			System.err.print("\nWork done..");
		}
	}

	private static class Tasks extends Thread {		
		private int number;
		
		public void run() {
			try {
				System.err.print(number + " ");
				barrier.await();
				Thread.sleep(1000);
				System.out.print("\n" + number);
				finish.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.print("Creating and starting tasks: ");
		for (int j = 1; j <= 3; j++) {
			Tasks t = new Tasks();
			t.number = j;
			t.start();
		}

		Thread.sleep(1500);
		System.out.print("\nNow all the tasks (3) await fourth: ");
		Thread.sleep(1000);
		Tasks t = new Tasks();
		t.number = 4;
		t.start();
	}

}