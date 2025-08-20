package sk.upjs.paz;

public class Brigady implements Comparable<Brigady>{
    int zaciatok;
    int koniec;
    int peniaye;

    public Brigady(int zaciatok, int koniec, int peniaye) {
        this.zaciatok = zaciatok;
        this.koniec = koniec;
        this.peniaye = peniaye;
    }

    @Override
    public int compareTo(Brigady o) {
        return Integer.compare(this.koniec,o.koniec);
    }

    @Override
    public String toString() {
        return "Brigady{" +
                "zaciatok=" + zaciatok +
                ", koniec=" + koniec +
                '}';
    }
}

