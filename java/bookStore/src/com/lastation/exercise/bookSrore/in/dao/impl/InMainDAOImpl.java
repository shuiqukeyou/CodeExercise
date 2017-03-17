package com.lastation.exercise.bookSrore.in.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.lastation.exercise.bookSrore.in.vo.InMainValueObject;
import com.lastation.exercise.bookSrore.in.dao.dao.InMainDAO;
import com.lastation.exercise.bookSrore.in.vo.InMainQueryValueObject;
import com.lastation.exercise.bookSrore.tool.FileIOUitl;

public class InMainDAOImpl implements InMainDAO {
	private final String FILE_NAME = "inMain.db";
	@Override
	public boolean create(InMainValueObject imvo) {
		List<InMainValueObject>list = FileIOUitl.FileInput(FILE_NAME);
		for (InMainValueObject limvo:list) {
			if (limvo.equals(imvo)) {
				return false;
			}
		}
		list.add(imvo);
		FileIOUitl.FileOut(FILE_NAME, list);
		return true;
	}

	@Override
	public boolean delete(Integer uuid) {
		List<InMainValueObject>list = FileIOUitl.FileInput(FILE_NAME);
		for (InMainValueObject limvo:list) {
			if (limvo.getUuid() == uuid) {
				list.remove(limvo);
				FileIOUitl.FileOut(FILE_NAME, list);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(InMainValueObject imvo) {
		List<InMainValueObject>list = FileIOUitl.FileInput(FILE_NAME);
		for (InMainValueObject limvo:list) {
			if (limvo.equals(imvo)) {
				limvo.update(imvo);
				FileIOUitl.FileOut(FILE_NAME, list);
				return true;
			}
		}
		return false;
	}

	@Override
	public InMainValueObject findInMain(Integer uuid) {
		List<InMainValueObject>list = FileIOUitl.FileInput(FILE_NAME);
		for (InMainValueObject limvo:list) {
			if (limvo.getUuid() == uuid) {
				return limvo;
			}
		}
		return null;
	}

	@Override
	public List<InMainValueObject> findInMainAll() {
		return FileIOUitl.FileInput(FILE_NAME);
	}

	@Override
	public List<InMainValueObject> findInMainByQuery(InMainQueryValueObject imqvo) {
		List<InMainValueObject>list = FileIOUitl.FileInput(FILE_NAME);
		List<InMainValueObject> result = new ArrayList<>();
		for (InMainValueObject limvo:list) {
			if (imqvo.getUuid() > 0) {
				if (limvo.getUuid() != imqvo.getUuid()) {
					System.out.println("ID不符");
					continue;
				}
			}
			
			if (imqvo.getInDateMin() > 0) {
				if (limvo.getInDate() < imqvo.getInDateMin()) {
					System.out.println("时间太小");
					continue;
				}
			}
			
			if (imqvo.getInDateMax() > 0) {
				if (limvo.getInDate() > imqvo.getInDateMax()) {
					System.out.println("时间太大");
					continue;
				}
			}
			
			if (imqvo.getInUserUuid() > 0) {
				if (limvo.getInUserUuid() != imqvo.getInUserUuid()) {
					System.out.println("用户ID不附");
					continue;
				}
			}
			result.add(limvo);
		}
		return result;
	}

}
