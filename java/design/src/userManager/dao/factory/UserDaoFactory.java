package userManager.dao.factory;

import userManager.dao.api.UserDAO;
import userManager.dao.impl.UserDaoMysqlIpml;

public class UserDaoFactory {
	private UserDaoFactory(){
	}
	
	private static UserDAO getUserDAO() {
		return new UserDaoMysqlIpml();
	}
}
