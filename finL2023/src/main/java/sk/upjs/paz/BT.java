package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class BT {
    int[] p;
    ArrayList<Zviera> zvierata;
    HashSet<Integer> mnozina;
    int[] riesenie;
    ArrayList<Zviera> konkretneDruhy;
    int minimum = Integer.MAX_VALUE;
    int k;
    int[] poleKopia;
    HashSet<Integer> h;
    int velkostmnozinyDruhov = 0;


    public void zvierataPriVode(ArrayList<Zviera> zvierata, int pocetDruhov, int k) {
        mnozina = new HashSet<>();
        for (int i = 0; i < pocetDruhov; i++) {
            mnozina.add(i);
        }
        p = new int[zvierata.size()];
        poleKopia = new int[p.length];
        this.zvierata = zvierata;
        riesenie = new int[p.length];
        //System.out.println(mnozina);
        this.k = k;

        generuj(0);
        System.out.println(minimum);
        System.out.println(konkretneDruhy);
        System.out.println(velkostmnozinyDruhov);
    }


    public void najViacDruhov() {

    }


    private void optimalizovaneGeneruj() {
        // budem generovat a ciastocne spracujem, retain all mozno
    }


    private void generuj(int odIdx) {
        if (odIdx == p.length) {
            //System.out.println(Arrays.toString(p));
            spracuj();
            return;
        }

        for (int i = 0; i <= 1; i++) {
            p[odIdx] = i;
            generuj(odIdx + 1);
        }
    }

    public void spracuj() {
        HashSet<Integer> kopia = (HashSet<Integer>) mnozina.clone();
        HashSet<Integer> aktualneZvierata = new HashSet<>();
        int pocitadloJednotiek = 0;


        for (int i = 0; i < p.length; i++) {
            if (p[i] == 1) {
                pocitadloJednotiek++;
                for (Integer z : zvierata.get(i).druhy) {
                    kopia.remove(z);
                }
            }
        }
        if (pocitadloJednotiek == k) {
            poleKopia = p.clone();
            for (int i = 0; i < poleKopia.length; i++) {
                if (p[i] == 1) {
                    for (Integer z : zvierata.get(i).druhy) {
                        aktualneZvierata.add(z);
                    }
                }
            }
            pocitadloJednotiek = 0;
            if (aktualneZvierata.size() > velkostmnozinyDruhov) {
                velkostmnozinyDruhov = aktualneZvierata.size();
            }
        }


        if (kopia.isEmpty()) {
            riesenie = p.clone();
            ArrayList<Zviera> ktoJepriVode = new ArrayList<>();
            int pocitadlo = 0;

            for (int i = 0; i < riesenie.length; i++) {
                if (p[i] == 1) {
                    pocitadlo++;
                    ktoJepriVode.add(zvierata.get(i));
                }
            }


            if (pocitadlo < minimum) {
                minimum = pocitadlo;
                konkretneDruhy = ktoJepriVode;
            }
        }


    }

    public static void main(String[] args) {
        //int[] vstup1 = {1, 2, 3};
        int[] vstup2 = {0, 1};
        int[] vstup3 = {2, 3};
        int[] vstup4 = {3, 4};

        // int [][] p = {vstup1,vstup2,vstup3,vstup4};


        // Zviera z = new Zviera(vstup1);
        Zviera z1 = new Zviera(vstup2);
        Zviera z2 = new Zviera(vstup3);
        Zviera z3 = new Zviera(vstup4);


        ArrayList<Zviera> list = new ArrayList<>();
        //list.add(z);
        list.add(z1);
        list.add(z2);
        list.add(z3);

        BT f = new BT();
        f.zvierataPriVode(list, 5, 2);
        f.najViacDruhov();

    }


}
