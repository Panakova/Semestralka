import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
/**
 * Trieda hra ma za ulohu vytvorit hraciu plochu a spravovat chod hry.
 * Tiež dokáže zapísať a načítať zo súboru informácie o predoslých hrách.
 * 
 * @author (Rebeka Panáková) 
 * @version (12.11.2021)
 */

public class Hra  {
    //atribúty triedy
    private static Hra plocha;
    private static final int NAROCNOSTI = 3;
    //atribúty inštancie
    private Platno platno;
    private Hrac hrac;
    private Casovac casovac;
    private Kvietok kvet;
    private Manazer manazer;
    private Obrazok pozadie;
    private ArrayList<ArrayList<Hrac>> hraci;
    
    /**
     * Konštruktor vytvorí hru a načíta hráčov zo záznamu
     */
    private Hra() {
        //inicializácia atribútov
        this.platno = null;
        this.casovac = null;
        this.hrac = null;
        this.manazer = null;
        this.kvet = null;
        this.pozadie = null;
        this.hraci = new ArrayList<ArrayList<Hrac>>();
        for (int i = 0; i < NAROCNOSTI; i++) {
            this.hraci.add(new ArrayList<Hrac>());
        }
        this.nacitajHracov();
    }
    
    //metódy triedy
    /**
     * Singleton kontroluje, či je vytvorená inštancia triedy
     */
    public static Hra zacniHru() {
        if (Hra.plocha == null) {
            Hra.plocha = new Hra();
        }
        return Hra.plocha;
    }

    //metódy inštanice
    /**
     * metóda načíta hráčov zo súboru, roztriedi ich do ArrayListov podľa zvolenej náročnosti
     * ak by nastala chyba pri nájdení súboru, vyhlási chybu
     */
    public void nacitajHracov() {
        try {
            File subor = new File("hry.txt");
            Scanner s = new Scanner(subor);
            while (s.hasNext()) {
                //načítanie hodnoty náročnosti 
                Narocnost nar = Narocnost.valueOf(s.next());
                //načítanie skóre a mena hráča
                int skore = s.nextInt();
                String meno = s.nextLine().replaceAll(" ", "");
                //vytvorenie hrača a následné priradenie do zoznamu hráčov uloženého v hre
                Hrac h = new Hrac (meno, skore, nar);
                if (nar == Narocnost.LAHKA) {
                    this.hraci.get(0).add(h);
                } else if (nar == Narocnost.STREDNA) {
                    this.hraci.get(1).add(h);
                } else {
                    this.hraci.get(2).add(h);
                }
            }
            s.close(); //zatvorenie súboru
        } catch (FileNotFoundException e) {
            // vyhlásenie chyby v prípade nenajdeného súboru
            JOptionPane.showMessageDialog(null, "Súbor nebol nájdený", "Chyba", JOptionPane.ERROR_MESSAGE, null);
        }
    }   
    
    /**
     * metóda na spustenie aplikácie, kde má používateľ na výber zvoliť si 
     * akciu, ktorú chce vykonať
     */
    public void spusti() {
        //zobrazenie ponuky možností
        String vyber = JOptionPane.showInputDialog(null, "Vyber: \n 1 - Hrať hru \n 2 - Výpis všetkých hier \n 3 - Zisti najlepšie skóre \n 4 - Hľadaj hráča \n 5 - Ukonči", "Hra: padajúce kvapky", JOptionPane.QUESTION_MESSAGE);
        if (vyber != null) {
            switch (vyber) {
                case "1":
                    this.hraj(); //spustenie hry
                    break;
                case "2":
                    this.vypisZoZoznamu(); //výpis všetkých hier
                    break;
                case "3":
                    this.getNajlepsiHrac(); //výpis najlepšieho hráča
                    break;
                case "4":
                    this.hladajHraca(); // výpis hľadaného hráča
                    break;
                case "5":
                    System.exit(0); //ukončnie hry
                    break;
                default:
                //výpis v prípade neplatného vstupu
                    JOptionPane.showMessageDialog(null, "Nespravne zadané hodnoty");
                    this.spusti();
                    break;
            }
        } else if (vyber == null) {
            //v prípade výberu hráča mimo ponúkaných aktivít systém ukončí aplikáciu
            System.exit(0);
        }
    }

