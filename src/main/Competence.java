package main;

public class Competence {

    private String nom;
    private int totalDamage;
    private int totalDefense;
    private int totalRecup;

    public Competence(String nom, int totalDamage, int totalDefense, int totalRecup){
        this.nom = nom;
        this.totalDamage = totalDamage;
        this.totalDefense = totalDefense;
        this.totalRecup = totalRecup;
    }

    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public int getTotalDamage() {
        return totalDamage;
    }


    public void setTotalDamage(int totalDamage) {
        this.totalDamage = totalDamage;
    }


    public int getTotalDefense() {
        return totalDefense;
    }


    public void setTotalDefense(int totalDefense) {
        this.totalDefense = totalDefense;
    }


    public int getTotalRecup() {
        return totalRecup;
    }


    public void setTotalRecup(int totalRecup) {
        this.totalRecup = totalRecup;
    }

    public String compJoueurToString(){
        return getNom() + " : "+ "Vous infligez " + getTotalDamage() + ", et vous perdez "+ getTotalRecup() + "pv";
    }

    public void afficherCompMob(){
        System.out.println("Le monstre utilise [" + getNom() + "]");
        System.out.println("Il vous inflige " + getTotalDamage() + " ...");
    }
}