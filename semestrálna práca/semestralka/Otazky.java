import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Trieda Otazky náhodne generuje otázky z vybranej oblasti.
 * V pripade že súťaziaci nevie odpoveď može požiadať o písmenko 
 * a trieda mu náhodne vygeneruje jedno pismeno zo správnej odpovede.
 * 
 * @author Rudolf Pastva 
 * @version 1.0 
 */
public class Otazky {
    
    private OblastOtazok uroven;
    private String hodnota;
    private int pocetOtazok;
    private String odpoved;
    private String retazecPomocny;
    private int[] pole;
    private double poceBZaOdpoved;
    private double celkovyPocBodov;
    /**
    * Bezparametrický konštruktor.
    */
    public Otazky() {
        this.uroven = new OblastOtazok();  
        this.hodnota = null;
        this.pocetOtazok = 0;
        this.odpoved = "";
        this.retazecPomocny = "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ";
        this.pole = null;
        this.poceBZaOdpoved = 4.0;
        this.celkovyPocBodov = 0.0;
    }
    
    /**
     * Metóda ktorá načíta oblasť z ktorej chce súťažiaci otázky.
     */
    public void zadajTypOtazok() {
        System.out.print("Vyber si kontinent: " + "\n");     
        Scanner klavesnica = new Scanner(System.in);
        String paHodnota = klavesnica.next();
        this.hodnota = this.uroven.typOt(paHodnota);
        if (this.hodnota == null) {
            System.out.println("Zadaná hodnota neexistuje,musiš si vybrať z ponuky.");
            this.zadajTypOtazok();
        }
    }
    
    /**
     * Metóda ktorá vráti typ otázok.
     * 
     * @return názov textového súboru 
     */
    public String getTypOtazok() {
        return this.hodnota;
    }
    
    /**
     * Metóda ktorá vráti počet otázok v danom súbore.
     * Táto hodnota sa používa dalej pri generovaní náhodnej otázky.
     * 
     * @return počet otázok 
     */
    public int getPocetOtazok() throws IOException {
        File subor = new File(this.hodnota + ".txt");
        Scanner citac = new Scanner(subor);
        this.pocetOtazok = 0;
        while (citac.hasNextLine()) {
            String otazka = citac.nextLine();
            this.pocetOtazok++;
        }
        citac.close();
        return this.pocetOtazok;
    }
    
    /**
     * Metóda ktorá vygeneruje otázku zo súboru a načítava odpoved.
     * Ak je zadaná odpoved správna suťažiacemu sa pripočítaju 4 body.
     * Ak si súťažiaci vypýta písmenko tak sa od bodov za správnu odpoveď
     * odpočíta diferenciál(diferenciál = 4 / počet písmenok odpovede).
     * Ak je zadaná odpoved nesprávna suťaziaci získava 0 bodov.
     * Tento cyklus sa opakuje 5 krát.
     */
    public void dajOtazku() throws IOException {
        
        File subor = new File(this.hodnota + ".txt");
        this.celkovyPocBodov = 0;
       
        for (int i = 1 ; i < 6 ; i++) {
            Scanner citac = new Scanner(subor);
            Random rnd = new Random();  
            int vygenerovaneCisOt = rnd.nextInt(this.getPocetOtazok() - 1) + 1;
            String otazka = "";
            int poradieOtazky = 0;
            this.poceBZaOdpoved = 4;
          
            while ((vygenerovaneCisOt != poradieOtazky)) {
                poradieOtazky = citac.nextInt();
                String pomocnapremenna = citac.next();
                otazka = citac.nextLine();
            
                if (vygenerovaneCisOt == poradieOtazky) {
                    System.out.print(i + "  ");
                    System.out.print(otazka);
                    System.out.print("     body " + this.poceBZaOdpoved);
                    System.out.println("     body celkovo " + this.celkovyPocBodov);
                    this.odpoved = pomocnapremenna;
                   
                }
            }
            this.pole = new int[this.odpoved.length()];
            String zadanaOdpoved = "";
            String zadanaOdpoved2 = null;
            int pocetVyziadPis = 0;
            while (!(zadanaOdpoved.equals(this.odpoved))) {
                Scanner klavesnica = new Scanner(System.in);
                zadanaOdpoved = klavesnica.nextLine();
                if (zadanaOdpoved.equals(this.odpoved)) {
                    System.out.println("spravna odpoved");
                    this.bodyCelkovo();
                    zadanaOdpoved2 = klavesnica.nextLine();
                    if (zadanaOdpoved2.equals("")) {
                        System.out.print ('\f');
                    }
                   
                } else {
                    if (zadanaOdpoved.equals("p")) {
                        pocetVyziadPis++;
                        System.out.print ('\f');
                        System.out.print(i + "  ");
                        System.out.print(otazka);
                        if (this.odpoved.length() == pocetVyziadPis) {
                            System.out.print("     body " + this.bodyZaNespravnuOdp());
                            this.bodyCelkovo();
                            System.out.println("     body celkovo " + this.celkovyPocBodov);
                            System.out.println("nesprávna");
                            zadanaOdpoved = this.odpoved;
                            zadanaOdpoved2 = klavesnica.nextLine();
                            
                            if (zadanaOdpoved2.equals("")) {
                                System.out.print ('\f');
                            }
                        } else {
                            System.out.print("     body " + this.body());
                            System.out.println("     body celkovo " + this.celkovyPocBodov);
                        }
                        System.out.println(this.pismenko());
                    } else {
                        System.out.print("     body " + this.bodyZaNespravnuOdp());
                        this.bodyCelkovo();
                        System.out.println("     body celkovo " + this.celkovyPocBodov);
                        System.out.println("nesprávna");
                        zadanaOdpoved = this.odpoved;
                        zadanaOdpoved2 = klavesnica.nextLine();
                        if (zadanaOdpoved2.equals("")) {
                            System.out.print ('\f');
                        }
                    }
                }
            }
            this.retazecPomocny = "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ";
            this.pole = new int[20];
            citac.close();
        }
        System.out.print ('\f');
        System.out.println("Koniec hry.  Skóre: " + this.celkovyPocBodov);
    }
   
