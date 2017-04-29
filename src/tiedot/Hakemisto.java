package tiedot;

import fi.uta.csjola.oope.lista.LinkitettyLista;
import omalista.OmaLista;
import apulaiset.Komennettava;

/**
 * Created by weppi on 31.3.2017.
 */

public class Hakemisto extends Tieto implements Komennettava<Tieto> {

    private static final String VALIMERKKI = "/ ";
    private static final String ERROR = "Error!";

    /*
    * Attribuutit
     */

    private Hakemisto ylihakemisto;
    private OmaLista lista;

    /*
    * Rakentajat
    *
     */

    public Hakemisto() {
        super(new StringBuilder("/"));
        asetaYlihakemisto(null);
    }

    public Hakemisto(StringBuilder annettuNimi, Hakemisto annettuYlihakemisto) throws IllegalArgumentException {
        super(annettuNimi);
        asetaYlihakemisto(annettuYlihakemisto);
        lista = new OmaLista();
    }

    /*
    * Aksessorit
    *
     */

    public void asetaYlihakemisto(Hakemisto annettuYlihakemisto) throws IllegalArgumentException {
        ylihakemisto = annettuYlihakemisto;
    }

    public Hakemisto annaYlihakemisto() {
        return ylihakemisto;
    }

    /*
    * Metodit
    *
     */

    // toString-metodi

    public String toString() {
        return super.toString() + VALIMERKKI + lista.koko();
    }

    /*
    * Komennettava-rajapinnan toteutukset
    *
     */

    /* Aksessori, joka antaa viitteen hakemiston sis�ll�n s�il�v��n listaan.
     *
     * @return viite hakemisto-olion osaolioon.
     *
     */

    public LinkitettyLista sisalto() {
        return lista;
    }

    /** Hakee hakemistosta tiedostoa tai alihakemistoa. Hy�dynt�� OmaLista-luokan
     * hae-operaatiota. Huomaa, ett� nime� k�ytt�en haun avuksi voidaan luoda
     * v�liaikainen tiedosto tai hakemisto.
     *
     * @param nimi haettavan tiedon nimi.
     * @return viite l�ydettyyn tietoon. Paluuarvo on null, jos tietoa ei l�ydet�.
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

    /** Lis�� hakemistoon tiedoston tai alihakemiston. Hy�dynt�� OmaLista-luokan
     * lisaa-operaatiota.
     *
     * @param lisattava viite lis�tt�v��n tietoon.
     * @return true, jos lis��minen onnistui ja false, jos tiedot on null-arvoinen
     * tai hakemistossa on jo tiedot parametrina annetulla nimell�.
     */

    public boolean lisaa(Tieto lisattava) {
        return lista.lisaa(lisattava);
    }

    /** Poistaa hakemistosta tiedoston tai alihakemiston. Hy�dynt�� OmaLista-luokan
     * poista-operaatiota. Huomaa, ett� nime� k�ytt�en poiston avuksi voidaan luoda
     * v�liaikainen tiedosto tai hakemisto.
     *
     * @param annettuNimi poistettavan tiedon nimi.
     * @return viite poistettuun tietoon. Paluuarvo on null, jos tietoa ei l�ydet�.
     */

    public Tieto poista(String annettuNimi) {
        if (annettuNimi != null) {
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
