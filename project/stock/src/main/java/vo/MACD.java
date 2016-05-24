package vo;

public class MACD {
	String date;
	double DIF;	//折线
	double DEA;	//折线
	double MACD;	//柱状
	public MACD(String date, double dIF, double dEA, double mACD) {
		super();
		this.date = date;
		DIF = dIF;
		DEA = dEA;
		MACD = mACD;
	}
	public String getDate() {
		return date;
	}
	public double getDIF() {
		return DIF;
	}
	public double getDEA() {
		return DEA;
	}
	public double getMACD() {
		return MACD;
	}

	
}
