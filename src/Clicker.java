import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.SwingConstants.RIGHT;

public class Clicker {

    JLabel metresTravelledLabel, clickCountLabel, perSecondLabelLabel, perSecondLabel, price, price1, price2, price3, price4;
    JButton carButton, button1, button2, button3, button4;
    int clickCount, timerSpeed, autoClickerNumber, autoClickerPrice, towTruckNumber, towTruckPrice, mechanicPrice, button3ClickIteration, repairCounter,clicksLeftToFixCar;
    double clicksPerSecond;
    float repairCounterPercent;
    boolean timerOn, autoClickerUnlocked, towTruckUnlocked, mechanicTriggeredYet, mechanicUnlocked, driveUnlocked, carInMechanic;
    Font font1, font2, font3;
    ClickHandler cHandler = new ClickHandler();
    Timer timer;
    JTextArea messageText;
    MouseHandler mHandler = new MouseHandler();
    Border raisedBorder = BorderFactory.createRaisedBevelBorder();

    public static void main(String[] args) {

        new Clicker();
    }

    public Clicker() {
        timerOn = false;
        clicksPerSecond = 0;
        clickCount = 0;
        autoClickerNumber = 0;
        autoClickerPrice = 10;
        towTruckNumber = 0;
        towTruckPrice = 200;
        mechanicPrice = 3500;
        mechanicTriggeredYet = false;
        repairCounter = 0;
        carInMechanic = false;
        clicksLeftToFixCar = 500;

        createFont();
        createUI();

    }

    public void createFont(){
        font1 = new Font("Arial", Font.PLAIN, 32);
        font2 = new Font("Arial", Font.PLAIN, 15);
        font3 = new Font("Arial", Font.BOLD, 27);
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
        carButton.setActionCommand("pushCar");
        clickHere.add(carButton);

        JPanel clickCounter = new JPanel();
        clickCounter.setBounds(100,100,300,100);
        clickCounter.setBackground(Color.black);
        clickCounter.setLayout(new GridLayout(4,1));
        window.add(clickCounter);

        metresTravelledLabel = new JLabel("Metres travelled:");
        metresTravelledLabel.setForeground(Color.yellow);
        metresTravelledLabel.setFont(font2);
        clickCounter.add(metresTravelledLabel);

        clickCountLabel = new JLabel(""+clickCount);
        clickCountLabel.setForeground(Color.white);
        clickCountLabel.setFont(font1);
        clickCounter.add(clickCountLabel);

        perSecondLabelLabel = new JLabel();
        perSecondLabelLabel.setForeground(Color.white);
        perSecondLabelLabel.setFont(font2);
        clickCounter.add(perSecondLabelLabel);

        perSecondLabel = new JLabel();
        perSecondLabel.setForeground(Color.white);
        perSecondLabel.setFont(font2);
        clickCounter.add(perSecondLabel);

        JPanel priceLabelPanel = new JPanel();
        priceLabelPanel.setBounds(390,120,100,50);
        priceLabelPanel.setBackground(Color.black);
        priceLabelPanel.setLayout(new GridLayout(1,1));
        window.add(priceLabelPanel);

        price = new JLabel("Price");
        price.setHorizontalAlignment(RIGHT);
        price.setForeground(Color.white);
        price.setFont(font1);
        priceLabelPanel.add(price);

        JPanel pricePanel = new JPanel();
        pricePanel.setBounds(390,170,100,250);
        pricePanel.setBackground(Color.black);
        pricePanel.setLayout(new GridLayout(4,1));
        window.add(pricePanel);

        price1 = new JLabel("10m");
        price1.setHorizontalAlignment(RIGHT);
        price1.setForeground(Color.white);
        price1.setFont(font1);
        pricePanel.add(price1);

        price2 = new JLabel();
        price2.setHorizontalAlignment(RIGHT);
        price2.setForeground(Color.white);
        price2.setFont(font1);
        pricePanel.add(price2);

        price3 = new JLabel();
        price3.setHorizontalAlignment(RIGHT);
        price3.setForeground(Color.white);
        price3.setFont(font1);
        pricePanel.add(price3);

        price4 = new JLabel();
        price4.setHorizontalAlignment(RIGHT);
        price4.setForeground(Color.white);
        price4.setFont(font1);
        pricePanel.add(price4);

        JPanel itemPanel = new JPanel();
        itemPanel.setBounds(500,170,250,250);
        itemPanel.setBackground(Color.black);
        itemPanel.setLayout(new GridLayout(4,1));
        window.add(itemPanel);

        button1 = new JButton("LOCKED");
        button1.setFont(font1);
        button1.setFocusPainted(false);
        button1.addActionListener(cHandler);
        button1.setActionCommand("Hired Help");
        button1.addMouseListener(mHandler);
        itemPanel.add(button1);

        button2 = new JButton("LOCKED");
        button2.setFont(font1);
        button2.setFocusPainted(false);
        button2.addActionListener(cHandler);
        button2.setActionCommand("Tow Truck");
        button2.addMouseListener(mHandler);
        itemPanel.add(button2);

        button3 = new JButton("LOCKED");
        button3.setFont(font1);
        button3.setFocusPainted(false);
        button3.addActionListener(cHandler);
        button3.setActionCommand("Mechanic");
        button3.addMouseListener(mHandler);
        itemPanel.add(button3);

        button4 = new JButton("LOCKED");
        button4.setFont(font1);
        button4.setFocusPainted(false);
        button4.addActionListener(cHandler);
        button4.setActionCommand("");
        button4.addMouseListener(mHandler);
        itemPanel.add(button4);

        JPanel messagePanel = new JPanel();
        messagePanel.setBounds(530,70,250,150);
        messagePanel.setBackground(Color.black);
        window.add(messagePanel);

        messageText = new JTextArea();
        messageText.setBounds(530, 70, 250, 150);
        messageText.setForeground(Color.yellow);
        messageText.setBackground(Color.black);
        messageText.setFont(font2);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEditable(false);
        messagePanel.add(messageText);

        window.setVisible(true);
    }

