package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.concurrent.TimeUnit;

public class Accueil {

    public static String filePath = "ressource/classement.csv";
    public static ArrayList<String[]> list;

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void afficherTitre() {
        Accueil.clear();
        System.out.println(" ██████  █████  ████████  █████   ██████  ██████  ███    ███ ██████  ███████ ███████");
        System.out.println("██      ██   ██    ██    ██   ██ ██      ██    ██ ████  ████ ██   ██ ██      ██      ");
        System.out.println("██      ███████    ██    ███████ ██      ██    ██ ██ ████ ██ ██████  █████   ███████ ");
        System.out.println("██      ██   ██    ██    ██   ██ ██      ██    ██ ██  ██  ██ ██   ██ ██           ██ ");
        System.out.println(" ██████ ██   ██    ██    ██   ██  ██████  ██████  ██      ██ ██████  ███████ ███████ ");
        System.out.println("");

    }

    public static void afficherRegles() {
        System.out.println(
                " CATACOMBES est un jeu où vous incarnez un explorateur à la recherche d'or. \n Vous devrez vous frayer un chemin à travers des catacombes pour trouver le trésor caché. \n Attention, des monstres se dresseront sur votre chemin et vous devrez les combattre pour survivre et continuer votre quête. \n Vous pouvez toutefois vous enfuir si vous n'êtes pas sûr de pouvoir les vaincre, mais vous perdrez la totalité de votre or. \n Bonne chance!");
    }

    public static void afficherPossibilitees() {
        System.out.println("Bienvenue dans CATACOMBES!");
        System.out.println("1. Jouer");
        System.out.println("2. Afficher le classement");
        System.out.println("3. Afficher les règles");
        System.out.println("4. Quitter");
    }

    public static void choixMenu() {
        int choix = 0;
        afficherTitre();
        while (choix != 4) {
            afficherPossibilitees();
            choix = Utilitaire.readInt();
            if (choix == 1) {
                System.out.println("Lancement du jeu...");
                Joueur j = créerJoueur();
                choixClasse(j);
            } else if (choix == 2) {
                Accueil.clear();
                System.out.println("Affichage du classement...");
                list = Accueil.readFile();
                list = Accueil.sortList(list);
                System.out.println(Accueil.printList(list));
                String barre = "█";
                try{
                    for(int i=0;i<26;i++){
                        TimeUnit.MILLISECONDS.sleep(100);
                        System.out.print(barre);
                    }
                    System.out.println('\n');
                } catch (InterruptedException e){
                    System.out.println("Retour à l'accueil");
                }catch (Exception e){
                    e.getMessage();
                }

            } else if (choix == 3) {
                Accueil.clear();
                Accueil.afficherRegles();
            } else if (choix == 4) {
                System.out.println("Fermeture du jeu...");
            } else {
                System.out.println(Color.RED + "Choix invalide, refaites un choix" + Color.RESET);
            }
        }
    }

    public static void GenererAcceuil() {
        choixMenu();
    }

    public static Joueur créerJoueur() {
        System.out.println("Comment t'appelles tu ?" + '\n');
        String str = "";
        str = Utilitaire.readString();
        Joueur j = new Joueur(str);
        System.out.println("Bienvenue dans les Catacombes de Mykérinos, " + str + " !!!" + '\n');
        return j;
    }

    public static void finPartie(Joueur j) {
        Accueil.clear();
        System.out.println("        █████ █████ █████ █████   █████ █   █ █████ ████ ");
        System.out.println("        █     █   █ █ █ █ █       █   █ █   █ █     █   █");
        System.out.println("        █  ██ █████ █ █ █ ████    █   █  █ █  ████  ████");
        System.out.println("        █   █ █   █ █   █ █       █   █  █ █  █     █  █ ");
        System.out.println("        █████ █   █ █   █ █████   █████   █   █████ █   █ " + '\n' + '\n' + '\n');
        System.out.println("                Voici votre score: " + j.getPoints());
    }

