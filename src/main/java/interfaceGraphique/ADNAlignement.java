//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package interfaceGraphique;

import interfaceGraphique.interface_pour_graphique.GridBagInterface;
import interfaceGraphique.interface_pour_graphique.ImgInterface;
import interfaceGraphique.interface_pour_graphique.TextInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import modele.*;

public class ADNAlignement extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel panelMatrice = new JPanel(new BorderLayout());
    private JPanel matrice = new JPanel();
    private JPanel autre_option = new JPanel();
    private ModeleGeneral modele;
    private JPanel score = new JPanel(new GridLayout(2, 2));
    JPanel parametre;
    JPanel panelSeq;
    JPanel panelBoutons;
    private boolean light;
    String link = System.getProperty("user.dir") + "/imgAlignement/";
    private Bouton lightMode;
    private Bouton darkMode;
    private Bouton aide;
    private Bouton start;
    private Bouton retour;
    private Bouton clearUp;
    private Bouton optimal_alignement;
    private boolean clear;
    private Bouton custom_path;
    private boolean customTrue;
    private static int acc = 0;
    private JFrame page;
    private AideDialog dialog;
    private static ADNAlignement.CaseInteraction precedent;
    private static Color precedentColor;
    private static Color precprecedentColor;
    Integer mismatch1;
    Integer match1;
    Integer gap1;
    JTextField seq1;
    JTextField seq2;
    JTextField match;
    JTextField mismatch;
    JTextField gap;
    String type;
    String esp1;
    String esp2;

    GrandModele m;

    public ADNAlignement(GrandModele m, ModeleGeneral var4, boolean var5, String var6,String var7,String var8, ArrayList<String> var9, ArrayList<String> var10) {
        this.esp1 = var4.getEsp1();
        this.esp2 = var4.getEsp2();
        this.lightMode = new Bouton(70, 70, new Color(173, 216, 230), ImgInterface.resize(this.link + "light.png", 35, 35));
        this.darkMode = new Bouton(70, 70, new Color(173, 216, 230), ImgInterface.resize(this.link + "dark.png", 35, 35));
        this.aide = new Bouton(70, 70, new Color(173, 216, 230), ImgInterface.resize(this.link + "aide.png", 35, 35));
        this.start = new Bouton(250, 70, new Color(173, 216, 230), "Start");
        this.retour = new Bouton(150, 70, new Color(173, 216, 230), "Retour", new Font("Serif", 1, 12));
        this.clearUp = new Bouton(150, 70, new Color(173, 216, 230), "Clear Path", new Font("Serif", 1, 12));
        this.optimal_alignement = new Bouton(150, 70, new Color(173, 216, 230), "Optimal Alignement", new Font("Serif", 1, 12));
        this.clear = true;
        this.custom_path = new Bouton(150, 70, new Color(173, 216, 230), "Custom Path", new Font("Serif", 1, 12));
        this.customTrue = false;
        this.page = new JFrame();
        this.dialog = new AideDialog();
        this.light = var5;
        this.type = var6;
        this.page.setTitle("ADN Alignement");
        this.page.setExtendedState(6);
        this.page.setVisible(true);
        this.page.setDefaultCloseOperation(3);
        this.autre_option.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.panelMatrice.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.modele = var4;
        this.generatedPage(var4, this.type);
        this.lightMode.setCursor(new Cursor(12));
        this.darkMode.setCursor(new Cursor(12));
        this.retour.setCursor(new Cursor(12));
        this.aide.setCursor(new Cursor(12));
        this.clearUp.setCursor(new Cursor(12));
        this.optimal_alignement.setCursor(new Cursor(12));
        this.custom_path.setCursor(new Cursor(12));
        this.start.setCursor(new Cursor(12));
        this.generatedBoutonListener();
        this.match1 = m.getMatch();
        this.mismatch1 = m.getMismatch();
        this.gap1 = m.getGap();
        this.m = m;
        this.page.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void generatedBoutonListener() {
        this.retour.addActionListener((var1) -> {
            this.customTrue = false;
            acc = 0;
            this.modele.setChemin(this.modele.getOptimalChemin());
            this.modele.alignerSeq();
            this.page.dispose();

            try {
                FenetreFusion var2 = new FenetreFusion(m, this.type);
                var2.setVisible(true);
            } catch (IOException var4) {
                var4.printStackTrace();
            }

        });
        this.lightMode.addActionListener((var1) -> {
            this.removeALL();
            this.light = true;
            this.generatedPage(this.modele, this.type);
            this.refresh();
        });
        this.darkMode.addActionListener((var1) -> {
            this.removeALL();
            this.light = false;
            this.generatedPage(this.modele, this.type);
            this.refresh();
        });
        this.aide.addActionListener((var1) -> {
            this.dialog.showDialog();
        });
        this.clearUp.addActionListener((var1) -> {
            this.customTrue = false;
            acc = 0;
            this.removeALL();
            this.clear = false;
            this.modele.viderChemin();
            this.modele.clear_seq();
            this.generatedPage(this.modele, this.type);
            this.refresh();
        });
        this.optimal_alignement.addActionListener((var1) -> {
            this.customTrue = false;
            acc = 0;
            this.removeALL();
            this.clear = true;
            this.modele.setChemin(this.modele.getOptimalChemin());
            this.modele.alignerSeq();
            this.generatedPage(this.modele, this.type);
            this.refresh();
        });
        this.custom_path.addActionListener((var1) -> {
            acc = 0;
            this.removeALL();
            this.modele.viderChemin();
            this.modele.clear_seq();
            this.customTrue = true;
            this.generatedPage(this.modele, this.type);
            this.refresh();
        });
    }

    public void generatedPage(ModeleGeneral var1, String var2) {
        //System.out.println("GENERATED");
        this.page.setLayout(new GridLayout(0, 2));
        this.autre_option.setLayout(new GridBagLayout());
        int var3 = var1.getSeq1().length() + 2;
        int var4 = var1.getSeq2().length() + 2;
        this.generatedMatrice(var1, var3, var4);
        this.generatedPanelBouton();
        GridBagInterface.gridBag(0, 0, this.panelBoutons, this.autre_option, new Insets(0, 0, 0, 0), 0.5D, 0.1D);
        this.creationEntreeDeValeur();
        this.generatedSequence();
        GridBagInterface.gridBag(0, 2, this.panelSeq, this.autre_option, new Insets(0, 0, 0, 0), 0.5D, 0.1D);
        GridBagInterface.gridBag(0, 3, this.score, this.autre_option, new Insets(0, 0, 0, 0), 0.5D, 0.1D);
        this.generatedTitre();
        this.darkAndLightMode();
        this.page.add(this.panelMatrice);
        this.page.add(this.autre_option);
    }

    public void generatedMatrice(ModeleGeneral var1, int var2, int var3) {
        this.matrice.setLayout(new GridLayout(var2, var3));
        this.matrice.setOpaque(false);
        Case[][] var4 = var1.getMatrice();
        JLabel[][] var5 = new JLabel[var2][var3];

        for(int var6 = 0; var6 < var2; ++var6) {
            for(int var7 = 0; var7 < var3; ++var7) {
                if (var4[var6][var7] != null && var4[var6][var7] instanceof CaseGap) {
                    var5[var6][var7] = new JLabel(var4[var6][var7].toString(), 0);
                    ADNAlignement.CaseInteraction var9 = new ADNAlignement.CaseInteraction(var6, var7);
                    var9.setLayout(new BorderLayout());
                    var5[var6][var7].setHorizontalAlignment(0);
                    var5[var6][var7].setFont(new Font("Serif", 1, 20));
                    var9.add(var5[var6][var7], "Center");
                    if (var1.getChemin().contains(var4[var6][var7])) {
                        if (this.clear) {
                            if (var4[var6][var7] instanceof CaseGap && !(var4[var6][var7] instanceof CaseChiffre)) {
                                var9.setBackground(Color.YELLOW);
                            } else {
                                var9.setBackground(Color.RED);
                            }
                        }
                    } else if (var4[var6][var7] instanceof CaseGap && !(var4[var6][var7] instanceof CaseChiffre)) {
                        var9.setBackground(Color.GREEN);
                    } else if (!this.light) {
                        var9.setBackground(Color.GRAY);
                    } else {
                        var9.setBackground(Color.WHITE);
                    }

                    this.matrice.add(var9);
                    var9.setBorder(new LineBorder(Color.BLACK, 1));
                } else {
                    JPanel var8;
                    if (var4[var6][var7] instanceof CaseLettre) {
                        var5[var6][var7] = new JLabel(var4[var6][var7].toString(), 0);
                        var8 = new JPanel(new BorderLayout());
                        var5[var6][var7].setHorizontalAlignment(0);
                        var5[var6][var7].setFont(new Font("Serif", 1, 20));
                        var8.add(var5[var6][var7], "Center");
                        var8.setBackground(Color.ORANGE);
                        this.matrice.add(var8);
                        var8.setBorder(new LineBorder(Color.BLACK, 1));
                    } else {
                        var8 = new JPanel(new BorderLayout());
                        var8.setBorder(new LineBorder(Color.BLACK, 1));
                        var8.setBackground(Color.BLACK);
                        var5[var6][var7] = new JLabel(" ");
                        var8.add(var5[var6][var7], "Center");
                        this.matrice.add(var8);
                    }
                }
            }
        }

    }

    public void generatedTitre() {
        JPanel var1 = new JPanel();
        JLabel var2 = new JLabel("<html><u>Matrice d'alignement d'ADN</u></html>");
        var2.setFont(new Font("Serif", 1, 25));
        var2.setHorizontalAlignment(0);
        var1.add(var2);
        var1.setOpaque(false);
        var1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.panelMatrice.add(var1, "North");
        this.panelMatrice.add(this.matrice, "Center");
    }

    public void darkAndLightMode() {
        if (this.light) {
            this.autre_option.setBackground(Color.WHITE);
            this.parametre.setBackground(Color.WHITE);
            this.panelMatrice.setBackground(Color.WHITE);
        } else {
            this.autre_option.setBackground(Color.GRAY);
            this.parametre.setBackground(Color.GRAY);
            this.panelMatrice.setBackground(Color.GRAY);
        }

    }

    public void generatedPanelBouton() {
        this.panelBoutons = new JPanel(new GridLayout(3, 3, 5, 5));
        this.panelBoutons.add(this.lightMode);
        this.panelBoutons.add(this.darkMode);
        this.panelBoutons.add(this.aide);
        this.panelBoutons.add(this.retour);
        this.panelBoutons.add(this.clearUp);
        this.panelBoutons.add(this.optimal_alignement);
        this.panelBoutons.add(this.custom_path);
        this.panelBoutons.setOpaque(false);
        this.score.setOpaque(false);
        this.score.setPreferredSize(new Dimension(500, 300));
    }

    public void creationEntreeDeValeur() {
        JLabel var1 = new JLabel("Valeur de match      ");
        this.match = new JTextField("");
        this.match.setMinimumSize(new Dimension(50, 24));
        JLabel var2 = new JLabel("Valeur de mismatch      ");
        this.mismatch = new JTextField("");
        this.mismatch.setMinimumSize(new Dimension(50, 24));
        JLabel var3 = new JLabel("Valeur de gap      ");
        this.gap = new JTextField("");
        this.gap.setMinimumSize(new Dimension(50, 24));
        JLabel var4 = new JLabel("seq1      ");
        this.seq1 = new JTextField("");
        this.seq1.setMinimumSize(new Dimension(50, 24));
        JLabel var5 = new JLabel("seq2      ");
        this.seq2 = new JTextField("");
        this.seq2.setMinimumSize(new Dimension(50, 24));
        JPanel var6 = new JPanel();
        var6.add(this.start);
        var6.setOpaque(false);
        this.parametre = new JPanel(new GridBagLayout());
        this.match.setPreferredSize(new Dimension(200, 24));
        this.mismatch.setPreferredSize(new Dimension(200, 24));
        this.gap.setPreferredSize(new Dimension(200, 24));
        this.seq1.setPreferredSize(new Dimension(200, 24));
        this.seq2.setPreferredSize(new Dimension(200, 24));
        this.seq1.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent var1) {
            }

            public void keyPressed(KeyEvent var1) {
            }

            public void keyReleased(KeyEvent var1) {
                int var2 = ADNAlignement.this.seq1.getCaretPosition();
                ADNAlignement.this.seq1.setText(ADNAlignement.this.seq1.getText().toUpperCase());
                ADNAlignement.this.seq1.setCaretPosition(var2);
            }
        });
        this.seq2.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent var1) {
            }

            public void keyPressed(KeyEvent var1) {
            }

            public void keyReleased(KeyEvent var1) {
                int var2 = ADNAlignement.this.seq2.getCaretPosition();
                ADNAlignement.this.seq2.setText(ADNAlignement.this.seq2.getText().toUpperCase());
                ADNAlignement.this.seq2.setCaretPosition(var2);
            }
        });
        this.start.addActionListener((var1x) -> {
            String varm = this.type;
            byte vara = -1;
            switch(varm.hashCode()) {
                case 96427:
                    if (varm.equals("adn")) {
                        vara = 0;
                    }
                    break;
                case 3016401:
                    if (varm.equals("base")) {
                        vara = 1;
                    }
            }

            switch(vara) {
                case 0:
                    try {
                        this.verifADN(this.parametre);
                    } catch (IOException vars) {
                        vars.printStackTrace();
                    }
                    break;
                case 1:
                    this.verifBase(this.parametre);
            }

        });
        MatteBorder var7 = BorderFactory.createMatteBorder(15, 15, 15, 15, Color.CYAN);
        this.parametre.setBorder(new CompoundBorder(var7, new EmptyBorder(10, 10, 10, 10)));
        JLabel var8 = new JLabel();
        TextInterface.setText(var8, "<html><u>Paramètre</u></html>", new Font("Serif", 1, 20));
        GridBagInterface.gridBag(0, 0, var8, this.parametre, new Insets(0, 0, 15, 0));
        if (this.type.equals("base")) {
            GridBagInterface.gridBag(0, 1, var1, this.parametre, new Insets(0, 0, 5, 0));
            GridBagInterface.gridBag(1, 1, this.match, this.parametre, new Insets(0, 0, 5, 0));
            GridBagInterface.gridBag(0, 2, var2, this.parametre, new Insets(0, 0, 5, 0));
            GridBagInterface.gridBag(1, 2, this.mismatch, this.parametre, new Insets(0, 0, 5, 0));
        }

        GridBagInterface.gridBag(0, 3, var3, this.parametre, new Insets(0, 0, 5, 0));
        GridBagInterface.gridBag(1, 3, this.gap, this.parametre, new Insets(0, 0, 5, 0));
        GridBagInterface.gridBag(0, 4, var4, this.parametre, new Insets(0, 0, 5, 0));
        GridBagInterface.gridBag(1, 4, this.seq1, this.parametre, new Insets(0, 0, 5, 0));
        GridBagInterface.gridBag(0, 5, var5, this.parametre, new Insets(0, 0, 5, 0));
        GridBagInterface.gridBag(1, 5, this.seq2, this.parametre, new Insets(0, 0, 5, 0));
        GridBagInterface.gridBag(0, 6, var6, this.parametre, new Insets(25, 0, 5, 0), 2);
        GridBagInterface.gridBag(0, 1, this.parametre, this.autre_option, new Insets(0, 0, 0, 0), 0.5D, 0.1D);
    }

    public void actionPerformed(ActionEvent var1) {
    }

    public void removeALL() {
        this.page.getContentPane().removeAll();
        this.score.removeAll();
        this.autre_option.removeAll();
        this.panelMatrice.removeAll();
        this.matrice.removeAll();
    }

    public void refresh() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ADNAlignement.this.score.validate();
                ADNAlignement.this.score.repaint();
                ADNAlignement.this.score.updateUI();
                ADNAlignement.this.autre_option.validate();
                ADNAlignement.this.autre_option.repaint();
                ADNAlignement.this.autre_option.updateUI();
                ADNAlignement.this.page.validate();
                ADNAlignement.this.page.repaint();
            }
        });
    }

    public void ajoutScore() {
        this.score.setPreferredSize(new Dimension(500, 300));
        GridBagInterface.gridBag(0, 3, this.score, this.autre_option, new Insets(0, 0, 0, 0), 0.5D, 0.1D);
    }

    public void generatedScore(int var1, int var2) {
        JPanel var3 = new JPanel(new BorderLayout());
        JLabel var4 = new JLabel("Score diagonal : " + ((CaseChiffre)this.modele.getMatrice()[var1][var2]).getStringD());
        var4.setFont(new Font("Serif", 1, 20));
        var4.setHorizontalAlignment(0);
        var3.add(var4, "Center");
        var3.setBorder(new LineBorder(Color.BLACK, 1));
        JPanel var5 = new JPanel(new BorderLayout());
        JLabel var6 = new JLabel("Score haut : " + ((CaseChiffre)this.modele.getMatrice()[var1][var2]).getStringH());
        var6.setFont(new Font("Serif", 1, 20));
        var6.setHorizontalAlignment(0);
        var5.add(var6, "Center");
        var5.setBorder(new LineBorder(Color.BLACK, 1));
        JPanel var7 = new JPanel(new BorderLayout());
        JLabel var8 = new JLabel("Score gauche : " + ((CaseChiffre)this.modele.getMatrice()[var1][var2]).getStringG());
        var8.setFont(new Font("Serif", 1, 20));
        var8.setHorizontalAlignment(0);
        var7.add(var8, "Center");
        var7.setBorder(new LineBorder(Color.BLACK, 1));
        JPanel var9 = new JPanel(new BorderLayout());
        JLabel var10 = new JLabel("Score max = " + ((CaseChiffre)this.modele.getMatrice()[var1][var2]).getMax());
        var10.setFont(new Font("Serif", 1, 20));
        var10.setHorizontalAlignment(0);
        var9.add(var10, "Center");
        var9.setBorder(new LineBorder(Color.BLACK, 1));
        this.score.add(var3);
        this.score.add(var5);
        this.score.add(var7);
        this.score.add(var9);
    }

    public void generatedSequence() {
        JPanel var1 = new JPanel(new BorderLayout());
        JPanel var2 = new JPanel(new BorderLayout());
        JPanel var3 = new JPanel(new BorderLayout());
        var1.setOpaque(false);
        var2.setOpaque(false);
        var3.setOpaque(false);
        JLabel var4 = new JLabel(this.esp1+" " + this.modele.getAlignS1());
        JLabel var5 = new JLabel(this.esp2+" " + this.modele.getAlignS2());
        JLabel var6;
        if (this.customTrue) {
            var6 = new JLabel("Score = " + this.modele.calcScoreCustom());
        } else {
            var6 = new JLabel("Score = " + this.modele.scoreF(this.clear));
        }

        var4.setFont(new Font("Courier New", 1, 20));
        var5.setFont(new Font("Courier New", 1, 20));
        var6.setFont(new Font("Courier New", 1, 20));
        var4.setHorizontalAlignment(0);
        var5.setHorizontalAlignment(0);
        var6.setHorizontalAlignment(0);
        var1.add(var4, "Center");
        var2.add(var5, "Center");
        var3.add(var6, "Center");
        this.panelSeq = new JPanel(new GridLayout(3, 1));
        this.panelSeq.setPreferredSize(new Dimension(200, 200));
        this.panelSeq.setOpaque(false);
        this.panelSeq.add(var1);
        this.panelSeq.add(var2);
        this.panelSeq.add(var3);
        this.panelSeq.setBackground(Color.GRAY);
    }

    public JFrame getPage() {
        return this.page;
    }

    public void verifBase(JPanel var1) {
        if (this.verifNbr(this.match.getText())) {
            this.match1 = Integer.valueOf(this.match.getText());
        }

        if (this.verifNbr(this.mismatch.getText())) {
            this.mismatch1 = Integer.valueOf(this.mismatch.getText());
        }

        if (this.verifNbr(this.gap.getText())) {
            this.gap1 = Integer.valueOf(this.gap.getText());
        }

        System.out.println(this.match1 + " ;" + this.mismatch1 + " ;" + this.gap1 + " ;" + this.seq1.getText() + " ;" + this.seq2.getText());
        if (this.verifSeqADN(this.seq1.getText()) && this.verifSeqADN(this.seq2.getText()) && this.match1 != null && this.mismatch1 != null && this.gap1 != null) {
            this.modele = new ModeleBasique(this.match1, this.mismatch1, this.gap1, this.seq1.getText(), this.seq2.getText(), this.esp1, this.esp2);
            this.removeALL();
            this.generatedPage(this.modele, this.type);
            this.refresh();
        } else if (this.verifSeqADN(this.seq1.getText()) && this.verifSeqADN(this.seq2.getText())) {
            this.messageErreur(var1, "Veuillez mettre des bonnes valeurs pour le gap , le match ou le mismatch .");
        } else {
            this.messageErreur(var1, "Veuillez mettre des bonnes séquences d'ADN .");
        }

    }

    public void verifADN(JPanel var1) throws IOException {
        if (this.verifNbr(this.gap.getText())) {
            this.gap1 = Integer.valueOf(this.gap.getText());
        }

        if (this.verifSeqADN(this.seq1.getText()) && this.verifSeqADN(this.seq2.getText()) && this.gap1 != null) {
            this.modele = new ModeleADN(this.gap1, this.seq1.getText(), this.seq2.getText(), this.esp1, this.esp2);
            this.removeALL();
            this.generatedPage(this.modele, this.type);
            this.refresh();
        } else if (this.verifSeqADN(this.seq1.getText()) && this.verifSeqADN(this.seq2.getText())) {
            this.messageErreur(var1, "Veuillez mettre des bonnes valeurs pour le gap .");
        } else {
            this.messageErreur(var1, "Veuillez mettre des bonnes séquences d'ADN .");
        }

    }

    private boolean verifSeqADN(String var1) {
        if (var1.isEmpty()) {
            return false;
        } else {
            char[] var2 = var1.toCharArray();
            char[] var3 = var2;
            int var4 = var2.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                char var6 = var3[var5];
                if (var6 != 'A' && var6 != 'C' && var6 != 'T' && var6 != 'G') {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean verifNbr(String var1) {
        String var2 = var1;
        if (var1.isEmpty()) {
            return false;
        } else {
            if (var1.charAt(0) == '-') {
                var2 = var1.substring(1);
            }

            char[] var3 = var2.toCharArray();
            char[] var4 = var3;
            int var5 = var3.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                char var7 = var4[var6];
                if (!Character.isDigit(var7)) {
                    return false;
                }
            }

            return true;
        }
    }

    public void messageErreur(JPanel var1, String var2) {
        JPanel var3 = new JPanel();
        JLabel var4 = new JLabel();
        TextInterface.setText(var4, "<html><u>Message d'erreur :<font color='red'>" + var2 + "</font></u></html>", new Font("Serif", 1, 20));
        var4.setHorizontalAlignment(0);
        var3.add(var4);
        GridBagInterface.gridBag(0, 7, var4, var1, new Insets(25, 0, 5, 0), 2);
        this.refresh();
    }

    public class CaseInteraction extends JPanel implements MouseListener {
        private static final long serialVersionUID = 1L;
        private int i;
        private int j;

        public CaseInteraction(int var2, int var3) {
            this.i = var2;
            this.j = var3;
            this.addMouseListener(this);
        }

        public void mouseClicked(MouseEvent var1) {
            if (!ADNAlignement.this.customTrue && ADNAlignement.this.modele.getMatrice()[this.i][this.j].getClass().getName().equals("modele.CaseChiffre")) {
                ADNAlignement.precprecedentColor = ADNAlignement.precedentColor;
                ADNAlignement.precedentColor = this.getBackground();
                ADNAlignement.this.autre_option.remove(ADNAlignement.this.score);
                ADNAlignement.this.score.removeAll();
                this.setBackground(Color.PINK);
                if (ADNAlignement.precedent != null) {
                    if (ADNAlignement.precprecedentColor == Color.RED) {
                        ADNAlignement.precedent.setBackground(Color.RED);
                    } else if (ADNAlignement.precprecedentColor == Color.PINK && ADNAlignement.precedentColor == Color.RED) {
                        ADNAlignement.precedent.setBackground(Color.RED);
                    } else if (ADNAlignement.this.light) {
                        ADNAlignement.precedent.setBackground(Color.WHITE);
                    } else {
                        ADNAlignement.precedent.setBackground(Color.GRAY);
                    }
                }

                ADNAlignement.precedent = this;
                ADNAlignement.this.generatedScore(this.i, this.j);
                ADNAlignement.this.ajoutScore();
                ADNAlignement.this.refresh();
            } else if (ADNAlignement.this.customTrue) {
                if (ADNAlignement.acc == 0) {
                    ADNAlignement.this.modele.getChemin().add((CaseGap)ADNAlignement.this.modele.getMatrice()[this.i][this.j]);
                    if (this.i != 1 && this.j != 1) {
                        this.setBackground(Color.RED);
                    } else {
                        this.setBackground(Color.YELLOW);
                    }

                    ADNAlignement.acc++;
                    ADNAlignement.this.modele.alignerSeq();
                } else {
                    CaseGap var2 = (CaseGap)ADNAlignement.this.modele.getChemin().getLast();
                    if (this.i == var2.getI() && this.j == var2.getJ() - 1 || this.i == var2.getI() - 1 && this.j == var2.getJ() - 1 || this.i == var2.getI() - 1 && this.j == var2.getJ()) {
                        ADNAlignement.this.modele.getChemin().add((CaseGap)ADNAlignement.this.modele.getMatrice()[this.i][this.j]);
                        if (this.i != 1 && this.j != 1) {
                            this.setBackground(Color.RED);
                        } else {
                            this.setBackground(Color.YELLOW);
                        }
                    }

                    ADNAlignement.acc++;
                }

                ADNAlignement.this.modele.alignerSeq();
                ADNAlignement.this.autre_option.remove(ADNAlignement.this.panelSeq);
                ADNAlignement.this.generatedSequence();
                GridBagInterface.gridBag(0, 2, ADNAlignement.this.panelSeq, ADNAlignement.this.autre_option, new Insets(0, 0, 0, 0), 0.5D, 0.0D);
                ADNAlignement.this.refresh();
            }

        }

        public void mouseEntered(MouseEvent var1) {
            if (ADNAlignement.this.modele.getMatrice()[this.i][this.j].getClass().getName().equals("modele.CaseChiffre")) {
                String var2 = ADNAlignement.this.modele.getSeq1();
                String var3 = ADNAlignement.this.modele.getSeq2();
                this.setToolTipText("(" + var2.substring(this.i - 2, this.i - 1) + ", " + var3.substring(this.j - 2, this.j - 1) + ")");
            }

        }

        public void mouseExited(MouseEvent var1) {
        }

        public void mousePressed(MouseEvent var1) {
        }

        public void mouseReleased(MouseEvent var1) {
        }
    }
}
