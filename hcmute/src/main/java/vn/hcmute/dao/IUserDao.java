package vn.hcmute.dao;

import vn.hcmute.models.UserModel;

public interface IUserDao {
	public UserModel findUserByUsername(String username);
	public boolean addUser(UserModel user);
	public boolean existsUser(String username);
	public boolean changePassword(String username, String password);
}
