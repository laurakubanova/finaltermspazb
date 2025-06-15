package sk.upjs.paz;

public class Backtrack1 {
    private int x;
    private int y;
    private boolean[] pouzite;
    private int[] perm;
    int[][] udaje;
    int maso = 0;

    private void generuj2(int odIndex) {
        if (udaje.length == odIndex) {
            spracuj();
            return;
        }
        for (int i = 0; i < udaje.length; i++) {
            if (!pouzite[i]) {
                perm[odIndex] = i;
                pouzite[i] = true;
                if (odIndex == 0) {
                    maso = udaje[perm[0]][perm[0]];
                } else {
                    // maso na cestu
                    maso = maso - udaje[perm[odIndex - 1]][perm[odIndex]];
                    if (maso < 0) {
                        // smrt ale opatrne
                        // maso na cestu vratim
                        maso = maso + udaje[perm[odIndex - 1]][perm[odIndex]];
                        pouzite[i] = false;
                        return;
                    }
                    // ulovim
                    maso = maso + udaje[perm[odIndex]][perm[odIndex]];

                }
                // idem do rekurzie
                generuj(odIndex + 1);
                // idem z aktualnej rekurzie
                pouzite[i] = false;
                // ideme generovat inu cestu preto
                // maso na cestu vratim
                maso = maso + udaje[perm[odIndex - 1]][perm[odIndex]];
                // ulovene maso vratim
                maso = maso - udaje[perm[odIndex]][perm[odIndex]];

            }

        }
    }

    private boolean spracujCiastocne(int pokial) {
        // na zaciatku na prvej prerii ulovim bizony
        int maso = udaje[perm[0]][perm[0]];
        // potom idem cez dalsie prerie
        for (int i = 1; i <= pokial; i++) {
            // cestou na preriu zjem maso preto minus
            maso = maso - udaje[perm[i - 1]][perm[i]];
            // ak umrem tak umrem
            if (maso < 0)
                return false;
            // ak som prezil tak na dalsej prerii si ulovim maso
            maso = maso + udaje[perm[i]][perm[i]];
        }
        // ak som nikdy neumrel tak je vsetko ok
        return true;
    }


    private void generuj(int odIndex) {
        if (udaje.length == odIndex) {
            spracuj();
            return;
        }
        for (int i = 0; i < udaje.length; i++) {
            if (!pouzite[i]) {
                perm[odIndex] = i;
                pouzite[i] = true;
                if (spracujCiastocne(odIndex))
                    generuj(odIndex + 1);
                pouzite[i] = false;
            }

        }
    }


    private void spracuj() {
        // tu by bolo
    }

    public static void main(String[] args) {

    }

}



