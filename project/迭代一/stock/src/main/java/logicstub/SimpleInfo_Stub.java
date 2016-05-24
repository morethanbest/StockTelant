package logicstub;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.LSTORE;

import logicService.SimpleInfoLogicService;
import vo.Exchange;
import vo.OriginInfoVO;

public class SimpleInfo_Stub implements SimpleInfoLogicService {
	
	   private String date;
	    private String code;
	    private String name;
	    private String open;    //开盘价    价格使用String是因为防止有不合理的数据出现
	    private String close;   //收盘价
	    private String highest; //最高价
	    private String lowest;  //最低价
	    private String volume;//成交量
	    

	
	public SimpleInfo_Stub(String date, String code, String name, String open, String close, String highest,
				String lowest, String volume) {
			super();
			this.date = date;
			this.code = code;
			this.name = name;
			this.open = open;
			this.close = close;
			this.highest = highest;
			this.lowest = lowest;
			this.volume = volume;
		}

	@Override
	public List<OriginInfoVO> getInfoByCity(long start, long end, Exchange exchange) {
		// TODO Auto-generated method stub
		List<OriginInfoVO> list=new ArrayList<OriginInfoVO>();
		list.add(new OriginInfoVO(date, code, name, open, close, highest, lowest, volume));
		return list;
	}

	@Override
	public long getNumByCity(Exchange exchange) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public List<String> getCodeName(String code, Exchange exchange) {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		if(this.code.contains(code)||this.name.contains(code)){
			list.add(this.code+" "+name);
		}
		return list;
	}

}
