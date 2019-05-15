/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

import java.awt.Point;

/**
 * @author peduzzi_samuele
 */
public class ThPallina extends Thread {


    private DatiCondivisi ptrDati;
    private int idPallina;

    public ThPallina(DatiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
    }

    public ThPallina(DatiCondivisi ptrDati, int idPallina) {
        this.ptrDati = ptrDati;
        this.idPallina = idPallina;
    }


    @Override
    public void run() {
        int r = 0, c = 0;
        Scatola scatola = ptrDati.getScatola(r, c);

        while (!isInterrupted()) {

            scatola.aggiornaPosPallina(ptrDati.getInclinazioneTavoloDiGiocoX(),ptrDati.getInclinazioneTavoloDiGiocoY());
            if (scatola.getPallina().isPresente()) {
                final Directions dirPallina = scatola.isPallinaControBordi();
                if (dirPallina != Directions.NONE) {
                    final Scatola s = ptrDati.getScatolaAdiacente(r, c, dirPallina);
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

    }


}
