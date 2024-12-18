package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class Mob extends Entite implements Print{

    private String nom;
    private int pv;
    private ArrayList<Competence> listComp;
    private int pvMax;
    private String sprite;


    public Mob(int force, int intelligence, int dexterite, int defense, String nom, int pv, String sprite){
        super(force, intelligence, dexterite, defense);
        this.nom = nom;
        this.pv = pv;
        this.listComp = new ArrayList<Competence>();
        this.pvMax = pv;
        this.sprite = sprite;
    }
    

    public String getNom() {
        return this.nom;
    }

    public void setNom(String other){
        this.nom = other;
    }

    public int getPv() {
        return pv;
    }
    
    
    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getPvMax(){
        return this.pvMax;
    }

    public void setPvMax(int pvMax){
        this.pvMax = pvMax;
    }

    
    public ArrayList<Competence> getListComp() {
        return listComp;
    }
    
    
    public void setListComp(ArrayList<Competence> listComp) {
        this.listComp = listComp;
    }
    public static ArrayList<Mob> initMob(){
        ArrayList<Mob> listMob = new ArrayList<Mob>();
        Mob mob1 = new Mob(10,10,10,1,"Ghost",60,"SpriteGhost.txt");
        Mob mob2 = new Mob(12,10,10,3,"Momie",50,"SpriteMomie.txt");
        Mob mob3 = new Mob(11,10,10,2,"Skeleton",75,"SpriteSkeleton.txt");
    
        mob1.listComp.add(new Competence("Cauchemar", mob1.getIntelligence(), 0, 0));
        mob1.listComp.add(new Competence("Malfaiscance", mob1.getIntelligence()*2, 0, 0));
        mob1.listComp.add(new Competence("Mauvais esprit", mob1.getIntelligence()+5, 0, 0));
    
        mob2.listComp.add(new Competence("Recup", 0, 0, mob2.getPv()+5));
        mob2.listComp.add(new Competence("Coup direct", mob2.getForce()+5, 0, 0));
        mob2.listComp.add(new Competence("Tir de bandeau", mob2.getDexterite()+3, 0, 0));

        mob3.listComp.add(new Competence("Aspiration d'âme", mob3.getDexterite() + mob3.getIntelligence(), 0, 0));
        mob3.listComp.add(new Competence("Copie", 0,mob3.getDefense() + 3, 0));
        mob3.listComp.add(new Competence("Ame recouvrée", 0, 0, mob3.getPv()+10));

        listMob.add(mob1);
        listMob.add(mob2);
        listMob.add(mob3);
    
        return listMob;
    }

    public Mob getRandMob(ArrayList<Mob> other){
        Random rand = new Random();
        return other.get(rand.nextInt(2));
    }

    public Competence getRandComp(){
        Random rand = new Random();
        Competence comp = this.listComp.get(rand.nextInt(2));
        if(comp.getNom().equals("Recup")){
            comp.setTotalRecup(this.getPv()/5);
        }
        return comp;
    }


    // @Override
    // public String toString() {
    //     return "Le " + nom + " a " + pv + " points de vie ";
    // }


    @Override
    public void printSprite() {
        try{
            String mob = Files.readString(Paths.get("./ressource/Sprites/" + sprite));
            System.out.println(mob);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}