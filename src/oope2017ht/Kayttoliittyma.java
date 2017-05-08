package oope2017ht;

import apulaiset.In;

/*
 * Käyttöliittymä-luokka pyörittää ohjelmaa ja vastaanottaa käyttäjän antamat syötteet.
 *
 * Käyttöliittymä hyödyntää apulaiset-kansiosta löytyvää In-luokkaa ja kutsuu
 * Komentotulkin paloittele()-metodia parametrina käyttäjän antama syöte.
 *
 * Noora Rintamäki (rintamaki.noora.m@student.uta.fi), Informaatiotieteiden yksikkö
 * (tietojenkäsittelytieteet), Tampereen yliopisto.
 *
 * Viimeksi muutettu 08.05.2017
 *
 */



public class Kayttoliittyma extends Komentotulkki {

    private static final String ERROR = "Error!";
    private static final String TERVETULOA = "Welcome to SOS.";
    private static final String KIRJOITA = ">";
    private static final String LOPETUS = "Shell terminated.";
    private Komentotulkki komentotulkki = new Komentotulkki();

    /*
     * Pyörittää ohjelmaa
     *
     */
    public void start() {
        String komento;
        boolean jatketaanko = true;

        /* tervehdyksen
         */
        tulostaln(TERVETULOA);
        /* luo juurihakemiston
         */
        komentotulkki.luoJuurihakemisto();

        /* pyörittää ohjelmaa, kunnes käyttäjä antaa lopettamiskäskyn
         */
        while (jatketaanko) {
            try {
                /* tulostaa polun ja syötekehotteen
                 */
                tulosta(komentotulkki.annaPolku());
                annaSyote();
                /* lukee käyttäjän syötteen
                 */
                komento = In.readString();
                /* lähettää syötteen Komentotulkille
                 */
                jatketaanko = komentotulkki.paloittele(komento);
            } catch (IllegalArgumentException e) {
                tulostaln(ERROR + "Illegal");
            } catch (NullPointerException e) {
                tulostaln(ERROR + "NullPointer");
            } /*catch (Exception e) {
                tulostaln(ERROR);
            }*/
        }
        /* tulostaa lopetustekstin
         */
        tulostaln(LOPETUS);
    }

    /* tulostaa parametrin
     *
     * @param tulostettava
     */
    public void tulosta(Object tulostettava) {
        System.out.print(tulostettava);
    }

    /* tulostaa parametrin rivinvaihdolla
     *
     * @param tulostettava
     */
    public void tulostaln(Object tulostettava) {
        System.out.println(tulostettava);
    }

    /* tulostaa KIRJOITA-attribuutin
     */
    private void annaSyote() {
        System.out.print(KIRJOITA);
    }
}
