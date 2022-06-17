package dao;

import metier.entities.admin;

public interface IadminDao {
	public admin getAdmin(String adresse);
	public admin updateAdmin(admin u);
	

}
