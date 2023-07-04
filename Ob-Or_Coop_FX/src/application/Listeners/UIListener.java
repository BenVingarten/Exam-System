package application.Listeners;

import java.util.ArrayList;

import application.Exceptions.DataNotCreatedYetException;
import application.Exceptions.InvalidUserInputException;
import application.Model.Question;
import application.Panes.ExamPane;
import application.Panes.RepositoryPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public interface UIListener {

	void printAllQuestionsFromUI(RepositoryPane repositoryPane);

	void addOpenQuesionFromUI(String questionString, String answersString);

	void printExamFromUI(int indexOf, ExamPane examPane);

	void createAutoExamForm();

	void createAutoExam(String examName, int numOfQuestions);

	void addAmericanQuestionFromUI(String string, ArrayList<TextField> allAnswersArrayList,
			ArrayList<ComboBox<Boolean>> answersTFArrayList);

	public void listOfQuestions(RepositoryPane repositoryPane, String mission, boolean isManualExam);

	void addQuestionToExam(int selectedExam, int indexOf, boolean isManualExam);

	void listOfExams(ExamPane chooseExam, String mission);

	void updateQuestion(String newContent, int questionNum);

	void updateAnswerType(int questionNum);

	void updateAmericanAnswer(int questionNum, int answerNum, String newContent, boolean TorF);

	void updateOpenAnswer(int questionNum, String text);

	void listOfAnswers(int question, String mission);

	void deleteAnswer(int questionNum, int answerNum);

	void generateManualExam(String examName, ArrayList<TextField> openQuestionsContent,
			ArrayList<TextField> openQuestionsAnswers, ArrayList<TextField> openQuestionsAnswers2,
			ArrayList<ArrayList<TextField>> americanQuestionAnswersContent,
			ArrayList<ArrayList<ComboBox<Boolean>>> americanQuestionAnswersBoolean);

	void createManualExam(String text);

	void duplicateExam(int examNum);

	void createAndShowSet();

	void exit();

	void initialProgrem();

}