    public void setTimer(){
        timer = new Timer(timerSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickCount++;
                clickCountLabel.setText(""+clickCount);

                if(!towTruckUnlocked && clickCount >= 200) {
                    towTruckUnlocked = true;
                    button2.setText("Tow Truck");
                }
                if(!mechanicUnlocked && clickCount == 3500) {
                    timer.stop();
                    perSecondLabelLabel.setText("");
                    perSecondLabel.setText("");
                    temporarilyLockPowerUpsForMechanicMiniGame(1, autoClickerNumber, towTruckNumber);
                }
            }
        });
    }
    public void timerUpdate() {
        if(!timerOn && clickCount<3500 && !carInMechanic) {
            timerOn=true;
        }
        else if (timerOn){
            timer.stop();
        }

        double speed = 1/clicksPerSecond*1000;
        timerSpeed = (int)Math.round(speed);

        String s = String.format("%.1f", clicksPerSecond);
        perSecondLabelLabel.setText("Metres of help per second:");
        perSecondLabel.setText(s);

        if((!mechanicUnlocked && clickCount<3500) || (mechanicUnlocked && driveUnlocked)) {
            setTimer();
            timer.start();
        }
    }
    public class ClickHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            String action = event.getActionCommand();

            switch(action) {
                case "pushCar":



//            double randValue = Math.random(); //cheat loop to boost clicks for debug
//            int max = 1000;
//            int min = 1;
//            int range = max - min + 1;
//            int timesToPush = (int)(range * randValue);
//
//            for (int i=0; i<timesToPush; i++){


                if (!mechanicUnlocked && clickCount<3500 || driveUnlocked) {
                    clickCount=clickCount+100;
                    clickCountLabel.setText("" + clickCount);

                    if (!autoClickerUnlocked && clickCount >= 10) {
                        autoClickerUnlocked = true;
                        button1.setText("Hired Help");
                        price1.setText(autoClickerPrice + "m");
                        price2.setText(towTruckPrice + "m");

                    }

                    if (!towTruckUnlocked && clickCount >= 200) {
                        towTruckUnlocked = true;
                        button2.setText("Tow Truck");
                        price3.setText(mechanicPrice + "m");
                    }

                }
                else if (!mechanicTriggeredYet && clickCount>3500) {
                    clickCount=3500;
                    clickCountLabel.setText(""+clickCount);
                    timer.stop();
                    temporarilyLockPowerUpsForMechanicMiniGame(1, autoClickerNumber, towTruckNumber); //lock/unlock powerups while at mechanic
                }
                else {
                    clickCount=3500;
                    clickCountLabel.setText(""+clickCount);
                    price4.setText(clicksLeftToFixCar + "m");
                    temporarilyLockPowerUpsForMechanicMiniGame(1, autoClickerNumber, towTruckNumber); //lock/unlock powerups while at mechanic
                }
 //           } //end cheat loop


                    break;
                case "Hired Help":
                    if(clickCount >= autoClickerPrice && !mechanicUnlocked){
                        clickCount = clickCount-autoClickerPrice;
                        autoClickerPrice = autoClickerPrice + 10;
                        clickCountLabel.setText(""+clickCount);
                        price1.setText("" + autoClickerPrice + "m");
                        autoClickerNumber++;
                        button1.setText("Hired Help (" + autoClickerNumber + ")");
                        messageText.setText("Hired Help!\n[Price: " + autoClickerPrice + "]\nExtra manpower to push \nevery 10 seconds!");
                        clicksPerSecond = clicksPerSecond + 0.1;
                        timerUpdate();
                    }
                    else {
                        if (!mechanicTriggeredYet) {
                            messageText.setText("\n\nYou need " + (towTruckPrice-clickCount) + " more metres\nto buy this upgrade!");
                        }
                        else {
                            messageText.setText("Already arrived at garage,\nno more hired help required");
                        }
                    }
                    break;
                case "Tow Truck":
                    if(clickCount >= towTruckPrice && !mechanicUnlocked){
                        clickCount = clickCount-towTruckPrice;
                        towTruckPrice = towTruckPrice + 100;
                        clickCountLabel.setText(""+clickCount);
                        towTruckNumber++;
                        button2.setText("Tow Truck (" + towTruckNumber + ")");
                        price2.setText(towTruckPrice + "m");
                        messageText.setText("Call a Tow Truck!\n[Price: " + towTruckPrice + "]\nTow Truck will move you much\nfaster than a pair of hands!");
                        clicksPerSecond = clicksPerSecond + 5;
                        timerUpdate();
                    }
                    else {
                        if (!mechanicTriggeredYet) {
                        messageText.setText("\n\nYou need " + (towTruckPrice-clickCount) + " more metres\nto buy this upgrade!");
                        }
                        else {
                            messageText.setText("Already arrived at garage,\nno more tow trucks required");
                        }
                    }
                    break;
                case "Mechanic":
                    if(clickCount == mechanicPrice){
                        if (button3ClickIteration==0) {
                            button3.setText("Car is 0% fixed");
                        }
                        clicksLeftToFixCar--;
                        price4.setText(clicksLeftToFixCar + "m");
                        messageText.setText("You arrived at the mechanic! Phew!\nSpend 3500m + 500 clicks to\nrepair your wheels!");
                        repairCounter++;
                        repairCounterPercent = repairCar(repairCounter);
                        String repairPercentString = String.format("%.2f", repairCounterPercent);
                        button3.setText(repairPercentString + "% fixed");
                        if ((int)repairCounterPercent == 100) {
                            carInMechanic = false;
                            clickCount=0;
                            button3.setText("Car Fixed!");
                            clickCountLabel.setText("0m");
                            String s = String.format("%.1f", clicksPerSecond);
                            perSecondLabelLabel.setText("Metres of help per second:");
                            perSecondLabel.setText(s);
                            temporarilyLockPowerUpsForMechanicMiniGame(2, autoClickerNumber, towTruckNumber);
                        }
                        button3ClickIteration++;
                    }
                    else {
                        if (!mechanicTriggeredYet) {
                            messageText.setText("\n\nYou need " + (towTruckPrice - clickCount) + " more metres\nto buy this upgrade!");
                        }
                        else{
                            messageText.setText("\n\nGarage Closed\nCar already repaired!");
                        }
                    }
                    break;
            }
