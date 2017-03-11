package com.lastation.exercise.bookSrore.user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.lastation.exercise.bookSrore.tool.FileIOUitl;
import com.lastation.exercise.bookSrore.user.dao.dao.UserDAO;
import com.lastation.exercise.bookSrore.user.vo.UserQueryValueObject;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public class UserDAOImpl implements UserDAO{
	private final String FILE_NAME = "user.txt";

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
	public boolean delete(String uuid) {
		List<UserValueObject> userList = new ArrayList<UserValueObject>();
		userList = FileIOUitl.FileInput(FILE_NAME);
		
		// 检查当前数据库文件是否已存在当前用户
		for (UserValueObject u:userList){
			if (u.getUuid().equals(uuid)) {
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
		for (int i = 0;i < userList.size();i++){
			if (userList.get(i).getUuid().equals(user.getUuid())) {
				userList.remove(i);
				userList.add(i, user);
				return true;
			}
		}		
		return false;
	}

	@Override
	public UserValueObject findUser(String uuid) {
		List<UserValueObject> userList = new ArrayList<UserValueObject>();
		userList = FileIOUitl.FileInput(FILE_NAME);
		
		// 检查当前数据库文件是否已存在当前用户,若存在直接返回
		for (UserValueObject u:userList){
			if (u.getUuid().equals(uuid)) {
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
		// TODO Auto-generated method stub
		return null;
	}

}
