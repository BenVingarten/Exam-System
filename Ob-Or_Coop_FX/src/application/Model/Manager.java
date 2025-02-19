package application.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.jar.Attributes.Name;
import java.util.zip.CheckedInputStream;

import javax.print.attribute.standard.DateTimeAtCreation;
import javax.xml.transform.Templates;

import application.Exceptions.DataIdenticalException;
import application.Exceptions.DataNotCreatedYetException;
import application.Exceptions.GeneralSystemException;
import application.Exceptions.InvalidUserInputException;
import application.Listeners.ModelListener;
import application.Listeners.UIListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Manager implements Serializable {

	Scanner scanner = new Scanner(System.in);

	private Vector<ModelListener> allListeners = new Vector();

	private ArrayList<Exam> allExams;
	private ArrayList<Question> questionsArray;

	public void registerListener(ModelListener newListener) {
		allListeners.add(newListener);
	}

	// C'tor of the manager
	public Manager() {
		this.allExams = new ArrayList<Exam>();
		this.questionsArray = new ArrayList<Question>();

	}

	public void inputDataFromBinary() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("questions.dat"));
		questionsArray = (ArrayList<Question>) inFile.readObject();
		inFile.close();

	}

	// create Exam in blank space or increase the array by 2.
	public void AddExamToArray(Exam exam) {
		this.allExams.add(exam);
		exam.sortQestionsByAnswersLength();

	}

	public void questionsRepository() throws GeneralSystemException {

		ArrayList<Answer> ansArray = new ArrayList<Answer>(); // Answer Array

		// 0-9 Q0
		ansArray.add(new Answer("Kabul", true));
		ansArray.add(new Answer("Nassau", false));

		addAmericanQToRepository("What is the capital of Afghanistan", ansArray); // Insert new American Q

		// 0-9 Q1
		ansArray.set(0, new Answer("Prague", false));
		ansArray.set(1, new Answer("Zagreb", true));

		addAmericanQToRepository("What is the capital of Belarus", ansArray); // Insert new American Q

		// 0-9 Q2
		ansArray.set(0, new Answer("vienna", false));
		ansArray.set(1, new Answer("Belmopan", false));

		addAmericanQToRepository("What is the capital of Croatia", ansArray); // Insert new American Q

		// 0-9 Q3
		ansArray.set(0, new Answer("Tallinn", true));
		ansArray.set(1, new Answer("Helsinki", false));

		addAmericanQToRepository("What is the capital of Estonia", ansArray); // Insert new American Q

		// 0-9 Q4
		ansArray.set(0, new Answer("Berlin", true));
		ansArray.set(1, new Answer("Munich", false));

		addAmericanQToRepository("What is the capital of Germany", ansArray); // Insert new American Q

		// 0-9 Q5
		ansArray.set(0, new Answer("Copenhagen", true));
		ansArray.set(1, new Answer("Odense", false));

		addAmericanQToRepository("What is the capital of Denemark", ansArray); // Insert new American Q

		// 0-9 Q6
		ansArray.set(0, new Answer("Stockholm", true));
		ansArray.set(1, new Answer("Dublin", false));

		addAmericanQToRepository("What is the capital of Sweden", ansArray); // Insert new American Q

		// 0-9 Q7
		ansArray.set(0, new Answer("Lima", true));
		ansArray.set(1, new Answer("Asuncion", false));

		addAmericanQToRepository("What is the capital of Peru", ansArray); // Insert new American Q

		// 0-9 Q8
		ansArray.set(0, new Answer("Zurich", false));
		ansArray.set(1, new Answer("Geneve", false));

		addAmericanQToRepository("What is the capital of Switzerland", ansArray); // Insert new
																					// American Q

		// 0-9 Q9
		ansArray.set(0, new Answer("Beijing", true));
		ansArray.set(1, new Answer("Hong Kong", false));

		addAmericanQToRepository("What is the capital of China", ansArray); // Insert new American Q

		// 10-19 Q10
		addOpenQToRepository("Who was the first prime minister of Israel", "David Ben Gurion");

		// 10-19 Q11
		addOpenQToRepository("In what year did World War II end", "1945");

		// 10-19 Q12
		addOpenQToRepository("What color do we get for mixing red, blue and yellow", "Black");

		// 10-19 Q13
		addOpenQToRepository("What is the name of Queen's lead singer", "freddie mercury");

		// 10-19 Q14
		addOpenQToRepository("In what year was the Yom Kipuer War", "1973");

		// 10-19 Q15
		addOpenQToRepository("What is the National fruit of India", "Mango");

		// 10-19 Q16
		addOpenQToRepository("Name the longest river on Earth", "Nile");

		// 10-19 Q17
		addOpenQToRepository("Who gave the universal law of gravitation", "Issac Newton");

		// 10-19 Q18
		addOpenQToRepository("Name the National game of the USA", "Baseball");

		// 10-19 Q19
		addOpenQToRepository("What is the National food of Israel", "Falafel");

	}

	// generate automatic Exam
	public void generateAutomaticExam(int numOfQ, String examName) throws DataIdenticalException {
		Exam automaticExam = new Exam(examName); // new exam
		isExamNameExists(automaticExam);

		int max = questionsArray.size() - 1, min = 0;
		for (int i = 0; i < numOfQ; i++) {
			Question question = questionsArray.get((int) (Math.random() * (max - min + 1) + min));
			try {
				if (question instanceof AmericanQuestion)
					if (((AmericanQuestion) question).getNumOfAnswers() > 4
							|| (((AmericanQuestion) question).getMoreThanOneRight()))
						throw new Exception();
				addQuestionToExam(automaticExam, question);
			} catch (Exception e) {
				i--;
			}
		}

		AddExamToArray(automaticExam);

	}

	public void saveExamToFile(int examNum) throws FileNotFoundException {
		Exam currExam = allExams.get(examNum);
		currExam.saveExamDeatails();

	}

	// Print Exams Name and Num
	public ArrayList<String> getListOfExams() throws DataNotCreatedYetException {// To String - print;
		if (this.allExams.size() == 0)
			throw new DataNotCreatedYetException("Exams");

		ArrayList<String> examList = new ArrayList();

		for (Exam ex : allExams)
			examList.add(ex.getExamName());

		return examList;
	}

	// Print Questions Name and Num
	public ArrayList<String> getListOfQuestions() {// To String - print;
		ArrayList<String> questionList = new ArrayList();

		for (Question question : questionsArray)
			questionList.add(question.getContent());

		return questionList;
	}

	// Get Num of exams
	public int getNumOfExams() {
		return this.allExams.size();
	}

	// Get Num of Questions
	public int getNumOfQ() {
		return this.questionsArray.size();
	}

	// Get Exam
	public Exam getExam(int numOfExam) {
		return allExams.get(numOfExam);
	}

	// check if question Exists
	public boolean isQuestionExist(Question q) {
		return questionsArray.contains(q);

	}

	// check if question Exists
	public boolean isExamNameExists(Exam exam) throws DataIdenticalException {
		if (allExams.contains(exam)) // Contains goes by equals which is over written to check names
			throw new DataIdenticalException("Exam"); // if for throw exception
		return false;

	}

	public Question addAmericanQToRepository(String qContent, ArrayList<Answer> ansArray)
			throws DataIdenticalException, GeneralSystemException {

		for (int i = 0; i < ansArray.size(); i++) // check if there is same answer in array
			for (int j = i + 1; j < ansArray.size(); j++)
				if (ansArray.get(i).equals(ansArray.get(j)))
					throw new DataIdenticalException("Answer");

		AmericanQuestion tempQ = new AmericanQuestion(qContent, ansArray);
		return checkIfCanAddQuestionAndAddIfPossible(tempQ);

	}

	public Question addAmericanQToRepository(String qContent, ArrayList<TextField> allAnswersArrayList,
			ArrayList<ComboBox<Boolean>> answersTFArrayList) throws DataIdenticalException, GeneralSystemException {
		ArrayList<Answer> ansArray = new ArrayList<>();
		for (int i = 0; i < allAnswersArrayList.size(); i++) {
			ansArray.add(new Answer(allAnswersArrayList.get(i).getText(), answersTFArrayList.get(i).getValue()));
		}

		return addAmericanQToRepository(qContent, ansArray);

	}

	// add American Question to questions array
	public Question addOpenQToRepository(String qContent, String aContent) throws DataIdenticalException {
		OpenQuestion tempQ = new OpenQuestion(qContent, aContent);
		return checkIfCanAddQuestionAndAddIfPossible(tempQ);

	}

	public Question checkIfCanAddQuestionAndAddIfPossible(Question q) throws DataIdenticalException {
		if (isQuestionExist(q))
			throw new DataIdenticalException("question");

		this.questionsArray.add(q);
		return q;
	}

	// add question to exam
	public boolean addQuestionToExam(Exam exam, Question question) throws DataIdenticalException {
		return exam.addQuestion(question);
	}

	// add question to exam
	public boolean addQuestionToExam(int selectedExam, int selectedQ) throws DataIdenticalException {
		return allExams.get(selectedExam).addQuestion(questionsArray.get(selectedQ));
	}

	// Check if the content exists
	public int isQuestionContentExist(String content) {
		for (int i = 0; i < questionsArray.size(); i++) // can't use question.equals because we match only string
														// content
			if (content.toLowerCase().equals(questionsArray.get(i).getContent().toLowerCase()))
				return i;

		return -1;
	}

	// update Q content
	public boolean updateQuestionContent(String content, int questionNum)
			throws DataIdenticalException, GeneralSystemException {
		Question question = getQuestion(questionNum); // we pass index and get question is considering not index
		int updateQuestion = isQuestionContentExist(content);
		if (updateQuestion != -1)
			if (question.getQuestionType().equals(questionsArray.get(updateQuestion).getQuestionType()))
				throw new DataIdenticalException("question");

		return question.updateContent(content);
	}

	// Get Question
	public Question getQuestion(int num) {
		return questionsArray.get(num);
	}

	public boolean deleteQuestion(int position) {
		questionsArray.remove(position);
		return true;
	}

	// update answer for Open Question
	public boolean updateOpenQAnswer(int questionNum, String aContent) {
		OpenQuestion question = (OpenQuestion) getQuestion(questionNum);
		return question.getAnswer().setContent(aContent);
	}

	// update answer for American Question
	public boolean updateAmericanQAnswer(int answerNum, String aContent, boolean isRight, int questionNum)
			throws DataIdenticalException {
		AmericanQuestion question = (AmericanQuestion) getQuestion(questionNum);
		Answer updatedAnswer = new Answer(aContent, isRight);
		Answer answer = question.getAnswer(answerNum);

		if (question.isAnswerExists(updatedAnswer)) {
			throw new DataIdenticalException("Answer");
		}

		if (!isRight && answer.isRight) { // if new answer is false but original was right
			question.RemoveAndDecreaseRightAnswersCounter(answer);
			answer.setFalse();
		} else if (isRight && !answer.isRight) { // if new answer is right but original was false
			question.increaseRightAnswersCounter(answer);
			answer.setRight();
		}

		question.checkForTrueAnswer();

		answer.setContent(aContent);
		return true;
	}

	// Select Exam
	public Exam selectExam(int examNum) throws DataNotCreatedYetException, InvalidUserInputException {
		if (allExams.size() == 0)
			throw new DataNotCreatedYetException("Exams");

		if (examNum < 1 || examNum > allExams.size())
			throw new InvalidUserInputException("Exams");

		return allExams.get(examNum - 1);
	}

	// Select Question
	public Question selectQuestion(int qNum) throws InvalidUserInputException {
		if (qNum < 1 || qNum > questionsArray.size())
			throw new InvalidUserInputException("question");

		return questionsArray.get(qNum - 1);
	}

	// Select American Answer
	public Answer selectAmericanAnswerandReturnAnswer(AmericanQuestion question, int aNum)
			throws InvalidUserInputException {

		checkAmericanAnswer(question, aNum);
		return question.getAnswer(aNum);

	}

	public void checkAmericanAnswer(AmericanQuestion question, int aNum) throws InvalidUserInputException {

		if (aNum < 1 || aNum > question.getNumOfAnswers() - 2)
			throw new InvalidUserInputException("answer");

	}

	public boolean deleteAmericanQAnswer(int qPosition, int aPosition) {
		AmericanQuestion q = (AmericanQuestion) getQuestion(qPosition);
		Answer answer = q.getAnswer(aPosition);
		if (q.getNumOfAnswers() == 3) {
			deleteQuestion(qPosition);
			return false;
		} else {
			return q.deleteAmericanAnswer(answer, aPosition);
		}
	}

	// Select 1 for American 2 for open
	public int checkValidRange(int typeN, int min, int max, int q) throws InvalidUserInputException {
		if (typeN < min || typeN > max)
			throw new InvalidUserInputException("Option");
		return q;

	}

	// to string
	public String printAllQuestions() {// To String - print;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("There are " + questionsArray.size() + " Questions: \n");
		for (Question q : questionsArray)
			sBuffer.append((questionsArray.indexOf(q) + 1) + ") " + q.toString() + "\n");

		return sBuffer.toString();

	}

	public String printExams() throws DataNotCreatedYetException {
		String str = "";
		if (allExams.size() == 0)
			throw new DataNotCreatedYetException("exams");
		for (Exam ex : allExams)
			str += ex.toString();
		return str;
	}

	public void exit() throws FileNotFoundException, IOException {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("questions.dat"));
		outFile.writeObject(questionsArray);
		outFile.close();
	}

	public void cloneExam(int examNum) throws CloneNotSupportedException {
		Exam currentExam = allExams.get(examNum);
		AddExamToArray(currentExam.clone());
		allExams.get(allExams.size() - 1).setExamName(currentExam.getExamName() + "  (duplicate)");

	}

	public String setOfAmericanAnswers() throws Exception {
		Set<Answer> americanAnswersSet = new Set<Answer>();
		for (Question q : questionsArray)
			if (q instanceof AmericanQuestion)
				((AmericanQuestion) q).addAnswersToSet(americanAnswersSet);
		return americanAnswersSet.tosString();
	}

	public void createManualExam(String name) throws DataIdenticalException {
		Exam newExam = new Exam(name);
		isExamNameExists(newExam);
		allExams.add(newExam);
	}

	
	public String getQuestionType(int questionNum) {
		return questionsArray.get(questionNum).getQuestionType();
	}

}
