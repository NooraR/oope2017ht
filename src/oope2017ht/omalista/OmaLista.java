package oope2017ht.omalista;

import apulaiset.Ooperoiva;
import fi.uta.csjola.oope.lista.LinkitettyLista;

/**
 * Linkitetty-luokasta periytyvä ja Ooperoiva-rajapinnan toteuttava OmaLista-luokka.
 *
 * Luokkaa käytetään Hakemisto-luokasta. Hakemisto-luokka luo olion OmaLista-luokasta
 * ja OmaLista-luokan metodeja käytetään olion hallinnoimiseen.
 *
 * Noora Rintamäki (rintamaki.noora.m@student.uta.fi), Informaatiotieteiden yksikkö
 * (tietojenkäsittelytieteet), Tampereen yliopisto.
 *
 * Versio: 1.1.
 *
 * Viimeksi muutettu 30.04.2017
 *
 */

public class OmaLista extends LinkitettyLista implements Ooperoiva {

    /** Hakee parametrina saadun olion ja palauttaa sen
     *
     * @param haettava
     * @return palautettava
     *
     */
    public Object hae(Object haettava) {
        Object palautettava = null;
        if (haettava != null) {
             for (int i = 0; i < koko; i++) {
                 if (haettava.equals(alkio(i))) {
                    palautettava = alkio(i);
                 }
             }
        }
        return palautettava;
    }

    /** Etsii String-parametrina saadun nimisen olion ja palauttaa sen
     *
     * @param haettava
     * @return palautettava
     *
     */
    public Object hae(String haettava) {
        Object palautettava = null;
        if (haettava != null) {
            for (int i = 0; i < koko; i++) {
                if (haettava.equals(alkio(i))) {
                    palautettava = alkio(i);
                }
            }

        }
        return palautettava;
    }

    /** Lisää parametrina saadun olion listaan ja palauttaa boolean-arvon
     * riippuen siitä onnistuiko olion lisäys
     *
     * @param uusi
     * @return boolean
     *
     */
    public boolean lisaa(Object uusi) {
        /** Tarkistaa, ettei parametrina saadun syötteen arvo ole null
         */
        if (uusi != null) {
            /** Käy hakemiston läpi ja palauttaa false jos löytää hakemistosta
             * samannimisen hakemiston
             */
            for (int k = 0; k < koko; k++) {
                if (uusi.toString().equals(alkio(k).toString())) {
                    return false;
                }
            }
            /** Jos hakemistossa on jo sisältö
             */
            int i = 0;
            if (koko() > 0) {
                while (i < koko()) {
                    /** Vertaa lisättävää oliota hakemistosta löytyviin alkioihin
                     */
                    String uudenNimi = uusi.toString().replace('.','a');
                    String vertailtavaNimi = alkio(i).toString().replace('.','a');
                    int j = uudenNimi.compareTo(vertailtavaNimi);
                    if (j > 0)
                        i++;
                    else if (j <= koko()) {
                        lisaa(i, uusi);
                        return true;
                    }
                    /** Jos muuttuja i on hakemiston sisällön lopussa, lisätään
                     * uusi olio hakemisto-listan loppuun
                     */
                    if (i == koko()) {
                        lisaaLoppuun(uusi);
                        return true;
                    }
                }
            }
            /** Jos hakemistossa ei ole vielä hakemistoja tai tiedostoja, lisätään annettu
             * oli alkuun
             */
            else {
                lisaaAlkuun(uusi);
                return true;
            }
        }
        return false;
    }

    /** Käy hakemiston läpi, etsii parametrina annetun olion ja poistaa sen
     *
     * @param poistettava
     * @return poista(i)
     *
     */
    public Object poista(Object poistettava) {
        for (int i = 0; i < koko; i++) {
            if (alkio(i).equals(poistettava)) {
                return poista(i);
            }
        }
        return null;
    }
}


