package org.example;

import org.example.FFT.*;
import org.example.Neurone.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Création d'un signal test simple
        Complexe[] signalTest = new Complexe[FFTCplx.TailleFFTtest];
        for (int i = 0; i < FFTCplx.TailleFFTtest; ++i)
            signalTest[i] = new ComplexeCartesien(Math.cos(2. * Math.PI * i / FFTCplx.TailleFFTtest * FFTCplx.Periode), 0);

        // On applique la FFT sur ce signal
        Complexe[] resultat = FFTCplx.appliqueSur(signalTest);

        // On affiche les valeurs du résultat
        for (int i = 0; i < resultat.length; ++i) {
            System.out.print(i + " : (" + (float) resultat[i].reel() + " ; " + (float) resultat[i].imag() + "i)");
            System.out.println(", (" + (float) resultat[i].mod() + " ; " + (float) resultat[i].arg() + " rad)");
        }

        // Fréquence du signal d'entrée
        int frequenceSignal = 440; // Exemple avec une fréquence de 440 Hz

        // Nombre de périodes du signal d'entrée
        int nombrePeriods = 4; // Exemple avec 4 périodes

        // Nombre d'échantillons par période
        int nombreEchantillons = 1024; // Exemple avec 1024 échantillons par période

        // Générer le signal d'entrée avec la fréquence et le nombre de périodes spécifiés
        float[] signalEntree = genererSignalEntree(frequenceSignal, nombrePeriods, nombreEchantillons);

        // Convertir le signal d'entrée en tableau de Complexe
        Complexe[] signalComplexe = convertirEnComplexe(signalEntree);

        // Appliquer la FFT sur le signal d'entrée
        Complexe[] resultatFFT = FFTCplx.appliqueSur(signalComplexe);

        // Analyser la sortie de la FFT
        analyserSortieFFT(resultatFFT);

        // Test de la classe NeuroneHeavyside
        int nbEntrees = 2;
        iNeurone neurone = new NeuroneHeavyside(nbEntrees);

        // Tableau des entrées de la fonction ET (0 = faux, 1 = vrai)
        float[][] entrees = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};

        // Tableau des sorties attendues de la fonction ET
        float[] resultats = {0, 0, 0, 1};

        // Apprentissage du neurone
        int nbIterations = neurone.apprentissage(entrees, resultats);

        // Affichage des poids synaptiques et du biais
        Neurone vueNeurone = (Neurone) neurone;
        System.out.println("Synapses : " + Arrays.toString(vueNeurone.synapses()));
        System.out.println("Biais : " + vueNeurone.biais());

        // Test des entrées avec le neurone entraîné
        for (int i = 0; i < entrees.length; ++i) {
            float[] entree = entrees[i];
            neurone.metAJour(entree);
            System.out.println("Entree " + i + " : " + neurone.sortie());
        }
    }
    private static float[] genererSignalEntree(int frequence, int nombrePeriods, int nombreEchantillons) {
        float[] signal = new float[nombrePeriods * nombreEchantillons];
        double periode = 1.0 / frequence;

        for (int i = 0; i < signal.length; i++) {
            double temps = i / (double) nombreEchantillons;
            signal[i] = (float) Math.cos(2 * Math.PI * frequence * temps);
        }

        return signal;
    }

    // Méthode pour convertir un tableau de float en tableau de Complexe
    private static Complexe[] convertirEnComplexe(float[] signal) {
        Complexe[] signalComplexe = new Complexe[signal.length];
        for (int i = 0; i < signal.length; i++) {
            signalComplexe[i] = new ComplexeCartesien(signal[i], 0);
        }
        return signalComplexe;
    }

    // Méthode pour analyser la sortie de la FFT
    private static void analyserSortieFFT(Complexe[] resultatFFT) {
        for (int i = 0; i < resultatFFT.length; i++) {
            double frequence = i / (double) resultatFFT.length;
            double amplitude = resultatFFT[i].mod();
            double phase = resultatFFT[i].arg();

            System.out.println("Fréquence " + frequence + " : Amplitude = " + amplitude + ", Phase = " + phase);
        }
    }
}
