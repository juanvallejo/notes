package CarAndHouse;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Hello extends Application {

	@Override
	public void start(Stage primaryStage) {
		Label message = new Label("Hello! I am impatient!");
		message.setFont(new Font(90));
		primaryStage.setScene(new Scene(message));
		primaryStage.setTitle("Hello, Impatient");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
