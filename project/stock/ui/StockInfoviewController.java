package ui;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Date.DateServ;
import controller.GraphController;
import controller.StatisticsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import ui.KLine.CandleStickChart;
import vo.DealVO;
import vo.KVO;

public class StockInfoviewController {

	// Reference to the main application.
	private MainApp mainApp;
	@FXML
	private Label startLabel;
	@FXML
	private Label endLabel;
	@FXML
	private BorderPane borderPane;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private LineChart<String, Double> lineChart;
	@FXML
	private CategoryAxis xAxis;
	private ObservableList<String> days = FXCollections.observableArrayList();
	@FXML
	private Label nameLabel;
	@FXML
	private Label codeLabel;
	@FXML
	private Label ARLabel;
	@FXML
	private Label BRLabel;
	@FXML
	private Label WMSLabel;
	private String code;
	private String name;
	@FXML
	private DatePicker start;
	@FXML
	private DatePicker end;
	private KType p;

	private String from;

    private String starthistory;
    private String endhistory;

	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void init(String c, String n) {
//		if(c == "hs300"){
//			System.out.print(c);
//			mainApp.showCollectionview();
//		}
		codeLabel.setText(c);
		nameLabel.setText(n);
		code = c;
		name = n;
		initDatePicker(start, LocalDate.ofEpochDay(LocalDate.now().toEpochDay() - 60));
		initDatePicker(end, LocalDate.now());
		starthistory=start.getValue().toString();
		endhistory=end.getValue().toString();
		StatisticsController statisticsController = new StatisticsController();
		DecimalFormat df = new DecimalFormat("######0.00");
		ARLabel.setText(df.format(statisticsController.getAR(code, start.getValue().toString(), end.getValue().toString())));
		BRLabel.setText(df.format(statisticsController.getBR(code, start.getValue().toString(), end.getValue().toString())));
		WMSLabel.setText(df.format(statisticsController.getWMS(code, start.getValue().toString(), end.getValue().toString())));
		p = KType.DAY;
		ShowKmap(KType.DAY);
	}

	@FXML
	private void showDayK() {
		p = KType.DAY;
		ShowKmap(KType.DAY);
	}

	@FXML
	private void showWeekK() {
		p = KType.WEEK;
		ShowKmap(KType.WEEK);
	}

	@FXML
	private void showMonthK() {
		p = KType.MONTH;
		ShowKmap(KType.MONTH);
	}

	@FXML
	private void dateChanged() {
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
		if (p == KType.deals)
			ShowLinechart();
		else if(p == KType.avgind){
			ShowLinechart2();
		}else if(p == KType.analysis){
			ShowAnalysis();
		}else if(p == KType.Info){
			ShowInfo();
		}else{
			ShowKmap(p);
		}

		StatisticsController statisticsController = new StatisticsController();
		DecimalFormat df = new DecimalFormat("######0.00");
		ARLabel.setText(df.format(statisticsController.getAR(code, start.getValue().toString(), end.getValue().toString())));
		BRLabel.setText(df.format(statisticsController.getBR(code, start.getValue().toString(), end.getValue().toString())));
		WMSLabel.setText(df.format(statisticsController.getWMS(code, start.getValue().toString(), end.getValue().toString())));

	}

	private void ShowKmap(KType p) {
		startLabel.setVisible(true);
        endLabel.setVisible(true);
        start.setVisible(true);
        end.setVisible(true);
        StatisticsController statisticsController = new StatisticsController();
		DecimalFormat df = new DecimalFormat("######0.00");
		ARLabel.setText(df.format(statisticsController.getAR(code, start.getValue().toString(), end.getValue().toString())));
		BRLabel.setText(df.format(statisticsController.getBR(code, start.getValue().toString(), end.getValue().toString())));
		WMSLabel.setText(df.format(statisticsController.getWMS(code, start.getValue().toString(), end.getValue().toString())));
		GraphController graphController = new GraphController();
		List<KVO> listkvo;
		if (p == KType.DAY)
			listkvo = graphController.getKdate(code, start.getValue().toString(), end.getValue().toString());
		else if (p == KType.WEEK)
			listkvo = graphController.getWeekKdate(code, start.getValue().toString(), end.getValue().toString());
		else if (p == KType.MONTH)
			listkvo = graphController.getMonthKdate(code, start.getValue().toString(), end.getValue().toString());
		else
			listkvo = new ArrayList<KVO>();
		int length = listkvo.size();

		double[][] data = new double[length][6];
		double ymin = 0;
		double ymax = 0;
		for (int i = 0; i < length; i++) {
			data[i][0] = (double) (i + 1);
			data[i][1] = listkvo.get(i).getStart();
			data[i][2] = listkvo.get(i).getEnd();
			data[i][3] = listkvo.get(i).getHigh();
			data[i][4] = listkvo.get(i).getLow();
			data[i][5] = (data[i][3] + data[i][4]) / 2;
			if (ymin > data[i][4] || ymin == 0) {
				ymin = data[i][4];
			}
			if (ymax < data[i][3]) {
				ymax = data[i][3];
			}
		}

		KLine kLine = new KLine();
		kLine.setdata(data, ymin, ymax, length, start.getValue(), end.getValue(), p);
		CandleStickChart candleStickChart = kLine.createChart();
		candleStickChart.setPrefSize(940, 450);
		borderPane.setCenter(candleStickChart);
	}

