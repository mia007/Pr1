package counter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter implements Counter {
	
	private AtomicInteger c = new AtomicInteger(0);

	public void increment() {
		c.getAndIncrement();
	}

	public void decrement() {
		c.getAndDecrement();
	}

	public long get() {
		return c.get();
	}
}