package dao;

import java.util.List;

import metier.entities.User;

public interface IuserDao {
	public User save(User u);
	public List<User> userParName(String un);
	public User getUser(String adresse);
	public User updateuser(User u);
	public void deleteUser(int id);
}
