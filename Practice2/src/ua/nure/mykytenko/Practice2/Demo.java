package ua.nure.mykytenko.Practice2;

import java.util.Iterator;

public class Demo {
	public static void main(String[] args) {
		MyListImpl m1 = new MyListImpl();
		MyListImpl c = new MyListImpl();
		m1.add(2);
		m1.add(3);
		m1.add(new Object());
		m1.add("A");
		System.out.println(m1.size());
		System.out.println(m1);
		System.out.println(m1.remove(1));
		System.out.println(m1.contains(1));
		c.add(2);
		c.add(3);
		System.out.println(c);
		System.out.println(m1.containsAll(c));
		MyList con = new MyListImpl();
		con.add("A");
		con.add("B");
		con.add(433);
		con.add(888);
		con.add(new Object());
		System.out.println(con);

		Iterator<Object> it = con.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		MyListImpl list = new MyListImpl();
		ListIterator item = list.listIterator();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		while (item.hasNext()) {
			System.out.print(item.next() + " ");
		}
		System.out.println();

		while (item.hasPrevious()) {
			System.out.print(item.previous() + " ");
		}
		System.out.println();

	}
}
