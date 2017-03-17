package com.lastation.exercise.bookSrore.in.business.ebi;

import java.util.List;

import com.lastation.exercise.bookSrore.in.vo.InDetailQueryValueObject;
import com.lastation.exercise.bookSrore.in.vo.InDetailValueObject;
import com.lastation.exercise.bookSrore.in.vo.InMainQueryValueObject;
import com.lastation.exercise.bookSrore.in.vo.InMainValueObject;

public interface InEbi {
	public boolean create(InMainValueObject imvo, List<InDetailValueObject> list);
	
	public InDetailValueObject findIn(Integer uuid);
	public List<InMainValueObject> findInAll();
	public List<InDetailValueObject> findInEByQuery(InDetailQueryValueObject idqvo);
	public List<InMainValueObject> findInMByQuery(InMainQueryValueObject idqvo);
	public List<InDetailValueObject> findDInByInUuid(int InUuid);
	public InMainValueObject findMInByInUuid(int InUuid);
}
