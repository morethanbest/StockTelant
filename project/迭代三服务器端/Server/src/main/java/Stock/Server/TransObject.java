package Stock.Server;

public class TransObject {
	public TransObject(String date, String code, String open, String close,
			String highest, String lowest,String adj_price, String volume,
			String turnover, String pe, String pb) {
		this.date = date;
		this.code = code;
		
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

	private String date;
	private String code;

	private String open; // 开盘价 价格使用String是因为防止有不合理的数据出现
	private String close; // 收盘价
	private String highest; // 最高价
	private String lowest; // 最低价
	private String adj_price;// 后复权价
	private String volume;// 成交量
	private String turnover;// 转手率
	private String pe; // 市盈率
	private String pb; // 市净率

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

	public String getTurnover() {
		return turnover;
	}

	public String getPe() {
		return pe;
	}

	public String getPb() {
		return pb;
	}

}
