package Models;

import Utils.dbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahertel
 */
public class Contrat {
    private int id;
    private int duree;
    private String etat;
    private String ordre;
    private String intituleD;
    private String intituleP;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getOrdre() {
        return ordre;
    }

    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    public String getIntituleD() {
        return intituleD;
    }

    public void setIntituleD(String intituleD) {
        this.intituleD = intituleD;
    }

    public String getIntituleP() {
        return intituleP;
    }

    public void setIntituleP(String intituleP) {
        this.intituleP = intituleP;
    }
    
    
    public static Vector<Contrat> getContratsByProgrammes(int idP){
        Vector<Contrat> objects = new Vector<Contrat>(); 
        Connection conn = dbUtils.connect(); // On se connecte à la base
        try{
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM programmes p, contrats c, diplomes d"
                    + " WHERE p.id = c.programme_id"
                    + " AND c.diplome_id = d.id"
                    + " AND c.programme_id = ?");
            statement.setInt(1, idP);
            ResultSet result = statement.executeQuery();
            while(result.next()) {  
                Contrat object = new Contrat();
                object.setId(result.getInt("id")); // On lui assigne son ID
                object.setDuree(result.getInt("duree"));
                object.setEtat(result.getString("etat"));
                object.setOrdre(result.getString("ordre"));
                object.setIntituleD(result.getString("d.intitule"));
                object.setIntituleP(result.getString("p.intitule"));
                objects.add(object);
            }    
        } catch(SQLException e) {
            // Nothing
        }

        return objects;
    }
    
    public static Vector<Contrat> getAll(){
        Vector<Contrat> objects = new Vector<Contrat>(); // On va stocker tous nos objets récupérés dans un Vecteur
        
        try {
            Connection conn = dbUtils.connect(); // On se connecte à la base
            ResultSet result = dbUtils.query(conn, "SELECT * FROM programmes p, contrats c, diplomes d"
                    + " WHERE p.id = c.programme_id"
                    + " AND c.diplome_id = d.id"); // Première étape : tout récupérer de toutes les universités
            
            while(result.next()) {
                Contrat object = new Contrat(); // On crée notre objet
                object.setId(result.getInt("c.id")); // On lui assigne son ID
                object.setDuree(result.getInt("c.duree"));
                object.setEtat(result.getString("c.etat"));
                object.setOrdre(result.getString("c.ordre"));
                object.setIntituleD(result.getString("d.intitule"));
                object.setIntituleP(result.getString("p.intitule"));
                objects.add(object);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Contrat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }
    
}
