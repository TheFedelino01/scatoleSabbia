/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

import processing.core.PApplet;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Vector;
import java.util.concurrent.Semaphore;


/**
 * @author feder
 */
public class DatiCondivisi {

    //TODO
    private Vector<Scatola> scatole;
    private int numS;
    private int dimensioneSchermoX;
    private int dimensioneSchermoy;
    private int inclinazioneTavoloDiGiocoX;
    private int inclinazioneTavoloDiGiocoY;
    private Semaphore finito;

    
    public DatiCondivisi(int numScatole, PApplet processingSketch) {
        scatole = new Vector<>();
        numS = numScatole;

        //Aggiungo le scatole al vettore
        for (int i = 0; i < numS; i++)
            scatole.add(new Scatola(processingSketch, new Point(200 * i + 100, 200)));
    }
    
    
    public int getNumS() {
        return numS;
    }

    public void setNumS(int numS) {
        this.numS = numS;
    }

    public int getDimensioneSchermoX() {
        return dimensioneSchermoX;
    }

    public void setDimensioneSchermoX(int dimensioneSchermoX) {
        this.dimensioneSchermoX = dimensioneSchermoX;
    }

    public int getDimensioneSchermoy() {
        return dimensioneSchermoy;
    }

    public void setDimensioneSchermoy(int dimensioneSchermoy) {
        this.dimensioneSchermoy = dimensioneSchermoy;
    }

    public int getInclinazioneTavoloDiGiocoX() {
        return inclinazioneTavoloDiGiocoX;
    }

    public void setInclinazioneTavoloDiGiocoX(int inclinazioneTavoloDiGiocoX) {
        this.inclinazioneTavoloDiGiocoX = inclinazioneTavoloDiGiocoX;
    }

    public int getInclinazioneTavoloDiGiocoY() {
        return inclinazioneTavoloDiGiocoY;
    }

    public void setInclinazioneTavoloDiGiocoY(int inclinazioneTavoloDiGiocoY) {
        this.inclinazioneTavoloDiGiocoY = inclinazioneTavoloDiGiocoY;
    }

    public Semaphore getFinito() {
        return finito;
    }

    public void setFinito(Semaphore finito) {
        this.finito = finito;
    }

    

    public void addScatolola(Scatola scatola) {
        scatole.add(scatola);
    }

    public Scatola getScatola(int idScatola) {
        return (Scatola) scatole.get(idScatola);
    }

    public Scatola getScatolaAdiacente(int idScatola, Directions direction) {
        return null;
    }

    public int getNumScatole() {
        return numS;
    }

    public Vector<Scatola> getScatole() {
        return scatole;
    }
}
