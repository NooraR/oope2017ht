package oope2017ht.tiedot;

/**
 * Abstracti luokka Tieto joka toteuttaa Comparable<T>-rajapinnan. Luokan attribuutti on
 * StringBuilder-tyyppinen nimi.
 *
 * Noora Rintamäki (rintamaki.noora.m@student.uta.fi), Informaatiotieteiden yksikkö
 * (tietojenkäsittelytieteet), Tampereen yliopisto.
 *
 * Viimeksi muutettu 30.04.2017
 *
 */

public abstract class Tieto implements Comparable<Tieto> {

    /**
     * Attribuuttit
     *
     */

    /**
     * tiedoston tai hakemiston nimi
     */

    private StringBuilder nimi;

    /**
     * Rakentajat
     *
     */

    /**
     * Parametriton rakentaja
     */
    public Tieto() {
        asetaNimi(null);
    }

    /**
     * Parametrillinen rakentaja
     *
     * @param annettuNimi
     * @throws IllegalArgumentException
     */
    public Tieto(StringBuilder annettuNimi) throws IllegalArgumentException {
        /**
         * parametrin tarkistus
         */
        if (nimiOk(annettuNimi)) {
            nimi = annettuNimi;
        }
    }

    /**
     * Kopiorakentaja
     *
     */

    /**
     * Tieto-luokan kopiorakentaja
     *
     * @param t
     */
    public Tieto(Tieto t) {
        if (t instanceof Tieto) {
            asetaNimi(new StringBuilder(t.annaNimi()));
        }
    }

    /**
     * Aksessorit
     *
     */

    /**
     * nimi-attribuutin setteri
     *
     * @param annettuNimi
     * @throws IllegalArgumentException
     */
    public void asetaNimi(StringBuilder annettuNimi) throws IllegalArgumentException {
        this.nimi = annettuNimi;
    }

    /**
     * nimi-attriuutin getteri
     *
     * @return StringBuilder-tyyppinen nimi
     */
    public StringBuilder annaNimi() {
        return this.nimi;
    }

    /**
     * Metodit
     *
     */

    /**
     * nimi-attribuutin tarkistus
     *
     * @param annettuNimi
     * @return true/false
     */
    public boolean nimiOk(StringBuilder annettuNimi) {
        String tarkasteltava = annettuNimi.toString();
        boolean onOk = true;
        int pisteet = 0;
        /**
         * parametrin sisältämien merkkien tarkistus
         */
        for (int i = 0; i < tarkasteltava.length(); i++) {
            if (tarkasteltava.charAt(i) >= 'a' && tarkasteltava.charAt(i) <= 'z'
                    || tarkasteltava.charAt(i) >= 'A' && tarkasteltava.charAt(i) <= 'Z'
                    || tarkasteltava.charAt(i) >= '0' && tarkasteltava.charAt(i) <= '9'
                    || tarkasteltava.charAt(i) == '_' || tarkasteltava.charAt(i) == '.') {
                /**
                 * Tarkastaa annetun parametrin pisteiden määrän.
                 */
                if (tarkasteltava.charAt(i) == '.' && pisteet == 1) {
                    return false;
                }
                else if (tarkasteltava.charAt(i) == '.')
                    pisteet++;
            }
            else {
                return false;
            }
        }
        return onOk;
    }

    /**
     * toString-metodin korvaus
     *
     * @return alkion nimi String-muotoisena
     */
    @Override
    public String toString() {
       return this.nimi.toString();
    }

    /**
     * equals-metodin uudelleenkirjoitus
     *
     * @param o
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Tieto) {
            if (this.annaNimi().toString().equals(((Tieto) o).annaNimi().toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * uudelleenkirjoittaa Comparaple-rajapinnan compareTo-metodin
     *
     * @param t
     * @return 1 tai 0
     */
    @Override
    public int compareTo(Tieto t) {
        return this.annaNimi().toString().compareTo(t.annaNimi().toString());
    }
}
