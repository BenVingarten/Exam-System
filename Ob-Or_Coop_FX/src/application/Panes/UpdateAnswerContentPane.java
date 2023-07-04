package application.Panes;

import java.util.Vector;

import application.Listeners.UIListener;
import application.View.UserInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class UpdateAnswerContentPane extends AbstractPane {

	public UpdateAnswerContentPane(BorderPane generalPane, Vector<UIListener> allListeners,
			UserInterface userInterface) {
		super(generalPane, allListeners, userInterface);
	}

	public void createAnswerUpdate(int questionNum) {
		for (UIListener listener : allListeners)
			listener.updateAnswerType(questionNum + 1);
	}

	public void americanAnswerUpdateForm(int questionNum, int answerNum) {
		VBox updateAmericanAnswerBox = new VBox();

		Label insertAnswerContentLabel = new Label(
				"Insert new answer content (make sure its different from the previous content)");
		TextField answerContentField = new TextField();
		ComboBox<Boolean> answerTF = new ComboBox<>();
		answerTF.getItems().addAll(true, false);
		Button updateButton = new Button("Update");

		updateAmericanAnswerBox.getChildren().addAll(insertAnswerContentLabel, answerContentField, answerTF,
				updateButton);

		updateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (UIListener listener : allListeners)
					listener.updateAmericanAnswer(questionNum, answerNum, answerContentField.getText(),
							answerTF.getValue());

			}
		});

		generalPane.setCenter(updateAmericanAnswerBox);
	}

	public void openQUpdateAnswer(int questionNum) {
		VBox updateOpenAnswerBox = new VBox();

		Label insertAnswerContentLabel = new Label(
				"Insert new answer content (make sure its different from the previous content)");
		TextField answerContentField = new TextField();
		Button updateButton = new Button("Update");

		updateOpenAnswerBox.getChildren().addAll(insertAnswerContentLabel, answerContentField, updateButton);

		updateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (UIListener listener : allListeners)
					listener.updateOpenAnswer(questionNum, answerContentField.getText());

			}
		});

		generalPane.setCenter(updateOpenAnswerBox);

	}

}
