package oope2017ht;


import tiedot.*;

/**
 * Created by weppi on 31.3.2017.
 */
public class Komentotulkki {

    private static final String ERROR = "Error!";

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

        if (komento.equals("md")) {
            // nykyinen hakemisto toiseksi parametriksi
            Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
            Hakemisto hakemisto = new Hakemisto(new StringBuilder(parametrit[0]), kayttoliittyma.annaHakemisto());
            kayttoliittyma.lisaaPolkuun(parametrit[1]);
        } else if (komento.equals("mf")) {
            Tiedosto tiedosto = new Tiedosto(new StringBuilder(parametrit[0]), Integer.parseInt(parametrit[1]));
            tulosta("mf toimi");
        }  else if (komento.equals("cd")) {
            if (parametrit[1] == "..") {

            } else if (parametrit[1] == null) {
                // Siirry ylihakemistoon
            } else {

            }
        } else if (komento.equals("ls")) {

        } else if (komento.equals("find")) {

        } else if (komento.equals("rm")) {

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

}
