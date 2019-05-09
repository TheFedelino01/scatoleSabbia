/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

import java.awt.Color;
import java.awt.Point;
import processing.core.PApplet;
/**
 *
 * @author Samuele Peduzzi
 */

public final class JPallina {
    
    
    private Point posScatola; //angolo sx scatola
    
    private PApplet processingSketch;
    
    private float velocitaSpostamento; //valore tra 0 e 1
    private Point Posizione; //Indicante le coordinate all'interno della scatola dell'oggetto
    private boolean Presente; //Indicante la presenza della pallina o viceversa
   
    private float Dimensioni; //Indicante la lunghezza del diametro della pallina
    
    private Color Colore; //Colore della pallina 
    
    private int r,c; //Posizione scatola nella quale è presente la pallina 


    public JPallina(float velocitaSpostamento, Point Posizione, boolean Presente, float Dimensioni, Color Colore) {
        this.velocitaSpostamento = velocitaSpostamento;
        this.Posizione = Posizione; //La posizione andrà impostata al centro della scatola
        this.Presente = Presente;
        this.Dimensioni = Dimensioni;
        this.Colore = Colore;
    }

    
    public JPallina(PApplet processingSketch, Point posScatola) {
        
        this.processingSketch = processingSketch;
        this.posScatola = posScatola;
        reset();
    }
    
    
    public void reset() {
        
        velocitaSpostamento = 0f;
        Presente = false; 
        Dimensioni = 10.0f;
        Posizione = new Point(); //La posizione andrà impostata al centro della scatola
        Colore = new Color(0,0, 240);
        
    }
    
    public void visualizza() {
        //TO DO
    }
    
    public void rimuoviPallina() { //La pallina viene rimossa
        
        Presente = false;
    }
    
    public void mostraPallina() { //Viene aggiunta la pallina
        
        Presente = true;
    }
    
    public void sposta(Point nuovaPos) { 
        Posizione = nuovaPos;
    }
    
    public void spostaX(float inclinazioneScatolaX) {
        
        velocitaSpostamento = Math.abs(inclinazioneScatolaX); //La velocità è direttamente proporzionale all'inclinazione della scatola

        if(inclinazioneScatolaX > 0) //Se la scatola è inclinata verso destra la scatola si spostaX verso destra
            Posizione.x += (0.5 * velocitaSpostamento);
        else
            Posizione.x += (-0.5 * velocitaSpostamento);
    }
    
    public void spostaY(float inclinazioneScatolaY) {
        
        velocitaSpostamento = Math.abs(inclinazioneScatolaY); //La velocità è direttamente proporzionale all'inclinazione della scatola

        if(inclinazioneScatolaY > 0) //Se la scatola è inclinata verso destra la scatola si spostaX verso destra
            Posizione.y += (0.5 * velocitaSpostamento);
        else
            Posizione.y += (-0.5 * velocitaSpostamento);
    }
    
    public void draw() {
        
        if (Presente) {
            processingSketch.fill(Colore.getRGB());
            processingSketch.ellipse(Posizione.x + posScatola.x, Posizione.y + posScatola.y, Dimensioni, Dimensioni);
        }


    }

    public boolean isPresente() {
        return Presente;
    }

    public Point getPosizione() {
        return Posizione;
    }

    public float getDimensioni() {
        return Dimensioni;
    }

    public Color getColore() {
        return Colore;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }
    
    
    
}
