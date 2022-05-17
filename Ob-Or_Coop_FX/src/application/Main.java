package application;
	
import javax.swing.JOptionPane;

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
		try {
			VBox vBox = new VBox();
			GridPane grid = new GridPane();
			

			
			
			vBox.setSpacing(5);
			vBox.setPadding(new Insets(10));
			
			grid.setHgap(5);
			grid.setVgap(5);
			
			Label lblMessage = new Label();

			Label lblName = new Label("Name: ");
			Label lblFavoriteAnimal = new Label("Favorite Animal : ");
			TextField tfEnterName = new TextField();
			ComboBox<String> cmbAnimals = new ComboBox<String>();
			cmbAnimals.getItems().addAll("Cat", "Horse", "Dog");
			CheckBox checkIsFemale = new CheckBox("Is Female");
			Button btnShow = new Button();
			btnShow.setText("Show Details");
			
			btnShow.setOnAction(new EventHandler<ActionEvent>() {
				
				
				@Override
				public void handle(ActionEvent arg0) {
					StringBuffer sBuffer = new StringBuffer();
					sBuffer.append("Your name is " + tfEnterName.getText());
					if(checkIsFemale.isSelected())
						sBuffer.append("(Female)");
					else
						sBuffer.append("(Male)");
					sBuffer.append(", your favorite animal is " + cmbAnimals.getValue());
					
					lblMessage.setText(sBuffer.toString());

					//JOptionPane.showMessageDialog(null, sBuffer );
					
				}
			});
			
			grid.add(lblName, 0, 0);
			grid.add(tfEnterName, 1, 0);
			grid.add(lblFavoriteAnimal, 0, 1);
			grid.add(cmbAnimals, 1, 1);
			grid.add(checkIsFemale, 0, 2);
			grid.add(btnShow, 0, 3);
			
			vBox.getChildren().addAll(grid, lblMessage);
			
			
			Scene scene = new Scene(vBox,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
