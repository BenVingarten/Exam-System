package application.Panes;

import java.util.Vector;

import application.Listeners.UIListener;
import application.View.UserInterface;
import javafx.scene.layout.BorderPane;

public abstract class AbstractPane {
	BorderPane generalPane;
	Vector<UIListener> allListeners;
	UserInterface userInterface;
	
	public AbstractPane(BorderPane generalPane, Vector<UIListener> allListeners, UserInterface userInterface) {
		this.generalPane = generalPane;
		this.allListeners = allListeners;
		this.userInterface = userInterface;
		
	}

}
