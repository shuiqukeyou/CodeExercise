package com.lastation.exercise.bookSrore.stock.business.ebi;

import java.util.List;

import com.lastation.exercise.bookSrore.stock.vo.StockQueryValueObject;
import com.lastation.exercise.bookSrore.stock.vo.StockValueObject;

public interface StockEbi {
	boolean create (StockValueObject svo);
	boolean delete (StockValueObject svo);
	boolean update (StockValueObject svo);
	boolean in (List<StockValueObject> list);
	boolean out (List<StockValueObject> list);
	StockValueObject findbyBookId(int bookId);
	List<StockValueObject> findAll();
	List<StockValueObject> findByQueryVO(StockQueryValueObject sqov);
	
}
