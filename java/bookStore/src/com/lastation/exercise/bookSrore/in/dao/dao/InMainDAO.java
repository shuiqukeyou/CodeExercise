package com.lastation.exercise.bookSrore.in.dao.dao;

import java.util.List;

import com.lastation.exercise.bookSrore.in.vo.InMainValueObject;
import com.lastation.exercise.bookSrore.in.vo.InMainQueryValueObject;

public interface InMainDAO {
	public boolean create(InMainValueObject imvo);
	public boolean delete(Integer uuid);
	public boolean update(InMainValueObject imvo);
	
	public InMainValueObject findInMain(Integer uuid);
	public List<InMainValueObject> findInMainAll();
	public List<InMainValueObject> findInMainByQuery(InMainQueryValueObject imqvo);
}
