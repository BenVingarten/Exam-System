package application;
	
import javax.swing.JOptionPane;

import application.Controller.Controller;
import application.Model.Manager;
import application.View.UserInterface;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		//TODO
		Manager theModel = new Manager();
		UserInterface userInterface = new UserInterface(primaryStage);
		Controller controller = new Controller(theModel, userInterface);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
