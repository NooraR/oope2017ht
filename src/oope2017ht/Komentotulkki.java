package oope2017ht;


import fi.uta.csjola.oope.lista.LinkitettyLista;
import tiedot.*;

/**
 * Created by weppi on 31.3.2017.
 */
public class Komentotulkki extends Hakemisto {

    private static final String ERROR = "Error!";
    private static final String EROTIN = "/";
    private Hakemisto nykyinenHakemisto;
    private StringBuilder polku = new StringBuilder();
    private Hakemisto juurihakemisto;

    // luo juurihakemiston
    public boolean luoJuurihakemisto() {
        nykyinenHakemisto = new Hakemisto(new StringBuilder("juuri"), null);
        juurihakemisto = nykyinenHakemisto;
        lisaaPolkuun("/");
        return true;
    }

    // pilkkoo syötteen osiin ja lähettää eteenpäin tulkittavaksi
    public boolean paloittele(String syote) {
        String[] parametrit;
        parametrit = syote.split(" ");
        return tulkitse(parametrit);
    }

    // erittelee mitä komento pitää sisällään
    private boolean tulkitse(String[] parametrit) {
        // luo uuden hakemiston
        if (parametrit[0].equals("md")) {
            // nykyinen hakemisto toiseksi parametriksi
            nykyinenHakemisto.lisaa(new Hakemisto(new StringBuilder(parametrit[1]), nykyinenHakemisto));
        }
        // luo uuden tiedoston
        else if (parametrit[0].equals("mf")) {
            if (parametrit.length > 2) {
                nykyinenHakemisto.lisaa(new Tiedosto(new StringBuilder(parametrit[1]), Integer.parseInt(parametrit[2])));
            } else {
                tulostaln("ei parametreja");
            }
        }
        // siirtyy haluttuun hakemistoon
        else if (parametrit[0].equals("cd")) {
            // siirtyy juurihakemistoon
             if (parametrit.length < 2) {
                 nykyinenHakemisto = juurihakemisto;
                 polku = new StringBuilder("/");
            }
            // siirtyy nykyisen hakemiston ylihakemistoon
            else if (parametrit[1].equals("..")) {
                 nykyinenHakemisto = nykyinenHakemisto.annaYlihakemisto();
                 tulostaln(nykyinenHakemisto);
                 paivitaPolku();
            }
            // siirtyy parametrina annettuun hakemistoon
            else if (parametrit.length > 1){
                 if (nykyinenHakemisto.hae(parametrit[1]) != null) {
                     // aiheuttaa nullPointerin
                     nykyinenHakemisto = (Hakemisto) nykyinenHakemisto.hae(parametrit[1]);
                     lisaaPolkuun(parametrit[1]);
                 }
            }
            // virheellinen
            else {
                tulosta("Ei mennyt mihinkaan");
            }
        }
        // listaa halutun hakemiston alihakemistot ja tiedostot
        else if (parametrit[0].equals("ls")) {
            // listaa parametrina annetun hakemiston alihakemistot ja tiedostot
            if (parametrit.length > 1) {
                Hakemisto haettu = (Hakemisto) hae(parametrit[1]);
                tulostaSisalto(haettu.sisalto());
                // hae parametrin nimisen tiedoston kaikki tiedostot ja alihakemistot listana
            }
            // listaa nykyisen hakemiston alihakemistot ja tiedostot
            else {
                if (nykyinenHakemisto != null) {
                    // aiheuttaa NullPointerin
                    tulostaSisalto(nykyinenHakemisto.sisalto());
                    // listaa tämän hakemiston tiedostot ja alihakemistot
                } else {
                    tulosta("nykyH == null");
                }
            }
        }
        //  listaa hakemiston rekursiivisesti esijärjestyksessä
        else if (parametrit[0].equals("find")) {

        }
        // poistaa parametrina annetun hakemiston tai tiedoston
        else if (parametrit[0].equals("rm")) {
            poista(parametrit[1]);
        }
        // luo kopion annetusta tiedostosta
        else if (parametrit[0].equals("cp")) {
            nykyinenHakemisto.lisaa(new Tiedosto(new StringBuilder(parametrit[2]),((Tiedosto)nykyinenHakemisto.hae(parametrit[1])).annaKoko()));
        }
        // muuttaa tiedoston nimen annetuksi jos kyseinen
        else if (parametrit[0].equals("mv")) {
            if (parametrit.length > 2) {
                if (nykyinenHakemisto.hae(parametrit[1]) != null) {
                    nykyinenHakemisto.hae(parametrit[1]).asetaNimi(new StringBuilder(parametrit[2]));
                }
            } else {
                tulosta("parametri puuttuu");
            }
        }
        // poistuu ohjelmasta
        else if (parametrit[0].equals("exit")) {
            return false;
        }
        // ei pysty tulkitsemaan komentoa
        else {
            tulostaln(ERROR + "Viimeisesta");
        }
        return true;
    }

    /* tulostaa annetun syötteen ilman rivinvaihtoa */
    private void tulosta(String tulostettava) {
        System.out.print(tulostettava);
    }

    /* tulostaa annetun syötteen rivinvaihdolla */
    private void tulostaln(Object tulostettava) {
        System.out.println(tulostettava);
    }

    /* päivittää hakemistopolun ajantasalle */
    private void paivitaPolku() {
        String[] hakemistot;
        hakemistot = polku.toString().split("/");
        /*vertaillaan hakemistopolkua nykyiseen hakemistoon kunnes hakemistopolun viimeinen hakemisto on
        nykyinen hakemisto */
        tulostaln(hakemistot[0]);
        tulostaln(nykyinenHakemisto);
        int i;
        for (i = 0; i < hakemistot.length; i++) {
            if (hakemistot[i].equals(nykyinenHakemisto)) {
                break;
            }
        }
        StringBuilder uusiPolku = new StringBuilder("");
        /* uusi polku luodaan lisäämällä siihen hakemistoja kunnes viimeinen hakemisto on nykyhakemisto*/
        for(int j = 0; j < i; j++) {
            uusiPolku.append(hakemistot[j] + EROTIN);
        }
        /* asetetaan uusiPolku hakemistopoluksi */
        polku = uusiPolku;
   }

   /* tulostaa LinkitettyLista-muotoisen sisällön */
   private static void tulostaSisalto(LinkitettyLista lista) {
       if (lista != null) {
           for (int i = 0; i < lista.koko(); i++) {
               System.out.println(lista.alkio(i));
           }
       }
   }

    /* lisää hakemistopolkuun uuden hakemiston */
    private void lisaaPolkuun(String lisays) {
        if (lisays.equals("/")) {
            polku.append(lisays);
        } else {
            polku.append(lisays + EROTIN);
        }
    }

    // palauttaa hakemistopolun
    public StringBuilder annaPolku() {
        return polku;
    }

    // asettaa annetun hakemiston nykyiseksi hakemistoksi
    public void asetaHakemisto(Hakemisto annettuHakemisto) {
        nykyinenHakemisto = annettuHakemisto;
    }

    // palauttaa nykyisen hakemiston
    public Hakemisto annaHakemisto() {
        return nykyinenHakemisto;
    }
}