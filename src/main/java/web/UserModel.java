package web;

import java.util.ArrayList;
import java.util.List;
import metier.entities.User;
public class UserModel {
private String name;
List<User> users = new ArrayList<>();
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public List<User> getUsers() {
	return users;
}
public void setUsers(List<User> users) {
	this.users = users;
}

}
