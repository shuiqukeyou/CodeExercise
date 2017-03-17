package com.lastation.exercise.bookSrore.in.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.lastation.exercise.bookSrore.in.dao.dao.InDetailDAO;
import com.lastation.exercise.bookSrore.in.vo.InDetailQueryValueObject;
import com.lastation.exercise.bookSrore.in.vo.InDetailValueObject;
import com.lastation.exercise.bookSrore.tool.FileIOUitl;

public class InDetailDAOImpl implements InDetailDAO {
	
	private final String FILE_NAME = "inDetail.db";
	@Override
	public boolean create(InDetailValueObject idvo) {
		List<InDetailValueObject>list = FileIOUitl.FileInput(FILE_NAME);
		for (InDetailValueObject limvo:list) {
			if (limvo.equals(idvo)) {
				return false;
			}
		}
		list.add(idvo);
		FileIOUitl.FileOut(FILE_NAME, list);
		return true;
	}

	@Override
	public boolean delete(Integer uuid) {
		List<InDetailValueObject>list = FileIOUitl.FileInput(FILE_NAME);
		for (InDetailValueObject limvo:list) {
			if (limvo.getUuid() == uuid) {
				list.remove(limvo);
				FileIOUitl.FileOut(FILE_NAME, list);
				return true;
			}
		}
		return true;
	}

	@Override
	public boolean update(InDetailValueObject idvo) {
		List<InDetailValueObject>list = FileIOUitl.FileInput(FILE_NAME);
		for (InDetailValueObject limvo:list) {
			if (limvo.equals(idvo)) {
				limvo.update(idvo);
				FileIOUitl.FileOut(FILE_NAME, list);
				return true;
			}
		}
		return true;
	}
	public List<InDetailValueObject> findByInUser(int inUuid) {
		List<InDetailValueObject>list = FileIOUitl.FileInput(FILE_NAME);
		List<InDetailValueObject> result = new ArrayList<>();
		for (InDetailValueObject limvo:list) {
			if (limvo.getInUuid() == inUuid) {
				result.add(limvo);
			}
		}
		return result;
	}
	@Override
	public List<InDetailValueObject> findInDe(Integer uuid) {
		List<InDetailValueObject>list = FileIOUitl.FileInput(FILE_NAME);
		List<InDetailValueObject> result = new ArrayList<>();
		for (InDetailValueObject limvo:list) {
			if (limvo.getUuid() == uuid) {
				result.add(limvo);
			}
		}
		return result;
	}

	@Override
	public List<InDetailValueObject> findInDeAll() {
		return FileIOUitl.FileInput(FILE_NAME);
	}

	@Override
	public List<InDetailValueObject> findInDeByQuery(
			InDetailQueryValueObject idqvo) {
		List<InDetailValueObject>list = FileIOUitl.FileInput(FILE_NAME);
		List<InDetailValueObject> result = new ArrayList<>();
		for (InDetailValueObject lidvo:list) {
			if (idqvo.getBookUuid() >0 ) {
				if (lidvo.getBookUuid() != idqvo.getBookUuid()) {
					continue;
				}
			}
			if (idqvo.getInUuid() >0 ) {
				if (lidvo.getInUuid() != idqvo.getInUuid()) {
					continue;
				}
			}
			
			if (idqvo.getNumMin() >0 ) {
				if (lidvo.getNum() < idqvo.getNumMin()) {
					continue;
				}
			}
			
			if (idqvo.getNumMax() >0 ) {
				if (lidvo.getNum() > idqvo.getNumMax()) {
					continue;
				}
			}
			
			if (idqvo.getSumMoneyMin() >0 ) {
				if (lidvo.getSumMoney() < idqvo.getSumMoneyMin()) {
					continue;
				}
			}
			
			if (idqvo.getSumMoneyMax() >0 ) {
				if (lidvo.getSumMoney() > idqvo.getSumMoneyMax()) {
					continue;
				}
			}
			
			result.add(lidvo);
			
		}
		return result;
	}

}
