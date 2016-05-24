package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Date.DateServ;
import controller.ChosenStockController;
import controller.StatisticsController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;

public class ProfessionInfoController {
	@FXML
	private Label l;
	@FXML
	private Label l2;

	@FXML
	private BarChart<String, Double> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private ObservableList<String> stockNames = FXCollections.observableArrayList();

	@FXML
	private TableView<String> stockTable;

	@FXML
	private TableColumn<String, String> codeColumn;

	@FXML
	private TableColumn<String, String> nameColumn;

	@FXML
	private DatePicker start;

	@FXML
	private DatePicker end;


	private MainApp mainApp;

	private ObservableList<String> personData = FXCollections.observableArrayList();
    private String starthistory;
    private String endhistory;
	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void init(String p) {
		l.setText(p);
		l2.setText(new StatisticsController().ARstatisticsIntervals());
		ChosenStockController c = new ChosenStockController();
		List<String> l = c.getSpecificFirm(p);
		personData.setAll(l);
		stockTable.setItems(personData);
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().substring(0, 8)));
		codeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().substring(9)));

		stockTable.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showStockDetails(newValue));

		initDatePicker(start, LocalDate.ofEpochDay(LocalDate.now().toEpochDay()-26));
		initDatePicker(end, LocalDate.now());

		starthistory=start.getValue().toString();
		endhistory=end.getValue().toString();

		List<String> s = new ArrayList<String>();
		for(String t : l){
			s.add(t.substring(9));
		}
        // Convert it to a list and add it to our ObservableList of months.
        stockNames.setAll(s);
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(stockNames);

        setStockData();
	}

	private void showStockDetails(String newValue) {
		mainApp.showStockInfo(newValue.substring(0, 8), newValue.substring(9), l.getText());
	}

	@FXML
	private void handleBack() {
		mainApp.showStockProfessionview();
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
    public void setStockData() {
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
    	StatisticsController g = new StatisticsController();
		List<Double> l2 = g.getARs(l.getText(), start.getValue().toString(), end.getValue().toString());

        XYChart.Series<String, Double> series = new XYChart.Series<>();

        for (int i = 0; i < 5; i++) {
            series.getData().add(new XYChart.Data<>(stockNames.get(i), l2.get(i)));
        }

        barChart.getData().setAll(series);



//		barChart.getPlotChildren().add(seriesPath);
    }
}
