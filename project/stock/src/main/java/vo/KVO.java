package vo;

public class KVO {
	private String date;
	private double open;//开盘价
	private double close; //收盘价
	private double high; //最高价
	private double low; //最低价
	private double volume;
	private double value;//平均价
	public KVO(String date, double open, double close, double high, double low, double volume, double value) {
		super();
		this.date = date;
		this.open = open;
		this.close = close;
		this.high = high;
		this.low = low;
		this.volume = volume;
		this.value = value;
	}
	public String getDate() {
		return date;
	}
	public double getOpen() {
		return open;
	}
	public double getClose() {
		return close;
	}
	public double getHigh() {
		return high;
	}
	public double getLow() {
		return low;
	}
	public double getVolume() {
		return volume;
	}
	public double getValue() {
		return value;
	}
	
	
	


	
	
	
}