    /**
     * Nastavenie prostredia pre hru a chod samotnej hry
     */
    public void hraj() {
        //this.spustenaHra = true;
        String meno = JOptionPane.showInputDialog(null, "Zadaj meno hráča:", "Hráč", JOptionPane.QUESTION_MESSAGE);
        if (meno == null) {
            meno = "Nezadane";
        }
        //vytvorenie hráča s príslušným počtom životov
        this.hrac = new Hrac(meno);
        this.platno = Platno.dajPlatno();
        this.pozadie = new Obrazok("pics\\pozadie.png", 0, 0);
        this.pozadie.zobraz();
        this.hrac.setZivoty();
        //inicializácia prostriedkov na chod hry
        this.manazer = new Manazer();
        this.casovac = new Casovac(this, this.hrac.getNarocnost());
        this.kvet = Kvietok.vytvorKvietok();
        this.manazer.spravujObjekt(this.kvet);
        //zobrazenie aktuálného skóre na plátne
        while (this.hrac.getZivot()) {
            this.platno.setRamik("Skóre: " + this.hrac.getSkore());
        }
    }
    
    /**
     * Ukončenie hry, uloženie údajov o hre a výpis údajov hráčovi.
     */
    public void koniecHry() {
        //uloženie hráča do zoznamu hráčov roztriedeného podľa náročnosti
        if (this.hrac.getNarocnost() == Narocnost.LAHKA) {
            this.hraci.get(0).add(this.hrac);
        } else if (this.hrac.getNarocnost() == Narocnost.STREDNA) {
            this.hraci.get(1).add(this.hrac);
        } else {
            this.hraci.get(2).add(this.hrac);
        }
        //vynulovanie prostriedkov na zastavenie hry
        this.manazer.prestanSpravovatObjekt(this.kvet);
        this.casovac.zastav();
        this.casovac.zmazVsetkyKvapky();
        this.zapisHru();
        String vypis = "Tvoje skóre je: " + this.hrac.getSkore();
        JOptionPane.showMessageDialog(null, vypis, "Koniec hry" , JOptionPane.PLAIN_MESSAGE);
        this.hrac = null;
        this.spusti(); //opätovné vrátenie sa na počiatočné menu
    }
    
    /**
     * vytvorenie kvapky na danej pozícií a následné pridanie do zoznamu
     * @param kde pozícia kvapky
     */
    public void novaKvapka(int kde) {
        Kvapka k = new Kvapka(kde);
        this.casovac.pridajKvapku(k);
    }

    /**
     * zapísanie odohranej hry do súboru
     */
    public void zapisHru() {
        try {
            //inicializácia zapisovateľa do súboru a otvorenie súboru
            PrintWriter pw = new PrintWriter(new File("hry.txt"));
            //zápis všetkých hráčov zo zoznamu hráčov do súboru
            for (int i = 0; i < this.hraci.size(); i++) {
                for (int j = 0; j < this.hraci.get(i).size(); j++) {
                    pw.print(this.hraci.get(i).get(j).getNarocnostString());
                    pw.print(" ");
                    pw.println(this.hraci.get(i).get(j).toString());
                }
            }
            pw.close();
        } catch (FileNotFoundException e) {
            //v prípade chýbajucého súboru vyhodí chybu
            JOptionPane.showMessageDialog(null, "Subor sa nenašiel");
        }
    }
    
