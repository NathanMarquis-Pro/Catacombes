package test;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import main.Arme;
import main.Joueur;
import main.Personnage;
import main.Mob;
import java.util.ArrayList;

public class PersonnageTest{
    private Personnage perso;
    private Joueur j;
    private Mob mob1;
    private Mob mob2;
    private Mob mob3;

    @Before
    public void setUp(){
        j = new Joueur("Benji");
        perso = new Personnage(j, "Archer de Khéops", 74, 20, 6, 13, 16, null);
        ArrayList<Mob> listmob = Mob.initMob();
        mob1 = listmob.get(0);
        mob2 = listmob.get(1);
        mob3 = listmob.get(2);
    }

    @Test 
    public void testCreation(){
        assertEquals(74,perso.getPv());
        assertEquals(20, perso.getForce());
        assertEquals("Benji", perso.getJoueur().getNom());
    }

    @Test 
    public void testArmeEquip(){
        assertEquals("Couteau", perso.getArme().getNom());
        assertEquals(20, perso.getArme().getForce());
        Arme arme2 = new Arme("AWP", 150, 0, 80, 0, 0);
        perso.setArme(arme2);
        assertEquals("AWP", perso.getArme().getNom());
        assertEquals(150, perso.getArme().getForce());
        arme2.setDefense(78);
        assertNotEquals(0, arme2.getDefense());
        assertEquals(78, arme2.getDefense());
    }

    @Test
    public void testMob(){
        assertEquals("Ghost", mob1.getNom());
        assertEquals("Momie", mob2.getNom());
        assertEquals("Skeleton", mob3.getNom());
        assertEquals("Cauchemar", mob1.getListComp().get(0).getNom());
        assertEquals("Malfaiscance", mob1.getListComp().get(1).getNom());
        assertEquals("Mauvais esprit", mob1.getListComp().get(2).getNom());

        assertEquals("Recup", mob2.getListComp().get(0).getNom());
        assertEquals("Coup direct", mob2.getListComp().get(1).getNom());
        assertEquals("Tir de bandeau", mob2.getListComp().get(2).getNom());

        assertEquals("Aspiration d'âme", mob3.getListComp().get(0).getNom());
        assertEquals("Copie", mob3.getListComp().get(1).getNom());
        assertEquals("Ame recouvrée", mob3.getListComp().get(2).getNom());
    }

}