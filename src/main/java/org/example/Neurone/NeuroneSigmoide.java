package org.example.Neurone;

public class NeuroneSigmoide extends Neurone {

    public NeuroneSigmoide(int nbEntrees) {
        super(nbEntrees);
    }
    @Override

    protected float activation(float valeur){
        double valeurDouble = 1/(1+Math.exp(-valeur));
        float valeurFloat = (float)valeurDouble;
        return valeurFloat;
    }
}
