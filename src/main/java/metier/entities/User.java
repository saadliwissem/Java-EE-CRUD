package metier.entities;

import java.io.Serializable;

import dao.DaoExceptions;

public class User implements Serializable{
	private int id;
	private String name;
	private String adresse;
	private String pwd;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) throws DaoExceptions{
		if(!adresse.matches(".+@.+\\.[a-z]+"))
			throw new DaoExceptions("l'email est invalide !");
		this.adresse = adresse;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) throws DaoExceptions{
		if(pwd.length()<8)
			throw new DaoExceptions("le mot de passe doit comporter plus que 8 caractères !");
		this.pwd = pwd;
	}
	public User(String name, String adresse, String pwd) throws DaoExceptions{
		super();
		
		this.name = name;
		if(!adresse.matches(".+@.+\\.[a-z]+"))
			throw new DaoExceptions("l'email est invalide !");
		this.adresse = adresse;
		if(pwd.length()<8)
			throw new DaoExceptions("le mot de passe doit comporter plus que 8 caractères !");
		this.pwd = pwd;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", adresse=" + adresse + ", pwd=" + pwd + "]";
	}
	
}
