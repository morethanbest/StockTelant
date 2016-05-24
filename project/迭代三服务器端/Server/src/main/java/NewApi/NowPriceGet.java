package NewApi;

public class NowPriceGet {
	public String getPriceNow(String code){
		String result=null;
		String url="http://hq.sinajs.cn/list="+code;
		String str=ApiGetter.do_get(url);
		String[] temp=str.split(",");
		if(temp.length>=4){
			result=temp[3];
		}
		
		
		
		return result;
	
	}
	
	public String getOpenToday(String code){
		String result=null;
		String url="http://hq.sinajs.cn/list="+code;
		String str=ApiGetter.do_get(url);
		String[] temp=str.split(",");
		if(temp.length>=3){
			result=temp[2];
		}
		
		
		
		return result;
	
	}
	
	
	
	
	
	public static void main(String[] args) {
		NowPriceGet p =new NowPriceGet();
		String res=p.getPriceNow("sh000300");
		System.out.println(res);
		String res2=p.getOpenToday("sh000300");
		System.out.println(res2);
	}
	
}
