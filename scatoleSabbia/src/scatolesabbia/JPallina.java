/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Samuele Peduzzi
 */
public final class JPallina {
    
    
    private float velocitaSpostamento; //valore tra 0 e 1
    private Point Posizione; //Indicante le coordinate all'interno della scatola dell'oggetto
    private boolean Presente; //Indicante la presenza della pallina o viceversa
   
    private float Dimensioni; //Indicante la lunghezza del diametro della pallina
    
    private Color Colore; //Colore della pallina 

    public JPallina(float velocitaSpostamento, Point Posizione, boolean Presente, float Dimensioni, Color Colore) {
        this.velocitaSpostamento = velocitaSpostamento;
        this.Posizione = Posizione; //La posizione andrà impostata al centro della scatola
        this.Presente = Presente;
        this.Dimensioni = Dimensioni;
        this.Colore = Colore;
    }

    
    public JPallina() {
        
        reset();
    }
    
    
    public void reset() {
        
        velocitaSpostamento = 0.0f;
        Presente = false; 
        Dimensioni = 1.0f;
        Posizione = new Point(); //La posizione andrà impostata al centro della scatola
        Colore = new Color(0,0,0);
        
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
    
    public void sposta(float inclinazioneScatolaX) {
        
        velocitaSpostamento = Math.abs(inclinazioneScatolaX); //La velocità è direttamente proporzionale all'inclinazione della scatola
        
        if(inclinazioneScatolaX > 0) //Se la scatola è inclinata verso destra la scatola si sposta verso destra
            Posizione.x += (1 * velocitaSpostamento);
        else 
            Posizione.x += (-1 * velocitaSpostamento);
    }
    

    
    
    
    
}
