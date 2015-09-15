package CarAndHouse;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProgramLayout extends Application {

	@Override
	public void start(Stage primaryStage) {
		// Buttons do not expand past their natural size
		BorderPane pane = new BorderPane();
		pane.setTop(new Button("Top"));
		pane.setLeft(new Button("Left"));
		pane.setCenter(new Button("Center"));
		pane.setRight(new Button("Right"));
		pane.setBottom(new Button("Bottom"));
		Group root = new Group(pane);
		Scene scene = new Scene(root, 800, 600);
				
		primaryStage.setScene(scene);
		primaryStage.setTitle("Shipping / Billing");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
