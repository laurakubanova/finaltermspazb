package sk.upjs.paz;

public class dovolenka {
    //jedna osoba chce byt pri mori a jeden v horach
    //M=2 H=1   2 dni more a jeden den hory 3 moznosti

    public static void main(String[] args) {
        int M = 2;
        int H = 3;
        int m = 1;
        int h = 2;

        int[][] DM = new int[M + 1][H + 1];
        int[][] DH = new int[M + 1][H + 1];
        //vypocet
        //DM[A][B] = sum DH[A-i][B]

        DM[0][0]=DH[0][0]=1;

        for (int a = 1; a <= M; a++) {
            for (int b = 0; b< H; b++){
                for (int i = 0; i < m; i++) {
                    DM[a][b] += DH[a-i][b];
                }

                for (int i = 0; i < h; i++) {
                    DH[a][b] = DM[a][b-i];
                }
            }
        }

        int result = DM[M][H] + DH[M][H];
        System.out.println(result);
    }
}