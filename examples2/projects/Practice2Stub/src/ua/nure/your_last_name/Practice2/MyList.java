package ua.nure.your_last_name.Practice2;

public interface MyList extends Iterable<Object> {
	
	void add(Object el);

	void clear();

	boolean remove(Object el);

	Object[] toArray();

	int size();

	boolean contains(Object el);

	boolean containsAll(MyList another);

}