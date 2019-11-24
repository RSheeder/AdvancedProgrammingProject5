import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	
	public void start(Stage stage) {
	BorderPane borderPane = new BorderPane();
	Text instrumentTypeLabel = new Text("Instrument Type: ");
	Text instrumentBrandLabel = new Text("Instrument Brand: ");
	Text maxCostLabel = new Text("Maximum Cost: ");
	Text warehouseLabel = new Text("Warehouse Location: ");
	
	Button submitButton = new Button("Submit Request");
	 submitButton.setOnAction(new EventHandler<ActionEvent>() {
   		 
         @Override
         public void handle(ActionEvent event) {
        	 System.out.println("Submit Button Pressed");
         }
    });
	 
	String instrumentTypes[] = {"InstrumentType 1", "InstrumentType 2"};
	ComboBox instrumentTypeComboBox = new ComboBox(FXCollections.observableArrayList(instrumentTypes));
	
	String instrumentBrands[] = {"InstrumentBrand 1", "InstrumentBrand 2"};
	ComboBox instrumentBrandComboBox = new ComboBox(FXCollections.observableArrayList(instrumentBrands));
	
	String warehouseLocations[] = {"WarehouseLocation 1", "WarehouseLocation 2"};
	ComboBox warehouseLocationsComboBox = new ComboBox(FXCollections.observableArrayList(warehouseLocations));
	
	TextField costTextField = new TextField();
	
	VBox vbox = new VBox(20);
	vbox.setAlignment(Pos.CENTER);
	HBox instrumentTypeHBox = new HBox();
	instrumentTypeHBox.setAlignment(Pos.CENTER);
	instrumentTypeHBox.getChildren().addAll(instrumentTypeLabel, instrumentTypeComboBox);
	
	HBox instrumentBrandHBox = new HBox();
	instrumentBrandHBox.setAlignment(Pos.CENTER);
	instrumentBrandHBox.getChildren().addAll(instrumentBrandLabel, instrumentBrandComboBox);
	
	HBox costHBox = new HBox();
	costHBox.setAlignment(Pos.CENTER);
	costHBox.getChildren().addAll(maxCostLabel, costTextField);
	
	HBox warehouseHBox = new HBox();
	warehouseHBox.setAlignment(Pos.CENTER);
	warehouseHBox.getChildren().addAll(warehouseLabel, warehouseLocationsComboBox);
	
	HBox submitHBox = new HBox();
	submitHBox.setAlignment(Pos.CENTER);
	submitHBox.getChildren().addAll(submitButton);
	
	vbox.getChildren().addAll(instrumentTypeHBox, instrumentBrandHBox, costHBox, warehouseHBox, submitHBox);
	
	borderPane.setCenter(vbox);
	
	Scene scene = new Scene(borderPane, 400, 350);
	stage.setTitle("ACP Project 5 - Instrument Lookup");
	stage.setScene(scene);
	stage.show();
	}
	
	public static void main(String[] args) {
        Application.launch(args);
    }
}
