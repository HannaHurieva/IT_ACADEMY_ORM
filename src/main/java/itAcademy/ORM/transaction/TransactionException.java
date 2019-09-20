package itAcademy.ORM.transaction;

import javax.persistence.PersistenceException;

public class TransactionException extends PersistenceException {
    /**
     * Constructs a TransactionException using the specified information.
     *
     * @param message The message explaining the exception condition
     */
    public TransactionException(String message) {
        super(message);
    }
}
