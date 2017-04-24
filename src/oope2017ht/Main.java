// OmaLista-luokan oletetaan olevan oope2017ht.omalista-pakkauksessa.
package oope2017ht;

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
        kayttoliittyma.start();
    }
}
