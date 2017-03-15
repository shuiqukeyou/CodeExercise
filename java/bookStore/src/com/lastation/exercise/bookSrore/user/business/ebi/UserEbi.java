package com.lastation.exercise.bookSrore.user.business.ebi;

import java.util.List;

import com.lastation.exercise.bookSrore.user.vo.UserQueryValueObject;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public interface UserEbi {
	/**
	 * 创建新用户
	 * @param user 将要被创建的用户对象
	 * @return 创建成功则返回true，否则返回false
	 */
	boolean create(UserValueObject user);
	
	/**
	 * 删除用户
	 * @param user 将要被删除的用户的uuid
	 * @return 删除成功则返回true，否则返回false
	 */
	boolean delete(int uuid);
	
	/**
	 * 更新用户信息
	 * @param user 将要更新的用户对象
	 * @return 更新成功则返回true，否则返回false
	 */
	boolean update(UserValueObject user);
	
	UserValueObject findUser(int uuid);
	List<UserValueObject> findAll();
	List<UserValueObject> findByCondition(UserQueryValueObject uqvo);
}
