package vn.hcmute.dao;

import vn.hcmute.models.UserModel;

public interface IUserDao {
	public UserModel findUserByUsername(String username);
}
