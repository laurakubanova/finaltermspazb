package sk.upjs.paz;

import java.util.Arrays;

public class DP_CicaMicAJonatan {
    int[][] dp;


    public void skakanie(int[][] vstup) {
        dp = new int[vstup.length][vstup[0].length];
        for (int i = 0; i < vstup[0].length; i++) {
            dp[0][i] = vstup[0][i];
        }


        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                int odkialSkacem = dp[i - 1][j];

                if (j == 0) {
                    //pod seba a doprava
                    dp[i][j] = Math.max(odkialSkacem + vstup[i][j], dp[i][j]);
                    dp[i][j + 1] = Math.max(odkialSkacem + vstup[i][j + 1], dp[i][j + 1]);

                } else if (j == dp[0].length - 1) {
                    //pod seba a dolava
                    dp[i][j] = Math.max(odkialSkacem + vstup[i][j], dp[i][j]);
                    dp[i][j - 1] = Math.max(odkialSkacem + vstup[i][j - 1], dp[i][j - 1]);
                } else {

                    dp[i][j] = Math.max(odkialSkacem + vstup[i][j], dp[i][j]);
                    dp[i][j - 1] = Math.max(odkialSkacem + vstup[i][j - 1], dp[i][j - 1]);
                    dp[i][j + 1] = Math.max(odkialSkacem + vstup[i][j + 1], dp[i][j + 1]);
                }
            }
        }

        System.out.println(Arrays.deepToString(dp));
    }


    public static void main(String[] args) {
        int[][] vstup = {
                {5, 1, 1},
                {3, 2, 1},
                {2, 5, 8},
                {1, 2, 4},
                {9, 5, 4},
        };
        DP_CicaMicAJonatan cm = new DP_CicaMicAJonatan();
        cm.skakanie(vstup);
    }
}
