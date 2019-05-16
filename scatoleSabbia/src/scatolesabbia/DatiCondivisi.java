/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

import processing.core.PApplet;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;

/**
 * @author feder
 */
public class DatiCondivisi {

    //TODO
    private Vector<Vector<Scatola>> scatole;
    private int numS;
    private int dimensioneSchermoX;
    private int dimensioneSchermoY;
    private int inclinazioneTavoloDiGiocoX;
    private int inclinazioneTavoloDiGiocoY;
    private Semaphore finito;
    private int dimensioneScatolaX;
    private int dimensioneScatolaY;

    public Vector<Vector<Scatola>> getScatole() {
        return scatole;
    }

    public List<Scatola> getScatoleInUnaLista() {
        List<Scatola> v = new ArrayList<Scatola>();
        for (Vector<Scatola> vett : scatole) {
            for (Scatola s : vett) {
                v.add(s);
            }
        }
        return v;
    }

    public void setScatole(Vector<Vector<Scatola>> scatole) {
        this.scatole = scatole;
    }

    public int getDimensioneSchermoY() {
        return dimensioneSchermoY;
    }

    public void setDimensioneSchermoY(int dimensioneSchermoY) {
        this.dimensioneSchermoY = dimensioneSchermoY;
    }

    public int getDimensioneScatolaX() {
        return dimensioneScatolaX;
    }

    public void setDimensioneScatolaX(int dimensioneScatolaX) {
        this.dimensioneScatolaX = dimensioneScatolaX;
    }

    public int getDimensioneScatolaY() {
        return dimensioneScatolaY;
    }

    public void setDimensioneScatolaY(int dimensioneScatolaY) {
        this.dimensioneScatolaY = dimensioneScatolaY;
    }

    public DatiCondivisi(int numScatole, PApplet processingSketch, int dimensioneScatolaX, int dimensioneScatolaY, int dimensioneSchermoY, int dimensioneSchermoX) {
        this.dimensioneScatolaX = dimensioneScatolaX;
        this.dimensioneScatolaY = dimensioneScatolaY;
        this.dimensioneSchermoY = dimensioneSchermoY;
        this.dimensioneSchermoX = dimensioneSchermoX;
        int spazioTraScatole = 10;
        numS = numScatole;
        int c = 0;
        scatole = new Vector<>();
        int numScatolePerRigaPossibili = dimensioneSchermoX / dimensioneScatolaX;
        int numScatolePerColonnaPossibili = dimensioneSchermoY / dimensioneScatolaY;
        //Aggiungo le scatole al vettore
        for (int i = 0; i < numScatolePerRigaPossibili; i++) {
            scatole.add(new Vector<Scatola>());
            for (int j = 0; j < numScatolePerColonnaPossibili; j++) {
                if (c < numS) {
                    scatole.get(i).add(new Scatola(processingSketch, new Point(j * dimensioneScatolaX + spazioTraScatole * (j + 1), i * dimensioneScatolaY + spazioTraScatole)));
                }
                //else
                //   scatole.get(i).add(new Scatola(processingSketch, new Point(i*dimensioneScatolaX, j*dimensioneScatolaY /*,false**/)));
                c++;
            }
        }

        //scatole.add(new Scatola(processingSketch, new Point(200 * i + 100, 200)));
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
        return dimensioneSchermoY;
    }

    public void setDimensioneSchermoy(int dimensioneSchermoy) {
        this.dimensioneSchermoY = dimensioneSchermoy;
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

    public void incInclinazioneTavoloDiGiocoY(int incremento) {
        this.inclinazioneTavoloDiGiocoY += incremento;
    }

    public void incInclinazioneTavoloDiGiocoX(int incremento) {
        this.inclinazioneTavoloDiGiocoX += incremento;
    }

    public Semaphore getFinito() {
        return finito;
    }

    public void setFinito(Semaphore finito) {
        this.finito = finito;
    }

    public Scatola getScatola(int r, int c) {
        return (Scatola) scatole.get(r).get(c);
    }

    public Scatola getScatolaAdiacente(int r, int c, Directions direction) {
        r /= dimensioneScatolaX;
        c /= dimensioneScatolaY;
        Scatola scatola = null;
        if (direction == Directions.SOPRA) {
            try {
                scatola = scatole.get(r + 1).get(c);
            } catch (Exception e) {
                scatola = null;
            }
        }
        if (direction == Directions.SOTTO) {
            try {
                scatola = scatole.get(r - 1).get(c);
            } catch (Exception e) {
                scatola = null;
            }
        }
        if (direction == Directions.DESTRA) {
            try {
                scatola = scatole.get(r).get(c + 1);
            } catch (Exception e) {
                scatola = null;
            }
        }
        if (direction == Directions.SINISTRA) {
            try {
                scatola = scatole.get(r).get(c - 1);
            } catch (Exception e) {
                scatola = null;
            }
        }
        return scatola;
    }

    public int getNumScatole() {
        return numS;
    }
}
