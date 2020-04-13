package ua.com.foxminded.task_7_SQL.exeptions;

public class QueryExecutionException  extends RuntimeException{

    public QueryExecutionException() {
    }

    public QueryExecutionException(String message) {
        super(message);
    }

    public QueryExecutionException(String message, Exception cause) {
        super(message, cause);
    }

}
