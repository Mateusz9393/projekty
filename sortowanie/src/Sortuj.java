import java.util.ArrayList;
import java.util.Arrays;

public class Sortuj {

     WatekScal scal;

     public int[] sortowanie(int[] tab, int iloscWatkow)
     {
         int roz = tab.length;
         int zakres = roz/iloscWatkow;
         int[][] podzieloneTab = new int [iloscWatkow][zakres];

         //System.out.println(Arrays.toString(tab));
         for(int nrWatka=0;nrWatka<iloscWatkow;nrWatka++)
         {
             //System.out.println("tab nr "+(nrWatka+1));
             if(nrWatka==iloscWatkow-1&&roz%iloscWatkow!=0)
             {
                 podzieloneTab[nrWatka] = new int [zakres+roz%iloscWatkow];
                 for(int i=0;i<zakres+roz%iloscWatkow;i++)
                 {
                     podzieloneTab[nrWatka][i] = tab[i+nrWatka*zakres];
                     //System.out.println(podzieloneTab[nrWatka][i]);
                 }
                 break;
             }
             for (int indeksTablicy=0; indeksTablicy < zakres; indeksTablicy++)
             {
                 podzieloneTab[nrWatka][indeksTablicy] = tab[indeksTablicy+nrWatka*zakres];
                 //System.out.println(podzieloneTab[nrWatka][indeksTablicy]);
             }

         }
         //////////////////////////////////////////////////////////////////////////////////////////
         WatekSort[] watki = new WatekSort[iloscWatkow];
         for(int i=0;i<iloscWatkow;i++)
         {
             watki[i] = new WatekSort(podzieloneTab[i]);
             watki[i].start();
         }
         for(int i=0;i<iloscWatkow;i++)
         {
             try {
                 watki[i].join();
             }catch(InterruptedException e){System.exit(1);}

         }
         for(int i=0;i<iloscWatkow;i++)
         {
             podzieloneTab[i]=watki[i].getTab();
             //System.out.println(Arrays.toString(podzieloneTab[i]));
         }
         ////////////////////////////////////////////////////////////////////////////////////////
         WatekScal[] scal = new WatekScal[(int)Math.ceil(iloscWatkow/2.0)];
         //System.out.println(scal.length);
         while(scal.length>1) {
             for (int i = 0; i < scal.length; i++) {
                 try {
                     scal[i] = new WatekScal(podzieloneTab[i * 2], podzieloneTab[i * 2 + 1]);
                 }catch (IndexOutOfBoundsException e){
                     scal[i] = new WatekScal(podzieloneTab[i * 2], null);
                 }
                 scal[i].start();
             }
             for(int i=0;i<scal.length;i++) {
                 try {
                     //System.out.println(scal[i]);
                     scal[i].join();
                 } catch (InterruptedException e) {
                     System.exit(1);
                 }
             }
             podzieloneTab = new int [scal.length][];
             for(int i=0;i<scal.length;i++)
             {
                 podzieloneTab[i] = scal[i].getTab();

             }
             scal = new WatekScal[(int)Math.ceil(podzieloneTab.length/2.0)];

         }

        WatekScal ostatnie = new WatekScal(podzieloneTab[0], podzieloneTab[1]);
        ostatnie.start();
        try{
            ostatnie.join();
        }catch(InterruptedException e){System.exit(1);}
        tab = ostatnie.getTab();
         return tab;
     }
}
