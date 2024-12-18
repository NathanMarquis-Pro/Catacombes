package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ListeArme {
    private ArrayList<Arme> liste;

    public ListeArme() {
        liste = new ArrayList<>();
    }

    public List<Arme> initListe(){
        try {
            File file = new File("."+File.separator+"ressource"+File.separator+"Armes.csv");
            Scanner sc = new Scanner(file);
            List<String> tmp = new ArrayList<>();
            sc.useDelimiter(",");
            while(sc.hasNext()){
                tmp.add(sc.next());
            }
            sc.close();
            try {
            for(int i = 0; i < tmp.size(); i=i+6){
                String nom = tmp.get(i);
                    int force = Integer.parseInt(tmp.get(i+1));
                    int intelligence = Integer.parseInt(tmp.get(i+2));
                    int dexterite = Integer.parseInt(tmp.get(i+3));
                    int defense = Integer.parseInt(tmp.get(i+4));
                    int num = Integer.parseInt(tmp.get(i+5));
                    liste.add(new Arme(nom, force, intelligence, dexterite, defense, num));
                }
                }catch (NumberFormatException e){
                    System.out.println(e);
            }
        }catch(IOException e){
            System.out.println(e);
        }
        return this.liste;
    }

    public Arme getRandomArme(){
        Random rdm = new Random();
        int number = rdm.nextInt(liste.size());
        return liste.get(number);
    }
    
}
