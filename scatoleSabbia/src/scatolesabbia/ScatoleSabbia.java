/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

import processing.core.PApplet;

import java.util.List;
import java.util.Vector;

/**
 * @author Federico Saccani
 */
public class ScatoleSabbia extends PApplet {

    private static DatiCondivisi dati;
    private static ThScatola[] thScatole;
    private static int numScatole = 3;

    /**
     * costruttore
     */
    public ScatoleSabbia() {
        //Inizializzo i dati condivisi e il numero di th

        dati = new DatiCondivisi(numScatole, this, 100, 100, 500, 1000);
        thScatole = new ThScatola[numScatole];

        //Assegno al vettore con i th i thread delle scatole
        List<Scatola> scatole = dati.getScatoleInUnaLista();
        for (int i = 0; i < scatole.size(); i++)
            thScatole[i] = new ThScatola(dati, scatole.get(i));
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"scatolesabbia.ScatoleSabbia"});//Imposto questo come main principale

        faiPartireITh();

        new ThInput(dati).start();
    }

    public void settings() {
        size(1080, 760);
    }

    private static void faiPartireITh() {
        //Faccio partire i th
        for (int i = 0; i < numScatole; i++)
            thScatole[i].start();
    }

    public void setup() {
        noStroke();
        frameRate(30);
        ellipseMode(RADIUS);
    }

    public void draw() {
        background(255, 255, 255);
        for (Vector<Scatola> v : dati.getScatole())
            for (Scatola s : v) {
                s.draw();
            }
    }
}

