package logic;

import java.util.ArrayList;
import java.util.List;

import Date.DateServ;
import dataService.ChosenStockService;
import dataService.GraphService;
import dataService.SpecificInfoDataService;
import logicService.GraphBLService;
import po.BenchmarkPO;
import po.DealPO;
import po.DealPie;
import po.FirmPO;
import po.SpecificInfoPO;
import vo.AR;
import vo.ATR;
import vo.BIAS;
import vo.BR;
import vo.DealPieVO;
import vo.DealVO;
import vo.FirmVO;
import vo.KVO;
import vo.MACD;
import vo.PSY;
import vo.RSV;
import vo.VR;
import vo.WMS;

public class GraphBL implements GraphBLService {
	private GraphService graphService;
	private ChosenStockService chosenStockService;
	private SpecificInfoDataService specificInfoDataService;
	


	public void setGraphService(GraphService graphService) {
		this.graphService = graphService;
	}


	public void setChosenStockService(ChosenStockService chosenStockService) {
		this.chosenStockService = chosenStockService;
	}


	public void setSpecificInfoDataService(SpecificInfoDataService specificInfoDataService) {
		this.specificInfoDataService = specificInfoDataService;
	}


	@Override
	public List<KVO> getKdate(String code,String begindate, String enddate) {
		// TODO Auto-generated method stub
		List<SpecificInfoPO> pos=specificInfoDataService.getInfoByRange(code, begindate, enddate);
		List<KVO> kvos=TransObject.transKList(pos);
		return kvos;
	}

	
	public List<KVO> getBenchmarkKdate(String begindate,String enddate){
		List<BenchmarkPO> kpos=chosenStockService.getBenchmarkList(begindate, enddate);
		List<KVO> kvos=TransObject.transBenchmarkKList(kpos);
		return kvos;
	}
	@Override
	public List<DealVO> getDealVO(String code,String begindate, String enddate) {
		// TODO Auto-generated method stub
		List<DealPO> pos=graphService.getDealPO(code, begindate, enddate);
		List<DealVO> vos=TransObject.transDealList(pos);
		return vos;
	}
	
//	public List<KVO> getWeekKdate(String code,String begindate,String enddate){
//		String begin=DateServ.getDateofWeek(begindate);
//		String end=DateServ.getDateofWeek(enddate);
//		List<KPO> kpos=graphService.getKdate(code, begin, end);
//		List<KVO> kvos=TransObject.transKList(kpos);
//		
//		List<KVO> kvos2=new ArrayList<>();
//		double max=0;
//		double min=Double.MAX_VALUE;
//		double sum=0;
//		double start=0;
//		for(int i=0;i<kvos.size();i++){
//			sum+=kvos.get(i).getHigh()+kvos.get(i).getLow();
//			if(i%5==0){
//				start=kvos.get(i).getOpen();
//			}
//			if(max<kvos.get(i).getHigh()){
//				max=kvos.get(i).getHigh();
//			}
//			if(min>kvos.get(i).getLow()){
//				min=kvos.get(i).getLow();
//			}
//			if(i%5==4){
//				kvos2.add(new KVO(start, kvos.get(i).getClose(), max, min, sum/10));
//				max=0;
//				min=Double.MAX_VALUE;
//				sum=0;
//				continue;
//			}
//			if(i==kvos.size()-1){
//				kvos2.add(new KVO(start, kvos.get(i).getClose(), max, min, sum/((i%5)+1)*2));
//			}
//		
//		}
//		return kvos2;
//	}
//	
//	
//	public List<KVO> getBenchmarkWeekKdate(String begindate,String enddate){
//		String begin=DateServ.getDateofWeek(begindate);
//		String end=DateServ.getDateofWeek(enddate);
//		List<BenchmarkPO> kpos=chosenStockService.getBenchmarkList(begin, end);
//		
//		List<KVO> kvos=TransObject.transBenchmarkKList(kpos);
//		
//		List<KVO> kvos2=new ArrayList<>();
//		double max=0;
//		double min=Double.MAX_VALUE;
//		double sum=0;
//		double start=0;
//		for(int i=0;i<kvos.size();i++){
//			sum+=kvos.get(i).getHigh()+kvos.get(i).getLow();
//			if(i%5==0){
//				start=kvos.get(i).getStart();
//			}
//			if(max<kvos.get(i).getHigh()){
//				max=kvos.get(i).getHigh();
//			}
//			if(min>kvos.get(i).getLow()){
//				min=kvos.get(i).getLow();
//			}
//			if(i%5==4){
//				kvos2.add(new KVO(start, kvos.get(i).getEnd(), max, min, sum/10));
//				max=0;
//				min=Double.MAX_VALUE;
//				sum=0;
//				continue;
//			}
//			if(i==kvos.size()-1){
//				kvos2.add(new KVO(start, kvos.get(i).getEnd(), max, min, sum/((i%5)+1)*2));
//			}
//		
//		}
//		return kvos2;
//	}
//	
//	public List<KVO> getMonthKdate(String code,String begindate,String enddate){
//		String begin=begindate;
//		String end=DateServ.getDateofMonth(begin);
//		List<KPO> kpos=graphService.getKdate(code, begin, end);
//		List<KVO> kvos=TransObject.transKList(kpos);
//		
//		List<KVO> kvos2=new ArrayList<>();
//		double max=0;
//		double min=Double.MAX_VALUE;
//		double sum=0;
//		double start=0;
//		while(begin.compareTo(enddate)<0){
//			for(int i=0;i<kvos.size();i++){
//				start=kvos.get(0).getStart();
//				if(max<kvos.get(i).getHigh()){
//					max=kvos.get(i).getHigh();
//				}
//				if(min>kvos.get(i).getLow()){
//					min=kvos.get(i).getLow();
//				}
//				if(i==kvos.size()-1){
//					kvos2.add(new KVO(start, kvos.get(i).getEnd(), max, min, sum/(kvos.size()*2)));
//					max=0;
//					min=Double.MAX_VALUE;
//					sum=0;
//				}
//			}
//			begin=end;
//			end=DateServ.getDateofMonth(begin);
//			kpos=graphService.getKdate(code, begin, end);
//			kvos=TransObject.transKList(kpos);
//		}
//		return kvos2;
//	}
//
//	
//	public List<KVO> getBenchmarkMonthKdate(String begindate,String enddate){
//		String begin=begindate;
//		String end=DateServ.getDateofMonth(begin);
//		List<BenchmarkPO> kpos=chosenStockService.getBenchmarkList(begin, end);
//		
//		List<KVO> kvos=TransObject.transBenchmarkKList(kpos);
//		
//		List<KVO> kvos2=new ArrayList<>();
//		double max=0;
//		double min=Double.MAX_VALUE;
//		double sum=0;
//		double start=0;
//		while(begin.compareTo(enddate)<0){
//			for(int i=0;i<kvos.size();i++){
//				start=kvos.get(0).getStart();
//				if(max<kvos.get(i).getHigh()){
//					max=kvos.get(i).getHigh();
//				}
//				if(min>kvos.get(i).getLow()){
//					min=kvos.get(i).getLow();
//				}
//				if(i==kvos.size()-1){
//					kvos2.add(new KVO(start, kvos.get(i).getEnd(), max, min, sum/(kvos.size()*2)));
//					max=0;
//					min=Double.MAX_VALUE;
//					sum=0;
//				}
//			}
//			begin=end;
//			end=DateServ.getDateofMonth(begin);
//			kpos=chosenStockService.getBenchmarkList(begin, end);
//			kvos=TransObject.transBenchmarkKList(kpos);
//		}
//		return kvos2;
//	}
	@Override
	public List<DealPieVO> getIndustryPie(String begindate, String enddate) {
		// TODO Auto-generated method stub
		List<DealPie> pies=graphService.getIndustryPie(begindate, enddate);
		List<DealPieVO> vos=TransObject.transPie(pies);
		return vos;
	}

