package main;

public class Arme extends Entite{
    private String nom;
    private double num;

    public Arme(String nom, int force, int intelligence, int dexterite, int defense,  double num) {
        super(force, intelligence, dexterite, defense);
        this.nom = nom;
        this.num = num;
    }
    public Arme(){
        super(20,20,20,0);
        this.nom = "Couteau";
        this.num = 0;
    }

    public String getNom() {
        return this.nom;
    }

    public double getChanceDrop(){
        return this.num;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        long temp;
        temp = Double.doubleToLongBits(num);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Arme other = (Arme) obj;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (Double.doubleToLongBits(num) != Double.doubleToLongBits(other.num))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return nom +" : "+  super.toString();
    }
    
}
