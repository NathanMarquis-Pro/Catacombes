package main;

public class Joueur{
    private String nom;
    private final int IDJOUEUR;
    private static int compteur = 1;
    private int points;

    public Joueur(String nom){
        this.nom = nom;
        this.IDJOUEUR = compteur++;
        this.points = 0;
    }

    public String toString(){
        return "IDjoueur: "+ this.IDJOUEUR+" Nom: " + this.nom + " Points: " + this.points;
    }

    public String getNom(){
        return this.nom;
    }

    public int getPoints(){
        return this.points;
    }

    public int getIDjoueur(){
        return this.IDJOUEUR;
    }

    public void setPoints(int i){
        this.points = this.points + i;
    }

}