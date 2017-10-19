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

import com.my.db.entity.User;

public class DBManager {

	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";

	private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?)";

	// ////////////////////////////
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
			throw new IllegalStateException("app.properties not found!", ex);
		}
		connectionUrl = props.getProperty("connection.url");
	}

	private final String connectionUrl;

	// //////////////////////////////

	public boolean insertUser(User user) {
		boolean result = false;
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
					result = true;
				}
			}
		} catch (SQLException ex) {
			// (1) write to log
			// LOG.error("Cannot insert a user " + user, ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con); // <== 
		}
		return result;
	}

	public User findUserByLogin(String login) {
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
			// LOG.error("Cannot find user by login: " + login, ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con); // <== 
		}
		return user;
	}
	
	
	public List<User> findAllUsers() {
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
			// LOG.error("Cannot obtain all users", ex);
		} finally {
			close(rs);
			close(stmt);
			close(con); // <== 
		}
		return users;
	}

	// /////////////////////////////
	// util methods
	
	private void close(AutoCloseable ac) {
		if (ac != null) {
			try {
				ac.close();
			} catch (Exception ex) {
				// (1) write to log				
				// LOG.error("Cannot close a resource: " + ac, ex);
			}
		}
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		return user;
	}

	private Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(connectionUrl);
		// ...
		return con;
	}

}
