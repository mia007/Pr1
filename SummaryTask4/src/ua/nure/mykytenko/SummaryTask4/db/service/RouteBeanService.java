package ua.nure.mykytenko.SummaryTask4.db.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.db.DBUtil;
import ua.nure.mykytenko.SummaryTask4.db.bean.RouteBean;
import ua.nure.mykytenko.SummaryTask4.exception.DBException;
import ua.nure.mykytenko.SummaryTask4.util.PropertyContainer;

/**
 * Methods to manage route bean
 * 
 * @author A.Mykytenko
 */
public class RouteBeanService {

	private static final Logger LOG = Logger.getLogger(RouteBeanService.class);

	/**
	 * Returns route beans  for the given train.
	 * 
	 * @return List of RouteBean.
	 */
	public List<RouteBean> getAllByTrainId(long trainId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RouteBean> beans = null;

		try {
			ps = conn.prepareStatement(PropertyContainer.get(Constants.GET_ROUTE_INFO_BY_TRAIN_ID));
			ps.setLong(1, trainId);

			rs = ps.executeQuery();

			beans = new ArrayList<>();
			while (rs.next()) {
				beans.add(extract(rs));
			}

			LOG.debug("beans obtained ==> " + beans);
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return beans;
	}

	/**
	 * Extracts a route bean from the result set.
	 * 
	 * @param rs
	 *            Result set from which a route bean will be extracted.
	 * @return RouteBean
	 */
	private RouteBean extract(ResultSet rs) throws SQLException {
		RouteBean bean = new RouteBean();
		bean.setArrTime(rs.getTime("arr_time"));
		bean.setDepTime(rs.getTime("dep_time"));
		bean.setStationName(rs.getString("name"));

		return bean;
	}
}