    public static void choixClasse(Joueur j) {

        int choix = 0;
        String allCharacters = "";
        System.out.println("Choisis ta classe" + '\n');
        try{
            allCharacters = Files.readString(Paths.get("./ressource/Sprites/AllCharacters.txt"));
            allCharacters +="\n4. Quitter";
            System.out.println(allCharacters);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        while (choix != 4) {

            choix = Utilitaire.readInt();
            if (choix == 1) { // Instanciation berzerker + comp
                System.out.println("Tu as choisi le Fantassin de Nil");
                Personnage player = new Personnage(j, "Fantassin du Nil", 50, 20, 2, 6, 12, "SpriteFantassin.txt");
                player.initComp(choix);
                list = Accueil.readFile();
                Plateau.affichagePlateau(j, player, choix);
                Accueil.finPartie(j);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("");
                }catch (Exception e){
                    e.getMessage();
                }
                break;
            } else if (choix == 2) { // Instanciation Archer + comp
                System.out.println("Tu as choisi l'Archer de Khéops");
                Personnage player = new Personnage(j, "Archer de Khéops", 50, 6, 14, 20, 6, "SpriteArcher.txt");
                player.initComp(choix);
                list = Accueil.readFile();
                Plateau.affichagePlateau(j, player, choix);
                Accueil.finPartie(j);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("");
                }catch (Exception e){
                    e.getMessage();
                }
                break;
            } else if (choix == 3) { // Instanciation Mage + comp
                System.out.println("Tu as choisi le Mage d'Anubis");
                Personnage player = new Personnage(j, "Mage d'Anubis", 50, 4, 20, 10, 6, "SpriteMage.txt");
                player.initComp(choix);
                list = Accueil.readFile();
                Plateau.affichagePlateau(j, player, choix);
                Accueil.finPartie(j);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("");
                }catch (Exception e){
                    e.getMessage();
                }
                break;
            } else if (choix == 4) {
                clear();
                System.out.println("Retour à l'Accueil");
            } else {
                System.out.println(allCharacters);
                System.out.println(Color.RED + "Choix invalide, refaites un choix" + Color.RESET);
            }
        }
    }

    /// Méthodes pour sauvegarde et affichage de tableau des scores

    public static String printTable(String[] table) {
        String rep = "" + table[0] + ";" + table[1] + '\n';

        return rep;
    }

    public static String affichTabScore(String[] table) {
        String rep = table[0] + " " + table[1] + " pts";
        return rep;
    }

    public static String printList(ArrayList<String[]> table) {
        String affich = "Joueur | Score " + '\n';

        for (int i = 0; i < table.size(); i++) {
            affich += (i+1) + ".) " + affichTabScore(table.get(i)) + '\n';
        }
        return affich;
    }

    public static ArrayList<String[]> readFile() {
        String line = "";
        String splitBy = ";";
        ArrayList<String[]> listeJoueur = new ArrayList<String[]>();


        try(BufferedReader br =new BufferedReader(new FileReader(filePath))){
            while ((line = br.readLine()) != null) {
                listeJoueur.add(line.split(splitBy));
                // System.out.println(line);
            }
        }catch (IOException e){
            System.out.println("Le fichier n'existe pas trouvé/n'existe pas");
        } catch (Exception e){
            e.getMessage();
        }
        return listeJoueur;

    }

    public static ArrayList<String[]> addPlayer(ArrayList<String[]> list, String[] table) {
        list.add(table);
        return list;
    }

    public static void addData(ArrayList<String[]> list) {

        try {
            File csvFile = new File(filePath);

            FileWriter csvWriter = new FileWriter(csvFile);

            for (int i = 0; i < list.size(); i++) {
                csvWriter.write(printTable(list.get(i)));
            }

        csvWriter.close(); 
    } catch (IOException e){
        System.out.println("Le fichier n'existe pas");
    } catch (Exception e){
        e.getMessage();
    }
    }

    public static ArrayList<String[]> sortList(ArrayList<String[]> list) {
        ArrayList<String[]> listTemp = new ArrayList<String[]>();
        while (!list.isEmpty()) {
            int index = 0;
            int max = Integer.parseInt(list.get(0)[1]);
            for (int i = 0; i < list.size(); i++) {
                if (Integer.parseInt(list.get(i)[1]) > max) {
                    max = Integer.parseInt(list.get(i)[1]);
                    index = i;
                }
            }
            listTemp.add(list.remove(index));
        }
        return listTemp;
    }
}