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
    /**
     * @brief matrice con le scatole
     * @author Colombo Alessandro
     * @version 1.0
     */
    private Vector<Vector<Scatola>> scatole;
    /**
     * @brief numero delle scatole
     * @author Colombo Alessandro
     * @version 1.0
     */
    private int numS;
    /**
     * @brief dimensione dello schermo x
     * @author Colombo Alessandro
     * @version 1.0
     */
    private int dimensioneSchermoX;
    /**
     * @brief dimensione dello schermo y
     * @author Colombo Alessandro
     * @version 1.0
     */
    private int dimensioneSchermoY;
    /**
     * @brief dimensione del tavolo di gioco x
     * @author Colombo Alessandro
     * @version 1.0
     */
    private int inclinazioneTavoloDiGiocoX;
    /**
     * @brief dimensione del tavolo di gioco y
     * @author Colombo Alessandro
     * @version 1.0
     */
    private int inclinazioneTavoloDiGiocoY;
    /**
     * @brief semaforo di segnale finito
     * @author Colombo Alessandro
     * @version 1.0
     */
    private Semaphore finito;
    /**
     * @brief dimensione scatola x
     * @author Colombo Alessandro
     * @version 1.0
     */
    private int dimensioneScatolaX;
    /**
     * @brief dimensione scatola y
     * @author Colombo Alessandro
     * @version 1.0
     */
    private int dimensioneScatolaY;

    /**
     * @return scatole valore dell'attributo quantitaSabbia della classe
     * @brief permette di ottenere la matrice
     * @author Colombo Alessandro
     * @version 1.0
     */
    public Vector<Vector<Scatola>> getScatole() {
        return scatole;
    }
    /**
     * @return v lista delle scatole
     * @brief Permette di ottenere una lista delle scatele
     * @author Colombo Alessandro
     * @version 1.0
     */
    public List<Scatola> getScatoleInUnaLista() {
        List<Scatola> v = new ArrayList<Scatola>();
        for (Vector<Scatola> vett : scatole) {
            for (Scatola s : vett) {
                v.add(s);
            }
        }
        return v;
    }
/**
     * @brief Permette di cambiare la lista di sactole
     * @param scatole 
     * @author Colobmo Alessandro
     * @version 1.0
     */
    public void setScatole(Vector<Vector<Scatola>> scatole) {
        this.scatole = scatole;
    }
    
    /**
     * @return dimensioneSchermoY
     * @brief Ritorna la dimensione y dello schermo
     * @author Colobmo Alessandro
     * @version 1.0
     */
    public int getDimensioneSchermoY() {
        return dimensioneSchermoY;
    }

    /**
     * @brief permette di cambiare la dimensione dello schermo
     * @param dimensioneSchermoY dimensionedello schermo y
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void setDimensioneSchermoY(int dimensioneSchermoY) {
        this.dimensioneSchermoY = dimensioneSchermoY;
    }
    
    /**
     * @return dimensioneScatolaX dimensione della scatola x
     * @brief permette di otteneere le dimensionidello schermo
     * @author Colombo Alessandro
     * @version 1.0
     */
    public int getDimensioneScatolaX() {
        return dimensioneScatolaX;
    }

    /**
     * @brief permette di cambiare la dimensione dello schermo x
     * @param dimensioneScatolaX dimensione schermo x
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void setDimensioneScatolaX(int dimensioneScatolaX) {
        this.dimensioneScatolaX = dimensioneScatolaX;
    }

    /**
     * @return dimensioneScatolaY dimensione dello schermo y
     * @brief permette di ottenere la dimensione y dello schermo
     * @author Colombo Alessandro
     * @version 1.0
     */
    public int getDimensioneScatolaY() {
        return dimensioneScatolaY;
    }

    /**
     * @brief permette di cambiare le dimensioni y della scatola
     * @param dimensioneScatolaY diemnsione scatola y
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void setDimensioneScatolaY(int dimensioneScatolaY) {
        this.dimensioneScatolaY = dimensioneScatolaY;
    }

    /**
     * @brief costruttore con parametri
     * costruttore con parametri che crea le scatole in base alla grandezza dello schermo e delle sctole 
     * @param numScatole numero delle scatole
     * @param processingSketch Sketch di  processing
     * @param dimensioneScatolaX diemnsione scatola x
     * @param dimensioneScatolaY diemnsione scatola y
     * @param dimensioneSchermoY dimensione schermo Y
     * @param dimensioneSchermoX dimensione schermo x
     * @author Colombo Alessandro
     * @version 1.0
     */
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
        scatole.get(0).get(0).getPallina().mostraPallina(); //Viene renderizzata solamente la pallina della prima scatola
        //scatole.add(new Scatola(processingSketch, new Point(200 * i + 100, 200)));
    }

    /**
     * @return getNumS numeroscatole
     * @brief permette di ottenere il numero di scatole
     * @author Colombo Alessandro
     * @version 1.0
     */
    public int getNumS() {
        return numS;
    }
