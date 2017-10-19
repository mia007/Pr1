package ua.nure.mykytenko.SummaryTask4.db.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.db.entity.User;
import ua.nure.mykytenko.SummaryTask4.util.Util;
import ua.nure.mykytenko.SummaryTask4.db.entity.Role;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.db.DBUtil;
import ua.nure.mykytenko.SummaryTask4.exception.DBException;
import ua.nure.mykytenko.SummaryTask4.util.PropertyContainer;

/**
 * Methods to manage user entity
 * 
 * @author A.Mykytenko
 */
public class UserService {

	private static final Logger LOG = Logger.getLogger(UserService.class);

	/**
	 * Method creates user entity
	 * 
	 * @param user
	 *            User to create.
	 */
	public boolean create(User user) {
		// hashing password to store it in BD
		String hash = generateHash(user.getEmail(), user.getPassword());
		user.setPassword(hash);
		LOG.debug(String.format("Adding user %s", user.toString()));

		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		final String query = PropertyContainer.get(Constants.INSERT_USER);

		try {
			ps = conn.prepareStatement(query);
			int k = 1;
			ps.setString(k++, user.getEmail());
			ps.setString(k++, user.getPassword());
			ps.setString(k++, user.getFirstName());
			ps.setString(k++, user.getLastName());
			ps.setLong(k++, user.getRole().getId());

			int count = ps.executeUpdate();
			conn.commit();
			if (count > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			DBUtil.rollback(conn);
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	/**
	 * Retrieves user entity from DB with given email and compares the provided
	 * password with the one from DB
	 * 
	 * @param login
	 *            String login to find the user.
	 * @param password
	 *            String password to match the password from DB.
	 */
	public User auth(String login, String password) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		final String query = PropertyContainer.get(Constants.GET_USER_BY_EMAIL);

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, login);

			rs = ps.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		LOG.debug(user);
		// creating hash string of the provided password to compare with the
		// hashed password from DB
		if (user != null) {
			String inPassword = generateHash(login, password);
			LOG.debug("Hash string = " + inPassword);
			if (user.getPassword().equals(inPassword)) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Returns hashed String using user's login and password
	 * 
	 * @param email
	 *            Email of the user.
	 * @param password
	 *            Password of the user
	 * @return String of the hashed input
	 */
	private String generateHash(String email, String password) {
		StringBuilder input = new StringBuilder(password).append(email);
		LOG.debug("Hashed input = " + input);
		return Util.hash(input.toString());
	}

	/**
	 * Returns a user with the given id.
	 * 
	 * @param id
	 *            User id.
	 * @return User entity.
	 */
	public User get(long id) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		final String query = PropertyContainer.get(Constants.GET_USER_BY_ID);

		try {
			ps = conn.prepareStatement(query);
			ps.setLong(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return user;
	}

	/**
	 * Extracts a user bean from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user bean will be extracted.
	 * @return User bean
	 */
	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setFirstName(rs.getString("f_name"));
		user.setLastName(rs.getString("l_name"));
		user.setRole(Role.get(rs.getLong("role_id")));
		return user;
	}
}
