package ua.nure.mykytenko.SummaryTask4.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Ticket entity.
 * 
 * @author A.Mykytenko
 * 
 */
public class Ticket implements Serializable {

	private static final long serialVersionUID = 2866200029840644423L;

	private long id;

	private String firstName;

	private String lastName;

	private long userId;

	private int seatNum;

	private BigDecimal price;

	private long carriageId;

	private long routeId;

	private long routeDepId;

	private long routeArrId;

	private Date depDate;

	private Date arrDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public long getCarriageId() {
		return carriageId;
	}

	public void setCarriageId(long carriageId) {
		this.carriageId = carriageId;
	}

	public long getRouteId() {
		return routeId;
	}

	public Date getDepDate() {
		return depDate;
	}

	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}

	public Date getArrDate() {
		return arrDate;
	}

	public void setArrDate(Date arrDate) {
		this.arrDate = arrDate;
	}

	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}

	public long getRouteDepId() {
		return routeDepId;
	}

	public void setRouteDepId(long routeDepId) {
		this.routeDepId = routeDepId;
	}

	public long getRouteArrId() {
		return routeArrId;
	}

	public void setRouteArrId(long routeArrId) {
		this.routeArrId = routeArrId;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userId=" + userId
				+ ", seatNum=" + seatNum +  ", price="
				+ price + ", carriageId=" + carriageId + ", routeId=" + routeId + ", routeItemDepId=" + routeDepId
				+ ", routeItemArrId=" + routeArrId + "]";
	}
}
