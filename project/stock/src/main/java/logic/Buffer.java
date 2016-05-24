package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Date.DateServ;
import data.ChosenStockData;
import dataService.ChosenStockService;
import po.SimpleInfoPO;
import vo.Exchange;

public class Buffer implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		File file=new File("data/"+DateServ.getDate()+".txt");
		if(file.isFile()&&file.exists()){
			
		}else{
			File directory=new File("data");
			File[] files=directory.listFiles();
			for(int i=0;i<files.length;i++){
				files[i].delete();
			}
//			SimpleInfoDataService service=new SimpleInfoData();
//			List<SimpleInfoPO> poList=service.getOriginList();
			ChosenStockService service=new ChosenStockData();
			List<SimpleInfoPO> poList=service.getChosenList();
			Serialize.writeObjectToFile(poList);
		}
			

	}
	
	public List<SimpleInfoPO> getInfoByCity(long start, long end, Exchange exchange) {
		List<SimpleInfoPO> polist=new ArrayList<>();
		List<SimpleInfoPO> localpolist=Serialize.readObjectFromFile();
		List<Integer> index=new ArrayList<>();
		if(exchange==Exchange.sh){
			for(int i=0;i<localpolist.size();i++){
				if(localpolist.get(i).getCode().contains("sh")){
					index.add(i);
				}
			}
		}else if(exchange==Exchange.both){
			for(int i=0;i<localpolist.size();i++){
					index.add(i);
			}
		}else if(exchange==Exchange.sz){
			for(int i=0;i<localpolist.size();i++){
				if(localpolist.get(i).getCode().contains("sz")){
					index.add(i);
				}
			}
		}
		for(int i=(int)start-1;i<end&&i<index.size();i++){
			polist.add(localpolist.get(index.get(i)));
		}
		return polist;
	}
	
	public long getNumByCity(Exchange exchange) {
		long counter=0;
		List<SimpleInfoPO> localpolist=Serialize.readObjectFromFile();
		if(exchange==Exchange.sh){
			for(int i=0;i<localpolist.size();i++){
				if(localpolist.get(i).getCode().contains("sh")){
					counter++;
				}
			}
		}else if(exchange==Exchange.sz){
			for(int i=0;i<localpolist.size();i++){
				if(localpolist.get(i).getCode().contains("sz")){
					counter++;
				}
			}
		}else if(exchange==Exchange.both){
			counter=localpolist.size();
		}
		return counter;
	
	
	}
	
	public List<SimpleInfoPO> getChosenStock(){
		List<SimpleInfoPO> polist=Serialize.readObjectFromFile();
		return polist;
	}


}