	@Override
	public List<FirmVO> getFirmCondition(String firmcode, String begindate, String enddate) {
		// TODO Auto-generated method stub
		List<FirmPO> pos=graphService.getFirmCondition(firmcode, begindate, enddate);
		List<FirmVO> vos=TransObject.transFirmList(pos);
		System.out.println(vos.size());
		return vos;
	}
	
	
	
	
	
	
	
public List<AR> getAR(String code,String starttime,String endtime){
		
		//默认是26天
		List<AR> ARS=new ArrayList<>();
		String rangestart=DateServ.getDateBefore(25, starttime);
		List<SpecificInfoPO> pos=specificInfoDataService.getInfoByRange(code, rangestart, endtime);
		double sumHO=0;
		double sumOL=0;
		SpecificInfoPO po;
		if(pos.size()==0){
			return ARS;
		}
		for(int i=0;i<pos.size()-25;i++){
			sumHO=0;
			sumOL=0;
			for(int j=0;j<26;j++){
				po=pos.get(i+j);
				sumHO+=Double.parseDouble(po.getHighest())-Double.parseDouble(po.getOpen());
				sumOL+=Double.parseDouble(po.getOpen())-Double.parseDouble(po.getLowest());
			}
			ARS.add(new AR(pos.get(i+25).getDate(),sumHO/sumOL*100));
		}
		return  ARS;
	}


