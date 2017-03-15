package com.lastation.exercise.BookStoreTest;

import java.util.List;

import org.junit.Test;

import com.lastation.exercise.bookSrore.common.UserEnum;
import com.lastation.exercise.bookSrore.user.business.ebo.UserEbo;
import com.lastation.exercise.bookSrore.user.business.factory.UserBusinessFactory;
import com.lastation.exercise.bookSrore.user.dao.dao.UserDAO;
import com.lastation.exercise.bookSrore.user.dao.factory.UserDAOFactory;
import com.lastation.exercise.bookSrore.user.vo.UserQueryValueObject;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public class UserTest {
	UserEbo uEbo = UserBusinessFactory.getUserBusinessImpl();
	// 普通添加测试
	@Test
	public void testDaoCreate(){
		UserValueObject uvo = new UserValueObject("shinono","1234",UserEnum.ADMIN);
		System.out.println(uEbo.create(uvo));
	}
	
	// 中文添加测试
	@Test
	public void testDaoCreate2(){
		UserValueObject uvo = new UserValueObject("胖头鱼","1234",UserEnum.BOOK);
		System.out.println(uEbo.create(uvo));
	}
		
	// 同ID添加测试
	@Test
	public void testDaoCreate3(){
		UserValueObject uvo = new UserValueObject("胖头鱼","1234",UserEnum.BOOK);
		System.out.println(uEbo.create(uvo));
	}
	
	// 全查找测试
	@Test
	public void testDaoFindAll() {
		
		List<UserValueObject> list = uEbo.findAll();
		for(UserValueObject uvo:list){
			System.out.println(uvo);
		}
		
	}
	
	// UUID查找测试
	@Test
	public void testDaoFindUser() {
		int uuid = 1;
		
		System.out.println(uEbo.findUser(uuid));
		
	}
	
	// 条件查找测试
	@Test
	public void testDaoFindByCondition() {
		UserQueryValueObject uqvo = new UserQueryValueObject();
		uqvo.setType(UserEnum.BOOK);
		
		List<UserValueObject> list = uEbo.findByCondition(uqvo);
		for(UserValueObject uvo:list){
			System.out.println(uvo);
		}
	}
	
	
	// 数据更新测试
	@Test
	public void testDaoUpdate() {
		UserValueObject uvo = new UserValueObject(1, "胖头妹","1234",UserEnum.BOOK);
		
		System.out.println(uEbo.update(uvo));
	}
	
	// 删除测试
	@Test
	public void testDaoUDelete() {
		UserValueObject uvo = new UserValueObject("胖头妹","1234",UserEnum.BOOK);
		
		System.out.println(uEbo.delete(uvo.getUuid()));
	}
	
}
