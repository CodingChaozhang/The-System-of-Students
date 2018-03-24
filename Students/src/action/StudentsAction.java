package action;
import service.StudentsDAO;


import service.impl.StudentDAOimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import entity.Students;
/**
 * 学生Action类
 * @author LvChaoZhang
 *
 */
public class StudentsAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	//查询所有学生的动作
	public String query()
	{
		//学生接口的引用
		StudentsDAO sdao = new StudentDAOimpl();
		//返回一个学生的集合
		List<Students> list =sdao.queryAllStudents();
		//将返回的集合放进session中
		if (list != null || list.size()>0){
			session.setAttribute("students_list", list);
		}
		return "query_success";	
	}
	//删除学生的动作
	public String  delete()
    {
    	//学生业务逻辑类实现的对象
    	StudentsDAO sdao= new StudentDAOimpl();
    	//获得学生的sid号
    	String sid = request.getParameter("sid");
    	//调用删除方法，删除学生
    	sdao.deleteStudents(sid);
    	//调用一个视图
    	return "delete_success";
    	
    }
	//修改学生资料动作
    public String modify() throws Exception
	{
    	//获得界面传过来的学生sid编号
    	String sid = request.getParameter("sid");
		//获得学生实现类
    	StudentsDAO sdao = new StudentDAOimpl();
		
		Students s = sdao.queryStudentsBySid(sid);
		//保存到会话中
		session.setAttribute("modify_students", s);
		return "modify_success";
	}
    
    //添加和保存是不同的操作，针对修改来说
    public String add () throws Exception
	{
    	Students s = new Students();
    	s.setSname(request.getParameter("sname"));
    	s.setGender(request.getParameter("gender"));
    	s.setAddress(request.getParameter("address"));
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	s.setBirthday(sdf.parse(request.getParameter("birthday")));
	   StudentsDAO sdao = new StudentDAOimpl();
	   sdao.addStudents(s);
	   return "add_success";
	}
    //保存修改后的学生资料动作
    public String save() throws Exception
	{
    	//获得一个学生信息
    	Students s = new Students();
    	//获得学号
    	s.setSid(request.getParameter("sid"));
    	System.out.println("测试修改--save()--sid:" + request.getParameter("sid") );
    	s.setSname(request.getParameter("sname"));
    	s.setGender(request.getParameter("gender"));
    	s.setAddress(request.getParameter("address"));
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	s.setBirthday(sdf.parse(request.getParameter("birthday")));
    	System.out.println("地址:" +  request.getParameter("address"));
    	StudentsDAO sdao = new StudentDAOimpl();
    	sdao.updateStudents(s);
    	//返回的视图
    	return "save_success";
	}
}
