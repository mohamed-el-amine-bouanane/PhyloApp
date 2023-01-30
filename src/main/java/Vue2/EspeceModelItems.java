package Vue2;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.ImageIcon;

/**
 *
 * @author pc
 */
public class EspeceModelItems {
    public String especename;
    private ImageIcon icon;

    public EspeceModelItems(String especename, ImageIcon icon) {
        super();
        this.especename = especename;
        this.icon = icon;
    }

    public String getEspecename() {
        return this.especename;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setEspecename(String especename) {
        this.especename = especename;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    public String toString(){
        return this.especename;
    }
}

