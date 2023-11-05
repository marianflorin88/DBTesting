package connection;

import database.DbConnectionHandler;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;

public class TestDbConnection {

    @Test
    public void testDbConnectionIsSuccessful() throws SQLException {
        DbConnectionHandler dbConnectionHandler = new DbConnectionHandler();
        Connection con = dbConnectionHandler.connect();

        try {
            dbConnectionHandler.getAllUsers(con);
            con.close();
        } catch(SQLException e) {
            String errorCode = e.getSQLState();
            if(errorCode == "08000") {
                throw e;
            } else if(errorCode == "42601") {
                throw e;
            } else {
                System.out.printf("SQL failed with error code: %s%n", errorCode);
            }
        }
    }
}