	public List<BR> getBR(String code,String starttime,String endtime){
		//默认是26天
		List<BR> BRS=new ArrayList<>();
		String rangestart=DateServ.getDateBefore(26, starttime);
		List<SpecificInfoPO> pos=specificInfoDataService.getInfoByRange(code, rangestart, endtime);
		double sumHO=0;
		double sumOL=0;
		if(pos.size()==0){
			return BRS;
		}
		for(int i=0;i<pos.size()-26;i++){
			sumHO=0;
			sumOL=0;
			for(int j=0;j<26;j++){
			sumHO+=Double.parseDouble(pos.get(i+j+1).getHighest())-Double.parseDouble(pos.get(i+j).getClose());
			sumOL+=Double.parseDouble(pos.get(i+j).getClose())-Double.parseDouble(pos.get(i+j+1).getLowest());
			}
			BRS.add(new BR(pos.get(i+26).getDate(),sumHO/sumOL*100));
		}
		return  BRS;
	}

	@Override
	public List<VR> getVR(String code, String starttime, String endtime) {
		// TODO Auto-generated method stub
		List<VR> VR=new ArrayList<>();
		String rangestart=DateServ.getDateBefore(26, starttime);
		List<SpecificInfoPO> pos=specificInfoDataService.getInfoByRange(code, rangestart, endtime);
		double upn=0;
		double downn=0;
		for(int i=0;i<pos.size()-26;i++){
			for(int j=0;j<26;j++){
			if(Double.parseDouble(pos.get(i+j).getClose())>Double.parseDouble(pos.get(i+j+1).getClose())){
				upn+=Double.parseDouble(pos.get(i+j+1).getVolume())*Double.parseDouble(pos.get(i+j+1).getClose());
			}else{
				downn+=Double.parseDouble(pos.get(i+j+1).getVolume())*Double.parseDouble(pos.get(i+j+1).getClose());
			}
			}
			VR.add(new VR(pos.get(i+26).getDate(),upn/downn*100));			//*100%
			
		}
		return VR;
		
	}


	public List<WMS> getWMS(String code,String starttime,String endtime){
		//默认是14天
		List<WMS> WMS=new ArrayList<>();
		String rangestart=DateServ.getDateBefore(13, starttime);
		List<SpecificInfoPO> pos=specificInfoDataService.getInfoByRange(code, rangestart, endtime);
		if(pos.size()==0){
			return WMS;
		}
		SpecificInfoPO lastpo;
		SpecificInfoPO po;
		double highest;
		double lowest;
		double close;
		double temphigh;
		double templow;
		for(int i=0;i<pos.size()-13;i++){
			lastpo=pos.get(i+13);
			highest=Double.parseDouble(lastpo.getHighest());
			lowest=Double.parseDouble(lastpo.getLowest());
			close=Double.parseDouble(lastpo.getClose());
			for(int j=0;j<14;j++){
				po=pos.get(i+j);
				temphigh=Double.parseDouble(po.getHighest());
				templow=Double.parseDouble(po.getLowest());
				if(highest<temphigh){
					highest=temphigh;
				}
				if(lowest>templow){
					lowest=templow;
				}
			}
			WMS.add(new WMS(pos.get(i+13).getDate(),(highest-close)/(highest-lowest)*100));
		}
		return WMS;
	}

	public List<ATR> getATRS(String code,String starttime,String endtime){
		String rangestart=DateServ.getDateBefore(1, starttime);
		List<SpecificInfoPO> pos=specificInfoDataService.getInfoByRange(code, rangestart, endtime);
//		long temptime=time+1;
//		while(pos.size()<=time){						//防止节假日影响日期判断
//			temptime++;
//			starttime=DateServ.getDateBefore(temptime);
//			pos=specificService.getInfoByRange(code, starttime, endtime);
//		}
		List<ATR> ATRS=new ArrayList<>();
		List<Double> TRs=new ArrayList<>();
		for(int i=0;i<pos.size()-1;i++){
			double hi=Double.parseDouble(pos.get(i+1).getHighest());
			double li=Double.parseDouble(pos.get(i+1).getLowest());
			double ci1=Double.parseDouble(pos.get(i).getClose());
			double li1=Double.parseDouble(pos.get(i).getLowest());
			double TR=Math.max(Math.abs(hi-li), Math.abs(ci1-hi));
			TR=Math.max(Math.abs(ci1-li1), TR);
			TRs.add(TR);
		}
		for(int i=0;i<TRs.size();i++){

			double sum=0;
			for(int j=0;j<=i;j++){
				sum+=TRs.get(j);
			}
			ATRS.add(new ATR(pos.get(i+1).getDate(),sum/(i+1)));
		}
		return ATRS;
	}


