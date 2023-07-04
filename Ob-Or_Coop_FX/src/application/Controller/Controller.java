package application.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import application.Exceptions.DataIdenticalException;
import application.Exceptions.DataNotCreatedYetException;
import application.Exceptions.GeneralSystemException;
import application.Listeners.ModelListener;
import application.Listeners.UIListener;
import application.Model.AmericanQuestion;
import application.Model.Answer;
import application.Model.Manager;
import application.Model.Question;
import application.Panes.ExamPane;
import application.Panes.RepositoryPane;
import application.View.UserInterface;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

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
	public void printAllQuestionsFromUI(RepositoryPane repositoryPane) {
		repositoryPane.printAllQuestions(manager.printAllQuestions());
	}

	public void listOfExams(ExamPane examPane, String mission) {
		try {

			examPane.chooseExamPane(manager.getListOfExams(), mission);
		} catch (DataNotCreatedYetException e) {
			userInterface.alertMessage(e.getMessage());
		}
	}

	@Override
	public void printExamFromUI(int indexOf, ExamPane examPane) {
		try {
			manager.saveExamToFile(indexOf);
			examPane.printExamPane(manager.getExam(indexOf).toString());
		} catch (FileNotFoundException e) {
			userInterface.alertMessage(e.getMessage());
		}

	}

	@Override
	public void createAutoExam(String examName, int numOfQuestions) {
		try {
			manager.generateAutomaticExam(numOfQuestions, examName);
			userInterface.alertMessage("Exam  '" + examName + "' created succesfully!");
		} catch (DataIdenticalException e) {
			userInterface.alertMessage(e.getMessage());
		}

	}

	@Override
	public void addOpenQuesionFromUI(String questionString, String answersString) {

		try {
			if (questionString.length() == 0 || answersString.length() == 0)
				throw new GeneralSystemException("No data inserted");
			
			if(manager.getListOfQuestions().contains(questionString)) //if the name already exist in American q(if open Q it would go to exception)
				questionString = questionString + " (Open Question)";
			manager.addOpenQToRepository(questionString, answersString);

			userInterface.alertMessage("Open question added");
		} catch (DataIdenticalException e) {
			userInterface.alertMessage(e.getMessage());
		} catch (GeneralSystemException e) {
			userInterface.alertMessage(e.getMessage());
		}

	}

	@Override
	public void addAmericanQuestionFromUI(String qContent, ArrayList<TextField> allAnswersArrayList,
			ArrayList<ComboBox<Boolean>> answersTFArrayList) {
		try {
			if (qContent.length() == 0)
				throw new GeneralSystemException("No data inserted in question content");
			
			if(manager.getListOfQuestions().contains(qContent)) //if the name already exist in Open Q(if American Q it would go to exception)
				qContent = qContent + " (American Question)";
			manager.addAmericanQToRepository(qContent, allAnswersArrayList, answersTFArrayList);
			throw new GeneralSystemException("American question added");
		} catch (DataIdenticalException e) {
			userInterface.alertMessage(e.getMessage());
		} catch (GeneralSystemException e) {
			userInterface.alertMessage(e.getMessage());
		}

	}

	@Override
	public void listOfQuestions(RepositoryPane repositoryPane, String mission, boolean isManualExam) {
		repositoryPane.chooseQuestionPane(manager.getListOfQuestions(), mission, isManualExam);
	}

	@Override
	public void addQuestionToExam(int selectedExam, int selectedQ, boolean isManualExam) {
		try {
			if (selectedExam == -1)
				selectedExam = manager.getNumOfExams() - 1; // last exam index
			if (selectedQ == -1)
				manager.addQuestionToExam(selectedExam, manager.getNumOfQ() - 1); // last question index
			else
				manager.addQuestionToExam(selectedExam, selectedQ);
			if (!isManualExam) {
				userInterface.show("Question added succesfully");
			} else {
				userInterface.createMenualExamPane.addQuestionsToManualExam();
			}

		} catch (DataIdenticalException e) {
			userInterface.alertMessage(e.getMessage());
		}

	}

	@Override
	public void updateQuestion(String newContent, int questionNum) {
		try {
			manager.updateQuestionContent(newContent, questionNum);
			userInterface.alertMessage("Question content updated!");
		} catch (DataIdenticalException e) {
			userInterface.alertMessage(e.getMessage());
		} catch (GeneralSystemException e) {
			userInterface.alertMessage(e.getMessage());
		}

	}

	@Override
	public void updateAnswerType(int questionNum) {
		Question question = manager.getQuestion(questionNum);
		String questionType = manager.getQuestionType(questionNum);
		
		if (questionType.equals("AmericanQuestion")) {
			userInterface.chooseAnswerPane.createChooseAnswerPane(question.getListOfAnswers(), "Update Answer",
					questionNum);
		} else {
			userInterface.updateAnswerContentPane.openQUpdateAnswer(questionNum);
		}

	}

	@Override
	public void updateAmericanAnswer(int questionNum, int answerNum, String newContent, boolean TorF) {
		try {
			manager.updateAmericanQAnswer(answerNum + 1, newContent, TorF, questionNum);
			userInterface.alertMessage("Answer updated succefully");
		} catch (DataIdenticalException e) {
			userInterface.alertMessage(e.getMessage());
		}

	}

	@Override
	public void updateOpenAnswer(int questionNum, String newContent) {
		if (!manager.updateOpenQAnswer(questionNum, newContent)) {
			userInterface.alertMessage("Answers are identical!");
		} else {
			userInterface.alertMessage("Answer updated succesfully");
		}

	}

	@Override
	public void listOfAnswers(int questionNum, String mission) {
		Question question = manager.getQuestion(questionNum);
		userInterface.chooseAnswerPane.createChooseAnswerPane(question.getListOfAnswers(), mission, questionNum);

	}

	@Override
	public void deleteAnswer(int questionNum, int answerNum) {
		Question question = manager.getQuestion(questionNum);
		if (question.getQuestionType().equals(("AmericanQuestion")))
			manager.deleteAmericanQAnswer(questionNum, answerNum);
		else {
			manager.deleteQuestion(questionNum);
		}

		userInterface.alertMessage("Answer deleted succesfully");

	}

	@Override
	public void generateManualExam(String examName, ArrayList<TextField> openQuestionsContent,
			ArrayList<TextField> openQuestionsAnswers, ArrayList<TextField> openQuestionsAnswers2,
			ArrayList<ArrayList<TextField>> americanQuestionAnswersContent,
			ArrayList<ArrayList<ComboBox<Boolean>>> americanQuestionAnswersBoolean) {

	}

	@Override
	public void createManualExam(String examName) {
		try {
			manager.createManualExam(examName);
			userInterface.createMenualExamPane.addQuestionsToManualExam();
		} catch (DataIdenticalException e) {
			userInterface.alertMessage(e.getMessage());
		}

	}

	@Override
	public void createAutoExamForm() {
		userInterface.createAutoExam.createAutoExamForm(manager.getNumOfQ());

	}

	@Override
	public void duplicateExam(int examNum) {
		try {
			manager.cloneExam(examNum);
			userInterface.alertMessage("Exam duplicated!");
		} catch (CloneNotSupportedException e) {
			userInterface.alertMessage(e.getMessage());
		}

	}

	@Override
	public void createAndShowSet() {
		try {
			userInterface.alertMessage(manager.setOfAmericanAnswers());
		} catch (Exception e) {
			userInterface.alertMessage(e.getMessage());
		}

	}

	@Override
	public void exit() {
		try {
			manager.exit();
		} catch (FileNotFoundException e) {
			userInterface.alertMessage("Problem In Exit 1");
		} catch (IOException e) {
			userInterface.alertMessage("Problem In Exit 2");
		}
	}

	@Override
	public void initialProgrem() {
		try {
			manager.inputDataFromBinary();
		} catch (FileNotFoundException e1) {
			// No need in message because it will always pop in first time
		} catch (IOException e1) {
			// ---------------" ------------------------------
		} catch (Exception e) {
			// --------------" ---------------------
		}

		try {
			manager.questionsRepository();
		} catch (Exception e) {
			// userInterface.alertMessage(e.getMessage());
			// No need in message because it will always pop when we have binary file
		}
	}

}
