import java.util.Scanner;
import java.io.IOException;

/**
 * Trieda Hra umožnuje zadať meno hráča tak ako aj oblasť otázok,
 * následne spustí generovanie otázok.
 * Po skončení pomocou metódy uloz v Statistike ukladá udaje: meno a dosiahnutý počet bodov a typ otázok.
 * 
 * @author Rudolf Pastva 
 * @version 1.0
 */
public class Hra {
    private String osoba;
    private Otazky otazky;  
    
    /**
    * Bezparametrický konštruktor.
    */
    public Hra() {
        this.osoba = "sc";
        this.otazky = new Otazky() ;
    }
    
    /**
    * Metóda pridaj hráča načíta meno hráča z konzoly.
    */
    public void pridajHraca() {
        System.out.println("Meno súťaziaceho" );     
        Scanner klavesnica = new Scanner(System.in);
        this.osoba = klavesnica.nextLine();
    }
    
    /**
    * Metóda toString vracia meno hráča.
    * 
    * @return meno hráča
    */
    public String toString() {
        return this.osoba;
    }
    
    /**
    * Metóda spustenie hry volá metódu pridaHraca, dalej metódu triedy Otazky zadajTypOtázok,dajOtazku.
    * Následne dosiahnuté hodnoty ukladá do Statistiky.
    */
    public void spustenieHry() throws IOException {
        this.pridajHraca();
        this.otazky.zadajTypOtazok();    
        this.otazky.dajOtazku() ;
        new Statistika().uloz(this.osoba, this.otazky.getbodyCelkovo(), this.otazky.getTypOtazok() );
    
    }
    
    
}
