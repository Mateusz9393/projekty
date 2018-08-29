import java.util.Random;

public class RandTab {

    int[] tab;
    RandTab(int rozmiar)
    {
        int[] tab = new int[rozmiar];
        Random liczba = new Random();
        for(int i=0;i<rozmiar;i++)
        {
            tab[i] = liczba.nextInt(1000);
        }
        this.tab = new int[rozmiar];
        for(int i=0;i<rozmiar;i++)
        {
            this.tab[i] = tab[i];
        }
    }
    public int[] zwracajIntTab()
    {
        return tab;
    }
}
