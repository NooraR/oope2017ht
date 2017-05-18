package oope2017ht;

import fi.uta.csjola.oope.lista.LinkitettyLista;
import oope2017ht.tiedot.*;

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
 *
 * KORJAA FIND ASSIGMENT ERRORS
 *
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
        String komento = parametrit[0];

        switch (komento) {
            case "md":
                md(parametrit);
                break;
            case "mf":
                mf(parametrit);
                break;
            case "cd":
                cd(parametrit);
                break;
            case "ls":
                ls(parametrit);
                break;
            case "find":
                find(parametrit);
                break;
            case "rm":
                rm(parametrit);
                break;
            case "cp":
                cp(parametrit);
                break;
            case "mv":
                mv(parametrit);
                break;
            /**
             * lopettaa ohjelman
             */
            case "exit":
                return false;
            default:
                tulostaln(ERROR);
        }

        /**
         * Jos komento on virheellinen, tulostaa ERROR:in.
         */

        return true;
    }


        /** luodaan uusi Hakemisto, jonka nimeksi asetetaan
         * parametrina annetusta nimestä tehty StringBuilder-olio
         */
    private void md(String[] parametrit) {
            if (parametrit.length > 1 && parametrit.length < 3
                && nykyinenHakemisto.nimiOk(new StringBuilder(parametrit[1]))) {
                Hakemisto apu = nykyinenHakemisto;
                nykyinenHakemisto.lisaa(new Hakemisto(new StringBuilder(parametrit[1]), nykyinenHakemisto));
                asetaYlihakemisto(apu);
            }
            else {
                tulostaln(ERROR);
            }
        }

        /** Luodaan uusi Tiedosto, jonka nimeksi asetetaan parametrina
         * annetusta nimestä tehty StringBuilder-olio
         */
        private void mf(String[] parametrit) {
            if (parametrit.length > 2 && parametrit.length < 4) {
                int apu = muutaNumeroksi(parametrit[2]);
                if (nykyinenHakemisto.nimiOk(new StringBuilder(parametrit[1])) && apu >= 0) {
                    nykyinenHakemisto.lisaa(new Tiedosto(new StringBuilder(parametrit[1]), apu));
                } else {
                    tulostaln(ERROR);
                }
            } else {
                tulostaln(ERROR);
            }
        }

        /** siirrytään parametrien määrittämään tiedostoon
         */
        private void cd(String[] parametrit) {
            /** parametria ei ole, siirrytään juurihakemistoon
             */
             if (parametrit.length < 2) {
                 if (nykyinenHakemisto.equals(juurihakemisto))
                     tulostaln(ERROR);
                 nykyinenHakemisto = juurihakemisto;
                 polku.setLength(0);
                 polku.append("/");
            }
            /** jos parametri on kaksi pistettä (..) siirrytään nykyisen
             * hakemiston ylihakemistoon
             */
            else if (parametrit[1].equals("..")) {
                 if (nykyinenHakemisto.equals(juurihakemisto)) {
                     tulostaln(ERROR);
                 }
                 else {
                     nykyinenHakemisto = nykyinenHakemisto.annaYlihakemisto();
                     paivitaPolku();
                 }
            }
            /** siirrytään parametrina annettuun hakemistoon
             */
            else if (parametrit.length > 1 && parametrit.length < 3){
                if (nykyinenHakemisto.hae(parametrit[1]) != null) {
                    nykyinenHakemisto = (Hakemisto) nykyinenHakemisto.hae(parametrit[1]);
                    lisaaPolkuun(parametrit[1]);
                }
                else {
                    tulostaln(ERROR);
                }
            }
            else {
                tulostaln(ERROR);
            }
        }

        /**
         * Listaa parametrina saadun hakemiston sisällön. Jos käyttäjä
         * ei ole antanut parametria, listataa nykyisen hakemiston sisältö
         */
        private void ls(String[] parametrit) {
            /**
             * Hakee parametrina saadun nimisen hakemiston ja tulostaa sen sisällön
             */
            if (parametrit.length > 1 && parametrit.length < 3
                    && nykyinenHakemisto.hae(parametrit[1]) != null) {
                tulostaln(nykyinenHakemisto.hae(parametrit[1]));
            }
            /** tulostaa nykyisen hakemiston sisällön
             */
            else if (parametrit.length < 2) {
                tulostaSisalto(nykyinenHakemisto.sisalto());
            } else {
                tulostaln(ERROR);
            }
        }

        /** listaa hakemiston rekursiivisesti esittämisjärjestyksessä
         */
        private void find(String[] parametrit) {
            if (parametrit.length < 2) {
                kayLapi(nykyinenHakemisto);
            }
            else {
                tulostaln(ERROR);
            }
        }

        /** Poistaa parametrina annetun nimisen tiedoston
         */
        private void rm(String[] parametrit) {
            if (parametrit.length > 1 && parametrit.length < 3 && nykyinenHakemisto.hae(parametrit[1]) != null)
                nykyinenHakemisto.poista(parametrit[1]);
            else
                tulostaln(ERROR);
        }

        /** Luo kopion parametrina annetusta Tiedostosta
         */
        private void cp(String[] parametrit) {
            /**
             * Tarkistaa, että hakemistosta löytyy kopioitava tiedosto ja että hakemistosta ei löydy uuden nimistä
             * tiedostoa.
             */
            if (nykyinenHakemisto.hae(parametrit[1]) != null
                    && nykyinenHakemisto.hae(parametrit[1]) instanceof Tiedosto
                    && nykyinenHakemisto.hae(parametrit[2]) == null) {
                int kopioKoko = ((Tiedosto) nykyinenHakemisto.hae(parametrit[1])).annaKoko();
                nykyinenHakemisto.lisaa(new Tiedosto(new StringBuilder(parametrit[2]), kopioKoko));
            }
            else {
                tulostaln(ERROR);
            }
        }

        /** Uudelleennimeää annetun nimisen tiedoston parametrina annetulla nimellä
         */
        private void mv(String[] parametrit) {
            /** nimeää tiedoston annetun nimiseksi tiedostoksi, jos
             * nimellä löydetään tiedosto nykyhakemistosta ja hakemistossa ei ole
             * vielä uuden nimistä tiedostoa
             */
            if (nykyinenHakemisto.hae(parametrit[1]) instanceof Tiedosto
                && nykyinenHakemisto.hae(parametrit[2]) == null
                && nykyinenHakemisto.nimiOk(new StringBuilder(parametrit[1]))
                && ((Tiedosto) nykyinenHakemisto.hae(parametrit[1])).annaKoko() >= 0) {
                    nykyinenHakemisto.lisaa(new Tiedosto((new StringBuilder(parametrit[2]))
                        ,((Tiedosto) nykyinenHakemisto.hae(parametrit[1])).annaKoko()));
                    parametrit[2] = parametrit[1];
                    rm(parametrit);
            }
            else {
                tulostaln(ERROR);
            }
        }

    /**
     * Muuttaa String-tyyppisen muuttajan vastaavaksi Integer-tyyppiseksi muuttujaksi.
     *
     * @param numero
     * @return
     */

    private int muutaNumeroksi(String numero) {
        for (int i = 0; i < numero.length(); i++) {
            if (numero.charAt(i) >= '0' && numero.charAt(i) <= '9') {

            } else {
                return -1;
            }
        }
        return Integer.parseInt(numero);
    }

    /**
     * Käy läpi parametrina annetun Hakemiston sisällön tulostaen sen ja käyden läpi myös Hakemistojen
     * alihakemistot.
     *
     * @param hakemisto
     */
    private void kayLapi(Hakemisto hakemisto) {
        for (int i = 0; i < hakemisto.sisalto().koko(); i++) {
            tulosta(tulostaHakemistopolku(hakemisto));
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
     * @param hakemisto
     * @return String-tyyppinen hakemistopolku-muuttuja
     */
    private String tulostaHakemistopolku(Hakemisto hakemisto) {
        String hakemistopolku = "";
        while (hakemisto != null) {
            if (hakemisto.equals(juurihakemisto)) {
                break;
            }
            else {
                hakemistopolku = hakemisto.annaNimi().toString() + EROTIN + hakemistopolku;
            }
            hakemisto = hakemisto.annaYlihakemisto();
        }
        hakemistopolku = EROTIN + hakemistopolku;
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

        if (hakemistot.length < 3) {
            polku.setLength(0);
            polku.append("/");
        }
        else {
            int i = hakemistot.length -1;
            /** poistaa hakemistoja polusta, kunnes nykyinenHakemisto on
             * polun viimeinen hakemisto
             */
            while (!(hakemistot[i].equals((nykyinenHakemisto).annaNimi().toString()))) {
                hakemistot[i] = null;
                i--;
            }

            polku.setLength(1);

            for (int j = hakemistot.length -1; j > 0; j--) {
                if (hakemistot[j] != null)
                    polku.append(hakemistot[j] + EROTIN);
            }
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
                tulostaln(lista.alkio(i));
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
