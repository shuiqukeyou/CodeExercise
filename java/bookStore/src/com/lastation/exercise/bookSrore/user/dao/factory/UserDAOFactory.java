package com.lastation.exercise.bookSrore.user.dao.factory;

import com.lastation.exercise.bookSrore.user.dao.impl.UserDAOImpl;

public class UserDAOFactory {
	private UserDAOFactory(){
	}
	
	public static UserDAOImpl vgetUserDAO() {
		return new UserDAOImpl();
	}
}
