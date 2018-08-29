import javax.swing.*;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Plik {
    int iloscPlikow;
    int iloscWatkow = 4;
    JProgressBar[] progressBar = new JProgressBar[iloscWatkow];

    Plik(JProgressBar progressBar[]) {
        for (int i=0;i<iloscWatkow;i++){
            this.progressBar[i]=progressBar[i];
        }
    }
//    void stworz(int iloscPlikow, int maxLiczb) {
//            this.iloscPlikow=iloscPlikow;
//            int ilePlikowZrobione = 0;
//            Thread[] watki = new Thread[iloscWatkow];
//
//            for (int pierwszeStworzenie = 0; pierwszeStworzenie < iloscWatkow; pierwszeStworzenie++) {
//                watki[pierwszeStworzenie] = new Thread(new WatekStworz(ilePlikowZrobione, maxLiczb));
//                watki[pierwszeStworzenie].start();
//                ilePlikowZrobione++;
//            }
//            for (int tworzenie = 0; true; tworzenie++) {
//                if (tworzenie == iloscWatkow) tworzenie = 0;
//                if (!watki[tworzenie].isAlive()) {
//                    watki[tworzenie] = new Thread(new WatekStworz(ilePlikowZrobione, maxLiczb));
//                    watki[tworzenie].start();
//                    ilePlikowZrobione++;
//                }
//                if (ilePlikowZrobione == iloscPlikow) break;
//            }
//        for (int join = 0;join<iloscWatkow;join++){
//            try {
//                watki[join].join();
//            }catch(InterruptedException e){}
//        }
//    }
    synchronized void stworz2(int iloscPlikow, int maxLiczb) {
        this.iloscPlikow = iloscPlikow;
        int rozmiarKolejki = iloscPlikow;
        int zrobionePliki = 0;

        BlockingQueue<Integer> kolejka = new ArrayBlockingQueue<>(rozmiarKolejki);
        Thread[] watki = new Thread[iloscWatkow];
        for (int i=0;i<watki.length;i++){
            watki[i] = new Thread(new WatekStworz(maxLiczb, kolejka, this.iloscPlikow));
            watki[i].start();
            zrobionePliki++;
        }
        try {
            for (int i = 0; i < rozmiarKolejki; i++) {
                kolejka.put(i);
            }
            if (zrobionePliki==iloscPlikow) {
                for (int i = 0; i < iloscWatkow; i++) {
                    watki[i].join();
                }
            }
            Thread.sleep(1000);
        }catch(InterruptedException e){e.printStackTrace();}

    }
    //////////////////////////////////////////////////////////////////
//    void sortuj(){
//        int ilePlikowZrobione=0;
//        Thread[] watki = new Thread[iloscPlikow];
//
//        for (int pierwszeSortowanie = 0; pierwszeSortowanie < iloscWatkow; pierwszeSortowanie++) {
//            watki[pierwszeSortowanie] = new Thread(new WatekSort(ilePlikowZrobione, progressBar[pierwszeSortowanie]));
//            watki[pierwszeSortowanie].start();
//            ilePlikowZrobione++;
//
//        }
//        for (int tworzenie = 0; true; tworzenie++) {
//            if (tworzenie == iloscWatkow) tworzenie = 0;
//            if (!watki[tworzenie].isAlive()) {
//                watki[tworzenie] = new Thread(new WatekSort(ilePlikowZrobione, progressBar[tworzenie]));
//                watki[tworzenie].start();
//                ilePlikowZrobione++;
//            }
//            if (ilePlikowZrobione == iloscPlikow) break;
//        }
//        for (int join = 0;join<iloscWatkow;join++){
//            try {
//                watki[join].join();
//            }catch(InterruptedException e){}
//        }
//    }
    synchronized void sortuj2(){
        int rozmiarKolejki = iloscPlikow;
        int zrobionePliki = 0;

        BlockingQueue<Integer> kolejka = new ArrayBlockingQueue<>(rozmiarKolejki);
        Thread[] watki = new Thread[iloscWatkow];
        for (int i=0;i<watki.length;i++){
            watki[i] = new Thread(new WatekSort(kolejka, progressBar[i], iloscPlikow));
            watki[i].start();
            zrobionePliki++;
        }
        try {
            for (int i = 0; i < rozmiarKolejki; i++) {
                kolejka.put(i);
            }
            if (zrobionePliki==iloscPlikow) {
                for (int i = 0; i < iloscWatkow; i++) {
                    watki[i].join();
                }
            }
        }catch(InterruptedException e){e.printStackTrace();}

    }
}
