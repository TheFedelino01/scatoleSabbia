package scatolesabbia;

/**
 * @brief Thread che gestisce il movimento della sabbia di ogni scatola.
 * @author Giacomo Orsenigo
 */
public class ThScatola extends Thread {

    /**
     * @brief puntatore ai dati condivisi.
     * @author Giacomo Orsenigo
     */
    private final DatiCondivisi ptrDati;

    /**
     * @brief scatola.
     * @author Giacomo Orsenigo
     */
    private final Scatola scatola;

    /**
     * @brief costruttore.
     * Inizializza gli attributi
     * @param ptrDati dati condivisi
     * @param s scatola
     * @author Giacomo Orsenigo
     */
    public ThScatola(DatiCondivisi ptrDati, Scatola s) {
        this.ptrDati = ptrDati;
        this.scatola = s;
    }

    /**
     * @brief run.
     *
     * Il thread aggiorna continuamente gli stati delle scatole in base all'inclinazione
     * Se la sabbia esce dalla scatola la sposta in quella adiacente
     * @see Scatola#aggiornaDistribuzioneVelocitaSabbia(float, float)
     * @see CSabbia#direzioneDiUscitaSabbia(int)
     * @see DatiCondivisi#getScatolaAdiacente(int, int, Directions)
     * @see Scatola#spostaSabbia(float, Scatola)
     * @author Giacomo Orsenigo
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            //scatola.muovi(ptrDati.getInclinazioneTavoloDiGiocoX(), ptrDati.getInclinazioneTavoloDiGiocoY());
            scatola.aggiornaDistribuzioneVelocitaSabbia(ptrDati.getInclinazioneTavoloDiGiocoX(), ptrDati.getInclinazioneTavoloDiGiocoY());
            
            //prendo l'altezza del lato dove c'e' il latoMaggiore della sabbia
            int altezzaLatoUscita = scatola.getDimensioni().getAltezza(Directions.fromInclinazioneX(ptrDati.getDimensioneSchermoX()));
            
            final Directions direzioneUscita = scatola.getSabbiaPresente().direzioneDiUscitaSabbia(altezzaLatoUscita);

            if (direzioneUscita != Directions.NONE) {
                //Sta uscendo
                final Scatola ricevente = ptrDati.getScatolaAdiacente(scatola.getPosMatrice().y, scatola.getPosMatrice().x, direzioneUscita);
                if (ricevente != null) {    //se la scatola esiste e non è vuoto
                    System.out.println("LA SABBIA ESCE!!");
                    scatola.spostaSabbia(1, ricevente);

                }
            }
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//                        if (scatola.getPalline().isPresente()) {
//                            final Directions dirPallina = scatola.isPallinaControBordi();
//                            if (dirPallina != Directions.NONE) {
//                                final Scatola s = ptrDati.getScatolaAdiacente(scatola.getPosizione().y, scatola.getPosizione().x, dirPallina);
//                                scatola.spostaPallina(s);
//                                Point nuovaPos = s.getPalline().getPosizione();
//                                switch (dirPallina) {
//                                    case SOPRA:
//                                    case SOTTO:
//                                        nuovaPos.y = -nuovaPos.y;
//                                        break;
//                                    case SINISTRA:
//                                    case DESTRA:
//                                        nuovaPos.x = -nuovaPos.x;
//                                        break;
//                                }
//                                s.getPalline().sposta(nuovaPos);
//                            }
//                        }