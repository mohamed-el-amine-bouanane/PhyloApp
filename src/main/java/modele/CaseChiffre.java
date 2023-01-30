//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package modele;

public class CaseChiffre extends CaseGap {
    protected final Case[] cases;
    protected String calcH;
    protected String calcD;
    protected String calcG;
    protected int max;

    public CaseChiffre(int var1, int var2, int var3, Case[] var4) {
        super(var1, var2, var3);
        this.cases = var4;
    }

    protected Case getCasePrecedent() {
        for(int var1 = 0; var1 < 2; ++var1) {
            if (this.cases[var1] != null) {
                return this.cases[var1];
            }
        }

        return this.cases[2];
    }

    public String getStringH() {
        return this.calcH;
    }

    public String getStringD() {
        return this.calcD;
    }

    public String getStringG() {
        return this.calcG;
    }

    public int getMax() {
        return this.max;
    }
}
