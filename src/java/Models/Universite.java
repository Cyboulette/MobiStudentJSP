package Models;

import Utils.dbUtils;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Quentin DESBIN, Arnaud HERTEL
 */
public class Universite {
    private int id;
    private String nom;
    private String adressePostale;
    private String adresseWeb;
    private String adresseMail;

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public String getAdresseWeb() {
        return adresseWeb;
    }

    public void setAdresseWeb(String adresseWeb) {
        this.adresseWeb = adresseWeb;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="show" desc="Méthodes">
    public static Vector<Universite> getAll() {
        Vector<Universite> objects = new Vector<Universite>(); // On va stocker tous nos objets récupérés dans un Vecteur
        Connection conn = dbUtils.connect(); // On se connecte à la base
        ResultSet result = dbUtils.query(conn, "SELECT * FROM universites u"); // Première étape : tout récupérer de toutes les universités
        try {
            while(result.next()) {
                Universite object = new Universite(); // On crée notre objet
                object.setId(result.getInt("id")); // On lui assigne son ID
                object.setNom(result.getString("nom")); // Son nom
                object.setAdressePostale(result.getString("adresse_postale")); // Son adresse postale
                object.setAdresseWeb(result.getString("adresse_web")); // Son adresse web
                object.setAdresseMail(result.getString("adresse_mail")); // Son adresse mail

                // Enfin, on ajoute à notre Vector de retour notre objet
                objects.add(object);
            }
        } catch(SQLException e) {
            // Nothing
        }
        return objects;
    }
    
    public static Universite getUnivByDiplome(int idD) {
        Connection conn = dbUtils.connect(); // On se connecte à la base
        Universite univ = null; // On crée un objet diplome
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM universites u, diplomes d"
                    + " WHERE u.id = d.universite_id"
                    + " AND d.id = ?;");
            statement.setInt(1, idD);
            ResultSet result = statement.executeQuery(); // On exécute la requête
            
            while (result.next()) { // Pour tous les diplomes trouvés
                univ = new Universite(); // On instancieun objet diplome
                univ.setId(result.getInt("id")); // On lui récupère son ID
                univ.setNom(result.getString("nom")); // Son nom
                univ.setAdresseWeb(result.getString("adresse_web")); // Son AdresseWeb
                univ.setAdressePostale(result.getString("adresse_postale")); // Son AdressePostale
                univ.setAdresseMail(result.getString("adresse_mail")); // Son AdresseMail
            }
        } catch (SQLException e) {
            //Nothing
        }
        return univ;
    }
    
    public String toString() {
        return this.nom;
    }
    //</editor-fold>
    
}
