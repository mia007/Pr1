import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {
	private static class Task implements Runnable {
		public void run()  {
			try {
				Thread.sleep(10_000);
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newSingleThreadExecutor();
		Future <?> f = es.submit(new Task());
		System.out.println("Run task!");
		es.shutdown();		
		Thread.sleep(1000);
		System.out.println("Try to cancel task");
		f.cancel(true);
	}
}
