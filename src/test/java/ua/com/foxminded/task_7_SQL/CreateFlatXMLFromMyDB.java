package ua.com.foxminded.task_7_SQL;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import ua.com.foxminded.task_7_SQL.connectorDB.ConnectorDB;

import java.io.FileOutputStream;
import java.sql.Connection;

public class CreateFlatXMLFromMyDB {

    public static void main(String[] args) throws Exception
    {
        // database connection
        Connection jdbcConnection = ConnectorDB.getConnect();
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        // full database export
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("dataset.xml"));


    }
}
