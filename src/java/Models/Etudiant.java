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
public class Etudiant {
    private int id;
    private int numEtudiant;
    private Diplome diplome;
    private String nom;
    private String prenom;
    private String email;
    private String cv;

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(int numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public Diplome getDiplome() {
        return diplome;
    }

    public void setDiplome(Diplome diplome) {
        this.diplome = diplome;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="show" desc="Méthodes">
    public static Vector<Etudiant> getAll() {
        Vector<Etudiant> objects = new Vector<Etudiant>(); // On va stocker tous nos objets récupérés dans un Vecteur
        Connection conn = dbUtils.connect(); // On se connecte à la base
        ResultSet result = dbUtils.query(conn, "SELECT * FROM etudiants e JOIN diplomes d ON d.id = e.diplome_id"); // Première étape : tout récupérer de toutes les universités
        try {
            while(result.next()) {
                Etudiant object = new Etudiant(); // On crée notre objet
                object.setId(result.getInt("id")); // On lui assigne son ID
                object.setNumEtudiant(result.getInt("num_etudiant")); // Son numéro étudiant
                object.setNom(result.getString("nom")); // Son nom
                object.setPrenom(result.getString("prenom")); // Son prénom
                object.setEmail(result.getString("email")); // Son email
                object.setCv(result.getString("cv")); // Son cv
                
                Diplome diplome = new Diplome();
                diplome.setId(result.getInt("id"));
                diplome.setIntitule(result.getString("intitule"));
                diplome.setAdresseWeb(result.getString("adresse_web"));
                diplome.setNiveau(result.getInt("niveau"));

                // Enfin, on ajoute à notre Vector de retour notre objet
                objects.add(object);
            }
        } catch(SQLException e) {
            // Nothing
        }
        return objects;
    }
    
    public static Etudiant getEtudiant(int id) {
        Etudiant etudiant = null;
        Connection conn = dbUtils.connect(); // On se connecte à la base
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM etudiants e JOIN diplomes d ON d.id = e.diplome_id WHERE e.id = ?"); // Première étape : tout récupérer de toutes les universités
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            
            etudiant = new Etudiant(); // On crée notre objet
            etudiant.setId(result.getInt("id")); // On lui assigne son ID
            etudiant.setNumEtudiant(result.getInt("num_etudiant")); // Son numéro étudiant
            etudiant.setNom(result.getString("nom")); // Son nom
            etudiant.setPrenom(result.getString("prenom")); // Son prénom
            etudiant.setEmail(result.getString("email")); // Son email
            etudiant.setCv(result.getString("cv")); // Son cv

            Diplome diplome = new Diplome();
            diplome.setId(result.getInt("id"));
            diplome.setIntitule(result.getString("intitule"));
            diplome.setAdresseWeb(result.getString("adresse_web"));
            diplome.setNiveau(result.getInt("niveau"));
            etudiant.setDiplome(diplome);
        } catch(SQLException e) {
            // Nothing
        }
        return etudiant;
    }
    //</editor-fold>
    
}
