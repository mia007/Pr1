package counter;

public class SyncCounter implements Counter {
	private long c;

	public synchronized void increment() {
		c++;
	}

	public synchronized void decrement() {
		c--;
	}

	public synchronized long get() {
		return c;
	}
}