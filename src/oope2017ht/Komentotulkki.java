package oope2017ht;

import fi.uta.csjola.oope.lista.LinkitettyLista;
import tiedot.*;

/**
 * Komentotulkki pahoittelee, tulkitsee ja toteuttaa parametrina saadun käskyn.
 *
 * Komentotulkki periytyy Hakemisto-luokasta ja luo ja/tai hallinnoi hakemistoja ja tiedostoja.
 *
 * Noora Rintamäki (rintamaki.noora.m@student.uta.fi), Informaatiotieteiden yksikkö
 * (tietojenkäsittelytieteet), Tampereen yliopisto.
 *
 * Viimeksi muutettu 30.04.2017
 *
 */

public class Komentotulkki extends Hakemisto {

    private static final String ERROR = "Error!";
    private static final String EROTIN = "/";
    private Hakemisto nykyinenHakemisto = null;
    private StringBuilder polku = new StringBuilder();
    private Hakemisto juurihakemisto;

    /** Paloittelee parametrina saadun syötteen yksittäisiksi
     * syötteiksi ja lähettää ne parametrina tulkitse-luokalle
     *
     * @param syote
     * @return tulkitse(parametrit)
     *
     */
    public boolean paloittele(String syote) {
        String[] parametrit;
        parametrit = syote.split(" ");
        return tulkitse(parametrit);
    }

    /** vastaanottaa parametrina String-muotoisen taulukon, tulkitsee
     * parametrien sisällön ja toteuttaa halutun komennon.
     *
     * @param parametrit
     * @return true/false
     *
     */
    private boolean tulkitse(String[] parametrit) {

        /** luodaan uusi Hakemisto, jonka nimeksi asetetaan
         * parametrina annetusta nimestä tehty StringBuilder-olio
         */
        if (parametrit[0].equals("md")) {
            nykyinenHakemisto.lisaa(new Hakemisto(new StringBuilder(parametrit[1]), nykyinenHakemisto));
        }

        /** Luodaan uusi Tiedosto, jonka nimeksi asetetaan parametrina
         * annetusta nimestä tehty StringBuilder-olio
         */
        else if (parametrit[0].equals("mf")) {
            Tiedosto tiedosto = new Tiedosto(new StringBuilder(parametrit[0]), Integer.parseInt(parametrit[1]));
        }

        /** siirrytään parametrien määrittämään tiedostoon
         */
        else if (parametrit[0].equals("cd")) {
            /** parametria ei ole, siirrytään juurihakemistoon
             */
             if (parametrit.length < 2) {
                 nykyinenHakemisto = juurihakemisto;
                 polku = new StringBuilder("/");
            }
            /** jos parametri on kaksi pistettä (..) siirrytään nykyisen
             * hakemiston ylihakemistoon
             */
            else if (parametrit[1].equals("..")) {
                 nykyinenHakemisto = nykyinenHakemisto.annaYlihakemisto();
                 paivitaPolku();
            }
            /** siirrytään parametrina annettuun hakemistoon
             */
            else if (parametrit.length > 1){
                if (nykyinenHakemisto.hae(parametrit[1]) != null) {
                    nykyinenHakemisto = (Hakemisto) nykyinenHakemisto.hae(parametrit[1]);
                    lisaaPolkuun(parametrit[1]);
                }
            }
        }

        /** Listaa parametrina saadun hakemiston sisällön. Jos käyttäjä
         * ei ole antanut parametria, listataa nykyisen hakemiston sisältö
         */
        else if (parametrit[0].equals("ls")) {
            /** Hakee parametrina saadun nimisen hakemiston ja tulostaa sen sisällön
             */
            if (parametrit.length > 1) {
                Hakemisto haettu = (Hakemisto) hae(parametrit[1]);
                tulostaSisalto(haettu.sisalto());
            }
            /** tulostaa nykyisen hakemiston sisällön
             */
            else {
                tulostaSisalto(nykyinenHakemisto.sisalto());
            }
        }

        /** listaa hakemiston rekursiivisesti esittämisjärjestyksessä
         */
        else if (parametrit[0].equals("find")) {
            for (int i = 0; i < juurihakemisto.sisalto().koko(); i++) {
                tulostaln(juurihakemisto.sisalto().alkio(i));
                kayLapi((Hakemisto)juurihakemisto.sisalto().alkio(i));
            }
        }

        /** Poistaa parametrina annetun nimisen tiedoston
         */
        else if (parametrit[0].equals("rm")) {
            poista(parametrit[1]);
        }

        /** Luo kopion parametrina annetusta Tiedostosta
         */
        else if (parametrit[0].equals("cp")) {
            Tiedosto tiedosto = new Tiedosto((Tiedosto)hae(parametrit[1]));
        }

        /** Uudelleennimeää annetun nimisen tiedoston parametrina annetulla nimellä
         */
        else if (parametrit[0].equals("mv")) {
            /** nimeää tiedoston annetun nimiseksi tiedostoksi, jos
             * nimellä löydetään tiedosto nykyhakemistosta ja hakemistossa ei ole
             * vielä uuden nimistä tiedostoa
             */
            if (hae(parametrit[1]) instanceof Tiedosto) {
                hae(parametrit[1]).asetaNimi(new StringBuilder(parametrit[2]));
            }
        }

        /** Lopettaa ohjelman
         */
        else if (parametrit[0].equals("exit")) {
            return false;
        }
        return true;
    }

