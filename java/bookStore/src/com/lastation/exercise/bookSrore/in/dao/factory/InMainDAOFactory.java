package com.lastation.exercise.bookSrore.in.dao.factory;

import com.lastation.exercise.bookSrore.in.dao.dao.InMainDAO;
import com.lastation.exercise.bookSrore.in.dao.impl.InMainDAOImpl;

public class InMainDAOFactory {
	private InMainDAOFactory() {
	}
	
	public static InMainDAO getInMainDAO() {
		return new InMainDAOImpl();
	}
}
