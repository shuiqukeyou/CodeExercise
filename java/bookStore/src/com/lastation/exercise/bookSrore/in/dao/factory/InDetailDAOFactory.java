package com.lastation.exercise.bookSrore.in.dao.factory;

import com.lastation.exercise.bookSrore.in.dao.dao.InDetailDAO;
import com.lastation.exercise.bookSrore.in.dao.impl.InDetailDAOImpl;

public class InDetailDAOFactory {
	private  InDetailDAOFactory() {
	}
	
	public static InDetailDAO getDetailDAO() {
		return new InDetailDAOImpl();
	}
}
