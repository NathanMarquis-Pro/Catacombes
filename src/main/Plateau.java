package main;
import java.util.ArrayList;
import java.util.Random;

import java.util.concurrent.TimeUnit;

public class Plateau {
    private static int comptPotion = 0;
    private static boolean canDefend = true;

    public static void affichagePlateau(Joueur j, Personnage player, int numPerso){
        boolean finDeJeu = false;
        ArrayList<Mob> list = Mob.initMob();
        ListeArme lArme = new ListeArme();
        lArme.initListe();
        while (!finDeJeu) {
            Random rand = new Random();
            int randomNum = rand.nextInt((3 - 1) + 1) + 1;
            Mob monstre = list.get(randomNum-1);
            boolean monstreMort = false;
            System.out.println("Un "+ monstre.getNom()+ " est apparu !");
            while(!monstreMort && player.getPv() > 0){
                monstre.printSprite();
                player.printSprite();
                System.out.println("Le "+ monstre.getNom()+ " a encore " + monstre.getPv()+" pv                                                      Points Joueur: " + j.getPoints()+'\n' + '\n');
                System.out.println("Points de Vie du joueur: " + player.getPv()+"/"+player.getMaxPv()+"pv          Niveau: "+player.getNiveau()+"             Expérience: "+player.getExp()+"/"+player.getMaxExp()+"exp" + '\n');
                System.out.println(player);
                System.out.println();
                System.out.println(player.getArme());
                System.out.println('\n'+"Actions possibles :");
                System.out.println("1. Attaquer");
                System.out.println("2. Prendre une potion     Vous avez "+ player.getNbPotions()+ " potions");
                System.out.println("3. Défendre");
                System.out.println("4. Abandonner");
                System.out.print("Choisissez une action : ");
                    int choix = Utilitaire.readInt();
                    if (choix == 1) {
                        if(menuAttaque(player, monstre)){
                            if(monstre.getPv()<=0){
                                monstreMort = true;
                                monstre.setPvMax((int)(monstre.getPvMax()*1.3));
                                monstre.setDefense(monstre.getDefense() + 5);
                                monstre.setPv(monstre.getPvMax());
                                monstre.setForce((int)(monstre.getForce()*1.2));
                                monstre.setDexterite((int)(monstre.getDexterite()*1.3));
                                monstre.setIntelligence((int)(monstre.getIntelligence()*1.3));
                                System.out.println("Monstre tué \n");
                                comptPotion+=1;
                                if(comptPotion %3 == 0){
                                    player.setNbPotions(player.getNbPotions()+1);
                                    System.out.println("Vous venez de gagner une potion \n");
                                }
                                Plateau.gainExp(player);
                                Plateau.loot(player, lArme);
                                j.setPoints(5);
                                player.initComp(numPerso);
                            }
                            else{
                                attaqueMonstre(monstre, player);
                            }
                            try {
                                TimeUnit.SECONDS.sleep(5);
                            } catch (InterruptedException e) {
                                System.out.println("Chargement...");
                            }
                        }
                    } else if (choix == 2) {
                        if(player.getNbPotion() != 0){
                            prendrePotion(player);
                            attaqueMonstre(monstre, player);
                        }
                        else{
                            System.out.println("Vous n'avez plus de potions...");
                        }
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            System.out.println("Chargement...");;
                        }
                    } else if (choix == 3) {
                        defendre(player, monstre);
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            System.out.println("Chargement...");;
                        }
                    } else if (choix == 4) {
                        fuir();
                        finDeJeu = true; // Finir le jeu si le joueur fuit
                        monstreMort = true;
                        String[] ply = new String[]{j.getNom(),j.getPoints()+""};
                        Accueil.list = Accueil.addPlayer(Accueil.list, ply);
                        Accueil.list = Accueil.sortList(Accueil.list);
                        Accueil.addData(Accueil.list);
                    } else {
                        System.out.println(Color.RED+ "Choix invalide, veuillez réessayer."+ Color.RESET);
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            System.out.println("Chargement...");;
                        }
                    }
                    Accueil.clear();
                }
                if(player.getPv()<=0){
                    finDeJeu = true;
                    String[] ply = new String[]{j.getNom(),j.getPoints()+""};
                    Accueil.list = Accueil.addPlayer(Accueil.list, ply);
                    Accueil.list = Accueil.sortList(Accueil.list);
                    Accueil.addData(Accueil.list);
            }
            

        }
    }

    private static boolean menuAttaque(Personnage player, Mob monstre){
        Accueil.clear();
        monstre.printSprite();
        player.printSprite();
        System.out.println('\n'+"1. " + player.getListComp().get(0).compJoueurToString());
        System.out.println("2. " + player.getListComp().get(1).compJoueurToString());
        System.out.println("3. " + player.getListComp().get(2).compJoueurToString());
        System.out.println("4. Retour");
            int choix = Utilitaire.readInt();
            if( choix == 4 ) return false;
            if(choix <= 0 && choix > 4){
                throw new NumberFormatException();
            }
            attaquer(player,monstre, choix-1);
        return true;

    }

    private static void attaqueMonstre(Mob monstre, Personnage player){
        Competence comp = monstre.getRandComp();
        comp.afficherCompMob();
        player.setPv(player.getPv()-comp.getTotalDamage());
        monstre.setPv(monstre.getPv()+comp.getTotalRecup());
        System.out.println("Il vous reste "+ player.getPv());
        
    }

    private static void attaquer(Personnage player, Mob monstre, int nComp) {
        Competence c = player.getListComp().get(nComp);
        System.out.println("Vous attaquez le monstre avec "+ c.getNom());
        monstre.setPv(monstre.getPv()-(c.getTotalDamage()));
        player.setPv(player.getPv()-c.getTotalRecup());
        System.out.println("Le "+ monstre.getNom()+" a pris "+ c.getTotalDamage());
        System.out.println("Il vous reste "+ player.getPv());
        canDefend = true;
    }


    private static void prendrePotion(Personnage player) {
        if(player.getNbPotions() == 0){
            System.out.println("Vous n'avez plus de potions !");
            }else{
            player.setNbPotions(player.getNbPotions()-1);
            System.out.println("Vous prenez une potion !");
            player.setPv(player.getPv()+player.getMaxPv()/3);
            System.out.println("Vous avez gagné"+player.getMaxPv()/3+" pv !");
            if(player.getPv() > player.getMaxPv()){
            player.setPv(player.getMaxPv());
            }
            canDefend = true;
            }
    }

    private static void defendre(Personnage player, Mob monstre) {
        if(canDefend){
            if (player.getDefense()<monstre.getForce()){
            player.setPv(player.getPv()-(monstre.getForce()-player.getDefense()));
            }
            System.out.println("Vous vous défendez !");
            System.out.println("Vous avez pris "+ (monstre.getForce()-player.getDefense()) + " dégâts ");
            canDefend = false;
        }
        else{
            System.out.println("Vous ne pouvez vous défendre qu'un tour sur deux !");
        }
    }

    private static void fuir() {
        System.out.println("Vous abandonnez le combat !");
    }

    private static void loot(Personnage player, ListeArme lArme){
        Arme newArme = lArme.getRandomArme();
        System.out.println("Vous avez cette arme d'équipé : " + player.getArme());
        System.out.println("Vous venez de trouver cette arme : " + newArme);
        System.out.println("Voulez vous l'équiper ? (oui/non)");
        boolean decision = false;
        while(!decision){
        String rep = Utilitaire.readString();
        rep = rep.toUpperCase();
        if(rep.equals("O")|| rep.equals("OUI")){
            player.setArme(newArme);
            System.out.println("Vous avez équipé " + newArme.getNom() + " !!! \n");
            decision = true;
        }
        else if(rep.equals("N")|| rep.equals("NON")){
            System.out.println("Aucun changement d'arme \n");
            decision = true;
        }
        else{
            System.out.println("Entrée incorrecte, veuillez entrer O ou OUI pour oui, N ou NON pour non");
        }
    }
    }

    private static void gainExp(Personnage player){
        player.setExp(player.getExp()+10*(1+comptPotion/10));
        if(player.getExp()>= player.getMaxExp()){
            int temp = player.getExp()- player.getMaxExp();
            player.setExp(temp);
            player.setMaxExp(player.getMaxExp() + 10*comptPotion);
            player.setNiveau(player.getNiveau() + 1);
            System.out.println("Vous montez de niveau \n Quel attribut voulez vous augmenter ?");
            System.out.println("1. Vie");
            System.out.println("2. Force");
            System.out.println("3. Intelligence");
            System.out.println("4. Dextérité");
            System.out.println("5. Défense");
            int augment = 0;
            boolean choixFait = false;
            String choix = "";
            while(!choixFait){
                augment = Utilitaire.readInt();
                if(augment == 1){
                    player.setPvMax(player.getMaxPv()+ 5);
                    player.setPv(player.getPv()+ 5);
                    choix = " de Vie Maximum";
                    System.out.println("Vous vous êtes soignés de 5 PV");

                }
                else if(augment == 2){
                    player.setForce(player.getForce()+5);
                    choix = " de Force";
                }
                else if(augment == 3){
                    player.setIntelligence(player.getIntelligence()+5);
                    choix = " d'Intelligence";
                }
                else if(augment == 4){
                    player.setDexterite(player.getDexterite()+5);
                    choix = " de Dextérité";
                }
                else if(augment == 5){
                    player.setDefense(player.getDefense()+5);
                    choix = " de Défense";
                }
                else {
                System.out.println("Choix invalide, veuillez réessayer.");
                }
        System.out.println("Vous avez gagné 5 points " + choix );
        choixFait = true;
        System.out.println(player);
    }
    }
}
}