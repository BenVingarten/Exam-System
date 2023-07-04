package application.Panes;

import java.util.ArrayList;
import java.util.Vector;

import application.Listeners.UIListener;
import application.View.UserInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CreateMenualExamPane extends AbstractPane {

	public CreateMenualExamPane(BorderPane generalPane, Vector<UIListener> allListeners, UserInterface userInterface) {
		super(generalPane, allListeners, userInterface);
		// TODO Auto-generated constructor stub
	}

	public void preQuestionsForm() {
		VBox preQuestionsBox = new VBox();

		Label nameOfExamLabel = new Label("What is the exam name?");
		TextField examNameField = new TextField();

		Button continueiButton = new Button("Continue to form");
		preQuestionsBox.getChildren().addAll(nameOfExamLabel, examNameField, continueiButton);

		continueiButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (UIListener listener : allListeners)
					listener.createManualExam(examNameField.getText());

			}
		});

		generalPane.setCenter(preQuestionsBox);

	}

	public void addQuestionsToManualExam() { // We know in process of making new exam our exam is the last one
		VBox addQuestionsPane = new VBox();

		Label questionLabel = new Label("Would you like to add question?");
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");

		addQuestionsPane.getChildren().addAll(questionLabel, yesButton, noButton);

		yesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				userInterface.addQuestionPane.createAddQuestionPane(true);

			}
		});

		noButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				userInterface.alertMessage("Manual Exam Created!");
			}
		});

		generalPane.setCenter(addQuestionsPane);
	}

}
