package users;

import data.User;
import database.DbConnectionHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static data.DataGenerator.*;

public class TestInsertNewUserIntoDb {

    private DbConnectionHandler dbConnectionHandler;
    private Connection con;
    private User actualUserData;
    private User expectedUserData;
    private Long expectedId = 21L;
    private String expectedName = generateRandomName();
    private String expectedEmailAddress = generateEmailAddress(expectedName);
    private String expectedJob = generateJob();
    private String expectedAvatar = generateAvatar();
    private String expectedLocation = generateLocation();

    @Before
    public void setup() throws SQLException {
        dbConnectionHandler = new DbConnectionHandler();
        con = dbConnectionHandler.connect();
        actualUserData = new User(expectedId, expectedName, expectedJob, expectedEmailAddress, expectedAvatar, expectedLocation);
        try {
            dbConnectionHandler.insertUser(con, actualUserData);
            System.out.println("Created user with email address: " + expectedEmailAddress);
            expectedUserData = dbConnectionHandler.getUserByEmail(con, expectedEmailAddress);
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
    public void testUserInsertedInDb() {
        Assert.assertNotNull(expectedUserData);
        Assert.assertEquals(expectedUserData.getEmail(), expectedEmailAddress);
    }

    @After
    public void cleanup() throws SQLException {
        try {
            dbConnectionHandler.deleteUserByEmail(con, expectedEmailAddress);
            System.out.println("Deleted user with email address: " + expectedEmailAddress);
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
