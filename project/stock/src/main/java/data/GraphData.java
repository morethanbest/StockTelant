package data;

import java.util.ArrayList;
import java.util.List;
import org.omg.PortableServer.POAManagerOperations;

import dataService.ChosenStockService;
import dataService.GraphService;
import dataService.SpecificInfoDataService;
import po.DealPO;
import po.DealPie;
import po.FirmPO;
import po.KPO;
import po.SpecificInfoPO;

public class GraphData implements GraphService{



	@Override
	public List<DealPie> getIndustryPie(String begindate, String enddate) {
		ChosenStockService dataserv=new ChosenStockData();
		SpecificInfoDataService data=new SpecificInfoData();
		List<DealPie> result=new ArrayList<DealPie>();
		List<String> industry=dataserv.getIndustry();
		for(int i=0;i<=industry.size()-1;i++){
			List<String> firms=dataserv.getSpecificFirmCode(industry.get(i));
			double total=0;
			for(int j=0;j<=firms.size()-1;j++){
				List<SpecificInfoPO> pos=data.getInfoByRange(firms.get(j), begindate, enddate);
				for(int k=0;k<=pos.size()-1;k++){
					total=total+Double.valueOf(pos.get(k).getVolume());
				}
				
			}
			String res=String.valueOf(total);
			DealPie pie=new DealPie(industry.get(i),res);
			result.add(pie);
		}
		//System.out.println("result                                size is"+result.size());
		return result;
	}

	@Override
	public List<FirmPO> getFirmCondition(String firmcode, String begindate, String enddate) {//
		// TODO Auto-generated method stub
		List<FirmPO> result=new ArrayList<FirmPO>();
		SpecificInfoDataService data=new	SpecificInfoData();
		
		List<SpecificInfoPO> pos=data.getInfoByRange(firmcode, begindate, enddate);
		for(int i=0;i<=pos.size()-1;i++){
			SpecificInfoPO spo=pos.get(i);
			FirmPO po=new FirmPO(spo.getDate(),spo.getCode(),spo.getOpen(),spo.getHighest(),spo.getLowest());
			result.add(po);
		}
		return result;
	}

	@Override
	public List<KPO> getKdate(String code, String begindate, String enddate) {
		// TODO Auto-generated method stub
		List<KPO> result=new ArrayList<KPO>();
		SpecificInfoDataService data=new	SpecificInfoData();
		List<SpecificInfoPO> pos=data.getInfoByRange(code, begindate, enddate);
		for(int i=0;i<=pos.size()-1;i++){
			SpecificInfoPO spo=pos.get(i);
			KPO po=new KPO(spo.getCode(),spo.getDate(),spo.getOpen(),spo.getClose(),spo.getHighest(),spo.getLowest());
			result.add(po);
		}
		return result;
	}

	@Override
	public List<DealPO> getDealPO(String code, String begindate, String enddate) {
		// TODO Auto-generated method stub
		//根据公司名称得到他们的成交量
		List<DealPO> result=new ArrayList<DealPO>();
		SpecificInfoDataService data=new	SpecificInfoData();
		
		List<SpecificInfoPO> pos=data.getInfoByRange(code, begindate, enddate);
		for(int i=0;i<=pos.size()-1;i++){
			SpecificInfoPO spo=pos.get(i);
			DealPO po=new DealPO(spo.getCode(),spo.getDate(),spo.getVolume());
			result.add(po);
		}
		return result;
	}

}
