import com.my.db.DBManager;
import com.my.db.entity.User;


public class Test2 {
	
	public static void main(String[] args) throws Exception {
		DBManager dbManager = DBManager.getInstance();
		
		User user = dbManager.findUserByLogin("admin");
		System.out.println(user);
		System.out.println("~~~");
		
		for (User u : dbManager.findAllUsers()) {
			System.out.println(u);
		}
		
		System.out.println("~~~~");
		
		User user2 = new User();
		user2.setLogin("asdf2");
		user2.setPassword("asdf2");
		user2.setName("asdf2");
		
		// = 0
		dbManager.insertUser(user2); // ??? id
		// =
		
		System.out.println("~~~");
		
		for (User u : dbManager.findAllUsers()) {
			System.out.println(u);
		}
		
	}

}
