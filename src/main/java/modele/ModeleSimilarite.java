//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class ModeleSimilarite extends ModeleGeneral {
    protected int[][] similarite;

    public ModeleSimilarite(int var1, String var2, String var3, String var4, String var5) {
        super(var1, var2, var3, var4, var5);
    }

    public abstract int coor(char var1);

    public int valeurMM(char var1, char var2) {
        return this.similarite[this.coor(var1)][this.coor(var2)];
    }

    public int calcScoreCustom() {
        int var1 = 0;
        int var2 = 0;
        int var3 = 0;

        for(int var6 = 0; var6 < this.chemin.size(); ++var6) {
            CaseGap var7 = (CaseGap)this.chemin.get(var6);
            int var4 = var7.i;
            int var5 = var7.j;
            if (var4 != 1 && var4 != var2) {
                if (!(this.matrice[0][var5] instanceof Case) && !(this.matrice[var4][0] instanceof Case)) {
                    var1 += this.valeurMM(((CaseLettre)this.matrice[0][var5]).c, ((CaseLettre)this.matrice[var4][0]).c);
                } else {
                    var1 += this.gap;
                }
            } else {
                var1 += this.gap;
            }

            if (var5 != 1 && var5 != var3) {
                if (!(this.matrice[0][var5] instanceof Case) && !(this.matrice[var4][0] instanceof Case)) {
                    var1 += this.valeurMM(((CaseLettre)this.matrice[0][var5]).c, ((CaseLettre)this.matrice[var4][0]).c);
                } else {
                    var1 += this.gap;
                }
            } else {
                var1 += this.gap;
            }

            var2 = var4;
            var3 = var5;
        }

        return var1;
    }

    public int[][] scannCSV(String var1) throws IOException {
        String var2 = System.getProperty("user.dir") + "/";
        String var3 = var2 + var1 + ".csv";
        BufferedReader var4 = null;
        String var5 = "";
        int var6 = 0;

        try {
            var4 = new BufferedReader(new FileReader(var3));
            int var7 = 1;
            boolean var8 = false;
            var5 = var4.readLine();
            String[] var9 = var5.split(";");

            int var21;
            for(var21 = var9.length; var4.readLine() != null; ++var7) {
            }

            var4 = new BufferedReader(new FileReader(var3));
            this.similarite = new int[var7][var21];

            while((var5 = var4.readLine()) != null) {
                String[] var10 = var5.split(";");
                int var11 = 0;
                String[] var12 = var10;
                int var13 = var10.length;

                for(int var14 = 0; var14 < var13; ++var14) {
                    String var15 = var12[var14];
                    this.similarite[var6][var11] = Integer.parseInt(var15);
                    ++var11;
                }

                ++var6;
                System.out.println();
            }
        } catch (Exception var19) {
            var19.printStackTrace();
        } finally {
            var4.close();
        }

        return this.similarite;
    }
}
