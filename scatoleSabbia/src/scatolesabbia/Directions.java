package scatolesabbia;

/**
 * @brief Rappresenta una direzione.
 * Utilizzata dalla pallina ({@link JPallina}) e dalla sabbia ({@link CSabbia})
 *
 * @author Giacomo Orsenigo
 */
public enum Directions {

    /**
     * @breif valori che può assumere la direzione della sabbia o della pallina.
     * @author Giacomo Orsenigo
     */
    SOPRA, SOTTO, DESTRA, SINISTRA, NONE;

    /**
     * @brief restituisce una direzione in base all'inclinazione.
     * @param inclinazioneX inclinazione sull'asse X
     * @return {@link #SINISTRA} se l'inclinazione è maggiore di 0. {@link #DESTRA} se è minore
     * @author Giacomo Orsenigo
     */
    public static Directions fromInclinazioneX(float inclinazioneX) {
        if (inclinazioneX == 0)
            return Directions.NONE;
        else if (inclinazioneX > 0)
            return SINISTRA;
        else
            return DESTRA;
    }

    /**
     * @brief restituisce una direzione in base all'inclinazione.
     * @param inclinazioneY inclinazione sull'asse Y
     * @return {@link #SOTTO} se l'inclinazione è maggiore di 0. {@link #SOPRA} se è minore
     * @author Giacomo Orsenigo
     */
    public static Directions fromInclinazioneY(float inclinazioneY) {
        if (inclinazioneY == 0)
            return Directions.NONE;
        else if (inclinazioneY > 0)
            return SOTTO;
        else
            return SOPRA;
    }
}
