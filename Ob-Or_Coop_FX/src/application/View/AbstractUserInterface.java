package application.View;

import java.io.FileNotFoundException;
import java.util.Vector;

import application.Exceptions.DataNotCreatedYetException;
import application.Exceptions.InvalidUserInputException;
import application.Listeners.UIListener;

public interface AbstractUserInterface {
	void registerListener(UIListener listener);

	void PresentInfo(Vector<UIListener> allListeners)
			throws InvalidUserInputException, DataNotCreatedYetException, FileNotFoundException;
}
