package Utils;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qdesbin
 */
public class dbUtils {
    
    public static Connection connect(){
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobi_student?useUnicode=yes&characterEncoding=UTF-8", "root", "");
        } catch(Exception e) {
            // TODO
        }
        
        return c;
    }
    
    public static void disconnect(Connection conn) {
        try {
            conn.close();
        } catch(SQLException e) {
            // TODO
        }
    }
    
    public static ResultSet query(Connection conn, String querySQL) {
        ResultSet result = null;
        try {
            Statement statement = conn.createStatement();
            result = statement.executeQuery(querySQL);
            //statement.close();
        } catch (SQLException ex) {
            // TODO
        }
        
        return result;
    }
}
