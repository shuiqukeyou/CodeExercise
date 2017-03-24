package com.lastation.exercise.bookSrore.in.business.ebo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lastation.exercise.bookSrore.common.uuid.dao.dao.UuidDAO;
import com.lastation.exercise.bookSrore.common.uuid.dao.factory.UuidDAOFactory;
import com.lastation.exercise.bookSrore.common.uuid.uuidEnum.UuidEnum;
import com.lastation.exercise.bookSrore.in.business.ebi.InEbi;
import com.lastation.exercise.bookSrore.in.dao.dao.InDetailDAO;
import com.lastation.exercise.bookSrore.in.dao.dao.InMainDAO;
import com.lastation.exercise.bookSrore.in.dao.factory.InDetailDAOFactory;
import com.lastation.exercise.bookSrore.in.dao.factory.InMainDAOFactory;
import com.lastation.exercise.bookSrore.in.vo.InDetailQueryValueObject;
import com.lastation.exercise.bookSrore.in.vo.InDetailValueObject;
import com.lastation.exercise.bookSrore.in.vo.InMainQueryValueObject;
import com.lastation.exercise.bookSrore.in.vo.InMainValueObject;
import com.lastation.exercise.bookSrore.stock.business.ebi.StockEbi;
import com.lastation.exercise.bookSrore.stock.business.factory.StockEbiFactory;
import com.lastation.exercise.bookSrore.stock.vo.StockValueObject;

public class InEbo implements InEbi {
	private final InMainDAO imd = InMainDAOFactory.getInMainDAO();
	private final InDetailDAO idd = InDetailDAOFactory.getDetailDAO();
	private final UuidDAO uuid  = UuidDAOFactory.getUuidDAO();
	private final StockEbi se = StockEbiFactory.getStockEbi();
	
	@Override
	public boolean create(InMainValueObject imvo, List<InDetailValueObject> list) {
		Integer mUuid = uuid.getUuid(UuidEnum.IN_MAIN);
		List<StockValueObject> svoList = new ArrayList<>();
		imvo.setUuid(mUuid); // 设置ID
		Boolean boom = imd.create(imvo); //存入主键
		Boolean bood = false;
		for (InDetailValueObject lidvo:list) {
			lidvo.setInUuid(mUuid);// 设置进货单ID
			lidvo.setUuid(uuid.getUuid(UuidEnum.IN_DETAIL)); // 设置ID
			bood = idd.create(lidvo); //逐个存入子键
			StockValueObject svo = new StockValueObject();
			svo.setBookUuid(lidvo.getBookUuid());
			svo.setSumMun(lidvo.getNum());
			svoList.add(svo);
			
		}
		return boom && bood && se.in(svoList);
	}
	
	@Override
	public InDetailValueObject findIn(Integer uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InMainValueObject> findInAll() {
		return imd.findInMainAll();
	}

	@Override
	public List<InDetailValueObject> findInEByQuery(
			InDetailQueryValueObject idqvo) {
		return idd.findInDeByQuery(idqvo);
	}

	@Override
	public List<InMainValueObject> findInMByQuery(InMainQueryValueObject imqvo) {
		return imd.findInMainByQuery(imqvo);
	}

	@Override
	public List<InDetailValueObject> findDInByInUuid(int InUuid) {
		return idd.findByInUser(InUuid);
	}

	@Override
	public InMainValueObject findMInByInUuid(int InUuid) {
		return imd.findInMain(InUuid);
	}

	@Override
	public Map<InMainValueObject, List<InDetailValueObject>> findAll() {
		Map<InMainValueObject, List<InDetailValueObject>> result = new LinkedHashMap<>();
		List<InMainValueObject> mlist  = imd.findInMainAll();
		
		for(InMainValueObject imvo: mlist) {
			List<InDetailValueObject> dlist = findDInByInUuid(imvo.getUuid());
			result.put(imvo, dlist);
		}
		return result;
	}

	

}
