/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deuxAscenseur;

/**
 *
 * @author hp
 */
import java.util.HashSet;

/**
 *
 * @author hp
 */
public class Ascenceur implements Runnable {
    int etageActu=0;
    int PlacesOccupees=0;
    int Capacite ;
    int nmbEtage;
    String status;
    IGAscenseur UG;
    public HashSet<Personne> infoPersonnes = new HashSet<Personne>();
    
    public Ascenceur(int capacite, int nmbEtage,IGAscenseur UG) {
        
        this.Capacite = capacite;
        this.nmbEtage = nmbEtage;
        this.UG=UG;
    }
    @Override
    public void run(){
             
        try{
        while(true){ 
            
        if(this.etageActu<=this.nmbEtage){
            if(this.etageActu==this.nmbEtage){
                Thread.sleep(5000);
                this.status="desent :";
            UG.setstatus(status);
                
            while(this.etageActu!=0){
             Thread.sleep(5000);
             System.out.println(this.etageActu);
             this.etageActu--;
             UG.setetage(String.valueOf(this.etageActu));
            }
            this.status="monte :";
            UG.setstatus(status);
            
               
            }
        
           Thread.sleep(5000);
           System.out.println(this.etageActu);
           this.etageActu++;
           UG.setetage(String.valueOf(this.etageActu));
        
        }
        
        }}catch(InterruptedException e){}
        
    
    
    }
    int places(){ return (this.Capacite - this.PlacesOccupees); } 
    
    synchronized boolean accept(Personne personne){
      if(this.etageActu==personne.etageActu){
          if(this.places()>0){
              this.infoPersonnes.add(personne);
              this.PlacesOccupees++;
              return true;
          }
          else{
          System.out.println("l'ascensueur est deja saturé");
          return false;
          }
          }
      else{
          if(this.etageActu>personne.etageActu){System.out.format("l'ascensueur a l'etage %d ,attendre qu'il descent",this.etageActu);
          return false;}
          else {System.out.format("l'ascensueur a l'etage %d ,attendre qu'il monte",this.etageActu);
          return false;}
      }
             
    }
    
    synchronized void leave(Personne personne){
           this.infoPersonnes.remove(personne);
           this.PlacesOccupees--;
    }
}
    


