import java.util.Arrays;

public class WatekSort extends Thread
{

    int rozmiarTab;
    int tmp;
    int[] tab;

    WatekSort(int[] tab)
    {
        this.rozmiarTab = tab.length;
        this.tab = tab;
    }
    public int[] sortuj(int[] tab)
    {
        Arrays.sort(tab);
        return tab;
    }
    @Override
    public void run() {

        this.tab = sortuj(tab);

    }

    public int[] getTab() {
        return tab;
    }
}
