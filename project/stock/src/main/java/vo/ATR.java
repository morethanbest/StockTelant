package vo;

public class ATR {
	String date;
	double ATR;
	public ATR(String date, double aTR) {
		super();
		this.date = date;
		ATR = aTR;
	}
	public String getDate() {
		return date;
	}
	public double getATR() {
		return ATR;
	}
	
}
