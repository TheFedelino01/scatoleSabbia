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
    private static ThPallina[] thPalline;
    private static int numScatole = 5;

    /**
     * costruttore
     */
    public ScatoleSabbia() {
        //Inizializzo i dati condivisi e il numero di th

        dati = new DatiCondivisi(numScatole, this, 100, 100, 500, 1000);
        thScatole = new ThScatola[numScatole];
        thPalline = new ThPallina[numScatole];

        //Assegno al vettore con i th i thread delle scatole
        List<Scatola> scatole = dati.getScatoleInUnaLista();
        
        for (int i = 0; i < scatole.size(); i++) {
            thScatole[i] = new ThScatola(dati, scatole.get(i));
            Scatola s = scatole.get(i);
            JPallina p = new JPallina(this,s.getPosizione(),s,i);
            s.addPallina(p);
            p.mostraPallina();
            thPalline[i] = new ThPallina(dati, p);
        }

//
//        Scatola s = scatole.get(0);
//        JPallina p = new JPallina(this,s.getPosizione(),s,1);
//        s.addPallina(p);
//        p.mostraPallina(); //Viene renderizzata solamente la pallina della prima scatola
//
//
//        thPalline[0] = new ThPallina(dati,p);
            
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"scatolesabbia.ScatoleSabbia"});//Imposto questo come main principale

        faiPartireITh();

        new SwingGui.InputAccelerometro(dati).setVisible(true);
        new SwingGui.inputGUI().setVisible(true);//TO FIX IT
        new SwingGui.BallColorChooser(dati).setVisible(true);
     
    }

    public void settings() {
        size(1080, 760);
    }

    private static void faiPartireITh() {
        //Faccio partire i th
        for (int i = 0; i < numScatole; i++) {
            thScatole[i].start();
            thPalline[i].start();
        }
            
        
       
    }

    public void setup() {
        noStroke();
        frameRate(100);
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

