package com.lastation.exercise.bookSrore.book.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.lastation.exercise.bookSrore.book.dao.dao.BookDAO;
import com.lastation.exercise.bookSrore.book.vo.BookQueryValueObject;
import com.lastation.exercise.bookSrore.book.vo.BookValueObject;
import com.lastation.exercise.bookSrore.tool.FileIOUitl;

public class BookDAOImpl implements BookDAO{
	private final String FILE_NAME = "Book.db";
	
	@Override
	public boolean create(BookValueObject book) {
		List<BookValueObject> list = FileIOUitl.FileInput(FILE_NAME);
		for(BookValueObject bvo:list) {
			if (bvo.equals(book)) {
				return false;
			}
		}
		list.add(book);
		FileIOUitl.FileOut(FILE_NAME, list);
		return true;
	}

	@Override
	public boolean delete(Integer uuid) {
		List<BookValueObject> list = FileIOUitl.FileInput(FILE_NAME);
		for(BookValueObject bvo:list) {
			if (bvo.getUuid() == uuid) {
				list.remove(bvo);
				FileIOUitl.FileOut(FILE_NAME, list);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(BookValueObject book) {
		List<BookValueObject> list = FileIOUitl.FileInput(FILE_NAME);
		for(BookValueObject bvo:list) {
			if (bvo.equals(book)) {
				bvo.update(book);
				FileIOUitl.FileOut(FILE_NAME, list);
;				return true;
			}
		}
		return false;
	}

	@Override
	public BookValueObject findBook(Integer uuid) {
		List<BookValueObject> list = FileIOUitl.FileInput(FILE_NAME);
		for(BookValueObject bvo:list) {
			if (bvo.getUuid() == uuid) {
				return bvo;
			}
		}
		return null;
	}

	@Override
	public List<BookValueObject> findBookAll() {
		return FileIOUitl.FileInput(FILE_NAME);
	}

	@Override
	public List<BookValueObject> findBookByQuery(BookQueryValueObject bqvo) {
		List<BookValueObject> list = FileIOUitl.FileInput(FILE_NAME);
		List<BookValueObject> result = new ArrayList<>();
		if (bqvo == null) {
			return result;
		}
		int uuid = bqvo.getUuid();
		String BookNo = bqvo.getBookNo();
		String bookname = bqvo.getBookName();
		double inPriceMin = bqvo.getInPriceMin();
		double inPriceMax = bqvo.getInPriceMax();
		double salePriceMin = bqvo.getSalePriceMin();
		double salePriceMax = bqvo.getSalePriceMax();
		
		for(BookValueObject bvo:list) {
			
			if (uuid !=0 ) {
				if (bvo.getUuid() != uuid) {
					continue;
				}
			}
			if (BookNo != null) {
				if (!bvo.getBookNo().contains(BookNo)) {
					continue;
				}
			}
			if (bookname != null) {
				if (!bvo.getBookName().contains(bookname)) {
					continue;
				}
			}
			if (inPriceMin != 0) {
				if (bvo.getInPrice() < inPriceMin) {
					continue;
				}
			}
			if (inPriceMax != 0) {
				if (bvo.getInPrice() > inPriceMax) {
					continue;
				}
			}
			
			if (salePriceMin != 0) {
				if (bvo.getSalePrice() < salePriceMin) {
					continue;
				}
			}
			
			if (salePriceMax != 0) {
				if (bvo.getSalePrice() > salePriceMax) {
					continue;
				}
			}
			
			result.add(bvo);
		}
		return result;
	}

}
