package ua.nure.mykytenko.Practice8.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.mykytenko.Practice8.db.entity.Group;
import ua.nure.mykytenko.Practice8.db.entity.User;



public class DBManager {

	private static final String SQL_FIND_USER = "SELECT * FROM users WHERE login = ?";
	
	private static final String SQL_FIND_GROUP = "SELECT * FROM groups WHERE name = ?";
	
	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
	
	private static final String SQL_FIND_ALL_GROUPS = "SELECT * FROM groups";
	
	private static final String SQL_FIND_USER_GROUPS = "SELECT g.id, g.name FROM users_groups ug join groups g on (ug.group_id=g.id) WHERE ug.user_id = ?;";

	private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?);";
	
	private static final String SQL_INSERT_GROUP = "INSERT INTO groups VALUES (DEFAULT, ?);";
	
	private static final String SQL_INSERT_USERS_GROUP = "INSERT INTO users_groups VALUES (?, ?);";
	
	private static final String SQL_DELETE_GROUP = "DELETE from groups where name = ?;";
	
	private static final String SQL_UPDATE_GROUP = "UPDATE groups SET name = ? where id = ? ;";

	
	private static final String URL = 
			"jdbc:mysql://localhost/practice8?user=root&password=root&useSSL=false";
	
	private static DBManager instance;

	public synchronized static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() {
		//
	}

	private Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(URL);
		//System.out.println(con);
		return con;
	}
	
	public boolean insertUser(User user) {
		boolean res = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			pstmt = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getLogin());

			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					user.setId(id);
					res = true;
				}
			}
		} catch (SQLException ex) {
			System.out.println(ex); 

		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return res;
	}
	
	public User getUser(String login){
		User user = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			pstmt = con.prepareStatement(SQL_FIND_USER);
			pstmt.setString(1, login);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
		} catch (SQLException ex) {
			System.out.println(ex); 
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return user;
	}
	
	public List<User> findAllUsers(){
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
			System.out.println(ex); 

		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		return users;
	}
	
	public boolean insertGroup(Group group) {
		boolean res = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			pstmt = con.prepareStatement(SQL_INSERT_GROUP, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, group.getName());

			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					group.setId(id);
					res = true;
				}
			}
		} catch (SQLException ex) {
			System.out.println(ex); 

		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return res;
	}
	
	public Group getGroup(String name){
		Group group = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			pstmt = con.prepareStatement(SQL_FIND_GROUP);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				group = extractGroup(rs);
			}
		} catch (SQLException ex) {
			System.out.println(ex); 
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return group;
	}
	
	public boolean setGroupsForUser(User user, Group ... groups) {
		boolean res = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(SQL_INSERT_USERS_GROUP);
			int i = 0;

	        for (Group g : groups) {
	            pstmt.setInt(1, user.getId());
	            pstmt.setInt(2, g.getId());

	            pstmt.addBatch();
	            i++;

	            if (i % 1000 == 0 || i == groups.length) {
	                pstmt.executeBatch(); 
	            }
	        }
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			System.out.println(ex);
			} finally {
			close(con);
		}
		return res;
	}

	public List<Group>  getUserGroups(User user){
		List<Group> groups = new ArrayList<>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			pstmt = con.prepareStatement(SQL_FIND_USER_GROUPS);
			pstmt.setInt(1, user.getId());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				groups.add(extractGroup(rs));
			}
		} catch (SQLException ex) {
			System.out.println(ex); 

		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return groups;
	}
	
	public List<Group> findAllGroups(){
		List<Group> groups = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_GROUPS);
			while (rs.next()) {
				groups.add(extractGroup(rs));
			}
		} catch (SQLException ex) {
			System.out.println(ex); 
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		return groups;
	}
	
	public void deleteGroup(Group group) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(SQL_DELETE_GROUP);
			pstmt.setString(1, group.getName());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			System.out.println(ex);
		} finally {
			close(con);
			close(pstmt);
		}
	}
	
	public void updateGroup(Group group) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(SQL_UPDATE_GROUP);
			int i = 1;
			pstmt.setString(i++, group.getName());
			pstmt.setInt(i++, group.getId());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			System.out.println(ex);
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		return user;
	}
	
	private Group extractGroup(ResultSet rs) throws SQLException {
		Group group = new Group();
		group.setId(rs.getInt("id"));
		group.setName(rs.getString("name"));
		return group;
	}

	private void close(AutoCloseable ac) {
		if (ac != null) {
			try {
				ac.close();
			} catch (Exception ex) {
				System.out.println(ex); 
			}
		}

	}
	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				System.out.println(ex); 
			}
		}
	}
	
}
