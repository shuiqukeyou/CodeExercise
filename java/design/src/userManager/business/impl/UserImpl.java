package userManager.business.impl;

import java.util.UUID;

import userManager.business.api.UserAPI;
import userManager.dao.api.UserDAO;
import userManager.dao.factory.UserDaoFactory;
import userManager.vo.UserVO;

public class UserImpl implements UserAPI {
	//获取Dao对象
	UserDAO dao = UserDaoFactory.getUserDAO();
	
	public UserImpl(){
	}
	
	@Override
	public boolean create(UserVO user) {
		//随机生成UUID
		user.setUuid(UUID.randomUUID().toString());
		//向数据层传入user对象
		return dao.create(user);
	}

}
