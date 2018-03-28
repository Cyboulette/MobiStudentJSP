package Models;

import Utils.dbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Quentin DESBIN, Arnaud HERTEL
 */
public class Diplome {

    private int id;
    private Universite universite;
    private String intitule;
    private String adresseWeb;
    private int niveau;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Universite getUniversite() {
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getAdresseWeb() {
        return adresseWeb;
    }

    public void setAdresseWeb(String adresseWeb) {
        this.adresseWeb = adresseWeb;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public static Diplome getDiplomeByMobi(int idMobi) {
        Connection conn = dbUtils.connect(); // On se connecte à la base
        Diplome diplome = null; // On crée un objet diplome
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM demande_mobilites de, diplomes d"
                    + " WHERE de.diplome_id = d.id"
                    + " AND de.id = ?;");
            statement.setInt(1, idMobi); // On assigne notre premiere inconnue dans la requête préparée : "?" à notre object.getId();
            ResultSet result = statement.executeQuery(); // On exécute la requête
            
            while (result.next()) { // Pour tous les diplomes trouvés
                diplome = new Diplome(); // On instancieun objet diplome
                diplome.setId(result.getInt("id")); // On lui récupère son ID
                diplome.setIntitule(result.getString("intitule")); // Son intitulé
                diplome.setAdresseWeb(result.getString("adresse_web")); // Son AdresseWeb
                diplome.setNiveau(Integer.parseInt(result.getString("niveau"))); // Son Niveau    
                diplome.setUniversite(Universite.getUnivByDiplome(diplome.getId()));
            }
        } catch (SQLException e) {
            //Nothing
        }
        return diplome;
    }

}