	public List<MACD> getMACD(String code,String starttime,String endtime){
		List<MACD> MACDs=new ArrayList<>();
		List<SpecificInfoPO> pos=specificInfoDataService.getInfoByRange(code, starttime, endtime);
		if(pos.size()==0){
			return MACDs;
		}
		double close=Double.parseDouble(pos.get(0).getClose());
		double EMA12=close;
		double EMA26=close;
		double DIF=EMA12-EMA26;
		double DEA=DIF;
		MACDs.add(new MACD(pos.get(0).getDate(),DIF,DEA,(DIF-DEA)*2));
		for(int i=1;i<pos.size();i++){
			close=Double.parseDouble(pos.get(i).getClose());
			EMA12=EMA12*11/13+close*2/13;
			EMA26=EMA26*25/27+close*2/27;
			DIF=EMA12-EMA26;
			DEA=DEA*8/10+DIF*2/10;
			MACDs.add(new MACD(pos.get(i).getDate(),DIF,DEA,(DIF-DEA)*2));

		}


		return MACDs;
	}



	
	
	
	public List<BIAS> BIAS(String code,String starttime,String endtime,long time){
		List<BIAS> BIAS=new ArrayList<>();
		String rangestart=DateServ.getDateBefore(time-1, starttime);
		List<SpecificInfoPO> pos=specificInfoDataService.getInfoByRange(code, rangestart, endtime);
		if(pos.size()==0){
			return BIAS;
		}
		int time1=(int)time-1;
		double average=0;
		for(int i=time1;i<pos.size();i++){
			average=0;
			for(int j=i-time1;j<=i;j++){
				average+=Double.parseDouble(pos.get(j).getClose());
			}
			average/=(time1+1);
			BIAS.add(new BIAS(pos.get(i).getDate(),(Double.parseDouble(pos.get(i).getClose())-average)/average*100));
			
		}
		
		return BIAS;
	}


	@Override
	public List<PSY> PSY(String code, String starttime, String endtime) {
		// TODO Auto-generated method stub
		List<PSY> PSY=new ArrayList<>();
		String rangestart=DateServ.getDateBefore(12, starttime);
		List<SpecificInfoPO> pos=specificInfoDataService.getInfoByRange(code, rangestart, endtime);
		double time=0;
		for(int i=1;i<pos.size()-11;i++){
			time=0;
			for(int j=0;j<12;j++)
			if(Double.parseDouble(pos.get(i+j-1).getClose())>Double.parseDouble(pos.get(i+j).getClose())){
				time++;
			}
			PSY.add(new PSY(pos.get(i+11).getDate(),time/12*100));			//*100%
			
		}
		return PSY;
	}
	@Override
	public List<RSV> RSV(String code, String starttime, String endtime) {
		// TODO Auto-generated method stub
		List<RSV> KDJ=new ArrayList<>();
		String rangestart=DateServ.getDateBefore(8, starttime);
		List<SpecificInfoPO> pos=specificInfoDataService.getInfoByRange(code, rangestart, endtime);
		List<Double> rsv=new ArrayList<>();
		if(pos.size()<=0){
			return KDJ;
		}
		double close;
		double lowest;
		double highest;
		double temphigh;
		double templow;
		SpecificInfoPO po;
		for(int i=0;i<pos.size()-9;i++){
			po=pos.get(i+8);
			close=Double.parseDouble(po.getClose());
			highest=Double.parseDouble(po.getHighest());
			lowest=Double.parseDouble(po.getLowest());
			for(int j=0;j<9;j++){
				po=pos.get(i+j);
				temphigh=Double.parseDouble(po.getHighest());
				templow=Double.parseDouble(po.getLowest());
				if(temphigh>highest){
					highest=temphigh;
				}
				if(templow<lowest){
					lowest=templow;
				}
			}
			rsv.add((close-lowest)/(highest-lowest)*100);
		}
		double k=50;
		double d=50;
		double j=3*k-2*d;
		
		KDJ.add(new RSV(pos.get(8).getDate(),k,d,j));
		for(int i=0;i<rsv.size();i++){
			k=2/3*k+1/3*rsv.get(i);
			d=2/3*d+1/3*k;
			j=3*k-2*d;
			KDJ.add(new RSV(pos.get(i+9).getDate(),k,d,j));
		}
		return KDJ;
	}

	//获得N日的移动平均价
		private double MA(String code,String starttime,long time){
			double result=0;
			String rangestart=DateServ.getDateBefore(time, starttime);
			List<SpecificInfoPO> pos=specificInfoDataService.getInfoByRange(code, rangestart, starttime);
			for(SpecificInfoPO po:pos){
				result+=Double.parseDouble(po.getClose());
			}
			result/=pos.size();
			return result;
		}



}
