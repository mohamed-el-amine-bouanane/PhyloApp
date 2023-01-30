//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package interfaceGraphique.interface_pour_graphique;

import java.awt.Image;
import javax.swing.ImageIcon;

public interface ImgInterface {
    static ImageIcon resize(String var0, int var1, int var2) {
        ImageIcon var3 = new ImageIcon(var0);
        Image var4 = var3.getImage();
        Image var5 = var4.getScaledInstance(var1, var2, 4);
        var3 = new ImageIcon(var5);
        return var3;
    }
}
