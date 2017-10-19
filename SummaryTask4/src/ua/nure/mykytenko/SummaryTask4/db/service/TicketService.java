package ua.nure.mykytenko.SummaryTask4.db.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.db.DBUtil;
import ua.nure.mykytenko.SummaryTask4.db.entity.Carriage;
import ua.nure.mykytenko.SummaryTask4.db.entity.Station;
import ua.nure.mykytenko.SummaryTask4.db.entity.Ticket;
import ua.nure.mykytenko.SummaryTask4.db.bean.TicketBean;
import ua.nure.mykytenko.SummaryTask4.db.bean.TicketOrderBean;
import ua.nure.mykytenko.SummaryTask4.db.bean.TrainBean;
import ua.nure.mykytenko.SummaryTask4.exception.DBException;
import ua.nure.mykytenko.SummaryTask4.util.PropertyContainer;

/**
 * Methods to manage ticket entity
 * 
 * @author A.Mykytenko
 */
public class TicketService {

	private static final Logger LOG = Logger.getLogger(TicketService.class);

	/**
	 * Creates ticket entity
	 * 
	 * @param ticket
	 *            Ticket to create
	 */
	public boolean create(Ticket ticket) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int res = 0;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Constants.SQL_INSERT_TICKET),
					Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			ps.setString(k++, ticket.getFirstName());
			ps.setString(k++, ticket.getLastName());
			ps.setInt(k++, ticket.getSeatNum());
			ps.setBigDecimal(k++, ticket.getPrice());
			ps.setTimestamp(k++, new Timestamp(ticket.getDepDate().getTime()));
			ps.setTimestamp(k++, new Timestamp(ticket.getArrDate().getTime()));
			ps.setLong(k++, ticket.getUserId());
			ps.setLong(k++, ticket.getCarriageId());
			ps.setLong(k++, ticket.getRouteId());
			ps.setLong(k++, ticket.getRouteDepId());
			ps.setLong(k++, ticket.getRouteArrId());

			LOG.debug("Ticket statement ==> " + ps);

			res = ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				ticket.setId(rs.getLong(1));
			}
			conn.commit();
		} catch (SQLException e) {
			DBUtil.rollback(conn);

			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return res != 0;
	}
	
	public List<TicketBean> getTicketNum() {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TicketBean> beans = null;

		try {
			ps = conn.prepareStatement(PropertyContainer
					.get(Constants.GET_TICKET_NUM));

			rs = ps.executeQuery();

			beans = new ArrayList<>();
			while (rs.next()) {
				beans.add(extractTicketBean(rs));
			}
			LOG.info(String.format("Tickets number found ==> %s", beans.toString()));
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
		return beans;
	}
	
	private TicketBean extractTicketBean(ResultSet rs) throws SQLException {
		TicketBean bean = new TicketBean();
		bean.setEmail(rs.getString("email"));
		bean.setFirstName(rs.getString("f_name"));
		bean.setLastName(rs.getString("l_name"));
		bean.setTicketNum(rs.getInt("ticket_num"));

		return bean;
	}

	/**
	 * Returns ticket order beans for the given user.
	 * 
	 * @return List of ticket order beans.
	 */
	public List<TicketOrderBean> getAllByUserId(long userId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TicketOrderBean> beans = null;

		try {
			ps = conn.prepareStatement(PropertyContainer
					.get(Constants.SQL_GET_ALL_TICKETS_BY_USER_ID));
			ps.setLong(1, userId);

			rs = ps.executeQuery();

			beans = new ArrayList<>();
			while (rs.next()) {
				beans.add(extractBean(rs));
			}
			LOG.info(String.format("Tickets found for userId=%d ==> %s", userId,
					beans.toString()));
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
		return beans;
	}

	/**
	 * Extracts a ticket order bean from the result set.
	 * 
	 * @param rs
	 *            Result set from which a ticket order bean will be extracted.
	 * @return TicketOrderBean 
	 */
	private TicketOrderBean extractBean(ResultSet rs) throws SQLException {
		TicketOrderBean bean = new TicketOrderBean();
		bean.setTicketId(rs.getLong("id"));
		bean.setFirstName(rs.getString("f_name"));
		bean.setLastName(rs.getString("l_name"));
		bean.setSeatNum(rs.getInt("seat_num"));

		Station s = new Station();
		s.setName(rs.getString("st_from"));
		bean.setStationFrom(s);
		s = new Station();
		s.setName(rs.getString("st_to"));
		bean.setStationTo(s);

		Carriage c = new Carriage();
		c.setPrice(rs.getBigDecimal("price"));
		c.setTag(rs.getString("car_tag"));

		TrainBean trainBean = new TrainBean();
		trainBean.setTrainTag(rs.getString("train_tag"));
		trainBean.setDepDate(rs.getTimestamp("dep_date"));
		trainBean.setArrDate(rs.getTimestamp("arr_date"));

		bean.setCarriage(c);
		bean.setTrainBean(trainBean);

		return bean;
	}

	public boolean exists(TicketOrderBean bean) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Integer> seatsTaken = null;
		try {

			ps = conn.prepareStatement(PropertyContainer
					.get(Constants.GET_TAKEN_SEATS_BY_CAR_ID_AND_ROUTE_ID));
			int k = 1;
			ps.setLong(k++, bean.getCarriage().getId());
			ps.setLong(k++, bean.getTrainBean().getRouteId());
			ps.setLong(k++, bean.getTrainBean().getTrainId());
			ps.setLong(k++, bean.getTrainBean().getRouteItemIdTo());
			ps.setLong(k++, bean.getTrainBean().getTrainId());
			ps.setLong(k++, bean.getTrainBean().getRouteItemIdFrom());

			rs = ps.executeQuery();

			seatsTaken = new ArrayList<>();
			while (rs.next()) {
				seatsTaken.add(rs.getInt(1));
			}

			LOG.debug(
					String.format("Seats found ==> %s", seatsTaken.toString()));

		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
		return seatsTaken.contains(bean.getSeatNum());
	}

}