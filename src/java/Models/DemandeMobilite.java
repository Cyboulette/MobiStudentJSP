package Models;

import Utils.dbUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Arnaud
 */
public class DemandeMobilite {
    private int id;
    private String date_depot;
    private String etat;
    private int idEtudiant;
    private int numEtudiant;
    private int idDiplome;
    private String intituleDiplome;
    private String nomUniv;
    
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

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public int getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(int numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public int getIdDiplome() {
        return idDiplome;
    }

    public void setIdDiplome(int idDiplome) {
        this.idDiplome = idDiplome;
    }

    public String getIntituleDiplome() {
        return intituleDiplome;
    }

    public void setIntituleDiplome(String intituleDiplome) {
        this.intituleDiplome = intituleDiplome;
    }

    public String getNomUniv() {
        return nomUniv;
    }

    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
    }
    
    public static DemandeMobilite getMobiById(int idM){
        DemandeMobilite mobilite = null;
        Connection conn = dbUtils.connect(); // On se connecte à la base
        try{
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM demande_mobilites de, etudiants e, diplomes d"
                + " WHERE etudiant_id = e.id"
                + " AND de.diplome_id = d.id"
                + " AND de.id = ?");
            statement.setInt(1, idM);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                mobilite = new DemandeMobilite();
                mobilite.setId(result.getInt("id"));
                mobilite.setIdEtudiant(result.getInt("etudiant_id"));
                mobilite.setNumEtudiant(result.getInt("num_etudiant"));
                mobilite.setIdDiplome(result.getInt("diplome_id"));
                mobilite.setIntituleDiplome(result.getString("intitule"));
                mobilite.setDate_depot(result.getString("date_depot"));
                mobilite.setEtat(result.getString("etat"));
            }
        } catch(SQLException e) {
            // Nothing
        }
        
        return mobilite;
    }
    
    public static Vector<DemandeMobilite> getMobisByEtud(int idEtudiant){
        Vector<DemandeMobilite> lesMobis = new Vector<DemandeMobilite>();// On va stocker tous nos objets récupérés dans un Vecteur
        Connection conn = dbUtils.connect(); // On se connecte à la base
        try{
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM demande_mobilites de, etudiants e, diplomes d"
                + " WHERE etudiant_id = e.id"
                + " AND de.diplome_id = d.id"
                + " AND e.id = ?");
            statement.setInt(1, idEtudiant);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                DemandeMobilite object = new DemandeMobilite();
                object.setId(result.getInt("id"));
                object.setIdEtudiant(result.getInt("etudiant_id"));
                object.setNumEtudiant(result.getInt("num_etudiant"));
                object.setIdDiplome(result.getInt("diplome_id"));
                object.setIntituleDiplome(result.getString("intitule"));
                object.setDate_depot(result.getString("date_depot"));
                object.setEtat(result.getString("etat"));
                lesMobis.add(object);
            }
        } catch(SQLException e) {
            // Nothing
        }
        return lesMobis;
    }
    
    public static Vector<DemandeMobilite> getMobisByDiplome(int idD){
        Vector<DemandeMobilite> lesMobis = new Vector<DemandeMobilite>();// On va stocker tous nos objets récupérés dans un Vecteur
        Connection conn = dbUtils.connect(); // On se connecte à la base
        try{
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM demande_mobilites de, etudiants e, diplomes d"
                + " WHERE etudiant_id = e.id"
                + " AND de.diplome_id = d.id"
                + " AND d.id = ?"); // Récupérer les demandes de mobilités par Diplômes
            statement.setInt(1, idD);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                DemandeMobilite object = new DemandeMobilite();
                object.setId(result.getInt("id"));
                object.setIdEtudiant(result.getInt("etudiant_id"));
                object.setNumEtudiant(result.getInt("num_etudiant"));
                object.setIdDiplome(result.getInt("diplome_id"));
                object.setIntituleDiplome(result.getString("intitule"));
                object.setDate_depot(result.getString("date_depot"));
                object.setEtat(result.getString("etat"));
                lesMobis.add(object);
            }
        } catch(SQLException e) {
            // Nothing
        }
        return lesMobis;
    }
    public static Vector<DemandeMobilite> getMobisByUniv(int id){
        Vector<DemandeMobilite> lesMobis = new Vector<DemandeMobilite>();// On va stocker tous nos objets récupérés dans un Vecteur
        Connection conn = dbUtils.connect(); // On se connecte à la base
        try{
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM demande_mobilites de, universites u, diplomes d, etudiants e"
                + " WHERE de.diplome_id = d.id"
                + " AND d.universite_id = u.id"
                + " AND e.id = de.etudiant_id"
                + " AND u.id = ?"); // Récupérer les demandes de mobilités par Universités
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                DemandeMobilite object = new DemandeMobilite();
                object.setId(result.getInt("id"));
                object.setIdDiplome(result.getInt("diplome_id"));
                object.setIntituleDiplome(result.getString("intitule"));
                object.setNomUniv(result.getString("nom"));
                object.setDate_depot(result.getString("date_depot"));
                object.setEtat(result.getString("etat"));
                object.setNumEtudiant(result.getInt("num_etudiant"));
                lesMobis.add(object);
            }
        } catch(SQLException e) {
            // Nothing
        }
        return lesMobis;
    }
    
    public boolean add() {
        boolean add = false;
        Connection conn = dbUtils.connect();
        String sql = "INSERT INTO demande_mobilites VALUES(null, ?,?,?,?);";
        
        try{
            PreparedStatement prepared = conn.prepareStatement(sql);
            prepared.setInt(1, this.idEtudiant);
            prepared.setInt(2, this.idDiplome);
            prepared.setString(3, this.date_depot);
            prepared.setString(4, this.etat);
            
            int nbInsert = prepared.executeUpdate();
            if(nbInsert > 0) add = true;
            prepared.close();
        }catch (SQLException ex){
            add = false;
        }
        dbUtils.disconnect(conn);
        return add;
    }
    
    public boolean edit() {
        boolean edit = false;
        Connection conn = dbUtils.connect();
        String sql = "UPDATE demande_mobilites SET etudiant_id = ?, diplome_id = ?, etat = ? WHERE id = ?;";
        
        try {
            PreparedStatement prepared = conn.prepareStatement(sql);
            prepared.setInt(1, this.idEtudiant);
            prepared.setInt(2, this.idDiplome);
            prepared.setString(3, this.etat);
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
        
        String sql = "DELETE FROM demande_mobilites WHERE id = ?";
        
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
