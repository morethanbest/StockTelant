package logicService;

import java.util.List;

import po.SimpleInfoPO;
import vo.Exchange;
import vo.OriginInfoVO;

/**
 * Created by Administrator on 2016/3/2.
 */
public interface SimpleInfoLogicService {

//    //通过交易所来获取第几到第几个股票的详情
//    public List<OriginInfoVO> getInfoByCity(long start,long end,Exchange exchange);
//    
//    
//    //根据交易所来获取股票总数
//    public long getNumByCity(Exchange exchange);
//    
//    //根据部分代码或名称获取完整的代码+名称
//    public List<String> getCodeName(String code,Exchange exchange);
    
    
	public List<SimpleInfoPO> getOriginList();
}
