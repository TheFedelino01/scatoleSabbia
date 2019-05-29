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
 * @brief Classe che rappresenta una scatola.
 * può contentere sabbia ({@link CSabbia}) e palline ({@link JPallina})
 */
public class Scatola {
    /**
     * @brief Puntatore all'istanza di processing per usare le librerie grafiche.
     * @author Giacomo Orsenigo
     */
    private final PApplet processingSketch;


    /**
     * @brief dimensioni della scatola.
     * @see DimensioniScatola
     * @author Giacomo Orsenigo
     */
    private DimensioniScatola dimensioni; //misurata in cm

    /**
     * @brief posizione della scatola nel piano.
     * @author Giacomo Orsenigo
     */
    private Point posizione;

    /**
     * @brief posizione nella matrice.     *
     * Valori X,Y della scatola all'interno della matrice {@link DatiCondivisi#scatole}
     * @author Giacomo Orsenigo
     */
    private Point posMatrice;

    /**
     * @brief sabbia presente nella scatola.
     * @see CSabbia
     * @author Giacomo Orsenigo
     */
    private CSabbia sabbiaPresente;

    /**
     * @brief Lista contenente le palline presente nella scatola (se presenti).
     * @author Giacomo Orsenigo
     */
    private List<JPallina> palline;

    /**
     * @brief costruttore.
     * Inizializza tutti gli attributi richiamando il costruttore {@link #Scatola(PApplet, DimensioniScatola, Point, Point, CSabbia, JPallina)}
     * @param processingSketch istanza di processing
     * @param posMatrice        posizione all'interno della matrice
     * @param posizione        posizione della scatola
     * @param quanita           quantità di sabbia presente
     *
     * @author Giacomo Orsenigo
     */
    public Scatola(PApplet processingSketch, Point posMatrice,Point posizione, int quanita) {
        this(processingSketch, new DimensioniScatola(),posMatrice, posizione, new CSabbia(quanita, processingSketch), null);
    }


    /**
     * @brief costruttore.
     * Inizializza tutti gli attributi in base ai parametri
     * @param processingSketch istanza di processing
     * @param dimensioni       dimensione della scatola
     * @param posMatrice        posizione all'interno della matrice
     * @param posizione        posizione della scatola
     * @param sabbiaPresente   sabbia presente nella scatola
     * @param pallina          pallina presente nella scatola
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
     * @brief muove la scatola.
     * Metodo che permette di simulare un movimento  della scatola
     * Richiama {@link #aggiornaDistribuzioneVelocitaSabbia(float, float)} e {@link #aggiornaPosPallina(float, float, int)}
     * @param inclinazioneX inclinazione X
     * @param inclinazioneY inclinazione Y
     * @param idPallina id della pallina
     * @author Giacomo Orsenigo
     */
    public void muovi(float inclinazioneX, float inclinazioneY, int idPallina) {
        aggiornaDistribuzioneVelocitaSabbia(inclinazioneX, inclinazioneY);
        aggiornaPosPallina(inclinazioneX, inclinazioneY, idPallina);
    }



    /**
     * @brief aggiorna inclinazione della sabbia.
     * Metodo che permette di aggiornare l'altezza e la velocita della sabbia nella parte destra e sinistra della scatola in base ai gradi di inclinazione della scatola
     * Richiama {@link CSabbia#aggiornati(float, DimensioniScatola)}
     * @param inclinazioneX inclinazione X
     * @param inclinazioneY inclinazione Y
     * @author Giacomo Orsenigo
     */
    public void aggiornaDistribuzioneVelocitaSabbia(float inclinazioneX, float inclinazioneY) {
        sabbiaPresente.aggiornati(inclinazioneX, dimensioni);
    }

    /**
     * @brief spostaX la sabbia in un'altra scatola.
     * Modifica {@link #sabbiaPresente}
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
     * @brief spostaX la pallina in un'altra scatola.
     * Modifica la lista {@link #palline}
     * @param id id della pallina da spostare
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
     * @brief get sabbia presente.
     * @return sabbia presente nella scatola
     * @author Giacomo Orsenigo
     */
    public CSabbia getSabbiaPresente() {
        return sabbiaPresente;
    }

    /**
     * @brief get dimensioni.
     * @return dimensioni della scatola
     * @author Giacomo Orsenigo
     */
    public DimensioniScatola getDimensioni() {
        return dimensioni;
    }

    /**
     * @brief aggiunge una pallina a questa scatola.
     * @param pallina da aggiungere
     * @author Giacomo Orsenigo
     */
    public void addPallina(JPallina pallina) {
        this.palline.add(pallina);
    }

