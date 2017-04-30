// OmaLista-luokan oletetaan olevan oope2017ht.omalista-pakkauksessa.
package oope2017ht;

import tiedot.*;

// Otetaan käyttöön In-luokka.

/*
 * Harjoitustyö.
 *
 * Olio-ohjelmoinnin perusteet, kevät 2017, Jorma Laurikkala, jorma.laurikkala@uta.fi.
 *
 * Omaa listaa testaava luokka, jota EI OLE SYYTÄ MUUTTAA MILLÄÄN TAVALLA,
 * koska WETO käyttää aina tämän luokan alkuperäistä versiota.
 *
 * Testit ovat main-metodissa. WETO käy läpi testit alla annetussa järjestyksessä.
 *
 * Aja luokka hakemistossa, jonka välittöminä alihakemistoina ovat oope2017ht-
 * apulaiset- ja lista-pakkausten hakemistot.
 *
 */

public class Main extends Kayttoliittyma {
    public static void main(String[] args) {
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();

        Tiedosto tiedosto = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335932);
        Tiedosto kopio = new Tiedosto(tiedosto);
        // 24
        // grumpy_cat.jpeg 335932
        // grumpy_cat.jpeg 335932
        // true
        // 0
        System.out.println(tiedosto);
        System.out.println(kopio);
        System.out.println(tiedosto.equals(kopio));
        System.out.println(tiedosto.compareTo(kopio));

        //kayttoliittyma.start();
    }
}
