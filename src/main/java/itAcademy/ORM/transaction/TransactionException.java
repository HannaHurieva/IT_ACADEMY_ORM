package itAcademy.ORM.transaction;

public class TransactionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TransactionException() {
    }

    /**
     * Constructs a TransactionException using the specified information.
     *
     * @param message The message explaining the exception condition
     */
    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(Throwable cause) {
        super(cause);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
