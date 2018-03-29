package Models;

import Utils.dbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


/**
 *
 * @author ahertel
 */
public class DemandeFinanciere {
    private int id;
    private String date_depot;
    private char etat;
    private double montant_accorde;
    private int idContrat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_depot() {
        return date_depot;
    }

    public void setDate_depot(String date_depot) {
        this.date_depot = date_depot;
    }

    public char getEtat() {
        return etat;
    }

    public void setEtat(char etat) {
        this.etat = etat;
    }

    public double getMontant_accorde() {
        return montant_accorde;
    }

    public void setMontant_accorde(double montant_accorde) {
        this.montant_accorde = montant_accorde;
    }

    public int getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(int id) {
        this.idContrat = id;
    }
    
    public static void add(){
        
    }
    public static Vector<DemandeFinanciere> getDemandeFiByContrat(int idC){
        Vector<DemandeFinanciere> objects = new Vector<DemandeFinanciere>(); 
        Connection conn = dbUtils.connect(); // On se connecte à la base
        try{
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM demande_financieres d"
                            + " WHERE contrat_id = ?");
            statement.setInt(1, idC);
            ResultSet result = statement.executeQuery();
            while(result.next())
            {                
            DemandeFinanciere object = new DemandeFinanciere();
            object.setId(result.getInt("id")); // On lui assigne son ID
            object.setDate_depot(result.getString("date_depot")); // Sa date de dépôt
            object.setEtat(result.getString("etat").charAt(0)); // Son État
            object.setMontant_accorde(result.getDouble("montant_accorde")); // Son montant
            object.setIdContrat(idC);  
            objects.add(object);
            }
         
        } catch(SQLException e) {
            // Nothing
        }
        return objects;
    }
    
    public static Vector<DemandeFinanciere> getDemandeFiByContratByProg(int idC, int idP){
        Vector<DemandeFinanciere> objects = new Vector<DemandeFinanciere>(); 
        Connection conn = dbUtils.connect(); // On se connecte à la base
        try{
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM demande_financieres d, contrats c"
                    + " WHERE d.contrat_id = c.id"
                    + " AND contrat_id = ?"
                    + " AND programme_id = ?");
            statement.setInt(1, idC);
            statement.setInt(2, idP);
            ResultSet result = statement.executeQuery();
            while(result.next())
            {  
            DemandeFinanciere object = new DemandeFinanciere();
            object.setId(result.getInt("id")); // On lui assigne son ID
            object.setDate_depot(result.getString("date_depot")); // Sa date de dépôt
            object.setEtat(result.getString("etat").charAt(0)); // Son État
            object.setMontant_accorde(result.getDouble("montant_accorde")); // Son montant
            object.setIdContrat(idC); 
            objects.add(object);
            }    
        } catch(SQLException e) {
            // Nothing
        }
        return objects;
    }
        
}
