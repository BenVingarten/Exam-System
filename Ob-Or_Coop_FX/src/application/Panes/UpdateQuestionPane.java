package application.Panes;

import java.util.List;
import java.util.Vector;

import application.Listeners.UIListener;
import application.View.UserInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class UpdateQuestionPane extends AbstractPane {

	public UpdateQuestionPane(BorderPane generalPane, Vector<UIListener> allListeners, UserInterface userInterface) {
		super(generalPane, allListeners, userInterface);

	}

	public void updateQuestionForm(int questionNum) {
		VBox updateQuestionBox = new VBox();

		Label insertTextLabel = new Label(
				"Insert new question content (make sure its different from the previous content)");
		TextField updatedQuestionField = new TextField();
		Button confirmButton = new Button("Update");
		updateQuestionBox.getChildren().addAll(insertTextLabel, updatedQuestionField, confirmButton);
		generalPane.setCenter(updateQuestionBox);

		confirmButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (UIListener listener : allListeners)
					listener.updateQuestion(updatedQuestionField.getText(), questionNum);

			}
		});
	}

}
