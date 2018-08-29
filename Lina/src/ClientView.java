import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientView{
    private JProgressBar progressBar1;
    private JPanel panel1;
    private JButton ciagnijButton;
    private JTextField textField1;
    private JTextField textField2;

    int port = 4444;
    int power = 5;
    String number;
    Socket myClient;
    BufferedReader input;
    PrintWriter output;
    String team;

    ClientView() {
        try {
            myClient = new Socket("localhost", port);
            input = new BufferedReader(new InputStreamReader(myClient.getInputStream()));
            output = new PrintWriter(myClient.getOutputStream(), true);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ciagnijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    output.println(power);
            }
        });
    }


    public void init() {
        JFrame frame = new JFrame("Client");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 300));
        frame.pack();
        frame.setVisible(true);
        try {
            team = input.readLine();
            //System.out.println(team);
            number = input.readLine();
            //System.out.println(number);
            progressBar1.setValue(Integer.parseInt(number));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            while (true) {
                number = input.readLine();
                //System.out.println(number);
                if (number != null) {
                    if (number.equals("w")||number.equals("b")){
                        team = number;
                    }else {
                        progressBar1.setValue(Integer.parseInt(number));
                        if (Integer.parseInt(number) > 99||Integer.parseInt(number) < 1) break;
                    }
                }
                if (team != null){
                    setTeamLabel(team);
                    //System.out.println(team);
                    team = null;
                }
            }

            textField1.setText("KONIEC GRY!!!");

            input.close();
            output.close();
            myClient.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void setTeamLabel(String team){
        //System.out.println(team);
        if (team.equals("w")){textField2.setText("DRUZYNA NIEBIESKA");}
        if (team.equals("b")){textField2.setText("DRUZYNA BIALA");}
    }
}
