package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import service.UsersDAO;
import service.impl.UserDAOimpl;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;

public class UsersAction extends SuperAction implements ModelDriven<Users> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 设计用户Action类,采用模型驱动接收表单数据
	 * 采用模型驱动不需要用set()和get()方法进行封装
	 */
	private Users user = new Users();
	
	//用户登录动作
	public String login() {
		UsersDAO uDao = new UserDAOimpl();
		if (uDao.usersLogin(user)) {
			//在session中保存登录成功的用户名
			session.setAttribute("loginUserName", user.getUsername());
			return "login_success";
		} else {
			System.out.println("s");
			return "login_failure";
		}
	}
	
	//用户注销方法
	@SkipValidation //调用注销方法时，不调用表单验证
	public String logout() {
		if(session.getAttribute("loginUserName")!=null) {
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}
	
	
	//重写父类方法，用以登录表单检查
	@Override
	public void validate() {
		//用户名不能为空
		if("".equals(user.getUsername().trim())) {
			this.addFieldError("usernameError", "用户名不能为空");
		}
		//密码不能小于6位
		if(user.getPassword().length()<6) {
			this.addFieldError("passwordError", "密码长度不少于6位");
		}
	}
	@Override
	public Users getModel() {
		return this.user;
	}

}
