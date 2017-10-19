import counter.AtomicCounter;
import counter.Counter;
import counter.LockCounter;
import counter.OrdinaryCounter;
import counter.SyncCounter;

public class CounterDemo {

	private static final int N = 5_000_000;

	public static void runCounter(Class<?> clazz) throws Exception {
		Counter counter = (Counter) clazz.newInstance();
		
		Thread t1 = new Thread(new Worker(counter, true, N));
		Thread t2 = new Thread(new Worker(counter, false, N));
		
		t1.start();		t2.start();
		
		long before = System.currentTimeMillis();
		
		t1.join();		t2.join();
		
		long time = System.currentTimeMillis() - before;
		System.out.println(counter.getClass().getSimpleName() + ": "
				+ counter.get() + " (" + time + "ms)");
	}

	public static void main(String[] args) throws Exception {
		while (true) {
			runCounter(OrdinaryCounter.class);
			runCounter(SyncCounter.class);
			runCounter(AtomicCounter.class);
			runCounter(LockCounter.class);
			System.out.println("~~~~~~~~~~~~");
		}
	}
}