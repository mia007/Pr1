package ua.nure.mykytenko.SummaryTask4.db.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;

import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.db.DBUtil;
import ua.nure.mykytenko.SummaryTask4.db.entity.RouteItem;
import ua.nure.mykytenko.SummaryTask4.db.entity.Train;
import ua.nure.mykytenko.SummaryTask4.exception.DBException;
import ua.nure.mykytenko.SummaryTask4.util.DateUtil;
import ua.nure.mykytenko.SummaryTask4.util.PropertyContainer;

/**
 * Methods to manage train entity
 * 
 * @author A.Mykytenko
 */
public class TrainService {

	private static final Logger LOG = Logger.getLogger(TrainService.class);
	
	/**
	 * Creates train entity
	 * 
	 * @param item
	 *            Train to create
	 */
	public boolean create(Train item) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Constants.SQL_CREATE_TRAIN),
					Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			ps.setString(k++, item.getTag());
			ps.setBigDecimal(k++, item.getPrice());
			result = ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (!rs.next()) {
				throw new DBException("A key for train was not generated");
			}
			item.setId(rs.getInt(1));

			if (!createRouteItems(conn, item.getRouteItems(), item.getId())) {
				throw new DBException("Error while creating RouteItems");
			}

			conn.commit();
		} catch (SQLException e) {
			DBUtil.rollback(conn);
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
		return result != 0;
	}
	
	/**
	 * Deletes train entity
	 * 
	 * @param id
	 *            Id of the train to delete
	 */
	public boolean delete(long id) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int result = 0;

		try {
			ps = conn.prepareStatement(PropertyContainer.get(Constants.SQL_DELETE_TRAIN));
			ps.setLong(1, id);
			result = ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			DBUtil.rollback(conn);
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
		return result != 0;

	}
	
	/**
	 * Creates route items for the required train
	 * 
	 * @param con
	 *            Connection
	 * @param items
	 *           List of trains to create
	 * @param trainId
	 *           Train for which route items will be created
	 *                    .
	 */
	private boolean createRouteItems(Connection conn, List<RouteItem> items, int trainId)
			throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Constants.SQL_CREATE_ROUTE_ITEM));

			for (RouteItem ri : items) {
				int k = 1;
				ps.setObject(k++, DateUtil.getTime(ri.getArrivalTime()),
						Types.TIME);
				ps.setObject(k++, DateUtil.getTime(ri.getDepartureTime()),
						Types.TIME);
				ps.setInt(k++, ri.getOrdinal());
				ps.setLong(k++, trainId);
				ps.setLong(k++, ri.getStationId());
				ps.addBatch();
			}
			result = DBUtil.isBatchSuccessful(ps.executeBatch());
		} finally {
			DBUtil.close(null, ps, rs);
		}

		return result;
	}

	/**
	 * Returns a train with the given id.
	 * 
	 * @param id
	 *            Train id.
	 * @return Train entity.
	 */
	public Train get(long id) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Train train = null;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Constants.SQL_GET_TRAIN_BY_ID));
			ps.setLong(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {
				train = extract(rs);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return train;
	}

	/**
	 * Extracts a train bean from the result set.
	 * 
	 * @param rs
	 *            Result set from which a train bean will be extracted.
	 * @return Train bean
	 */
	private Train extract(ResultSet rs) throws SQLException {
		Train train = new Train();
		train.setId(rs.getInt("id"));
		train.setPrice(rs.getBigDecimal("price"));
		train.setTag(rs.getString("tag"));
		return train;
	}

}
