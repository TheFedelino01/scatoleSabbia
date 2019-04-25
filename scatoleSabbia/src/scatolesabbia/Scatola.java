/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;


import javafx.scene.shape.Box;

import java.awt.*;


/**
 * @author Giacomo Orsenigo
 */
public class Scatola {

    //Indica il grado di inclinazione della scatola
    private float inclinazioneX;
    private float inclinazioneY;

    //Indica le dimensioni della scatola
    private final Box dimensioni; //misurata in cm
    private final Point posizione;

    //Indica la sabbia presente nella scatola
    private CSabbia sabbiaPresente;
    
    private JPallina pallina;

    //Rappresenta la pallina presente nella scatola (se presente)
    //private CPallina pallina


    public Scatola(Box dimensioni, Point posizione, CSabbia sabbiaPresente, JPallina pallina) {
        this.dimensioni = dimensioni;
        this.posizione = posizione;
        this.sabbiaPresente = sabbiaPresente;
        this.pallina = pallina;
        inclinazioneX = 0;
        inclinazioneY = 0;
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
    }

    //Metodo che permette di aggiornare l'altezza e la velocita della sabbia nella parte destra e sinistra della scatola in base ai gradi di inclinazione della scatola'/
    public void aggiornaDistribuzioneVelocitaSabbia() {
        sabbiaPresente.aggiornati(inclinazioneX);
    }

    /**
     * sposta la sabbia in un'altra scatola
     *
     * @param altezza sabbia da spostare
     * @param altra scatola in cui spostare la sabbia
     */
    public void spostaSabbia(float altezza, Scatola altra){
        sabbiaPresente.rimuoviSabbia(altezza);
        altra.sabbiaPresente.aggiungiSabbia(altezza);
    }

    /**
     * sposta la pallina in un'altra scatola
     *
     * @param altra scatola in cui spostare la pallina
     */
    public void spostaPallina(Scatola altra){
        
        this.pallina.rimuovi();
        altra.setPallina(pallina);
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
}
