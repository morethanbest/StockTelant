package datastub;

import java.util.ArrayList;
import java.util.List;

import dataService.SimpleInfoDataService;
import po.SimpleInfoPO;
import vo.Exchange;
import vo.OriginInfoVO;

public class Simpledata_stub implements SimpleInfoDataService{
    private String date;
    private String code;
    private String name;
    private String open;    //开盘价    价格使用String是因为防止有不合理的数据出现
    private String close;   //收盘价
    private String highest; //最高价
    private String lowest;  //最低价
    private String volume;//成交量
	 public Simpledata_stub(String date, String code,String name,  String open, String close, String highest, String lowest, String volume) {
	        this.date = date;
	        this.code = code;
	        this.name=name;
	        this.open = open;
	        this.close = close;
	        this.highest = highest;
	        this.lowest = lowest;
	        this.volume = volume;
	    }
	@Override
	public List<SimpleInfoPO> getOriginList() {
		// TODO Auto-generated method stub
		List<SimpleInfoPO> list=new ArrayList<SimpleInfoPO>();
		list.add(new SimpleInfoPO(date, code, name, open, close, highest, lowest, volume));
		return list;
	}

	@Override
	public List<SimpleInfoPO> getInfoByCity(long start, long end, Exchange exchange) {
		// TODO Auto-generated method stub
		List<SimpleInfoPO> list=new ArrayList<SimpleInfoPO>();
		list.add(new SimpleInfoPO(date, code, name, open, close, highest, lowest, volume));
		return list;
	}

	@Override
	public long getNumByCity(Exchange exchange) {
		// TODO Auto-generated method stub
		return 10;
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
