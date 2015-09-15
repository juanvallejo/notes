package CarAndHouse;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeclarativeLayout extends Application {

	@Override
	public void start(Stage primaryStage) {
		// Root and Scene layed out according to FXML
		Parent root;
		try {
			/*
			 * I think that the Eclipse build process copies this over from src to bin.
			 * getClass().getResource() returns the path to bin, and "/Declarative..." appends
			 * that filename to it. Note that this fails without "/...".		 
			 */
			root = FXMLLoader.load(getClass().getResource(
					"/DeclarativeLayout.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error with layout file");
			return;
		}
		Scene scene = new Scene(root, 480, 640);
		scene.getStylesheets().add("DeclarativeStyle.css");

		// Event Handling using CSS-Style Selectors
		Button hello = (Button) root.lookup("#hello");
		Button javafx = (Button) root.lookup("#javafx");
		Button whats = (Button) root.lookup("#whats");
		Button up = (Button) root.lookup("#up");
		hello.setOnAction((e) -> System.out.println("Hello!"));
		javafx.setOnAction((e) -> System.out.println("JavaFX"));
		whats.setOnAction((e) -> System.out.println("What's"));
		up.setOnAction((e) -> System.out.println("zzzzuuuupppp?????"));
		
		// Set the stage and go
		primaryStage.setScene(scene);
		primaryStage.setTitle("User Login");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
