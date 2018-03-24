package service.impl;
/**
 * 学生业务逻辑接口实现类
 */
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Students;
import service.StudentsDAO;

public class StudentDAOimpl implements StudentsDAO {
	//无参的构造方法
	public StudentDAOimpl(){
		
	}
	//查询所有学生资料
	@Override
	public List<Students> queryAllStudents() {
		//创建事务对象
		Transaction tx = null;
		//学生集合
		List<Students> list = null;
		//hql语句
		String hql = "";
		try{
			//首先获得一个session会话对象
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			//开启session事务
			tx = session.beginTransaction();
			//hql语句
			hql = "from Students";
			//执行hql语句
			Query query = session.createQuery(hql);
			//获得返回的结果集
			list = query.list();
			//提交事务
			tx.commit();
			return list;
		} catch(Exception ex){
			ex.printStackTrace();
			//提交事务
			tx.commit();
			return list;
		} finally {
			if (tx != null){
				tx = null;
				System.out.println("事务置空");
			}
		}
	}
	//显示单个学生的信息
	@Override
	public Students queryStudentsBySid(String sid) {
		//获得一个session对象
		Transaction tx = null;
		//单个学生对象
		Students s = null;
		try{
			//获得一个session会话
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			//开启事务
			tx = session.beginTransaction();
			//获得学生信息
			s = (Students) session.get(Students.class, sid);
			//提交事务
			tx.commit();
			return s;
		} catch (Exception ex){
			ex.printStackTrace();
			tx.commit();
			return s;
		} finally {
			if (tx != null){
				tx = null;
			}
		}
	}
	//添加学生的方法
	@Override
	public boolean addStudents(Students s) {
		//生成添加学生的学号
		s.setSid(getNewSid());
		//生成一个事物对象
		Transaction tx = null;
		try{
			//获得一个session会话
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			//开启事务
			tx = session.beginTransaction();
			session.save(s);
			//提交事务
			tx.commit();
			return true;	
		} catch (Exception ex){
			ex.printStackTrace();
			tx.commit();
			return false;
		} finally {
			if (tx != null){
				tx = null;
			}
		}
	}

	//保存学生信息
	@Override
	public boolean updateStudents(Students s) {
		//获得事务对象
		Transaction tx = null;
		try{
			//获得一个会话对象
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			//开启一个事务
			tx = session.beginTransaction();
			session.update(s);
			tx.commit();//要记得提交事务啊，不然写到数据库里面去
			return true;
		} catch (Exception ex){
			ex.printStackTrace();
			//提交事务
			tx.commit();
			return false;
		} finally {
			if (tx != null){
				tx = null;
			}
		}
	}
	
	//删除单个学生的方法
	@Override
	public boolean deleteStudents(String sid) {
		//生成事务对象
		Transaction tx = null;
		try {
			//获得一个会话对象
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			//开启事务
			tx = session.beginTransaction();
			//获得一个学生对象
			Students s = (Students) session.get(Students.class, sid);
			session.delete(s);
			//提交事务
			tx.commit();
			return true;
		} catch (Exception ex) {
			//打印异常
			ex.printStackTrace();
			return false;
		} finally {
			if (tx != null){
				tx = null;
			}
		}
	}
	
	//生成学生的学号
	public String getNewSid(){
		//生成一个事务对象
		Transaction tx = null;
		//hql语句
		String hql = "";
		//要生成的学生的编号
		String sid = null;
		try {
			//获得一个session
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			//开启session的事务
			tx = session.beginTransaction();
			//获得当前学生列表中最大的学生编号
			hql = "select max(sid) from Students";
			//获得一个query对象
			Query query = session.createQuery(hql);
			//获得唯一条记录
			sid = (String) query.uniqueResult();
			//判断一下sid，说明数据库的学生表没有任何记录,给一个默认的最大编号
			if (sid == null || "".equals(sid)){
				sid = "S0000001";
			} else {
				//已经找到了最大值
				//去掉第一个字母s,只取最后7位
				String temp = sid.substring(1);
				//字符串转成数字
				int i = Integer.parseInt(temp);
				//生成新的编号的后7位
				i++;
				//再还原成字符串
				temp = String.valueOf(i);
				//获得一下长度
				int len = temp.length();
				//凑成7位字符串
				for (int j = 0; j < 7-len; j++){
					temp = "0" + temp;
				}
				sid = "S" + temp;
			}
			//提交事务
			tx.commit();
			return sid;
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.commit();
			return sid;
		} finally {
			if (tx != null){
				//关闭事务
				tx = null;
			}
		}
	}

}
