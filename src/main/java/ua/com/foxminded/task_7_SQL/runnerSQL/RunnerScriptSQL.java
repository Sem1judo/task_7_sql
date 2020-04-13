package ua.com.foxminded.task_7_SQL.runnerSQL;

import org.apache.ibatis.jdbc.ScriptRunner;
import ua.com.foxminded.task_7_SQL.connectorDB.ConnectorDB;
import ua.com.foxminded.task_7_SQL.exeptions.DBConnectionException;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public class RunnerScriptSQL {

    public void runScriptSQL(String script) {
        Reader reader;
        ScriptRunner scriptRunner;
        try (Connection con = ConnectorDB.getConnect()) {
            scriptRunner = new ScriptRunner(con);
            reader = new BufferedReader(new FileReader(script));
            scriptRunner.runScript(reader);
        } catch (SQLException | FileNotFoundException e) {
            new DBConnectionException("Problem with reading script", e);
        }
    }
}