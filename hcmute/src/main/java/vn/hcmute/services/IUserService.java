package vn.hcmute.services;

import vn.hcmute.models.UserModel;

public interface IUserService {
	public UserModel getUserByUsername(String username);
	public UserModel login(String username, String password);
}
