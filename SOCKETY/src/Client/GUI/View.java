package Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
    private JFrame frame;
    private JPanel panel;
    private JLabel teamLabel;
    private JProgressBar rope;
    private JButton pullButton;
    private Client.Client client;

    public View() {
        pullButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendClick();
            }
        });
    }

    public void init(){
        frame = new JFrame("GuiView");
        frame.setContentPane(this.panel);
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setTeamLabel(String label) {
        if (label.equals("l")){
            teamLabel.setText("Lewa strona");
        }else{
            teamLabel.setText("Prawa strona");
        }
    }

    public void setRope(int progress){
        rope.setValue(progress);
    }

    public void setClient(Client.Client client){
        this.client = client;
    }
}
