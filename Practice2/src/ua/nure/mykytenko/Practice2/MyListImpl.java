package ua.nure.mykytenko.Practice2;

import java.util.*;

public class MyListImpl implements MyList, ListIterable {

	private Object[] defaultArray;

	private int size;

	public MyListImpl(int size) {
		this.size = size;
		defaultArray = new Object[size];
	}

	public MyListImpl() {
		this(0);
	}

	public void add(Object e) {
		Object[] temp = new Object[size + 1];
		System.arraycopy(defaultArray, 0, temp, 0, size);
		temp[size] = e;
		defaultArray = temp;
		size++;
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			defaultArray[i] = null;
		}
		size = 0;
	}

	public boolean remove(Object o) {
		if (o != null) {
			for (int i = 0; i < size; i++) {
				if (o.equals(defaultArray[i])) {
					return remove(i);
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (defaultArray[i] == null) {
					return remove(i);
				}
			}
		}
		return false;
	}

	private boolean remove(int index) {
		System.arraycopy(defaultArray, index + 1, defaultArray, index, size - index - 1);
		size--;
		return true;
	}

	public Object[] toArray() {
		return Arrays.copyOf(defaultArray, size);
	}

	public int size() {
		return size;
	}

	public boolean contains(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (defaultArray[i] == null) {
					return true;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(defaultArray[i])) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean containsAll(MyList c) {
		for (Object e : c.toArray()) {
			if (!contains(e)) {
				return false;
			}
		}
		return true;
	}

	public String toString() {
		return Arrays.deepToString(this.toArray());
	}

	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	public ListIterator listIterator() {
		return new ListIteratorImpl();
	}

	private class IteratorImpl implements Iterator<Object> {
		int cursor = 0; // index of next element to return
		int lastRet = -1; //index of last element returned, -1 if no such

		/* returns true if the iteration has more elements */
		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		/* returns the next element in the iteration */
		@Override
		public Object next() {
			int i = cursor;
			if (i >= size) {
				throw new NoSuchElementException();
			}
			Object[] newArray = MyListImpl.this.defaultArray;
			if (i >= newArray.length) {
				throw new ConcurrentModificationException();
			}
			cursor = i + 1;
			return newArray[lastRet = i];
		}

		/* removes the last element returned by this iterator */
		@Override
		public void remove() {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			try {
				MyListImpl.this.remove(MyListImpl.this.defaultArray[lastRet]);
				cursor = lastRet;
				lastRet = -1;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException(ex);
			}
		}

	}

	private class ListIteratorImpl extends IteratorImpl implements ListIterator {

		@Override
		public boolean hasPrevious() {
			return cursor != 0;
		}

		@Override
		public Object previous() {
			int i = cursor - 1;
			if (i < 0) {
				throw new NoSuchElementException();
			}
			Object[] elementData = MyListImpl.this.defaultArray;
			if (i >= elementData.length) {
				throw new ConcurrentModificationException();
			}
			cursor = i;
			return elementData[lastRet = i];
		}

		@Override
		public void set(Object e) {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			try {
				MyListImpl.this.defaultArray[lastRet] = e;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException(ex);
			}
		}

		public void remove() {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			try {
				MyListImpl.this.remove(MyListImpl.this.defaultArray[lastRet]);
				cursor = lastRet;
				lastRet = -1;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException(ex);
			}
		}
	}

}
