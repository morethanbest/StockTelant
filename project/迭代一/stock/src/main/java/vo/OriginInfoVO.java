package vo;

/**
 * Created by Administrator on 2016/3/1.
 */
public class OriginInfoVO {
    public OriginInfoVO(String date, String code, String name, String open, String close, String highest, String lowest, String volume) {
        this.date = date;
        this.code = code;
        this.name=name;
        this.open = open;
        this.close = close;
        this.highest = highest;
        this.lowest = lowest;
        this.volume = volume;
    }

    private String date;
    private String code;
    private String name;
    private String open;    //开盘价    价格使用String是因为防止有不合理的数据出现
    private String close;   //收盘价
    private String highest; //最高价
    private String lowest;  //最低价
    private String volume;//成交量

    public String getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }

    public String getName(){
    	return name;
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

    public String getVolume() {
        return volume;
    }

    public String getLowest() {
        return lowest;
    }
}
