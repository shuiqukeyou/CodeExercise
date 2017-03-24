package com.lastation.exercise.bookSrore.stock.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.lastation.exercise.bookSrore.stock.dao.dao.StockDAO;
import com.lastation.exercise.bookSrore.stock.vo.StockQueryValueObject;
import com.lastation.exercise.bookSrore.stock.vo.StockValueObject;
import com.lastation.exercise.bookSrore.tool.FileIOUitl;

public class StockDAOImpl implements StockDAO {
	private final String FILE_NAME = "Stock.db";
	@Override
	public boolean create(StockValueObject svo) {
		List<StockValueObject> list = FileIOUitl.FileInput(FILE_NAME);
		for (StockValueObject lsvo :list){
			if (lsvo.getBookUuid() == svo.getBookUuid()){
				return false;
			}
		}
		list.add(svo);
		FileIOUitl.FileOut(FILE_NAME, list);
		return true;
	}

	@Override
	public boolean delete(StockValueObject svo) {
		List<StockValueObject> list = FileIOUitl.FileInput(FILE_NAME);
		for (StockValueObject lsvo :list){
			if (lsvo.getBookUuid() == svo.getBookUuid()){
				list.remove(svo);
				FileIOUitl.FileOut(FILE_NAME, list);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(StockValueObject svo) {
		List<StockValueObject> list = FileIOUitl.FileInput(FILE_NAME);
		for (StockValueObject lsvo :list){
			if (lsvo.getBookUuid() == svo.getBookUuid()){
				lsvo.update(svo);
				FileIOUitl.FileOut(FILE_NAME, list);
				return true;
			}
		}
		return false;
	}

	@Override
	public StockValueObject findbyBookId(int bookId) {
		List<StockValueObject> list = FileIOUitl.FileInput(FILE_NAME);
		for (StockValueObject lsvo :list){
			if (lsvo.getBookUuid() == bookId){
				return lsvo;
			}
		}
		return null;
	}

	@Override
	public List<StockValueObject> findAll() {
		return  FileIOUitl.FileInput(FILE_NAME);
	}

	@Override
	public List<StockValueObject> findByQueryVO(StockQueryValueObject sqov) {
		List<StockValueObject> result = new ArrayList<>();
		List<StockValueObject> list = FileIOUitl.FileInput(FILE_NAME);
		for (StockValueObject lsvo :list){
			if (sqov.getUuid() >0) {
				if (lsvo.getUuid()!=sqov.getUuid()) {
					continue;
				}
			}
			if (sqov.getBookUuid() > 0) {
				if (lsvo.getBookUuid() != sqov.getBookUuid()){
					continue;
				}
			}
			if (sqov.getSumMun1() > 0) {
				if (lsvo.getSumMun() < sqov.getSumMun1()){
					continue;
				}
			}
			if (sqov.getSumMun2() > 0) {
				if (lsvo.getSumMun() > sqov.getSumMun2()){
					continue;
				}
			}
			result.add(lsvo);
		}
		return result;
	}

}
