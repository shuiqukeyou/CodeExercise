package com.lastation.exercise.bookSrore.stock.business.factory;

import com.lastation.exercise.bookSrore.stock.business.ebi.StockEbi;
import com.lastation.exercise.bookSrore.stock.business.ebo.StockEbo;

public class StockEBIFactory {
	private StockEBIFactory(){
	}
	
	public static StockEbi getEbi() {
		return new StockEbo();
	}
}
