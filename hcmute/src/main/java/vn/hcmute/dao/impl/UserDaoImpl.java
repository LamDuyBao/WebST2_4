package vn.hcmute.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vn.hcmute.config.DBConnectionSQLServer;
import vn.hcmute.dao.IUserDao;
import vn.hcmute.models.UserModel;

public class UserDaoImpl implements IUserDao{
	public UserModel findUserByUsername(String username) {
		try {
			Connection conn = new DBConnectionSQLServer().getConnection();
			String sql = "select * from users where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			UserModel user = null;
			while(rs.next()) {
				user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setImage(rs.getString("image"));
				user.setFullname(rs.getString("fullname"));
				user.setRoleid(rs.getInt("roleid"));
				user.setPhone(rs.getString("phone"));
			}
			conn.close();
			return user;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
		try {
			IUserDao userDao = new UserDaoImpl();
			UserModel user = userDao.findUserByUsername("ldb");
			System.out.println(user.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
