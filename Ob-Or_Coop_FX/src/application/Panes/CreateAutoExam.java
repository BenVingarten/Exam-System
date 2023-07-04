package application.Panes;

import java.util.Vector;

import application.Listeners.UIListener;
import application.View.UserInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CreateAutoExam extends AbstractPane{

	public CreateAutoExam(BorderPane generalPane, Vector<UIListener> allListeners, UserInterface userInterface) {
		super(generalPane, allListeners, userInterface);
	}

	
	public void createAutoExamForm(int maxNumOfQuestions) {
		VBox createAutoExamBox = new VBox();
		
		Label nameLabel = new Label("Insert name for the exam:");
		TextField examNameField = new TextField();
		
		Label howManyLabel = new Label("How many questions would you like (limited to number of question in repository)");
		Spinner<Integer> numOfQuestionSpinner = new Spinner<>(0, maxNumOfQuestions, 0);
		
		Button generateExamButton = new Button("Generate Exam");
		
		createAutoExamBox.getChildren().addAll(nameLabel, examNameField, howManyLabel, numOfQuestionSpinner, generateExamButton);
		
		generateExamButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				for(UIListener listener : allListeners)
					listener.createAutoExam(examNameField.getText(), numOfQuestionSpinner.getValue());
				
			}
		});
		
		generalPane.setCenter(createAutoExamBox);
	}
	
}
