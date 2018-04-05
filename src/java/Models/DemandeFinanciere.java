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
public class DemandeFinanciere {
    private int id;
    private String date_depot;
    private String etat;
    private double montant_accorde;
    private int idContrat;
    
    public static String[] etats = new String[]{"En attente", "En cours d'examen", "Acceptée", "Refusée", "Archivée"};

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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
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
    
    public static DemandeFinanciere getDemandeFiById(int idC){
        DemandeFinanciere demandeFi = null;
        Connection conn = dbUtils.connect(); // On se connecte à la base
        try{
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM demande_financieres d"
                            + " WHERE d.id = ?");
            statement.setInt(1, idC);
            ResultSet result = statement.executeQuery();
            while(result.next()) {                
                demandeFi = new DemandeFinanciere();
                demandeFi.setId(result.getInt("id")); // On lui assigne son ID
                demandeFi.setDate_depot(result.getString("date_depot")); // Sa date de dépôt
                demandeFi.setEtat(result.getString("etat")); // Son État
                demandeFi.setMontant_accorde(result.getDouble("montant_accorde")); // Son montant
                demandeFi.setIdContrat(result.getInt("contrat_id"));  
            }
        } catch(SQLException e) {
            // Nothing
        }
        return demandeFi;
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
            object.setEtat(result.getString("etat")); // Son État
            object.setMontant_accorde(result.getDouble("montant_accorde")); // Son montant
            object.setIdContrat(idC);  
            objects.add(object);
            }
         
        } catch(SQLException e) {
            // Nothing
        }
        return objects;
    }
    
    public static Vector<DemandeFinanciere> getDemandeFiByProg(int idP){
        Vector<DemandeFinanciere> objects = new Vector<DemandeFinanciere>(); 
        Connection conn = dbUtils.connect(); // On se connecte à la base
        try{
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM demande_financieres d, contrats c"
                    + " WHERE d.contrat_id = c.id"
                    + " AND programme_id = ?");
            statement.setInt(1, idP);
            ResultSet result = statement.executeQuery();
            while(result.next())
            {  
            DemandeFinanciere object = new DemandeFinanciere();
            object.setId(result.getInt("id")); // On lui assigne son ID
            object.setDate_depot(result.getString("date_depot")); // Sa date de dépôt
            object.setEtat(result.getString("etat")); // Son État
            object.setMontant_accorde(result.getDouble("montant_accorde")); // Son montant
            object.setIdContrat(result.getInt("contrat_id")); 
            objects.add(object);
            }    
        } catch(SQLException e) {
            // Nothing
        }
        return objects;
    }
    
    public boolean add(){
        boolean add = false;
        Connection conn = dbUtils.connect();
        String sql = "INSERT INTO demande_financieres VALUES(null, ?,?,?,?);";
        try {
            PreparedStatement prepared = conn.prepareStatement(sql);
            prepared.setInt(1, this.idContrat);
            prepared.setString(2, this.date_depot);
            prepared.setString(3, this.etat);
            prepared.setDouble(4, this.montant_accorde);
            
            int nbInsert = prepared.executeUpdate();
            if(nbInsert > 0) add = true;
            prepared.close();
        } catch (SQLException ex) {
            add = false;
            System.out.println(ex);
        }
        dbUtils.disconnect(conn);
        return add;
    }
    
    public boolean edit() {
        boolean edit = false;
        Connection conn = dbUtils.connect();
        String sql = "UPDATE demande_financieres SET contrat_id = ?, etat = ?, montant_accorde = ? WHERE id = ?;";
        
        try {
            PreparedStatement prepared = conn.prepareStatement(sql);
            prepared.setInt(1, this.idContrat);
            prepared.setString(2, this.etat);
            prepared.setDouble(3, this.montant_accorde);
            prepared.setInt(4, this.id);
            
            int nbUpdate = prepared.executeUpdate();
            if(nbUpdate > 0) edit = true;
            prepared.close();
        } catch (SQLException ex) {
            edit = false;
        }
        dbUtils.disconnect(conn);
        return edit;
    }
    
    public boolean delete() {
        boolean delete = false;
        Connection conn = dbUtils.connect();
        
        String sql = "DELETE FROM demande_financieres WHERE id = ?";
        
        try {
            PreparedStatement prepared = conn.prepareStatement(sql);
            prepared.setInt(1, this.id);
            
            int nbDelete = prepared.executeUpdate();
            if(nbDelete > 0) delete = true;
            prepared.close();
        } catch (SQLException ex) {
            delete = false;
        }
        dbUtils.disconnect(conn);
        return delete;
    }
}
