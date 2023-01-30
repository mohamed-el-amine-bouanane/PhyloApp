//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package interfaceGraphique;

import interfaceGraphique.interface_pour_graphique.GridBagInterface;
import interfaceGraphique.interface_pour_graphique.ImgInterface;
import interfaceGraphique.interface_pour_graphique.TextInterface;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class AideDialog {
    GridBagConstraints gbc;
    private JDialog dialog;
    private JScrollPane scroll;

    public AideDialog(int var1, int var2) {
        this.gbc = new GridBagConstraints();
        this.dialog = new JDialog();
        JPanel var3 = new JPanel(new GridBagLayout());
        this.setImgAide(var3);
        this.scroll = new JScrollPane(var3, 22, 30);
        this.dialog.getContentPane().setLayout(new BorderLayout(0, 0));
        this.dialog.getContentPane().add(this.scroll, "Center");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AideDialog.this.scroll.getVerticalScrollBar().setValue(AideDialog.this.scroll.getVerticalScrollBar().getMinimum());
            }
        });
        this.dialog.pack();
        this.dialog.setSize(var1, var2);
    }

    public AideDialog() {
        this(950, 800);
    }

    public JDialog getDialog() {
        return this.dialog;
    }

    public JScrollPane getScroll() {
        return this.scroll;
    }

    public void setImgAide(JPanel var1) {
        byte var2 = 0;
        String var3 = System.getProperty("user.dir") + "/imgAlignement/";
        JLabel var4 = new JLabel();
        TextInterface.setText(var4, "<html><u>Le but de cette application</u></html>", new Font("Serif", 1, 25));
        GridBagInterface.gridBag(0, var2, var4, var1, new Insets(25, 10, 25, 0));
        int var11 = var2 + 1;
        GridBagInterface.gridBag(0, var11, this.creerTexte("Le but de cette application est d'illustrer visuellement comment la matrice d'alignement est construite et comment l'algorithme de programmation dynamique Needleman-Wunsch remplit cette matrice en fonction des scores de match, de mismatch et de gap définis par l'utilisateur."), var1, new Insets(10, 10, 0, 0));
        ++var11;
        JLabel var5 = new JLabel();
        TextInterface.setText(var5, "<html><u>Le principe</u></html>", new Font("Serif", 1, 25));
        GridBagInterface.gridBag(0, var11, var5, var1, new Insets(25, 10, 25, 0));
        ++var11;
        GridBagInterface.gridBag(0, var11, this.creerTexte("L'objectif de l'alignement de séquences est de rechercher le maximum d'appariements entre les nucléotides (=lettres) des deux séquences comparées. Pour ce faire, on va rechercher à maximiser un score d’alignement.\nOn s'appuiera sur l'alignement de la figure ci-dessous pour expliquer le principe.\nPour aligner les séquences 1 (\"GATTACA\", longueur = 7) et 2 (\"GTCGACGCA\", longueur = 9) une matrice de taille 8*10 est remplie.\nVous remarquez que l'utilisateur définit des scores de \"match\", \"miscmatch\" et de \"gap\".\nCes scores permettent de valoriser l'identité entre les éléments des deux séquences et de pénaliser la substitution et l'insertion de gap.\nL'identité signifie que les deux lettres comparées sont identiques (ex \"G\" et \"G\"), la substitution signifie qu'elles sont distinctes (ex \"T\" et \"A\"), le gap signifiequ'un trou est inséré dans la séquence (\"-\")."), var1, new Insets(10, 10, 10, 0));
        ++var11;
        GridBagInterface.gridBag(0, var11, this.creerImage(ImgInterface.resize(var3 + "exempPrincipe.jpg", 654, 326)), var1, new Insets(10, 10, 10, 0));
        ++var11;
        JLabel var6 = new JLabel();
        TextInterface.setText(var6, "<html><u>Remplissage de la matrice</u></html>", new Font("Serif", 1, 25));
        GridBagInterface.gridBag(0, var11, var6, var1, new Insets(25, 10, 25, 0));
        ++var11;
        GridBagInterface.gridBag(0, var11, this.creerTexte("Soient x[1..n] et y[1..m] deux séquences à aligner (x horizontal, y vertical).\nOn nomme S_match le score de match, S_mismatch le score de mismatch et d la pénalité de gap (dans l’exemple S_match = 1, S_mismatch = −1, et d = −2).\nOn nomme s(a, b), la fonction de score telle que :"), var1, new Insets(10, 10, 10, 0));
        ++var11;
        GridBagInterface.gridBag(0, var11, this.creerImage(ImgInterface.resize(var3 + "exempRemp1.jpg", 283, 78)), var1, new Insets(10, 10, 10, 0));
        ++var11;
        GridBagInterface.gridBag(0, var11, this.creerTexte("Soit F la matrice d'alignement de m + 1 lignes et n + 1 colonnes. La première ligne et la première colonne de la matrice sont initialisées à l’aide de la pénalité de gap :\n\t- F[i][0] = d ∗ i\n\t- F[0][j] = d ∗ j\n\nOn a ensuite 3 façons d'étendre un alignement de x_i à y_i :\n\t- x_i et y_i sont alignés : on a avancé les deux mots en alignant deux lettres\n\t- x_i est aligné avec un gap : on a avancé dans x, mais pas dans y\n\t- y_i est aligné avec un gap : on a avancé dans y, mais pas dans x\n"), var1, new Insets(10, 10, 10, 0));
        ++var11;
        GridBagInterface.gridBag(0, var11, this.creerImage(ImgInterface.resize(var3 + "exempRemp2.jpg", 449, 106)), var1, new Insets(10, 10, 10, 0));
        ++var11;
        JLabel var7 = new JLabel();
        TextInterface.setText(var7, "<html><u>Backtracking</u></html>", new Font("Serif", 1, 25));
        GridBagInterface.gridBag(0, var11, this.creerTexte("Une fois la matrice remplie, le chemin pour obtenir le score final, c'est-à-dire le score correspondant à la case F[m][n] est retrouvé.\nDans l'exemple, ce score est de -3, et il correspond à l’alignement :"), var1, new Insets(10, 10, 10, 0));
        ++var11;
        GridBagInterface.gridBag(0, var11, this.creerImage(ImgInterface.resize(var3 + "exempBackt.jpg", 332, 88)), var1, new Insets(10, 10, 10, 0));
        ++var11;
        JLabel var8 = new JLabel();
        TextInterface.setText(var8, "<html><u>Comment utiliser cette application ?</u></html>", new Font("Serif", 1, 25));
        GridBagInterface.gridBag(0, var11, var8, var1, new Insets(25, 10, 25, 0));
        ++var11;
        GridBagInterface.gridBag(0, var11, this.creerTexte("Pour utiliser cette application, choisissez l'une des types d'alignement suivants :\n\t - Basique (type ADN avec valeur de match et mismatch à saisir)\n\t - ADN (Acide DésoxyriboNucléique)\n\t - AA (Acide Aminé)"), var1, new Insets(10, 10, 0, 0));
        ++var11;
        JLabel var9 = new JLabel();
        TextInterface.setText(var9, "<html><u>Les différents types de modèle</u></html>", new Font("Serif", 1, 25));
        GridBagInterface.gridBag(0, var11, this.creerTexte(" ==> Le type Basique (type ADN avec valeur de match et mismatch à saisir) :\nPour utiliser cette application avec le type Basique, il faut remplir les champs suivant :\nvaleur de match, valeur de mismatch, valeur de gap (avec un entier),\nseq1 et seq2 (avec une chaîne de caractère qui contient que des G, A, T, C).\n\n ==> Le type ADN (Acide DésoxyriboNucléique):\nPour utiliser cette application avec le type ADN, il faut remplir les champs suivant :\nvaleur de gap (avec un entier), seq1 et seq2 (avec une chaîne de caractère qui contient que des G, A, T, C).\n\n ==> Le type AA (Acide Aminé):\nPour utiliser cette application avec le type AA, il faut remplir les champs suivant :\nvaleur de gap (avec un entier), seq1 et seq2 (avec une chaîne de caractère qui contient que\ndes A, R, N, D, C, Q, E, G, H, I, L, K, M, F, P, S, T, W, Y, V)."), var1, new Insets(10, 10, 0, 0));
        ++var11;
        GridBagInterface.gridBag(0, var11, this.creerLegende(ImgInterface.resize(var3 + "attention.png", 100, 100), "   Attention, les valeurs qui ont été saisies alors qu'elles ne sont pas nécessaire ne seront pas pris en compte."), var1, new Insets(10, 10, 10, 0));
        ++var11;
        JLabel var10 = new JLabel();
        TextInterface.setText(var10, "<html><u>Les cases</u></html>", new Font("Serif", 1, 25));
        GridBagInterface.gridBag(0, var11, var10, var1, new Insets(25, 10, 25, 0));
        ++var11;
        GridBagInterface.gridBag(0, var11, this.creerLegende(ImgInterface.resize(var3 + "caseLettre.png", 70, 70), "   Case lettre : Case affichant une lettre de la séquence actuelle."), var1, new Insets(10, 10, 10, 0));
        ++var11;
        GridBagInterface.gridBag(0, var11, this.creerLegende(ImgInterface.resize(var3 + "caseChiffre.png", 70, 70), "   Case chiffre : Case affichant le score de l'alignement."), var1, new Insets(10, 10, 10, 0));
        ++var11;
        GridBagInterface.gridBag(0, var11, this.creerLegende(ImgInterface.resize(var3 + "gap.png", 70, 70), "   Case gap : Case affichant la valeur du gap."), var1, new Insets(10, 10, 10, 0));
        ++var11;
        GridBagInterface.gridBag(0, var11, this.creerLegende(ImgInterface.resize(var3 + "chemin.png", 70, 70), "   Case chemin : Case affichant une case qui se trouve dans le chemin."), var1, new Insets(10, 10, 10, 0));
        ++var11;
        GridBagInterface.gridBag(0, var11, this.creerLegende(ImgInterface.resize(var3 + "gapAndChemin.png", 70, 70), "   Case gap et chemin : Case affichant une case qui représente une case gap et une case qui est dans le chemin."), var1, new Insets(10, 10, 10, 0));
        ++var11;
        GridBagInterface.gridBag(0, var11, this.creerTexte(""), var1, new Insets(10, 10, 10, 0));
    }

    public JPanel creerLegende(ImageIcon var1, String var2) {
        JPanel var3 = new JPanel(new BorderLayout());
        JLabel var4 = new JLabel();
        var4.setIcon(var1);
        var4.setHorizontalAlignment(0);
        JLabel var5 = new JLabel();
        TextInterface.setText(var5, var2, new Font("Serif", 1, 16));
        var3.add(var4, "West");
        var3.add(var5, "Center");
        return var3;
    }

    public JPanel creerImage(ImageIcon var1) {
        JPanel var2 = new JPanel(new BorderLayout());
        JLabel var3 = new JLabel();
        var3.setIcon(var1);
        var3.setHorizontalAlignment(0);
        var2.add(var3, "Center");
        return var2;
    }

    public JPanel creerTexte(String var1) {
        JPanel var2 = new JPanel(new BorderLayout());
        JTextArea var3 = new JTextArea(5, 30);
        TextInterface.setText(var3, var1, new Font("Serif", 1, 16));
        var3.setLineWrap(true);
        var3.setEditable(false);
        var3.setOpaque(false);
        var2.add(var3);
        return var2;
    }

    public void showDialog() {
        this.dialog.setVisible(true);
        this.dialog.requestFocus();
    }
}
