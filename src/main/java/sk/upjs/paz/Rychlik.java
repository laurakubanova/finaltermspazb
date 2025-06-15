package sk.upjs.paz;

import java.util.Arrays;

public class Rychlik {
    int[] miesta;       // pole sedadiel, index = sedadlo, hodnota = číslo pasažiera alebo 0
    int[] vybrane;      // indexy miest, ktoré sú obsadené
    int pasazieri;
    int celkovoMiest;

    public void miestenkyVkupe(int sedadla, int pocetVagonov, int cestujuci){
        celkovoMiest = sedadla * pocetVagonov;
        pasazieri = cestujuci;
        miesta = new int[celkovoMiest];      // všetky sedadlá, defaultne 0
        vybrane = new int[pasazieri];        // indexy obsadených miest
        generuj(0, 0);
    }

    public void generuj(int odIdx, int start){
        if (odIdx == pasazieri){
            // zapíš pasažierov do sedadiel
            Arrays.fill(miesta, 0);
            for (int i = 0; i < pasazieri; i++) {
                miesta[vybrane[i]] = i + 1; // pasažieri sú číslovaní od 1
            }
            System.out.println(Arrays.toString(miesta));
            return;
        }

        for (int i = start; i < celkovoMiest; i++){
            vybrane[odIdx] = i;
            generuj(odIdx + 1, i + 1);
        }
    }

    public static void main(String[] args) {
        Rychlik r = new Rychlik();
        r.miestenkyVkupe(6, 6, 6); // 6 pasažierov zo 36 sedadiel
    }
}