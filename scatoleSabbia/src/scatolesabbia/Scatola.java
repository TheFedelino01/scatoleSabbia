/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;


import processing.core.PApplet;

import java.awt.Point;
import java.util.List;
import java.util.Vector;


/**
 * @author Giacomo Orsenigo
 * @brief Classe che rappresenta una scatola, che può contentere sabbia e palline
 */
public class Scatola {
    /**
     * @brief Puntatore all'istanza di processing per usare le librerie grafiche
     * @author Giacomo Orsenigo
     */
    private final PApplet processingSketch;


    /**
     * @brief dimensioni della scatola
     * @author Giacomo Orsenigo
     */
    private DimensioniScatola dimensioni; //misurata in cm

    /**
     * @brief posizione della scatola nel piano
     * @author Giacomo Orsenigo
     */
    private Point posizione;

    private Point posMatrice;

    /**
     * @brief sabbia presente nella scatola
     * @author Giacomo Orsenigo
     */
    private CSabbia sabbiaPresente;

    /**
     * @brief palline presente nella scatola (se presenti)
     * @author Giacomo Orsenigo
     */
    private List<JPallina> palline;

    /**
     * @param processingSketch istanza di processing
     * @param posizione        posizione della scatola
     * @brief costruttore
     * <p>
     * Inizializza tutti gli attributi richiamando il costruttore {@link #Scatola(PApplet, DimensioniScatola, Point, CSabbia, JPallina)}
     * @author Giacomo Orsenigo
     */
    public Scatola(PApplet processingSketch, Point posMatrice,Point posizione, int quanita) {
        this(processingSketch, new DimensioniScatola(),posMatrice, posizione, new CSabbia(quanita, processingSketch), null);
    }


    /**
     * @param processingSketch istanza di processing
     * @param dimensioni       dimensione della scatola
     * @param posizione        posizione della scatola
     * @param sabbiaPresente   sabbia presente nella scatola
     * @param pallina          pallina
     * @brief costruttore
     * <p>
     * Inizializza tutti gli attributi in base ai parametri
     * @author Giacomo Orsenigo
     */
    public Scatola(PApplet processingSketch, DimensioniScatola dimensioni, Point posMatrice,Point posizione, CSabbia sabbiaPresente, JPallina pallina) {
        this.processingSketch = processingSketch;
        this.dimensioni = dimensioni;
        this.posMatrice=posMatrice;
        this.posizione = posizione;
        this.sabbiaPresente = sabbiaPresente;
        this.palline = new Vector<>();
        if (pallina != null)
            palline.add(pallina);
//        palline.mostraPallina();
    }


    /**
     * @param inclinazioneX inclinazione X
     * @param inclinazioneY inclinazione Y
     * @brief muove la scatola
     * <p>
     * Metodo che permette di simulare un movimento  della scatola richiamando aggiornamento Sabbia, poi aggiornamento Pallina e successivamento visualizzazione scatola
     * Richiama {@link #aggiornaDistribuzioneVelocitaSabbia(float, float)} e {@link #aggiornaPosPallina(float, float, int)}
     * @author Giacomo Orsenigo
     */
    public void muovi(float inclinazioneX, float inclinazioneY, int idPallina) {
        aggiornaDistribuzioneVelocitaSabbia(inclinazioneX, inclinazioneY);
        aggiornaPosPallina(inclinazioneX, inclinazioneY, idPallina);
    }

    /**
     * @param inclinazioneX inclinazione X
     * @param inclinazioneY inclinazione Y
     * @brief aggiorna posizione palline
     * <p>
     * Metodo che permette di aggiornare la posizione della palline all'interno della scatola a seconda della velocita' della sabbia
     * Richiama {@link JPallina#spostaX(float)} e {@link JPallina#spostaY(float)}
     * @author Giacomo Orsenigo
     */
    public void aggiornaPosPallina(float inclinazioneX, float inclinazioneY, int idPallina) {
        JPallina toMove = findPallina(idPallina);

        if (inclinazioneX < 0 && isPallinaControBordi(idPallina) == Directions.SINISTRA)
            return;
        if (inclinazioneX > 0 && isPallinaControBordi(idPallina) == Directions.DESTRA)
            return;

        toMove.spostaX(inclinazioneX);

        if (inclinazioneY < 0 && isPallinaControBordi(idPallina) == Directions.SOPRA)
            return;
        if (inclinazioneY > 0 && isPallinaControBordi(idPallina) == Directions.SOTTO)
            return;

        toMove.spostaY(inclinazioneY);
    }

    /**
     * @param inclinazioneX inclinazione X
     * @param inclinazioneY inclinazione Y
     * @brief aggiorna inclinazione della sabbia
     * <p>
     * Metodo che permette di aggiornare l'altezza e la velocita della sabbia nella parte destra e sinistra della scatola in base ai gradi di inclinazione della scatola
     * Richiama {@link CSabbia#aggiornati(float, DimensioniScatola)}
     * @author Giacomo Orsenigo
     */
    public void aggiornaDistribuzioneVelocitaSabbia(float inclinazioneX, float inclinazioneY) {
        sabbiaPresente.aggiornati(inclinazioneX, dimensioni);
    }