	@FXML
	private void ShowLinechart() {
		startLabel.setVisible(true);
        endLabel.setVisible(true);
        start.setVisible(true);
        end.setVisible(true);
        StatisticsController statisticsController = new StatisticsController();
		DecimalFormat df = new DecimalFormat("######0.00");
		ARLabel.setText(df.format(statisticsController.getAR(code, start.getValue().toString(), end.getValue().toString())));
		BRLabel.setText(df.format(statisticsController.getBR(code, start.getValue().toString(), end.getValue().toString())));
		WMSLabel.setText(df.format(statisticsController.getWMS(code, start.getValue().toString(), end.getValue().toString())));
		yAxis.setLabel("成      交      量");
		p = KType.deals;
		GraphController graphController = new GraphController();
		List<DealVO> list = graphController.getDealVO(code, start.getValue().toString(), end.getValue().toString());
		int length = list.size();
		double[] deals = new double[length];
		for (int i = 0; i < length; i++) {
			deals[i] = list.get(i).getDeal();
		}
		ArrayList<String> day = new ArrayList<String>();
		for (int i = 1; i <= length; i++) {
			day.add(Integer.toString(i));
		}
		days.setAll(day);
		xAxis.setCategories(days);
		XYChart.Series<String, Double> series = new XYChart.Series<>();
		series.setName("成交量");
		for (int i = 0; i < days.size(); i++) {
			series.getData().add(new XYChart.Data<>(days.get(i), deals[i]));
		}
		lineChart.getData().clear();
		lineChart.getData().add(series);
		borderPane.setCenter(lineChart);
	}
	@FXML
	private void ShowLinechart2() {
		startLabel.setVisible(true);
        endLabel.setVisible(true);
        start.setVisible(true);
        end.setVisible(true);
        StatisticsController statisticsController = new StatisticsController();
		DecimalFormat df = new DecimalFormat("######0.00");
		ARLabel.setText(df.format(statisticsController.getAR(code, start.getValue().toString(), end.getValue().toString())));
		BRLabel.setText(df.format(statisticsController.getBR(code, start.getValue().toString(), end.getValue().toString())));
		WMSLabel.setText(df.format(statisticsController.getWMS(code, start.getValue().toString(), end.getValue().toString())));
		yAxis.setLabel("");
		p = KType.avgind;
		List<Double> list = statisticsController.getATRS(code,start.getValue().toString(), end.getValue().toString());
		int length = list.size();
		double[] deals = new double[length];
		for(int i=0;i<length;i++){
			deals[i]=list.get(i);
		}
		ArrayList<String> day = new ArrayList<String>();
		for(int i=1;i<=length;i++){
			day.add(Integer.toString(i));
		}
		days.setAll(day);
		xAxis.setCategories(days);
		XYChart.Series<String, Double> series = new XYChart.Series<>();
		series.setName("ATR");
		for (int i = 0; i < days.size(); i++) {
            series.getData().add(new XYChart.Data<>(days.get(i), deals[i]));
        }
		lineChart.getData().clear();
        lineChart.getData().add(series);
        borderPane.setCenter(lineChart);
	}
	@FXML
	private void ShowAnalysis() {
        startLabel.setVisible(false);
        endLabel.setVisible(false);
        start.setVisible(false);
        end.setVisible(false);
        StatisticsController statisticsController=new StatisticsController();
        DecimalFormat df = new DecimalFormat("######0.00");
        ARLabel.setText(df.format(statisticsController.getAR(code, 26)));
		BRLabel.setText(df.format(statisticsController.getBR(code, 26)));
		WMSLabel.setText(df.format(statisticsController.getWMS(code, 14)));
		try {
			File file=new File("fxml/Analysisview.fxml");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(file.toURI().toURL());
			AnchorPane analysisview = (AnchorPane) loader.load();

			AnalysisController controller = loader.getController();
			controller.init(code);
			borderPane.setCenter(analysisview);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private void handleBack() {
		if(from.equals("fromCollection"))
			mainApp.showCollectionview();
		else
			mainApp.showProfessionInfo(from);
	}

	@FXML
	private void ShowKang() {
		mainApp.showBenchmarkDialog(code);
	}

	@FXML
	private void ShowInfo() {
		p = KType.Info;
		startLabel.setVisible(true);
        endLabel.setVisible(true);
        start.setVisible(true);
        end.setVisible(true);
        StatisticsController statisticsController = new StatisticsController();
		DecimalFormat df = new DecimalFormat("######0.00");
		ARLabel.setText(df.format(statisticsController.getAR(code, start.getValue().toString(), end.getValue().toString())));
		BRLabel.setText(df.format(statisticsController.getBR(code, start.getValue().toString(), end.getValue().toString())));
		WMSLabel.setText(df.format(statisticsController.getWMS(code, start.getValue().toString(), end.getValue().toString())));
		try {
			File file=new File("fxml/StockInfoTable.fxml");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(file.toURI().toURL());
			AnchorPane tableview = (AnchorPane) loader.load();

			StockInfoTableController controller = loader.getController();
			System.out.println(start.getValue().toString());
			controller.setTime(start.getValue().toString(), end.getValue().toString(), codeLabel.getText());
			controller.init();
			borderPane.setCenter(tableview);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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