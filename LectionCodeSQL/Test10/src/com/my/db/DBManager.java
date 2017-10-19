package com.my.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.my.AppException;
import com.my.db.entity.User;

public class DBManager {

	// /////////////////////////////////
	// SQL queries

	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";

	private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?);";

	// /////////////////////////////////

	private final String connectionURL;

	// ////////////////////////////////
	// Singleton

	private static DBManager instance;

	public synchronized static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("app.properties"));
		} catch (IOException ex) {
			// (1) write to log
			// LOG.error("Cannot find app.properties!!!", ex);

			throw new IllegalStateException("Cannot find app.properties!!!", ex);
		}
		connectionURL = props.getProperty("connection.url");
	}

	// /////////////////////////////////

	public boolean insertUser(User user) throws AppException {
		boolean res = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			pstmt = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			pstmt.setString(k++, user.getLogin());
			pstmt.setString(k++, user.getPassword());
			pstmt.setString(k++, user.getName());

			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					user.setId(id);
					res = true;
				}
			}
		} catch (SQLException ex) {
			// (1) write to log
			// LOG.error("cannot insert a user: " + user, ex);

			throw new AppException("Cannot insert a user: " + user, ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return res;
	}

	public User findUserByLogin(String login) throws AppException {
		User user = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException ex) {
			// (1) write to log
			// LOG.error("cannot obtain a user by login: " + login, ex);

			throw new AppException("Cannot obtain a user by login, login="
					+ login, ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return user;
	}

	public List<User> findAllUsers() throws AppException {
		List<User> users = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_USERS);
			while (rs.next()) {
				users.add(extractUser(rs));
			}
		} catch (SQLException ex) {
			// (1) write to log
			// LOG.error("cannot obtain a user by login: " + login, ex);

			throw new AppException("Cannot obtain all users", ex);
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		return users;
	}

	// /////////////////////////////
	// util methods

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		return user;
	}

	private Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(connectionURL);
		System.out.println(con);
		// ...
		return con;
	}

	private void close(AutoCloseable ac) {
		if (ac != null) {
			try {
				ac.close();
			} catch (Exception ex) {
				// (1) write to log
			}
		}

	}
}