/**
     * @brief permette di cambiare il numero delle scatole
     * @param numS numereo di scatole nuovo
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void setNumS(int numS) {
        this.numS = numS;
    }

    /**
     * @return dimensioneScheroX dimensione dello schermo X
     * @brief permette di ottenere la dimensione x dello schermo
     * @author Colombo Alessandro
     * @version 1.0
     */
    public int getDimensioneSchermoX() {
        return dimensioneSchermoX;
    }

    /**
     * @brief permette di ottenere la dimensione x dello schermo
     * @param dimensioneSchermoX dimensione dello schermo
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void setDimensioneSchermoX(int dimensioneSchermoX) {
        this.dimensioneSchermoX = dimensioneSchermoX;
    }

    /**
     * @return inclinazioneTavoloDiGiocoX inclinazione x
     * @brief permette di ottenere la inclinazione x
     * @author Colombo Alessandro
     * @version 1.0
     */
    public int getInclinazioneTavoloDiGiocoX() {
        return inclinazioneTavoloDiGiocoX;
    }

    /**
     * @brief permette di cambiare la inclinazione x
     * @param inclinazioneTavoloDiGiocoX inclinazione del tavolo x
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void setInclinazioneTavoloDiGiocoX(int inclinazioneTavoloDiGiocoX) {
        this.inclinazioneTavoloDiGiocoX = inclinazioneTavoloDiGiocoX;
    }

    /**
     * @return inclinazioneTavoloDiGiocoY inclinazione y
     * @brief permette di ottenere la inclinazione y
     * @author Colombo Alessandro
     * @version 1.0
     */
    public int getInclinazioneTavoloDiGiocoY() {
        return inclinazioneTavoloDiGiocoY;
    }

    /**
     * @brief permette di cambiare la inclinazione y
     * @param inclinazioneTavoloDiGiocoY inclinazione del tavolo 
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void setInclinazioneTavoloDiGiocoY(int inclinazioneTavoloDiGiocoY) {
        this.inclinazioneTavoloDiGiocoY = inclinazioneTavoloDiGiocoY;
    }
/**
     * @param incremento incremento sull'inclinazione
     * @brief incrementa l'inclinazione y
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void incInclinazioneTavoloDiGiocoY(int incremento) {
        this.inclinazioneTavoloDiGiocoY += incremento;
    }

    /**
     * @param incremento incremento sull'inclinazione
     * @brief incrementa l'inclinazione x
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void incInclinazioneTavoloDiGiocoX(int incremento) {
        this.inclinazioneTavoloDiGiocoX += incremento;
    }
/**
     * @return finito semaforo 
     * @brief permette di ottenere il semaforo finito
     * @author Colombo Alessandro
     * @version 1.0
     */
    public Semaphore getFinito() {
        return finito;
    }
/**
     * @param finito incremento sull'inclinazione
     * @brief ermette di cambiare il semaforo finito
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void setFinito(Semaphore finito) {
        this.finito = finito;
    }

    /**
     * @param r riga della matrice
     * @param c riga della matrice
     * @brief permette di ottenere una scatola secondole coordinate
     * @return scatole una scatola precisa secondo le coordinate nella matrice
     * @author Colombo Alessandro
     * @version 1.0
     */
    public Scatola getScatola(int r, int c) {
        return (Scatola) scatole.get(r).get(c);
    }

    /**
     * @param r riga della matrice
     * @param c riga della matrice
     * @param direction direzione impostata
     * @brief permette di ottenere la scatola adiacente ad unaltra presente nella direzione selezionata
     * @return scatole una scatola precisa secondo le coordinate nella matrice
     * @author Colombo Alessandro
     * @version 1.0
     */
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

    /**

     * @brief permette di ottenere il numero delle scatole
     * @return numS numeroscatole
     * @author Colombo Alessandro
     * @version 1.0
     */
    public int getNumScatole() {
        return numS;
    }
}
