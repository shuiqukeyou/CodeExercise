package userManager.dao.impl;

import java.util.Collection;

import userManager.dao.api.UserDAO;
import userManager.vo.UserQueryVO;
import userManager.vo.UserVO;

public class UserDaoMysqlIpml implements UserDAO{
	public UserDaoMysqlIpml(){
	}

	@Override
	public boolean create(UserVO user) {
		System.out.println("正在写入数据：" + user);
		System.out.println("写入成功");
		return true;
	}

	@Override
	public boolean delete(UserVO user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(UserVO user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserVO getUser(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<UserVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<UserVO> getByCondition(UserQueryVO uqvo) {
		// TODO Auto-generated method stub
		return null;
	}
}
