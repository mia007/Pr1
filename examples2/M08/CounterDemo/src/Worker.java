import counter.Counter;

public class Worker extends Thread {
	
	private Counter c;
	
	private boolean increment;
	
	private int count;

	public Worker(Counter c, boolean increment, int count) {
		this.c = c;
		this.increment = increment;
		this.count = count;
	}

	public void run() {
		for (int i = 0; i < count; i++) {
			if (increment) c.increment();
			else c.decrement();
		}
	}
	
}
