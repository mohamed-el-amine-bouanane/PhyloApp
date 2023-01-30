//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package modele;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class GrandModele {
    protected LinkedList<ModeleGeneral> liste_modeles = new LinkedList();
    protected ArrayList<String> liste_noms = new ArrayList<>();
    protected ArrayList<String> liste_seq = new ArrayList<>();
    protected int min;
    protected int max;

    protected int match;

    protected int mismatch;

    protected int gap;

    public GrandModele(ArrayList<String> var1, ArrayList<String> var2, int var3, int var4, int var5, String var6) throws IOException {
        this.liste_noms = var1;
        this.liste_seq = var2;

        for(int var7 = 0; var7 < var2.size(); ++var7) {
            for(int var8 = var7 + 1; var8 < var2.size(); ++var8) {
                if (var6.equals("base")) {
                    this.liste_modeles.add(new ModeleBasique(var5, var4, var3, (String)var2.get(var7), (String)var2.get(var8), (String)var1.get(var7), (String)var1.get(var8)));
                } else {
                    this.liste_modeles.add(new ModeleADN(var3, (String)var2.get(var7), (String)var2.get(var8), (String)var1.get(var7), (String)var1.get(var8)));
                }
            }
        }

        this.match = var5;
        this.mismatch = var4;
        this.gap = var3;
        this.min = 0;
        this.max = 0;
        this.init();
    }

    public boolean verifier(String var1) {
        for(int var2 = 0; var2 < this.liste_noms.size(); ++var2) {
            String var3 = (String)this.liste_noms.get(var2);
            boolean var4 = false;
            if (var1.equals(var3)) {
                return true;
            }
        }

        return false;
    }

    public void init() {
        this.calcMin();
        this.calcMax();
        this.calcScoreNormalise();
        this.calcDistance();
    }

    public void calcMin() {
        this.min = ((ModeleGeneral)this.liste_modeles.get(0)).scoreF(true);
        Iterator var1 = this.liste_modeles.iterator();

        while(var1.hasNext()) {
            ModeleGeneral var2 = (ModeleGeneral)var1.next();
            int var3 = var2.scoreF(true);
            if (this.min > var3) {
                this.min = var3;
            }
        }

    }

    public void calcMax() {
        this.max = ((ModeleGeneral)this.liste_modeles.get(0)).scoreF(true);
        Iterator var1 = this.liste_modeles.iterator();

        while(var1.hasNext()) {
            ModeleGeneral var2 = (ModeleGeneral)var1.next();
            int var3 = var2.scoreF(true);
            if (this.max < var3) {
                this.max = var3;
            }
        }

    }

    public void calcScoreNormalise() {
        ModeleGeneral var2;
        BigDecimal var3;
        for(Iterator var1 = this.liste_modeles.iterator(); var1.hasNext(); var2.scorenormalise = var3.doubleValue()) {
            var2 = (ModeleGeneral)var1.next();
            var2.scorenormalise = ((double)var2.scoreF(true) - (double)this.min) / (double)(this.max - this.min);
            var3 = (new BigDecimal(var2.scorenormalise)).setScale(2, RoundingMode.HALF_UP);
            var2.scorenormalise = var3.doubleValue();
        }

    }

    public void calcDistance() {
        ModeleGeneral var2;
        BigDecimal var3;
        for(Iterator var1 = this.liste_modeles.iterator(); var1.hasNext(); var2.distance = var3.doubleValue()) {
            var2 = (ModeleGeneral)var1.next();
            var2.distance = 1.0D - var2.scorenormalise;
            var3 = (new BigDecimal(var2.distance)).setScale(2, RoundingMode.HALF_UP);
            var2.distance = var3.doubleValue();
        }

    }

    public LinkedList<ModeleGeneral> getListeModele() {
        return this.liste_modeles;
    }

    public ArrayList<String> getNoms() {
        return this.liste_noms;
    }

    public ArrayList<String> getSequences() {
        return this.liste_seq;
    }

    public int getMatch(){ return this.match;}

    public int getMismatch(){ return this.mismatch;}
    public int getGap(){ return this.gap;}
    
}
