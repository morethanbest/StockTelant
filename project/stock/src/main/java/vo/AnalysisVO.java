package vo;

public class AnalysisVO {
    private String code;
    private String name;
    private String AR;
    private String BR;
    private String WMS;

    public AnalysisVO(String code, String name, String AR, String BR, String WMS) {
        this.code = code;
        this.name=name;
        this.AR = AR;
        this.BR = BR;
        this.WMS = WMS;
    }


    public String getCode() {
        return code;
    }

    public String getName(){
    	return name;
    }

    public String getAR() {
        return AR;
    }

    public String getBR() {
        return BR;
    }

    public String getWMS() {
        return WMS;
    }

}
