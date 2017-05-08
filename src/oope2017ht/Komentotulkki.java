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
            Hakemisto hakemisto = new Hakemisto(new StringBuilder(parametrit[0]), nykyinenHakemisto);
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
                 nykyinenHakemisto = this.annaYlihakemisto();
                 paivitaPolku();
            }
            /** siirrytään parametrina annettuun hakemistoon
             */
            else if (parametrit.length > 1){
                 tulosta(parametrit[1]);
                 nykyinenHakemisto = (Hakemisto)nykyinenHakemisto.hae(parametrit[1]);
                 lisaaPolkuun(parametrit[1]);
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

            //  listaa hakemiston rekursiivisesti esijärjestyksessä

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
            /** nimeää tiedoston annetun nimiseksi uudeksi tiedostoksi, jos
             * nimellä löydetään tiedosto nykyhakemistosta ja hakemistossa ei ole
             * vielä uuden nimistä tiedostoa
             */
        }
        /** Lopettaa ohjelman
         */
        else if (parametrit[0].equals("exit")) {
            return false;
        }
        return true;
    }

    /** tulostaa parametrina annetun syötteen
     *
     * @param tulostettava
     */
    private void tulosta(String tulostettava) {
        System.out.println(tulostettava);
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
