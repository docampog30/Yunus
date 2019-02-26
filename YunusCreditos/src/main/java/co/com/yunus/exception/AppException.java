package co.com.yunus.exception;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = -3065959894389465288L;

	public AppException() {
		super();
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public AppException(Throwable throwable) {
		super(throwable);
	}

}
