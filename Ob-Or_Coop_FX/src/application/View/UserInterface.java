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
	
	public UserInterface(Stage mainStage) {
		mainStage.setTitle("Manu");
		
		VBox manu = new VBox();
		Label chooseTask = new Label("Please choose the task you want");
		
		Button presentInfoButton = new Button("Present database and exmas (all Q&A)");
		Button addQuestionButton = new Button("Add Question (to the Exam and or data base");
		Button updateQuestionButton = new Button("Update content of an existing question");
		Button updateAnswerButton = new Button("Update content of an existing answer");
		Button deletAnswerButton = new Button("Delete an answer to an existing question");
		Button createManualExamButton = new Button("Create exam manually");
		Button createAutoExamButton = new Button("Create exam automatically");
		Button duplicateExamButton = new Button("Duplicate Exam");
		Button createAndShowSetButton = new Button("Create and show Set");
		Button ExitButton = new Button("Exit");
		
		
		
		presentInfoButton.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent arg0) {
				try {
					getIntFromUser("What would you like to view: \n1) Repository Questions \n2) Exams created", 1, 2);
					PresentInfo(allListeners);
				} catch (InvalidUserInputException | DataNotCreatedYetException | FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});
		
		
		
		
		ExitButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
			mainStage.close();
			}
		
		});
		
		manu.getChildren().addAll( chooseTask, presentInfoButton, addQuestionButton, updateQuestionButton,
				updateAnswerButton, deletAnswerButton, createManualExamButton, createAutoExamButton,
				duplicateExamButton, createAndShowSetButton, ExitButton);
		
		manu.setSpacing(10);
		manu.setPadding(new Insets(10));
		
		mainStage.setScene(new Scene(manu));
		mainStage.show();
		
	}
	
	
	
	
	@Override
	public void registerListener(UIListener newListener) {
		allListeners.add(newListener);
	}
	
	@Override
	public void PresentInfo(Vector<UIListener> allListeners)
			throws InvalidUserInputException, DataNotCreatedYetException, FileNotFoundException {
		
		
		
		
		int UserChose = userInputInt;
		System.out.println(UserChose);
		if (UserChose == 1) {
			firePrintQuestions();
			System.out.println("Entered");
		}
		
		/*
		}
			System.out.println();
		} else {
			System.out.println(((UIListener) allListeners).getListOfExamsFromUI());
			System.out.println("Please choose exam in list by number: ");
			Exam selectedExam = ((UIListener) allListeners)manager.selectExamFromUI(input.nextInt());
			((UIListener) allListeners).saveExamToFileFromUI(selectedExam);
			System.out.println(selectedExam.toString());
		}*/
		
		
		
	}
	
	public void firePrintQuestions() {
		Stage presentInfoStage = new Stage();
		VBox presentBox = new VBox();
		presentInfoStage.setTitle("Prestent Info");
		Label printLabel = new Label();
		
		for(UIListener listener : allListeners) 
			printLabel.setText(listener.printAllQuestionsFromUI());
		
		presentBox.setSpacing(10);
		presentBox.setPadding(new Insets(10));
		presentBox.getChildren().add(printLabel);
		
		presentInfoStage.setScene(new Scene(presentBox));
		presentInfoStage.show();
		
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
		
		alert.setPadding(new Insets(50));
		
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
	
	public void getIntFromUser(String text, int min, int max) {
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
				try {
					userInputInt = Integer.parseInt(inpuTextField.getText());					
					for(UIListener listener : allListeners) 
						listener.checkValidRangeFromUI(userInputInt, 1, 2);
					talkToUserStage.close();
					
				} catch (Exception e2) {
					alertWindow("You must insert integer, and int range !");
				}
					
			}
		});
		
		userInputBox.setSpacing(10);
		userInputBox.setPadding(new Insets(10));
		
		talkToUserStage.setScene(new Scene(userInputBox));
		talkToUserStage.show();
		
		
	}
	
}
