package ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.Buffer;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");

		this.primaryStage.getIcons().add(new Image("file:1458143574_2.png"));

		initRootLayout();

		showCollectionview();
	}

	public static void main(String[] args) {
		launch(args);
		Buffer buffer=new Buffer();
		buffer.run();
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			File file=new File("fxml/RootLayout.fxml");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(file.toURI().toURL());
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
			scene.getStylesheets().add(this.getClass().getResource("chart.css").toExternalForm());
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showCollectionview() {
		try {
			// Load person overview.
			File file=new File("fxml/Collectionview.fxml");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(file.toURI().toURL());
			AnchorPane collectionview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(collectionview);

			// Give the controller access to the main app.
			CollectionviewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showStockProfessionview() {
		try {
			File file=new File("fxml/Stockprofessionview.fxml");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(file.toURI().toURL());
			AnchorPane stockprofessionview = (AnchorPane) loader.load();

			StockProfessionController controller = loader.getController();
			controller.setMainApp(this);
			controller.init();
			rootLayout.setCenter(stockprofessionview);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showProfessionInfo(String p) {
		try {
			File file=new File("fxml/ProfessionInfoview.fxml");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(file.toURI().toURL());
			AnchorPane professioninfoview = (AnchorPane) loader.load();

			ProfessionInfoController controller = loader.getController();
			controller.setMainApp(this);
			controller.init(p);
			rootLayout.setCenter(professioninfoview);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showStockInfo(String c, String n, String from) {
		try {
			File file=new File("fxml/StockInfoView.fxml");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(file.toURI().toURL());
			AnchorPane stockinfoview = (AnchorPane) loader.load();

			StockInfoviewController controller = loader.getController();
			controller.setFrom(from);
			controller.setMainApp(this);
			controller.init(c, n);
			rootLayout.setCenter(stockinfoview);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
	    	Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("警告！");
	    	alert.setHeaderText("大盘数据无详细信息");
	    	alert.setContentText("请选择其他股票以查看详细信息");

	    	alert.showAndWait();
		}
	}

	public void showStockMarketview() {
		// TODO Auto-generated method stub

	}
	public void showBenchmarkDialog(String code) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	    	File file=new File("fxml/BenchmarkDialog.fxml");
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(file.toURI().toURL());
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Benchmark Contrast");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        BenchmarkDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.initialize(code);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
