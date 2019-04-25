/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

/**
 * @author giaco
 */
public class ThScatola extends Thread {

    private final DatiCondivisi ptrDati;
    private final int idScatola;

    public ThScatola(DatiCondivisi ptrDati, int idScatola) {
        this.ptrDati = ptrDati;
        this.idScatola = idScatola;
    }

    @Override
    public void run() {
//        final Scatola scatola = ptrDati.getScatola(idScatola);
//
//        while (!isInterrupted()) {
//            scatola.muovi();
//            final Directions direzioneUscita = scatola.getSabbiaPresente().direzioneDiUscitaSabbia((int) scatola.getDimensioni().getHeight());
//            
//            if (direzioneUscita!=Directions.NONE) {
//                final Scatola ricevente = ptrDati.getScatolaAdiacente(idScatola, direzioneUscita);
//                scatola.spostaSabbia(1, ricevente);
//            }
//        }
    }
}
