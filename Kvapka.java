
/**
 * Trieda nám vytvorí kvapku kompozíciou pomocou tried 
 * Trojuholník a Kruh na zadanej pozicii.
 * 
 * @author (Rebeka Panáková) 
 * @version (16.12.2021)
 */
public class Kvapka {
    //atributy triedy
    private static int rychlost = 4;
    //atributy inštancie
    private Kruh spodok; //časti kvapky
    private Trojuholnik vrch;
    private int pozX; //súradnica kvapky x
    private int pozY; //súradnica kvapky y
    
    /**
     * Konštruktor triedy Kvapka vytvorí kvapku na danej 
     * suradnici x a y pomocou svojích častí kruh a trojuholník.
     * 
     * @param pozX je x suradnica kde sa ma kvapka vytvorit 
     */
    public Kvapka(int pozX) {
        // inicializacia kvapky na danej pozícii x a y na 0, aby sa kvapka vytvorila na vrchu obrazovky
        this.spodok = new Kruh(30, pozX, this.pozY + 30); //pomocou kompozície vytvára spodnú časť kvapky
        this.vrch = new Trojuholnik(40, 30, pozX + 15, this.pozY); //pomocou kompozície vytvára vrchnú časť kvapky
        this.pozX = pozX;
        this.pozY = 0;
    }
    
    //metódy triedy
    /**
     * zrýchli pohyb kvapky o 2 milisekundy
     */
    public static void zrychli() {
        rychlost += 2;
    }
    
    //metódy inštancie
    /**
     * Posielanie správ častiam kvapky s posunom smerom dole pre plynulý pohyb
     */
    public void padaj() {
        this.vrch.posunZvisle(rychlost);
        this.spodok.posunZvisle(rychlost);
        this.pozY += rychlost;
        this.zobraz();
    }
    
    /**
     * @return vrati pozíciu x
     */
    public int getPozX() {
        return this.pozX;
    }
    
    /**
     * @return vrati pozíciu y
     */
    public int getPozY() {
        return this.pozY;
    }
    
    /**
     * Metóda skryje časti kvapky
     */
    public void skry() {
        this.spodok.skry();
        this.vrch.skry();
    }
    
    /**
     * Metóda zobrazí časti kvapky
     */
    public void zobraz() {
        this.spodok.zobraz();
        this.vrch.zobraz();
    }
}
