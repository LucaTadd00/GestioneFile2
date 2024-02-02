
package com.mycompany.gestionefile;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class GestioneFile {
    
    // DICHIARO LE VARIABILI GLOBALI
    
    public static Scanner s = new Scanner(System.in); 
    public static Lettore lettore = new Lettore("output.csv");
    public static Scrittore scrittore = new Scrittore("output.csv");
    public static Scrittore copiatore = new Scrittore("copia.csv");
    public static Matrice matrix;
    public static Scrittore scrittoreCSV = new Scrittore("user.csv");
    public static ArrayList<User> users = new ArrayList<>();
            
  public static void main(String[] args) {              
        
      //ISTANZIO LA MATRICE PER CIFRARE LA PASSWORD
      
        System.out.println("chiave di cifratura: GESTIONEFILE ");
        String key = "GESTIONEFILE";
        matrix = new Matrice(key);                
        matrixBuilder();

 //MENU DELLE SCELTE
       
        int choise = 8;
    while(choise != 7) { 
        try { 
        
        System.out.println("-----------MENU SCELTE------------"); 
        System.out.println("scegli un opzione"); 
        System.out.println("inserisci 1 per copiare il file con tutti gli username e password");
        System.out.println("inserisci 2 per aggiungere un nuovo utente"); 
        System.out.println("inserisci 3 per scrivere sul file user.csv (DataOutputStream)");
        System.out.println("inserisci 4 per leggere dal file user.csv (DataInputStream)");
        System.out.println("inserisci 5 per scrivere nel file user.txt tramite serializzazione");
        System.out.println("inserisci 6 per leggere dal file user.txt tramite serializzazione (devi prima scrivere per poter leggere!)");
        System.out.println("inserisci 7 per uscire dal programma");
        System.out.println("consiglio: non effettuare la copia due volte o risciverai anche dati gia copiati!"); 
        System.out.println("scelta : "); 
        choise = s.nextInt();
        s.nextLine();
     
        switch(choise) {
  case 1:
      try {
          
    //ISSUE 1, LETTURA DAL FILE output.csv PER POI COPIARE IN copia.csv
    String output = lettore.leggi();
      System.out.println(output);
    String[] userPass = output.split("\n");
  
        for (String pair : userPass) {

            String[] div = pair.split(";");

            String user = div[0];
            String password = div[1];
            
           copiatore.scrivi(user, password);
}       
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("copiature effettuata correttamente!");    
         }
   
    break;
  case 2:
      
      //METODO PER INSERIRE UTENTE E PASSWORD E CIFRARE POI LA PASSWORD
      
    login();
    break;
  case 3:
      
      //ISSUE 3, SCRIVO NEL FILE user.csv I DATI COME INDICATI IN user.json
      
      String[] elements = {"", "", "",} ;
        System.out.println("inserisci il tuo nome");
        elements[0] = s.nextLine();
        System.out.println("inserisci il tuo cognome");
        elements[1] = s.nextLine();
        System.out.println("inserisci il tuo impiego");
        elements[2] = s.nextLine();
        scrittoreCSV.scriviCSV(elements);
        System.out.println("inserimento effettuato correttamente!"); 
        
    break;

  case 4:
      
      //ISSUE 3, LEGGO DATI DAL FILE user.csv
      
      Lettore lettoreCSV = new Lettore("user.csv");
      System.out.println("inizio lettura");
      lettoreCSV.leggiCSV();
      
    break;
  case 5:
      
      //ISSUE 4, CREO L'OGGETTO UTENTE PER ESPORTARLO TRAMITE SERIALIZZAZIONE NEL FILE user.txt
      
        System.out.println("inserisci il tuo id");
        int id2 = s.nextInt();
        s.nextLine();
        System.out.println("inserisci il tuo nome");
        String name = s.nextLine();
        System.out.println("inserisci il tuo cognome");
        String surname = s.nextLine();
        System.out.println("inserisci il tuo impiego");
        String role = s.nextLine();
        User utente = new User(id2, name, surname, role);
        utente.esportaUser("user.txt");
    
    break;
    case 6:
        
        //ISSUE 4, RICREO L'OGGETTO UTENTE IMPORTANDOLO DAL FILE user.txt

        System.out.println("lettura da file user.txt tramite serializzazione in corso...");
        User utenteImportato = new User("user.txt");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace(); }  
         System.out.println("parametri utente importato : id : " + utenteImportato.getId() + ", nome : " + utenteImportato.getName() + ", cognome : " + utenteImportato.getSurname() 
                 + ", impiego : " + utenteImportato.getRole());
    
    break;
      case 7:
      System.out.println("uscita in corso..."); 
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace(); }      
    break;
           
  default:
    System.out.println("scelta inesistente o non corretta"); 
}
    } catch (InputMismatchException e) { 
    System.err.println("Errore: Input non numerico. Assicurati di inserire un numero valido.");
    choise = 0;
    s.nextLine();
    }
    }
    
    }
  
  //METODO PER INSERIRE UTENTE E PASSWORD
  
       public static void login() {
           
        System.out.println("inserisci l'username : ");
        String userName = s.nextLine().toUpperCase();
        System.out.println("inserisci la password : ");
        String password = s.nextLine().toUpperCase();
        
        String criptedPass = matrix.cifra(password);
            
        scrittore.scrivi(userName, criptedPass);
        
        System.out.println("Login riuscito! ora le tue credenziali sono salvate nel file 'output.csv'.");
        
        System.out.println("username : " + userName);
        System.out.println("password criptata: " + criptedPass);
        }
       
       //METODO PER CREARE LA MATRICE NECESSARIA PER CIFRARE LA PASSWORD
       
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
    
