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
    private Semaphore finito;//RM-MIO
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
    public List<Scatola> ottieniListaConScatole() {
        List<Scatola> RIS = new ArrayList<Scatola>();
        
        for (Vector<Scatola> vett : scatole) {
            for (Scatola s : vett) {
                RIS.add(s);
            }
        }
        return RIS;
    }

    /**
     * @param scatole
     * @brief Permette di cambiare la lista di sactole
     * @author Colobmo Alessandro
     * @version 1.0
     */
    public void setScatole(Vector<Vector<Scatola>> scatole) {//RM-MIO
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
     * @param dimensioneSchermoY dimensionedello schermo y
     * @brief permette di cambiare la dimensione dello schermo
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void setDimensioneSchermoY(int dimensioneSchermoY) {//RM-MIO
        this.dimensioneSchermoY = dimensioneSchermoY;
    }

    /**
     * @return dimensioneScatolaX dimensione della scatola x
     * @brief permette di otteneere le dimensionidello schermo
     * @author Colombo Alessandro
     * @version 1.0
     */
    public int getDimensioneScatolaX() {//RM-MIO
        return dimensioneScatolaX;
    }

    /**
     * @param dimensioneScatolaX dimensione schermo x
     * @brief permette di cambiare la dimensione dello schermo x
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void setDimensioneScatolaX(int dimensioneScatolaX) {//RM-MIO
        this.dimensioneScatolaX = dimensioneScatolaX;
    }

    /**
     * @return dimensioneScatolaY dimensione dello schermo y
     * @brief permette di ottenere la dimensione y dello schermo
     * @author Colombo Alessandro
     * @version 1.0
     */
    public int getDimensioneScatolaY() {//RM-MIO
        return dimensioneScatolaY;
    }

    /**
     * @param dimensioneScatolaY diemnsione scatola y
     * @brief permette di cambiare le dimensioni y della scatola
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void setDimensioneScatolaY(int dimensioneScatolaY) {//RM-MIO
        this.dimensioneScatolaY = dimensioneScatolaY;
    }

    /**
     * @param numScatole         numero delle scatole
     * @param processingSketch   Sketch di  processing
     * @param dimensioneScatolaX diemnsione scatola x
     * @param dimensioneScatolaY diemnsione scatola y
     * @param dimensioneSchermoY dimensione schermo Y
     * @param dimensioneSchermoX dimensione schermo x
     * @brief costruttore con parametri
     * costruttore con parametri che crea le scatole in base alla grandezza dello schermo e delle sctole
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
            scatole.add(new Vector<Scatola>());//PER OGNI RIGA AGGIUNGO UN VETTORE
            for (int j = 0; j < numScatolePerColonnaPossibili; j++) {
                if (c < numS) {
                    int quant = 0;
                    if (c == 4) {
                        quant = 100;
                    } else {
                        quant = 0;
                    }
                    
                    //AL VETTORE DELLA RIGA, AGGIUNGO LA SCATOLA
                    scatole.get(i).add(new Scatola(processingSketch, new Point(j, i), new Point(j * dimensioneScatolaX + spazioTraScatole * (j + 1), i * dimensioneScatolaY + spazioTraScatole), quant));
                }
                //else
                //   scatole.get(i).add(new Scatola(processingSketch, new Point(i*dimensioneScatolaX, j*dimensioneScatolaY /*,false**/)));
                c++;


            }


        }
    }

    /**
     * @return getNumS numeroscatole
     * @brief permette di ottenere il numero di scatole
     * @author Colombo Alessandro
     * @version 1.0
     */
    public int getNumS() {//RM-MIO
        return numS;
    }

    /**
     * @param numS numereo di scatole nuovo
     * @brief permette di cambiare il numero delle scatole
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void setNumS(int numS) {//RM-MIO
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
     * @param dimensioneSchermoX dimensione dello schermo
     * @brief permette di ottenere la dimensione x dello schermo
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void setDimensioneSchermoX(int dimensioneSchermoX) {//RM-MIO
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
     * @param inclinazioneTavoloDiGiocoX inclinazione del tavolo x
     * @brief permette di cambiare la inclinazione x
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
     * @param inclinazioneTavoloDiGiocoY inclinazione del tavolo
     * @brief permette di cambiare la inclinazione y
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
    public Semaphore getFinito() {//RM-MIO
        return finito;
    }

    /**
     * @param finito incremento sull'inclinazione
     * @brief ermette di cambiare il semaforo finito
     * @author Colombo Alessandro
     * @version 1.0
     */
    public void setFinito(Semaphore finito) {//RM-MIO
        this.finito = finito;
    }

    /**
     * @param r riga della matrice
     * @param c riga della matrice
     * @return scatole una scatola precisa secondo le coordinate nella matrice
     * @brief permette di ottenere una scatola secondole coordinate
     * @author Colombo Alessandro
     * @version 1.0
     */
    public Scatola getScatola(int r, int c) {//RM-MIO
        return (Scatola) scatole.get(r).get(c);
    }

    /**
     * @param r         riga della matrice
     * @param c         riga della matrice
     * @param direction direzione impostata
     * @return scatole una scatola precisa secondo le coordinate nella matrice
     * @brief permette di ottenere la scatola adiacente ad unaltra presente nella direzione selezionata
     * @author Colombo Alessandro
     * @version 1.0
     */
    public Scatola getScatolaAdiacente(int r, int c, Directions direction) {
//        r /= dimensioneScatolaX;
//        c /= dimensioneScatolaY;
        Scatola scatola = null;
        
        try {
            
            if (direction == Directions.SOPRA) {
                    scatola = scatole.get(r + 1).get(c);
            }
            if (direction == Directions.SOTTO) {
                    scatola = scatole.get(r - 1).get(c);
            }
            if (direction == Directions.DESTRA) {
                    scatola = scatole.get(r).get(c + 1);
            }
            if (direction == Directions.SINISTRA) {

                    scatola = scatole.get(r).get(c - 1);
                } 
            }
        
        catch (Exception e) {
                scatola = null;
        }
        
        return scatola;
    }


    public Scatola getScatolaAdiacente(Scatola scatola, Directions direction) {
        return getScatolaAdiacente(scatola.getPosMatrice().y, scatola.getPosMatrice().x, direction);
    }

    /**
     * @return numS numeroscatole
     * @brief permette di ottenere il numero delle scatole
     * @author Colombo Alessandro
     * @version 1.0
     */
    public int getNumScatole() {
        return numS;
    }
}
