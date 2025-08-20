package sk.upjs.paz;


import java.util.Arrays;

public class DP_PolMesiac {
    int[][] dp;
    int maximum = Integer.MIN_VALUE;

    public void polmesiac(int[][] rozlozenie) {
        dp = new int[rozlozenie.length][rozlozenie[0].length];
        for (int i = 0; i < rozlozenie[0].length; i++) {
            dp[0][i] = rozlozenie[0][i];
        }


        for (int i = 1; i < dp.length; i++) {

            for (int j = 0; j < dp[0].length; j++) {
                if (rozlozenie[i][j] == 1) {
                    if (j - 1 < 0) {
                        // len nad a doprava
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]) + 1;
                        maximum = Math.max(dp[i][j], maximum);
                    } else if (j + 1 >= dp[0].length) {
                        // nad a doľava
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + 1;
                        maximum = Math.max(dp[i][j], maximum);
                    } else {
                        // nad, doľava a doprava
                        int a = Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
                        dp[i][j] = Math.min(a, dp[i - 1][j + 1]) + 1;
                        maximum = Math.max(dp[i][j], maximum);
                    }

                }else {
                    dp[i][j] = 0;
                }
            }
        }

        System.out.println(Arrays.deepToString(dp));
        System.out.println(maximum);

    }


    public static void main(String[] args) {
        int[][] vstup = {
                {1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
        };

        DP_PolMesiac m = new DP_PolMesiac();
        m.polmesiac(vstup);
    }
}
