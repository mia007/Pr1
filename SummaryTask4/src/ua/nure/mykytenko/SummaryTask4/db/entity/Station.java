package ua.nure.mykytenko.SummaryTask4.db.entity;

import java.io.Serializable;

/**
 * Station entity.
 * 
 * @author A.Mykytenko
 * 
 */
public class Station implements Serializable {

	private static final long serialVersionUID = -8719779295565545313L;

	private int id;

	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Station [id=" + id + ", name=" + name + "]";
	}
}
