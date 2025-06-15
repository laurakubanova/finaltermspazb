package sk.upjs.paz;

import java.util.Arrays;

public class Zeriav {
    int[] uhly;
    int[] p;
    int najlepsieOtocenie = Integer.MAX_VALUE;
    int[] najlepsiePole;

    public void otocenieZeriavu(int[] uhly) {
        this.uhly = uhly;
        p = new int[uhly.length];
        najlepsiePole = new int[p.length];
        generuj(0);

        System.out.println("Najlepšie otáčanie: " + najlepsieOtocenie);
        System.out.println("Najlepšie voľby: " + Arrays.toString(najlepsiePole));
    }

    private void generuj(int odIdx) {
        if (odIdx == p.length) {
            spracuj();
            return;
        }

        for (int i = 0; i <= 1; i++) {
            p[odIdx] = i;
            generuj(odIdx + 1);
        }
    }

    private void spracuj() {
        int minimum = 0;
        int maximum = 0;
        int otocenie = 0;

        for (int i = 0; i < p.length; i++) {
            if (p[i] == 1) {
                otocenie += uhly[i];
                maximum = Math.max(otocenie,maximum);
            } else {
                otocenie -= uhly[i];
                minimum = Math.min(otocenie,minimum);
            }
        }

        int rozsah = Math.abs(minimum) + Math.abs(maximum);

        if (rozsah < najlepsieOtocenie) {
            najlepsieOtocenie = rozsah;
            najlepsiePole = p.clone();
        }
    }

    public static void main(String[] args) {
        int[] uhly = {10,12,15};
        int  [] uhly2 = {15,10,12};
        Zeriav z = new Zeriav();
        //z.otocenieZeriavu(uhly);
        z.otocenieZeriavu(uhly2);
    }
}