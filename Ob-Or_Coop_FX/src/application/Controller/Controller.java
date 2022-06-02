package application.Controller;

import application.Exceptions.InvalidUserInputException;
import application.Listeners.ModelListener;
import application.Listeners.UIListener;
import application.Model.Manager;
import application.View.AbstractUserInterface;

public class Controller implements UIListener, ModelListener {
	private Manager manager;
	private AbstractUserInterface userInterface;
	
	public Controller(Manager manager, AbstractUserInterface userInterface) {
		this.userInterface = userInterface;
		this.manager = manager;
		
		this.userInterface.registerListener(this);
		//register listiner to manager
		
	}
	
	@Override
	public void checkValidRangeFromUI(int chosen, int min, int max) throws InvalidUserInputException {
		// TODO Auto-generated method stub
		manager.checkValidRange(chosen, min, max);
	}
	
	@Override
	public String printAllQuestionsFromUI(){
		return manager.printAllQuestions();
	}
	
	


	
	
	

}
