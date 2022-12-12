/**
 * Trieda OblastOtazok priradí k vybranej oblasti otázok názov súboru v ktorom sú otázky ulozené.
 * 
 * @author Rudolf Pastva 
 * @version 1.0
 */
public class OblastOtazok {
    private String uroven;
    
    /**
    * Bezparametrický konštruktor.
    */
    public OblastOtazok() {
        this.uroven = null;
    }
    
    /**
    * Metóda ktorá vracia String názov súboru v ktorom sú otázky uložené.
    * 
    * @return hodnota (názov) 
    */
    public String typOt(String paHodnota) {
        this.uroven = paHodnota;
        String hodnota = null ;
        if (this.uroven.equals("1")) {
            hodnota = "otazkyEuropa"; 
        }
        if (this.uroven.equals("2")) {
            hodnota = "otazkyAmerika";
        }
        if  (this.uroven.equals("3")) {
            hodnota = "otazkyAzia";
        }
        if  (this.uroven.equals("4")) {
            hodnota = "otazkyAfrika";
        }
        return hodnota;
    }
}
