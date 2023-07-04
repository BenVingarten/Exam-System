package application.Panes;

import java.util.ArrayList;
import java.util.Vector;

import application.Listeners.UIListener;
import application.View.UserInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class RepositoryPane extends AbstractPane {

	public RepositoryPane(BorderPane generalPane, Vector<UIListener> allListeners, UserInterface userInterface) {
		super(generalPane, allListeners, userInterface);

	}

	public void printAllQuestions(String allQuestions) {
		userInterface.show(allQuestions);
	}

	public void chooseQuestionPane(ArrayList<String> questionList, String mission, boolean isManualExam) {
		Label messageLabel = new Label("Choose Question number:");
		VBox chooseQuestionPane = new VBox();
		chooseQuestionPane.getChildren().add(messageLabel);

		for (String questionName : questionList) {
			Button b = new Button(questionName);
			chooseQuestionPane.getChildren().add(b);

			if (mission.equals("addExistingQuestion")) {
				b.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						userInterface.addQuestionPane.addExistingQuestionToExam((questionList.indexOf(questionName)),
								isManualExam);
					}

				});

			}

			else if (mission.equals("Update question")) {
				b.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						userInterface.updateQuestionPane.updateQuestionForm(questionList.indexOf(questionName));
					}

				});
			}

			else if (mission.equals("Update Answer")) {
				b.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {
						for (UIListener listener : allListeners)
							listener.updateAnswerType(questionList.indexOf(questionName));
					}

				});
			}

			else if (mission.equals("Delete Answer")) {
				b.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						for (UIListener listener : allListeners)
							listener.listOfAnswers(questionList.indexOf(questionName), mission);
					}

				});
			}

		}

		generalPane.setCenter(chooseQuestionPane);

	}

}
