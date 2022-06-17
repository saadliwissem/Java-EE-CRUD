package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.SingletonConnection;
import metier.entities.User;

public class UserDaoImpl implements IuserDao{

	
	
	@Override
	public User save(User u) {
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps= conn.prepareStatement("INSERT INTO users(name,adresse,pwd) VALUES(?,?,?)");
			ps.setString(1, u.getName());
			ps.setString(2, u.getAdresse());
			ps.setString(3, u.getPwd());
			ps.executeUpdate();
			PreparedStatement ps2= conn.prepareStatement
			("SELECT MAX(id) as MAX_ID FROM users");
			ResultSet rs =ps2.executeQuery();
			if (rs.next()) {
			u.setId(rs.getInt("MAX_ID"));
			}
			ps.close();
			ps2.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			}
		return u;
	}

	@Override
	public List<User> userParName(String un) {
		List<User> users= new ArrayList<User>();
		 Connection conn=SingletonConnection.getConnection();
		 try {
			PreparedStatement ps= conn.prepareStatement("select * from users where name LIKE ?");
			ps.setString(1, "%"+un+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				try {
					User u = new User();
					u.setId(rs.getInt("id"));
					u.setName(rs.getString("name"));
					u.setAdresse(rs.getString("adresse"));
					u.setPwd(rs.getString("pwd"));
					users.add(u);
				} 
				catch (DaoExceptions e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return users;
		}

	@Override
	public User getUser(String adresse) {
		Connection conn=SingletonConnection.getConnection();
		 User u = new User();
		 try {
		PreparedStatement ps= conn.prepareStatement("select * from users where adresse = ?");
		ps.setString(1, adresse);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
		
		try {
			u.setId(rs.getInt("id"));
			u.setName(rs.getString("name"));
			u.setAdresse(rs.getString("adresse"));
			u.setPwd(rs.getString("pwd"));
		} catch (DaoExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return u;
	}

	@Override
	public User updateuser(User u) {
		Connection conn=SingletonConnection.getConnection();
		 try {
		PreparedStatement ps= conn.prepareStatement("UPDATE users SET name=?,adresse=?,pwd=? WHERE id=?");
		ps.setString(1, u.getName());
		ps.setString(2, u.getAdresse());
		ps.setString(3, u.getPwd());
		ps.setInt(4, u.getId());
		ps.executeUpdate();
		ps.close();
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return u;
	}

	@Override
	public void deleteUser(int id) {
		Connection conn=SingletonConnection.getConnection();
		 try {
		PreparedStatement ps= conn.prepareStatement("DELETE FROM users WHERE id = ?");
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
		} catch (SQLException e) {
		e.printStackTrace();
		}
		
	}

}
