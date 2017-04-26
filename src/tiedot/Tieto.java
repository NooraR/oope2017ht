package tiedot;

/**
 * Created by weppi on 23.3.2017.
 */
public abstract class Tieto implements Comparable<Tieto> {

    /*
    * Attribuuttit
    *
     */

    // tiedoston tai hakemiston nimi
    private StringBuilder nimi;

    /*
    * Rakentajat
    *
     */

    public Tieto() {
        asetaNimi(null);
    }

    public Tieto(StringBuilder annettuNimi) throws IllegalArgumentException {
        if (nimiOk(annettuNimi)) {
            nimi = annettuNimi;
        }
    }

    /*
    * Aksessorit
    *
     */

    public void asetaNimi(StringBuilder annettuNimi) throws IllegalArgumentException {
        this.nimi = annettuNimi;
    }

    public StringBuilder annaNimi() {
        return this.nimi;
    }

    /*
    * Kopiorakentajat
    *
     */

    public Tieto(Tieto t) {
        asetaNimi(t.annaNimi());
    }

    /*
    * Metodit
    *
     */

    private boolean nimiOk(StringBuilder annettuNimi) throws IllegalArgumentException {
        String tarkasteltava = annettuNimi.toString();
        boolean onOk = true;
        for (int i = 0; i < tarkasteltava.length(); i++) {
            if (tarkasteltava.charAt(i) >= 'a' && tarkasteltava.charAt(i) <= 'z'
                    || tarkasteltava.charAt(i) >= 'A' && tarkasteltava.charAt(i) <= 'Z'
                    || tarkasteltava.charAt(i) >= '0' && tarkasteltava.charAt(i) <= '0'
                    || tarkasteltava.charAt(i) == '_' || tarkasteltava.charAt(i) == '.' || tarkasteltava.charAt(i) == '/') {
            } else {
                throw new IllegalArgumentException();
            }
        }

        return onOk;
    }

    // kirjoita uudestaan
    @Override
    public String toString() {
       return this.nimi.toString();
    }

    public boolean equals(Object o) {
        if (o instanceof Tieto) {
            if (this.annaNimi().toString().equals(((Tieto) o).annaNimi().toString())) {
                return true;
            }
        }
        return false;
    }

    public int compareTo(Tieto t) {
        return this.annaNimi().toString().compareTo(t.annaNimi().toString());
    }
}
