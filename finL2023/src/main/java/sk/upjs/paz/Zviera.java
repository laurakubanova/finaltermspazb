package sk.upjs.paz;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Zviera implements Comparable<Zviera> {
    ArrayList<Integer> druhy = new ArrayList<>();

    @Override
    public String toString() {
        return "Zviera{" +
                "druhy=" + druhy +
                '}';
    }

    public Zviera(int [] popis) {

        for (int i = 0; i < popis.length; i++) {
            this.druhy.add(popis[i]);
        }
    }

    @Override
    public int compareTo(Zviera o) {
        return Integer.compare(this.druhy.size(),o.druhy.size());
    }
}
