package application.Panes;

import java.util.Vector;

import application.Listeners.UIListener;
import application.View.UserInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuPane extends AbstractPane {

	public MenuPane(BorderPane generalPane, Vector<UIListener> allListeners, UserInterface userInterface) {
		super(generalPane, allListeners, userInterface);

		createMenu();
	}

	private void createMenu() {
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
		Button createAndShowSetButton = new Button("Create and show Set of American answers");
		Button ExitButton = new Button("Exit");

		presentInfoButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				userInterface.presentInfoPane.PresentInfo(userInterface.repositoryPane, userInterface.examPane);

			}

		});

		addQuestionButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				userInterface.addQuestionPane.createAddQuestionPane(false);
			}
		});

		updateQuestionButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (UIListener listener : allListeners)
					listener.listOfQuestions(userInterface.repositoryPane, "Update question", false);
			}

		});

		deletAnswerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for (UIListener listener : allListeners)
					listener.listOfQuestions(userInterface.repositoryPane, "Delete Answer", false);
			}
		});

		updateAnswerButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (UIListener listener : allListeners)
					listener.listOfQuestions(userInterface.repositoryPane, "Update Answer", false);
			}

		});

		createManualExamButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				userInterface.createMenualExamPane.preQuestionsForm();

			}
		});

		createAutoExamButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (UIListener listener : allListeners)
					listener.createAutoExamForm();
			}

		});

		duplicateExamButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for (UIListener listener : allListeners)
					listener.listOfExams(userInterface.examPane, "Duplicate Exam");

			}
		});

		createAndShowSetButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for (UIListener listener : allListeners)
					listener.createAndShowSet();

			}
		});

		ExitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (UIListener listener : allListeners)
					listener.exit();
				userInterface.closeMainStage();
			}

		});

		line1.getChildren().addAll(chooseTask);

		line2.getChildren().addAll(presentInfoButton, addQuestionButton, updateQuestionButton, updateAnswerButton);

		line3.getChildren().addAll(deletAnswerButton, createManualExamButton, createAutoExamButton, duplicateExamButton,
				createAndShowSetButton, ExitButton);

		menu.getChildren().addAll(line1, line2, line3);

		menu.setPadding(new Insets(10, 10, 10, 10));
		menu.setMaxHeight(300);

		generalPane.setTop(menu);

	}

}
