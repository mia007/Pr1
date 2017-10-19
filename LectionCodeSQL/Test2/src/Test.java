import com.my.db.DBManager;
import com.my.db.entity.User;


public class Test {
	
	public static void main(String[] args) throws Exception {
		DBManager dbManager = DBManager.getInstance();
		for (User user : dbManager.findAllUsers()) {
			System.out.println(user);
		}
		System.out.println("~~~~~~~~~~~~~");
		User user = new User();
		user.setLogin("asdf2");
		user.setPassword("asdf");
		user.setName("asdf");
		boolean res = dbManager.insertUser(user);
		System.out.println(res);
		
		System.out.println("~~~~~");
		for (User u : dbManager.findAllUsers()) {
			System.out.println(u);
		}
	}

}
