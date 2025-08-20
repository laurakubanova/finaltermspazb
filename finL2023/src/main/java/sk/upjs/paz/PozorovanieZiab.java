package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Collections;

public class PozorovanieZiab {
    int [] dp;

    public void pocetZiab(ArrayList<Udalost>udalosti){
        Collections.sort(udalosti);

        //int naj = udalosti.get(udalosti.size()-1).koniec;
        Collections.reverse(udalosti);
        int najvacsiDen = udalosti.get(0).koniec;
        Collections.reverse(udalosti);
        dp = new int[najvacsiDen+1];
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i-1];
              for (Udalost u : udalosti) {
                if (u.koniec==i){
                    dp[i] = Math.max(dp[u.zaciatok]+u.pocetNavstevnikov,dp[i]);
                }
            }
        }
        System.out.println(dp[dp.length-1]);
    }


    public static void main(String[] args) {
        Udalost u1 = new Udalost(100,107,60_000);
        Udalost u2 = new Udalost(101,105,30_000);
        Udalost u3 = new Udalost(106,108,40_000);
        Udalost u4 = new Udalost(45,45,1000);
        ArrayList<Udalost> udalosti = new ArrayList<>();
        udalosti.add(u1);
        udalosti.add(u2);
        udalosti.add(u3);
        udalosti.add(u4);

        PozorovanieZiab z = new PozorovanieZiab();
        z.pocetZiab(udalosti);


    }
}
