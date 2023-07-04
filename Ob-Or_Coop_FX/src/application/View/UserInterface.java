package application.View;

import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.Vector;

import application.Listeners.UIListener;
import application.Panes.AddQuestionPane;
import application.Panes.ChooseAnswerPane;
import application.Panes.CreateAutoExam;
import application.Panes.CreateMenualExamPane;
import application.Panes.ExamPane;
import application.Panes.MenuPane;
import application.Panes.PresentInfoPane;
import application.Panes.RepositoryPane;
import application.Panes.UpdateAnswerContentPane;
import application.Panes.UpdateQuestionPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserInterface {
	private Vector<UIListener> allListeners = new Vector<UIListener>();
	BorderPane generalBorderPane = new BorderPane();
	public int selectedExam;
	private Stage mainStage;

	// All panes classes:
	public PresentInfoPane presentInfoPane = new PresentInfoPane(generalBorderPane, allListeners, this);
	public MenuPane menuPane = new MenuPane(generalBorderPane, allListeners, this);
	public ExamPane examPane = new ExamPane(generalBorderPane, allListeners, this);
	public RepositoryPane repositoryPane = new RepositoryPane(generalBorderPane, allListeners, this);
	public AddQuestionPane addQuestionPane = new AddQuestionPane(generalBorderPane, allListeners, this);
	public UpdateQuestionPane updateQuestionPane = new UpdateQuestionPane(generalBorderPane, allListeners, this);
	public UpdateAnswerContentPane updateAnswerContentPane = new UpdateAnswerContentPane(generalBorderPane,
			allListeners, this);
	public ChooseAnswerPane chooseAnswerPane = new ChooseAnswerPane(generalBorderPane, allListeners, this);
	public CreateMenualExamPane createMenualExamPane = new CreateMenualExamPane(generalBorderPane, allListeners, this);
	public CreateAutoExam createAutoExam = new CreateAutoExam(generalBorderPane, allListeners, this);

	public UserInterface(Stage mainStage) {

		this.mainStage = mainStage;
		mainStage.setTitle("Manu");

		Scene generalScene = new Scene(generalBorderPane, 1000, 1000);

		generalBorderPane.setPadding(new Insets(10, 10, 10, 10));

		menuPane = new MenuPane(generalBorderPane, allListeners, this);

		mainStage.setScene(generalScene);
		mainStage.show();

	}

	public void closeMainStage() {
		mainStage.close();
	}

	public void show(String toPrint) {
		Label toPrintLabel = new Label(toPrint);
		VBox vBox = new VBox();

		ScrollPane scroller = new ScrollPane(vBox);

		vBox.getChildren().addAll(toPrintLabel);

		generalBorderPane.setCenter(scroller);

	}

	public void registerListener(UIListener newListener) {
		allListeners.add(newListener);
	}

	public void alertMessage(String toPrint) {
		Label messageLabel = new Label(toPrint);
		VBox vBox = new VBox();
		vBox.getChildren().addAll(messageLabel);
		generalBorderPane.setCenter(vBox);
	}

}
