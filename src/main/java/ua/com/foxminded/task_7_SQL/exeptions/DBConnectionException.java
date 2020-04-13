package ua.com.foxminded.task_7_SQL.exeptions;

public class DBConnectionException extends RuntimeException {
    public DBConnectionException() {
    }

    public DBConnectionException(String message) {
        super(message);
    }

    public DBConnectionException(String message, Exception cause) {
        super(message, cause);
    }

}

