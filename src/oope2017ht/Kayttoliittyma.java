package oope2017ht;

import apulaiset.*;
import tiedot.Hakemisto;

/**
 * Created by weppi on 31.3.2017.
 */
public class Kayttoliittyma extends Komentotulkki {

    // juurihakemiston '/' pois Tieto-kansion sallituista merkeistä

    private static final String ERROR = "Error!";
    private static final String EROTIN = "/";
    private static final String TERVETULOA = "Welcome to SOS.";
    private static final String KIRJOITA = ">";

    public void start() {
        String komento;
        boolean jatketaanko = true;

        Komentotulkki komentotulkki = new Komentotulkki();
        tulostaln(TERVETULOA);
        luoJuurihakemisto();

        // pyörittää ohjelmaa
        while (jatketaanko) {
            try {
                tulosta(annaPolku());
                annaSyote();
                komento = In.readString();
                jatketaanko = komentotulkki.paloittele(komento);
            } catch (IllegalArgumentException e) {
                tulostaln(ERROR + "Illegal");
            } catch (NullPointerException e) {
                tulostaln(ERROR + "NullPointer");
            } catch (Exception e) {
                tulostaln(ERROR);
            }
        }
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
