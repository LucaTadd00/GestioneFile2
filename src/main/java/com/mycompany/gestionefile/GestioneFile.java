package gestionefile;

public class GestioneFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //1)LETTURA
        Lettore lettore = new Lettore("user.json");
        lettore.start();
        //2)ELABORAZIONE
        
        //3) SCRITTURA
        Scrittore scrittore = new Scrittore("output.csv");
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
        
        Scanner s = new Scanner(System.in); 
        
        System.out.println("inserisci l'username : ");
        String userName = s.nextLine();
        System.out.println("inserisci la password : ");
        String password = s.nextLine();
        
        
        
        
    }
    
}