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
     * @brief Inclinazione della scatola lungo l'asse X
     */
    private float inclinazioneX;

    /**
     * @brief Inclinazione della scatola lungo l'asse Y
     */
    private float inclinazioneY;


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
    


    public Scatola(PApplet processingSketch, Point posizione) {
        this(processingSketch,new DimensioniScatola(),posizione,new CSabbia(processingSketch),new JPallina(processingSketch, posizione));
    }

    public Scatola(PApplet processingSketch, DimensioniScatola dimensioni, Point posizione, CSabbia sabbiaPresente, JPallina pallina) {
        this.processingSketch = processingSketch;
        this.dimensioni = dimensioni;
        this.posizione = posizione;
        this.sabbiaPresente = sabbiaPresente;
        this.pallina = pallina;
        inclinazioneX = 0;
        inclinazioneY = 0;
        pallina.mostraPallina();
    }


    //Metodo che permette di rappresentare la scatola visualizzando la sabbia, la pallina e le finestre sul dispositivo
    public void visualizzaScatola() {
    }

    //Metodo che permette di simulare un movimento  della scatola richiamando aggiornamento Sabbia, poi aggiornamento Pallina e successivamento visualizzazione scatola'/
    public void muovi() {
        aggiornaDistribuzioneVelocitaSabbia();
        aggiornaPosPallina();
    }

    //Metodo che permette di aggiornare la posizione della pallina all'interno della scatola a seconda della velocita' della sabbia
    public void aggiornaPosPallina() {
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

    //Metodo che permette di aggiornare l'altezza e la velocita della sabbia nella parte destra e sinistra della scatola in base ai gradi di inclinazione della scatola'/
    public void aggiornaDistribuzioneVelocitaSabbia() {
        sabbiaPresente.aggiornati(inclinazioneX, dimensioni);
    }

    /**
     * spostaX la sabbia in un'altra scatola
     *
     * @param altezza sabbia da spostare
     * @param altra   scatola in cui spostare la sabbia
     */
    public void spostaSabbia(float altezza, Scatola altra) {
        sabbiaPresente.rimuoviSabbia(altezza);
        altra.sabbiaPresente.aggiungiSabbia(altezza);
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

    public CSabbia getSabbiaPresente() {
        return sabbiaPresente;
    }

    public DimensioniScatola getDimensioni() {
        return dimensioni;
    }

    public void setPallina(JPallina pallina) {
        this.pallina = pallina;
    }

    public JPallina getPallina() {
        return pallina;
    }

    public Point getPosizioneCentrale() {
        return posizione;
    }


    public void draw() {
        processingSketch.fill(processingSketch.color(240, 0, 0));
        processingSketch.rect(posizione.x, posizione.y, (float) dimensioni.getLarghezza(), (float) dimensioni.getProfondita());

//        if (pallina.isPresente()) {
//            processingSketch.fill(pallina.getColore().getRGB());
//            processingSketch.ellipse(pallina.getPosizione().x + posizione.x, pallina.getPosizione().y + posizione.y, pallina.getDimensioni(), pallina.getDimensioni());
//        }

        pallina.draw();

        processingSketch.noFill();
        processingSketch.stroke(0, 0, 0);
        processingSketch.rect(posizione.x, posizione.y, (float) dimensioni.getLarghezza(), (float) dimensioni.getProfondita());

        sabbiaPresente.visualizza(this);
    }

    public float getInclinazioneX() {
        return inclinazioneX;
    }

    public float getInclinazioneY() {
        return inclinazioneY;
    }

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

    public void setInclinazioneX(float inclinazioneX) {
        this.inclinazioneX = inclinazioneX;
    }

    public void setInclinazioneY(float inclinazioneY) {
        this.inclinazioneY = inclinazioneY;
    }


}
