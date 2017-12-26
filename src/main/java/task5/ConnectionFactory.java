package task5;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {

    Connection getConnection(String schemaName, String login, String password) throws SQLException;
    Connection getConnection() throws SQLException;

}
