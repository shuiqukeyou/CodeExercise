package com.lastation.exercise.BookStoreTest;

import java.util.List;

import org.junit.Test;

import com.lastation.exercise.bookSrore.user.dao.dao.UserDAO;
import com.lastation.exercise.bookSrore.user.dao.factory.UserDAOFactory;
import com.lastation.exercise.bookSrore.user.vo.UserQueryValueObject;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public class UserTest {
	
	// 普通添加测试
	@Test
	public void testDaoCreate(){
		UserValueObject uvo = new UserValueObject("1","shinono","1234",2);
		UserDAO udao = UserDAOFactory.getUserDAO();
		System.out.println(udao.create(uvo));
	}
	
	// 中文添加测试
	@Test
	public void testDaoCreate2(){
		UserValueObject uvo = new UserValueObject("2","胖头鱼","1234",2);
		UserDAO udao = UserDAOFactory.getUserDAO();
		System.out.println(udao.create(uvo));
	}
		
	// 同ID添加测试
	@Test
	public void testDaoCreate3(){
		UserValueObject uvo = new UserValueObject("1","胖头鱼","1234",2);
		UserDAO udao = UserDAOFactory.getUserDAO();
		System.out.println(udao.create(uvo));
	}
	
	// 全查找测试
	@Test
	public void testDaoFindAll() {
		UserDAO udao = UserDAOFactory.getUserDAO();
		List<UserValueObject> list = udao.findAll();
		for(UserValueObject uvo:list){
			System.out.println(uvo);
		}
		
	}
	
	// UUID查找测试
	@Test
	public void testDaoFindUser() {
		String uuid = "1";
		UserDAO udao = UserDAOFactory.getUserDAO();
		System.out.println(udao.findUser(uuid));
		
	}
	
	// 条件查找测试
	@Test
	public void testDaoFindByCondition() {
		UserQueryValueObject uqvo = new UserQueryValueObject();
		uqvo.setType(2);
		UserDAO udao = UserDAOFactory.getUserDAO();
		List<UserValueObject> list = udao.findByCondition(uqvo);
		for(UserValueObject uvo:list){
			System.out.println(uvo);
		}
	}
	
	
	// 数据更新测试
	@Test
	public void testDaoUpdate() {
		UserValueObject uvo = new UserValueObject("1","胖头妹","1234",2);
		UserDAO udao = UserDAOFactory.getUserDAO();
		System.out.println(udao.update(uvo));
	}
	
	// 删除测试
	@Test
	public void testDaoUDelete() {
		UserValueObject uvo = new UserValueObject("1","胖头妹","1234",2);
		UserDAO udao = UserDAOFactory.getUserDAO();
		System.out.println(udao.delete(uvo.getUuid()));
	}
	
}
