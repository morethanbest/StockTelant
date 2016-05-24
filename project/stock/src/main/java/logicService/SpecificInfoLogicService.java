package logicService;

import vo.ConcreteInfoVO;

import java.util.List;

/**
 * Created by Administrator on 2016/3/2.
 */
public interface SpecificInfoLogicService {

    //根据开始时间和结束时间获取详细信息
    public List<ConcreteInfoVO>  getInfoByRange(String starttime,String endtime,String code);
}
