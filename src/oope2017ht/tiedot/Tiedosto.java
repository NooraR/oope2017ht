package oope2017ht.tiedot;

/**
 * Tiedosto periytyy Tieto-luokasta. Tiedosto-olion attribuutti on koko (int).
 *
 * Noora Rintamäki (rintamaki.noora.m@student.uta.fi), Informaatiotieteiden yksikkö
 * (tietojenkäsittelytieteet), Tampereen yliopisto.
 *
 * Viimeksi muutettu 30.04.2017
 *
 */

public class Tiedosto extends Tieto {

    private static final String VALIMERKKI = " ";

    /**
     * Attribuutit
     */

    private int koko;

    /**
     * Rakentajat
     *
     */

    /**
     * Tiedoston parametriton rakentaja
     */
    public Tiedosto() {
        super();
        this.asetaKoko(0);
    }

    /**
     * Tiedoston parametrillinen rakentaja.
     *
     * @param annettuNimi
     * @param annettuKoko
     * @throws IllegalArgumentException
     */
    public Tiedosto(StringBuilder annettuNimi, int annettuKoko) throws IllegalArgumentException {
            super(annettuNimi);
            this.asetaKoko(annettuKoko);
    }

    /**
     * Kopiorakentaja
     */

    /**
     * Tiedoston parametrillinen kopiorakentaja
     *
     * @param t
     */
    public Tiedosto(Tiedosto t) {
          super(t);
          asetaKoko(t.annaKoko());
    }

    /**
     * Aksessorit
     *
     */

    /**
     * koko-attribuutin setteri
     *
     * @param annettuKoko
     * @throws IllegalArgumentException
     */
    public void asetaKoko(int annettuKoko) throws IllegalArgumentException {
        if (kokoOk(annettuKoko)) {
            koko = annettuKoko;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * koko-attribuutin getteri
     *
     * @return koko
     */
    public int annaKoko() {
        return koko;
    }

    /**
    * Metodit
    *
     */

    /**
     * koko-attribuutin tarkistus
     *
     * @param annettuKoko
     * @return true/false
     */
    private boolean kokoOk(int annettuKoko) {
        return annettuKoko >= 0;
    }

    /**
     * toString-metodin korvaus
     *
     * @return alkion nimi ja koko Styring-muotoisena
     */
    @Override
    public String toString() {
        return super.annaNimi() + VALIMERKKI + this.annaKoko();
    }
}