package po;

public class BenchmarkPO {
	public BenchmarkPO(String date,String code,String open,String close,String highest
			,String lowest,String adj_price,String voluem){
		this.date=date;
		this.code=code;
		this.open=open;
		this.close=close;
		this.highest=highest;
		this.lowest=lowest;
		this.adj_price=adj_price;
		this.volume=voluem;
	}
	
    private String date;
    private String code;
    private String open;    //开盘价    价格使用String是因为防止有不合理的数据出现
    private String close;   //收盘价
    private String highest; //最高价
    private String lowest;  //最低价
    private String adj_price;
    private String volume;//成交量
	public String getDate() {
		return date;
	}
	public String getCode() {
		return code;
	}
	public String getOpen() {
		return open;
	}
	public String getClose() {
		return close;
	}
	public String getHighest() {
		return highest;
	}
	public String getLowest() {
		return lowest;
	}
	
	
	public String getAdj_price() {
		return adj_price;
	}
	public String getVolume() {
		return volume;
	}
   


}
