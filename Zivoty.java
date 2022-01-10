
/**
 * Trieda Zivoty vytvorí hráčovi 3 životy. Životy sú v podobe obrázka a 
 * informácie o stave hráča.
 * 
 * @author (Rebeka Panáková) 
 * @version (20.12.2021)
 */
public class Zivoty {
    //atribúty inštancie
    private Obrazok zivot1;
    private boolean prvy;
    private Obrazok zivot2;
    private boolean druhy;
    private Obrazok zivot3;
    private boolean treti;
    /**
     * Konštruktor vytvorí obrázky reprezentujúce životy, 
     * priradí im hodnotu a následne ich zobrazí na plátne.
     */
    public Zivoty() {
        //inicializácia atribútov
        this.zivot1 = new Obrazok("pics\\heart.png", 300, 5);
        this.zivot2 = new Obrazok("pics\\heart.png", 280, 5);
        this.zivot3 = new Obrazok("pics\\heart.png", 260, 5);
        this.prvy = true;
        this.druhy = true;
        this.treti = true;
        this.zivot1.zobraz();
        this.zivot2.zobraz();
        this.zivot3.zobraz();
    }
    
    //metódy inštancie
    /**
     * Postupné odoberanie životov
     */
    public void uberZivot() {
        if (this.treti) {
            this.treti = false;
            this.zivot3.skry();
        } else if (!this.treti && this.druhy) {
            this.druhy = false;
            this.zivot2.skry();
        } else {
            this.prvy = false;
            this.zivot1.skry();
        }
    }
    
    /**
     * Getter na stav životov
     */
    public boolean masZivot() {
        return this.prvy;
    }
}
