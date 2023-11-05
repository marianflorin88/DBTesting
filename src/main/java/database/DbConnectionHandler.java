package database;
import data.User;
import org.postgresql.ds.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnectionHandler {

    private static final DataSource dataSource = createDataSource();

    private static DataSource createDataSource() {
        final String url = "jdbc:postgresql://localhost:5432/usermanagement?user=postgres&password=password";
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(url);
        return dataSource;
    }

    public Connection connect() throws SQLException {
        return dataSource.getConnection();
    }

    public void getAllUsers(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("select * from users");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            System.out.printf("id: %d, name: %s, job: %s, email: %s, avatar: %s, location: %s%n",
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("job"),
                    rs.getString("email"),
                    rs.getString("avatar"),
                    rs.getString("location")
                    );
        }
    }

    public User getUserByEmail(Connection conn, String userEmail) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("select * from users where email = ?");
        stmt.setString(1, userEmail);

        ResultSet rs = stmt.executeQuery();

        if(rs.next()) {
           long id = rs.getLong("id");
           String name = rs.getString("name");
           String job = rs.getString("job");
           String email = rs.getString("email");
           String avatar = rs.getString("avatar");
           String location = rs.getString("location");

           return new User(id, name, job, email, avatar, location);
        }

        return null;
    }

    public void insertUser(Connection conn, User user) throws SQLException {
        String query = "INSERT INTO users (id, name, job, email, avatar, location) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setLong(1, user.getId());
        stmt.setString(2, user.getName());
        stmt.setString(3, user.getJob());
        stmt.setString(4, user.getEmail());
        stmt.setString(5, user.getAvatar());
        stmt.setString(6, user.getLocation());
        stmt.executeUpdate();
    }

    public void deleteUserByEmail(Connection conn, String userEmail) throws SQLException {
        String query = "DELETE FROM users WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, userEmail);
        stmt.executeUpdate();
    }

    public void updateUser(Connection conn, User user) throws SQLException {
        String query = "UPDATE users SET name = ?, job = ?, avatar = ?, location = ? WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getJob());
        stmt.setString(3, user.getAvatar());
        stmt.setString(4, user.getLocation());
        stmt.setString(5, user.getEmail());
        stmt.executeUpdate();
    }
}
