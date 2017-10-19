package ua.nure.mykytenko.SummaryTask4.db.entity;

import java.io.Serializable;

/**
 * Carriage type entity.
 * 
 * @author A.Mykytenko
 * 
 */
public class CarriageType implements Serializable, Comparable<CarriageType> {

	private static final long serialVersionUID = -650751055652067704L;

	private int id;

	private String name;

	private int seatNum;

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

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CarriageType other = (CarriageType) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CarriageType [id=" + id + ", name=" + name + ", seatNum=" + seatNum + "]";
	}

	@Override
	public int compareTo(CarriageType o) {
		return o.name.compareTo(name);
	}
}
