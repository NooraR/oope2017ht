package oope2017ht;


import tiedot.*;

/**
 * Created by weppi on 31.3.2017.
 */
public class Komentotulkki extends Hakemisto {

    private static final String ERROR = "Error!";
    private Kayttoliittyma kayttoliittyma;

    public Komentotulkki() {

    }

    public Komentotulkki(Kayttoliittyma k) {
        this.kayttoliittyma = k;
    }

    public boolean paloittele(String syote) {
        String komento = "ei komentoa";
        String[] parametrit = null;

        if (syote.contains(" ")) {
            parametrit = syote.split(" ");
            komento = parametrit[0];
        } else {
            tulosta("Syöte ei sisällä parametreja");
        }

       /* System.out.println(syote);

        char etsitty = ' ';
        String komento = null;
        String parametri = null;

        if (syote.charAt(2) == etsitty) {
            komento = syote.substring(0,2);
            for(int i = 3; i < syote.length(); i++) {

            }
            parametri = syote.substring(3,syote.length());
        } else if (syote.charAt(4) == ' ') {
            komento = syote.substring(0,4);
            parametri = syote.substring(5,syote.length());
        } else {
            System.out.println(ERROR);
        }*/

        /*System.out.println(komento);
        System.out.println(parametrit[1]);*/
        return tulkitse(komento, parametrit);

    }

    private boolean tulkitse(String komento, String[] parametrit) {

        // Kayttoliittyma kayttoliittyma = new Kayttoliittyma();

        if (komento.equals("md")) {
            // nykyinen hakemisto toiseksi parametriksi
            Hakemisto hakemisto = new Hakemisto(new StringBuilder(parametrit[0]), kayttoliittyma.annaHakemisto());
            kayttoliittyma.lisaaPolkuun(parametrit[1]);
        } else if (komento.equals("mf")) {
            Tiedosto tiedosto = new Tiedosto(new StringBuilder(parametrit[0]), Integer.parseInt(parametrit[1]));
            tulosta("mf toimi");
        }  else if (komento.equals("cd")) {
            if (parametrit[1].equals("..")) {
                kayttoliittyma.nykyinenHakemisto = this.annaYlihakemisto();
                paivitaPolku(kayttoliittyma.nykyinenHakemisto);
                // Siirry ylihakemistoon
            } else if (parametrit[1].equals(null)) {
                String juurihakemisto = "/";
                kayttoliittyma.nykyinenHakemisto = this.hae(juurihakemisto);
                // Siirry juurihakemistoon
            } else {
                // Siirry annettuun hakemistoon
                kayttoliittyma.nykyinenHakemisto = this.hae(parametrit[i]);
            }
        } else if (komento.equals("ls")) {
            if (!(parametrit[1].equals(null))) {
                // hae parametrin nimisen tiedoston kaikki tiedostot ja alihakemistot listana
            } else {
                // listaa tämän hakemiston tiedostot ja alihakemistot
            }
        } else if (komento.equals("find")) {

        } else if (komento.equals("rm")) {
            poista(parametrit[1]);
        } else if (komento.equals("cp")) {

        } else if (komento.equals("mv")) {

        } else if (komento.equals("exit")) {
            return false;
        } else {
            System.out.println(ERROR + "Viimeisesta");
        }
        return true;
    }

    private void tulosta(String tulostettava) {
        System.out.println(tulostettava);
    }

    protected void paivitaPolku(Hakemisto nykyinen) {

        int i = kayttoliittyma.polku.length();
        while(!(kayttoliittyma.polku.charAt(i) == '/')) {
            kayttoliittyma.polku.deleteCharAt(i);
            i--;
        }
        i = kayttoliittyma.polku.length();
        kayttoliittyma.polku.deleteCharAt(i);
        System.out.println(kayttoliittyma.polku);
    }

}
