package com.lastation.exercise.bookSrore.stock.dao.factory;

import com.lastation.exercise.bookSrore.stock.dao.dao.StockDAO;
import com.lastation.exercise.bookSrore.stock.dao.impl.StockDAOImpl;

public class StockDAOFactory {
	private StockDAOFactory(){
	}
	
	public static StockDAO getStockDAO() {
		return new StockDAOImpl();
	}
}
