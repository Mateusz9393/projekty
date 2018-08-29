import java.util.Arrays;

public class WatekScal extends Thread
{
    int[] tab1;
    int[] tab2;
    int[] tab;
    int index1=0;
    int index2=0;
    int indexTab =0;

    WatekScal(int[] tab1, int[] tab2)
    {
        this.tab1=tab1;
        this.tab2=tab2;
    }
    public int[] scal()
    {
        if(tab2 != null){
            this.tab=new int[tab1.length+tab2.length];
            while (index1+index2!=tab1.length+tab2.length) {
                if(tab1[index1]<tab2[index2])
                {
                    tab[indexTab]=tab1[index1];
                    indexTab++;
                    index1++;
                    while(index1==tab1.length&&index2!=tab2.length)
                    {
                        tab[indexTab]=tab2[index2];
                        indexTab++;
                        index2++;
                    }
                }
                else
                {
                    tab[indexTab]=tab2[index2];
                    indexTab++;
                    index2++;
                    while(index2==tab2.length&&index1!=tab1.length)
                    {
                        tab[indexTab]=tab1[index1];
                        indexTab++;
                        index1++;
                    }

                }
            }
        }else{
            this.tab = tab1;
        }
        return tab;
    }

    @Override
    public void run()
    {
        this.tab = scal();
    }

    public int[] getTab() {
        return tab;
    }
}
