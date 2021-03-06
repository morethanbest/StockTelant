package logicstub;

import java.util.ArrayList;
import java.util.List;

import logicService.SpecificInfoLogicService;
import vo.ConcreteInfoVO;
import vo.OriginInfoVO;

public class SpecificInfo_Stub implements SpecificInfoLogicService {
	   private String date;
	    private String code;
	    private String name;
	    private String open;    //开盘价    价格使用String是因为防止有不合理的数据出现
	    private String close;   //收盘价
	    private String highest; //最高价
	    private String lowest;  //最低价
	    private String adj_price;//后复权价
	    private String volume;//成交量
	    private String turnover;//转手率
	    private String pe;  //市盈率
	    private String pb;  //市净率
	    
	public SpecificInfo_Stub(String date, String code, String name, String open, String close, String highest,
				String lowest, String adj_price, String volume, String turnover, String pe, String pb) {
			super();
			this.date = date;
			this.code = code;
			this.name = name;
			this.open = open;
			this.close = close;
			this.highest = highest;
			this.lowest = lowest;
			this.adj_price = adj_price;
			this.volume = volume;
			this.turnover = turnover;
			this.pe = pe;
			this.pb = pb;
		}

	@Override
	public List<ConcreteInfoVO> getInfoByRange(String starttime, String endtime, String code) {
		// TODO Auto-generated method stub
		List<ConcreteInfoVO> list=new ArrayList<ConcreteInfoVO>();
		if (code.equals(this.code))
			list.add(new ConcreteInfoVO(date, this.code, name, open, close, highest, lowest, adj_price, volume, turnover, pe, pb));
		return list;
	}

}
