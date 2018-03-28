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
    private char etat;
    private Diplome diplome;
    private Etudiant etudiant;

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

    public Diplome getDiplome() {
        return diplome;
    }

    public void setDiplome(Diplome diplome) {
        this.diplome = diplome;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    
    public static Vector<DemandeMobilite> getAll() {
        Vector<DemandeMobilite> objects = new Vector<DemandeMobilite>(); // On va stocker tous nos objets récupérés dans un Vecteur
        Connection conn = dbUtils.connect(); // On se connecte à la base
        ResultSet result = dbUtils.query(conn, "SELECT * FROM demande_mobilites d"); // Première étape : tout récupérer de toutes les universités
        try {
            while(result.next()) {
                DemandeMobilite object = new DemandeMobilite(); // On crée notre objet
                object.setId(result.getInt("id")); // On lui assigne son ID
                object.setDate_depot(result.getString("date_depot")); // Sa date de dépôt
                object.setEtat(result.getString("etat").charAt(0)); // Son État
                object.setDiplome(Diplome.getDiplomeByMobi(object.getId()));
                
                /**
                 * On travaille en objet, donc notre demandeMobi à des diplomes associés (0 ou n)
                 * Il faut donc faire une requête supplémentaire pour récupérer les informations des diplomes et les stocker dans le vecteur this.lesDiplomes
                 */
                // Deuxième étape : on fait une requête préparée pour joindre notre université actuelle avec ses potentiels programmes
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM demande_mobilites de, diplomes d"
                        + " WHERE de.diplome_id = d.id"
                        + " AND de.id = ?;");
                statement.setInt(1, object.getId()); // On assigne notre premiere inconnue dans la requête préparée : "?" à notre object.getId();
                ResultSet result2 = statement.executeQuery(); // On exécute la requête
        
                Diplome diplome = new Diplome(); // On crée un objet programme
                diplome.setId(result2.getInt("id")); // On lui récupère son ID
                diplome.setIntitule(result2.getString("intitule")); // Son intitulé
                diplome.setAdresseWeb(result2.getString("adresse_web")); // Son AdresseWeb
                diplome.setNiveau(Integer.parseInt(result2.getString("niveau"))); // Son Niveau
                diplome.setUniversite(Universite.getUnivByDiplome(diplome.getId()));
                object.setDiplome(diplome);
                

                PreparedStatement statement2 = conn.prepareStatement("SELECT * FROM demande_mobilites de, etudiants e"
                        + " WHERE de.etudiant_id = e.id"
                        + " AND de.id = ?;");
                statement.setInt(1, object.getId()); // On assigne notre premiere inconnue dans la requête préparée : "?" à notre object.getId();
                ResultSet result3 = statement.executeQuery(); // On exécute la requête
        
                Etudiant diplome = new Diplome(); // On crée un objet programme
                diplome.setId(result2.getInt("id")); // On lui récupère son ID
                diplome.setIntitule(result2.getString("intitule")); // Son intitulé
                diplome.setAdresseWeb(result2.getString("adresse_web")); // Son AdresseWeb
                diplome.setNiveau(Integer.parseInt(result2.getString("niveau"))); // Son Niveau
                diplome.setUniversite(Universite.getUnivByDiplome(diplome.getId()));
                object.setDiplome(diplome);
                // Enfin, on ajoute à notre Vector de retour notre objet
                objects.add(object);
            }
        } catch(SQLException e) {
            // Nothing
        }
        return objects;
    }
    /*
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
    }*/
    
}
