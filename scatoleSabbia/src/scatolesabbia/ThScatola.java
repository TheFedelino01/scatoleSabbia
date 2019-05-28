package scatolesabbia;

/**
 * @author Giacomo Orsenigo
 * @brief Thread che gestisce la fisica del gioco
 */
public class ThScatola extends Thread {

    /**
     * @brief puntatore ai dati condivisi
     */
    private final DatiCondivisi ptrDati;

    /**
     * @brief id della scatola
     */
    private final Scatola scatola;

    /**
     * @param ptrDati dati condivisi
     * @param s scatola
     * @brief costruttore
     * <p>
     * Inizializza gli attributi
     */
    public ThScatola(DatiCondivisi ptrDati, Scatola s) {
        this.ptrDati = ptrDati;
        this.scatola = s;
    }

    /**
     * @brief run
     *
     * Il thread aggiorna continuamente gli stati delle scatole in base all'inclinazione
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