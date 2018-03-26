package Models;

import Utils.dbUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Arnaud
 */
public class DemandeMobilite {
    private int id, etudiant_id, diplome_id, num_etudiant;
    private Date date_depot;
    private char etat;
    private String intituleDiplome, nomUniv;

    public String getNomUniv() {
        return nomUniv;
    }

    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
    }

    public int getNum_etudiant() {
        return num_etudiant;
    }

    public void setNum_etudiant(int num_etudiant) {
        this.num_etudiant = num_etudiant;
    }

    public String getIntituleDiplome() {
        return intituleDiplome;
    }

    public void setIntituleDiplome(String intituleDiplome) {
        this.intituleDiplome = intituleDiplome;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtudiant_id() {
        return etudiant_id;
    }

    public void setEtudiant_id(int etudiant_id) {
        this.etudiant_id = etudiant_id;
    }

    public int getDiplome_id() {
        return diplome_id;
    }

    public void setDiplome_id(int diplome_id) {
        this.diplome_id = diplome_id;
    }

    public Date getDate_depot() {
        return date_depot;
    }

    public void setDate_depot(Date date_depot) {
        this.date_depot = date_depot;
    }

    public char getEtat() {
        return etat;
    }

    public void setEtat(char etat) {
        this.etat = etat;
    }
    
    public static Vector<DemandeMobilite> getMobisByEtud(int num_etudiant){
        Vector<DemandeMobilite> lesMobis = new Vector<DemandeMobilite>();// On va stocker tous nos objets récupérés dans un Vecteur
        Connection conn = dbUtils.connect(); // On se connecte à la base
        ResultSet result = dbUtils.query(conn, "SELECT * FROM demande_mobilites de, etudiants e, diplomes d"
                + " WHERE etudiant_id = e.id"
                + " AND de.diplome_id = d.id"
                + " AND num_etudiant = "+ num_etudiant); // Récupérer les demandes de mobilités par Etudiant
        try{
            while(result.next()){
                DemandeMobilite object = new DemandeMobilite();
                object.setId(result.getInt("id"));
                object.setEtudiant_id(result.getInt("etudiant_id"));
                object.setNum_etudiant(result.getInt("num_etudiant"));
                object.setDiplome_id(result.getInt("diplome_id"));
                object.setIntituleDiplome(result.getString("intitule"));
                object.setDate_depot(result.getDate("date_depot"));
                object.setEtat(result.getString("etat").charAt(0));
                lesMobis.add(object);
            }
        } catch(SQLException e) {
            // Nothing
        }
        return lesMobis;
    }
    public static Vector<DemandeMobilite> getMobisByDiplome(String intitule){
        Vector<DemandeMobilite> lesMobis = new Vector<DemandeMobilite>();// On va stocker tous nos objets récupérés dans un Vecteur
        Connection conn = dbUtils.connect(); // On se connecte à la base
        ResultSet result = dbUtils.query(conn, "SELECT * FROM demande_mobilites de, etudiants e, diplomes d"
                + " WHERE etudiant_id = e.id"
                + " AND de.diplome_id = d.id"
                + " AND intitule = '"+ intitule +"';"); // Récupérer les demandes de mobilités par Diplômes
        try{
            while(result.next()){
                DemandeMobilite object = new DemandeMobilite();
                object.setId(result.getInt("id"));
                object.setEtudiant_id(result.getInt("etudiant_id"));
                object.setNum_etudiant(result.getInt("num_etudiant"));
                object.setDiplome_id(result.getInt("diplome_id"));
                object.setIntituleDiplome(result.getString("intitule"));
                object.setDate_depot(result.getDate("date_depot"));
                object.setEtat(result.getString("etat").charAt(0));
                lesMobis.add(object);
            }
        } catch(SQLException e) {
            // Nothing
        }
        return lesMobis;
    }
    public static Vector<DemandeMobilite> getMobisByUniv(String nom){
        Vector<DemandeMobilite> lesMobis = new Vector<DemandeMobilite>();// On va stocker tous nos objets récupérés dans un Vecteur
        Connection conn = dbUtils.connect(); // On se connecte à la base
        ResultSet result = dbUtils.query(conn, "SELECT * FROM demande_mobilites de, universites u, diplomes d"
                + " WHERE de.diplome_id = d.id"
                + " AND d.universite_id = u.id"
                + " AND u.nom = '"+ nom +"';"); // Récupérer les demandes de mobilités par Universités
        try{
            while(result.next()){
                DemandeMobilite object = new DemandeMobilite();
                object.setId(result.getInt("id"));
                object.setDiplome_id(result.getInt("diplome_id"));
                object.setIntituleDiplome(result.getString("intitule"));
                object.setNomUniv(result.getString("nom"));
                object.setDate_depot(result.getDate("date_depot"));
                object.setEtat(result.getString("etat").charAt(0));
                lesMobis.add(object);
            }
        } catch(SQLException e) {
            // Nothing
        }
        return lesMobis;
    }
    
}
