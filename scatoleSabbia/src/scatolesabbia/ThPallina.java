/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author peduzzi_samuele
 */
public class ThPallina extends Thread {


    /**
     * @brief Puntatore alla classe conenente i dati condivisi
     * @author Peduzzi Samuele
     * @version 1.0
     */

    private DatiCondivisi ptrDati;

    /**
     * @brief L'attributo rappresenta l'identificativo della pallina
     * @author Peduzzi Samuele
     * @version 1.0
     */

    private int idPallina;
    private JPallina pallina;

    /**
     * @param ptrDati viene passsato il puntatore alla classe dati condivisi mediante costruttore
     * @brief Costruttore
     * @author Peduzzi Samuele
     * @version 1.0
     */

    public ThPallina(DatiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
    }

    /**
     * @param ptrDati viene passsato il puntatore alla classe dati condivisi mediante costruttore
     * @param pallina pallina
     * @brief Costruttore
     * @author Peduzzi Samuele
     * @version 1.0
     */

    public ThPallina(DatiCondivisi ptrDati, JPallina pallina) {
        this.ptrDati = ptrDati;
        this.idPallina = pallina.getId();
        this.pallina = pallina;
    }


    @Override

    /**
     * @brief Thread incaricato della gestione di una pallina.
     * Il thread in questione varia costantemente la posizione sul piano della pallina basandosi sulle inclinazioni del piano di gioco.
     * Le inclinazioni vengono recuperate dai dati condivisi 
     * Inoltre ad ogni iterazione verifica se la pallina entra in contatto con uno dei bordi della scatola.
     * Basandosi sulla direzione recupera dai dati condivisi la scatola adiacente 
     * Sposta la pallina all'interno della scatola adiacente e ne aggiorna la posizione
     * @author Peduzzi Samuele
     * @version 1.0
     */

    public void run() {

        Scatola scatolaAttuale = pallina.getScatola(); //La scatola in cui è contenuta la pallina
        while (!isInterrupted()) {
            int incX = ptrDati.getInclinazioneTavoloDiGiocoX();
            int incY = ptrDati.getInclinazioneTavoloDiGiocoY();
            scatolaAttuale.aggiornaPosPallina(incX, incY, idPallina); //viene aggiornata la posizione della pallina
            if (pallina.isPresente()) {
                final Directions dirPallina = scatolaAttuale.isPallinaControBordi(idPallina);
                if (dirPallina != Directions.NONE && (dirPallina == Directions.fromInclinazioneX(incX)||dirPallina==Directions.fromInclinazioneY(incY))) {
                    System.out.println(dirPallina);
                    final Scatola ricevente = ptrDati.getScatolaAdiacente(scatolaAttuale, dirPallina);
                    //final Scatola s = ptrDati.getScatola(0, 1);
                    if (ricevente != null) {
                        scatolaAttuale.spostaPallina(idPallina, ricevente);
                        scatolaAttuale = ricevente;
                        Point nuovaPos = pallina.getPosizione();
                        switch (dirPallina) {
                            case SOPRA:
                            case SOTTO:
                                nuovaPos.y = scatolaAttuale.getDimensioni().getProfondita() - nuovaPos.y;
                                break;
                            case SINISTRA:
                            case DESTRA:
                                nuovaPos.x = scatolaAttuale.getDimensioni().getLarghezza() - nuovaPos.x;
                                break;
                        }
                        pallina.sposta(nuovaPos);
                    }
                }
            }

            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThPallina.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }


}
