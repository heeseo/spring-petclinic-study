package study.petclinic.exception;

public class VisitNotFoundException extends RuntimeException{
    public VisitNotFoundException() {
        super();
    }

    public VisitNotFoundException(String message) {
        super(message);
    }

    public VisitNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VisitNotFoundException(Throwable cause) {
        super(cause);
    }

    protected VisitNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
