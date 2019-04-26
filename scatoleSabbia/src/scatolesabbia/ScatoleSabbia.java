/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

import processing.core.PApplet;

/**
 *
 * @author Federico Saccani
 */
public class ScatoleSabbia extends PApplet{

    private static  DatiCondivisi dati;
    private static ThScatola[] thScatole;
    private static int numScatole=3;

    /**
     * costruttore
     */
    public ScatoleSabbia(){
        //Inizializzo i dati condivisi e il numero di th
        dati = new DatiCondivisi(numScatole,this);
        thScatole = new ThScatola[numScatole];
        //Assegno al vettore con i th i thread delle scatole
        for(int i=0; i<numScatole;i++)
            thScatole[i] = new ThScatola(dati,i);
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"scatolesabbia.ScatoleSabbia"});//Imposto questo come main principale

        faiPartireITh();

        new ThInput(dati).start();
    }

    public void settings() {
        size(1080, 760);
    }
    
    private static void faiPartireITh(){
        //Faccio partire i th
        for(int i=0; i<numScatole;i++)
            thScatole[i].start();
    }

    public void setup() {
        noStroke();
        frameRate(30);
        ellipseMode(RADIUS);
    }

    public void draw() {
        background(255, 255, 255);
        
        for(int i=0; i<dati.getNumScatole();i++){
            dati.getScatola(i).draw();
        }
    }
    
}
