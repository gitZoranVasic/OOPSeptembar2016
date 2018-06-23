package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.Baza;
import view.Page1;
import view.Page2;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Baza.getInstance();
		Page1.getInstance();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
