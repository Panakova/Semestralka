/**
 * Trieda Main spustí aplikáciu
 * 
 * @author (Rebeka Panáková) 
 * @version (18.12.2021)
 */
public abstract class Main {
    //metódy triedy
    /**
     * metóda main vytvorí jedinú inštanciu triedy Hra a spustí ju
     */
    public static void main(String[] args) {
        Hra paramHra = Hra.zacniHru();
        paramHra.spusti();
    }
}
