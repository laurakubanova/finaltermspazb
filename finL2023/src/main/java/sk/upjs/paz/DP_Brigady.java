package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Collections;

public class DP_Brigady {
    ArrayList<Brigady> brigady;
    ArrayList<Brigady> navstivene = new ArrayList<>();
    int[] dp;


    public DP_Brigady(ArrayList<Brigady> brigada) {
        this.brigady = brigada;
        Collections.sort(brigady);
        int najvacsie = brigady.get(brigady.size() - 1).koniec;
        dp = new int[najvacsie + 1];
        dp[0] = 0;
        vyplnBrigady();
        System.out.println(dp[dp.length - 1]);
        System.out.println(navstivene);


    }

    public void vyplnBrigady() {

        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1];
            for (Brigady b : brigady) {
                if (b.koniec == i) {
                    dp[i] = Math.max(dp[i], dp[b.zaciatok-1] + b.peniaye);
                }
            }
        }
        spatnyChod();
    }

    public void spatnyChod() {
        int i = dp.length - 1;

        while (i > 0) {
            if (dp[i] == dp[i - 1]) {
                i--;
            } else {
                for (Brigady b : brigady) {
                    if (b.koniec == i) {
                        int predchadzajuciIndex = b.zaciatok-1;
                        if (predchadzajuciIndex >= 0 && dp[i] == dp[predchadzajuciIndex] + b.peniaye) {
                            navstivene.add(b);
                            i = predchadzajuciIndex;
                            break;
                        }
                    }
                }
            }
        }
        Collections.reverse(navstivene);
    }


    public static void main(String[] args) {
        Brigady b1 = new Brigady(2, 2, 30);
        Brigady b2 = new Brigady(4, 6, 90);
        Brigady b3 = new Brigady(8, 10, 90);
        Brigady b4 = new Brigady(8, 10, 90);
        Brigady b5 = new Brigady(12, 12, 30);
        Brigady b6 = new Brigady(5, 9, 250);
        ArrayList<Brigady> brigady = new ArrayList<>();
        brigady.add(b1);
        brigady.add(b2);
        brigady.add(b3);
        brigady.add(b4);
        brigady.add(b5);
        brigady.add(b6);
        DP_Brigady dp = new DP_Brigady(brigady);
        dp.spatnyChod();


    }
}
