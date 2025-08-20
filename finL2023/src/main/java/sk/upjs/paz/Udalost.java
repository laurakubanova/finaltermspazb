package sk.upjs.paz;

public class Udalost implements Comparable<Udalost>{

    int zaciatok;
    int koniec;
    int pocetNavstevnikov;

    public Udalost(int zaciatok, int koniec, int pocetNavstevnikov) {
        this.zaciatok = zaciatok;
        this.koniec = koniec;
        this.pocetNavstevnikov = pocetNavstevnikov;
    }

    @Override
    public int compareTo(Udalost o) {
        return Integer.compare(this.koniec,o.koniec);
    }


}
