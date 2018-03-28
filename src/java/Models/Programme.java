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
}
