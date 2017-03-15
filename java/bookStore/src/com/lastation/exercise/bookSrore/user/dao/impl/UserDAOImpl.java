package com.lastation.exercise.bookSrore.user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.lastation.exercise.bookSrore.tool.FileIOUitl;
import com.lastation.exercise.bookSrore.user.dao.dao.UserDAO;
import com.lastation.exercise.bookSrore.user.vo.UserQueryValueObject;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public class UserDAOImpl implements UserDAO{
	private final String FILE_NAME = "user.db";

	@Override
	public boolean create(UserValueObject user) {
		List<UserValueObject> userList = new ArrayList<UserValueObject>();
		userList = FileIOUitl.FileInput(FILE_NAME);
		
		// 检查当前数据库文件是否已存在当前用户
		for (UserValueObject u:userList){
			if (u.equals(user)) {
				return false;
			}
		}
		
		userList.add(user);
	
		// 若不存在就直接写入
		FileIOUitl.FileOut(FILE_NAME, userList);
		return true;
	}

	@Override
	public boolean delete(int uuid) {
		List<UserValueObject> userList = new ArrayList<UserValueObject>();
		userList = FileIOUitl.FileInput(FILE_NAME);
		
		// 检查当前数据库文件是否已存在当前用户
		for (UserValueObject u:userList){
			if (u.getUuid() == uuid) {
				userList.remove(u);
				// 写入删除后的记录
				FileIOUitl.FileOut(FILE_NAME, userList);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(UserValueObject user) {
		List<UserValueObject> userList = new ArrayList<UserValueObject>();
		userList = FileIOUitl.FileInput(FILE_NAME);
		
		// 检查当前数据库文件是否已存在当前用户,若存在就删除后再添加
		for (UserValueObject u:userList){
			if (u.equals(user)) {
				// 修改对象
				u.update(user);
				// 写入删除后的记录
				FileIOUitl.FileOut(FILE_NAME, userList);
				return true;
			}
		}		
		return false;
	}

	@Override
	public UserValueObject findUser(int uuid) {
		List<UserValueObject> userList = new ArrayList<UserValueObject>();
		userList = FileIOUitl.FileInput(FILE_NAME);
		
		// 检查当前数据库文件是否已存在当前用户,若存在直接返回
		for (UserValueObject u:userList){
			if (u.getUuid() == uuid) {
				return u;
			}
		}
		// 若不存在返回null
		return null;
	}

	@Override
	public List<UserValueObject> findAll() {
		return FileIOUitl.FileInput(FILE_NAME);
	}

	@Override
	public List<UserValueObject> findByCondition(UserQueryValueObject uqvo) {
		List<UserValueObject> userList = new ArrayList<UserValueObject>();
		userList = FileIOUitl.FileInput(FILE_NAME);
		
		List<UserValueObject> userlist2 = new ArrayList<>();
		for (UserValueObject uvo:userList) {
			
			if (uqvo == null) {
				return userList;
			}
			// 若UUID条件存在
			if (uqvo.getUuid() != 0){
				// 若UUID不符合，下一个
				if (uvo.getUuid() != uqvo.getUuid()) {
					continue;
				}
			}
			
			// 若用户名条件存在
			if (uqvo.getUserName() != null) {
				// 若用户名不符合，下一个
				if (!uvo.getUserName().contains(uqvo.getUserName())) {
					continue;
				}
			}
			
			// 若用户类型存在
			if (uqvo.getType() != null && uqvo.getType().getType() > 0) {
				// 若用户类型不符合，下一个
				if (uvo.getType().getType() != uqvo.getType().getType()) {
					continue;
				}
			}
			// 全部复合则加入集合
			userlist2.add(uvo);

		}
		return userlist2;
	}

}
