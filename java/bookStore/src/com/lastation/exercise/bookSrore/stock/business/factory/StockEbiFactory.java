package com.lastation.exercise.bookSrore.stock.business.factory;

import com.lastation.exercise.bookSrore.stock.business.ebi.StockEbi;
import com.lastation.exercise.bookSrore.stock.business.ebo.StockEbo;

public class StockEbiFactory {
	private StockEbiFactory(){
	}
	
	public static StockEbi getStockEbi() {
		return new StockEbo();
	}
}
