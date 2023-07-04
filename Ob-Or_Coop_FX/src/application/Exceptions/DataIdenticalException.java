package application.Exceptions;

public class DataIdenticalException extends GeneralSystemException {

	public DataIdenticalException(String data) {
		super("There is already  " + data + "  with the same content");
	}

}
