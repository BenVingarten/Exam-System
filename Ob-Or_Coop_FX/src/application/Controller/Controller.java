package application.Controller;

import java.util.ArrayList;

import application.Exceptions.DataIdenticalException;
import application.Exceptions.DataNotCreatedYetException;
import application.Exceptions.GeneralSystemException;
import application.Listeners.ModelListener;
import application.Listeners.UIListener;
import application.Model.Manager;
import application.View.UserInterface;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Controller implements UIListener, ModelListener {
	private Manager manager;
	private UserInterface userInterface;
	
	public Controller(Manager manager, UserInterface userInterface) {
		this.userInterface = userInterface;
		this.manager = manager;
		
		this.userInterface.registerListener(this);
		this.manager.registerListener(this);
		
	}
	
	
	
	@Override
	public void printAllQuestionsFromUI(){
		manager.printAllQuestions();
	}
	
	
	public void selectAndDoExamFromUI(int mission) {
		try {
			
			userInterface.selectExamAndDo(manager.getListOfExams(), mission);
		} catch (DataNotCreatedYetException e) {
			userInterface.alertMessage(e.getMessage());
		}
	}
	
	
	
	
	
	
	public void showFromModel(String toPrint) {
		userInterface.show(toPrint);
	}



	


	@Override
	public void printExamFromUI(int indexOf) {
		showFromModel(manager.getExam(indexOf).toString()); 
		
	}



	@Override
	public void createAutoExam() {
		try {
			manager.generateAutomaticExam(2, "Auto");
		} catch (DataIdenticalException e) {
			
			e.printStackTrace();
		}
		
	}



	
	
	@Override
	public void addOpenQuesionToExamFromUI(String questionString, String answersString, int examNum) {
		
		try {
			if(questionString.length() == 0 || answersString.length() == 0)
				throw new GeneralSystemException("No data inserted");
			manager.addQuestionToExam(manager.getExam(examNum), manager.addOpenQToRepository(questionString, answersString));
			userInterface.alertMessage("Open question added");
		} catch (DataIdenticalException e) {
			userInterface.alertMessage(e.getMessage());
		}
		catch (GeneralSystemException e) {
			userInterface.alertMessage(e.getMessage());
		}
		
	}



	
	@Override
	public void addAmericanQuestionToExam(String qContent, ArrayList<TextField> allAnswersArrayList,
			ArrayList<ComboBox<Boolean>> answersTFArrayList, int examNum) {
		try {
			if(qContent.length() == 0)
				throw new GeneralSystemException("No data inserted in question content");
			manager.addQuestionToExam(manager.getExam(examNum), manager.addAmericanQToRepository(qContent, allAnswersArrayList, answersTFArrayList));
			throw new GeneralSystemException("American question added");
		} catch (DataIdenticalException e) {
			userInterface.alertMessage(e.getMessage());
		} catch (GeneralSystemException e) {
			userInterface.alertMessage(e.getMessage());
		}
		
	}
	
	

	

	
	
	

}
