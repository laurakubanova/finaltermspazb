package sk.upjs.paz;
import java.util.Arrays;

public class vlak {
    int[] vlak;
    int pocetCestujucich;
    int[] mnozinaCisel;
    int[] p;
    boolean[] pouzite;
    boolean [] obsadene;
    int [] ktoCheAkeMiesto;

    public vlak(int pocetMiestVoVlaku) {
        vlak = new int[pocetMiestVoVlaku];
        mnozinaCisel = new int[pocetMiestVoVlaku];
        pouzite = new boolean[pocetMiestVoVlaku];
        obsadene = new boolean[pocetMiestVoVlaku];

    }

    public void usadenie(int pocetLudi, int [] poziadavky) {
        p = new int[pocetLudi];
        pocetCestujucich = pocetLudi;
        ktoCheAkeMiesto = poziadavky;
        generuj(0);
    }

    private void generuj(int odIdx) {
        if (p.length == odIdx) {
            System.out.println(Arrays.toString(p));
            spracuj();
            return;
        }

        for (int i = 0; i < vlak.length ; i++) {

            if (!pouzite[i]) {
                p[odIdx] = i;
                pouzite[i] = true;
                generuj(odIdx + 1);
                pouzite[i] = false;
            }
        }
    }

    // miesto 0 a 3 su pri okne
    // miesto 1 a 4 v strede
    // miesto 2 a 5 v ulicke
    // 0,1,2 su v smere jazdy

    //poziadavky :
    // -1-pri okne
    //-2 sam
    //-3 smer jazdy
    public void spracuj() {
        for (int i = 0; i < p.length; i++) {
          int vagon = p[i]/6;
          int konkretneMiesto = p[i];
          int oproti;

          if (ktoCheAkeMiesto[i] == -1){
              if (konkretneMiesto%6==0||konkretneMiesto%6==3){
                  obsadene[konkretneMiesto]=true;
              }else return;
          }

          if (ktoCheAkeMiesto[i]==-2){
              if (konkretneMiesto%6==0||konkretneMiesto%6==1||konkretneMiesto%6==2){
                  oproti = konkretneMiesto + 3;
              }else {
                  oproti = konkretneMiesto - 3;
              }

              if (!obsadene[konkretneMiesto] && !obsadene[oproti]){
                  obsadene[konkretneMiesto] = true;
                  obsadene [oproti] = true;
              }else return;
          }

            if (ktoCheAkeMiesto[i] == -3){
                if (!(konkretneMiesto % 6 == 0 || konkretneMiesto % 6 == 1 || konkretneMiesto % 6 == 2)) {
                    return;
                }else obsadene[konkretneMiesto]=true;
            }

        }
    }

    public static void main(String[] args) {
        sk.upjs.paz.vlak v = new vlak(6);

    }
}
