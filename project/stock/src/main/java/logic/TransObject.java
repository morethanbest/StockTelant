package logic;

import java.util.ArrayList;
import java.util.List;

import po.BenchmarkPO;
import po.DealPO;
import po.DealPie;
import po.FirmPO;
import po.KPO;
import po.SimpleInfoPO;
import po.SpecificInfoPO;
import vo.BenchmarkVO;
import vo.ConcreteInfoVO;
import vo.DealPieVO;
import vo.DealVO;
import vo.FirmVO;
import vo.KVO;
import vo.OriginInfoVO;

/**
 * Created by Administrator on 2016/3/2.
 */
public class TransObject {
    public static ConcreteInfoVO transToConcrete(SpecificInfoPO po){
    	ConcreteInfoVO vo=new ConcreteInfoVO(po.getDate(), po.getCode(), po.getName(), po.getOpen(), po.getClose(), po.getHighest(),
    			po.getLowest(), po.getAdj_price(), po.getVolume(), po.getTurnover(), po.getPe(), po.getPb());

    	return vo;
    }


    public static OriginInfoVO transToOrigin(SimpleInfoPO po){
    	OriginInfoVO vo=new OriginInfoVO(po.getDate(),po.getCode(),po.getName(),po.getOpen(),
    			po.getClose(),po.getHighest(),po.getLowest(),po.getVolume());
    	return vo;
    }

    public static List<ConcreteInfoVO> transToConceteList(List<SpecificInfoPO> list){
    	List<ConcreteInfoVO> volist=new ArrayList<ConcreteInfoVO>();
    	for(SpecificInfoPO po:list){
    		volist.add(transToConcrete(po));
    	}
    	return volist;
    }

    public static List<OriginInfoVO>  transToOriginList(List<SimpleInfoPO> list){
    	List<OriginInfoVO> volist=new ArrayList<OriginInfoVO>();
    	for(SimpleInfoPO po:list){
    		volist.add(transToOrigin(po));
    	}
    	return volist;
    }
    
    public static KVO transK(SpecificInfoPO po){
    	double open=Double.parseDouble(po.getOpen());
    	double close=Double.parseDouble(po.getClose());
    	double high=Double.parseDouble(po.getHighest());
    	double low=Double.parseDouble(po.getLowest());
    	double volume=Double.parseDouble(po.getVolume());
    	double average=(high+low)/2;
    	KVO kvo=new KVO(po.getDate(),open,close,high,low,volume,average);
    	return kvo;
    }
    
    public static List<KVO> transKList(List<SpecificInfoPO> pos){
    	List<KVO> kvos=new ArrayList<>();
    	for(SpecificInfoPO po:pos){
    		kvos.add(transK(po));
    	}
    	return kvos;
    }
    
    public static DealVO transDeal(DealPO po){
    	double deal=Double.parseDouble(po.getDeal());
    	DealVO vo=new DealVO(po.getDate(), deal);
    	return vo;
    }
    
    public static List<DealVO> transDealList(List<DealPO> pos){
    	List<DealVO> vos=new ArrayList<>();
    	for(DealPO po:pos){
    		vos.add(transDeal(po));
    	}
    	return vos;
    }
    
    public static BenchmarkVO transBenchmark(BenchmarkPO po){
    	if(po==null){
    		return null;
    	}
    	BenchmarkVO vo=new BenchmarkVO(po.getDate(), po.getCode(), Double.parseDouble(po.getOpen()),
    			Double.parseDouble(po.getClose()),Double.parseDouble( po.getHighest()), Double.parseDouble(po.getLowest())
    			, Double.parseDouble(po.getAdj_price()), Double.parseDouble(po.getVolume()));
    	return vo;
    			
    }
    
    public static List<BenchmarkVO> transBenchList(List<BenchmarkPO> pos){
    	List<BenchmarkVO> volist=new ArrayList<BenchmarkVO>();
    	for(BenchmarkPO po:pos){
    		volist.add(transBenchmark(po));
    	}
    	return volist;
    }
    
    public static List<DealPieVO> transPie(List<DealPie> dealPies){
    	List<DealPieVO> volist=new ArrayList<DealPieVO>();
    	double sum=0;
    	for(DealPie po:dealPies){
    		sum+=Double.parseDouble(po.getNum());
    	}
    	for(DealPie po:dealPies){
    		volist.add(new DealPieVO(po.getName(),Double.parseDouble(po.getNum())/sum,Double.parseDouble(po.getNum()) ));
    	}
    	return volist;
    }
    
    public static FirmVO transFirm(FirmPO po){
    	FirmVO vo=new FirmVO(po.getCode(), po.getDate(), po.getOpen(), po.getHighest(), po.getLowest());
    	return vo;
    }
    
    public static List<FirmVO> transFirmList(List<FirmPO> pos){
    	List<FirmVO> vos=new ArrayList<FirmVO>();
    	for(FirmPO po : pos){
    		vos.add(transFirm(po));
    	}
    	return vos;
    }
    
    public static KVO transBenchmarkK(BenchmarkPO po){
    	double open=Double.parseDouble(po.getOpen());
    	double close=Double.parseDouble(po.getClose());
    	double high=Double.parseDouble(po.getHighest());
    	double low=Double.parseDouble(po.getLowest());
    	double volume=Double.parseDouble(po.getVolume());
    	double average=(high+low)/2;
    	KVO kvo=new KVO(po.getDate(),open,close,high,low,volume,average);
    	return kvo;
    }
    
    public static List<KVO> transBenchmarkKList(List<BenchmarkPO> kpos){
    	List<KVO> kvos=new ArrayList<>();
    	for(BenchmarkPO po:kpos){
    		kvos.add(transBenchmarkK(po));
    	}
    	return kvos;
    }
    

}