//
        }
    }

    private void temporarilyLockPowerUpsForMechanicMiniGame(int toggle, int autoClickerNumber, int towTruckNumber) {
        if (toggle == 1) {
            button1.setText("LOCKED");
            button2.setText("LOCKED");
            button3.setText("Fix Your Car!");
            autoClickerUnlocked = false;
            towTruckUnlocked = false;
            mechanicUnlocked = true;
            mechanicTriggeredYet = true;
        }
        else if (toggle == 2) {
            autoClickerUnlocked = true;
            towTruckUnlocked = true;
            mechanicUnlocked=false;
            driveUnlocked = true;
            button1.setText("Hired Help (" + autoClickerNumber + ")");
            button2.setText("Tow Truck (" + towTruckNumber + ")");
            button4.setFont(font3);
            button4.setForeground(Color.red);
            button4.setBorder(raisedBorder);
            button4.setText("START ENGINE!");
        }
    }

    private float repairCar(int repairCounter) {
        float repairCounterAsFloat = repairCounter;
        float repairPercent = repairCounterAsFloat/500*100;
        //return repairPercent;
        return 100;
    }

    public class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

            }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JButton button = (JButton)e.getSource();

            if (button == button1) {
                if (!autoClickerUnlocked) {
                    messageText.setText("\n\n\nThis item is currently locked!");
                }
                else {
                    messageText.setText("Hired Help!\n[Price: " + autoClickerPrice + "]\nExtra manpower to push \nevery 10 seconds!");
                }
            } else if (button == button2) {
                if (!towTruckUnlocked){
                    messageText.setText("\n\n\nThis item is currently locked!");
                }
                else {
                    messageText.setText("Call a Tow Truck!\n[Price: " + towTruckPrice + "]\nTow Truck will move you much\nfaster than a pair of hands!");
                }

            } else if (button == button3) {
                if (!mechanicUnlocked){
                    messageText.setText("\n\n\nThis item is currently locked!");
                }
                else {
                    messageText.setText("You arrived at the mechanic! Phew!\nSpend 3500m + 500 clicks to\nrepair your wheels!");
                }
            } else if (button == button4) {
                messageText.setText("\n\n\nThis item is currently locked!");
            }
        }
        @Override
        public void mouseExited(MouseEvent e) {
            JButton button = (JButton)e.getSource();

            if(button == button1){
                messageText.setText(null);
            } else if (button == button2) {
                messageText.setText(null);
            } else if (button == button3) {
                messageText.setText(null);
            } else if (button == button4) {
                messageText.setText(null);
            }

        }
    }
    }
