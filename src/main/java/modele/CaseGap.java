//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package modele;

public class CaseGap extends Case {
    protected final int c;
    protected final int i;
    protected final int j;

    public CaseGap(int var1, int var2, int var3) {
        this.c = var1;
        this.i = var2;
        this.j = var3;
    }

    public String toString() {
        return this.c + "";
    }

    public String affichage() {
        return this.c + " [" + this.i + "][" + this.j + "]";
    }

    public int getI() {
        return this.i;
    }

    public int getJ() {
        return this.j;
    }
}

