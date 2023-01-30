package Controller;

import Vue2.*;

import java.io.File;
import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws IOException {

        File folder = new File("./images/");
        folder.mkdir();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Board L= new Board();
                L.setVisible(true);
            }
        });
        File [] files = folder.listFiles();
        for(File file : files){
            if(file.isFile()){
                file.delete();
            }
        }

    }
}