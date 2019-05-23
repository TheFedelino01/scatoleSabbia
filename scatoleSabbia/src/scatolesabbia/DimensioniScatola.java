package scatolesabbia;

import java.awt.*;

/**
 * @brief classe che rappresenta la dimensione di una scatola
 * @author Giacomo Orsenigo
 */
public class DimensioniScatola {
    /**
     * @brief dimensioni della scatola
     * 
     * @author Giacomo Orsenigo
     */
    private final int larghezza, profondita, altezzaSx, altezzaDx, altezzaSopra, altezzaSotto;

    /**
     * @brief costruttore
     * <p>
     * Inizializza tutti gli attributi
     * 
     * @author Giacomo Orsenigo
     */
    public DimensioniScatola() {
        this(100, 100, 100, 100, 100, 100);
    }

    /**
     * @param larghezza
     * @param profondita
     * @param altezzaSx
     * @param altezzaDx
     * @param altezzaSopra
     * @param altezzaSotto
     * @brief costruttore
     * <p>
     * Inizializza tutti gli attributi
     * 
     * @author Giacomo Orsenigo
     */
    public DimensioniScatola(int larghezza, int profondita, int altezzaSx, int altezzaDx, int altezzaSopra, int altezzaSotto) {
        this.larghezza = larghezza;
        this.profondita = profondita;
        this.altezzaSx = altezzaSx;
        this.altezzaDx = altezzaDx;
        this.altezzaSopra = altezzaSopra;
        this.altezzaSotto = altezzaSotto;
    }

    /**
     * @param direzione direzione scelta
     * @return altezza corrispondente
     * @brief get altezza in base alla direzione
     * <p>
     * Restituisce l'altezza della vasca nel lato ricevuto
     * 
     * @author Giacomo Orsenigo
     */
    public int getAltezza(Directions direzione) {
        switch (direzione) {
            case DESTRA:
                return altezzaDx;
            case SINISTRA:
                return altezzaSx;
            case SOPRA:
                return altezzaSopra;
            case SOTTO:
                return altezzaSotto;
        }
        return 0;
    }

    /**
     *
     * @return larghezza
     * 
     * @author Giacomo Orsenigo
     */
    public int getLarghezza() {
        return larghezza;
    }
    /**
     *
     * @return profondita
     * 
     * @author Giacomo Orsenigo
     */
    public int getProfondita() {
        return profondita;
    }
    /**
     *
     * @return altezzaSx
     * 
     * @author Giacomo Orsenigo
     */
    public int getAltezzaSx() {
        return altezzaSx;
    }
    /**
     *
     * @return altezzaDx
     * 
     * @author Giacomo Orsenigo
     */
    public int getAltezzaDx() {
        return altezzaDx;
    }
    /**
     *
     * @return altezzaSopra
     * 
     * @author Giacomo Orsenigo
     */
    public int getAltezzaSopra() {
        return altezzaSopra;
    }
    /**
     *
     * @return altezzaSotto
     * 
     * @author Giacomo Orsenigo
     */
    public int getAltezzaSotto() {
        return altezzaSotto;
    }
}
