import java.util.concurrent.Exchanger;

public class ExchangerDemo extends Thread {

	private static Exchanger<String> exchanger = new Exchanger<>();

	private static class Task extends Thread {
		private String message;

		public void run() {
			try {
				System.err.println(this.getName()
						+ " whants to exchange: message --> " + message);
				Thread.sleep(1000);
				message = exchanger.exchange(message);
				System.out.println(this.getName()
						+ " exchange was successful: message --> " + message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Task t = new Task();
		t.message = "AAA";
		t.start();

		Task t2 = new Task();
		t2.message = "77777";
		t2.start();
	}

}