package oope2017ht;

import apulaiset.*;
import tiedot.Hakemisto;

/**
 * Created by weppi on 31.3.2017.
 */
public class Kayttoliittyma extends Komentotulkki {

    private static final String ERROR = "Error!";
    private static final String EROTIN = "/";
    private static final String TERVETULOA = "Welcome to SOS.";
    private static final String KIRJOITA = "/>";
    protected Hakemisto nykyinenHakemisto = null;
    protected StringBuilder polku;

    public void start() {
        String komento;
        boolean jatketaanko = true;
        String valmisKomento = "md /";
        polku = new StringBuilder();

        Komentotulkki komentotulkki = new Komentotulkki();
        tulosta(TERVETULOA);
        komentotulkki.paloittele(valmisKomento);

        // pyörittää ohjelmaa
        while (jatketaanko) {
            try {
                System.out.println(polku);
                tulosta("jaa-a");
                annaSyote();
                komento = In.readString();
                jatketaanko = komentotulkki.paloittele(komento);
            } catch (Exception e) {
                tulosta(ERROR);
            }
        }
    }

    public void asetaHakemisto(Hakemisto annettuHakemisto) {
        nykyinenHakemisto = annettuHakemisto;
    }

    public Hakemisto annaHakemisto() {
        return nykyinenHakemisto;
    }

    public void tulosta(String tulostettava) {
        System.out.println(tulostettava);
    }

    private void annaSyote() {
        System.out.print(KIRJOITA);
    }

    public void lisaaPolkuun(String lisays) {
        // ei pysty lisäämään Stringia
        StringBuilder lisaa = new StringBuilder(EROTIN + lisays);
        polku.append(lisaa);
    }
}
