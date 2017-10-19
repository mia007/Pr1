package counter;

public class OrdinaryCounter implements Counter {
	
	private volatile long c;

	public void increment() {
		c++;
	}

	public void decrement() {
		c--;
	}

	public long get() {
		return c;
	}
	
}