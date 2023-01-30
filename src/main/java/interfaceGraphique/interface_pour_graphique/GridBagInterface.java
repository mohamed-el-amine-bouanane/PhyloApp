//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package interfaceGraphique.interface_pour_graphique;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JPanel;

public interface GridBagInterface {
    static void gridBag(int var0, int var1, JComponent var2, JPanel var3) {
        GridBagConstraints var4 = new GridBagConstraints();
        var4.gridx = var0;
        var4.gridy = var1;
        var4.fill = 1;
        var3.add(var2, var4);
    }

    static void gridBag(int var0, int var1, JComponent var2, JPanel var3, Insets var4) {
        GridBagConstraints var5 = new GridBagConstraints();
        var5.gridx = var0;
        var5.gridy = var1;
        var5.fill = 1;
        var5.insets = var4;
        var3.add(var2, var5);
    }

    static void gridBag(int var0, int var1, JComponent var2, JPanel var3, Insets var4, int var5) {
        GridBagConstraints var6 = new GridBagConstraints();
        var6.gridx = var0;
        var6.gridy = var1;
        var6.fill = 1;
        var6.insets = var4;
        var6.gridwidth = var5;
        var3.add(var2, var6);
    }

    static void gridBag(int var0, int var1, JComponent var2, JPanel var3, Insets var4, double var5, double var7) {
        GridBagConstraints var9 = new GridBagConstraints();
        var9.gridx = var0;
        var9.gridy = var1;
        var9.fill = 1;
        var9.weightx = var5;
        var9.weighty = var7;
        var9.insets = var4;
        var3.add(var2, var9);
    }
}

