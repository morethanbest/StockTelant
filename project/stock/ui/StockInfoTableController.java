package ui;

import controller.APIController;
import controller.ChosenStockController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import vo.ConcreteInfoVO;

public class StockInfoTableController {
	@FXML
	private TableView<ConcreteInfoVO> stockTable;

	@FXML
	private TableColumn<ConcreteInfoVO, String> dateColumn;
	@FXML
	private TableColumn<ConcreteInfoVO, String> openColumn;
	@FXML
	private TableColumn<ConcreteInfoVO, String> closeColumn;
	@FXML
	private TableColumn<ConcreteInfoVO, String> highColumn;
	@FXML
	private TableColumn<ConcreteInfoVO, String> lowColumn;
	@FXML
	private TableColumn<ConcreteInfoVO, String> adjColumn;
	@FXML
	private TableColumn<ConcreteInfoVO, String> volumeColumn;
	@FXML
	private TableColumn<ConcreteInfoVO, String> turnoverColumn;
	@FXML
	private TableColumn<ConcreteInfoVO, String> peColumn;
	@FXML
	private TableColumn<ConcreteInfoVO, String> pbColumn;

	private ObservableList<ConcreteInfoVO> stockData = FXCollections.observableArrayList();

	private String start;
	private String end;
	private String code;

	public void init() {
		APIController c = new APIController();
		stockData.setAll(c.getInfoByRange(start, end, code));
		stockTable.setItems(stockData);
		dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
		openColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpen()));
		closeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClose()));
		highColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHighest()));
		lowColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLowest()));
		adjColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdj_price()));
		volumeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVolume()));
		turnoverColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTurnover()));
		peColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPe()));
		pbColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPb()));

	}

	public void setTime(String start, String end, String code) {
		this.start = start;
		this.end = end;
		this.code = code;

	}

}
