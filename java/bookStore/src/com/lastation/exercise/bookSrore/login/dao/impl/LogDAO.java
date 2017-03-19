package com.lastation.exercise.bookSrore.login.dao.impl;

import java.util.List;

import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public interface LogDAO {
	List<UserValueObject> getUsersByName();
}
