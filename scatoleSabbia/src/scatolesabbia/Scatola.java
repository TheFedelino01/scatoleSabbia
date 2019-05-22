/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;


import javafx.scene.shape.Box;
import processing.core.PApplet;

import java.awt.*;
import java.util.Random;


/**
 * @author Giacomo Orsenigo
 * @brief Classe che rappresenta una scatola, che può contentere sabbia e pallina
 */
public class Scatola {
    /**
     * @brief Puntatore all'istanza di processing per usare le librerie grafiche
     */
    private final PApplet processingSketch;


    /**
     * @brief dimensioni della scatola
     */
    private DimensioniScatola dimensioni; //misurata in cm

    /**
     * @brief posizione della scatola nel piano
     */
    private Point posizione;

    /**
     * @brief sabbia presente nella scatola
     */
    private CSabbia sabbiaPresente;

    /**
     * @brief pallina presente nella scatola (se presente)
     */
    private JPallina pallina;

    /**
     * @brief costruttore
     *
     * Inizializza tutti gli attributi richiamando il costruttore {@link #Scatola(PApplet, DimensioniScatola, Point, CSabbia, JPallina)}
     * @param processingSketch istanza di processing
     * @param posizione posizione della scatola
     */
    public Scatola(PApplet processingSketch, Point posizione) {
        this(processingSketch, new DimensioniScatola(), posizione, new CSabbia(processingSketch), new JPallina(processingSketch, posizione));
    }

    /**
     * @brief costruttore
     *
     * Inizializza tutti gli attributi in base ai parametri
     * @param processingSketch istanza di processing
     * @param dimensioni dimensione della scatola
     * @param posizione posizione della scatola
     * @param sabbiaPresente sabbia presente nella scatola
     * @param pallina pallina
     */
    public Scatola(PApplet processingSketch, DimensioniScatola dimensioni, Point posizione, CSabbia sabbiaPresente, JPallina pallina) {
        this.processingSketch = processingSketch;
        this.dimensioni = dimensioni;
        this.posizione = posizione;
        this.sabbiaPresente = sabbiaPresente;
        this.pallina = pallina;
        pallina.mostraPallina();
    }


    /**
     * @brief muove la scatola
     *
     * Metodo che permette di simulare un movimento  della scatola richiamando aggiornamento Sabbia, poi aggiornamento Pallina e successivamento visualizzazione scatola
     * Richiama {@link #aggiornaDistribuzioneVelocitaSabbia(float, float)} e {@link #aggiornaPosPallina(float, float)}
     * @param inclinazioneX inclinazione X
     * @param inclinazioneY inclinazione Y
     */
    public void muovi(float inclinazioneX, float inclinazioneY) {
        aggiornaDistribuzioneVelocitaSabbia(inclinazioneX, inclinazioneY);
        aggiornaPosPallina(inclinazioneX, inclinazioneY);
    }

    /**
     * @brief aggiorna posizione pallina
     *
     * Metodo che permette di aggiornare la posizione della pallina all'interno della scatola a seconda della velocita' della sabbia
     * Richiama {@link JPallina#spostaX(float)} e {@link JPallina#spostaY(float)}
     * @param inclinazioneX inclinazione X
     * @param inclinazioneY inclinazione Y
     */
    public void aggiornaPosPallina(float inclinazioneX, float inclinazioneY) {
        if (inclinazioneX < 0 && isPallinaControBordi() == Directions.SINISTRA)
            return;
        if (inclinazioneX > 0 && isPallinaControBordi() == Directions.DESTRA)
            return;

        pallina.spostaX(inclinazioneX);

        if (inclinazioneY < 0 && isPallinaControBordi() == Directions.SOPRA)
            return;
        if (inclinazioneY > 0 && isPallinaControBordi() == Directions.SOTTO)
            return;

        pallina.spostaY(inclinazioneY);
    }

