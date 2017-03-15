package com.lastation.exercise.bookSrore.common.uuid.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.lastation.exercise.bookSrore.common.uuid.dao.dao.UuidDAO;
import com.lastation.exercise.bookSrore.common.uuid.uuidEnum.UuidEnum;
import com.lastation.exercise.bookSrore.common.uuid.vo.UuidValueObject;
import com.lastation.exercise.bookSrore.tool.FileIOUitl;

public class UuidDAOImpl implements UuidDAO{
	private final String FILE_NAME = "uuid.db";
	@Override
	public int getUuid(UuidEnum uuidEnum) {
		List<UuidValueObject> list = new ArrayList<>();
		
		String uuidType = uuidEnum.getVOName();
		list = FileIOUitl.FileInput(FILE_NAME);
		for (UuidValueObject uvo:list) {
			// 如果匹配上
			if (uvo.getVOName().equals(uuidEnum.getVOName())) {
				// 取值
				int uuid = uvo.getVOUuid();
				// 加一后回存
				uvo.setUuid(uuid+1); 
				FileIOUitl.FileOut(FILE_NAME, list);
				// 返回uuid
				return uuid;
			}
			
		}
		
		UuidValueObject uvo = new UuidValueObject();
		uvo.setUuid(2);
		uvo.setVOName(uuidEnum.getVOName());
		list.add(uvo);
		FileIOUitl.FileOut(FILE_NAME, list);
		return 1;
	}

}
