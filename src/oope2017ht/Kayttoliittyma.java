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
        String valmisKomento1 = "md /";
        String valmiskomento2 = "cd /";


        Komentotulkki komentotulkki = new Komentotulkki();
        //Komentotulkki komentotulkki = new Komentotulkki(this);
        tulosta(TERVETULOA);
        komentotulkki.paloittele(valmisKomento1);
        komentotulkki.paloittele(valmiskomento2);

        // pyörittää ohjelmaa
        while (jatketaanko) {
            try {
                tulosta(annaPolku());
                annaSyote();
                komento = In.readString();
                jatketaanko = komentotulkki.paloittele(komento);
            } catch (Exception e) {
                tulosta(ERROR);
            }
        }
    }

    public void tulosta(Object tulostettava) {
        System.out.println(tulostettava);
    }

    private void annaSyote() {
        System.out.print(KIRJOITA);
    }


}
