//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package modele;

public class ModeleBasique extends ModeleGeneral {
    protected final int match;
    protected final int mismatch;

    public ModeleBasique(int var1, int var2, int var3, String var4, String var5, String var6, String var7) {
        super(var3, var4, var5, var6, var7);
        this.match = var1;
        this.mismatch = var2;
        this.initMatrice();
        this.remplirMatrice();
        this.creerChemin();
    }

    protected int calculDiag(int var1, int var2) {
        int var3 = ((CaseGap)this.matrice[var1 - 1][var2 - 1]).c;
        String var4 = "" + var3 + " + ";
        if (((CaseLettre)this.matrice[0][var2]).c == ((CaseLettre)this.matrice[var1][0]).c) {
            var4 = var4 + this.match + " = " + (var3 + this.match);
            calcD = var4;
            return var3 + this.match;
        } else {
            var4 = var4 + this.mismatch + " = " + (var3 + this.mismatch);
            calcD = var4;
            return var3 + this.mismatch;
        }
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
                if (this.matrice[0][var5] instanceof Case || this.matrice[var4][0] instanceof Case) {
                    var1 += this.gap;
                }

                if (((CaseLettre)this.matrice[0][var5]).c == ((CaseLettre)this.matrice[var4][0]).c) {
                    var1 += this.match;
                } else {
                    var1 += this.mismatch;
                }
            } else {
                var1 += this.gap;
            }

            if (var5 != 1 && var5 != var3) {
                if (!(this.matrice[0][var5] instanceof Case) && !(this.matrice[var4][0] instanceof Case)) {
                    if (((CaseLettre)this.matrice[0][var5]).c == ((CaseLettre)this.matrice[var4][0]).c) {
                        var1 += this.match;
                    } else {
                        var1 += this.mismatch;
                    }
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
}

