package Model2;
import modele.GrandModele;
import modele.ModeleGeneral;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class UPGMA
{
    public static ArrayList<int[][]> MatEtats=new ArrayList<int[][]>();
    public static ArrayList<ArrayList<noeudE>> lArb=new ArrayList<ArrayList< noeudE>>();
    public static Map<Integer,espece> especes;
    public static Map<String,Integer> tabCorr;
    int i=0;
    int nbc;
    public static ArrayList<String> le;
    String FileName;
    public static ArrayList<String> l1= new ArrayList<>();
    public static ArrayList<String> l2= new ArrayList<>();
    public static ArrayList<DefaultMutableTreeNode> nodes1= new ArrayList<>();
    public static ArrayList<DefaultMutableTreeNode> nodes2= new ArrayList<>();
    public static LinkedList<ModeleGeneral> Lmg=new LinkedList<>();
    public static GrandModele Gm;




    public UPGMA(GrandModele G, ArrayList<String> l) throws FileNotFoundException
    {

        genRef(l);
        Gm=G;
        Lmg=G.getListeModele();
        nbc=l.size();
       /*
        TabNomgene(sc,l);
        file = new FileInputStream(FileName);
        sc = new Scanner(file);
        */

    }
    public void CreationArbPhylo()
    {
        MatEtats.add(MatDistInit(especes));
        AffichMat(MatEtats.get(i));
        int lenMa=MatEtats.get(i).length;
        ArbInit();

        while(lenMa>3)
        {
            int[] m=Mincoor(MatEtats.get(i));
            int[]m2=Mincoor_esp(m,MatEtats.get(i));
            String s=especes.get(m2[0]).name;
            s=s+"-"+especes.get(m2[1]).name;
            //espece e=new espece((especes.get(MatEtats.get(i)[m[0]][0]).val+especes.get(MatEtats.get(i)[m[1]][0]).val)/2,s,null);
            espece e=new espece(String.valueOf(MatEtats.get(i)[m[0]][m[1]]),s,null);
            especes.put(nbc+1,e);
            NextArb(e, m2);
            MatEtats.add(NextMatDist(MatEtats.get(i),m));
            nbc++;
            i++;
            lenMa--;

        }
        try {
            Heatmap.DrawMatrices();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    int NbrCrea(Scanner scanner) {
        int c=0;
        while(scanner.hasNextLine())
        {
            c++;
            String s = scanner.nextLine();
        }

        return c;
    }
    //--------------------------------------------------------------------------------------------

    void  genRef(ArrayList<String> l)
    {
        Map<Integer,espece> genes= new HashMap<>();
        for(int i=0;i<l.size();i++)
        {
            espece p = new espece("0", l.get(i), "");
            genes.put(i+1,p);
        }
        this.especes=genes;

    }
//**************************************************************************************

    void  TabNomgene(Scanner scanner,ArrayList<String> l)
    {
        Map<String,Integer> Tab_cor= new HashMap<>();
        while(scanner.hasNextLine())
        {
            String[] s = scanner.nextLine().split(":");
            if(l.contains(s[1])) {
                Tab_cor.put(s[1], Integer.valueOf(s[0]));
            }
        }
        tabCorr=Tab_cor;
    }
//**************************************************************************************

    int[][]  MatDistInit(Map<Integer,espece> genes)
    {
        int[][] M= new int[genes.size()+1][genes.size()+1];
        int k=0;
        Set set = genes.entrySet();
        Iterator i = set.iterator();
        while(i.hasNext()) {
            k++;
            Map.Entry me = (Map.Entry)i.next();
            M[k][0]=(int) me.getKey();
            M[0][k]=(int) me.getKey();


        }

        for(int j=1;j<M.length;j++)
        {
            for (int l = 1; l < M.length; l++)
            {
                if(l==j)
                {
                    M[l][j]=0;
                }
                else
                {
                    for(int p=0;p<Lmg.size();p++)
                    {
                        ModeleGeneral mg=Lmg.get(p);
                        if((mg.getEsp1().equals(especes.get(j).name) && mg.getEsp2().equals(especes.get(l).name))||(mg.getEsp2().equals(especes.get(j).name) && mg.getEsp1().equals(especes.get(l).name)  ))
                        {
                            M[l][j]=(int)(mg.getDistance()*100);
                        }

                    }
                }


                //M[l][j]=(int) Math.sqrt( Math.pow((genes.get(M[j][0]).val-genes.get(M[l][0]).val),2) );
            }
        }
        //inverserM(M);
        return M;
    }
    //----------------------------------------------------------------------------------------------------
    void inverserM(int[][] M)
    {
        for(int i=0;i<M.length/2;i++)
        {
            echangerLigne(M[i],M[M.length-1-i]);
        }


    }
    //---------------------------------------------------------------------------------------
    void echangerLigne(int[] t1, int[] t2)
    {
        int[] tmp=new int[t1.length];
        for(int i=0;i<t2.length;i++)
        {
            tmp[i]=t1[i];
            t1[i]=t2[i];
        }
        for(int i=0;i<t2.length;i++)
        {
            t2[i]=tmp[i];
        }

    }
//----------------------------------------------------------------------------------------------------

    void ArbInit()
    {
        //Map<Integer,espece> genes;
        Set set = especes.entrySet();
        Iterator it = set.iterator();
        ArrayList< noeudE> nL =new ArrayList<noeudE>();
        lArb.add(nL);
        for(espece e: especes.values())
        {
            noeudE n=new noeudE(e,null,null);
            lArb.get(0).add(n);
        }
    }

    void NextArb(espece e, int[]coor)
    {
        espece e1=especes.get(coor[0]);
        espece e2=especes.get(coor[1]);
        ArrayList< noeudE> nL =lArb.get(i);
        // AffichArb(i);

        noeudE nE=new noeudE(e,null,null);
        ArrayList< noeudE> nL2=new ArrayList<noeudE>();
        for(int j=0;j<nL.size();j++)
        {
            if((nL.get(j).getval().compareto(e1))||(nL.get(j).getval().compareto(e2)))
            {
                if(nE.getfg()==null)
                {
                    nE.setfg(nL.get(j));
                }
                else
                {
                    nE.setfd(nL.get(j));
                }
            }
            else
            {

                nL2.add(nL.get(j));

            }
        }

        nL2.add(nE);
        lArb.add(nL2);


    }

//**************************************************************************************
    /**
     * This function will display a 2D table (Matrice) just for testing
     * @param M : 2D array
     * @return  void
     */
    void  AffichMat(int[][] M)
    {
        for (int j = 0; j < M.length; j++) {

            for (int k = 0; k < M.length; k++) {
                //System.out.print(M[k][j]+" | ");
            }
            //System.out.println("");
        }
    }


    //**************************************************************************************

    void  AffichArb(int i)
    {
        for (int j = 0; j < lArb.get(i).size(); j++)
        {
            //System.out.println(lArb.get(i).get(j).toString());

        }
    }
//**************************************************************************************

    public void AfficherlArb()
    {
        for(int i=0;i<lArb.size();i++)
        {
            //System.out.println("Arbre numero : "+(i+1));
            AffichArb(i);
        }
    }
//***************************************************************************************************************//

    int[] Mincoor(int[][] M)
    {
        int min=Integer.MAX_VALUE;
        int [] coordonnes=new int[2];//ici coordonnes[0] est la colonne et coordonnes[1] est la ligne
        for (int i = 1; i < M.length; i++)
        {
            for (int j = 1; j < M.length; j++) {
                if(M[i][j]!= 0 && M[i][j]<min)
                {
                    min=M[i][j];
                    coordonnes[0]=i;
                    coordonnes[1]=j;
                }
            }
        }
        return coordonnes;
    }
//***************************************************************************************************************//

    int[] Mincoor_esp(int[] coor,int[][] M)
    {
        int min=Integer.MAX_VALUE;
        int [] coordonnes=new int[2];//ici coordonnes[0] est la colonne et coordonnes[1] est la ligne
        coordonnes[0]=M[0][coor[0]];
        coordonnes[1]=M[0][coor[1]];
        return coordonnes;
    }
    //***************************************************************************************************************

    int[][]  NextMatDist(int[][] M,int[] coor)
    {
        int[][] Nm=new int[M.length-1][M.length-1];
        //System.out.println("Length matrice Nm  : "+Nm.length);
        int k=0,l=0;
        for(int i =0;i<M.length;i++)
        {
            l=0;
            for(int j=0;j<M.length;j++)
            {
                if(j!=coor[0]&& j!=coor[1] && i!=coor[0]&& i!=coor[1])
                {

                    Nm[k][l]=M[i][j];
                    if(l==0)
                    {
                        l=2;
                    }
                    else
                    {
                        l++;
                    }
                }

            }
            if(i!=coor[0]&& i!=coor[1])
            {
                if(k==0)
                {
                    k=2;
                }
                else
                {
                    k++;
                }
            }
        }
        Nm[1][0]=nbc+1;
        Nm[0][1]=nbc+1;
        for(int j=2;j<Nm.length;j++)
        {

            Nm[1][j]=(Dist2Esp(M,M[coor[0]][0], Nm [j][0])+Dist2Esp(M,M[coor[1]][0], Nm [j][0]))/2;
        }
        for(int j=2;j<Nm.length;j++)
        {
            Nm[j][1]=(Dist2Esp(M,M[coor[0]][0], Nm [j][0])+Dist2Esp(M,M[coor[1]][0], Nm [j][0]))/2;
        }

        //MatEtats.add(Nm);
        return Nm;
    }
    //----------------------------------------------------------------------------------------------------

    int Dist2Esp(int[][] M,int e1, int e2)
    {
        int i=0,j=0;
        while(M[0][i]!=e1)
        {
            i++;
        }
        while(M[0][j]!=e2)
        {
            j++;
        }



        return M[i][j];
    }

    public void creaTree(noeudE n) {
        //System.out.println(n.toString());
        if(n.getfg()!=null) {
            creaTree(n.getfg());
        }
        if(n.getfd()!=null) {
            creaTree(n.getfd());
        }
    }

    public static void creaTreeString(noeudE n,ArrayList<String> l,ArrayList<DefaultMutableTreeNode> nodes)
    {
        l.add(n.val.name);
        nodes.add(new DefaultMutableTreeNode(n.val.name));
        if(n.getfg()!=null)
        {
            creaTreeString(n.getfg(),l,nodes);
        }
        if(n.getfd()!=null)
        {
            creaTreeString(n.getfd(),l,nodes);
        }
    }

}
