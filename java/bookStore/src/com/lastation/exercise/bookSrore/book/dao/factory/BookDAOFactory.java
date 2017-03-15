package com.lastation.exercise.bookSrore.book.dao.factory;

import com.lastation.exercise.bookSrore.book.dao.dao.BookDAO;
import com.lastation.exercise.bookSrore.book.dao.impl.BookDAOImpl;

public class BookDAOFactory {
    private BookDAOFactory(){
    }
    
    public static BookDAO getBookDAO() {
		return new BookDAOImpl();
	}
}
