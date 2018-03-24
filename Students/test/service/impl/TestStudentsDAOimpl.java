package service.impl;

import org.junit.Assert;
import org.junit.Test;

import entity.Students;
import service.StudentsDAO;
import service.impl.StudentDAOimpl;

import java.util.Date;
import java.util.List;
public class TestStudentsDAOimpl {

//测试查询所有学生的方法
  @Test
  public void testQueryAllStudents()
  {
	   StudentsDAO sdao = new StudentDAOimpl();
	   List<Students> list = sdao.queryAllStudents();
       //遍历集合
	   for(int i= 0;i<list.size();i++)
       {
    	   System.out.println(list.get(i));
    	   
       }
  }
  
  //测试生成添加学生的编号的方法
  @Test
  public void testGetNewSid()
  {
	   StudentDAOimpl sdao = new StudentDAOimpl();
	   System.out.println(sdao.getNewSid());
  }
  //测试添加学生的方法
  @Test
  public void testaddStudents()
  {
	  Students s = new Students();
	  s.setSname("吕超章");
	  s.setGender("男");
	  s.setBirthday(new Date());
      s.setAddress("山东省"); 
      StudentsDAO sdao = new StudentDAOimpl();
      //断言
      Assert.assertEquals(true,sdao.addStudents(s));   
      
  }
}
