/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;


import javafx.scene.shape.Box;
import processing.core.PApplet;

import java.awt.*;


/**
 * @author Giacomo Orsenigo
 */
public class Scatola {
    private final PApplet processingSketch;

    //Indica il grado di inclinazione della scatola
    private float inclinazioneX;
    private float inclinazioneY;

    //Indica le dimensioni della scatola
    private Box dimensioni; //misurata in cm
    private Point posizione;

    //Indica la sabbia presente nella scatola
    private CSabbia sabbiaPresente;

    private JPallina pallina;

    //Rappresenta la pallina presente nella scatola (se presente)
    //private CPallina pallina

    public Scatola(PApplet processingSketch) {
        this.processingSketch = processingSketch;
        dimensioni = new Box();
        dimensioni.setDepth(100);
        dimensioni.setHeight(500);

        posizione = new Point(200, 200);

        sabbiaPresente = new CSabbia();
        pallina = new JPallina();
        pallina.mostraPallina();
    }

    public Scatola(PApplet processingSketch, Point posizione) {
        this.processingSketch = processingSketch;
        dimensioni = new Box();
        dimensioni.setWidth(100);
        dimensioni.setDepth(100);
        dimensioni.setHeight(50);
        this.posizione = posizione;
        sabbiaPresente = new CSabbia(10);
        pallina = new JPallina();
        pallina.mostraPallina();
        inclinazioneX = 0;
        inclinazioneY = 0;
    }

    public Scatola(PApplet processingSketch, Box dimensioni, Point posizione, CSabbia sabbiaPresente, JPallina pallina) {
        this.processingSketch = processingSketch;
        this.dimensioni = dimensioni;
        this.posizione = posizione;
        this.sabbiaPresente = sabbiaPresente;
        this.pallina = pallina;
        inclinazioneX = 0;
        inclinazioneY = 0;
        pallina = new JPallina();
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
        pallina.sposta(inclinazioneX);
    }

    //Metodo che permette di aggiornare l'altezza e la velocita della sabbia nella parte destra e sinistra della scatola in base ai gradi di inclinazione della scatola'/
    public void aggiornaDistribuzioneVelocitaSabbia() {
        sabbiaPresente.aggiornati(inclinazioneX, dimensioni);
    }

    /**
     * sposta la sabbia in un'altra scatola
     *
     * @param altezza sabbia da spostare
     * @param altra   scatola in cui spostare la sabbia
     */
    public void spostaSabbia(float altezza, Scatola altra) {
        sabbiaPresente.rimuoviSabbia(altezza);
        altra.sabbiaPresente.aggiungiSabbia(altezza);
    }

    /**
     * sposta la pallina in un'altra scatola
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

    public Box getDimensioni() {
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
        processingSketch.rect(posizione.x, posizione.y, (float) dimensioni.getWidth(), (float) dimensioni.getDepth());

        if (pallina.isPresente()) {
            processingSketch.fill(pallina.getColore().getRGB());
            processingSketch.ellipse(pallina.getPosizione().x + posizione.x, pallina.getPosizione().y + posizione.y, pallina.getDimensioni(), pallina.getDimensioni());
        }

        processingSketch.noFill();
        processingSketch.stroke(0, 0, 0);
        processingSketch.rect(posizione.x, posizione.y, (float) dimensioni.getWidth(), (float) dimensioni.getDepth());
    }

    public float getInclinazioneX() {
        return inclinazioneX;
    }

    public float getInclinazioneY() {
        return inclinazioneY;
    }

    public Directions isPallinaControBordi() {
        if (pallina.getPosizione().x > dimensioni.getWidth())
            return Directions.DESTRA;
        else if (pallina.getPosizione().x < 0)
            return Directions.SINISTRA;
        else if (pallina.getPosizione().y < 0)
            return Directions.SOPRA;
        else if (pallina.getPosizione().y > dimensioni.getDepth())
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
