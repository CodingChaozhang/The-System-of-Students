package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Users;
import service.UsersDAO;




public class UserDAOimpl implements UsersDAO {

	@Override
	public boolean usersLogin(Users u) {
		//事物对象
		Transaction tx=null;
		String hql="";
		try {
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();//获得一个会话对象
			tx = session.beginTransaction();//开启事务
			hql="from Users where username=? and password=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, u.getUsername());
			query.setParameter(1, u.getPassword());
			List list = query.list();
			tx.commit();//提交事务
			if ( list.size() > 0 ){
				System.out.println("1");
				return true;
			}else{
				System.out.println("2");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			if(tx!=null) {
				tx=null;
				System.out.println("3");
			}
		}	
		
	}	

}