    /**
     * spostaX la sabbia in un'altra scatola
     *
     * @param altezza sabbia da spostare
     * @param altra   scatola in cui spostare la sabbia
     * @author Giacomo Orsenigo
     */
    public void spostaSabbia(float altezza, Scatola altra) {

        //NON SPOSTO LA SABBIA SE NON CE N'E'!!!!

        //DA CONTROLLARE PERCHE' DOPO UN CERTO PUNTO, VA IN LOOP
        //E CONTINUA A RICHIAMARE QUESTO METODO E CONTINUA A DIRE CHE NON PUO'
        //SPOSTARLA... (LA COSA E' CORRETTA MA NON CAPISCO PERCHE' CONTINUA A
        //RICHIAMARLO)...
        if (sabbiaPresente.getQuantita() - altezza < 0) {
            System.out.println("Dovrei spostare la sabbia ma non ce n'e'");
        } else {
            System.out.println("Sposto la sabbia...");
            sabbiaPresente.rimuoviSabbia(altezza);
            altra.sabbiaPresente.aggiungiSabbia(altezza);
        }

    }

    /**
     * spostaX la palline in un'altra scatola
     *
     * @param altra scatola in cui spostare la palline
     * @author Giacomo Orsenigo
     */
    public void spostaPallina(int id, Scatola altra) {
        JPallina toMove = this.findPallina(id);
        toMove.setScatola(altra);
        palline.remove(toMove);
        altra.addPallina(toMove);
        //altra.palline.mostraPallina();
    }

    /**
     * @return sabbia presente nella scatola
     * @brief get sabbia presente
     * @author Giacomo Orsenigo
     */
    public CSabbia getSabbiaPresente() {
        return sabbiaPresente;
    }

    /**
     * @return dimensioni della scatola
     * @brief get dimensioni
     * @author Giacomo Orsenigo
     */
    public DimensioniScatola getDimensioni() {
        return dimensioni;
    }

    public void addPallina(JPallina pallina) {
        this.palline.add(pallina);
    }

    public List<JPallina> getPalline() {
        return palline;
    }

    /**
     * get posizione centrale
     *
     * @return posizione
     * @author Giacomo Orsenigo
     */
    public Point getPosizioneCentrale() {
        return posizione;
    }

    /**
     * @brief disegna la scatola
     * <p>
     * Disegna la scatola con tutto il contenuto
     * Richiama {@link CSabbia#visualizza(Scatola, boolean)} e {@link JPallina#draw()}
     * @author Giacomo Orsenigo
     */
    public void draw() {
        processingSketch.fill(processingSketch.color(255, 255, 255));
        processingSketch.rect(posizione.x, posizione.y, (float) dimensioni.getLarghezza(), (float) dimensioni.getProfondita());

//        if (palline.isPresente()) {
//            processingSketch.fill(palline.getColore().getRGB());
//            processingSketch.ellipse(palline.getPosizione().x + posizione.x, palline.getPosizione().y + posizione.y, palline.getDimensioni(), palline.getDimensioni());
//        }

        for (JPallina p : palline)
            p.draw();

        processingSketch.noFill();
        processingSketch.stroke(0, 0, 0);
        processingSketch.rect(posizione.x, posizione.y, (float) dimensioni.getLarghezza(), (float) dimensioni.getProfondita());

        sabbiaPresente.visualizza(this, true);
    }

    /**
     * @return direzione del bordo con la palline appoggiata
     * @brief controlla se la palline è contro i bordi
     * @author Giacomo Orsenigo
     */
    public Directions isPallinaControBordi(int id) {
        JPallina p = findPallina(id);
        if (p.getPosizione().x > dimensioni.getLarghezza())
            return Directions.DESTRA;
        else if (p.getPosizione().x < 0)
            return Directions.SINISTRA;
        else if (p.getPosizione().y < 0)
            return Directions.SOPRA;
        else if (p.getPosizione().y > dimensioni.getProfondita())
            return Directions.SOTTO;
        return Directions.NONE;
    }

    /**
     * get posizione
     *
     * @return posizione
     * @author Giacomo Orsenigo
     */
    public Point getPosizione() {
        return posizione;
    }

    public Point getPosMatrice() {
        return posMatrice;
    }

    /**
     * @param id id pallina da cercare
     * @return pallina se presente
     * @throws IllegalArgumentException se la pallina non è presente
     * @brief trova una pallina tra quelle presenti nella scatola
     */
    public JPallina findPallina(int id) {
        for (JPallina p : palline)
            if (p.getId() == id)
                return p;
        throw new IllegalArgumentException("Pallina non presente");
    }
}
