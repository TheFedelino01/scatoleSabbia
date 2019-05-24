package scatolesabbia;

/**
 * Rappresenta una direzione
 * Per indicare le scatole adiacenti
 *
 * @author Giacomo Orsenigo
 */
public enum Directions {
    SOPRA, SOTTO, DESTRA, SINISTRA, NONE;

    /**
     * @brief restituisce una direzione in base all'inlcinazione
     * @param inclinazioneX
     * @return
     * 
     * @author Giacomo Orsenigo
     */
    public static Directions fromInclinazioneX(float inclinazioneX) {
        if (inclinazioneX > 0)
            return DESTRA;
        else
            return SINISTRA;
    }

    /**
     * @brief restituisce una direzione in base all'inlcinazione
     * @param inclinazioneY
     * @return
     *
     * @author Giacomo Orsenigo
     */
    public static Directions fromInclinazioneY(float inclinazioneY) {
        if (inclinazioneY > 0)
            return SOPRA;
        else
            return SOTTO;
    }
}
