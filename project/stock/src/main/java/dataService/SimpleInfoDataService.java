package dataService;

import java.util.List;

import po.SimpleInfoPO;
import vo.Exchange;
import vo.OriginInfoVO;

/**
 * Created by Administrator on 2016/3/1.
 */
public interface SimpleInfoDataService {

    public List<SimpleInfoPO> getOriginList();
    
    public List<SimpleInfoPO> getInfoByCity(long start,long end,Exchange exchange);
    
    public long getNumByCity(Exchange exchange);
    
    public List<String>  getCodeName(String code,Exchange exchange);
    
    public List<SimpleInfoPO> getSimpleInfoByCode(List<String> code);
    
    
    
}
