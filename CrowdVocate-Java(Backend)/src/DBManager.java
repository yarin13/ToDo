import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;


public class DBManager {
    private final static String USERNAME = "root";
    private final static String PASSWORD = null;
    private final static String URL = "jdbc:mysql://localhost:3306/CrowdVocate";

    private static Connection connection = null;
    
    
    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            return connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
    public static void closeConnection() {

        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

    }
    
   

    public static int runExecute(String query) {

        PreparedStatement statement = null;

        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(query);
            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                try {
                    statement.close();
                    closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return -1;
    }
    public static void runSelect(String query, Consumer<ResultSet> con) {

        Statement statement = null;

        try {
            connection = DBManager.getConnection(); 
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                con.accept(res);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                try {
                    statement.close();
                    closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}
