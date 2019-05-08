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
     */
    public static Directions fromInclinazioneX(float inclinazioneX) {
        if (inclinazioneX > 0)
            return SINISTRA;
        else
            return DESTRA;
    }
}
