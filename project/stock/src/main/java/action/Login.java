package action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import controller.ChosenStockController;
import vo.OriginInfoVO;

public class Login extends ActionSupport {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	private ChosenStockController controller;
	
	


	public void setController(ChosenStockController controller) {
		this.controller = controller;
	}




	@Override
	public String execute() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		List<OriginInfoVO> al = controller.getChosenList();
		session.put("al", al);
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpServletResponse response= ServletActionContext.getResponse ();
		response.addCookie(new Cookie("312212", "1324354"));
		Cookie[] cs = request.getCookies();
		for (Cookie c : cs) {
			System.out.println(c.getName());
		}
		return SUCCESS;
	}
}
