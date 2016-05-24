package vo;

public class Statistics {
	String date;
	double value;
	public Statistics(String date, double value) {
		super();
		this.date = date;
		this.value = value;
	}
	public String getDate() {
		return date;
	}
	public double getValue() {
		return value;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
}
