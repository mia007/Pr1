/**
 * Demo volatile key word. You must run the code with "-server" option:
 * 
 * <pre>
 * java -server VolatileDemo
 * </pre>
 * 
 */

public class VolatileDemo extends Thread {

	// private volatile int x = 1;
	private int x = 1;

	public void run() {
		while (x == 1)
			;
		System.out.println("x = " + x);
	}

	public static void main(String[] args) throws InterruptedException {
		VolatileDemo t = new VolatileDemo();
		t.start();
		Thread.sleep(500);
		t.x = 0;
		System.out.println("Complete, x = " + t.x);
	}

}