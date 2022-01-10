import javax.swing.JOptionPane;
/**
 * Trieda Hráč vytvorí hráča so všetkými potrebnými údajmi.
 * 
 * @author (Rebeka Panáková) 
 * @version (12.12.2021)
 */
public class Hrac {
    //atribúty inštancie
    private String meno;
    private int skore;
    private Zivoty zivoty;
    private boolean masZivot;
    private Narocnost narocnost;
    
    /**
     * Konštruktor vytvorí nového hráča pred začatím hry
     * @param meno priradí meno hráčovi
     */
    public Hrac(String meno) {
        // inicializacia atribútov
        this.meno = meno; 
        this.skore = 0;
        this.zivoty = null;
        this.masZivot = true;
        this.narocnost = this.setNarocnost(); //používateľ si zvolí náročnosť
    }
    
    /**
     * Konštruktor vytvorí hráča po načítaní zo súboru
     * @param meno priradí zadané meno
     * @param dosiahnuteSkore priradí dosiahnute skóre
     * @param narocnost priradí zvolenú náročnosť
     */
    public Hrac (String meno, int dosiahnuteSkore, Narocnost narocnost) {
        //inicializácia atribútov
        this.meno = meno;
        this.skore = dosiahnuteSkore;
        this.zivoty = null;
        this.masZivot = false;
        this.narocnost = narocnost;
    }
    
    //metódy inštancie
    /**
     * Výber náročnosti podľa hráča z ponuky prednastavených náročností
     */
    private Narocnost setNarocnost() {
        String[] narocnosti = {"ľahka", "stredna", "ťažka"};
        int volba = JOptionPane.showOptionDialog(null, "Zvoľ si náročnosť", "Voľba", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, narocnosti, 0);
        switch (volba) {
            case 0:
                this.narocnost = Narocnost.LAHKA;
                break;
            case 1:
                this.narocnost = Narocnost.STREDNA;
                break;
            case 2:
                this.narocnost = Narocnost.TAZKA;
                break;
            default:
            //v prípade nevybrania ponuky rekurziou opäť zavoláme túto metódu
                this.setNarocnost();
        }
        return this.narocnost;
    }
    
    /**
     * @return názov zvolenej náročnosti
     */
    public String getNarocnostString() {
        return this.narocnost.name();
    }
    
    /**
     * @return zvolená náročnosť
     */
    public Narocnost getNarocnost() {
        return this.narocnost;
    }
    
    /**
     * Metóda uberie hráčovi život
     */
    public void uberZivot() {
        this.zivoty.uberZivot();
    }
    
    /**
     * @retun aktuálne skóre
     */
    public int getSkore() {
        return this.skore;
    }
    
    /**
     * @return aktúálny stav životov
     */
    public boolean getZivot() {
        return this.zivoty.masZivot();
    }
    
    /**
     * @return meno hráča
     */
    public String getMeno() {
        return this.meno;
    }
    
    /**
     * pri načítaní hráča zo súboru mmu priradí zadané skóre
     * @param skore nastaví do atribútu skóre
     */
    public void setSkore(int skore) {
        this.skore = skore;
    }
    
    /**
     * vytvorí hráčovi 3 životy 
     */
    public void setZivoty() {
        this.zivoty = new Zivoty();
    }
    
    /**
     * pripíše hráčovi skóre
     */
    public void pripisSkore() {
        this.skore++;
    }
    
    /**
     * výpis hráča pre zápis do súboru
     */
    public String toString() {
        return this.getSkore() + " " + this.getMeno();
    }
    
    /**
     * výpis hráča pre záznam hráčov
     */
    public String vypisPrePanel() {
        return String.format("%-15s skóre: %-8d Náročnosť: %-7s", this.getMeno(), this.getSkore(), this.getNarocnostString());
    }
}
