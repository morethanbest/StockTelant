package ui;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import Date.DateServ;
import controller.ChosenStockController;
import controller.GraphController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import vo.DealPieVO;

public class StockProfessionController {

	// Reference to the main application.
	private MainApp mainApp;
	private List<String> plist;

	@FXML
	private PieChart pc;
	@FXML
	private Button p1;
	@FXML
	private Button p2;
	@FXML
	private Button p3;
	@FXML
	private Button p4;
	@FXML
	private Button p5;
	@FXML
	private Button p6;
	@FXML
	private DatePicker start;

	@FXML
	private DatePicker end;
    private String starthistory;
    private String endhistory;

	public void init() {
		ChosenStockController c = new ChosenStockController();
		plist = c.getIndustry();
		initButtons();
		initDatePicker(start, LocalDate.ofEpochDay(LocalDate.now().toEpochDay() - 30));
		initDatePicker(end, LocalDate.now());
		starthistory=start.getValue().toString();
		endhistory=end.getValue().toString();
		showPieChart();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void showPieChart() {
		String nowdate = DateServ.getNowDate();
		if(end.getValue().toString().compareTo(nowdate)>0){
			String[] splits = endhistory.split("-");
			end.setValue(LocalDate.of(Integer.parseInt(splits[0]), Integer.parseInt(splits[1]), Integer.parseInt(splits[2])));
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("警告！");
	    	alert.setHeaderText("请不要将截止日期超过今天");

	    	alert.showAndWait();
	    	return ;
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
	    	return ;
		}
		starthistory=start.getValue().toString();
		endhistory=end.getValue().toString();
		GraphController c = new GraphController();
		List<DealPieVO> l = c.getIndustryPie(start.getValue().toString(), end.getValue().toString());
		ObservableList<PieChart.Data> ol = FXCollections.observableArrayList(

				new PieChart.Data(l.get(0).getName(), l.get(0).getPercentage()),

				new PieChart.Data(l.get(1).getName(), l.get(1).getPercentage()),

				new PieChart.Data(l.get(2).getName(), l.get(2).getPercentage()),

				new PieChart.Data(l.get(3).getName(), l.get(3).getPercentage()),

				new PieChart.Data(l.get(4).getName(), l.get(4).getPercentage()),

				new PieChart.Data(l.get(5).getName(), l.get(5).getPercentage())

		);
		pc.setData(ol);
		for (final PieChart.Data data : ol) {
			String origin = data.getName();
			data.getNode().setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					DecimalFormat df = new java.text.DecimalFormat("#.00");
					data.setName(origin + " " +df.format(data.getPieValue() * 100)+"%");
				}
			});
			data.getNode().setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					data.setName(origin);
				}
			});
			data.getNode().setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					mainApp.showProfessionInfo(origin.trim());
				}
			});
		}
		pc.setLabelLineLength(15);
		pc.setLabelsVisible(true);

	}

	private void initButtons() {
		p1.setText(plist.get(0));
		p2.setText(plist.get(1));
		p3.setText(plist.get(2));
		p4.setText(plist.get(3));
		p5.setText(plist.get(4));
		p6.setText(plist.get(5));
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

	@FXML
	private void handleP1() {
		mainApp.showProfessionInfo(plist.get(0));
	}

	@FXML
	private void handleP2() {
		mainApp.showProfessionInfo(plist.get(1));
	}

	@FXML
	private void handleP3() {
		mainApp.showProfessionInfo(plist.get(2));
	}

	@FXML
	private void handleP4() {
		mainApp.showProfessionInfo(plist.get(3));
	}

	@FXML
	private void handleP5() {
		mainApp.showProfessionInfo(plist.get(4));
	}

	@FXML
	private void handleP6() {
		mainApp.showProfessionInfo(plist.get(5));
	}

	@FXML
	private void handleBack() {
		mainApp.showCollectionview();
	}
}
