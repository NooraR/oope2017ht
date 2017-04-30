package tiedot;


/**
 * Created by weppi on 31.3.2017.
 */
public class Tiedosto extends Tieto {

    private static final String VALIMERKKI = " ";

    /*
    * Attribuutit
     */

    private int koko;

    /*
    * Rakentajat
    *
     */

    public Tiedosto() {
        super();
        this.asetaKoko(0);
    }

    public Tiedosto(StringBuilder annettuNimi, int annettuKoko) throws IllegalArgumentException {
            super(annettuNimi);
            this.asetaKoko(annettuKoko);
    }

    // Kopiorakentaja

    public Tiedosto(Tiedosto t) {
          super(t);
          asetaKoko(t.annaKoko());
    }

    /*
    * Aksessorit
    *
     */

    public void asetaKoko(int annettuKoko) throws IllegalArgumentException {
        if (kokoOk(annettuKoko)) {
            koko = annettuKoko;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int annaKoko() {
        return koko;
    }

    /*
    * Metodit
    *
     */

    private boolean kokoOk(int annettuKoko) {
        return annettuKoko >= 0;
    }

    public String toString() {
        return super.annaNimi() + VALIMERKKI + this.annaKoko();
    }

}

