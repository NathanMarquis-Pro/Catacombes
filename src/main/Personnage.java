package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Personnage extends Entite implements Print{
    private Joueur joueur;
    private String classe;
    private int pv;
    private Arme arme;
    private int nbPotion;
    private int maxPv;
    private int exp;
    private int niveau = 1;
    private int maxExp = 10;
    private ArrayList<Competence> listComp;
    private String sprite;

    public Personnage(Joueur joueur,String classe, int pv, int force, int intelligence, int dexterite, int defense, String sprite){
        super(force,intelligence,dexterite,defense);
        this.classe = classe;
        this.pv = pv;
        this.joueur = joueur;
        this.nbPotion = 2;
        this.maxPv = pv;
        arme = new Arme();
        this.listComp = new ArrayList<Competence>();
        this.sprite = sprite;
    }

    public int getPv(){
        return this.pv;
    }

    public void setPv(int pv){
        this.pv = pv;
    }

    public String getClasse(){
        return this.classe;
    }

    public int getForce(){
        return super.getForce();
    }

    public void setForce(int force){
        super.setForce(force);
    }

    public Arme getArme(){
        return this.arme;
    }

    public void setArme(Arme a){
        this.arme = a;
    }

    public int getNbPotions(){
        return this.nbPotion;
    }

    public void setNbPotions(int a){
        this.nbPotion = a;
    }

    public int getMaxPv(){
        return this.maxPv;
    }

    public void setPvMax(int maxPv){
        this.maxPv = maxPv;
    }

    public String toString(){
        return this.joueur.getNom() + ":    " + this.classe + "   "  + this.getForce() + " For   " + this.getIntelligence() + " Int   " + this.getDexterite() + " Dex   " + this.getDefense() + " Def";
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }


    public Joueur getJoueur() {
        return joueur;
    }


    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }


    public void setClasse(String classe) {
        this.classe = classe;
    }


    public int getNbPotion() {
        return nbPotion;
    }


    public void setNbPotion(int nbPotion) {
        this.nbPotion = nbPotion;
    }


    public void setMaxPv(int maxPv) {
        this.maxPv = maxPv;
    }


    public ArrayList<Competence> getListComp() {
        return listComp;
    }


    public void setListComp(ArrayList<Competence> listComp) {
        this.listComp = listComp;
    }

    public void initComp(int choix){
        ArrayList<Competence> nListComp = new ArrayList<>();
        if (choix == 1){
            nListComp.add(new Competence("Coup de khépesh: Utilise la force", this.getForce() + this.getArme().getForce(), 0, 0));
            nListComp.add(new Competence("Entaille sanglante: Utilise la force mais fait perdre de la vie",this.getForce() * 2 + this.getArme().getForce(), 0, this.getMaxPv()/6));
            nListComp.add(new Competence("Tranchage: Utilise en partie la fore et la dexterité",(int) (this.getDexterite() * 1.5) + this.getArme().getDexterite() + this.getForce(), 0,0));
        } else if (choix == 2){
            nListComp.add(new Competence("Tir précis: Utilise la dexterité", this.getDexterite() + this.getArme().getDexterite(), 0, 0));
            nListComp.add(new Competence("Flèche sacrée: Utilise en partie l'intelligence et la dexterité", this.getDexterite() + this.getArme().getDexterite()+ this.getIntelligence() + this.getArme().getIntelligence(), 0, 0));
            nListComp.add(new Competence("Pluie de flèche: Utilise la dexterité mais fait perdre de la vie",(int) (this.getDexterite() * 1.5) + this.getArme().getDexterite(), 0, this.getMaxPv()/6));

        } else if (choix == 3){
            nListComp.add(new Competence("Glace de abîme: Utilise l'intelligence",this.getIntelligence() + 10 + this.getArme().getIntelligence(), 0, 0));
            nListComp.add(new Competence("Changement des élements: Utilise l'intelligence",(int) (this.getIntelligence() * 1.5) + this.getArme().getIntelligence(), 0, 0));
            nListComp.add(new Competence("Brasier: Utilise en partie l'intelligence et la dexterité mais fait perdre de la vie",(int) (this.getDexterite() * 1.5) + (int) (this.getArme().getDexterite())+ this.getIntelligence() + this.getArme().getIntelligence(),0, this.getMaxPv()/6));
        }
        this.setListComp(nListComp);
    }

    @Override
    public void printSprite() {
        try{
            String perso = Files.readString(Paths.get("./ressource/Sprites/" + sprite));
            System.out.println(perso);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}