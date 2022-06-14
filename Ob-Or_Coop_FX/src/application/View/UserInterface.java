package application.View;


import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.Vector;



import application.Listeners.UIListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class UserInterface {
	private Vector<UIListener> allListeners = new Vector<UIListener>();
	BorderPane generalBorderPane = new BorderPane();
	int selectedExam;

	private Stage mainStage;
	
	public UserInterface(Stage mainStage) {
		
		this.mainStage = mainStage;
		mainStage.setTitle("Manu");
		
		Scene generalScene = new Scene(generalBorderPane, 1000, 1000);
		
		generalBorderPane.setPadding(new Insets(10, 10, 10, 10));
		
        
        generalBorderPane.setTop(menuPane()); //Set top of the window to menu
	
		
		mainStage.setScene(generalScene);
		mainStage.show();

		
	}
	
	
	public Pane menuPane() {
			VBox menu = new VBox();
			HBox line1 = new HBox();
			HBox line2 = new HBox();
			HBox line3 = new HBox();
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
					generalBorderPane.setCenter(presentInfo());
				}
			
			});
			
			
			addQuestionButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					
					Label selectLabel = new Label("Select:");
					Button addNewToExam = new Button("Add new question to exam"); 
					Button addToExamFromRepo = new Button("Add question to exam from repository");
					Button addToRepo = new Button("Add new question to repository"); 
					
					addNewToExam.setOnAction(new EventHandler<ActionEvent>() {
					
						@Override
						public void handle(ActionEvent arg0) {
							for(UIListener listener : allListeners)
								listener.selectAndDoExamFromUI(2);
						}
					
					});
					
					addToExamFromRepo.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent arg0) {
							//TODO-----------------------------
						}
					
					});
					
					addToRepo.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent arg0) {
							//TODO-----------------------------
						}
					
					});
					
					
					VBox form = new VBox();
					form.getChildren().addAll(selectLabel, addNewToExam, addToExamFromRepo, addToRepo);
					generalBorderPane.setCenter(form);
					
					
				}
			
			});
			
			
			createAutoExamButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					for(UIListener listener : allListeners)
						listener.createAutoExam();
				}
			
			});
			
			
			ExitButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
				mainStage.close();
				}
			
			});
			
			line1.getChildren().addAll(chooseTask);
			
			line2.getChildren().addAll(presentInfoButton, addQuestionButton, updateQuestionButton,
					updateAnswerButton);
			
			line3.getChildren().addAll( deletAnswerButton, createManualExamButton, createAutoExamButton,
					duplicateExamButton, createAndShowSetButton, ExitButton);
			
			menu.getChildren().addAll(line1, line2, line3);
			
			
			menu.setPadding(new Insets(10, 10, 10, 10));
			menu.setMaxHeight(300);
			
			
			
		return menu;
			 
		
	}
	
	
	public Pane presentInfo() {
		VBox req = new VBox();
		
		Label whatWouldYouView = new Label("What would you like to view:");
		Button repository = new Button("Repository Questions ");
		Button exam = new Button("Exams created");
		
		
		
		req.getChildren().addAll(whatWouldYouView ,repository, exam);
		req.setPadding(new Insets(10));
		
		repository.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent arg0) {
				for(UIListener listener : allListeners)
					listener.printAllQuestionsFromUI();
			}
		
		
		
		});
		
		exam.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				for(UIListener listener : allListeners)
					listener.selectAndDoExamFromUI(1);
					
					
			}
		
		
		
		});
		
		
		return req;
		
	}
	
	
	
	public void selectExamAndDo(ArrayList<String> examList, int mission) {
		
		
		Label messageLabel = new Label("Choose Exam number:");
		VBox vBox = new VBox();
		vBox.getChildren().add(messageLabel);
		
		
		for(String examName : examList ) {
			Button b = new Button(examName);
			vBox.getChildren().add(b);
			
			if(mission == 1) { //print exam
			b.setOnAction(new EventHandler<ActionEvent>() {
			
				@Override
				public void handle(ActionEvent arg0) {
					for(UIListener listener : allListeners)
						listener.printExamFromUI(examList.indexOf(examName));
				}
			
			});
	
		}
		
		
		if(mission == 2) { //add question to exam
			b.setOnAction(new EventHandler<ActionEvent>() {
			
				@Override
				public void handle(ActionEvent arg0) {
					Label selectLabel = new Label("Select:");
					RadioButton openQueButton = new RadioButton("Open Question");
					RadioButton americanQueButton = new RadioButton("American Question"); 
					
					ToggleGroup questionTypeToggleGroup = new ToggleGroup();
					openQueButton.setToggleGroup(questionTypeToggleGroup);
					americanQueButton.setToggleGroup(questionTypeToggleGroup);
					
					Label howManyLabel = new Label("Num of answers:");
					ComboBox<Integer> numOfAnswersAmerican = new ComboBox<>();
					numOfAnswersAmerican.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);
					
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
										for(UIListener listener:allListeners)
											listener.addOpenQuesionToExamFromUI(qContent.getText(), aContent.getText(), examList.indexOf(examName));
										
									}
								});
								
								
								questionForm .getChildren().addAll(qContentLabel, qContent, aContentLabel, aContent, addQButton);
								generalBorderPane.setCenter(questionForm);
								
								
							}
							else if(americanQueButton.isSelected()){
								if(numOfAnswersAmerican.getValue() == null) {
									alertMessage("Cannot add american question with no answers!");
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
												listener.addAmericanQuestionToExam(qContent.getText(),allAnswersArrayList, AnswersTFArrayList, examList.indexOf(examName));
											}
											
										}
									});
									
									questionForm .getChildren().addAll(qContentLabel, qContent, aContentLabel);
									for(int i = 0; i<allAnswersArrayList.size(); i++) {
										questionForm.getChildren().add(allAnswersArrayList.get(i));
										questionForm.getChildren().add(AnswersTFArrayList.get(i));
									}
									questionForm.getChildren().add(addQButton);
									generalBorderPane.setCenter(questionForm);
									
								}
							}
							
							
							
						}
					
					});
					
					
					VBox form = new VBox();
					form.getChildren().addAll(selectLabel, openQueButton, americanQueButton, howManyLabel,
							numOfAnswersAmerican, exButton);
					generalBorderPane.setCenter(form);
				}
			
			});
		}
		
		
		
		
		
	}
	
		
		
		generalBorderPane.setCenter(vBox);
	}
	
	
	
	
	public void show(String toPrint) {
		Label toPrintLabel = new Label(toPrint);
		VBox vBox = new VBox();
		
		ScrollPane scroller = new ScrollPane(vBox);
	//	scroller.setFitToWidth(true);
		
        vBox.getChildren().addAll(toPrintLabel);
        
        
        generalBorderPane.setCenter(scroller);
		
	}
	
	
	public void registerListener(UIListener newListener) {
		allListeners.add(newListener);
	}
	
	public void alertMessage(String toPrint) {
		Label messageLabel = new Label(toPrint);
		VBox vBox = new VBox();
		vBox.getChildren().addAll(messageLabel);
		generalBorderPane.setCenter(vBox);
	}

	
	
	
	
}
