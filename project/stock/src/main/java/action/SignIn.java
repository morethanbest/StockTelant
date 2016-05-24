package action;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import controller.UserController;

public class SignIn extends ActionSupport{
	
	private String username;
	private String password;
	
	private UserController controller;
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setController(UserController controller) {
		this.controller = controller;
	}
	@Override
	public String execute() throws Exception {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		HttpServletResponse response= ServletActionContext.getResponse();
		response.addCookie(new Cookie(username, password));
		return SUCCESS;
	}
	
	@Override
	public void validate() {
		if(controller.checkUser(username, password) == 0)
			addFieldError("username", "用户名或密码错误");
	}
}
