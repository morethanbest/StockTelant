package logicService;

import java.util.List;

import vo.BenchmarkVO;
import vo.ConcreteInfoVO;
import vo.OriginInfoVO;

public interface ChosenStockBLService {
	
	public BenchmarkVO getBenchmark(String date); //根据日期得到大盘具体数据
	
	public List<BenchmarkVO> getBenchmarkList(String begindate,String enddate);//根据起始日期得到大盘数据列表
	
	public	List<OriginInfoVO> getChosenList();	//得到选股的最新信息列表
	
	public List<String> getIndustry();// get all industry catagories
	
	public List<String> getSpecificFirm(String industry);       // get all selected firms in one industry
	
	public List<String> getAllCodes();
}
