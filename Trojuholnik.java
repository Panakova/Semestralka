import java.awt.Polygon;

/**
 * Trieda z cvičení
 * Rovnoramenný trojuholník, s ktorým možno pohybovať a nakreslí sa na plátno.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Trojuholnik {
    private int vyska;
    private int zakladna;
    private int lavyHornyX; // lavy horny bod oblasti
    private int lavyHornyY; // lavy horny bod oblasti
    private String farba;
    private boolean jeViditelny;

    /**
     * Vytvor nový rovnoramenný trojuholník preddefinovanej farby na preddefinovanej pozícii.
     */
    public Trojuholnik(int vyska, int zakladna, int x, int y) {
        this.vyska = vyska;
        this.zakladna = zakladna;
        this.lavyHornyX = x;
        this.lavyHornyY = y;
        this.farba = "blue";
        this.jeViditelny = false;
    }
    
    /**
     * (Trujuholník) Posuň sa zvisle o dĺžku danú parametrom.
     */
    public void posunZvisle(int vzdialenost) {
        this.zmaz();
        this.lavyHornyY += vzdialenost;
        this.nakresli();
    }
    
    /**
     * (Trujuholník) Zobraz sa.
     */
    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }
    
    /**
     * (Trujuholník) Skry sa.
     */
    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }

    /**
     * (Trujuholník) Zmeň farbu na hodnotu danú parametrom.
     * Nazov farby musí byť po anglicky.
     * Možné farby sú tieto:
     * červená - "red"
     * žltá    - "yellow"
     * modrá   - "blue"
     * zelená  - "green"
     * fialová - "magenta"
     * čierna  - "black"
     */
    public void zmenFarbu(String farba) {
        this.farba = farba;
        this.nakresli();
    }

    /*
     * Draw the triangle with current specifications on screen.
     */
    private void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            int[] xpoints = { this.lavyHornyX, this.lavyHornyX + (this.zakladna / 2), this.lavyHornyX - (this.zakladna / 2) };
            int[] ypoints = { this.lavyHornyY, this.lavyHornyY + this.vyska, this.lavyHornyY + this.vyska };
            canvas.draw(this, this.farba, new Polygon(xpoints, ypoints, 3));
        }
    }

    /*
     * Erase the triangle on screen.
     */
    private void zmaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.erase(this);
        }
    }
}
