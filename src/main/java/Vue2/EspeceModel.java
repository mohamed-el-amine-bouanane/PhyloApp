package Vue2;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.io.File;

/**
 *
 * @author pc
 */
public class EspeceModel extends DefaultListModel{

    public EspeceModel() {

    }
    public void init(){
        File folder = new File("./EspeceModelItems/");
        File [] files = folder.listFiles();
        for(File file : files){
            if(file.isFile()){
                EspeceModelItems espece = new EspeceModelItems(file.getName().substring(0,file.getName().length()-4), new ImageIcon("./EspeceModelItems/"+file.getName()));
                addElement(espece);
            }
        }
    }

}
