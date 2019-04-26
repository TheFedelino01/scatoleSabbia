/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

import processing.core.PApplet;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Vector;


/**
 * @author feder
 */
public class DatiCondivisi {

    //TODO
    private Vector<Scatola> scatole;
    private int numS;

    public DatiCondivisi(int numScatole, PApplet processingSketch) {
        scatole = new Vector<>();
        numS = numScatole;

        //Aggiungo le scatole al vettore
        for (int i = 0; i < numS; i++)
            scatole.add(new Scatola(processingSketch, new Point(200 * i + 100, 200)));
    }

    public void addScatolola(Scatola scatola) {
        scatole.add(scatola);
    }

    public Scatola getScatola(int idScatola) {
        return (Scatola) scatole.get(idScatola);
    }

    public Scatola getScatolaAdiacente(int idScatola, Directions direction) {
        return null;
    }

    public int getNumScatole() {
        return numS;
    }

    public Vector<Scatola> getScatole() {
        return scatole;
    }
}
