package com.lastation.exercise.bookSrore.book.dao.dao;

import java.util.List;

import com.lastation.exercise.bookSrore.book.vo.BookQueryValueObject;
import com.lastation.exercise.bookSrore.book.vo.BookValueObject;

public interface BookDAO {

	public boolean create(BookValueObject book);
	public boolean delete(Integer uuid);
	public boolean update(BookValueObject book);
	

	public BookValueObject findBook(Integer uuid);
	public List<BookValueObject> findBookAll();
	public List<BookValueObject> findBookByQuery(BookQueryValueObject bqvo);
}
