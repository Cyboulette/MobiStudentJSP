package Models;

import Utils.dbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Quentin DESBIN, Arnaud HERTEL
 */
public class Programme {

    private int id;
    private String intitule;
    private String explication;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getExplication() {
        return explication;
    }

    public void setExplication(String explication) {
        this.explication = explication;
    }

    public String toString() {
        return this.intitule;
    }

    public static Vector<Programme> getAll() {
        Vector<Programme> objects = new Vector<Programme>(); // On va stocker tous nos objets récupérés dans un Vecteur
        Connection conn = dbUtils.connect(); // On se connecte à la base
        ResultSet result = dbUtils.query(conn, "SELECT * FROM programmes p"); // Première étape : tout récupérer de toutes les universités
        try {
            while (result.next()) {
                Programme object = new Programme(); // On crée notre objet
                object.setId(result.getInt("id")); // On lui assigne son ID
                object.setIntitule(result.getString("intitule")); // Son intitule
                object.setExplication(result.getString("explication")); // Son explication 

                objects.add(object);
            }
        } catch (SQLException e) {
            // Nothing
        }
        return objects;
    }
    
    public static Programme getProgrammeById(int idP){
        Connection conn = dbUtils.connect(); // On se connecte à la base
        Programme programme = null;
        try{
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM programmes p WHERE p.id = ?");
            statement.setInt(1, idP);
            ResultSet result = statement.executeQuery();
            while(result.next()) {  
                programme = new Programme();
                programme.setId(result.getInt("id")); // On lui assigne son ID
                programme.setIntitule(result.getString("intitule")); // Sa date de dépôt
                programme.setExplication(result.getString("explication")); // Son État
            }    
        } catch(SQLException e) {
            // Nothing
        }
        return programme;
    }
    
    public static Vector<Programme> getProgrammesByContrat(int idC){
        Vector<Programme> objects = new Vector<Programme>(); 
        Connection conn = dbUtils.connect(); // On se connecte à la base
        try{
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM programmes p, contrats c"
                    + " WHERE p.id = c.programme_id"
                    + " AND programme_id = ?");
            statement.setInt(1, idC);
            ResultSet result = statement.executeQuery();
            while(result.next())
            {  
            Programme object = new Programme();
            object.setId(result.getInt("id")); // On lui assigne son ID
            object.setIntitule(result.getString("intitule")); // Sa date de dépôt
            object.setExplication(result.getString("explication")); // Son État
            objects.add(object);
            }    
        } catch(SQLException e) {
            // Nothing
        }
        return objects;
    }
}
