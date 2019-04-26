/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

import java.awt.Point;
import javafx.scene.shape.Box;
import processing.core.PApplet;

/**
 *
 * @author Saccani Federico
 */
public class CSabbia {
    private float movimentoX;//Capisco se la sponda maggiore e' a DX o a SX
    private float altezzaLatoMaggiore;//In cm identifica a che livello arriva la sabbia sulla sponda maggiore
    private float altezzaLatoMinore;//In cm identifica a che livello arriva la sabbia sulla sponda minore
    //private float movimentoY;
    private float quantitaSabbia;//QUANTITA MAX 100 litri di sabbia
    private float velocita;
    
    public CSabbia(){
        this.quantitaSabbia=10;
        setup();
    }
    
    public CSabbia(float quantita){
        this.quantitaSabbia=quantita;
        setup();
    }
    
    private void setup(){
        movimentoX=0;
        //movimentoY=0;
        velocita=0;
    }
    
    public void aggiungiSabbia(float aggiunta){
        quantitaSabbia+=aggiunta;
    }
    
    public void rimuoviSabbia(float rimossa){
        quantitaSabbia-=rimossa;
    }
    
    
    public void aggiornati(float inclinazioneX, Box dimensioni){
        impostaMovimentoX(inclinazioneX);
        
        calcolaLivelloDiRaggiungimentoMaggiore(inclinazioneX, dimensioni);
    }
    
//    private void calcolaLivelloDiRaggiungimentoMaggiore(float inclinazioneX){
//        //CALCOLO IL LIVELLO MASSIMO DI RAGGIUNGIMENTO DELLA SABBIA SULLA SPONDA VERSO LA QUALE E' ORIENTATA (la sabbia)
//        float valoreVirgola = inclinazioneX/100; //10 gradi ---> 0,1
//        float percentualeSabbiaMovente = quantitaSabbia*valoreVirgola;//La sabbia che si muove in base all'inclinazione
//        
//        //livello massimo a cui la sabbia arriva con l'inclinazione
//        //maggiore sara' il livello, e maggiore sara' l'altezza registrata
//        altezzaLatoMaggiore = (quantitaSabbia+percentualeSabbiaMovente/2); //diviso 2 una parte sale e una scende \ oppure /
//    }
    
    private void calcolaLivelloDiRaggiungimentoMaggiore(float inclinazioneX,Box dimensioni){
        inclinazioneX=Math.abs(inclinazioneX);//La rendo sempre positiva
        
        double altezzaScatola = dimensioni.getHeight();
        double lato = dimensioni.getDepth();
        double VolumeIniziale =  ((quantitaSabbia*lato)*lato);
        
        
        double angoloAdiacente = 180-(90+inclinazioneX);
        double risCosAngA=  Math.cos(trasformaARadianti(angoloAdiacente))*altezzaScatola;
        double risSinAngA=  Math.sin(trasformaARadianti(angoloAdiacente))*altezzaScatola;
        
        double angoloSuperioreSX= Math.abs((180-(angoloAdiacente+90))-90);
        double angoloSuperioreDX= 180-(angoloSuperioreSX+90);
        
        double lunghezzaDiagonale = lato/(Math.cos(trasformaARadianti(angoloSuperioreDX)));
        
        
        double latoTriangolo =  (Math.sin(trasformaARadianti(angoloSuperioreDX))*lunghezzaDiagonale);
        double volumeTriangolo = ((lato*latoTriangolo)/2)*lato;
        

        double altezzaRett = -((volumeTriangolo-VolumeIniziale)/(lato*lato));
        double volumeRettangolo = (altezzaRett*lato)*lato;
        
        double volumeTrapezio = volumeRettangolo+volumeTriangolo;
        

        altezzaLatoMaggiore= (float)( altezzaRett+latoTriangolo);
        altezzaLatoMinore=(float) altezzaRett;
    }
    
    private double trasformaARadianti(double gradi){
        //180:gradi=pi:rad
        return ((gradi*Math.PI)/180);
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
        return quantitaSabbia;
    }
    public float getVelocita() {
        return velocita;
    }


    public float getAltezzaLatoMaggiore() {
        return altezzaLatoMaggiore;
    }
    public float getAltezzaLatoMinore() {
        return altezzaLatoMinore;
    }
}
