
/**
 * Enum Náročnosť obsahuje predvolené hodnoty pre náročnosť. extenzie triedy obsahujú
 * číslo pre časovač. 
 * 
 * @author (Rebeka Panáková) 
 * @version (28.12.2021)
 */
public enum Narocnost {
    //atribúty triedy
    LAHKA(0.035),
    STREDNA(0.07),
    TAZKA(0.1);
    
    //atribúty inštancie
    private double cislo;
    
    /**
     * @param cislo nastaví číslo pre jednotlivé objekty triedy
     */
    Narocnost(double cislo) {
        this.cislo = cislo;
    }
    
    /**
     * @param narocnost udá korému objektu chceme zistiť priradené číslo
     * @return cislo vráti číslo priradené danej náročnosti
     */
    public double getCislo(Narocnost narocnost) {
        return narocnost.cislo;
    }
}
