package ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import controller.ChosenStockController;
import controller.StatisticsController;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import vo.AnalysisVO;
import vo.OriginInfoVO;

public class AnalysisController {

	@FXML
	private Label summaryAR;
	@FXML
	private Label summaryBR;
	@FXML
	private Label summaryWMS;
	@FXML
	private Label AR1;
	@FXML
	private Label AR2;
	@FXML
	private Label AR3;
	@FXML
	private Label BR1;
	@FXML
	private Label BR2;
	@FXML
	private Label BR3;
	@FXML
	private Label WMS1;
	@FXML
	private Label WMS2;
	@FXML
	private Label WMS3;
	@FXML
	private TableView<AnalysisVO> stockTable;
	@FXML
	private TableColumn<AnalysisVO, String> codeColumn;
	@FXML
	private TableColumn<AnalysisVO, String> nameColumn;
	@FXML
	private TableColumn<AnalysisVO, Number> ARColumn;
	@FXML
	private TableColumn<AnalysisVO, Number> BRColumn;
	@FXML
	private TableColumn<AnalysisVO, Number> WMSColumn;

	private ObservableList<AnalysisVO> personData = FXCollections.observableArrayList();
	private List<AnalysisVO> list;

	public void init(String code) {
		ChosenStockController c = new ChosenStockController();
		List<OriginInfoVO> listo = c.getChosenList();
		list = new ArrayList<AnalysisVO>();
		StatisticsController sc = new StatisticsController();
		DecimalFormat df = new DecimalFormat("######0.00");
		int numAR1 = 0;
		int numAR2 = 0;
		int numAR3 = 0;
		int numBR1 = 0;
		int numBR2 = 0;
		int numBR3 = 0;
		int numWMS1 = 0;
		int numWMS2 = 0;
		int numWMS3 = 0;
		for (int i = 1; i < listo.size(); i++) {
			String vocode = listo.get(i).getCode();
			String voname = listo.get(i).getName();
			double ar = sc.getAR(vocode, 26);
			double br = sc.getBR(vocode, 26);
			double wms = sc.getWMS(vocode, 14);
			String AR = df.format(ar);
			String BR = df.format(br);
			String WMS = df.format(wms);
			AnalysisVO vo = new AnalysisVO(vocode, voname, AR, BR, WMS);
			list.add(vo);
			if (ar > 120) {
				numAR1++;
			} else if (ar < 80) {
				numAR3++;
			} else {
				numAR2++;
			}
			if (br > 150) {
				numBR1++;
			} else if (br < 70) {
				numBR3++;
			} else {
				numBR2++;
			}
			if (wms > 80) {
				numWMS1++;
			} else if (wms < 20) {
				numWMS3++;
			} else {
				numWMS2++;
			}
		}
		personData.setAll(list);
		stockTable.setItems(personData);
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		nameColumn.setCellFactory(new Callback<TableColumn<AnalysisVO, String>, TableCell<AnalysisVO, String>>() {
			@Override
			public TableCell<AnalysisVO, String> call(TableColumn<AnalysisVO, String> param) {
				TableCell<AnalysisVO, String> cell = new TableCell<AnalysisVO, String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						if (item == getItem())
							return;

						super.updateItem(item, empty);

						if (item == null) {
							super.setText(null);
							super.setGraphic(null);
						} else {
							super.setText(item.toString());
							super.setGraphic(null);
						}
						AnalysisVO nowVO = new AnalysisVO("", "", "","", "");
						for (AnalysisVO vo : list) {
							if(vo.getCode().equals(code)){
								nowVO = vo;
							}
						}
						if(nowVO.getName().equals(item)){
							setBackground(new Background(
									new BackgroundFill(Color.web("0xFF0000", 0.1), CornerRadii.EMPTY, Insets.EMPTY)));
							setFont(Font.font("System", FontWeight.BOLD, 15));
						}else{
							setBackground(new Background(
									new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
							setFont(Font.getDefault());
						}
					}
				};

				return cell;
			}
		});
		codeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCode()));
		codeColumn.setCellFactory(new Callback<TableColumn<AnalysisVO, String>, TableCell<AnalysisVO, String>>() {
			@Override
			public TableCell<AnalysisVO, String> call(TableColumn<AnalysisVO, String> param) {
				TableCell<AnalysisVO, String> cell = new TableCell<AnalysisVO, String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						if (item == getItem())
							return;

						super.updateItem(item, empty);

						if (item == null) {
							super.setText(null);
							super.setGraphic(null);
						} else {
							super.setText(item.toString());
							super.setGraphic(null);
						}
						AnalysisVO nowVO = new AnalysisVO("", "", "","", "");
						for (AnalysisVO vo : list) {
							if(vo.getCode().equals(code)){
								nowVO = vo;
							}
						}
						if(nowVO.getCode().equals(item)){
							setBackground(new Background(
									new BackgroundFill(Color.web("0xFF0000", 0.1), CornerRadii.EMPTY, Insets.EMPTY)));
							setFont(Font.font("System", FontWeight.BOLD, 15));
						}else{
							setBackground(new Background(
									new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
							setFont(Font.getDefault());
						}
					}
				};

				return cell;
			}
		});
		ARColumn.setCellValueFactory(
				cellData -> new SimpleDoubleProperty(Double.parseDouble(cellData.getValue().getAR())));
		ARColumn.setCellFactory(new Callback<TableColumn<AnalysisVO, Number>, TableCell<AnalysisVO, Number>>() {
			@Override
			public TableCell<AnalysisVO, Number> call(TableColumn<AnalysisVO, Number> param) {
				TableCell<AnalysisVO, Number> cell = new TableCell<AnalysisVO, Number>() {
					@Override
					protected void updateItem(Number item, boolean empty) {
						if (item == getItem())
							return;

						super.updateItem(item, empty);

						if (item == null) {
							super.setText(null);
							super.setGraphic(null);
						} else {
							super.setText(item.toString());
							super.setGraphic(null);
						}
						if(item == null)
							return;
						if(item.doubleValue() > 120){
							setTextFill(Color.RED);
						}else if(item.doubleValue() > 80){
							setTextFill(Color.GREEN);
						}else {
							setTextFill(Color.BLUE);
						}
					}
				};

				return cell;
			}
		});
		BRColumn.setCellValueFactory(
				cellData -> new SimpleDoubleProperty(Double.parseDouble(cellData.getValue().getBR())));
		BRColumn.setCellFactory(new Callback<TableColumn<AnalysisVO, Number>, TableCell<AnalysisVO, Number>>() {
			@Override
			public TableCell<AnalysisVO, Number> call(TableColumn<AnalysisVO, Number> param) {
				TableCell<AnalysisVO, Number> cell = new TableCell<AnalysisVO, Number>() {
					@Override
					protected void updateItem(Number item, boolean empty) {
						if (item == getItem())
							return;

						super.updateItem(item, empty);

						if (item == null) {
							super.setText(null);
							super.setGraphic(null);
						} else {
							super.setText(item.toString());
							super.setGraphic(null);
						}
						if(item == null)
							return;
						if(item.doubleValue() > 150){
							setTextFill(Color.RED);
						}else if(item.doubleValue() > 70){
							setTextFill(Color.GREEN);
						}else {
							setTextFill(Color.BLUE);
						}
					}
				};

				return cell;
			}
		});
		WMSColumn.setCellValueFactory(
				cellData -> new SimpleDoubleProperty(Double.parseDouble(cellData.getValue().getWMS())));
		WMSColumn.setCellFactory(new Callback<TableColumn<AnalysisVO, Number>, TableCell<AnalysisVO, Number>>() {
			@Override
			public TableCell<AnalysisVO, Number> call(TableColumn<AnalysisVO, Number> param) {
				TableCell<AnalysisVO, Number> cell = new TableCell<AnalysisVO, Number>() {
					@Override
					protected void updateItem(Number item, boolean empty) {
						if (item == getItem())
							return;

						super.updateItem(item, empty);

						if (item == null) {
							super.setText(null);
							super.setGraphic(null);
						} else {
							super.setText(item.toString());
							super.setGraphic(null);
						}
						if(item == null)
							return;
						if(item.doubleValue() > 80){
							setTextFill(Color.RED);
						}else if(item.doubleValue() > 20){
							setTextFill(Color.GREEN);
						}else {
							setTextFill(Color.BLUE);
						}
					}
				};

				return cell;
			}
		});
		summaryAR.setText(sc.ARstatistics(sc.getAR(code, 26)));
		summaryBR.setText(sc.BRstatistics(sc.getBR(code, 26)));
		summaryWMS.setText(sc.WMSstatistics(sc.getWMS(code, 14)));
		AR1.setText(df.format((double) numAR1 / 3 * 10) + "%");
		AR2.setText(df.format((double) numAR2 / 3 * 10) + "%");
		AR3.setText(df.format((double) numAR3 / 3 * 10) + "%");
		BR1.setText(df.format((double) numBR1 / 3 * 10) + "%");
		BR2.setText(df.format((double) numBR2 / 3 * 10) + "%");
		BR3.setText(df.format((double) numBR3 / 3 * 10) + "%");
		WMS1.setText(df.format((double) numWMS1 / 3 * 10) + "%");
		WMS2.setText(df.format((double) numWMS2 / 3 * 10) + "%");
		WMS3.setText(df.format((double) numWMS3 / 3 * 10) + "%");
	}



}