    /**
     * @brief get palline contenute in questa scatola.     *
     * @return lista di palline
     * @author Giacomo Orsenigo
     */
    public List<JPallina> getPalline() {
        return palline;
    }

    /**
     * @brief get posizione.     *
     * @return posizione
     * @author Giacomo Orsenigo
     */
    public Point getPosizione() {
        return posizione;
    }

    /**
     * @brief get posizione nella matrice.
     * @return posizione nella matrice
     * @author Giacomo Orsenigo
     */
    public Point getPosMatrice() {
        return posMatrice;
    }


    /**
     * @brief disegna la scatola.
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


        processingSketch.noFill();
        processingSketch.stroke(0, 0, 0);
        processingSketch.rect(posizione.x, posizione.y, (float) dimensioni.getLarghezza(), (float) dimensioni.getProfondita());

        sabbiaPresente.visualizza(this, true);

        for (JPallina p : palline)
            p.draw();
    }


    //////

    /**
     * @brief controlla se la palline è contro i bordi.
     * @deprecated Usare {@link #isPallinaControBordiX(int)} e {@link #isPallinaControBordiY(int)}
     * @bug Se la pallina è appoggiata a due bordi ne ritorna uno solo
     * @param id id della pallina
     * @return direzione del bordo con la pallina appoggiata
     * @author Giacomo Orsenigo
     */
    public Directions isPallinaControBordi(int id) {
        JPallina p = findPallina(id);
        if (p.getPosizione().x + p.getDimensioni() >= dimensioni.getLarghezza())
            return Directions.DESTRA;
        else if (p.getPosizione().x - p.getDimensioni() <= 0)
            return Directions.SINISTRA;
        else if (p.getPosizione().y- p.getDimensioni()  <= 0)
            return Directions.SOPRA;
        else if (p.getPosizione().y + p.getDimensioni() >= dimensioni.getProfondita())
            return Directions.SOTTO;
        return Directions.NONE;
    }


    /**
     * @brief controlla se una pallina è contro i bordi destro o sinistro.
     * @param id id della pallina
     * @return direzione del bordo con la palline appoggiata
     * @author Giacomo Orsenigo
     */
    public Directions isPallinaControBordiX(int id) {
        JPallina p = findPallina(id);
        if (p.getPosizione().x + p.getDimensioni() >= dimensioni.getLarghezza())
            return Directions.DESTRA;
        else if (p.getPosizione().x - p.getDimensioni() <= 0)
            return Directions.SINISTRA;
        return Directions.NONE;
    }

    /**
     * @brief controlla se una pallina è contro i bordi superiore o inferiore.
     * @param id id della pallina
     * @return direzione del bordo con la palline appoggiata
     * @author Giacomo Orsenigo
     */
    public Directions isPallinaControBordiY(int id) {
        JPallina p = findPallina(id);
        if (p.getPosizione().y- p.getDimensioni()  <= 0)
            return Directions.SOPRA;
        else if (p.getPosizione().y + p.getDimensioni() >= dimensioni.getProfondita())
            return Directions.SOTTO;
        return Directions.NONE;
    }

    /**
     * @brief trova una pallina tra quelle presenti nella scatola.
     * @param id id della pallina da cercare
     * @return pallina se presente
     * @throws IllegalArgumentException se la pallina non è presente
     * @author Giacomo Orsenigo
     */
    public JPallina findPallina(int id) {
        for (JPallina p : palline)
            if (p.getId() == id)
                return p;
        throw new IllegalArgumentException("Pallina non presente");
    }


    /**
     * @brief aggiorna posizione palline.
     * Metodo che permette di aggiornare la posizione della palline all'interno della scatola a seconda della velocita' della sabbia
     * Richiama {@link JPallina#spostaX(float)} e {@link JPallina#spostaY(float)}
     * @param inclinazioneX inclinazione X
     * @param inclinazioneY inclinazione Y
     * @param idPallina id della pallina
     * @author Giacomo Orsenigo
     */
    public void aggiornaPosPallina(float inclinazioneX, float inclinazioneY, int idPallina) {
        JPallina toMove = findPallina(idPallina);

        if (inclinazioneX > 0 && isPallinaControBordiX(idPallina) == Directions.SINISTRA)
            return;
        if (inclinazioneX < 0 && isPallinaControBordiX(idPallina) == Directions.DESTRA)
            return;

        toMove.spostaX(inclinazioneX);

        if (inclinazioneY < 0 && isPallinaControBordiY(idPallina) == Directions.SOPRA)
            return;
        if (inclinazioneY > 0 && isPallinaControBordiY(idPallina) == Directions.SOTTO)
            return;

        toMove.spostaY(inclinazioneY);
    }
}
