package application.View;

import java.util.Vector;
import application.Listeners.UIListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class UserInterface {
	private Vector<UIListener> allListeners = new Vector<UIListener>();
	BorderPane generalBorderPane = new BorderPane();

	private Stage mainStage;
	
	public UserInterface(Stage mainStage) {
		
		this.mainStage = mainStage;
		mainStage.setTitle("Manu");
		
		
		
		Scene generalScene = new Scene(generalBorderPane, 1000, 1000);
		
		
		
    
		
		
		generalBorderPane.setPadding(new Insets(10, 10, 10, 10));
		
        
        generalBorderPane.setTop(menuPane()); //Set top of the window to menu
	
		
		mainStage.setScene(generalScene);
		mainStage.show();

		
	}
	
	
	public Pane menuPane() {
			VBox menu = new VBox();
			HBox line1 = new HBox();
			HBox line2 = new HBox();
			HBox line3 = new HBox();
			Label chooseTask = new Label("Please choose the task you want");
			
			Button presentInfoButton = new Button("Present database and exmas (all Q&A)");
			Button addQuestionButton = new Button("Add Question (to the Exam and or data base");
			Button updateQuestionButton = new Button("Update content of an existing question");
			Button updateAnswerButton = new Button("Update content of an existing answer");
			Button deletAnswerButton = new Button("Delete an answer to an existing question");
			Button createManualExamButton = new Button("Create exam manually");
			Button createAutoExamButton = new Button("Create exam automatically");
			Button duplicateExamButton = new Button("Duplicate Exam");
			Button createAndShowSetButton = new Button("Create and show Set");
			Button ExitButton = new Button("Exit");
			
			
			presentInfoButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
				generalBorderPane.setCenter(presentInfo());
				}
			
			});
			
			ExitButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
				mainStage.close();
				}
			
			});
			
			line1.getChildren().addAll(chooseTask);
			
			line2.getChildren().addAll(presentInfoButton, addQuestionButton, updateQuestionButton,
					updateAnswerButton);
			
			line3.getChildren().addAll( deletAnswerButton, createManualExamButton, createAutoExamButton,
					duplicateExamButton, createAndShowSetButton, ExitButton);
			
			menu.getChildren().addAll(line1, line2, line3);
			
			
			menu.setPadding(new Insets(10, 10, 10, 10));
			menu.setMaxHeight(300);
			
			
			
		return menu;
			 
		
	}
	
	
	public Pane presentInfo() {
		VBox req = new VBox();
		
		Label whatWouldYouView = new Label("What would you like to view:");
		RadioButton repository = new RadioButton("Repository Questions ");
		RadioButton exam = new RadioButton("Exams created");
		ToggleGroup radioButtonsGroup = new ToggleGroup();
		
		repository.setToggleGroup(radioButtonsGroup);
		exam.setToggleGroup(radioButtonsGroup);
		
		req.getChildren().addAll(whatWouldYouView ,repository, exam);
		req.setPadding(new Insets(10));
		
		repository.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent arg0) {
				for(UIListener listener : allListeners)
					listener.printAllQuestionsFromUI();
			}
		
		
		
		});
		
		return req;
		
	}
	
	public void show(String toPrint) {
		Label toPrintLabel = new Label(toPrint);
		VBox vBox = new VBox();
		
		ScrollPane scroller = new ScrollPane(vBox);
	//	scroller.setFitToWidth(true);
		
        vBox.getChildren().addAll(toPrintLabel);
        
        
        generalBorderPane.setCenter(scroller);
		
	}
	
	
	public void registerListener(UIListener newListener) {
		allListeners.add(newListener);
	}
	
	
}
