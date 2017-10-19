import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {
	
	private static class Task implements Callable<String> {

		private String message;
		
		public Task(String message) {
			this.message = message;
		}

		public String call() throws Exception {
			Thread.sleep(1000);
			return Thread.currentThread().getName() + " : " + message;
		}
	}

	
	public static void main(String[] args) throws Exception {
		//ExecutorService es = Executors.newFixedThreadPool(23);
		//ExecutorService es = Executors.newSingleThreadExecutor();
		ExecutorService es = Executors.newCachedThreadPool();
		
		List<Future<String>> results = new ArrayList<>();

		for (int j = 1; j <= 100; j++) {
			Task t = new Task("message " + j);
			Future<String> result = es.<String>submit(t);
			results.add(result);
		}
		es.shutdown();
		es.awaitTermination(5, TimeUnit.SECONDS);

		for (Future<String> r : results) {
			System.out.println(r.get());
		}
    }
}