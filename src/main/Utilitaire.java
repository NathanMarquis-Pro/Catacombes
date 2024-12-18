package main;

import java.util.Scanner;

public class Utilitaire {
    private static Scanner scan = new Scanner(System.in);

    public static String readString(){
    return scan.nextLine();
    }
   
    public static int readInt(){
        try{
            return Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e){
            System.out.println(Color.RED + "Veuillez entrer un chiffre" + Color.RESET);
        }catch (Exception e){
            e.getMessage();
        }
            return readInt();
    }
        
}

