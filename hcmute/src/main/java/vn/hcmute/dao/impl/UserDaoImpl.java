package vn.hcmute.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vn.hcmute.config.DBConnectionSQLServer;
import vn.hcmute.dao.IUserDao;
import vn.hcmute.models.UserModel;

public class UserDaoImpl implements IUserDao {
	public UserModel findUserByUsername(String username) {
		try {
			Connection conn = new DBConnectionSQLServer().getConnection();
			String sql = "select * from users where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			UserModel user = null;
			while (rs.next()) {
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
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addUser(UserModel user) {
		if (existsUser(user.getUsername())) {
			return false;
		} else {
			try {
				Connection conn = new DBConnectionSQLServer().getConnection();
				String sql = "insert into users(username, password, email, fullname, image, roleid, phone) values (?,?,?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getFullname());
				ps.setString(5, user.getImage());
				ps.setInt(6, user.getRoleid());
				ps.setString(7, user.getPhone());
				ps.executeUpdate();
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}
	}

	@Override
	public boolean existsUser(String username) {
		return this.findUserByUsername(username) != null;
	}

	@Override
	public boolean changePassword(String username, String password) {
		if (!this.existsUser(username)) {
			return false;
		} else {
			Connection conn;
			try {
				conn = new DBConnectionSQLServer().getConnection();

				String sql = "update users set password = ? where username = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, password);
				ps.setString(2, username);
				ps.executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public static void main(String[] args) {
		try {
			IUserDao userDao = new UserDaoImpl();
			System.out.println(userDao.changePassword("ldb", "123"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
