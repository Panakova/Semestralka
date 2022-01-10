/**
 * Trieda Kvietok vytvorí jedinú inštanciu kvietku, ktorým používaťeľ hrá hru. 
 * 
 * @author (Rebeka Panáková) 
 * @version (28.12.2021)
 */
public class Kvietok {
    // atribúty triedy
    private static Kvietok kvietok;
    
    //atribúty inštancie
    private Obrazok obrazok; 
    private int pozX;
    private int pozY;
    
    /**
     * Privátny konštruktor vytvorí inštanciu triedy
     */
    private Kvietok() {
        //inicializácia atribútov
        this.pozX = 150; // pozícia X 
        this.pozY = 350; //pozícia Y
        this.obrazok = new Obrazok("pics\\kvet.png", this.pozX, 350); //načítanie obrázku kvietka zo súboru
        this.obrazok.zobraz(); //zobrazenie kvietka
    }
    
    //metóda triedy
    /**
     * Singleton kontroluje či je vytvorená inštancia triedy, ak nie vyvolá konštruktor
     */
    public static Kvietok vytvorKvietok() {
        if (Kvietok.kvietok == null) {
            Kvietok.kvietok = new Kvietok();
        }
        return Kvietok.kvietok;
    }
    
    //metódy inštancie
    /**
     * šípkami ovladané príkazy na posun v rozmedzí hraej plochy
     */
    public void posunVpravo() {        
        if (this.pozX < 288) {
            this.pozX += 8;     
            this.obrazok.posunVodorovne(8);
        }
        
    }
    
    /**
     * šípkami ovladané príkazy na posun v rozmedzí hraej plochy
     */
    public void posunVlavo() {
        if (this.pozX > 0) {
            this.obrazok.posunVodorovne(-8);
            this.pozX -= 8;     
        }
    }
    
    /**
     * @return pozíciu x, na ktorej sa práve nachádza
     */
    public int getPozX() {
        return this.pozX;
    }
    
    /**
     * @return pozíciu y, na ktorej sa práve nachádza
     */
    public int getPozY() {
        return this.pozY;
    }
}
