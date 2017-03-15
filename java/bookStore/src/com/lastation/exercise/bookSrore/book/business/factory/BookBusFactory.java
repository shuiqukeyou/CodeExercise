package com.lastation.exercise.bookSrore.book.business.factory;

import com.lastation.exercise.bookSrore.book.business.ebi.BookEbi;
import com.lastation.exercise.bookSrore.book.business.ebo.BookEbo;

public class BookBusFactory {
	private BookBusFactory(){
	}
	
	public static BookEbi getBookEbi() {
		return new BookEbo();
	}
}
