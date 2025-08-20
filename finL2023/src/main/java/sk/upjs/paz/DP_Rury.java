package sk.upjs.paz;

import java.util.ArrayList;


public class DP_Rury {
    boolean[] dp;

    public boolean skladanieRur(ArrayList<Rura> rury, int rozmer) {
        dp = new boolean[rozmer + 1];
        dp[0] = true;

        for (int i = 0; i < dp.length; i++) {
            for (Rura rura : rury) {
                if (dp[i]) {
                    if (i + rura.dlzka < dp.length) {
                        dp[i + rura.dlzka] = true;
                    }
                }
            }
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        Rura r1 = new Rura(5);
        Rura r2 = new Rura(10);
        Rura r3 = new Rura(15);
        Rura r4 = new Rura(20);
        ArrayList<Rura> ruras = new ArrayList<>();
        ruras.add(r1);
        ruras.add(r2);
        ruras.add(r3);
        ruras.add(r4);
        DP_Rury dr = new DP_Rury();
        System.out.println(dr.skladanieRur(ruras, 45));
    }
}
