package userManager.business.factory;

import userManager.business.impl.UserbuImpl;

public class UserBuFactory {
	private UserBuFactory(){
	}
	
	public static UserbuImpl getbuImpl() {
		return new UserbuImpl();
	}
}
