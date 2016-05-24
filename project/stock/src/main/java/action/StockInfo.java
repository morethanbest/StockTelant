package action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import controller.GraphController;
import controller.SpecificInfoController;
import net.sf.json.JSONArray;
import vo.ConcreteInfoVO;

public class StockInfo extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String stockNumber;
	private SpecificInfoController controller;
	private GraphController controller2;
	
	public String getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(String stockNumber) {
		this.stockNumber = stockNumber;
	}

	public void setController(SpecificInfoController controller) {
		this.controller = controller;
	}
	public void setController2(GraphController controller2) {
		this.controller2 = controller2;
	}
	@Override
	public String execute() throws Exception {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		List<ConcreteInfoVO> concrete = controller.getInfoByRange("2010-01-01", "2016-05-01", stockNumber);
		JSONArray k = controller2.getKdate(stockNumber, "2010-01-01", "2016-05-01");
		JSONArray js = controller2.getBR(stockNumber, "2016-04-01", "2016-05-01");
		JSONArray js2 = controller2.getAR(stockNumber, "2016-04-01", "2016-05-01");
		System.out.println(js);
		session.put("concrete", concrete);
		session.put("k", k);
		session.put("name", concrete.get(0).getName());
		session.put("code", stockNumber);
		session.put("BR", js);
		session.put("AR", js2);
		return SUCCESS;
	}


}
