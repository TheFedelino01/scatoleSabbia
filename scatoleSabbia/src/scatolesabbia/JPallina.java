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
        this.Posizione = Posizione;
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
        Posizione = new Point();
        Colore = new Color(0,0,0);
        
    }
    
    public void visualizza() {
        //TO DO
    }
    
    public void rimuovi() {
        
        Presente = false;
    }
    
    public void mostra() {
        
        Presente = true;
    }
    
    
    
    
    
    public void sposta(Point nuovaPos) { 
        Posizione = nuovaPos;
    }
    
    
    

    
    
    
    
}
