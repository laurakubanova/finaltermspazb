package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class greedy {
    ArrayList<Integer> ulohy = new ArrayList<>();
    ArrayList<Integer> poctymuzi = new ArrayList<>();
    boolean [][] dostupnost;
    int [][] dostupneOsoby;

    public void naplnenieMatice(String [] informacie, int M , int D){
        dostupnost = new boolean[M][D];
        dostupneOsoby = new int[M][D];

        for (int i = 0; i < informacie.length; i++) {
            String riadok = informacie[i];
            String[] split =  riadok.split(" ");
            for (int j = 0; j < split.length; j++) {
                int cislo = Integer.parseInt(split[j]);
                dostupnost[i][cislo] = true;
            }
        }

        for (int i = 0; i < dostupnost[0].length; i++) {
            int pocitadlo = 0;
            for (int j = 0; j < dostupnost.length; j++) {
                if (dostupnost[j][i]){
                    dostupneOsoby[i][j] = j;
                    pocitadlo++;
                }
            }
            poctymuzi.add(pocitadlo);
            System.out.println(Arrays.deepToString(dostupneOsoby));
        }


//        for (int den = 0; den < dostupnost[0].length; den++) {
//            int pocitadlo = 0;
//            for (int muz = 0; muz < dostupnost.length; muz++) {
//                if (dostupnost[muz][den]) {
//                    pocitadlo++;
//                }
//            }
//            poctymuzi.add(pocitadlo);
//        }

        System.out.println(Arrays.deepToString(dostupnost));
        System.out.println(poctymuzi);
    }

    public boolean rozdeleniePrac(ArrayList<Integer> kapacitaNaUlohy){
        Collections.sort(kapacitaNaUlohy);
        Collections.sort(poctymuzi);
        Collections.reverse(kapacitaNaUlohy);
        Collections.reverse(poctymuzi);

        for (int i = 0; i < kapacitaNaUlohy.size(); i++) {
            int hladaneCislo = kapacitaNaUlohy.get(i);
            for (int j = 0; j < poctymuzi.size(); j++) {
                if (poctymuzi.get(j)>=hladaneCislo){
                    kapacitaNaUlohy.set(i,-1);
                    poctymuzi.set(j,-1);
                    break;
                }
            }
        }

        int pocitadlo = 0;
        for (Integer i : kapacitaNaUlohy) {

            if (i==-1){
                pocitadlo++;
            }
        }
        if (pocitadlo==kapacitaNaUlohy.size()){
            return true;
        }return false;
    }

    public static void main(String[] args) {
        //String [] vstup = {"0", "1 2" ,"0 2 1 3"};
        greedy g = new greedy();
        ArrayList<Integer> ulohy = new ArrayList<>();
        String[] vstup = {"0 1 2 3", "0 1", "2 3", "1 2 3"};
        g.naplnenieMatice(vstup, 4,4);
        ulohy.add(1);
        ulohy.add(2);
        ulohy.add(2);
        ulohy.add(2);
        System.out.println(g.rozdeleniePrac(ulohy));
    }
}
