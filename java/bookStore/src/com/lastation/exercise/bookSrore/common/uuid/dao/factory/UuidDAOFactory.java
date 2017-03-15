package com.lastation.exercise.bookSrore.common.uuid.dao.factory;

import com.lastation.exercise.bookSrore.common.uuid.dao.dao.UuidDAO;
import com.lastation.exercise.bookSrore.common.uuid.dao.impl.UuidDAOImpl;

public class UuidDAOFactory {
	private UuidDAOFactory(){
	}
	
	public static UuidDAO getUuidDAO() {
		return new UuidDAOImpl();
	}
}
