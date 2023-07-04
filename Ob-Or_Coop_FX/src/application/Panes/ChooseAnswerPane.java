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

public class ChooseAnswerPane extends AbstractPane {

	public ChooseAnswerPane(BorderPane generalPane, Vector<UIListener> allListeners, UserInterface userInterface) {
		super(generalPane, allListeners, userInterface);
	}
	
	public void createChooseAnswerPane(ArrayList<String> answerList, String mission, int questionNum) {
		Label messageLabel = new Label("Choose Answer number:");
		VBox chooseQuestionPane = new VBox();
		chooseQuestionPane.getChildren().add(messageLabel);
		
		if(mission.equals("Delete Answer")) {
			Label warningLabel = new Label("If you choose to delet open question answer or american question with less than 1 answer the question will be deleted");
			chooseQuestionPane.getChildren().add(warningLabel);
		}
		
		for(String answerName : answerList) {
			Button b = new Button(answerName);
			chooseQuestionPane.getChildren().add(b);
			
			if(mission.equals("Update Answer")) {
				b.setOnAction(new EventHandler<ActionEvent>() {
				
					@Override
					public void handle(ActionEvent arg0) {
						userInterface.updateAnswerContentPane.americanAnswerUpdateForm(questionNum, answerList.indexOf(answerName));
					}
				
				});
		
			}	
			
			if(mission.equals("Delete Answer")) {
				b.setOnAction(new EventHandler<ActionEvent>() {
				
					@Override
					public void handle(ActionEvent arg0) {
						for(UIListener listener : allListeners)
							listener.deleteAnswer(questionNum, (answerList.indexOf(answerName) + 1));
					}
				
				});
		
			}	
		
			
		}
		
		generalPane.setCenter(chooseQuestionPane);
	}

}
