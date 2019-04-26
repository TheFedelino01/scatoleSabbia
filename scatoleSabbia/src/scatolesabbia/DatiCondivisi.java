/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

import processing.core.PApplet;

import java.lang.reflect.Array;
import java.util.Vector;


/**
 *
 * @author feder
 */
public class DatiCondivisi {

    //TODO
    private Vector scatole;
    private int numS;

    public DatiCondivisi(int numScatole, PApplet processingSketch){
        scatole = new Vector<Scatola>();
        numS=numScatole;

        //Aggiungo le scatole al vettore
        for(int i=0; i<numS;i++)
            scatole.add(new Scatola(processingSketch));
    }
    
    public void addScatolola(Scatola scatola){
        scatole.add(scatola);
    }
    public Scatola getScatola(int idScatola) {
        return (Scatola)scatole.get(idScatola);
    }

    public Scatola getScatolaAdiacente(int idScatola, Directions direction) {
        return null;
    }

    public int getNumScatole() {
       return numS;
    }

}
