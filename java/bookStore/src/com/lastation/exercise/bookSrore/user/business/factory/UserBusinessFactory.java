package com.lastation.exercise.bookSrore.user.business.factory;

import com.lastation.exercise.bookSrore.user.business.ebo.UserEbo;

public class UserBusinessFactory {
	private UserBusinessFactory(){
	}
	
	public static UserEbo  getUserBusinessImpl (){
		return new UserEbo();
	}
}
