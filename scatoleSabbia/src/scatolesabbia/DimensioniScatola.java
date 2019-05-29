package scatolesabbia;

/**
 * @brief classe che rappresenta la dimensione di una scatola.
 * @author Giacomo Orsenigo
 */
public class DimensioniScatola {
    /**
     * @brief dimensioni della scatola.
     * 
     * @author Giacomo Orsenigo
     */
    private final int larghezza, profondita, altezzaSx, altezzaDx, altezzaSopra, altezzaSotto; //le 4 altezze possono essere diverse

    /**
     * @brief costruttore.
     *
     * Inizializza tutti gli attributi richiamando {@link #DimensioniScatola(int, int, int, int, int, int)}
     * 
     * @author Giacomo Orsenigo
     */
    public DimensioniScatola() {
        this(100, 100, 10, 10, 10, 10);
    }

    /**
     * @brief costruttore.
     *
     * Inizializza tutti gli attributi
     * @param larghezza
     * @param profondita
     * @param altezzaSx
     * @param altezzaDx
     * @param altezzaSopra
     * @param altezzaSotto
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
     * @brief get altezza in base alla direzione.
     *
     * Restituisce l'altezza della vasca nel lato ricevuto
     * @param direzione direzione scelta
     * @return altezza corrispondente
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
     * @brief get larghezza.
     * @return larghezza
     * 
     * @author Giacomo Orsenigo
     */
    public int getLarghezza() {
        return larghezza;
    }
    /**
     * @brief get profondità.
     * @return profondita
     * 
     * @author Giacomo Orsenigo
     */
    public int getProfondita() {
        return profondita;
    }
    /**
     * @brief get altezza sinistra.
     * @return altezzaSx
     * 
     * @author Giacomo Orsenigo
     */
    public int getAltezzaSx() {//RM-MIO
        return altezzaSx;
    }
    /**
     * @brief get altezza destra.
     * @return altezzaDx
     * 
     * @author Giacomo Orsenigo
     */
    public int getAltezzaDx() {//RM-MIO
        return altezzaDx;
    }
    /**
     * @brief get altezza superiore.
     * @return altezzaSopra
     * 
     * @author Giacomo Orsenigo
     */
    public int getAltezzaSopra() {//RM-MIO
        return altezzaSopra;
    }
    /**
     * @brief get alteza inferiore.
     * @return altezzaSotto
     * 
     * @author Giacomo Orsenigo
     */
    public int getAltezzaSotto() {
        return altezzaSotto;
    }
}
