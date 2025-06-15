package sk.upjs.paz;

import java.util.Comparator;

public class Oslavy implements Comparable<Oslavy> {
    int start;
    int koniec;

    public Oslavy(int denStartu, int hodinaStartu, int denKonca, int hodinaKonca){
        start = 24*denStartu + hodinaStartu;
        koniec = 24*denKonca + hodinaKonca;
    }

    @Override
    public int compareTo(Oslavy o) {
        return Integer.compare(this.start, o.start);
    }
}
