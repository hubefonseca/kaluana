package mobilis.api.exception;

public class ComponentUnavailableException extends Exception {

	private static final long serialVersionUID = 1L;

	public ComponentUnavailableException() {
		super();
	}
	
	public ComponentUnavailableException(String message) {
		super(message);
	}
	
}