    /**
     * Metóda ktorá náhodne vygeneruje jedno písmenko odpovede.
     * 
     * @return 1 písmenko z odpovede 
     */
    private String pismenko() {
        Random rnd = new Random();  
        int poradiepismenka = rnd.nextInt(this.odpoved.length());
        String retazec = "";
        this.pole[0] = 20;
        int j = 0; 
        for ( ; j < this.pole.length  ; j++) {
            if (this.pole[j] == poradiepismenka ) {
                poradiepismenka = rnd.nextInt(this.odpoved.length());
                j = 0;
            }
        }
        this.pole[poradiepismenka] = poradiepismenka;
        
          
        for (int i = 0 ; i < this.odpoved.length() ; i++) {
            String pottrznik = "" + this.retazecPomocny.charAt(i * 2);
             
            if (pottrznik.equals("_")) {
              
                if (i == (poradiepismenka )) {
                    retazec = retazec + this.odpoved.charAt(poradiepismenka) + " ";
                } else {
                    retazec = retazec + "_ ";
                }
            } else {
                retazec = retazec + this.retazecPomocny.charAt(i * 2) + " ";
            }
        }
        this.retazecPomocny = retazec;
        return retazec;
    }
    
    /**
     * Metóda ktorá vráti body za aktuálnu odpoveď 
     * po odpočítaní diferenciálu (váhy jedneho písmenka).
     * 
     * @return počet bodov za odpoveď 
     */
    private double body() {
        double diferencial = 4.0 / this.odpoved.length();
        this.poceBZaOdpoved = this.poceBZaOdpoved - diferencial;
        return this.poceBZaOdpoved;
    }
    
    /**
     * Metóda ktorá vráti body za nespávnu odpoveď.
     * 
     * @return počet bodov za nespávnu odpoveď 
     */
    private double bodyZaNespravnuOdp() {
        this.poceBZaOdpoved = 0.0;
        return this.poceBZaOdpoved;
    }
    
    /**
     * Metóda ktorá k celkovému počtu bodov pripočíta body za aktuálnu odpoveď.
     */
    private void bodyCelkovo() {
        this.celkovyPocBodov = this.celkovyPocBodov + this.poceBZaOdpoved;
    }
    
    /**
     * Metóda ktorá vráti celkový počet bodov.
     * 
     * @return celkový počet bodov 
     */
    public double getbodyCelkovo() {
        return this.celkovyPocBodov;
    }
    
    
}
