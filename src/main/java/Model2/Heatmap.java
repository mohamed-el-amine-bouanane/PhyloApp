package Model2;

//import org.tc33.jheatchart.HeatChart;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static Model2.UPGMA.especes;


public class Heatmap {

    public  static int[][] MatModif(int[][] m)
    {
        int[][] m1=new int[m.length-1][m.length-1];
        for (int j = 1; j < m.length; j++)
        {
            for (int k = 1; k < m.length; k++)
            {
                m1[j-1][k-1]=m[j][k];
            }
        }
        return m1;
    }



    /**
     * cette function retourne un tableau de nom d'especes avec seulment
     * les trois premier lettres de chaque nom pour l'ffichage correcte des
     * matrice de taille grande
     * @param nm : String[]
     * @return  String[]
     */
    public  static String[] NamSub(String[] nm){

        final String SEPARATEUR = "-";
        for (int j = 0; j < nm.length; j++)
        {
            String nmi=nm[j];
            String nmiseparer[] = nmi.split(SEPARATEUR);
            nm[j]="";
            for (int k = 0; k < nmiseparer.length; k++)
            {
                //System.out.print(nmiseparer[k].substring(0,3)+"  ");
                nm[j]=nm[j]+nmiseparer[k].substring(0,3)+"-";
            }
            //System.out.println();
            nm[j]=nm[j].substring(0,nm[j].length()-1)+" ";
        }
        //System.out.println("-------------------");
        return nm;

    }


    public static void DrawMatrices() throws IOException {

        int cp=0;
        int cp1=0;
        int cp2=0;
        int cpt=10;

        for (int i=0;i<(UPGMA.MatEtats.size());i++)
        {

            Object[] arrayOfUntypedArraies = Arrays.stream(Heatmap.MatModif(UPGMA.MatEtats.get(i))).map(intArray -> Arrays.stream(intArray).asDoubleStream().toArray()).toArray();
            double[][] m1 = Arrays.copyOf(arrayOfUntypedArraies, arrayOfUntypedArraies.length, double[][].class);

            int[][] p=UPGMA.MatEtats.get(i);
            String[] nm=new String[p.length-1];
            for (int h=1;h<p.length;h++)
            {
                nm[h-1]=especes.get(p[0][h]).name;
            }

            if ( cp != 0) {
                nm = NamSub(nm);
            }
            HeatChart map = new HeatChart(m1);
            map.setHighValueColour(Color.BLUE);
            map.setLowValueColour(Color.WHITE);
            map.setYValues(nm);
            map.setXValues(nm);
            map.setXValuesHorizontal(true);
            //map.setAxisValuesColour(Color.BLUE);

            map.setTitle("Matrice distance");
            map.setXAxisLabel("noms des especes");
            map.setYAxisLabel("noms des especes");

            if (cp <= 9) {
                char c = (char) (cp + '0');
                // System.out.println("ccccccccccccccccc ="+c);
                String s = "./images/Matrice_";
                s = s + c;
                s = s + ".png";

                if (c == 0) {
                    map.setCellSize(new Dimension(100, 26));
                } else {
                    map.setCellSize(new Dimension(100, 70));
                }

                // Output the chart to a file.
                map.saveToFile(new File(s));
                cp++;
                // map.getChartImage();

            } else if (cp>9 && cp<=19)
            {
                // if(cp==10)  cp1=0;
                String cc = "1"+String.valueOf(cp1);
                // System.out.println("ccccccccccccccccc ="+c);
                String s = "./images/Matrice_";
                s = s + cc;
                s = s + ".png";
                map.setCellSize(new Dimension(100, 70));
                // Output the chart to a file.
                map.saveToFile(new File(s));
                cp1++;
                cp++;
                // map.getChartImage();
            }else if (cp>19 && cp<=29)
            {
                String cc2 = "2"+String.valueOf(cp2);
                // System.out.println("ccccccccccccccccc ="+c);
                String s = "./images/Matrice_";
                s = s + cc2;
                s = s + ".png";
                map.setCellSize(new Dimension(100, 70));
                // Output the chart to a file.
                map.saveToFile(new File(s));
                cp2++;
                cp++;
                // map.getChartImage();
            }

        }
    }
}
