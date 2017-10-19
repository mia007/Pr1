package ua.nure.mykytenko.SummaryTask4.db.bean;

import java.io.Serializable;

import ua.nure.mykytenko.SummaryTask4.db.entity.Carriage;
import ua.nure.mykytenko.SummaryTask4.db.entity.RouteItem;
import ua.nure.mykytenko.SummaryTask4.db.entity.Station;

/**
 * Order bean.
 * 
 * @author A.Mykytenko
 * 
 */
public class TicketOrderBean implements Serializable {

	private static final long serialVersionUID = 5043178011947694361L;

	private String firstName;

	private String lastName;

	private Station stationFrom;

	private Station stationTo;

	private RouteItem routeItemFrom;

	private RouteItem routeItemTo;

	private TrainBean trainBean;

	private int seatNum;

	private Carriage carriage;

	private long ticketId;

	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public TrainBean getTrainBean() {
		return trainBean;
	}

	public void setTrainBean(TrainBean trainBean) {
		this.trainBean = trainBean;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public Carriage getCarriage() {
		return carriage;
	}

	public Station getStationFrom() {
		return stationFrom;
	}

	public void setStationFrom(Station stationFrom) {
		this.stationFrom = stationFrom;
	}

	public Station getStationTo() {
		return stationTo;
	}

	public void setStationTo(Station stationTo) {
		this.stationTo = stationTo;
	}

	public RouteItem getRouteItemFrom() {
		return routeItemFrom;
	}

	public void setRouteItemFrom(RouteItem routeItemFrom) {
		this.routeItemFrom = routeItemFrom;
	}

	public RouteItem getRouteItemTo() {
		return routeItemTo;
	}

	public void setRouteItemTo(RouteItem routeItemTo) {
		this.routeItemTo = routeItemTo;
	}

	public void setCarriage(Carriage carriage) {
		this.carriage = carriage;
	}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ticketId ^ (ticketId >>> 32));
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
		TicketOrderBean other = (TicketOrderBean) obj;
		if (ticketId != other.ticketId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TicketOrderBean [firstName=" + firstName + ", lastName="
				+ lastName + ", trainBean=" + trainBean + ", seatNum=" + seatNum
				+ ", carriage=" + carriage + "]";
	}

}
