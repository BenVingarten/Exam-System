package application.Controller;

import application.Exceptions.InvalidUserInputException;
import application.Listeners.ModelListener;
import application.Listeners.UIListener;
import application.Model.Manager;
import application.View.UserInterface;

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
	
	public void showFromModel(String toPrint) {
		userInterface.show(toPrint);
	}
	
	


	
	
	

}