    /**
     * @brief aggiorna inclinazione della sabbia
     *
     * Metodo che permette di aggiornare l'altezza e la velocita della sabbia nella parte destra e sinistra della scatola in base ai gradi di inclinazione della scatola
     * Richiama {@link CSabbia#aggiornati(float, DimensioniScatola)}
     * @param inclinazioneX inclinazione X
     * @param inclinazioneY inclinazione Y
     */
    public void aggiornaDistribuzioneVelocitaSabbia(float inclinazioneX, float inclinazioneY) {
        sabbiaPresente.aggiornati(inclinazioneX, dimensioni);
    }

    /**
     * spostaX la sabbia in un'altra scatola
     *
     * @param altezza sabbia da spostare
     * @param altra   scatola in cui spostare la sabbia
     */
    public void spostaSabbia(float altezza, Scatola altra) {
        
        //NON SPOSTO LA SABBIA SE NON CE N'E'!!!!
        
        //DA CONTROLLARE PERCHE' DOPO UN CERTO PUNTO, VA IN LOOP
        //E CONTINUA A RICHIAMARE QUESTO METODO E CONTINUA A DIRE CHE NON PUO'
        //SPOSTARLA... (LA COSA E' CORRETTA MA NON CAPISCO PERCHE' CONTINUA A
        //RICHIAMARLO)...
        if(sabbiaPresente.getQuantita()-altezza<0){
            System.out.println("Dovrei spostare la sabbia ma non ce n'e'");
        }else{
            System.out.println("Sposto la sabbia...");
           sabbiaPresente.rimuoviSabbia(altezza);
            altra.sabbiaPresente.aggiungiSabbia(altezza); 
        }
        
    }

    /**
     * spostaX la pallina in un'altra scatola
     *
     * @param altra scatola in cui spostare la pallina
     */
    public void spostaPallina(Scatola altra) {
        this.pallina.rimuoviPallina();
        altra.setPallina(pallina);
        altra.pallina.mostraPallina();
    }

    /**
     * @brief get sabbia presente
     * @return sabbia presente nella scatola
     */
    public CSabbia getSabbiaPresente() {
        return sabbiaPresente;
    }

    /**
     * @brief get dimensioni
     * @return dimensioni della scatola
     */
    public DimensioniScatola getDimensioni() {
        return dimensioni;
    }

    public void setPallina(JPallina pallina) {
        this.pallina = pallina;
    }

    public JPallina getPallina() {
        return pallina;
    }

    /**
     * get posizione centrale
     * @return posizione
     */
    public Point getPosizioneCentrale() {
        return posizione;
    }

    /**
     * @brief disegna la scatola
     *
     * Disegna la scatola con tutto il contenuto
     * Richiama {@link CSabbia#visualizza(Scatola, boolean)} e {@link JPallina#draw()}
     */
    public void draw() {
        processingSketch.fill(processingSketch.color(255, 255, 255));
        processingSketch.rect(posizione.x, posizione.y, (float) dimensioni.getLarghezza(), (float) dimensioni.getProfondita());

//        if (pallina.isPresente()) {
//            processingSketch.fill(pallina.getColore().getRGB());
//            processingSketch.ellipse(pallina.getPosizione().x + posizione.x, pallina.getPosizione().y + posizione.y, pallina.getDimensioni(), pallina.getDimensioni());
//        }

        pallina.draw();

        processingSketch.noFill();
        processingSketch.stroke(0, 0, 0);
        processingSketch.rect(posizione.x, posizione.y, (float) dimensioni.getLarghezza(), (float) dimensioni.getProfondita());

        sabbiaPresente.visualizza(this, true);
    }

    /**
     * @brief controlla se la pallina è contro i bordi
     * @return direzione del bordo con la pallina appoggiata
     */
    public Directions isPallinaControBordi() {
        if (pallina.getPosizione().x > dimensioni.getLarghezza())
            return Directions.DESTRA;
        else if (pallina.getPosizione().x < 0)
            return Directions.SINISTRA;
        else if (pallina.getPosizione().y < 0)
            return Directions.SOPRA;
        else if (pallina.getPosizione().y > dimensioni.getProfondita())
            return Directions.SOTTO;
        return Directions.NONE;
    }

    /**
     * get posizione
     * @return posizione
     */
    public Point getPosizione() {
        return posizione;
    }
}
