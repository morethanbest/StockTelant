package vo;

public class DealPieVO {
	private String name;
	private double percentage;
	private double num;
	
	public DealPieVO(String name,double percentage,double num){
		this.name=name;
		this.percentage=percentage;
		this.num=num;
	}

	public String getName() {
		return name;
	}


	//得到的是小数
	public double getPercentage() {
		return percentage;
	}



	public double getNum() {
		return num;
	}


}
