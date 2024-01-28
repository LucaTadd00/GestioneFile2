package com.mycompany.gestionefile;

import java.util.Scanner;
import java.util.ArrayList;

public class GestioneFile {
    public static Scanner s = new Scanner(System.in); 
    public static Lettore lettore = new Lettore("output.csv");
    public static Scrittore scrittore = new Scrittore("output.csv");
    public static Scrittore copiatore = new Scrittore("copia.csv");
    public static Matrice matrix;
            
  public static void main(String[] args) {
               
        //lettore.start();
        //Thread threadScrittore = new Thread(scrittore);
        
        System.out.println("inserisci la chiave di cifratura: ");
        String key = s.nextLine().toUpperCase(); 
        matrix = new Matrice(key);
                 
        matrixBuilder();
        login();
        
        int choise = 5;
    while(choise != 3) {
        System.out.println("Login riuscito! ora le tue credenziali sono salvate nel file 'output.csv'"); 
        System.out.println("ora scegli un opzione"); 
        System.out.println("inserisci 1 per copiare il file con tutti gli username e password");
        System.out.println("inserisci 2 per aggiungere un nuovo utente"); 
        System.out.println("inserisci 3 per uscire dal programma"); 
        System.out.println("scelta : "); 
        choise = s.nextInt();
        s.nextLine();
        
        switch(choise) {
  case 1:
    String output = lettore.leggi();
    System.out.println(output);
    String[] userPass = output.split("\n");

        for (String pair : userPass) {

            String[] div = pair.split(";");

            String user = div[0];
            String password = div[1];
            
           copiatore.scrivi(user, password); 

        }   
    break;
  case 2:
    login();
    break;
  case 3:
      System.out.println("uscita in corso..."); 
    break;
  default:
    System.out.println("scelta inesistente o non corretta"); 
}
    }
    
    }
  
       public static void login() {
           
        System.out.println("inserisci l'username : ");
        String userName = s.nextLine().toUpperCase();
        System.out.println("inserisci la password : ");
        String password = s.nextLine().toUpperCase();
        
        String criptedUser = matrix.cifra(userName);
        String criptedPass = matrix.cifra(password);
            
        scrittore.scrivi(criptedUser, criptedPass);
        
        System.out.println("username criptato: " + criptedUser);
        System.out.println("password criptata: " + criptedPass);
        }
       
    public static void matrixBuilder() {
    ArrayList<Vigenere> quadranti = new ArrayList<>();
    Vigenere quadrante_1=new Vigenere(0,12,0,12,matrix);
     quadranti.add(quadrante_1);
    
    Vigenere quadrante_2=new Vigenere(0,12,12,26,matrix);
     quadranti.add(quadrante_2);
    
    Vigenere quadrante_3=new Vigenere(12,26,0,12,matrix);
     quadranti.add(quadrante_3);
    
    Vigenere quadrante_4=new Vigenere(12,26,12,26,matrix);
    quadranti.add(quadrante_4);
        
        for (Vigenere v : quadranti) {
          Thread t = new Thread(v);
          t.start();
          try {
              t.join();
          } catch (InterruptedException ex) {
              System.err.println("Errore metodo join");
          }
        }
}
    
}