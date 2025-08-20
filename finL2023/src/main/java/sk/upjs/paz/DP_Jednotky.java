package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;

public class DP_Jednotky {
    boolean[][] dp;


    public boolean suroviny(ArrayList<Jednotky> udaje, int x, int y) {

        dp = new boolean[x + 1][y + 1];
        dp[0][0] = true;

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i][j]) {
                    for (Jednotky jednotky : udaje) {
                        // pozor na i a j  i pre x-riadky, j pre y- stlpce
                        if (i + jednotky.x < dp.length && j + jednotky.y < dp[0].length) {
                            dp[i + jednotky.x][j + jednotky.y] = true;
                        }
                    }
                }
            }
        }
        return dp[x][y];

    }


    public static void main(String[] args) {
        ArrayList<Jednotky> jednotky = new ArrayList<>();
        jednotky.add(new Jednotky(2, 3));
        jednotky.add(new Jednotky(1, 1));
        jednotky.add(new Jednotky(4, 2));
        DP_Jednotky dj = new DP_Jednotky();

        System.out.println(dj.suroviny(jednotky, 5, 5));
    }
}
