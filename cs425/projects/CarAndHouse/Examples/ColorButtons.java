package CarAndHouse;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ColorButtons extends Application {

	@Override
	public void start(Stage primaryStage) {
		// GUI Components
		Button redButton = new Button("Red");
		Button blueButton = new Button("Blue");
		Label message = new Label("Hello! I am impatient!");
		message.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, null, null)));
		Slider slider = new Slider();
		// Layout; there are better ways to do this...
		FlowPane pane = new FlowPane(Orientation.VERTICAL);
		pane.setColumnHalignment(HPos.CENTER);
		pane.getChildren().addAll(redButton, blueButton, message, slider);
		// Events
		redButton.setOnAction(event -> message.setTextFill(Color.RED));
		blueButton.setOnAction(event -> message.setTextFill(Color.BLUE));
		
//		slider.valueProperty().addListener(observable -> message.setFont(new Font(slider.getValue())));
		// OR
		slider.valueProperty().addListener((property, oldvalue, newvalue) 
				-> message.setFont(new Font(newvalue.doubleValue())));
				
		// Set the scene and stage
		primaryStage.setScene(new Scene(pane, 800, 600));
		primaryStage.setTitle("Hello, Impatient");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
