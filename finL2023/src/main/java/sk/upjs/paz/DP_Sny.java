package sk.upjs.paz;

import java.util.ArrayList;
import java.util.Collections;

public class DP_Sny {
    int [] dp;
    ArrayList<Sen> sny;
    ArrayList<Sen> splneneSny = new ArrayList<>();

    public void plnenieSnov(ArrayList<Sen>sny){
        this.sny = sny;
        Collections.sort(sny);
        dp = new int[sny.get(sny.size()-1).koniec+1];
        dp [0] = 0;

        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i-1];
            for (Sen sen : sny) {
                if (sen.koniec==i){
                    dp[i] = Math.max(dp[sen.zaciatok]+sen.odmena,dp[i]);
                }
            }
        }
        System.out.println(dp[dp.length-1]);
    }


    public void spatnyPrechod(){
        int i = dp.length-1;

        while (i>0){
            if (dp[i]==dp[i-1]){
                i--;
            }else {
                for (Sen sen : sny) {
                    if (sen.koniec==i){
                        int denpredZaciatkom = sen.zaciatok;
                        if (denpredZaciatkom>=0 && dp[i] == dp [denpredZaciatkom]+sen.odmena){
                            splneneSny.add(sen);
                            i = denpredZaciatkom;
                            break;
                        }
                    }
                }
            }
        }
        Collections.reverse(splneneSny);
        System.out.println(splneneSny);

    }

    public static void main(String[] args) {


        Sen s1 = new Sen(19, 5, 10_000);
        Sen s2 = new Sen(24, 1, 4_000);
        Sen s3 = new Sen(22, 2, 2_000);
        Sen s4 = new Sen(20, 2, 6_000);



        ArrayList<Sen> vstup = new ArrayList<>();
        vstup.add(s1);
        vstup.add(s2);
        vstup.add(s3);
        vstup.add(s4);
        DP_Sny d = new DP_Sny();
        d.plnenieSnov(vstup);
        d.spatnyPrechod();

    }



}
