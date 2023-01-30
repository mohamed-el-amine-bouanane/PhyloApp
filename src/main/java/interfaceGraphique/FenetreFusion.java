//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package interfaceGraphique;

import modele.GrandModele;
import modele.ModeleGeneral;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class FenetreFusion extends JFrame {
    private static final long serialVersionUID = 1L;
    private GrandModele grandModele;
    private JPanel page = new JPanel();
    private JLabel j;
    private String adn;
    private int match;
    private int mismatch;
    private int gap;

    private ADNAlignement var12;

    public FenetreFusion(GrandModele m, String var6) throws IOException{
        j = new JLabel();
        grandModele = m;
        this.adn = var6;
        this.match = m.getMatch();
        this.mismatch = m.getMismatch();
        this.gap = m.getGap();
        this.setTitle("Matrices de similarité entre espèces");
        this.setSize(500, 200);
        this.setVisible(true);
        this.page.setLayout(new GridLayout(2,2));
        this.page.setOpaque(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel var7 = new JPanel(new GridBagLayout());
        JPanel varAux = new JPanel();

        GridBagConstraints c = new GridBagConstraints();

        /*Iterator var8 = this.grandModele.getListeModele().iterator();

        while(var8.hasNext()) {
            ModeleGeneral var9 = (ModeleGeneral)var8.next();
            JLabel var10 = new JLabel(var9.getEsp1() + " " + var9.getEsp2());
            JButton var11 = new JButton("Accéder à la matrice de similarité");
            var11.addActionListener((var6x) -> {
                ADNAlignement var12;
                if (var6.equals("adn")) {
                    this.dispose();
                    var12 = new ADNAlignement(m, var9,true, "adn",  var9.getSeq1(), var9.getSeq2(),this.grandModele.getNoms(), this.grandModele.getSequences());
                    var12.getPage().setVisible(true);
                } else {
                    this.dispose();
                    var12 = new ADNAlignement(m, var9,true, "base", var9.getSeq1(), var9.getSeq2(), this.grandModele.getNoms(), this.grandModele.getSequences());
                    var12.getPage().setVisible(true);
                }

            });
            var7.add(var10);
            var7.add(var11);
        }*/

        String[] noms = this.grandModele.getNoms().stream().toArray(String[]::new);
        JComboBox<String> choix1 = new JComboBox<>(noms);
        JComboBox<String> choix2 = new JComboBox<>(noms);
        choix1.setVisible(true);
        choix2.setVisible(true);
        varAux.add(choix1);
        varAux.add(choix2);
        var7.add(varAux);
        //var7.add(choix1);
        //var7.add(choix2);
        JButton var11 = new JButton("Accéder à la matrice de similarité");
        var11.addActionListener((event)->{
            String esp1 = choix1.getSelectedItem().toString();
            String esp2 = choix2.getSelectedItem().toString();
            if(esp1.equals(esp2)) {
                if (j.getText().isEmpty()) {
                    j = new JLabel("Vous ne pouvez pas choisir la même espèce");
                    c.fill = GridBagConstraints.CENTER;
                    c.gridx= 0;
                    c.gridy=2;
                    var7.add(j, c);
                }
            }

            else
            {
                for(ModeleGeneral var9 : grandModele.getListeModele())
                {
                    if(var9.getEsp1().equals(esp1) && var9.getEsp2().equals(esp2) || var9.getEsp1().equals(esp2) && var9.getEsp2().equals(esp1))
                    {
                        if (var6.equals("adn")) {
                            this.dispose();
                            var12 = new ADNAlignement(m, var9,true, "adn",  var9.getSeq1(), var9.getSeq2(),this.grandModele.getNoms(), this.grandModele.getSequences());
                            var12.getPage().setVisible(true);
                        } else {
                            this.dispose();
                            var12 = new ADNAlignement(m, var9,true, "base", var9.getSeq1(), var9.getSeq2(), this.grandModele.getNoms(), this.grandModele.getSequences());
                            var12.getPage().setVisible(true);
                        }
                    }
                }
            }
        });

        c.fill = GridBagConstraints.CENTER;
        c.gridx= 0;
        c.gridy=1;
        var7.add(var11, c);
        var7.setPreferredSize(new Dimension(200, 300));
        var7.setMinimumSize(new Dimension(200, 300));
        var7.setMaximumSize(new Dimension(200, 300));
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(var7, "Center");
    }
}
