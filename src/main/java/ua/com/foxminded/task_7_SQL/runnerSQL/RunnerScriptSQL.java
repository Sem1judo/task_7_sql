package ua.com.foxminded.task_7_SQL.runnerSQL;

import org.apache.ibatis.jdbc.ScriptRunner;
import ua.com.foxminded.task_7_SQL.connectorDB.ConnectorDB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;

public class RunnerScriptSQL {


    private static final String SCRIPT_SQL = new File("src/main/resources/schoolDB.sql").getAbsolutePath();


    public static void main(String args[]) throws Exception {
        Connection con = ConnectorDB.getConnect();
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader(SCRIPT_SQL));
        sr.runScript(reader);
    }

}