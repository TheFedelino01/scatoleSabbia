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
    
    /**
     * @brief Costruttore 
     * @param ptrDati viene passsato il puntatore alla classe dati condivisi mediante costruttore
     * @author Peduzzi Samuele
     * @version 1.0
     */
    
    public ThPallina(DatiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
    }
    
    /**
     * @brief Costruttore 
     * @param ptrDati viene passsato il puntatore alla classe dati condivisi mediante costruttore
     * @param idPallina identificatore pallina
     * @author Peduzzi Samuele
     * @version 1.0
     */
    
    public ThPallina(DatiCondivisi ptrDati, int idPallina) {
        this.ptrDati = ptrDati;
        this.idPallina = idPallina;
    }


    @Override
    
    /**
     * @brief run threasd
     * @author Peduzzi Samuele
     * @version 1.0
     */
    
    public void run() {
        int r = 0, c = 0;
        Scatola scatola = ptrDati.getScatola(r, c); //La pallina inizialmente è nella prima scatola

        while (!isInterrupted()) {

            scatola.aggiornaPosPallina(ptrDati.getInclinazioneTavoloDiGiocoX(),ptrDati.getInclinazioneTavoloDiGiocoY()); //viene aggiornata la posizione della pallina
            if (scatola.getPallina().isPresente()) {
                final Directions dirPallina = scatola.isPallinaControBordi();
                if (dirPallina != Directions.NONE) {
                    System.out.println(dirPallina);
//                    final Scatola s = ptrDati.getScatolaAdiacente(r, c, dirPallina);
                    final Scatola s = ptrDati.getScatola(0, 1);
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
            
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThPallina.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }


}
