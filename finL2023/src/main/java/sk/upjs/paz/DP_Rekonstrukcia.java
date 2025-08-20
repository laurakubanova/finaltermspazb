package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Arrays;

public class DP_Rekonstrukcia {
    int [][] dp;
    public static final int NEDA_SA = Integer.MAX_VALUE;
    int minumum = Integer.MAX_VALUE;

    public void stavbaCiest(ArrayList<Cesta> cesty, int pozadovaneKilometre){
        dp = new int[cesty.size()+1][pozadovaneKilometre+1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i],NEDA_SA);
        }
        dp[0][0] = 0;

        for (int i = 0; i < dp.length-1; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if(dp[i][j]!=NEDA_SA){
                    ///////////////////!!!!!!!!!!!!!!!????????????????????
                    dp[i + 1][j] = dp[i][j];
                    if (j+cesty.get(i).dlzka<=pozadovaneKilometre){
                        dp[i][j+cesty.get(i).dlzka] = dp[i][j] + cesty.get(i).cena;
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));


        for (int i = 0; i <dp.length ; i++) {
            minumum = Math.min(dp[i][pozadovaneKilometre],minumum);
        }

        System.out.println(minumum);
    }


    public static void main(String[] args) {
        ArrayList<Cesta> cesticky = new ArrayList<>();
        cesticky.add(new Cesta(3, 10));
        cesticky.add(new Cesta(4, 7));
        cesticky.add(new Cesta(6, 15));
        cesticky.add(new Cesta(7, 20));
//        cesticky.add(new Cesta(30, 70));
//        cesticky.add(new Cesta(100, 300));
//        cesticky.add(new Cesta(17, 24));
//        cesticky.add(new Cesta(6, 9));
        DP_Rekonstrukcia r = new DP_Rekonstrukcia();
        r.stavbaCiest(cesticky,7);
    }
}
