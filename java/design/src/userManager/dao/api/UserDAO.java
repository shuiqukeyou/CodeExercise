package userManager.dao.api;

import java.util.Collection;

import userManager.vo.UserQueryVO;
import userManager.vo.UserVO;

public interface UserDAO {
	boolean create(UserVO user);
	boolean delete(UserVO user);
	boolean update(UserVO user);
	
	UserVO	getUser(String uuid);
	Collection<UserVO> getAll();
	Collection<UserVO> getByCondition(UserQueryVO uqvo);
}
