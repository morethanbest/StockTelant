package ui;

import controller.ChosenStockController;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import vo.OriginInfoVO;

public class CollectionviewController {

	// Reference to the main application.
	private MainApp mainApp;

	@FXML
	private TableView<OriginInfoVO> stockTable;

	@FXML
	private TableColumn<OriginInfoVO, String> codeColumn;
	@FXML
	private TableColumn<OriginInfoVO, String> nameColumn;
	@FXML
	private TableColumn<OriginInfoVO, String> dateColumn;
	@FXML
	private TableColumn<OriginInfoVO, Number> openColumn;
	@FXML
	private TableColumn<OriginInfoVO, Number> closeColumn;
	@FXML
	private TableColumn<OriginInfoVO, Number> highColumn;
	@FXML
	private TableColumn<OriginInfoVO, Number> lowColumn;
	@FXML
	private TableColumn<OriginInfoVO, Number> volumeColumn;

	@FXML
	private Button profession;
	@FXML
	private Button marketIndex;

	private ObservableList<OriginInfoVO> personData = FXCollections.observableArrayList();

	public void initialize() {
		ChosenStockController c = new ChosenStockController();
		personData.setAll(c.getChosenList());
		stockTable.setItems(personData);
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		codeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCode()));
		dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
		openColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(Double.parseDouble(cellData.getValue().getOpen())));
		closeColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(Double.parseDouble(cellData.getValue().getClose())));
		highColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(Double.parseDouble(cellData.getValue().getHighest())));
		lowColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(Double.parseDouble(cellData.getValue().getLowest())));
		volumeColumn.setCellValueFactory(cellData -> new SimpleLongProperty(Long.parseLong(cellData.getValue().getVolume())));

			stockTable.getSelectionModel().selectedItemProperty()
					.addListener((observable, oldValue, newValue) -> showStockDetails(newValue));
	}

	private void showStockDetails(OriginInfoVO newValue) {
		if(newValue.getCode().equals("hs300")){

		}else{
			mainApp.showStockInfo(newValue.getCode(), newValue.getName(), "fromCollection");
		}

	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void HandleProfession() {
		mainApp.showStockProfessionview();
	}

	@FXML
	private void HandleMarketIndex() {
		mainApp.showStockMarketview();
	}
}
