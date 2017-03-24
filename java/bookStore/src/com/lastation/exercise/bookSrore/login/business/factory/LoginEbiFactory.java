package com.lastation.exercise.bookSrore.login.business.factory;

import com.lastation.exercise.bookSrore.login.business.ebi.LoginEbi;
import com.lastation.exercise.bookSrore.login.business.ebo.LoginEbo;

public class LoginEbiFactory {
	private LoginEbiFactory(){
	}
	
	public static LoginEbi getLoginEbi() {
		return new LoginEbo();
	}
}
