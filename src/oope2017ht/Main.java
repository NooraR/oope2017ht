package oope2017ht;

/**
 * Pitää sisällää Main-metodin.
 *
 * Noora Rintamäki (rintamaki.noora.m@student.uta.fi), Informaatiotieteiden yksikkö
 * (tietojenkäsittelytieteet), Tampereen yliopisto.
 *
 * Viimeksi muutettu 08.05.2017
 *
 */

public class Main extends Kayttoliittyma {
    public static void main(String[] args) {
        /** Luo Kayttoliittyma-olion
         */
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        /** Käynnistää ohjelman
         */
        kayttoliittyma.start();
    }
}
