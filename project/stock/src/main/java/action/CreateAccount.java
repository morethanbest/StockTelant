package action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import controller.UserController;

public class CreateAccount extends ActionSupport {

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
		addActionMessage("注册成功");
		return SUCCESS;
	}

	@Override
	public void validate() {
		if (controller.addUser(username, password) == 0)
			addFieldError("username", "用户名已存在");
	}
}
