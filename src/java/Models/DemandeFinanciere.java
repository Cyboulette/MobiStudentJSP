package Models;

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
    private Vector<Contrat> lesContrats;

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

    public Vector<Contrat> getLesContrats() {
        return lesContrats;
    }

    public void setLesContrats(Vector<Contrat> lesContrats) {
        this.lesContrats = lesContrats;
    }
    
}
