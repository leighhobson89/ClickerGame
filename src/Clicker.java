import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clicker {

    JLabel clickCountLabel, perSecondLabel;
    int clickCount;
    Font font1, font2;
    ClickHandler cHandler = new ClickHandler();

    public static void main(String[] args) {

        new Clicker();
    }

    public Clicker() {
        clickCount = 0;
        createFont();
        createUI();

    }

    public void createFont(){
        font1 = new Font("Arial", Font.PLAIN, 32);
        font2 = new Font("Arial", Font.PLAIN, 15);
    }
    public void createUI(){
        JFrame window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        JPanel clickHere = new JPanel();
        clickHere.setBounds(100,220,200,200);
        clickHere.setBackground(Color.black);
        window.add(clickHere);

        ImageIcon pushCar = new ImageIcon(getClass().getClassLoader().getResource("resource/carImage.png"));

        JButton carButton = new JButton();
        carButton.setBackground(Color.black);
        carButton.setFocusPainted(false);
        carButton.setBorder(null);
        carButton.setIcon(pushCar);
        carButton.addActionListener(cHandler);
        clickHere.add(carButton);

        JPanel clickCounter = new JPanel();
        clickCounter.setBounds(100,100,300,100);
        clickCounter.setBackground(Color.black);
        clickCounter.setLayout(new GridLayout(2,1));
        window.add(clickCounter);

        clickCountLabel = new JLabel(clickCount + " pushes!");
        clickCountLabel.setForeground(Color.white);
        clickCountLabel.setFont(font1);
        clickCounter.add(clickCountLabel);

        perSecondLabel = new JLabel();
        perSecondLabel.setForeground(Color.white);
        perSecondLabel.setFont(font2);
        clickCounter.add(perSecondLabel);

        window.setVisible(true);
    }

    public class ClickHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
//            double randValue = Math.random(); //cheat loop to boost clicks for debug
//            int max = 100;
//            int min = 1;
//            int range = max - min + 1;
//            int timesToPush = (int)(range * randValue);
//
//            for (int i=0; i<timesToPush; i++){
            clickCount++;
            clickCountLabel.setText(clickCount + " pushes!");
//            } //end cheat loop
        }
    }
}
