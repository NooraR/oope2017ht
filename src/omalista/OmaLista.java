package omalista;

import apulaiset.Ooperoiva;
import fi.uta.csjola.oope.lista.LinkitettyLista;
import tiedot.*;

/**
 * Created by weppi on 31.3.2017.
 */
public class OmaLista extends LinkitettyLista implements Ooperoiva {

    private static final String VALINMERKKI = " ";

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

    public boolean lisaa(Object uusi) {
        if (uusi != null) {
            for (int k = 0; k < koko; k++) {
                if (uusi.toString().equals(alkio(k).toString())) {
                    return false;
                }
            }
            int i = 0;
            if (koko() > 0) {
                while(i < koko()) {
                    int j = uusi.toString().compareTo(alkio(i).toString());
                    if (j > 0)
                        i++;
                    else if (j <= koko()){
                        lisaa(i, uusi);
                        return true;
                    }
                    if (i == koko()) {
                        lisaaLoppuun(uusi);
                        return true;
                    }
                }
            } else {
                lisaaAlkuun(uusi);
                return true;
            }
        }
        return false;
    }

    public Object poista(Object poistettava) {
        for (int i = 0; i < koko; i++) {
            if (alkio(i).equals(poistettava)) {
                return poista(i);
            }
        }
        return null;
    }
}


