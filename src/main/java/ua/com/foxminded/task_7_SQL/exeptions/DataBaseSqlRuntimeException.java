package ua.com.foxminded.task_7_SQL.exeptions;

public class DataBaseSqlRuntimeException extends RuntimeException {
    public DataBaseSqlRuntimeException() {
    }

    public DataBaseSqlRuntimeException(String message) {
        super(message);
    }

    public DataBaseSqlRuntimeException(String message, Exception cause) {
        super(message, cause);
    }

}
