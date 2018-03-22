package Models;

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
    
    
}
