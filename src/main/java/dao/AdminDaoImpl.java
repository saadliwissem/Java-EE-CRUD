package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import metier.SingletonConnection;
import metier.entities.admin;

public class AdminDaoImpl implements IadminDao{

	@Override
	public admin getAdmin(String adresse) {
		Connection conn=SingletonConnection.getConnection();
		 admin a = new admin();
		 try {
		PreparedStatement ps= conn.prepareStatement("select * from admin where adresse = ?");
		ps.setString(1, adresse);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
		
		try {
			a.setAdresse(rs.getString("adresse"));
			a.setPwd(rs.getString("pwd"));
		} catch (DaoExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return a;
	}

	@Override
	public admin updateAdmin(admin a) {
		Connection conn=SingletonConnection.getConnection();
		 try {
		PreparedStatement ps= conn.prepareStatement("UPDATE admin SET adresse=?,pwd=? WHERE adresse=?");
		ps.setString(1, a.getAdresse());
		ps.setString(2, a.getPwd());
		ps.setString(3, a.getAdresse());
		
		ps.executeUpdate();
		ps.close();
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return a;
	}
}
