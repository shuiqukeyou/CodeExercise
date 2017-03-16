package com.lastation.exercise.bookSrore.book.business.ebi;

import java.util.List;

import com.lastation.exercise.bookSrore.book.vo.BookQueryValueObject;
import com.lastation.exercise.bookSrore.book.vo.InMainValueObject;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public interface BookEbi {
	/**
	 * 创建新用户
	 * @param user 将要被创建的用户对象
	 * @return 创建成功则返回true，否则返回false
	 */
	boolean create(InMainValueObject book);
	
	/**
	 * 删除用户
	 * @param user 将要被删除的用户的uuid
	 * @return 删除成功则返回true，否则返回false
	 */
	boolean delete(int uuid);
	
	/**
	 * 更新用户信息
	 * @param user 将要更新的用户对象
	 * @return 更新成功则返回true，否则返回false
	 */
	boolean update(InMainValueObject book);
	
	InMainValueObject findBook(int uuid);
	List<InMainValueObject> findAll();
	List<InMainValueObject> findByCondition(BookQueryValueObject bqvo);
}
