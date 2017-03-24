package com.lastation.exercise.bookSrore.login.business.ebo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.lastation.exercise.bookSrore.login.business.ebi.LoginEbi;
import com.lastation.exercise.bookSrore.user.business.ebi.UserEbi;
import com.lastation.exercise.bookSrore.user.business.factory.UserEbiFactory;
import com.lastation.exercise.bookSrore.user.vo.UserQueryValueObject;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public class LoginEbo implements LoginEbi {
	private final UserEbi ue = UserEbiFactory.getUserEbi();
	@Override
	public Map<String, Object> Login(String name, String pwd) {
		Map<String, Object> result = new HashMap<>();
		UserQueryValueObject uqvo = new UserQueryValueObject();
		uqvo.setUserName(name);
		List<UserValueObject> users = ue.findByCondition(uqvo);
		if (users.size() <= 0){
			result.put("error", "用户名或密码错误");
		}
		UserValueObject user = users.get(0);
		for (UserValueObject uvo : users){
			if (user.getPassWd().equals(pwd)) {//如果找到一个匹配的
				result.put("user", uvo);
				return result;
			}
		}
		result.put("error", "用户名或密码错误");
		
		return result;
	}

}
