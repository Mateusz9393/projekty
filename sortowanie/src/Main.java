import java.util.Arrays;

public class Main {

    public static void main(String[] args) {



        Sortuj sortuj = new Sortuj();
        RandTab a = new RandTab(100000000);
        int[] tab = a.zwracajIntTab();
        long czas = System.nanoTime();
        tab = sortuj.sortowanie(tab, 10);
        //System.out.println(Arrays.toString(tab));
        long czas2 = System.nanoTime();
        System.out.println(czas2 - czas);
    }
}
