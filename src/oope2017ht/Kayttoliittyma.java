package oope2017ht;

import apulaiset.*;
import tiedot.Hakemisto;

/**
 * Created by weppi on 31.3.2017.
 */
public class Kayttoliittyma extends Komentotulkki {

    // juurihakemiston '/' pois Tieto-kansion sallituista merkeistä

    private static final String ERROR = "Error!";
    private static final String TERVETULOA = "Welcome to SOS.";
    private static final String KIRJOITA = ">";
    private static final String LOPETUS = "Shell terminated.";
    private Komentotulkki komentotulkki = new Komentotulkki();


    public void start() {
        String komento;
        boolean jatketaanko = true;

        tulostaln(TERVETULOA);
        komentotulkki.luoJuurihakemisto();

        // pyörittää ohjelmaa
        while (jatketaanko) {
            try {
                tulosta(komentotulkki.annaPolku());
                annaSyote();
                komento = In.readString();
                jatketaanko = komentotulkki.paloittele(komento);
            } catch (IllegalArgumentException e) {
                tulostaln(ERROR + "Illegal");
            } catch (NullPointerException e) {
                tulostaln(ERROR + "NullPointer");
            } /*catch (Exception e) {
                tulostaln(ERROR);
            }*/
        }
        tulostaln(LOPETUS);
    }

    public void tulosta(Object tulostettava) {
        System.out.print(tulostettava);
    }

    public void tulostaln(Object tulostettava) {
        System.out.println(tulostettava);
    }

    private void annaSyote() {
        System.out.print(KIRJOITA);
    }
}
