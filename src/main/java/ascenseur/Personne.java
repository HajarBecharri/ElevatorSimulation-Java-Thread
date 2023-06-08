/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ascenseur;
import java.lang.*;
//import java.util.*; 
/**
 *
 * @author hp
 */
public class Personne implements Runnable {
    
    String nom;
    int etageAriv;
    int etageActu;
    Ascenseur ascenseur;
    IGAscenseur UG;
    String timer;

    public Personne(String nom, int etageAriv, int etageActu, Ascenseur ascenseur,IGAscenseur UG) {
        this.nom = nom;
        this.etageAriv = etageAriv;
        this.etageActu = etageActu;
        this.ascenseur = ascenseur;
        this.UG=UG;
        
    }

    
    
    
    @Override
    public void run(){
        
    timer=this.nom+" : Attendre à l'etage "+this.etageActu;
    UG.setText(timer);
    UG.setImage("C:\\ascA.jpeg");
        try {
    
    this.rentrer();
    timer=this.nom+" : Entre à l'etage "+ascenseur.etageActu+"\n";
    UG.setText(timer);
    UG.setImage("C:\\ascE.jpeg");
    while(ascenseur.etageActu!=this.etageAriv){
    Thread.sleep(2000);
    System.out.format("[%s]: Je demande à sortir \n", this.nom);
    }
    this.ascenseur.leave(this);
    timer=this.nom+" : sort à l'étage"+ ascenseur.etageActu;
    UG.setText(timer);
    UG.setImage("C:\\ascS.png");
    
    
}
        catch(InterruptedException e){
    
}

    
}
    
    
    
    public void rentrer() throws InterruptedException{
    while (!(this.ascenseur.accept(this)))
    {
    Thread.sleep(2000);
    System.out.format("[%s] : Je redemande à rentrer \n", this.nom);
    }
    
    }
    
    
}
