package application.Panes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import application.Listeners.UIListener;
import application.View.UserInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ExamPane extends AbstractPane {

	public ExamPane(BorderPane generalPane, Vector<UIListener> allListeners, UserInterface userInterface) {
		super(generalPane, allListeners, userInterface);

	}

	public void chooseExamPane(ArrayList<String> examList, String mission) {
		Label messageLabel = new Label("Choose Exam number:");
		VBox chooseExamPane = new VBox();
		chooseExamPane.getChildren().add(messageLabel);

		for (String examName : examList) {
			Button b = new Button(examName);
			chooseExamPane.getChildren().add(b);

			if (mission.equals("printExam")) {
				b.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						for (UIListener listener : allListeners)
							listener.printExamFromUI(examList.indexOf(examName), ExamPane.this);
					}

				});

			}

			else if (mission.equals("addNewQuestion")) {
				b.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						userInterface.addQuestionPane.addQuestionForm((examList.indexOf(examName)), false);
					}

				});

			}

			else if (mission.equals("addExistingQuestion")) {
				b.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						for (UIListener listener : allListeners)
							listener.listOfQuestions(userInterface.repositoryPane, mission, false);
						userInterface.addQuestionPane.setExamNum((examList.indexOf(examName)));
					}

				});
			}

			else if (mission.equals("Duplicate Exam")) {
				b.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						for (UIListener listener : allListeners)
							listener.duplicateExam(examList.indexOf(examName));

					}

				});
			}

		}

		generalPane.setCenter(chooseExamPane);

	}

	public void printExamPane(String examString) {
		userInterface.show(examString);
	}

}
