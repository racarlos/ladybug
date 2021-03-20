package application;
	
import javafx.application.Application;
import javafx.stage.Stage;

// Main Class
public class Main extends Application {

	
	public static void main(String[] args) {
		launch();
	}
	
	public void start(Stage stage){
		MainMenu actual = new MainMenu();
		actual.setStage(stage);
	}
}
