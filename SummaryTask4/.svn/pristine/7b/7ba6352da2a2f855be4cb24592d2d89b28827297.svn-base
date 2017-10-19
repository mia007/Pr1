package ua.nure.mykytenko.SummaryTask4.db.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Route entity.
 * 
 * @author A.Mykytenko
 * 
 */
public class Route implements Serializable {

	private static final long serialVersionUID = 4598337738091554308L;

	private int id;

	private Date date;

	private long trainId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return (Date) date.clone();
	}

	public void setDate(Date date) {
		this.date = (Date) date.clone();
	}

	public long getTrainId() {
		return trainId;
	}

	public void setTrainId(long trainId) {
		this.trainId = trainId;
	}

	@Override
	public String toString() {
		return "Route [id=" + id + ", date=" + date + ", trainId=" + trainId + "]";
	}
}
