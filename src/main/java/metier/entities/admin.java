package metier.entities;

import java.io.Serializable;

import dao.DaoExceptions;

public class admin implements Serializable{
	private String adresse;
	private String pwd;
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
	public admin(String adresse, String pwd)throws DaoExceptions {
		super();
		if(!adresse.matches(".+@.+\\.[a-z]+"))
			throw new DaoExceptions("l'email est invalide !");
		this.adresse = adresse;
		if(pwd.length()<8)
			throw new DaoExceptions("le mot de passe doit comporter plus que 8 caractères !");
		this.pwd = pwd;
	}
	public admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
