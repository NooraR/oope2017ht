package tiedot;

import fi.uta.csjola.oope.lista.LinkitettyLista;
import oope2017ht.omalista.OmaLista;
import apulaiset.Komennettava;

/**
 * Hakemisto-luokka periytyy Tieto-luokasta ja toteuttaa Komennettava<T>-rajapinnan.
 * Luokasta luodaan Hakemisto-olioita, joihin sisällytetään tallennetaan OmaLista-attribuutin
 * avulla.
 *
 * Noora Rintamäki (rintamaki.noora.m@student.uta.fi), Informaatiotieteiden yksikkö
 * (tietojenkäsittelytieteet), Tampereen yliopisto.
 *
 * Viimeksi muutettu 08.05.2017
 *
 */

public class Hakemisto extends Tieto implements Komennettava<Tieto> {

    private static final String VALIMERKKI = "/ ";

    /**
     * Attribuutit
     */

    private Hakemisto ylihakemisto;
    private OmaLista lista;

    /**
     * Rakentajat
     *
     */

    /**
     * parametriton rakentaja
     */
    public Hakemisto() { }

    /**
     * parametrillinen rakentaja
     *
     * @param annettuNimi, annettuYlihakemisto
     */
    public Hakemisto(StringBuilder annettuNimi, Hakemisto annettuYlihakemisto) throws IllegalArgumentException {
        /**
         * Kutsuu Tieto-luokan rakentajaa
         */
        super(annettuNimi);
        asetaYlihakemisto(annettuYlihakemisto);
        /**
         * Luo OmaLista-attribuutin
         */
        lista = new OmaLista();
    }

    /**
     * Aksessorit
     *
     */

    /**
     * ylihakemiston setteri
     *
     * @param annettuYlihakemisto
     */
    public void asetaYlihakemisto(Hakemisto annettuYlihakemisto) throws IllegalArgumentException {
        ylihakemisto = annettuYlihakemisto;
    }

    /**
     * ylihakemiston getteri
     *
     * @return Hakemisto-tyyppinen ylihakemisto
     */
    public Hakemisto annaYlihakemisto() {
        return ylihakemisto;
    }

    /**
     * Metodit
     *
     */

    /**
     * toString-metodin korvaus
     *
     * @return alkion nimi ja koko String-muotoisina
     */
    @Override
    public String toString() {
        return super.toString() + VALIMERKKI + lista.koko();
    }

    /**
     * Komennettava-rajapinnan toteutus
     */

    /**
     * Aksessori, joka antaa viitteen hakemiston sisällön säilövään listaan.
     *
     * @return viite hakemisto-olion osaolioon.
     */

    public LinkitettyLista sisalto() {
        return lista;
    }

    /** Hakee hakemistosta tiedostoa tai alihakemistoa. Hyödyntää OmaLista-luokan
     * hae-operaatiota.
     *
     * @param nimi haettavan tiedon nimi.
     * @return viite löydettyyn tietoon. Paluuarvo on null, jos tietoa ei löydetä.
     */

    public Tieto hae(String nimi) {
        if (null != nimi) {
            Tiedosto apuTiedosto = new Tiedosto(new StringBuilder(nimi), 1);
            Hakemisto apuHakemisto = new Hakemisto(new StringBuilder(nimi), null);

            if (lista.hae(apuTiedosto) != null) {
                return (Tieto) lista.hae(apuTiedosto);
            } else if (lista.hae(apuHakemisto) != null) {
                return (Tieto) lista.hae(apuHakemisto);
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    /** Lisää hakemistoon tiedoston tai alihakemiston. Hyödyntää OmaLista-luokan
     * lisaa-operaatiota.
     *
     * @param lisattava viite lisättävään tietoon.
     * @return true, jos lisääminen onnistui ja false, jos tiedot on null-arvoinen
     * tai hakemistossa on jo tiedot parametrina annetulla nimellä.
     */

    public boolean lisaa(Tieto lisattava) {
        return lista.lisaa(lisattava);
    }

    /** Poistaa hakemistosta tiedoston tai alihakemiston. Hyödyntää OmaLista-luokan
     * poista-operaatiota.
     *
     * @param annettuNimi poistettavan tiedon nimi.
     * @return viite poistettuun tietoon. Paluuarvo on null, jos tietoa ei löydetä.
     */

    public Tieto poista(String annettuNimi) {
        if (annettuNimi != null) {
            /**
             * Luo uuden Tiedosto- ja Hakemisto-olion ja asettaa nimeksi saadun parametrin
             */
            Tiedosto apuTiedosto = new Tiedosto((new StringBuilder(annettuNimi)), 0);
            Hakemisto apuHakemisto = new Hakemisto((new StringBuilder(annettuNimi)), null);

            if(lista.hae(apuTiedosto) != null) {
                return (Tieto) lista.poista(apuTiedosto);
            } else if (lista.hae(apuHakemisto) != null) {
                return (Tieto) lista.poista(apuHakemisto);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