    /**
     * Käy läpi parametrina annetun Hakemiston sisällön tulostaen sen ja käyden läpi myös Hakemistojen
     * alihakemistot.
     *
     * @param hakemisto
     */
    private void kayLapi(Hakemisto hakemisto) {
        for (int i = 0; i < hakemisto.sisalto().koko(); i++) {
            tulosta(tulostaHakemistopolku(hakemisto.sisalto().alkio(i)));
            tulostaln(hakemisto.sisalto().alkio(i).toString());
            if (hakemisto.sisalto().alkio(i) instanceof Hakemisto) {
               kayLapi((Hakemisto)hakemisto.sisalto().alkio(i));
            }
        }
    }

    /**
     * Jos parametri ei ole null, metodi hakee parametrin ylähakemistoja aina juurihakemistoon asti
     * ja lisää hakemistojen nimet ketjuksi hakemistopolku-nimiseen muuttujaan, jonka metodi palauttaa.
     *
     * @param viimeinen
     * @return String-tyyppinen hakemistopolku-muuttuja
     */
    private String tulostaHakemistopolku(Object viimeinen) {
        String hakemistopolku = "";
        while (viimeinen != null) {
            viimeinen = ((Hakemisto)viimeinen).annaYlihakemisto();
            if (viimeinen.equals(juurihakemisto)) {
                break;
            }
            else {
                hakemistopolku = ((Tieto) viimeinen).annaNimi().toString() + EROTIN + hakemistopolku;
            }
        }
        return hakemistopolku;
    }

    /** tulostaa parametrina annetun syötteen
     *
     * @param tulostettava
     */
    private void tulosta(Object tulostettava) {
        System.out.print(tulostettava);
    }

    /** tulostaa parametrina annetun syötteen ja rivinvaihdon
     *
     * @param tulostettava
     */
    private void tulostaln(Object tulostettava) {
        System.out.println(tulostettava);
    }

    /** Päivittää polun niin, että uusi nykyinenHakemisto on
     * polun viimeinen hakemisto
     */
    private void paivitaPolku() {
        /** Esittelee String-muotoisen hakemistot-taulukon
         */
        String[] hakemistot;
        /** paloittelee polun yksittäisiin hakemistoihin
         */
        hakemistot = polku.toString().split("/");

        int i = hakemistot.length;
        /** poistaa hakemistoja polusta, kunnes nykyinenHakemisto on
         * polun viimeinen hakemisto
         */
        while (!(hakemistot[i].equals(nykyinenHakemisto))) {
            hakemistot[i] = null;
            i--;
        }
   }

   /** tulostaa parametrina saadun LinkitettyLista-sisällön
    *
    * @param lista
    */
    private void tulostaSisalto(LinkitettyLista lista) {
        /** tarkistaa, että parametrina saatu lista ei ole null-arvoinen
         */
        if (lista != null) {
            /** Käy läpi listaa ja tulostaa sen alkiot
             */
            for (int i = 0; i < lista.koko(); i++) {
                System.out.print(lista.alkio(i));
            }
        }
    }

    /** lisää polkuun saadun parametrin
     *
     * @param lisays
     */
    private void lisaaPolkuun(String lisays) {
        /** jos kyseessä on juurihakemiston symboli
         */
        if (lisays.equals("/")) {
            polku.append(lisays);
        }
        /** lisätessä muuta kuin juurihakemiston symbolia
         */
        else {
            polku.append(lisays + EROTIN);
        }
    }

    /** Palauttaa StringBuilder-tyyppisen polun
     *
     * @return polku
     */
    public StringBuilder annaPolku() {
        return polku;
    }

    /** luo juurihakemiston
     */
    public void luoJuurihakemisto() {
        nykyinenHakemisto = new Hakemisto(new StringBuilder("juuri"), null);
        juurihakemisto = nykyinenHakemisto;
        lisaaPolkuun("/");
    }
}
