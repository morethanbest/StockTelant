package vo;

public class StatisticsVO {
	String code;
	String date;
	AR AR; //人气指标
	BR BR; //意愿指标
	VR VR; //容量比率
	WMS WMS; //威廉指标
	PSY PSY; //心理线
	public StatisticsVO(String code, String date, vo.AR aR, vo.BR bR, vo.VR vR, vo.WMS wMS, vo.PSY pSY) {
		super();
		this.code = code;
		this.date = date;
		AR = aR;
		BR = bR;
		VR = vR;
		WMS = wMS;
		PSY = pSY;
	}
	public String getCode() {
		return code;
	}
	public String getDate() {
		return date;
	}
	public AR getAR() {
		return AR;
	}
	public BR getBR() {
		return BR;
	}
	public VR getVR() {
		return VR;
	}
	public WMS getWMS() {
		return WMS;
	}
	public PSY getPSY() {
		return PSY;
	}
	

	
	
	
}
