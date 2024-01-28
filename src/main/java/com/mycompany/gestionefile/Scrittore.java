package com.mycompany.gestionefile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Scrittore implements Runnable{

    String nomeFile;
    int check;
    
    public Scrittore(String nomeFile){
        this.nomeFile = nomeFile;
        this.check = 0;
    }
    
    @Override
    public void run() {
        //scrivi();
    }

    public void scrivi(String user, String pass){
        BufferedWriter br=null;
        
        
        try {
            //1) apro il file
            br = new BufferedWriter(
                    new FileWriter(nomeFile));
            //2) scrivo nel buffer
            br.write(user);
            br.write(";");
            br.write(pass);
            br.write("\n\r");
          
            
            //3) svuoto il buffer e salvo nel file i dati
            br.flush();         
        } catch (IOException ex) {
            Logger.getLogger(Scrittore.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (br!=null)
                try {
                    //4)chiudo lo stream in uscita
                    br.close();
            } catch (IOException ex) {
                Logger.getLogger(Scrittore.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
    }
}