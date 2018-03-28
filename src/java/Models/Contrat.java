package Models;

import java.util.Vector;

/**
 *
 * @author ahertel
 */
public class Contrat {
    private int id;
    private int duree;
    private char etat;
    private char ordre;
    private Vector<Diplome> lesDiplomes;
    private Vector<DemandeMobilite> lesDemandesMobilites;
    private Vector <Programme> lesProgrammes;



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

    public char getEtat() {
        return etat;
    }

    public void setEtat(char etat) {
        this.etat = etat;
    }

    public char getOrdre() {
        return ordre;
    }

    public void setOrdre(char ordre) {
        this.ordre = ordre;
    }

    public Vector<Diplome> getLesDiplomes() {
        return lesDiplomes;
    }

    public void setLesDiplomes(Vector<Diplome> lesDiplomes) {
        this.lesDiplomes = lesDiplomes;
    }

    public Vector<DemandeMobilite> getLesDemandesMobilites() {
        return lesDemandesMobilites;
    }

    public void setLesDemandesMobilites(Vector<DemandeMobilite> lesDemandesMobilites) {
        this.lesDemandesMobilites = lesDemandesMobilites;
    }

    public Vector<Programme> getLesProgrammes() {
        return lesProgrammes;
    }

    public void setLesProgrammes(Vector<Programme> lesProgrammes) {
        this.lesProgrammes = lesProgrammes;
    }
    
    
    
}
