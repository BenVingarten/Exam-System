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
import javafx.scene.layout.VBox;

public class PresentInfoPane extends AbstractPane {

	public PresentInfoPane(BorderPane generalPane, Vector<UIListener> allListeners, UserInterface userInterface) {
		super(generalPane, allListeners, userInterface);
	}

	public void PresentInfo(RepositoryPane repositoryPane, ExamPane examPane) {
		VBox req = new VBox();

		Label whatWouldYouView = new Label("What would you like to view:");
		Button repository = new Button("Repository Questions ");
		Button exam = new Button("Exams created");

		req.getChildren().addAll(whatWouldYouView, repository, exam);
		req.setPadding(new Insets(10));

		repository.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for (UIListener listener : allListeners)
					listener.printAllQuestionsFromUI(repositoryPane);
			}

		});

		exam.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for (UIListener listener : allListeners)
					listener.listOfExams(examPane, "printExam");
			}

		});

		generalPane.setCenter(req);
	}

}
