import javafx.application.Application;
import javafx.stage.Stage;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static javax.swing.SwingConstants.RIGHT;

public class Clicker extends Application {

    String obstacleType;
    JFrame window;
    JPanel clickHere, clickCounter, pricePanel, priceLabelPanel, itemPanel, messagePanel, bottomDistanceInfoPanel;
    JLabel metresTravelledLabel, clickCountLabel, perSecondLabelLabel, perSecondLabel, price, price1, price2, price3, price4, distanceToGoLabel, distanceToGoTitleLabel, whatIsNextObstacle, distanceToNextObstacleTitleLabel, distanceToNextObstacleLabel;
    JLabel obstacleConditionsTitle, obstacleConditions, timerObstacleTitle, timerObstacle, passFailObstacle, costOfFailure;
    JButton button1, button2, button3, button4;
    int clickCount, timerSpeed, secondsElapsedDelayToRemoveObstaclePanel, delayPanelAfterObstacleTimerSpeed, autoClickerNumber, autoClickerPrice, towTruckNumber, towTruckPrice, mechanicPrice, button3ClickIteration, repairCounter, clicksLeftToFixCar, nextObstDistance, distanceToSteves;
    int driveFirstClickFlag, obstacleTarget, costOfFailureValue,timerObstacleValue, passObstacleFlag;
    int delayObstaclePanelTimerSpeed, countDownToPassObstacleTimerSpeed;
    double clicksPerSecond, speedKmH;
    float repairCounterPercent;
    boolean stage1, stage2, stage2Start, inBetweenMechanicAndClickStartEngine, timerOn, delayObstaclePanelOn, autoClickerUnlocked, towTruckUnlocked, mechanicTriggeredYet, mechanicUnlocked, driveUnlocked, carInMechanic, accelerateClickedFlag,displayObstacleConditionsFlag, costOfFailureFirstIterationFlag;
    boolean startDelayTimerFlag, countDownToPassObstacleOn, startCountDownToPassObstacleFlag;
    Font font1, font2, font3;
    ClickHandler cHandler = new ClickHandler();
    Timer timer, delayPanelAfterObstacleTimer, countDownToPassObstacleTimer;
    JTextArea messageText;
    MouseHandler mHandler = new MouseHandler();
    Border raisedBorder = BorderFactory.createRaisedBevelBorder();
    String[] obstacleNameArray = {"Garage Gate", "Sheep Crossing", "Police Checkpoint", "Level Crossing", "Tractor Up Ahead", "Dog Running in Road", "Drunk Man in Road", "Fallen Rocks", "Broken Water Pipe", "Kids Playing in Road",
            "Turning Lorry Up Ahead", "Bin Wagon Spilled Trash", "Fallen Tree", "Road Works Up Ahead", "Bicycle Up Ahead", "Mate Wants A Race", "Road Rager Chasing You", "Minimum Speed Limit", "Out Accelerate A Sports Car", "Out Accelerate A Hatchback"};
    Integer[][] speedRangeRequiredObstaclesArray = {{0, 0, 0}, {0, 6, 1}, {0, 3, 0}, {0, 0, 0}, {0, 5, 1}, {0, 6, 1}, {0, 8, 1}, {0, 10, 1}, {0, 12, 1}, {0, 4, 1}, {0, 11, 1}, {0, 11, 1}, {0, 9, 1}, {0, 6, 1}, {0, 9, 1}, {50, 60, 0}, {45, 50, 0}, {30, 40, 0}, {65, 70, 0}, {60, 65, 0}};

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
        inBetweenMechanicAndClickStartEngine = false;
        stage1 = true;
        driveFirstClickFlag = 0;
        stage2Start = false;
        obstacleType = "Garage Gate";
        nextObstDistance = 25;
        distanceToSteves = 30000;
        accelerateClickedFlag = false;
        obstacleTarget = 0;
        costOfFailureValue = 0;
        timerObstacleValue = (int)(Math.random() * 7) + 3;
        displayObstacleConditionsFlag = false;
        costOfFailureFirstIterationFlag = false;
        delayPanelAfterObstacleTimerSpeed = 1000;
        passObstacleFlag = 0; //1 pass obst 2 fail obst
        startDelayTimerFlag = false;
        countDownToPassObstacleOn = false;
        startCountDownToPassObstacleFlag = false;
        createFont();
        createUI();
    }

    @Override
    public void start(Stage stage) {

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
        clickHere.setBounds(100,200,200,200);
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
        clickCounter.setBounds(100,100,320,100);
        clickCounter.setBackground(Color.black);
        clickCounter.setLayout(new GridLayout(4,1));
        window.add(clickCounter);

        metresTravelledLabel = new JLabel("Distance travelled:");
        metresTravelledLabel.setForeground(Color.yellow);
        metresTravelledLabel.setFont(font2);
        clickCounter.add(metresTravelledLabel);

        clickCountLabel = new JLabel(clickCount+"m");
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

        JPanel obstacleInfoPanel = new JPanel();
        obstacleInfoPanel.setBounds(500,401,250,150);
        obstacleInfoPanel.setBackground(Color.black);
        obstacleInfoPanel.setLayout(new GridLayout(6,1));
        window.add(obstacleInfoPanel);

        obstacleConditionsTitle = new JLabel("");
        obstacleConditionsTitle.setForeground(Color.yellow);
        obstacleConditionsTitle.setFont(font2);
        obstacleInfoPanel.add(obstacleConditionsTitle);

        obstacleConditions = new JLabel("");
        obstacleConditions.setForeground(Color.white);
        obstacleConditions.setFont(font2);
        obstacleInfoPanel.add(obstacleConditions);

        timerObstacleTitle = new JLabel("");
        timerObstacleTitle.setForeground(Color.yellow);
        timerObstacleTitle.setFont(font2);
        obstacleInfoPanel.add(timerObstacleTitle);

        timerObstacle = new JLabel("");
        timerObstacle.setForeground(Color.white);
        timerObstacle.setFont(font2);
        obstacleInfoPanel.add(timerObstacle);

        costOfFailure = new JLabel("");
        costOfFailure.setForeground(Color.red);
        costOfFailure.setFont(font2);
        obstacleInfoPanel.add(costOfFailure);

        passFailObstacle = new JLabel("");
        passFailObstacle.setForeground(Color.green);
        passFailObstacle.setFont(font1);
        obstacleInfoPanel.add(passFailObstacle);

        JPanel priceLabelPanel = new JPanel();
        priceLabelPanel.setBounds(390,100,100,50);
        priceLabelPanel.setBackground(Color.black);
        priceLabelPanel.setLayout(new GridLayout(1,1));
        window.add(priceLabelPanel);

        price = new JLabel("Price");
        price.setHorizontalAlignment(RIGHT);
        price.setForeground(Color.white);
        price.setFont(font1);
        priceLabelPanel.add(price);

        JPanel pricePanel = new JPanel();
        pricePanel.setBounds(390,150,100,250);
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
        itemPanel.setBounds(500,150,250,250);
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
        button4.setActionCommand("LetsDrive");
        button4.addMouseListener(mHandler);
        itemPanel.add(button4);

        JPanel messagePanel = new JPanel();
        messagePanel.setBounds(530,50,250,150);
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

        JPanel bottomDistanceInfoPanel = new JPanel();
        bottomDistanceInfoPanel.setBounds(100,415,300,130);
        bottomDistanceInfoPanel.setBackground(Color.black);
        bottomDistanceInfoPanel.setLayout(new GridLayout(5,1));
        window.add(bottomDistanceInfoPanel);

        distanceToGoTitleLabel = new JLabel("");
        distanceToGoTitleLabel.setForeground(Color.yellow);
        distanceToGoTitleLabel.setFont(font2);
        bottomDistanceInfoPanel.add(distanceToGoTitleLabel);

        distanceToGoLabel = new JLabel("");
        distanceToGoLabel.setForeground(Color.white);
        distanceToGoLabel.setFont(font1);
        bottomDistanceInfoPanel.add(distanceToGoLabel);

        distanceToNextObstacleTitleLabel = new JLabel("");
        distanceToNextObstacleTitleLabel.setForeground(Color.yellow);
        distanceToNextObstacleTitleLabel.setFont(font2);
        bottomDistanceInfoPanel.add(distanceToNextObstacleTitleLabel);

        whatIsNextObstacle = new JLabel("");
        whatIsNextObstacle.setForeground(Color.white);
        whatIsNextObstacle.setFont(font2);
        bottomDistanceInfoPanel.add(whatIsNextObstacle);

        distanceToNextObstacleLabel = new JLabel("");
        distanceToNextObstacleLabel.setForeground(Color.white);
        distanceToNextObstacleLabel.setFont(font1);
        bottomDistanceInfoPanel.add(distanceToNextObstacleLabel);

        window.setVisible(true);
    }

    public void setDelayPanelAfterObstacleTimer() {
        delayPanelAfterObstacleTimer = new Timer(delayPanelAfterObstacleTimerSpeed, e -> {
            secondsElapsedDelayToRemoveObstaclePanel++;
        });
    }

    private void setCountDownToPassObstacleTimer() {
        countDownToPassObstacleTimer = new Timer(countDownToPassObstacleTimerSpeed, e -> {
            timerObstacleValue--;
            if (timerObstacleValue >=0) {
                timerObstacle.setText(timerObstacleValue + "s");
            }
        });
    }
    public void setTimer() {
        timer = new Timer(timerSpeed, e -> {
            clickCount++;
            if (stage2){
                double ccountAsDouble = clickCount;
                DecimalFormat num = new DecimalFormat("0.00");
                num.setRoundingMode(RoundingMode.FLOOR);
                String km = num.format(ccountAsDouble/1000);
                clickCountLabel.setText(clickCount + "m (" + km + "km)");
                distanceToGoLabel.setText(distanceToSteves - clickCount + "m");
                nextObstDistance--;
                passFailObstacle.setText("");
                distanceToNextObstacleLabel.setText((nextObstDistance) + "m");
                if(nextObstDistance < 300 && nextObstDistance >= 3) {
                    displayObstacleConditionsFlag = true;
                    displayObstaclePassConditions(nextObstDistance);
                } else if (nextObstDistance < 0) {
                    //hitObstacleScenarioAtWrongSpeed
                    passFailObstacle.setForeground(Color.red);
                    passFailObstacle.setText("*FAIL*");
                    startDelayTimerFlag = true;
                    passObstacleFlag = 2;
                    delayPanelAfterObstacleTimerUpdate();
                    if (clickCount >= costOfFailureValue) { //subtract clicks for failing
                        clickCount = clickCount - costOfFailureValue;
                    } else { // set clicks to zero if garage gate
                        clickCount = 0;
                    }
                    setupNextObstacle(passObstacleFlag);
                }
                if (nextObstDistance < 4 && nextObstDistance >= 0 && clicksPerSecond <= (speedRangeRequiredObstaclesArray[obstacleTarget][1] - speedRangeRequiredObstaclesArray[obstacleTarget][0])) {
                    startCountDownToPassObstacleFlag = true;
                    setCountDownToPassObstacleTimer();
                    countDownToPassObstacleTimerUpdate();

                    //if it is start a 5 second timer and constantly check speed in that time
                    //if outside range during timer period, stop timer
                    //after timer expires and in correct range update next random obstacle random distance 0-3000m
                    if (nextObstDistance < 4 && nextObstDistance >= 0 && clicksPerSecond <= (speedRangeRequiredObstaclesArray[obstacleTarget][1] - speedRangeRequiredObstaclesArray[obstacleTarget][0]) && timerObstacleValue == 0) {
                        passFailObstacle.setForeground(Color.green);
                        passFailObstacle.setText("*PASS*");
                        passObstacleFlag = 1;
                        delayPanelAfterObstacleTimerUpdate();
                        if (distanceToSteves > 0) {
                            setupNextObstacle(passObstacleFlag);
                        }
                        else {
                            //win Stage 2
                        }
                    }
                }
                System.out.println("Delay Timer says" + secondsElapsedDelayToRemoveObstaclePanel);
                if (!displayObstacleConditionsFlag) {
                    obstacleConditionsTitle.setText("");
                    obstacleConditions.setText("");
                    timerObstacleTitle.setText("");
                    timerObstacle.setText("");
                    costOfFailure.setText("");
                    passFailObstacle.setText("");
                }
                if(secondsElapsedDelayToRemoveObstaclePanel >= 3) {
                    displayObstacleConditionsFlag = false;
                    delayPanelAfterObstacleTimer.stop();
                    secondsElapsedDelayToRemoveObstaclePanel = 0;
                }
            }
            else if (stage1){
                clickCountLabel.setText(clickCount + "m");
            }

            if(!towTruckUnlocked && clickCount >= 200 && stage1) {
                towTruckUnlocked = true;
                button2.setText("Tow Truck");
            }
            if(clickCount >= 3500 && stage1) {
                clickCount = 3500;
                timer.stop();
                perSecondLabelLabel.setText("");
                perSecondLabel.setText("");
                price4.setText("500m");
                temporarilyLockPowerUpsForMechanicMiniGame(1);
            }
        });
    }

    private void setupNextObstacle(int passObstacleFlag) {
        if (passObstacleFlag == 2) {
            clicksPerSecond = 0;
            perSecondLabel.setText(clicksPerSecond + "m/s");
            timerUpdate();
        }
        int value = (int)(Math.random() * 19) + 1; //pick a new obstacle
        obstacleType = obstacleNameArray[value];
        obstacleTarget = value;
        whatIsNextObstacle.setText("Next obstacle: " + obstacleType);
        nextObstDistance = (int) (Math.random() * 2000) + 300; //distance of next obstacle
        distanceToNextObstacleLabel.setText(nextObstDistance + "m");
        timerObstacleValue = (int)(Math.random() * 7) + 3; //add this once pass/fail
        if (costOfFailureFirstIterationFlag) {
            costOfFailureValue = (int)(Math.random() * (clickCount/100 * 12)) + 200;
            costOfFailureFirstIterationFlag = false;
        } else {
            costOfFailureValue = (int)(Math.random() * (clickCount/100 * 33)) + (clickCount/100 * 10);
        }

    }

    private void displayObstaclePassConditions(int nextObstDistance) {
        if (costOfFailureValue == 0) { //if leaving garage and Garage Gate
            costOfFailureFirstIterationFlag = true;
            costOfFailureValue = 25; //lose all clicks if fail first obstacle
        }
        obstacleConditionsTitle.setText("Speed Range:");
        obstacleConditions.setText(speedRangeRequiredObstaclesArray[obstacleTarget][0] + " - " + speedRangeRequiredObstaclesArray[obstacleTarget][1] + "m/s");
        timerObstacleTitle.setText("Time to hold Speed:");
        timerObstacle.setText(timerObstacleValue + "s");
        costOfFailure.setText("Cost of Failure: " + costOfFailureValue + "m");
        //calculate pass/fail
    }

    public void delayPanelAfterObstacleTimerUpdate() {
        if(!delayObstaclePanelOn) {
            delayObstaclePanelOn=true;
        }
        else if (delayObstaclePanelOn){
            delayPanelAfterObstacleTimer.stop();
        }

        delayObstaclePanelTimerSpeed = 1000;

        if(startDelayTimerFlag) {
            setDelayPanelAfterObstacleTimer();
            delayPanelAfterObstacleTimer.start();
            startDelayTimerFlag = false;
        }
    }

    public void countDownToPassObstacleTimerUpdate() {
        if(!countDownToPassObstacleOn) {
            countDownToPassObstacleOn=true;
        }
        else if (countDownToPassObstacleOn){
            countDownToPassObstacleTimer.stop();
        }

        countDownToPassObstacleTimerSpeed = 1000;
        timerObstacle.setText(timerObstacleValue + "s");

        if(startCountDownToPassObstacleFlag) {
            setCountDownToPassObstacleTimer();
            countDownToPassObstacleTimer.start();
            System.out.println("countdownTimerStarted, time is" + timerObstacleValue + "s");
            startCountDownToPassObstacleFlag = false;
        }
    }
    public void timerUpdate() {
        if (!timerOn && clickCount < 3500 && !carInMechanic) {
            timerOn = true;
        } else if (timerOn) {
            timer.stop();
        }
        if (stage2Start) {
            clicksPerSecond = 1;
        }
        if (clicksPerSecond > 0) {
            double speed = 1 / clicksPerSecond * 1000;
            timerSpeed = (int) Math.round(speed);
        }

        if (stage1) {
            String s = String.format("%.1f", clicksPerSecond);
            perSecondLabelLabel.setText("Metres of help per second:");
            perSecondLabel.setText(s);
        } else if (stage2) {
            Integer mS = ((int) clicksPerSecond);
            speedKmH = clicksPerSecond * 3.6;
            String kmHr = String.format("%.1f", speedKmH);
            perSecondLabelLabel.setText("Speed:");
            perSecondLabel.setText(mS + "m/s (" + kmHr + "km/hr)");
        }
        else {
            System.out.println("Beyond stage2 not setup yet");
        }

        if((!mechanicUnlocked && stage1 && clickCount<3500) || (stage2 && clicksPerSecond > 0)) {
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

                if (!mechanicUnlocked && clickCount<3500 && stage1) {
                    clickCount=clickCount+1750; //change +xxx value here to advance clicks quicker for debug
                    clickCountLabel.setText(clickCount+"m");

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
                else if (stage1 && clickCount>3500) {
                    clickCount=3500;
                    clickCountLabel.setText(clickCount+"m");
                    timer.stop();
                    temporarilyLockPowerUpsForMechanicMiniGame(1); //lock/unlock powerups while at mechanic
                }
                else {
                    if(stage1){
                    clickCount=3500;
                    clickCountLabel.setText(clickCount+"m");
                    price4.setText(clicksLeftToFixCar + "m");
                    temporarilyLockPowerUpsForMechanicMiniGame(1); //lock/unlock powerups while at mechanic
                    }
                }
                if (inBetweenMechanicAndClickStartEngine) {
                        clickCount=0;
                        clickCountLabel.setText("0m");
                }
 //           } //end cheat loop


                    break;
                case "Hired Help":
                    if(clickCount >= autoClickerPrice && !mechanicUnlocked && stage1){
                        clickCount = clickCount-autoClickerPrice;
                        autoClickerPrice = autoClickerPrice + 5;
                        clickCountLabel.setText(clickCount+"m");
                        price1.setText("" + autoClickerPrice + "m");
                        autoClickerNumber++;
                        button1.setText("Hired Help (" + autoClickerNumber + ")");
                        messageText.setText("Hired Help!\n[Price: " + autoClickerPrice + "]\nExtra manpower to push \nevery 10 seconds!");
                        clicksPerSecond = clicksPerSecond + 0.1;
                        timerUpdate();
                    }
                    else {
                        if (!mechanicTriggeredYet && stage1) {
                            messageText.setText("\n\nYou need " + (autoClickerPrice-clickCount) + " more metres\nto buy this upgrade!");
                        }
                        else {
                            messageText.setText("Already arrived at garage,\nno more hired help required");
                        }
                    }
                    break;
                case "Tow Truck":
                    if(clickCount >= towTruckPrice && !mechanicUnlocked && stage1){
                        clickCount = clickCount-towTruckPrice;
                        towTruckPrice = towTruckPrice + 50;
                        clickCountLabel.setText(clickCount+"m");
                        towTruckNumber++;
                        button2.setText("Tow Truck (" + towTruckNumber + ")");
                        price2.setText(towTruckPrice + "m");
                        messageText.setText("Call a Tow Truck!\n[Price: " + towTruckPrice + "]\nTow Truck will move you much\nfaster than a pair of hands!");
                        clicksPerSecond = clicksPerSecond + 5;
                        timerUpdate();
                    }
                    else {
                        if (!mechanicTriggeredYet && stage1) {
                        messageText.setText("\n\nYou need " + (towTruckPrice-clickCount) + " more metres\nto buy this upgrade!");
                        }
                        else {
                            messageText.setText("Already arrived at garage,\nno more tow trucks required");
                        }
                    }
                    break;
                case "Mechanic":
                    if(clickCount == mechanicPrice && stage1){
                        if (button3ClickIteration==0) {
                            button3.setText("Car is 0% fixed");
                            clickCountLabel.setText("0m");
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
                            inBetweenMechanicAndClickStartEngine = true;
                            clickCountLabel.setText("0m");
                            temporarilyLockPowerUpsForMechanicMiniGame(2);
                        }
                        button3ClickIteration++;
                    }
                    else {
                        if (!mechanicTriggeredYet && stage1) {
                            messageText.setText("\n\nYou need " + (mechanicPrice - clickCount) + " more metres\nto buy this upgrade!");
                        }
                        else{
                            messageText.setText("\n\nGarage Closed\nCar already repaired!");
                        }
                    }
                    break;
                case "LetsDrive":
                    if (repairCounterPercent == 100) {
                        letsDrive();
                    }
                    break;
                case "Accelerate":
                    System.out.println("acc");
                    accelerateClickedFlag = true;
                    if (clicksPerSecond <= 69) {
                        clicksPerSecond++;
                    }
                    timerUpdate();
                break;
                case "Brake":
                    System.out.println("brk");
                    if (accelerateClickedFlag) {
                        if (clicksPerSecond >= 1) {
                            clicksPerSecond--;
                        } else if (clicksPerSecond < 1) {
                            clicksPerSecond = 0;
                        }
                        timerUpdate();
                        if (nextObstDistance < 4 && nextObstDistance >= 0 && clicksPerSecond <= (speedRangeRequiredObstaclesArray[obstacleTarget][1] - speedRangeRequiredObstaclesArray[obstacleTarget][0])) {
                            startCountDownToPassObstacleFlag = true;
                            setCountDownToPassObstacleTimer();
                            countDownToPassObstacleTimerUpdate();
                        }
                    }
                    else if (!accelerateClickedFlag) {
                        clicksPerSecond = 0;
                    }
                break;
                case "SwerveLeft":
                    System.out.println("left");
                break;
                case "SwerveRight":
                    System.out.println("right");
                break;
            }
//
        }
    }
    private void letsDrive() {
        stage1 = false;
        stage2 = true;
        stage2Start = true;
        setDelayPanelAfterObstacleTimer();
        distanceToGoTitleLabel.setText("Distance to Steve's house:");
        distanceToGoLabel.setText("30000m");
        whatIsNextObstacle.setText("Next obstacle: " + obstacleType);
        distanceToNextObstacleTitleLabel.setText("Distance to obstacle:");
        distanceToNextObstacleLabel.setText("25m");
        button1.setActionCommand("Accelerate");
        button2.setActionCommand("Brake");
        button3.setActionCommand("SwerveLeft");
        button4.setActionCommand("SwerveRight");
        button1.setText("Accelerate");
        button2.setText("Brake");
        button3.setText("Left");
        button4.setText("Right");
        button4.setFont(font1);
        button4.setForeground(Color.black);
        price1.setText("");
        price2.setText("");
        price3.setText("");
        price4.setText("");
        price.setText("");
        stage2Start = false;
    }

    private void temporarilyLockPowerUpsForMechanicMiniGame(int toggle) {
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
        return 100; //debug skip repair clicks
    }

    public class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JButton button = (JButton) e.getSource();

            if (driveFirstClickFlag == 0 && inBetweenMechanicAndClickStartEngine && button == button4) {
                driveFirstClickFlag = 1;
                inBetweenMechanicAndClickStartEngine = false;
                Utils.playSound("startCar.mp3");
            }
            if (stage2) {
                if (button == button1) {
                    messageText.setText("\n\n\nAccelerate by 1m/s");
                }
                if (button == button2) {
                    messageText.setText("\n\n\nSlow by 1m/s");
                }
                if (button == button3) {
                    messageText.setText("\n\n\nSwerve Left!");
                }
                if (button == button4) {
                    messageText.setText("\n\n\nSwerve Right!");
                }
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

            }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JButton button = (JButton) e.getSource();

            if (stage1) {
                if (button == button1) {
                    if (!autoClickerUnlocked) {
                        messageText.setText("\n\n\nThis item is currently locked!");
                    } else {
                        messageText.setText("Hired Help!\n[Price: " + autoClickerPrice + "]\nExtra manpower to push \nevery 10 seconds!");
                    }
                } else if (button == button2) {
                    if (!towTruckUnlocked) {
                        messageText.setText("\n\n\nThis item is currently locked!");
                    } else {
                        messageText.setText("Call a Tow Truck!\n[Price: " + towTruckPrice + "]\nTow Truck will move you much\nfaster than a pair of hands!");
                    }

                } else if (button == button3) {
                    if (!mechanicUnlocked) {
                        messageText.setText("\n\n\nThis item is currently locked!");
                    } else {
                        messageText.setText("You arrived at the mechanic! Phew!\nSpend 3500m + 500 clicks to\nrepair your wheels!");
                    }
                } else if (button == button4) {
                    if (driveUnlocked && driveFirstClickFlag == 0) {
                        messageText.setText("Start your engine and\nbegin stage 2!");
                    } else {
                        messageText.setText("\n\n\nThis item is currently locked!");
                    }
                }
            }
                if (stage2) {
                    if (button == button1) {
                        messageText.setText("\n\n\nAccelerate by 1m/s");
                    }
                    if (button == button2) {
                        messageText.setText("\n\n\nSlow by 1m/s");
                    }
                    if (button == button3) {
                        messageText.setText("\n\n\nSwerve Left!");
                    }
                    if (button == button4) {
                        messageText.setText("\n\n\nSwerve Right!");
                    }

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