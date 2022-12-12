import java.io.IOException;
import java.util.Scanner;
/**
 * Trieda Aplikacia obsahuje metódu main ktorá zabezpečuje konečný chod programu.
 * Po skončený hry je v menu ponuka dalšej hry alebo koniec hry.
 * 
 * @author Rudolf Pastva 
 * @version 1.0 
 */
public class Aplikacia {
    
    public static void main (String[] args) throws IOException {
        Hra kviz;
        Scanner scanner = new Scanner(System.in);
        String volba = "";
        
        while (!(volba.equals("0"))) {
           
            switch (volba) {
                case "0": 
                    System.out.print ('\f');
                    break;  
                case (""):
                    kviz = new Hra();
                    Aplikacia.uvod();
                    kviz.spustenieHry();
                    System.out.println("*******************************************************************");
                    System.out.println("Nová hra (ENTER)"); 
                    System.out.println("Koniec hry (0)");
                    System.out.print("*******************************************************************" + "\n");
                    break;
            }
            volba = scanner.nextLine();
            scanner.nextLine();
        }
    }
    private static void uvod() {
        System.out.print ('\f');
        System.out.println("*******************************************************************");
        System.out.println("Dobrý deň,"); 
        System.out.println("vitajte vo vedomostnom kvíze ktorý preverí vaše vedomosti");
        System.out.println("z oblasti geografie, predmetom otázok sú hlavné mestá štátov.");       
        System.out.println("Ak nebudete vedieť odpoveď môžte si pomôct stlačením: „p” ");
        System.out.println("Máte na výber nasledovné kontinenty (1) - Európa");
        System.out.println("                                    (2) - Amerika");
        System.out.println("                                    (3) - Azia");
        System.out.println("                                    (4) - Afrika");
        System.out.println("*******************************************************************" );
    }
}
