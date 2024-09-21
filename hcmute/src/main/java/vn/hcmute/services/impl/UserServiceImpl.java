package vn.hcmute.services.impl;

import vn.hcmute.dao.IUserDao;
import vn.hcmute.dao.impl.UserDaoImpl;
import vn.hcmute.models.UserModel;
import vn.hcmute.services.IUserService;

public class UserServiceImpl implements IUserService {
	private IUserDao userDao = new UserDaoImpl();

	@Override
	public UserModel getUserByUsername(String username) {
		return userDao.findUserByUsername(username);
	}

	@Override
	public UserModel login(String username, String password) {
		try {
			UserModel user = userDao.findUserByUsername(username);
			if (user != null && user.getPassword().equals(password)) {
				return user;
			} else {
				return null;
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		try {
			IUserService userService = new UserServiceImpl();
			UserModel user = userService.login("lb", "123");
			System.out.println(user.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
