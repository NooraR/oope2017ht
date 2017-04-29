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

   /* Tiedosto tiedosto = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335932);
    Tiedosto kopio = new Tiedosto(tiedosto);
    // 24
    // grumpy_cat.jpeg 335932
    // grumpy_cat.jpeg 335932
    // true
    // 0
         System.out.println(tiedosto);
         System.out.println(kopio);
         System.out.println(tiedosto.equals(kopio));
         System.out.println(tiedosto.compareTo(kopio));*/


    public Tiedosto() {
        super();
        this.asetaKoko(0);
    }

    public Tiedosto(StringBuilder annettuNimi, int annettuKoko) throws IllegalArgumentException {
            super(annettuNimi);
            this.asetaKoko(annettuKoko);
    }

    // Kopiorakentaja

    public Tiedosto(Object t) {
        //if (t instanceof Tiedosto) {
            super((Tiedosto)t);
        //}
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

