package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;

public class DP_Vlaky {
    int[][] dp;
    final static int NEDA_SA = Integer.MAX_VALUE;


    public boolean prepravcovia(ArrayList<Vagon> vagony) {
        int pocet = (int) Math.pow(2d, vagony.size() - 1);
        dp = new int[vagony.size()][pocet];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0][0] = vagony.get(0).pocetVagonov;

        for (int i = 0; i < dp.length - 1; i++) {
            int index = 0;
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i][j] !=NEDA_SA) {

                    //davaj si pozor na index vagonu, beries ten novy cize o riadok viac
                    dp[i + 1][index] = Math.abs(dp[i][j] + vagony.get(i + 1).pocetVagonov);
                    index++;
                    dp[i + 1][index] = Math.abs(dp[i][j] - vagony.get(i + 1).pocetVagonov);
                    index++;
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));

        for (int i = 0; i < dp[0].length; i++) {
            if (dp[dp.length - 1][i] == 0) return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Vagon v1 = new Vagon(15);
        Vagon v2 = new Vagon(21);
        Vagon v3 = new Vagon(7);
        Vagon v4 = new Vagon(10);
        Vagon v5 = new Vagon(9);
        ArrayList<Vagon> vstup = new ArrayList<>();
        vstup.add(v1);
        vstup.add(v2);
        vstup.add(v3);
        vstup.add(v4);
        vstup.add(v5);
        DP_Vlaky dpVlaky = new DP_Vlaky();
        System.out.println(dpVlaky.prepravcovia(vstup));
    }
}
