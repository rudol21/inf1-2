import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Trieda Statistika najprv načíta do ArrayListu predošlých hráčov a pridá aj posledného hráča,
 * následne hráčov z ArrazListu zoradí podla skóre  a zapíše do súboru štatistika.
 * Potom údaje zo súboru postupne načíta a výpíše do konzoly.
 * Zapisuje sa do štyroch súborov každej oblasti prislúcha jeden. 
 * 
 * @author Rudolf Pastva 
 * @version 1.0 
 */
public class Statistika {
    private String menoHraca;
    private double pocetBodov;
    private String kontinent;
    private String statistikaKo;
    private ArrayList<String> statistika;
    private ArrayList<String> nacitaneSkore;
    private double[] skoreHr;
    
    /**
    * Bezparametrický konštruktor.
    */
    public Statistika() {
        this.pocetBodov = 0.0;
        this.menoHraca = "";
        this.kontinent = "";
        this.statistikaKo = "";
        this.statistika = new ArrayList<String>();
        this.nacitaneSkore = new ArrayList<String>();
        this.skoreHr = new double[1];
    }
     
    /**
    * Metóda uloz načíta hodnoty posledného sutaziaceho.
    * A zavolá medódy getStatistiku(), zapisSkoreHracaArray(),
    * zapisSkoreHracaDoSuboru(), vypisStatistiku().
    */
    public void uloz(String hrac , double pocBodov , String pakontinent) throws IOException {
        this.menoHraca = hrac;
        this.pocetBodov = pocBodov;
        this.kontinent = pakontinent;
        this.getStatistiku();
        this.zapisSkoreHracaArray();
        this.zapisSkoreHracaDoSuboru();
        System.out.println("*******************************************************************");
        this.vypisStatistiku();
    }
    
     /**
    * Metóda getStatistiku priradí pomocou switch case otázkam prislúchajúci súbor štatistiku.
    * 
    * @return názov súboru
    */
    public String getStatistiku() {
        switch(this.kontinent) {
            case "otazkyEuropa" :
                this.statistikaKo = "statistikaEu";
                break; 
            case "otazkyAmerika" :
                this.statistikaKo = "statistikaAm";
                break; 
            case "otazkyAzia" :
                this.statistikaKo = "statistikaAz";
                break; 
            case "otazkyAfrika" :
                this.statistikaKo = "statistikaAf";
                break; 
   
        }
        return this.statistikaKo;
    }
    
    /**
    * Metóda zapisSkoreHracaArray zapíše do ArrayListu hodnoty zo súbora + hodnoty posledného súťažiaceho.
    */
    public void zapisSkoreHracaArray() throws IOException {
        File subor = new File(this.statistikaKo + ".txt");
        Scanner citanie = new Scanner(subor);
        this.skoreHr = new double[100];
        int i = 0;
        while (citanie.hasNextLine()) {
            String pomPrem = citanie.next();
            this.statistika.add(" " + citanie.next() + " " + citanie.next()   );
            this.nacitaneSkore.add ( citanie.nextLine());
            this.skoreHr[i] = Double.valueOf(this.nacitaneSkore.get(i) );
            i++;
        }
        this.statistika.add(" " + this.menoHraca + " skore " );
        this.nacitaneSkore.add("" + this.pocetBodov);
        this.skoreHr[i] =  this.pocetBodov;
        citanie.close();
    }
 
    /**
    * Metóda zapisSkoreHracaDoSuboru zapíše do prislúchajúceho súboru
    * zoznam hráčov zoradených podla výšky celkového skóre.
    */
    public void zapisSkoreHracaDoSuboru() throws IOException {
        File subor = new File(this.statistikaKo + ".txt");
        PrintWriter zapis = new PrintWriter(subor);
        int poradiePoslednehoHraca = 0;
        int poradie = 0;
     
        for (int i = 0 ; i < this.statistika.size() - 1 ; i++ ) {
            if (this.skoreHr[this.statistika.size() - 1] <= this.skoreHr[i]) {
                poradiePoslednehoHraca++;
        
            }
        }
        for ( ;poradie < poradiePoslednehoHraca  ; poradie++ ) {
            zapis.print(poradie + 1);
            zapis.print(this.statistika.get(poradie)); 
            zapis.println(this.nacitaneSkore.get(poradie)); 
        }
        zapis.print(poradie + 1);
        zapis.print(this.statistika.get(this.statistika.size() - 1)); 
        zapis.println(this.nacitaneSkore.get(this.statistika.size() - 1) );
        for ( ;poradie < this.statistika.size() - 1  ;poradie++ ) {
            zapis.print(poradie + 2);
            zapis.print(this.statistika.get(poradie)); 
            zapis.println(this.nacitaneSkore.get(poradie) ); 
        
        }
        zapis.close();
    }
   
    /**
    * Metóda vypisStatistiku vypíše zoznam hráčov do konzoly.
    */
    public void vypisStatistiku() throws IOException {
        File subor = new File(this.statistikaKo + ".txt");
        Scanner citanie = new Scanner(subor);
        while (citanie.hasNextLine()) { 
            System.out.print(citanie.next());
            System.out.print(String.format("%15s" , citanie.next()));
            System.out.println(citanie.nextLine());
        }
        citanie.close();
    }
  
    
}
   
   

