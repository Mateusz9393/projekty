import javax.swing.*;
import java.awt.*;

public class Progress {

    private JPanel progress;
    private JProgressBar progressBar1;
    private JProgressBar progressBar2;
    private JProgressBar progressBar3;
    private JProgressBar progressBar4;

    JProgressBar[] bary = new JProgressBar[4];

    public void init() {

        JFrame frame = new JFrame("Progress");
        frame.setContentPane(this.progress);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500,300));
        frame.pack();
        frame.setVisible(true);

        this.baryDoTablicy();
        Plik pliki = new Plik(this.bary);
        pliki.stworz2(20,100000000);
        pliki.sortuj2();

    }
    public void baryDoTablicy(){
        this.bary[0] = progressBar1;
        this.bary[1] = progressBar2;
        this.bary[2] = progressBar3;
        this.bary[3] = progressBar4;
    }
}
