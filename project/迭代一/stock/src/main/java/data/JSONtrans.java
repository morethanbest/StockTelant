package data;

import java.util.List;

import po.SimpleInfoPO;

public class JSONtrans {
	public static void main(String[] args){
/*		SpecificInfoData data=new SpecificInfoData();
		String url="http://121.41.106.89:8010/api/stock/sh600000/?start=2014-10-10&end=2015-10-10&fields=open+high+close";
		List<SpecificInfoPO> list=data.getInfoByUrl(url);
		SpecificInfoPO po=list.get(0);
		System.out.println(po.getHighest());
		System.out.println(po.getPb());*/
		long start = System.currentTimeMillis();	// 记录起始时间
		
	//	SimpleInfoData data=new SimpleInfoData();//2087   
//		List<SimpleInfoPO> list=data.getOriginList();//214123
//		List<SimpleInfoPO> list=data.getOriginBriefList();
		long end = System.currentTimeMillis();		// 记录结束时间
		System.out.println(end-start);				// 相减得出运行时间
		
	}
}
