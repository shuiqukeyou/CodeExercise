package com.lastation.exercise.bookSrore.book.dao.dao;

import java.util.List;

import com.lastation.exercise.bookSrore.book.vo.BookQueryValueObject;
import com.lastation.exercise.bookSrore.book.vo.InMainValueObject;

public interface BookDAO {

	public boolean create(InMainValueObject book);
	public boolean delete(Integer uuid);
	public boolean update(InMainValueObject book);
	

	public InMainValueObject findBook(Integer uuid);
	public List<InMainValueObject> findBookAll();
	public List<InMainValueObject> findBookByQuery(BookQueryValueObject bqvo);
}
