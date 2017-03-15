package com.lastation.exercise.bookSrore.user.business.ebo;

import java.util.List;

import com.lastation.exercise.bookSrore.common.uuid.dao.dao.UuidDAO;
import com.lastation.exercise.bookSrore.common.uuid.dao.factory.UuidDAOFactory;
import com.lastation.exercise.bookSrore.common.uuid.uuidEnum.UuidEnum;
import com.lastation.exercise.bookSrore.user.business.ebi.UserEbi;
import com.lastation.exercise.bookSrore.user.dao.dao.UserDAO;
import com.lastation.exercise.bookSrore.user.dao.factory.UserDAOFactory;
import com.lastation.exercise.bookSrore.user.vo.UserQueryValueObject;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public class UserEbo implements UserEbi{
	private UuidDAO uDAO = UuidDAOFactory.getUuidDAO();

	@Override
	public boolean create(UserValueObject user) {
		UserDAO udao = UserDAOFactory.getUserDAO();
		user.setUuid(uDAO.getUuid(UuidEnum.USER));
		return udao.create(user);
	}

	@Override
	public boolean delete(int uuid) {
		UserDAO udao = UserDAOFactory.getUserDAO();
		return udao.delete(uuid);
	}

	@Override
	public boolean update(UserValueObject user) {
		UserDAO udao = UserDAOFactory.getUserDAO();
		return udao.update(user);
	}

	@Override
	public UserValueObject findUser(int uuid) {
		UserDAO udao = UserDAOFactory.getUserDAO();
		return udao.findUser(uuid);
	}

	@Override
	public List<UserValueObject> findAll() {
		UserDAO udao = UserDAOFactory.getUserDAO();
		return udao.findAll();
	}

	@Override
	public List<UserValueObject> findByCondition(UserQueryValueObject uqvo) {
		UserDAO udao = UserDAOFactory.getUserDAO();
		return udao.findByCondition(uqvo);
	}

}
