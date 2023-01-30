//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package modele;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class ModeleGeneral {
    protected final int gap;
    protected final String seq1;
    protected final String seq2;
    protected Case[][] matrice;
    protected LinkedList<CaseGap> chemin = new LinkedList();
    protected LinkedList<CaseGap> optimal_chemin = new LinkedList();
    protected String alignS1;
    protected String alignS2;
    protected static String calcG;
    protected static String calcH;
    protected static String calcD;
    protected double scorenormalise;
    protected double distance;
    protected final String esp1;
    protected final String esp2;

    protected ModeleGeneral(int var1, String var2, String var3, String var4, String var5) {
        this.gap = var1;
        this.seq1 = var2;
        this.seq2 = var3;
        this.matrice = new Case[var2.length() + 2][var3.length() + 2];
        this.esp1 = var4;
        this.esp2 = var5;
    }

    protected void initMatrice() {
        this.matrice[1][1] = new CaseGap(0, 1, 1);

        int var1;
        for(var1 = 2; var1 < this.seq2.length() + 2; ++var1) {
            this.matrice[0][var1] = new CaseLettre(this.seq2.charAt(var1 - 2));
            this.matrice[1][var1] = new CaseGap(this.gap * (var1 - 1), 1, var1);
        }

        for(var1 = 2; var1 < this.seq1.length() + 2; ++var1) {
            this.matrice[var1][0] = new CaseLettre(this.seq1.charAt(var1 - 2));
            this.matrice[var1][1] = new CaseGap(this.gap * (var1 - 1), var1, 1);
        }

    }

    public void affiche() {
        for(int var1 = 0; var1 < this.matrice.length; ++var1) {
            for(int var2 = 0; var2 < this.matrice[var1].length; ++var2) {
                if (this.matrice[var1][var2] == null) {
                    System.out.print("    |");
                } else if (this.matrice[var1][var2].toString().length() == 2) {
                    System.out.printf(String.format(" %s |", this.matrice[var1][var2]));
                } else if (this.matrice[var1][var2].toString().length() == 3) {
                    System.out.printf(String.format(" %s|", this.matrice[var1][var2]));
                } else {
                    System.out.printf(String.format("  %s |", this.matrice[var1][var2]));
                }
            }

            System.out.println();
        }

        System.out.println();
    }

    protected Case[] creerTabCase(int var1, int var2, boolean[] var3) {
        Case[] var4 = new Case[3];
        if (var3[0]) {
            var4[0] = this.matrice[var1 - 1][var2 - 1];
        }

        if (var3[1]) {
            var4[1] = this.matrice[var1 - 1][var2];
        }

        if (var3[2]) {
            var4[2] = this.matrice[var1][var2 - 1];
        }

        return var4;
    }

    protected boolean[] caseValMax(int var1, int var2, int var3, int var4) {
        boolean[] var5 = new boolean[]{false, false, false};
        if (var2 == var1) {
            var5[0] = true;
        }

        if (var3 == var1) {
            var5[1] = true;
        }

        if (var4 == var1) {
            var5[2] = true;
        }

        return var5;
    }

    protected abstract int calculDiag(int var1, int var2);

    protected int calculHaut(int var1, int var2) {
        int var3 = ((CaseGap)this.matrice[var1 - 1][var2]).c;
        calcH = "" + var3 + " + " + this.gap + " = " + (var3 + this.gap);
        return var3 + this.gap;
    }

    protected int calculGauc(int var1, int var2) {
        int var3 = ((CaseGap)this.matrice[var1][var2 - 1]).c;
        calcG = "" + var3 + " + " + this.gap + " = " + (var3 + this.gap);
        return var3 + this.gap;
    }

    protected void remplirUneCase(int var1, int var2) {
        int var3 = this.calculDiag(var1, var2);
        int var4 = this.calculHaut(var1, var2);
        int var5 = this.calculGauc(var1, var2);
        int var6 = Math.max(Math.max(var3, var4), var5);
        boolean[] var7 = this.caseValMax(var6, var3, var4, var5);
        Case[] var8 = this.creerTabCase(var1, var2, var7);
        CaseChiffre var9 = new CaseChiffre(var6, var1, var2, var8);
        var9.calcG = calcG;
        var9.calcD = calcD;
        var9.calcH = calcH;
        var9.max = var6;
        this.matrice[var1][var2] = var9;
    }

    protected void remplirMatrice() {
        for(int var1 = 2; var1 < this.matrice.length; ++var1) {
            for(int var2 = 2; var2 < this.matrice[0].length; ++var2) {
                this.remplirUneCase(var1, var2);
            }
        }

    }

    public Case[][] getMatrice() {
        return this.matrice;
    }

    public String getSeq1() {
        return this.seq1;
    }

    public String getSeq2() {
        return this.seq2;
    }

    public String getAlignS1() {
        return this.alignS1;
    }

    public String getAlignS2() {
        return this.alignS2;
    }

    public LinkedList<CaseGap> getChemin() {
        return this.chemin;
    }

    public void setChemin(LinkedList<CaseGap> var1) {
        this.chemin.clear();
        this.chemin.addAll(var1);
    }

    public void viderChemin() {
        this.chemin.clear();
    }

    public LinkedList<CaseGap> getOptimalChemin() {
        return this.optimal_chemin;
    }

    public void clear_seq() {
        this.alignS1 = "";
        this.alignS2 = "";
    }

    public void alignerSeq() {
        int var1 = 0;
        int var2 = 0;
        int var5 = 0;
        String var6 = "";
        String var7 = "";

        do {
            CaseGap var8 = (CaseGap)this.chemin.get(var5);
            int var3 = var8.i;
            int var4 = var8.j;
            if (var3 != 1 && var3 != var1) {
                var6 = var6 + this.matrice[var3][0].toString();
            } else {
                var6 = var6 + "-";
            }

            if (var4 != 1 && var4 != var2) {
                var7 = var7 + this.matrice[0][var4].toString();
            } else {
                var7 = var7 + "-";
            }

            var1 = var3;
            var2 = var4;
            ++var5;
        } while(var5 < this.chemin.size());

        this.alignS1 = var6;
        this.alignS2 = var7;
    }

    public void creerChemin() {
        int var1 = this.matrice.length - 1;
        int var2 = this.matrice[0].length - 1;
        CaseGap var3 = (CaseGap)this.matrice[var1][var2];
        this.chemin.add(var3);
        this.optimal_chemin.add(var3);

        while(this.chemin.getLast() != (CaseGap)this.matrice[1][1]) {
            if (var3 instanceof CaseChiffre) {
                var3 = (CaseGap)((CaseChiffre)var3).getCasePrecedent();
            } else if (var3.i == 1 && var3.j != 1) {
                var3 = (CaseGap)this.matrice[1][var3.j - 1];
            } else {
                var3 = (CaseGap)this.matrice[var3.i - 1][1];
            }

            this.chemin.add(var3);
            this.optimal_chemin.add(var3);
        }

        this.chemin.removeLast();
        this.optimal_chemin.removeLast();
        Collections.reverse(this.chemin);
        Collections.reverse(this.optimal_chemin);
        this.alignerSeq();
    }

    public int scoreF(boolean var1) {
        return var1 ? ((CaseChiffre)this.matrice[this.matrice.length - 1][this.matrice[this.matrice.length - 1].length - 1]).c : 0;
    }

    public abstract int calcScoreCustom();

    public String getEsp1() {
        return this.esp1;
    }

    public String getEsp2() {
        return this.esp2;
    }

    public double getDistance(){return this.distance;}
    public void textuelle() {
        Scanner var1 = new Scanner(System.in);
        System.out.println("**  Evaluer un score optimal de similarité entre ces deux séquences  **\n");

        try {
            int var7;
            try {
                do {
                    int var2;
                    do {
                        System.out.print("Veuillez saisir «la valeur de match» que vous voulez (entier positif) : ");
                        var2 = var1.nextInt();
                    } while(var2 < 0);

                    int var3;
                    do {
                        System.out.print("Veuillez saisir «la valeur de mismatch» que vous voulez (entier négatif) : ");
                        var3 = var1.nextInt();
                    } while(var3 > 0);

                    int var4;
                    do {
                        System.out.print("Veuillez saisir «la valeur de gap» que vous voulez (entier négatif) : ");
                        var4 = var1.nextInt();
                    } while(var4 > 0);

                    String var5;
                    do {
                        var1.nextLine();
                        System.out.print("Veuillez saisir «la séquence 1» que vous voulez : ");
                        var5 = var1.nextLine();
                    } while(var5.length() == 0);

                    String var6;
                    do {
                        System.out.print("Veuillez saisir «la séquence 2» que vous voulez : ");
                        var6 = var1.nextLine();
                    } while(var6.equals(""));

                    System.out.println();
                    ModeleBasique var8 = new ModeleBasique(var2, var3, var4, var5, var6, "esp1", "esp2");
                    var8.remplirMatrice();
                    var8.affiche();
                    System.out.println("Voulez-vous en évaluer des autres? 1: OUI / 2: NON");
                    var7 = var1.nextInt();
                } while(var7 == 1);
            } catch (InputMismatchException var13) {
                System.out.println("ERREUR - saisissez un bon type d'entrée.");
            }
        } finally {
            var1.close();
        }

    }
}
