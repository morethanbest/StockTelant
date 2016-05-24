package dataService;

import java.util.List;

import po.SpecificInfoPO;

/**
 * Created by Administrator on 2016/3/1.
 */
public interface SpecificInfoDataService {

    

    public List<SpecificInfoPO>  getInfoByName(String code);

    public List<SpecificInfoPO> getInfoByRange(String code,String starttime,String endtime);

    
/*    public List<SpecificInfoPO>  getInfoByUrl(String url);
    */
    

}
