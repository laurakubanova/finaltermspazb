package sk.upjs.paz;

public class Sen implements Comparable<Sen> {
    int zaciatok;
    int koniec;
    int odmena;


    public Sen(int zaciatok, int trvanie, int odmena) {
        this.zaciatok = zaciatok;
        this.koniec = zaciatok + trvanie;
        this.odmena = odmena;
    }

    @Override
    public int compareTo(Sen o) {
        return Integer.compare(this.koniec,o.koniec);
    }

    @Override
    public String toString() {
        return "Sen{" +
                "zaciatok=" + zaciatok +
                ", koniec=" + koniec +
                ", odmena=" + odmena +
                '}';
    }
}
