/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

import java.awt.Point;
import processing.core.PApplet;

/**
 *
 * @author Saccani Federico
 */
public class CSabbia {
    private float movimentoX;//Capisco se la sponda maggiore e' a DX o a SX
    private float altezzaLatoMaggiore;//In cm identifica a che livello arriva la sabbia sulla sponda maggiore
    //private float movimentoY;
    private float quantita;//QUANTITA MAX 100 litri di sabbia ogni litro corrisponde a 1 cm
    private float velocita;
    
    public CSabbia(){
        this.quantita=0;
        setup();
    }
    
    public CSabbia(float quantita){
        this.quantita=quantita;
        setup();
    }
    
    private void setup(){
        movimentoX=0;
        //movimentoY=0;
        velocita=0;
    }
    
    public void aggiungiSabbia(float aggiunta){
        quantita+=aggiunta;
    }
    
    public void rimuoviSabbia(float rimossa){
        quantita-=rimossa;
    }
    
    //http://spartanita.altervista.org/download/manuali/livello.pdf
    public void aggiornati(float inclinazioneX){
        impostaMovimentoX(inclinazioneX);
        
        calcolaLivelloDiRaggiungimentoMaggiore(inclinazioneX);
    }
    
    private void calcolaLivelloDiRaggiungimentoMaggiore(float inclinazioneX){
        //CALCOLO IL LIVELLO MASSIMO DI RAGGIUNGIMENTO DELLA SABBIA SULLA SPONDA VERSO LA QUALE E' ORIENTATA (la sabbia)
        float valoreVirgola = inclinazioneX/100; //10 gradi ---> 0,1
        float percentualeSabbiaMovente = quantita*valoreVirgola;//La sabbia che si muove in base all'inclinazione
        
        //livello massimo a cui la sabbia arriva con l'inclinazione
        //maggiore sara' il livello, e maggiore sara' l'altezza registrata
        altezzaLatoMaggiore = (quantita+percentualeSabbiaMovente/2); //diviso 2 una parte sale e una scende \ oppure /
    }
    
    private void impostaMovimentoX(float inclinazioneX){
        //IMPOSTO IL VERSO DI MOVIMENTO/ORIENTAMENTO DELLA SABBIA (VERSO DX O VERSO SX)
        
        //Se l'inclinazione e' positiva, la sabbia si muove verso SX quindi il livello maggiore
        //registrato sara' sulla sponda SX della scatola
        if(inclinazioneX>0){
            movimentoX=1;
        }else{
           //Se l'inclinazione e' negativa, la sabbia si muove verso DX quindi il livello maggiore
            //registrato sara' sulla sponda DX della scatola
            movimentoX=-1; 
        }
    }
    
    /*True: esce della sabbia  | False: la sabbia non ha un inclinazione tale per uscire*/
    private boolean staUscendo(int altezzaDaSuperare){
        if(altezzaLatoMaggiore>altezzaDaSuperare){
            //Significa che la sabbia sta' uscendo
            return true;
        }else{
            return false;//La sabbia ha un altezza inferiore e quindi non esce dalla scatola
        }
        
    }
    
    public Directions direzioneDiUscitaSabbia(int altezzaDaSuperare){
        Directions direzione = Directions.NONE;//Parto dal presupposto che la sabbia non sta uscendo
        
        //Controllo se effettivamente sta uscendo la sabbia
        if(staUscendo(altezzaDaSuperare)){
            
            //Sta uscendo, dico da dove esce
            if(movimentoX==1){
                direzione = Directions.SINISTRA;
            }else{
                //Movimento = -1
                direzione = Directions.DESTRA;
            }
            
        }else{
            //Non sta uscendo
            direzione = Directions.NONE;
        }
        
        return direzione;
    }
    
    public void visualizza(Point centroScatola){
        //TODO 
        
    }
    
    //GET e SET DELLA CLASSE
    public float getMovimentoX() {
        return movimentoX;
    }
    public float getQuantita() {
        return quantita;
    }
    public float getVelocita() {
        return velocita;
    }
}
