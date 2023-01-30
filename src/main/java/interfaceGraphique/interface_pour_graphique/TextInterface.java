//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package interfaceGraphique.interface_pour_graphique;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public interface TextInterface {
    static void setText(JLabel var0, String var1, Font var2) {
        var0.setText(var1);
        var0.setFont(var2);
        var0.setForeground(Color.BLACK);
    }

    static void setText(JTextArea var0, String var1, Font var2) {
        var0.setText(var1);
        var0.setFont(var2);
        var0.setForeground(Color.BLACK);
    }
}

