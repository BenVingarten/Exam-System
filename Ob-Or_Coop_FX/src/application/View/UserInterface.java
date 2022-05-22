package application.View;

import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.util.InputMismatchException;
import java.util.Vector;

import application.Controller.Controller;
import application.Exceptions.DataIdenticalException;
import application.Exceptions.DataNotCreatedYetException;
import application.Exceptions.GeneralSystemException;
import application.Exceptions.InvalidUserInputException;
import application.Listeners.UIListener;
import application.Model.Exam;
import application.Model.Main_Interface;
import application.Model.Manager;
import application.Model.Question;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class UserInterface implements AbstractUserInterface, Main_Interface{
	private Vector<UIListener> allListeners = new Vector<UIListener>();
	private String userInputString;
	private int userInputInt;
	
	public UserInterface(Stage stage) {
		stage.setTitle("Manu");
		
		VBox manu = new VBox();
		Label chooseTask = new Label("Please choose the task you want");
		
		
		ToggleGroup optionsToggleGroup = new ToggleGroup();
		RadioButton case1 = new RadioButton("Present database and exmas (all Q&A) ");
		RadioButton case2 = new RadioButton("Add Question (to the Exam and or data base ");
		RadioButton case3 = new RadioButton("Update content of an existing question ");
		RadioButton case4 = new RadioButton("Update content of an existing answer ");
		RadioButton case5 = new RadioButton("Delete an answer to an existing question ");
		RadioButton case6 = new RadioButton("Create exam manually ");
		RadioButton case7 = new RadioButton("Create exam automatically ");
		RadioButton case8 = new RadioButton("Create exam duplicate ");
		RadioButton case9 = new RadioButton("Create and show Set ");
		RadioButton case10 = new RadioButton("Exit");
		case1.setToggleGroup(optionsToggleGroup);
		case2.setToggleGroup(optionsToggleGroup);
		case3.setToggleGroup(optionsToggleGroup);
		case4.setToggleGroup(optionsToggleGroup);
		case5.setToggleGroup(optionsToggleGroup);
		case6.setToggleGroup(optionsToggleGroup);
		case7.setToggleGroup(optionsToggleGroup);
		case8.setToggleGroup(optionsToggleGroup);
		case9.setToggleGroup(optionsToggleGroup);
		case10.setToggleGroup(optionsToggleGroup);
		
		
		
		case1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				PresentInfo(allListeners);
				
				
			}
		});
		
		manu.getChildren().addAll(chooseTask, case1, case2, case3, case4,
				case5, case6, case7, case8, case9, case10);
		
		manu.setSpacing(10);
		manu.setPadding(new Insets(10));
		
		stage.setScene(new Scene(manu));
		stage.show();
		
	}

	
	@Override
	public void registerListener(UIListener newListener) {
		allListeners.add(newListener);
	}
	
	@Override
	
public void PresentInfo(Vector<UIListener> allListeners)
			throws InvalidUserInputException, DataNotCreatedYetException, FileNotFoundException {
		
		Stage presentInfoStage = new Stage();
		presentInfoStage.setTitle("Present Info");
		
		getIntFromUser("What would you like to view: \n1) Repository Questions \n2) Exams created");
		//System.out.println("What would you like to view: \n1) Repository Questions \n2) Exams created");
		int option = userInputInt;
		((UIListener) allListeners).checkValidRangeFromUI(option, 1, 2);
		if (option == 1) {
			System.out.println(((UIListener) allListeners).printAllQuestionsFromUI());
		} else {
			System.out.println(((UIListener) allListeners).getListOfExamsFromUI());
			System.out.println("Please choose exam in list by number: ");
			Exam selectedExam = ((UIListener) allListeners)manager.selectExamFromUI(input.nextInt());
			((UIListener) allListeners).saveExamToFileFromUI(selectedExam);
			System.out.println(selectedExam.toString());
		}
		
		
		
	}

	@Override
	public Question createAmericanQ(Manager manager)
			throws InvalidUserInputException, DataIdenticalException, GeneralSystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question createOpenQ(Manager manager) throws DataIdenticalException, InputMismatchException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addQuestion(Manager manager) throws DataNotCreatedYetException, InvalidUserInputException,
			DataIdenticalException, InputMismatchException, GeneralSystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateContentOfQuestion(Manager manager) throws InputMismatchException, GeneralSystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateContentOfAnswer(Manager manager) throws DataNotCreatedYetException, InvalidUserInputException,
			DataIdenticalException, InputMismatchException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAnswerFromQuestion(Manager manager)
			throws DataNotCreatedYetException, InvalidUserInputException, InputMismatchException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createExamManually(Manager manager) throws InvalidUserInputException, DataNotCreatedYetException,
			DataIdenticalException, InputMismatchException, GeneralSystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAutomaticExam(Manager manager) throws DataIdenticalException, InvalidUserInputException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void duplicateExam(Manager manager)
			throws DataNotCreatedYetException, InvalidUserInputException, CloneNotSupportedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ShowSet(Manager manager) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	public void alertWindow(String message) {
		Stage alertStage = new Stage();
		alertStage.setTitle("Exception");
		
		VBox alert = new VBox();
		Label messageLabel = new Label(message);
		
		alert.getChildren().add(messageLabel);
		alertStage.setScene(new Scene(alert));
		alertStage.show();
		
		
	}

	public void getStringFromUser(String text) {
		
		
		Stage talkToUserStage = new Stage();
		talkToUserStage.setTitle("User Input");
		
		VBox userInputBox = new VBox();
		Label textLabel = new Label(text);
		Button returnButton = new Button("return");
		TextField inpuTextField = new TextField();
		
		userInputBox.getChildren().addAll(textLabel, inpuTextField, returnButton);
		userInputBox.setAlignment(Pos.CENTER);
		userInputBox.setSpacing(10);
		
		returnButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				userInputString = inpuTextField.getText();
				
			}
		});
		
		talkToUserStage.setScene(new Scene(userInputBox));
		talkToUserStage.show();
		
		
	}
	
	public void getIntFromUser(String text) {
		Stage talkToUserStage = new Stage();
		talkToUserStage.setTitle("User Input");
		
		VBox userInputBox = new VBox();
		Label textLabel = new Label(text);
		Button returnButton = new Button("return");
		TextField inpuTextField = new TextField();
		
		userInputBox.getChildren().addAll(textLabel, inpuTextField, returnButton);
		userInputBox.setAlignment(Pos.CENTER);
		userInputBox.setSpacing(10);
		
		returnButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				userInputInt = Integer.parseInt(inpuTextField.getText());
				
			}
		});
		
		talkToUserStage.setScene(new Scene(userInputBox));
		talkToUserStage.show();
		
		
	}
	
}
