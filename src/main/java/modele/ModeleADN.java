//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package modele;

import java.io.IOException;

public class ModeleADN extends ModeleSimilarite {
    public ModeleADN(int var1, String var2, String var3, String var4, String var5) throws IOException {
        super(var1, var2, var3, var4, var5);
        this.initSimilarite();
        this.initMatrice();
        this.remplirMatrice();
        this.creerChemin();
    }

    public void initSimilarite() throws IOException {
        this.similarite = this.scannCSV("matriceADN1");
    }

    public int coor(char var1) {
        switch(var1) {
            case 'A':
                return 0;
            case 'C':
                return 3;
            case 'G':
                return 2;
            case 'T':
                return 1;
            default:
                return -1;
        }
    }

    protected int calculDiag(int var1, int var2) {
        int var3 = ((CaseGap)this.matrice[var1 - 1][var2 - 1]).c;
        calcD = "" + var3 + " + " + this.valeurMM(((CaseLettre)this.matrice[0][var2]).c, ((CaseLettre)this.matrice[var1][0]).c) + " = " + (var3 + this.valeurMM(((CaseLettre)this.matrice[0][var2]).c, ((CaseLettre)this.matrice[var1][0]).c));
        return var3 + this.valeurMM(((CaseLettre)this.matrice[0][var2]).c, ((CaseLettre)this.matrice[var1][0]).c);
    }
}
