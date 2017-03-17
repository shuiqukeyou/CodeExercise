package com.lastation.exercise.bookSrore.in.dao.dao;

import java.util.List;

import com.lastation.exercise.bookSrore.in.vo.InDetailQueryValueObject;
import com.lastation.exercise.bookSrore.in.vo.InDetailValueObject;

public interface InDetailDAO {
	boolean create(InDetailValueObject idvo);
	boolean delete(Integer uuid);
	boolean update(InDetailValueObject idvo);
	List<InDetailValueObject> findByInUser(int InUuid);
	List<InDetailValueObject> findInDe(Integer uuid);
	List<InDetailValueObject> findInDeAll();
	List<InDetailValueObject> findInDeByQuery(InDetailQueryValueObject idqvo);
}
