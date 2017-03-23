package com.lastation.exercise.bookSrore.stock.business.ebo;

import java.util.List;

import com.lastation.exercise.bookSrore.common.uuid.dao.factory.UuidDAOFactory;
import com.lastation.exercise.bookSrore.common.uuid.uuidEnum.UuidEnum;
import com.lastation.exercise.bookSrore.stock.business.ebi.StockEbi;
import com.lastation.exercise.bookSrore.stock.dao.dao.StockDAO;
import com.lastation.exercise.bookSrore.stock.dao.factory.StockDAOFactory;
import com.lastation.exercise.bookSrore.stock.vo.StockQueryValueObject;
import com.lastation.exercise.bookSrore.stock.vo.StockValueObject;

public class StockEbo implements StockEbi {
	private StockDAO sd = StockDAOFactory.getStockDAO();
	
	@Override
	public boolean create(StockValueObject svo) {
		svo.setUuid(UuidDAOFactory.getUuidDAO().getUuid(UuidEnum.STOCK));
		Boolean boo = sd.create(svo);
		if (!boo) {
			boo = update(svo);
		}
		return boo;
	}

	@Override
	public boolean delete(StockValueObject svo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(StockValueObject svo) {
		StockValueObject dsvo = sd.findbyBookId(svo.getBookUuid());
		if (dsvo == null){
			return false;
		}
		dsvo.setSumMun(dsvo.getSumMun()+svo.getSumMun());
		return sd.update(dsvo);
	}

	@Override
	public StockValueObject findbyBookId(int bookId) {
		return sd.findbyBookId(bookId);
	}

	@Override
	public List<StockValueObject> findAll() {
		return sd.findAll();
	}

	@Override
	public List<StockValueObject> findByQueryVO(StockQueryValueObject sqov) {
		return sd.findByQueryVO(sqov);
	}

	@Override
	public boolean in(List<StockValueObject> list) {
		boolean boo = false;
		for (StockValueObject bvo :list) {
			boo = create(bvo);
			if (!boo) {
				return false;
			}
		}
		return boo;
	}

	@Override
	public boolean out(List<StockValueObject> list) {
		boolean boo = false;
		for (StockValueObject bvo :list) {
			boo = create(bvo);
			if (!boo) {
				return false;
			}
		}
		return boo;
	}



}
