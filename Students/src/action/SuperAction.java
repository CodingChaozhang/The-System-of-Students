package action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

//所有Acion动作的父类
public class SuperAction extends ActionSupport implements ServletResponseAware, ServletRequestAware, ServletContextAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 为了实现Action拓展的过滤器和拦截器功能,需要继承ActionSupport
	 * 为了方便获得内置对象，分别实现以上三个接口
	 */
	protected HttpServletRequest request;//请求对象
	protected HttpServletResponse response;//响应对象
	protected HttpSession session;//会话对象
	protected ServletContext application;//全局对象
	@Override
	public void setServletContext(ServletContext application) {
		this.application = application;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = this.request.getSession();
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
}
