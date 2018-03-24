package service.impl;

import junit.framework.Assert;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

import service.UsersDAO;
import entity.Students;
import entity.Users;

public class TestUsersDAOimpl {

	//测试用户接口类
	@Test
	public void testUsersLogin(){
		Users u = new Users(1, "zhangsan", "123456");
		UsersDAO udao = new UserDAOimpl();
		Assert.assertEquals(true, udao.usersLogin(u));
	}
	
}
