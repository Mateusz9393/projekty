import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BlockingQueue;


public class WatekStworz implements Runnable {

    int maxLiczb;
    private static int licznikZrobionych;
    int ileDoZrobienia;
    BlockingQueue<Integer> kolejka;

    WatekStworz(int maxLiczb, BlockingQueue<Integer>kolejka, int doZrobienia){

        this.maxLiczb = maxLiczb;
        this.kolejka = kolejka;
        this.ileDoZrobienia = doZrobienia;
    }

    @Override
    public void run() {
        try {
            while(licznikZrobionych<ileDoZrobienia) {

                Integer x = kolejka.take();
                licznikZrobionych++;
                File plik = new File("C:/Users/Mati/Desktop/programki/Pliki/Pliki", x + ".txt");
                Random generator = new Random();
                try {
                    plik.createNewFile();
                    BufferedWriter write = new BufferedWriter(new FileWriter("C:/Users/Mati/Desktop/programki/Pliki/Pliki/" + x + ".txt"));
                    for (int i2 = 0; i2 < generator.nextInt(maxLiczb - 1) + 1; i2++) {
                        write.write(generator.nextInt(1000) + " ");
                    }
                    write.close();
                } catch (IOException e) {e.printStackTrace();}
            }
        }catch (InterruptedException e){e.printStackTrace();}
    }
}
