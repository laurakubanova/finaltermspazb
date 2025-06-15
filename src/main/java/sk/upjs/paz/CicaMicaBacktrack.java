package sk.upjs.paz;

import java.util.Arrays;

public class CicaMicaBacktrack {

    // chceme generovat obchod z ktoreho vezmeme hracku a indexy su konkretne hracky
    int[] p;
    int hracky;
    int obchody;
    int[][] zoznamHracie;
    int [][] postovne;
    int najlepsiaSuma = Integer.MAX_VALUE;
    int[] ceny;

    public void nakupHracie(int[][] ponuka, int[][] cenaPostovneho) {
        p = new int[ponuka[0].length];
        obchody = ponuka.length;
        hracky = ponuka[0].length;
        zoznamHracie = ponuka;
        postovne = cenaPostovneho;
        ceny = new int[obchody];
        generuj(0);
        System.out.println(najlepsiaSuma);
    }

    private void generuj(int odIdx) {

        if (odIdx == p.length) {
            System.out.println(Arrays.toString(p));
            spracuj();
            return;
        }

        for (int i = 0; i < obchody; i++) {
            if (zoznamHracie[i][odIdx] != -1) { // ak dany predajca nema danu hracku tak ho neuvazujem ani do danej volby
                p[odIdx] = i;
                ceny[i] += zoznamHracie[i][odIdx]; // rovno si napocitavam tie ceny u predajcov
                generuj(odIdx + 1);
                ceny[i] -= zoznamHracie[i][odIdx];
            }
        }
    }

    public void spracuj() {
        int aktualnaCena = 0;

        // vybavene pri generovani
//        int[] ceny = new int[obchody];
//
//        for (int i = 0; i < p.length; i++) {
//            int obchod = p[i];
//            if (zoznamHracie[obchod][i] == -1){ //ak je aspon jeden predmet nedostupny zahadzujem moznost
//                return;
//            }else {
//                ceny[obchod] += zoznamHracie[obchod][i];
//            }
//        }

        for (int i = 0; i < obchody; i++) {
            if (ceny[i] == 0) continue;

            if (ceny[i] >= postovne[i][1]) {
                aktualnaCena += ceny[i];
                if (aktualnaCena>najlepsiaSuma){ //ak presiahnem cenu zahadzujem moznost
                    return;
                }
            } else {
                aktualnaCena += ceny[i] + postovne[i][0];
                if (aktualnaCena>najlepsiaSuma){
                    return;
                }
            }
        }

        najlepsiaSuma = Math.min(najlepsiaSuma, aktualnaCena);
    }



    public static void main(String[] args) {
        int[][] vstup = {{25, -1, 10, 25}, {-1, 25, 20, 22}, {30, 30, 30, 30}};
        int[][] postovne = {{15, 50}, {20, 45}, {30, 100}};
        CicaMicaBacktrack c = new CicaMicaBacktrack();
        c.nakupHracie(vstup, postovne);
    }

}

