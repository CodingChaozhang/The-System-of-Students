package service;
//学生的业务逻辑接口
import java.util.List;

import entity.Students;

public interface StudentsDAO {

	//查询所有学生资料
	public List<Students> queryAllStudents();
	
	//根据学生编号查询单个学生资料
	public Students queryStudentsBySid(String sid);
	
	//添加学生资料
	public boolean addStudents(Students s);
	
	//修改学生资料
	public boolean updateStudents(Students s);
	
	//删除学生资料
	public boolean deleteStudents(String sid);
	
}
