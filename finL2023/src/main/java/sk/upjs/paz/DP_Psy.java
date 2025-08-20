package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;

public class DP_Psy {
    int[][] dp;
    public static final int NEDA_SA = Integer.MAX_VALUE;

    public boolean rozdeleniePsov(ArrayList<Pesik> psy) {
        int exp = (int) (Math.pow(2.0, (double) psy.size()));

        dp = new int[psy.size() + 1][exp];
//        for (int i = 0; i < dp.length; i++) {
//            Arrays.fill(dp[i],NEDA_SA);
//        }
        dp[0][0] = 0;

        for (int i = 0; i < dp.length - 1; i++) {
            int index = 0;
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i][j] != NEDA_SA) {
                    dp[i + 1][index] = Math.abs(dp[i][j] + psy.get(i).vaha);
                    index++;
                    dp[i + 1][index] = Math.abs(dp[i][j] - psy.get(i).vaha);
                    index++;
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            if (dp[dp.length - 1][i] == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Pesik p1 = new Pesik(7);
        Pesik p2 = new Pesik(4);
        Pesik p3 = new Pesik(3);
//        Pesik p4 = new Pesik(5);
        ArrayList<Pesik> psiky = new ArrayList<>();
        psiky.add(p1);
        psiky.add(p2);
        psiky.add(p3);
//        psiky.add(p4);
        DP_Psy d = new DP_Psy();
        System.out.println(d.rozdeleniePsov(psiky));
    }
}
