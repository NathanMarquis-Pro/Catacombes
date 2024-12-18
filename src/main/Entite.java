package main;

public abstract class Entite {
    private int force; //attaque physique mélée
    private int intelligence; //attaque magie
    private int dexterite; //attaque physique distance
    private int defense; //réduction de dégat

    public Entite(int force, int intelligence, int dexterite, int defense){
        this.force = force;
        this.intelligence = intelligence;
        this.dexterite = dexterite;
        this.defense = defense;
    }

    public int getForce() {
        return force;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getDexterite() {
        return dexterite;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense){
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "force = " + force + ", intelligence = " + intelligence + ", dexterite = " + dexterite + ", defense = "
                + defense;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setDexterite(int dexterite) {
        this.dexterite = dexterite;
    }
}