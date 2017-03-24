package com.lastation.exercise.bookSrore.user.business.factory;

import com.lastation.exercise.bookSrore.user.business.ebo.UserEbo;

public class UserEbiFactory {
	private UserEbiFactory(){
	}
	
	public static UserEbo  getUserEbi (){
		return new UserEbo();
	}
}
