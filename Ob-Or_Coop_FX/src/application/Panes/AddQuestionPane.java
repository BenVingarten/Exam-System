package application.Panes;

import java.util.ArrayList;
import java.util.Vector;

import application.Listeners.UIListener;
import application.View.UserInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class AddQuestionPane extends AbstractPane {
	int examNum;
	
public AddQuestionPane(BorderPane generalPane, Vector<UIListener> allListeners, UserInterface userInterface) {
		super(generalPane, allListeners, userInterface);
		
	}

	public void createAddQuestionPane(boolean isManualExam) {
		VBox form = new VBox();
		
		Label selectLabel = new Label("Select:");
		Button addNewToExam = new Button("Add new question to exam");
		Button addToExamFromRepo = new Button("Add question to exam from repository");
		
		form.getChildren().addAll(selectLabel, addNewToExam, addToExamFromRepo);
		
		if(!isManualExam) {
			Button addToRepo = new Button("Add new question to repository");
			form.getChildren().add(addToRepo);
			addToRepo.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					addQuestionForm(false, null, isManualExam);
				}
				
			});
		}
		

		addNewToExam.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if(!isManualExam) {
				for (UIListener listener : allListeners)
					listener.listOfExams(userInterface.examPane, "addNewQuestion");
				} else {
					addQuestionForm(-1, isManualExam);
				}
			}

		});

		addToExamFromRepo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if(!isManualExam) {
				for (UIListener listener : allListeners)
					listener.listOfExams(userInterface.examPane, "addExistingQuestion");
				} else {
					for(UIListener listener : allListeners)
						listener.listOfQuestions(userInterface.repositoryPane, "addExistingQuestion", isManualExam);
				}
			}

		});


		
		generalPane.setCenter(form);		
	}

	
	
	public void addQuestionForm(boolean addToExam, Integer examNum, boolean isManualExam) {
		Label selectLabel = new Label("Select:");
		RadioButton openQueButton = new RadioButton("Open Question");
		RadioButton americanQueButton = new RadioButton("American Question"); 
		
		ToggleGroup questionTypeToggleGroup = new ToggleGroup();
		openQueButton.setToggleGroup(questionTypeToggleGroup);
		americanQueButton.setToggleGroup(questionTypeToggleGroup);
		
		Label howManyLabel = new Label("Num of answers:");
		ComboBox<Integer> numOfAnswersAmerican = new ComboBox<>();
		numOfAnswersAmerican.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);
		
		openQueButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				numOfAnswersAmerican.setDisable(true);
			}	
		});
		
		americanQueButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				numOfAnswersAmerican.setDisable(false);
			}	
		});
		
		
		
		
		Button exButton = new Button("Execute");
		exButton.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent arg0) {
				if(openQueButton.isSelected()) {
					VBox questionForm = new VBox();
					Label qContentLabel = new Label("Insert question content:");
					TextField qContent = new TextField();
					Label aContentLabel = new Label("Insert answer content:");
					TextField aContent = new TextField();
					Button addQButton = new Button("Add Question");
					
					addQButton.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent arg0) {
							for(UIListener listener:allListeners) {
								listener.addOpenQuesionFromUI(qContent.getText(), aContent.getText());
								if(addToExam)
									listener.addQuestionToExam(examNum, -1, isManualExam); //if we want the last question added to repository 
							}
							
						}
					});
					
					
					questionForm .getChildren().addAll(qContentLabel, qContent, aContentLabel, aContent, addQButton);
					generalPane.setCenter(questionForm);
					
					
				}
				else if(americanQueButton.isSelected()){
					if(numOfAnswersAmerican.getValue() == null) {
						userInterface.alertMessage("Cannot add american question with no answers!");
					}else {
						VBox questionForm = new VBox();
						Label qContentLabel = new Label("Insert question content:");
						TextField qContent = new TextField();
						Label aContentLabel = new Label("Insert answers content:");
						ArrayList<TextField> allAnswersArrayList = new ArrayList();
						ArrayList<ComboBox<Boolean>> AnswersTFArrayList = new ArrayList();
						for(int i = 0; i < numOfAnswersAmerican.getValue(); i++) {
							TextField aContent = new TextField();
							allAnswersArrayList.add(aContent);
							ComboBox<Boolean> answerTF = new ComboBox<>();
							answerTF.getItems().addAll(true, false);
							AnswersTFArrayList.add(answerTF);
							
						}
						
						Button addQButton = new Button("Add Question");
						
						addQButton.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent arg0) {
								for(UIListener listener:allListeners) {
									listener.addAmericanQuestionFromUI(qContent.getText(),allAnswersArrayList, AnswersTFArrayList);
									if(addToExam)
										listener.addQuestionToExam(examNum, -1, isManualExam); //if we want the last question added to repository 
								
								}
								
							}
						});
						
						questionForm .getChildren().addAll(qContentLabel, qContent, aContentLabel);
						for(int i = 0; i<allAnswersArrayList.size(); i++) {
							questionForm.getChildren().add(allAnswersArrayList.get(i));
							questionForm.getChildren().add(AnswersTFArrayList.get(i));
						}
						questionForm.getChildren().add(addQButton);
						generalPane.setCenter(questionForm);
						
					}
				}
				
				
				
			}
		
		});
		
		
		VBox form = new VBox();
		form.getChildren().addAll(selectLabel, openQueButton, americanQueButton, howManyLabel,
				numOfAnswersAmerican, exButton);
		generalPane.setCenter(form);
	}
	
	
	public void addQuestionForm(int exam, boolean isManualExam) {
		addQuestionForm(true, exam, isManualExam);
		
		
	}
	
	public void setExamNum(int num) {
		examNum = num;
	}
	
	public void addExistingQuestionToExam(int questionNum, boolean isManualExam) {
		for(UIListener listener : allListeners)
			listener.addQuestionToExam(examNum, questionNum, isManualExam);
	}
}
