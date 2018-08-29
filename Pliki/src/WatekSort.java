import javax.swing.*;
import java.io.*;
import java.util.concurrent.BlockingQueue;

public class WatekSort implements Runnable{

    int[] intTab;
    int zrobionyProc = 0;
    private static int licznikZrobionych2;
    int ileDoZrobienia;
    BlockingQueue<Integer> kolejka;
    JProgressBar progressBar;
    Integer x;

    WatekSort(BlockingQueue<Integer> kolejka, JProgressBar progressBar, int ileDoZrobienia){

        this.kolejka=kolejka;
        this.progressBar=progressBar;
        this.ileDoZrobienia = ileDoZrobienia;
    }

    @Override
    public void run() {
        while (licznikZrobionych2 < ileDoZrobienia) {

            try {
                this.x = kolejka.take();
                licznikZrobionych2++;
                String string = null;
                String[] stringTab;

                //WCZYTAJ I ZAMIEN NA TABLICE INTOW
                try {
                    BufferedReader read = new BufferedReader(new FileReader("C:/Users/Mati/Desktop/programki/Pliki/Pliki/" + x + ".txt"));

                    try {
                        string = read.readLine();
                    } catch (IOException e) {e.printStackTrace();}
                        if (string!=null) {
                            stringTab = string.split(" ");
                            //System.out.println(Arrays.toString(stringTab)+"\n");
                            intTab = new int[stringTab.length];
                            for (int i2 = 0; i2 < stringTab.length; i2++) {
                                intTab[i2] = Integer.parseInt(stringTab[i2]);

                            }
                        }
                    //System.out.println(Arrays.toString(intTab));
                } catch (FileNotFoundException e) {e.printStackTrace();
                }
            } catch (InterruptedException e) {e.printStackTrace();
            }
            //BOMBLE
            int tmp;
            if(intTab!=null) {
                for (int cykl = 0; cykl < intTab.length; cykl++) {

                    for (int powtorzenia = 1; powtorzenia < (intTab.length - cykl); powtorzenia++) {
                        if (intTab[powtorzenia - 1] > intTab[powtorzenia]) {
                            tmp = intTab[powtorzenia - 1];
                            intTab[powtorzenia - 1] = intTab[powtorzenia];
                            intTab[powtorzenia] = tmp;
                        }
                    }

                    //LICZY ILE PROCENT ZROBIONO
                    this.zrobionyProc = Math.round((float) cykl / intTab.length * 100);
                    progressBar.setValue(this.zrobionyProc);
                    //System.out.println(this.zrobionyProc + "plik" + x);
                    if (cykl == intTab.length - 1) {
                        this.zrobionyProc = 100;
                        progressBar.setValue(this.zrobionyProc);
                        //System.out.println(this.zrobionyProc + "plik" + x);
                    }
                }
            }

            //ZAPISZ
            try {
                if (intTab!=null) {
                    BufferedWriter write = new BufferedWriter(new FileWriter("C:/Users/Mati/Desktop/programki/Pliki/Pliki/" + x + ".txt"));
                    for (int i3 = 0; i3 < intTab.length; i3++) {
                        write.write(intTab[i3] + " ");
                    }
                    write.close();
                }
            } catch (IOException e) {e.printStackTrace();}

        }
    }
}
