import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Random;
/**
 * Trieda z cvičenia, upravená
 */
public class Casovac implements ActionListener {
    private Timer timer;
    private long oldTick;    
    private static long TICK_LENGTH = 50000000;   
    
    //doplnené atribúty inštancie
    private ArrayList<Kvapka> kvapky; //zoznam kvapiek
    private Hra hra;
    private Narocnost narocnost; 
    private Platno platno;
    /**
     * Konštruktor vytvorí časovač pre danú hru.
     * @param hra aktuálna hra
     * @param volba náročnosť zvolená hráčom
     */
    public Casovac(Hra hra, Narocnost volba) {
        this.timer = new javax.swing.Timer(25, null);        
        this.timer.addActionListener(this);  
        this.oldTick = 0;
        this.timer.start();

        this.kvapky = new ArrayList<Kvapka>();
        this.hra = hra;
        this.narocnost = volba;
    }
    
    /**
     * počas plynutia času generuje kvapky na náhodnej X-ovej súradnici,
     * generuje ich náhodne čast podľa hračom zvolenej náročnosti
     * počas plynutia času kontroluje či háč chytil kvapku, 
     * ak áno pripíše mu skóre a kvapku vymaže
     * 
     * @param event 
     */
    public void actionPerformed(ActionEvent event) {
        long newTick = System.nanoTime();    
        if (newTick - this.oldTick >= Casovac.TICK_LENGTH || newTick < Casovac.TICK_LENGTH) {
            this.oldTick = (newTick / Casovac.TICK_LENGTH) * Casovac.TICK_LENGTH;
            
            this.padajte();
            Random r = new Random();
            if (r.nextDouble() < this.narocnost.getCislo(this.narocnost) ) {
                this.hra.novaKvapka(r.nextInt(320));
            }
            if (this.kvapky.size() > 0) {
                for (int i = 0; i < this.kvapky.size(); i++) {
                    if (this.hra.ciChytil(i)) {
                        this.hra.pripisSkore();
                        
                        this.zmazKvapku(this.kvapky.get(i));
                        //sťažovanie náročnosti
                        if (this.hra.getSkore() % 10 == 0) {
                            Kvapka.zrychli();
                        }
                    } 
                }
            }
        }
    } 
    
    
    /**
     * vymaže všetky kvapky zo zoznamu
     */
    public void zmazVsetkyKvapky() {
        while (this.kvapky.size() > 0) {
            this.kvapky.get(0).skry();
            this.zmazKvapku(this.kvapky.get(0));
        }
    }
    
    /**
     * vymaže danú kvapku zo zoznamu
     * @param k kvapka ktorú hráč chytil
     */
    public void zmazKvapku(Kvapka k) {
        this.kvapky.remove(k);
    }
    
    /**
     * @return vráti zoznam aktuálnych kvapiek
     */
    public ArrayList<Kvapka> getKvapky () {
        return this.kvapky;
    }
    
    /**
     * zastaví plynutie času
     */
    public void zastav () {
        this.timer.stop();
    }

    /**
     * pridá vytvorenô kvapku zo zozonamu
     */
    public void pridajKvapku(Kvapka k) {
        this.kvapky.add(k);
    }

    /**
     * prikáže všetkým kvapkam v zozname aby sa hýbali smerom dole
     */
    public void padajte() {
        for (int i = 0; i < this.kvapky.size(); i++) {
            this.kvapky.get(i).padaj();
        } 
    }

}
