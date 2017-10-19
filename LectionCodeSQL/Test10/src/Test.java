import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Test {
	
	private static final String URL = 
		"jdbc:mysql://localhost:3333/testDB?user=testUser&password=testPass";
		//"jdbc:derby://localhost:1527/testDB;user=testUser;password=testPass;create=true";

	public static void main(String[] args) throws Exception {
		// (1) obtain a connection
		Connection con = DriverManager.getConnection(URL);
		System.out.println(con);
		
		// (2) create a statement
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT login FROM users");
		
		// (3) proccess a result
		while (rs.next()) {
			String login = rs.getString("login");
			System.out.println(login);
		}
		
		
	}

}