    /**
     * výpis vvšetkých hier
     */
    public void vypisZoZoznamu() {
        String vypis = "";
        for (int i = 0; i < NAROCNOSTI; i++) {
            for (Hrac h : this.hraci.get(i)) {
                vypis += h.vypisPrePanel() + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, vypis, "Zoznam hier" , JOptionPane.PLAIN_MESSAGE);  
        this.spusti();
    }
    
    /**
     * obalovacia metóda pre pripísanie skóre
     */
    public void pripisSkore() {
        this.hrac.pripisSkore();
    }

    /**
     * overenie či hráč chytil kvapku na i-tej pozícií
     * @return pravda alebo nepravda
     */
    public boolean ciChytil(int i) {
        Kvapka k = this.casovac.getKvapky().get(i);
        boolean chytil = false;
        //pozície, v ktorej akceptujeme že hráč chytil kvapku
        if (this.kvet.getPozX() + 32 >= k.getPozX() && this.kvet.getPozX() <= k.getPozX() 
            && k.getPozY() >= 340 && k.getPozY() <= 440) {
            chytil = true;
            k.skry();
        } else if (k.getPozY() > 450) {
            //dolná hranica, kedy už hráč kvapku chytiť nestihol
            this.hrac.uberZivot();
            k.skry();
            this.casovac.getKvapky().remove(k);
            if (!this.hrac.getZivot()) {
                this.koniecHry();
            }
        }
        return chytil;
    }
    
    /**
     * obaľovacia trieda na skóre hráča
     * @return skore hráča
     */
    public int getSkore() {
        return this.hrac.getSkore();
    }
    
    /**
     * prehľadávaie zoznamov hráčov a následné vyhodnotenie najlepšieho
     */
    public void getNajlepsiHrac() {
        ImageIcon vyhra = new ImageIcon("pics\\winner_cup.png");
        Hrac najlepsi = null;
        int najlepsieSkore = 0;
        //ponuka možností
        String[] narocnosti = {"ľahka", "stredna", "ťažka"};
        int volba = JOptionPane.showOptionDialog(null, "Zvoľ si náročnosť", "Ziskaj najlepšieho hráča", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, narocnosti, 0);
        //prípad, že používateľ nezvolí možnosť
        if (volba == -1) {
            this.spusti();
        }
        //prehľadavanie zoznamov hráčov
        for (Hrac h : this.hraci.get(volba)) {
            if (najlepsieSkore < h.getSkore()) {
                najlepsieSkore = h.getSkore();
                najlepsi = h;
            }
        }
        JOptionPane.showConfirmDialog(null, najlepsi.vypisPrePanel() , "Najlepší hráč je", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, vyhra);
        this.spusti();
    }
    
    /**
     * Metóda nájde nami zadaného hráča a vypíše jeho hry a dosiahnuté skóre
     */
    public void hladajHraca() {
        //zadanie hľadaného hráča
        String hladany = JOptionPane.showInputDialog(null, "Zadaj meno hráča");
        ArrayList<Hrac> najdeny = new ArrayList<Hrac>();
        //prehľadavanie v zoznamoch
        for (int i = 0; i < NAROCNOSTI; i++) {
            for (Hrac h : this.hraci.get(i)) {
                if (hladany == null) {
                    this.spusti();
                } else if (hladany.equals(h.getMeno())) {
                    najdeny.add(h); 
                }
            }
        }
        String vypis = "";
        //výpis najdeného hráča a všetkých jeho hier
        if (vypis.equals("")) {
            for (int i = 0; i < najdeny.size(); i++) {
                vypis += najdeny.get(i).vypisPrePanel();
                vypis += "\n";
            }   
            JOptionPane.showMessageDialog(null, vypis, "Hľadanie hráča", JOptionPane.INFORMATION_MESSAGE);
        } else {
            //výpis v prípade nenajdeného hráča
            JOptionPane.showMessageDialog(null, "Hráč nebol nájdený", "Hľadanie hráča", JOptionPane.ERROR_MESSAGE);
        }
        this.spusti();
    }
}
