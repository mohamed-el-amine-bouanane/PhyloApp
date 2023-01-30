//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package interfaceGraphique;

import interfaceGraphique.interface_pour_graphique.TextInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Bouton extends JButton {
    private static final long serialVersionUID = 1L;

    public Bouton(int var1, int var2, Color var3, ImageIcon var4) {
        this.setPreferredSize(new Dimension(var1, var2));
        this.setBackground(var3);
        this.setLayout(new BorderLayout());
        JLabel var5 = new JLabel();
        var5.setIcon(var4);
        var5.setHorizontalAlignment(0);
        this.add(var5, "Center");
    }

    public Bouton(int var1, int var2, Color var3, String var4) {
        this.setPreferredSize(new Dimension(var1, var2));
        this.setBackground(var3);
        this.setLayout(new BorderLayout());
        JLabel var5 = new JLabel();
        TextInterface.setText(var5, var4, new Font("Serif", 1, 16));
        var5.setHorizontalAlignment(0);
        this.add(var5, "Center");
    }

    public Bouton(int var1, int var2, Color var3, String var4, Font var5) {
        this.setPreferredSize(new Dimension(var1, var2));
        this.setBackground(var3);
        this.setLayout(new BorderLayout());
        JLabel var6 = new JLabel();
        TextInterface.setText(var6, var4, var5);
        var6.setHorizontalAlignment(0);
        this.add(var6, "Center");
    }
}
