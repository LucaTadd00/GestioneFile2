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
try (BufferedWriter br = new BufferedWriter(new FileWriter(nomeFile, true))) {
            if (check == 0) {
              BufferedWriter bc = new BufferedWriter(new FileWriter(nomeFile));
            bc.write("");
            bc.flush();
            bc.close();
            }

            br.write(user);
            br.write(";");
            br.write(pass);
            br.write("\n\r");

            br.flush();
            check++;
        } catch (IOException ex) {
            Logger.getLogger(Scrittore.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        }
    }
