package application.Listeners;

import application.Exceptions.InvalidUserInputException;

public interface UIListener {
	void checkValidRangeFromUI(int chosen, int min, int max) throws InvalidUserInputException ;

	String printAllQuestionsFromUI();

}
