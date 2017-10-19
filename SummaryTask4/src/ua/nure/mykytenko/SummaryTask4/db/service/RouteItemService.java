package ua.nure.mykytenko.SummaryTask4.db.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.db.DBUtil;
import ua.nure.mykytenko.SummaryTask4.db.entity.RouteItem;
import ua.nure.mykytenko.SummaryTask4.exception.DBException;
import ua.nure.mykytenko.SummaryTask4.util.PropertyContainer;

/**
 * Methods to manage route item entity
 * 
 * @author A.Mykytenko
 */
public class RouteItemService {

	private static final Logger LOG = Logger.getLogger(RouteItemService.class);

	/**
	 * Returns route items by train id and stations.
	 * 
	 * @return List of route item entities.
	 */
	public List<RouteItem> getAllByStations(long trainId, long stFromId, long stToId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RouteItem> items;

		try {
			ps = conn.prepareStatement(PropertyContainer.get(Constants.GET_ALL_ROUTE_ITEMS_BY_TRAIN_ID_FROM_TO));
			int k = 1;
			ps.setLong(k++, trainId);
			ps.setLong(k++, stFromId);
			ps.setLong(k++, trainId);
			ps.setLong(k++, stToId);
			ps.setLong(k++, trainId);

			rs = ps.executeQuery();

			items = new ArrayList<>();
			while (rs.next()) {
				items.add(extract(rs));
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
			throw new DBException(ex.getMessage(), ex);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return items;
	}
	
	/**
	 * Extracts a route item bean from the result set.
	 * 
	 * @param rs
	 *            Result set from which a route item bean will be extracted.
	 * @return RouteItem bean
	 */
	private RouteItem extract(ResultSet rs) throws SQLException {
		RouteItem item = new RouteItem();
		item.setId(rs.getLong("id"));
		item.setArrivalTime(rs.getTime("arr_time"));
		item.setDepartureTime(rs.getTime("dep_time"));
		item.setOrdinal(rs.getInt("ordinal"));
		item.setTrainId(rs.getInt("train_id"));
		item.setStationId(rs.getInt("station_id"));

		return item;
	}

	/**
	 * Returns all route items 
	 * 
	 * @return List of route item entities.
	 */
	public List<RouteItem> getAll() {
		Connection conn = DBUtil.getConnection();
		Statement s = null;
		ResultSet rs = null;
		List<RouteItem> routeItems = null;

		try {
			s = conn.createStatement();
			rs = s.executeQuery(PropertyContainer.get(Constants.SQL_GET_ALL_ROUTE_ITEMS));

			routeItems = new ArrayList<>();
			while (rs.next()) {
				routeItems.add(extract(rs));
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
			throw new DBException(ex.getMessage(), ex);
		} finally {
			DBUtil.close(conn, s, rs);
		}

		return routeItems;
	}
	
	/**
	 * Returns route item by train id and station id.
	 * 
	 * @return Route item entity.
	 */
	public RouteItem getByTrainAndStation(long trainId, long stationId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		RouteItem ri = null;

		try {
			ps = conn.prepareStatement(PropertyContainer.get(Constants.SQL_GET_ROUTE_ITEM_BY_TRAIN_AND_STATION));
			int k = 1;
			ps.setLong(k++, stationId);
			ps.setLong(k++, trainId);

			rs = ps.executeQuery();

			if (rs.next()) {
				ri = extract(rs);
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
			throw new DBException(ex.getMessage(), ex);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return ri;
	}
}
