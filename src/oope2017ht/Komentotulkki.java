package oope2017ht;


import fi.uta.csjola.oope.lista.LinkitettyLista;
import tiedot.*;

/**
 * Created by weppi on 31.3.2017.
 */
public class Komentotulkki extends Hakemisto {

    private static final String ERROR = "Error!";
    private static final String EROTIN = "/";
    private Hakemisto nykyinenHakemisto = null;
    private StringBuilder polku = new StringBuilder();
    private Hakemisto juurihakemisto;


    public boolean paloittele(String syote) {
        String[] parametrit;
        parametrit = syote.split(" ");
        return tulkitse(parametrit);
    }

    private boolean tulkitse(String[] parametrit) {

        if (parametrit[0].equals("md")) {
            // nykyinen hakemisto toiseksi parametriksi
            Hakemisto hakemisto = new Hakemisto(new StringBuilder(parametrit[0]), nykyinenHakemisto);
        } else if (parametrit[0].equals("mf")) {
            Tiedosto tiedosto = new Tiedosto(new StringBuilder(parametrit[0]), Integer.parseInt(parametrit[1]));
            tulosta("mf toimi");
        } else if (parametrit[0].equals("cd")) {

             if (parametrit.length < 2) {
                 tulosta("väärä paikka");
                 nykyinenHakemisto = juurihakemisto;
                 polku = new StringBuilder("/");
                 // Siirry juurihakemistoon

            } else if (parametrit[1].equals("..")) {

                 nykyinenHakemisto = this.annaYlihakemisto();
                 paivitaPolku();
                 // Siirry ylihakemistoon

            } else if (parametrit.length > 1){

                 tulosta("siirtyminen");
                 // Siirry annettuun hakemistoon
                 tulosta(parametrit[1]);
                 // aiheuttaa nullPointerin
                 nykyinenHakemisto = (Hakemisto)nykyinenHakemisto.hae(parametrit[1]);
                 lisaaPolkuun(parametrit[1]);

            } else {
                tulosta("Ei mennyt mihinkaan");
            }
        } else if (parametrit[0].equals("ls")) {
            if (!(parametrit[1].equals(null))) {
                Hakemisto haettu = (Hakemisto) hae(parametrit[1]);
                tulostaSisalto(haettu.sisalto());
                // hae parametrin nimisen tiedoston kaikki tiedostot ja alihakemistot listana
            } else {
                tulostaSisalto(nykyinenHakemisto.sisalto());
                // listaa tämän hakemiston tiedostot ja alihakemistot
            }
        } else if (parametrit[0].equals("find")) {

        } else if (parametrit[0].equals("rm")) {
            poista(parametrit[1]);
        } else if (parametrit[0].equals("cp")) {
            //Tiedosto tiedosto = new Tiedosto(hae(parametrit[1]));
        } else if (parametrit[0].equals("mv")) {

        } else if (parametrit[0].equals("exit")) {
            return false;
        } else {
            tulostaln(ERROR + "Viimeisesta");
        }
        return true;
    }

    private void tulosta(String tulostettava) {
        System.out.println(tulostettava);
    }

    private void tulostaln(Object tulostettava) {
        System.out.println(tulostettava);
    }

    private void paivitaPolku() {
        String[] hakemistot;
        hakemistot = polku.toString().split("/");

        int i = hakemistot.length;
        while (!(hakemistot[i].equals(nykyinenHakemisto))) {
            hakemistot[i] = null;
            i--;
        }



    }

    private void tulostaSisalto(LinkitettyLista lista) {
        if (lista != null) {
            tulosta("[ ");
            for (int i = 0; i < lista.koko(); i++) {
                System.out.print(lista.alkio(i));
                if (i < lista.koko() -1)
                    tulosta(", ");
            }
            tulostaln(" ]");
        }
    }

    private void lisaaPolkuun(String lisays) {
        if (lisays.equals("/")) {
            polku.append(lisays);
        } else {
            polku.append(lisays + EROTIN);
        }
    }

    public StringBuilder annaPolku() {
        return polku;
    }

    public void asetaHakemisto(Hakemisto annettuHakemisto) {
        nykyinenHakemisto = annettuHakemisto;
    }

    public Hakemisto annaHakemisto() {
        return nykyinenHakemisto;
    }

    public void luoJuurihakemisto() {
        nykyinenHakemisto = new Hakemisto();
        juurihakemisto = nykyinenHakemisto;
        lisaaPolkuun(nykyinenHakemisto.annaNimi().toString());

    }
}
