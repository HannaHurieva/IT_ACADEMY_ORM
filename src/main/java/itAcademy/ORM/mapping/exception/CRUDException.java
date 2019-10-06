package itAcademy.ORM.mapping.exception;

public class CRUDException extends RuntimeException {

    public CRUDException() {
    }

    /**
     * Constructs a CRUDException using the specified information.
     *
     * @param message The message explaining the exception condition
     */

    public CRUDException(String message) {
        super(message);
    }

    public CRUDException(String message, Throwable cause) {
        super(message, cause);
    }

    public CRUDException(Throwable cause) {
        super(cause);
    }

    public CRUDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
