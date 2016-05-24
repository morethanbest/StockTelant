package logic;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.StackInstruction;

import po.SimpleInfoPO;
import po.SpecificInfoPO;
import vo.ConcreteInfoVO;
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
}
