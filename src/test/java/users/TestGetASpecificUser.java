package users;

import data.User;
import database.DbConnectionHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TestGetASpecificUser {

    private DbConnectionHandler dbConnectionHandler;
    private Connection con;
    private User actualUserData;
    private String expectedEmailAddress = "user6@example.com";

    @Before
    public void setup() throws SQLException {
        dbConnectionHandler = new DbConnectionHandler();
        con = dbConnectionHandler.connect();

        try {
            actualUserData = dbConnectionHandler.getUserByEmail(con, expectedEmailAddress);
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

    @Test
    public void testGetUserFromDbUsingEmailAddress() {
        Assert.assertNotNull(actualUserData);
        Assert.assertEquals(actualUserData.getEmail(), expectedEmailAddress);
    }
}
