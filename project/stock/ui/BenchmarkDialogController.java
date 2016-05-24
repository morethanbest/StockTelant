package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.scene.control.skin.FXVK.Type;

import Date.DateServ;
import controller.ChosenStockController;
import controller.GraphController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ui.KLine.CandleStickChart;
import vo.BenchmarkVO;
import vo.KVO;

public class BenchmarkDialogController {
    @FXML
    private BorderPane borderPane;
    @FXML
    private BorderPane borderPaneB;
	@FXML
	private DatePicker start;
	@FXML
	private DatePicker end;
	private KType p;
    private Stage dialogStage;
    private String code;
    private String starthistory;
    private String endhistory;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */

    public void initialize(String code) {
    	this.code = code;
		initDatePicker(start, LocalDate.ofEpochDay(LocalDate.now().toEpochDay()-60));
		initDatePicker(end, LocalDate.now());
		starthistory=start.getValue().toString();
		endhistory=end.getValue().toString();
		p = KType.DAY;
    	showBenchKmap(p);
    	showKmap(p);
    }
    @FXML
    private void showDayK(){
    	p = KType.DAY;
    	showKmap(KType.DAY);
    	showBenchKmap(KType.DAY);
    }
    @FXML
    private void showWeekK(){
    	p = KType.WEEK;
    	showKmap(KType.WEEK);
    	showBenchKmap(KType.WEEK);
    }
    @FXML
    private void showMonthK(){
    	p = KType.MONTH;
    	showKmap(KType.MONTH);
    	showBenchKmap(KType.MONTH);
    }
    public void refresh(){
    	String nowdate = DateServ.getNowDate();
		if(end.getValue().toString().compareTo(nowdate)>0){
			String[] splits = endhistory.split("-");
			end.setValue(LocalDate.of(Integer.parseInt(splits[0]), Integer.parseInt(splits[1]), Integer.parseInt(splits[2])));
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("警告！");
	    	alert.setHeaderText("请不要将截止日期超过今天");

	    	alert.showAndWait();
		}
		if(start.getValue().toString().compareTo(end.getValue().toString())>=0){
			String[] endsplits = endhistory.split("-");
			String[] startsplits = starthistory.split("-");
			end.setValue(LocalDate.of(Integer.parseInt(endsplits[0]), Integer.parseInt(endsplits[1]), Integer.parseInt(endsplits[2])));
			start.setValue(LocalDate.of(Integer.parseInt(startsplits[0]), Integer.parseInt(startsplits[1]), Integer.parseInt(startsplits[2])));
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("警告！");
	    	alert.setHeaderText("请不要将截止日期早于或等于开始日期");

	    	alert.showAndWait();
		}
		starthistory=start.getValue().toString();
		endhistory=end.getValue().toString();
    	showKmap(p);
    	showBenchKmap(p);
    }
    public void showKmap(KType p){
    	GraphController graphController = new GraphController();
		List<KVO> listkvo;
		if(p == KType.DAY)
			listkvo = graphController.getKdate(code,start.getValue().toString(), end.getValue().toString());
		else if(p == KType.WEEK)
			listkvo = graphController.getWeekKdate(code,start.getValue().toString(), end.getValue().toString());
		else if(p == KType.MONTH)
			listkvo = graphController.getMonthKdate(code,start.getValue().toString(), end.getValue().toString());
		else
			listkvo = new ArrayList<KVO>();
		int length = listkvo.size();

		double[][] data = new double[length][6];
        double ymin = 0;
        double ymax = 0;
		for(int i=0;i<length;i++){
			data[i][0] = (double)(i+1);
			data[i][1] = listkvo.get(i).getStart();
			data[i][2] = listkvo.get(i).getEnd();
			data[i][3] = listkvo.get(i).getHigh();
			data[i][4] = listkvo.get(i).getLow();
			data[i][5] = (data[i][3]+data[i][4])/2;
			if(ymin>data[i][4]||ymin == 0){
				ymin = data[i][4];
			}
			if(ymax<data[i][3]){
				ymax = data[i][3];
			}
		}

		KLine kLine = new KLine();
	    kLine.setdata(data, ymin, ymax, length, start.getValue(), end.getValue(), p);
	    CandleStickChart candleStickChart = kLine.createChart();
	    candleStickChart.setPrefSize(940, 450);
	    borderPane.setCenter(candleStickChart);
    }
    public void showBenchKmap(KType p){
    	GraphController graphController = new GraphController();
		List<KVO> listkvo;
		if(p == KType.DAY){
			listkvo = graphController.getBenchmarkKdate(start.getValue().toString(), end.getValue().toString());
		}else if(p==KType.WEEK){
			listkvo = graphController.getBenchmarkWeekKdate(start.getValue().toString(), end.getValue().toString());
		}else{
			listkvo = graphController.getBenchmarkMonthKdate(start.getValue().toString(), end.getValue().toString());
		}

		int length = listkvo.size();

		double[][] data = new double[length][6];
        double ymin = 0;
        double ymax = 0;
		for(int i=0;i<length;i++){
			data[i][0] = (double)(i+1);
			data[i][1] = listkvo.get(i).getStart();
			data[i][2] = listkvo.get(i).getEnd();
			data[i][3] = listkvo.get(i).getHigh();
			data[i][4] = listkvo.get(i).getLow();
			data[i][5] = (data[i][3]+data[i][4])/2;
			if(ymin>data[i][4]||ymin == 0){
				ymin = data[i][4];
			}
			if(ymax<data[i][3]){
				ymax = data[i][3];
			}
		}

		KLine kLine = new KLine();
	    kLine.setdata(data, ymin, ymax, length, start.getValue(), end.getValue(), p);
	    CandleStickChart candleStickChart = kLine.createChart();
	    candleStickChart.setPrefSize(940, 450);
	    borderPaneB.setCenter(candleStickChart);
    }
    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    private void initDatePicker(DatePicker picker, LocalDate date) {
		String pattern = "yyyy-MM-dd";
		picker.setConverter(new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		});
		picker.setValue(date);
	}
}
