import javax.swing.*;
import java.awt.*;

public class Clicker {
    public static void main(String[] args) {

        new Clicker();
    }

    public Clicker() {
        createUI();

    }

    public void createUI(){
        JFrame window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        JPanel clickHere = new JPanel();
        clickHere.setBounds(100,220,200,200);
        clickHere.setBackground(Color.blue);
        window.add(clickHere);

        ImageIcon pushCar = new ImageIcon(getClass().getClassLoader().getResource("resource/carImage.png"));

        JButton carButton = new JButton();
        carButton.setBackground(Color.black);
        carButton.setFocusPainted(false);
        carButton.setBorder(null);
        carButton.setIcon(pushCar);
        clickHere.add(carButton);

       window.setVisible(true);
    }
}
