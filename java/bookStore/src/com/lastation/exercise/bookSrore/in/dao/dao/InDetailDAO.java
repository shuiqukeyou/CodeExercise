package com.lastation.exercise.bookSrore.in.dao.dao;

import java.util.List;

import com.lastation.exercise.bookSrore.book.vo.InMainValueObject;
import com.lastation.exercise.bookSrore.in.vo.InDetailQueryValueObject;

public interface InDetailDAO {
	public boolean create(InMainValueObject idvo);
	public boolean delete(Integer uuid);
	public boolean update(InMainValueObject idvo);
	
	public InMainValueObject findBook(Integer uuid);
	public List<InMainValueObject> findBookAll();
	public List<InMainValueObject> findBookByQuery(InDetailQueryValueObject idqvo);
}
