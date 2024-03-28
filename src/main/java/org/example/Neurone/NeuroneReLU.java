package org.example.Neurone;

public class NeuroneReLU extends Neurone {
    public NeuroneReLU(int nbEntrees) {
        super(nbEntrees);
    }
    @Override
    protected float activation(float valeur) {
        float valeurRetour=0;
        if (valeur<0){
            valeurRetour=0;
        }else if (valeur>=0){
            valeurRetour=valeur;
        }
        return valeurRetour;
    }
}
