package CarAndHouse;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleBind extends Application {

	@Override
	public void start(Stage primaryStage) {
		Circle	circle = new Circle();
		circle.setFill(Color.BLUE);
		Group root = new Group(circle);
		Scene scene = new Scene(root, 800, 600);
		// Bind means "depends on", not "determines"
		circle.radiusProperty().bind(Bindings.divide(scene.heightProperty(),2));
		circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(),2));
		circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(),2));
				
		primaryStage.setScene(scene);
		primaryStage.setTitle("Shipping / Billing");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
