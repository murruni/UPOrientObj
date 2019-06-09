package exception;

public class PersistenceException extends Exception {

	private static final long serialVersionUID = 1L;

	public PersistenceException() {
		super("Error de persistencia. Contacte al administrador");
	}

	public PersistenceException(String msg) {
		super(msg);
	}

	public PersistenceException(Throwable t) {
		super(t);
	}

	public PersistenceException(String msg, Throwable t) {
		super(msg, t);
	}
}
