package vo;

public class BenchmarkVO {
	private String date;
    private String code;
    private double open;    //开盘价    价格使用String是因为防止有不合理的数据出现
    private double close;   //收盘价
    private double highest; //最高价
    private double lowest;  //最低价
    private double adj_price;//后复权价
    private double volume;//成交量
	public BenchmarkVO(String date, String code, double open, double close, double highest, double lowest,
			double adj_price, double volume) {
		super();
		this.date = date;
		this.code = code;
		this.open = open;
		this.close = close;
		this.highest = highest;
		this.lowest = lowest;
		this.adj_price = adj_price;
		this.volume = volume;
	}
	public String getDate() {
		return date;
	}
	public String getCode() {
		return code;
	}
	public double getOpen() {
		return open;
	}
	public double getClose() {
		return close;
	}
	public double getHighest() {
		return highest;
	}
	public double getLowest() {
		return lowest;
	}
	public double getAdj_price() {
		return adj_price;
	}
	public double getVolume() {
		return volume;
	}
	
}
