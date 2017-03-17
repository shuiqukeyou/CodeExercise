package com.lastation.exercise.bookSrore.book.business.ebo;

import java.util.List;

import com.lastation.exercise.bookSrore.book.business.ebi.BookEbi;
import com.lastation.exercise.bookSrore.book.dao.dao.BookDAO;
import com.lastation.exercise.bookSrore.book.dao.factory.BookDAOFactory;
import com.lastation.exercise.bookSrore.book.vo.BookQueryValueObject;
import com.lastation.exercise.bookSrore.book.vo.BookValueObject;
import com.lastation.exercise.bookSrore.common.uuid.dao.factory.UuidDAOFactory;
import com.lastation.exercise.bookSrore.common.uuid.uuidEnum.UuidEnum;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public class BookEbo implements BookEbi {
	private BookDAO bdao = BookDAOFactory.getBookDAO();
	
	@Override
	public boolean create(BookValueObject book) {
		book.setUuid(UuidDAOFactory.getUuidDAO().getUuid(UuidEnum.USER));
		return bdao.create(book);
	}

	@Override
	public boolean delete(int uuid) {
		return bdao.delete(uuid);
	}

	@Override
	public boolean update(BookValueObject book) {
		return bdao.update(book);
	}

	@Override
	public BookValueObject findBook(int uuid) {
		return bdao.findBook(uuid);
	}

	@Override
	public List<BookValueObject> findAll() {
		return bdao.findBookAll();
	}

	@Override
	public List<BookValueObject> findByCondition(BookQueryValueObject bqvo) {
		return bdao.findBookByQuery(bqvo);
	}

}
