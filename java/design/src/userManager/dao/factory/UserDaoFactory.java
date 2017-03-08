package userManager.dao.factory;

import userManager.dao.api.UserDAO;
import userManager.dao.impl.UserDaoMysqlIpml;

public class UserDaoFactory {
	private UserDaoFactory(){
	}
	
	public static UserDAO getUserDAO() {
		return new UserDaoMysqlIpml();
	}
}
