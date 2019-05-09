/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

import java.awt.*;

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
     * @param ptrDati   dati condivisi
     * @param scatola scatola
     * @brief costruttore
     * <p>
     * Inizializza gli attributi
     */
    public ThScatola(DatiCondivisi ptrDati, int r, int c) {
        this.ptrDati = ptrDati;
        this.scatola = ptrDati.getScatola(r, c);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            scatola.muovi();
            int altezzaLatoUscita = scatola.getDimensioni().getAltezza(Directions.fromInclinazioneX(ptrDati.getDimensioneSchermoX()));
            final Directions direzioneUscita = scatola.getSabbiaPresente().direzioneDiUscitaSabbia(altezzaLatoUscita);

            if (direzioneUscita != Directions.NONE) {
                System.out.println("LA SABBIA ESCE!!");
                final Scatola ricevente = ptrDati.getScatolaAdiacente(scatola.getPosizione().y, scatola.getPosizione().x, direzioneUscita);
                scatola.spostaSabbia(1, ricevente);
                if (scatola.getPallina().isPresente()) {
                    final Directions dirPallina = scatola.isPallinaControBordi();
                    if (dirPallina != Directions.NONE) {
                        final Scatola s = ptrDati.getScatolaAdiacente(scatola.getPosizione().y, scatola.getPosizione().x, dirPallina);
                        scatola.spostaPallina(s);
                        Point nuovaPos = s.getPallina().getPosizione();
                        switch (dirPallina) {
                            case SOPRA:
                            case SOTTO:
                                nuovaPos.y = -nuovaPos.y;
                                break;
                            case SINISTRA:
                            case DESTRA:
                                nuovaPos.x = -nuovaPos.x;
                                break;
                        }
                        s.getPallina().sposta(nuovaPos);
                    }
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
