package dataService;

import java.util.List;

import po.BenchmarkPO;
import po.SimpleInfoPO;
import po.SpecificInfoPO;

public interface ChosenStockService {

	public BenchmarkPO getBenchmark(String date); //根据日期得到大盘具体数据  传入那天的日期
	
	public List<BenchmarkPO> getBenchmarkList(String begindate,String enddate);//根据起始日期得到大盘数据列表
	
	public	List<SimpleInfoPO> getChosenList();	//得到选股的最新信息列表
	
	public List<String> getIndustry();// get all industry catagories
	
	public List<String> getSpecificFirm(String industry);       // get all selected firms in one industry
	
	public List<String> getSpecificFirmCode(String industry);
}
