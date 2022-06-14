package application.Listeners;

import java.util.ArrayList;

import application.Exceptions.DataNotCreatedYetException;
import application.Exceptions.InvalidUserInputException;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public interface UIListener {
	
	void printAllQuestionsFromUI();

	void selectAndDoExamFromUI(int mission);

	void addOpenQuesionToExamFromUI(String questionString, String answersString, int examNum);
	

	void printExamFromUI(int indexOf);

	void createAutoExam();

	void addAmericanQuestionToExam(String string, ArrayList<TextField> allAnswersArrayList,
			ArrayList<ComboBox<Boolean>> answersTFArrayList, int examNum);

}
