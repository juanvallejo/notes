package CarAndHouse;

import javafx.application.Application;
import javafx.stage.Stage;

public class PropertyPlayground extends Application {

	@Override
	public void start(Stage primaryStage) {
		BeanClass bean = new BeanClass();
		bean.setText("foo");
		System.out.println(bean.getText());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
