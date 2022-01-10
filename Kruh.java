import java.awt.geom.Ellipse2D;

/**
 * Trieda z cvičení
 * Kruh, s ktorým možno pohybovať a nakreslí sa na plátno.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Kruh {
    private int priemer;
    private int lavyHornyX;
    private int lavyHornyY;
    private String farba;
    private boolean jeViditelny;
    
    /**
     * Vytvor nový kruh preddefinovanej farby na preddefinovanej pozícii. 
     */
    public Kruh(int priemer, int x , int y) {
        this.priemer = priemer;
        this.lavyHornyX = x;
        this.lavyHornyY = y;
        this.farba = "blue";
        this.jeViditelny = false;
    }
    
    /**
     * (Kruh) Posuň sa zvisle o dĺžku danú parametrom. 
     */
    public void posunZvisle(int vzdialenost) {
        this.zmaz();
        this.lavyHornyY += vzdialenost;
        this.nakresli();
    }
    
    /**
     * (Kruh) Zobraz sa.
     */
    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }
    
    /**
     * (Kruh) Skry sa.
     */
    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }

    /*
     * Draw the circle with current specifications on screen.
     */
    private void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.draw(this, this.farba, new Ellipse2D.Double(this.lavyHornyX, this.lavyHornyY, 
                                                          this.priemer, this.priemer));
        }
    }

    /*
     * Erase the circle on screen.
     */
    private void zmaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.erase(this);
        }
    }
}
