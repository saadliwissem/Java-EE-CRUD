package dao;
import java.util.List;
import metier.entities.User;
public class TestDao {
	public static void main(String[] args) {
		UserDaoImpl udao= new UserDaoImpl();
		User prod= udao.save(new User("wissem","saadliwissem88@gmail.com","76003475w%"));
		System.out.println(prod);
		List<User> users =udao.userParName("wissem");
		for (User u : users)
		System.out.println(u);
	}
}
