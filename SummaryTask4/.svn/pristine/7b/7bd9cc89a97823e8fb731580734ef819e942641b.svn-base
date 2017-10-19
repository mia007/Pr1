package ua.nure.mykytenko.SummaryTask4.db.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.db.DBUtil;
import ua.nure.mykytenko.SummaryTask4.db.entity.CarriageType;
import ua.nure.mykytenko.SummaryTask4.exception.DBException;
import ua.nure.mykytenko.SummaryTask4.util.PropertyContainer;

/**
 * Methods to manage carriage type entity
 * 
 * @author A.Mykytenko
 */
public class CarriageTypeService {

	private static final Logger LOG = Logger.getLogger(CarriageTypeService.class);

	/**
	 * Returns all carriage types.
	 * 
	 * @return List of carriage types.
	 */
	public List<CarriageType> getAll() {
		Connection conn = DBUtil.getConnection();
		Statement s = null;
		ResultSet rs = null;
		List<CarriageType> types = null;

		try {
			s = conn.createStatement();
			rs = s.executeQuery(PropertyContainer.get(Constants.SQL_GET_ALL_CAR_TYPES));
			types = new ArrayList<>();
			while (rs.next()) {
				types.add(extractType(rs));
			}

		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, s, rs);
		}
		return types;
	}

	/**
	 * Extracts a carriage type bean from the result set.
	 * 
	 * @param rs
	 *            Result set from which a carriage type bean will be extracted.
	 * @return Carriage type bean
	 */
	private CarriageType extractType(ResultSet rs) throws SQLException {
		CarriageType type = new CarriageType();
		type.setId(rs.getInt("id"));
		type.setName(rs.getString("name"));
		type.setSeatNum(rs.getInt("seat_num"));

		return type;
	}
}
