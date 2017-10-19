package counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounter implements Counter {
	
	private static Lock mutex = new ReentrantLock();
	
	private long c;
	
	public void increment() {
		mutex.lock();
		c++;
		mutex.unlock();
	}

	public void decrement() {
		mutex.lock();
		c--;
		mutex.unlock();
	}

	public long get() {
		return c;
	}
	
}