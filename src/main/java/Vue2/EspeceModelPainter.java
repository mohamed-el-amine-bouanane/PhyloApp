package Vue2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author pc
 */
public class EspeceModelPainter extends JLabel implements ListCellRenderer{

    public EspeceModelPainter() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus){
        EspeceModelItems item = (EspeceModelItems) value;
        this.setText(item.getEspecename());
        this.setIcon(item.getIcon());
        if(isSelected){
            setBackground(Color.BLACK);
            setForeground(Color.WHITE);
        }
        if(!isSelected){
            setBackground(Color.white);
            setForeground(Color.black);
        }
        if(cellHasFocus){
            setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        else{
            setBorder(BorderFactory.createEmptyBorder());
        }
        return this;
    }
}


