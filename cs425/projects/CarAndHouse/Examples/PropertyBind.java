package CarAndHouse;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PropertyBind extends Application {

	@Override
	public void start(Stage primaryStage) {
		Label shiplabel = new Label("Shipping");
		TextField shiptext = new TextField();
		Label billlabel = new Label("Billing");
		TextField billtext = new TextField();
		VBox root = new VBox(shiplabel, shiptext, billlabel, billtext);
		
		// Bind one property to another
		shiptext.textProperty().bindBidirectional(billtext.textProperty());
		
		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.setTitle("Shipping / Billing");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
