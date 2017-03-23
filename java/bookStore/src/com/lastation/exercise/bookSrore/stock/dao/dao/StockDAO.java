package com.lastation.exercise.bookSrore.stock.dao.dao;

import java.util.List;

import com.lastation.exercise.bookSrore.stock.vo.StockQueryValueObject;
import com.lastation.exercise.bookSrore.stock.vo.StockValueObject;

public interface StockDAO {
	boolean create (StockValueObject svo);
	boolean delete (StockValueObject svo);
	boolean update (StockValueObject svo);
	
	StockValueObject findbyBookId(int bookId);
	List<StockValueObject> findAll();
	List<StockValueObject> findByQueryVO(StockQueryValueObject sqov);
}
