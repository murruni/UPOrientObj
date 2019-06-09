package exception;

public class UserException extends Exception {
	public UserException() {
		super("Error de aplicación. Contacte al administrador");
	}

	public UserException(String msg) {
		super(msg);
	}

	public UserException(Throwable t) {
		super(t);
	}

	public UserException(String msg, Throwable t) {
		super(msg, t);
	}
}
