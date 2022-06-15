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
import java.util.Objects;

import static javax.swing.SwingConstants.RIGHT;

public class Clicker extends Application {
    final String LOCKED = "LOCKED";
    final int DISTANCE_TO_SWITCH_KM_TO_M = 5000;
    final int DISTANCE_DISPLAY_OBSTACLE_BOARD = 600;
    final int DISTANCE_ENTER_OBSTACLE_ELIGIBILITY = 10;
    final int DISTANCE_TO_TRIGGER_FAST_OBSTACLE_RANGE_COUNTDOWN_SPEED = 10;
    final int TIME_UNTIL_STEVE_MOVES = 600;
    final int MAX_DISTANCE_TO_ADD_IF_STEVE_MOVES = 8000;
    final int MIN_DISTANCE_TO_ADD_IF_STEVE_MOVES = 2000;
    final double WITHIN_RANGE_OBST_DISTANCE_COUNT_SPEED = 0.1;
    final double FAST_OBSTACLE_WITHIN_RANGE_COUNTDOWN_SPEED = 0.01;
    final int DELAY_TO_REMOVE_OBST_PANEL_AFTER_OBSTACLE = 3;
    final int PRICE_TO_UNLOCK_TOW_TRUCK = 200;
    final int COST_INCREASE_PER_TOW_TRUCK = 50;
    final int TOW_TRUCK_ADDS_THIS_MANY_CLICKS_PER_SECOND = 5;
    final int PRICE_TO_UNLOCK_MECHANIC = 3500;
    final int PRICE_TO_UNLOCK_AUTOCLICKER = 10;
    final int COST_INCREASE_PER_AUTOCLICKER = 5;
    final double AUTOCLICKER_ADDS_THIS_MANY_CLICKS_PER_SECOND = 0.1;
    final int CLICKS_TO_FIX_CAR = 10;
    final int NUMBER_OF_OBSTACLES_IN_ARRAY_MINUS_1 = 19;
    final int MAX_NEW_OBSTACLE_DISTANCE_STG2_1 = 2000;
    final int MIN_NEW_OBSTACLE_DISTANCE_STG2_1 = 650;
    final int MAX_NEW_OBSTACLE_DISTANCE_STG2_2 = 12000;
    final int MIN_NEW_OBSTACLE_DISTANCE_STG2_2 = 5000;
    final int MAX_NEW_TIMER_AMOUNT = 7;
    final int MIN_NEW_TIMER_AMOUNT = 5;
    final int COST_OF_FAILURE_FIRST_ITERATION_FACTOR = 12;
    final int COST_OF_FAILURE_FURTHER_ITERATIONS_FACTOR1 = 33;
    final int COST_OF_FAILURE_FURTHER_ITERATIONS_FACTOR2 = 10;
    final int DISTANCE_TO_GARAGE_GATE = 25;
    final int DISTANCE_TO_STEVE = 20000;
    final int DISTANCE_TO_ESCAPE_PLANE = 50000;
    final int COST_OF_FAILURE_FOR_HITTING_STEVE = 10000;
    final int DEBUG_QUICK_CLICKER_STG_1 = 1750;
    final int MAX_SPEED_OF_CAR_MINUS_1_STAGE2 = 69;
    final int MAX_NO_OF_TURNING_CLICKS = 10;
    final int MIN_NO_OF_TURNING_CLICKS = 0;
    final int FIRST_OBSTACLE_STG2_2 = 2500;

    String obstacleType, km, kmSteve;
    JLabel stage2ElapsedValue, stage2ElapsedLabel, metresTravelledLabel, clickCountLabel, perSecondLabelLabel, perSecondLabel, price, price1, price2, price3, price4, distanceToGoLabel, distanceToGoTitleLabel, whatIsNextObstacle, distanceToNextObstacleTitleLabel, distanceToNextObstacleLabel;
    JLabel obstacleConditionsTitle, obstacleConditions, timerObstacleTitle, timerObstacle, passFailObstacle, costOfFailure;
    JButton button1, button2, button3, button4;
    int clickCount, timerSpeed, secondsElapsedDelayToRemoveObstaclePanel, autoClickerNumber, autoClickerPrice, towTruckNumber, towTruckPrice, mechanicPrice, button3ClickIteration, repairCounter, clicksLeftToFixCar, distanceToSteve;
    int randomSteveMovementModifier, driveFirstClickFlag, obstacleTarget, costOfFailureValue,timerObstacleValue, originalTimerObstacleValue, passObstacleFlag, rangePermitted, rangeActual, generalTimerElapsedSeconds, generalTimerSecondsToDisplay, generalTimerElapsedMinutes;
    int countDisplay, distanceToSteveAfterFail, requiredLeftClicks, requiredRightClicks, countTimesPassTimeUntilSteveMoves, leftClickCount, rightClickCount;
    double clicksPerSecond, speedKmH, nextObstDistance;
    float repairCounterPercent;
    boolean stage1, stage2, stage2Start, inBetweenMechanicAndClickStartEngine, timerOn, delayObstaclePanelOn, autoClickerUnlocked, towTruckUnlocked, mechanicTriggeredYet, mechanicUnlocked, driveUnlocked, carInMechanic, accelerateClickedFlag,displayObstacleConditionsFlag, costOfFailureFirstIterationFlag;
    boolean failedObstacleWithinApproachOfSteve, approachingSteveFlag, checkTimeSteveMoveCount, startDelayTimerFlag, countDownToPassObstacleOn, startCountDownToPassObstacleFlag, wasPassingNowFailing, moreThanOneMinuteElapsedFlag;
    boolean stg2Part2;
    Font font1, font2, font3;
    ClickHandler cHandler = new ClickHandler();
    Timer timer, delayPanelAfterObstacleTimer, countDownToPassObstacleTimer, generalElapsedCounter;
    JTextArea messageText;
    MouseHandler mHandler = new MouseHandler();
    Border raisedBorder = BorderFactory.createRaisedBevelBorder();
    String[] obstacleNameArrayStg2_1 = {"Garage Gate", "Sheep Crossing", "Police Checkpoint", "Level Crossing", "Tractor Up Ahead", "Dog Running in Road", "Drunk Man in Road", "Fallen Rocks", "Broken Water Pipe", "Kids Playing in Road",
            "Turning Lorry Up Ahead", "Bin Wagon Spilled Trash", "Fallen Tree", "Road Works Up Ahead", "Bicycle Up Ahead", "Mate Wants A Race", "Road Rager Chasing You", "Minimum Speed Limit", "Out Accelerate A Sports Car", "Out Accelerate A Hatchback", "Steve"};
    String[] obstacleNameArrayStg2_2 = {"Motorway Entry Road", "Slow Lorry Convoy", "Broken Down Van", "Pedestrian on Motorway", "Overpass Collapse", "Race a 1300cc Motorbike", "Jump The Ramp", "Max Power", "Steady She Goes", "Motorway Exit", "Airfield Entrance", "Escape Plane"};
    final Integer[][] SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1 = {{0, 0, 0}, {2, 8, 1}, {1, 2, 0}, {0, 0, 0}, {3, 6, 1}, {2, 9, 1}, {4, 8, 1}, {3, 10, 1}, {6, 12, 1}, {2, 4, 1}, {8, 11, 1}, {6, 15, 1}, {3, 14, 1}, {0, 1, 1}, {3, 15, 1}, {50, 60, 0}, {45, 50, 0}, {30, 70, 0}, {65, 70, 0}, {60, 65, 0}, {0, 0, 0}};
    final Integer[][] SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_2 = {{40, 50}, {15, 25}, {20, 30}, {10, 15}, {5, 10}, {110, 140}, {150, 180}, {300, 350}, {40, 50}, {30, 40}, {10, 15}, {0, 0}};
    public static void main(String[] args) {
        new Clicker();

    }

    public Clicker() {
        km = "";
        kmSteve = "";
        clicksPerSecond = 0;
        clickCount = 0;
        autoClickerNumber = 0;
        autoClickerPrice = PRICE_TO_UNLOCK_AUTOCLICKER;
        towTruckNumber = 0;
        towTruckPrice = PRICE_TO_UNLOCK_TOW_TRUCK;
        mechanicPrice = PRICE_TO_UNLOCK_MECHANIC;
        repairCounter = 0;
        clicksLeftToFixCar = CLICKS_TO_FIX_CAR;
        stage1 = true;
        driveFirstClickFlag = 0;
        obstacleType = "Garage Gate";
        nextObstDistance = DISTANCE_TO_GARAGE_GATE;
        distanceToSteve = DISTANCE_TO_STEVE;
        randomSteveMovementModifier = 0;
        obstacleTarget = 0;
        costOfFailureValue = 0;
        originalTimerObstacleValue = (int) ((Math.random() * (MAX_NEW_TIMER_AMOUNT - MIN_NEW_TIMER_AMOUNT)) + MIN_NEW_TIMER_AMOUNT);
        timerObstacleValue = originalTimerObstacleValue;
        passObstacleFlag = 0; //1 pass obst 2 fail obst
        rangePermitted = 0;
        rangeActual = 0;
        generalTimerElapsedMinutes = 0;
        generalTimerElapsedSeconds = 0;
        countTimesPassTimeUntilSteveMoves = 1;
        leftClickCount = 0;
        rightClickCount = 0;
        requiredLeftClicks = 0;
        requiredRightClicks = 0;
        countDisplay = 0;
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

        ImageIcon pushCar = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resource/carImage.png")));

        JButton carButton = new JButton();
        carButton.setBackground(Color.black);
        carButton.setFocusPainted(false);
        carButton.setBorder(null);
        carButton.setIcon(pushCar);
        carButton.addActionListener(cHandler);
        carButton.setActionCommand("pushCar");
        clickHere.add(carButton);

        JPanel clickCounter = new JPanel();
        clickCounter.setBounds(100,50,320,150);
        clickCounter.setBackground(Color.black);
        clickCounter.setLayout(new GridLayout(6,1));
        window.add(clickCounter);

        stage2ElapsedLabel = new JLabel("");
        stage2ElapsedLabel.setForeground(Color.yellow);
        stage2ElapsedLabel.setFont(font2);
        clickCounter.add(stage2ElapsedLabel);

        stage2ElapsedValue = new JLabel("");
        stage2ElapsedValue.setForeground(Color.white);
        stage2ElapsedValue.setFont(font1);
        clickCounter.add(stage2ElapsedValue);

        metresTravelledLabel = new JLabel("Distance travelled:");
        metresTravelledLabel.setForeground(Color.yellow);
        metresTravelledLabel.setFont(font2);
        clickCounter.add(metresTravelledLabel);

        clickCountLabel = new JLabel(clickCount+"m");
        clickCountLabel.setForeground(Color.white);
        clickCountLabel.setFont(font1);
        clickCounter.add(clickCountLabel);

        perSecondLabelLabel = new JLabel();
        perSecondLabelLabel.setForeground(Color.yellow);
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

        button1 = new JButton(LOCKED);
        button1.setFont(font1);
        button1.setFocusPainted(false);
        button1.addActionListener(cHandler);
        button1.setActionCommand("Hired Help");
        button1.addMouseListener(mHandler);
        itemPanel.add(button1);

        button2 = new JButton(LOCKED);
        button2.setFont(font1);
        button2.setFocusPainted(false);
        button2.addActionListener(cHandler);
        button2.setActionCommand("Tow Truck");
        button2.addMouseListener(mHandler);
        itemPanel.add(button2);

        button3 = new JButton(LOCKED);
        button3.setFont(font1);
        button3.setFocusPainted(false);
        button3.addActionListener(cHandler);
        button3.setActionCommand("Mechanic");
        button3.addMouseListener(mHandler);
        itemPanel.add(button3);

        button4 = new JButton(LOCKED);
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

        distanceToNextObstacleLabel = new JLabel("");
        distanceToNextObstacleLabel.setForeground(Color.white);
        distanceToNextObstacleLabel.setFont(font1);
        bottomDistanceInfoPanel.add(distanceToNextObstacleLabel);

        whatIsNextObstacle = new JLabel("");
        whatIsNextObstacle.setForeground(Color.white);
        whatIsNextObstacle.setFont(font2);
        bottomDistanceInfoPanel.add(whatIsNextObstacle);

        window.setVisible(true);
    }

    public void setStage2ElapsedTimer() {
        generalElapsedCounter = new Timer(1000, e -> {
            generalTimerElapsedSeconds++;
            generalTimerSecondsToDisplay++;
        });
    }

    public void setDelayPanelAfterObstacleTimer() {
        delayPanelAfterObstacleTimer = new Timer(1000, e -> secondsElapsedDelayToRemoveObstaclePanel++);
    }

    private void setCountDownToPassObstacleTimer() {
        countDownToPassObstacleTimer = new Timer(1000, e -> {
            timerObstacleValue--;
            if (timerObstacleValue >=0) {
                timerObstacle.setText(timerObstacleValue + "s");
                if (!approachingSteveFlag) {
                    distanceToNextObstacleLabel.setText((int) nextObstDistance + "m");
                }
            }
            if (timerObstacleValue <=0 && countDownToPassObstacleOn) {
                countDownToPassObstacleTimer.stop();
                passObstacleFlag = 1;
                countDownToPassObstacleOn = false;
                countDownToPassObstacleTimerUpdate();
            }
            if (nextObstDistance <= 0 && (rangeActual > rangePermitted || rangeActual < 0)) {
                passObstacleFlag = 2;
                countDownToPassObstacleTimerUpdate();
            }
        });
    }
    public void setTimer() {
        timer = new Timer(timerSpeed, e -> {
            clickCount++;
            if (stage2){
                distanceToSteve--;
                double cCountAsDouble = clickCount;
                double distanceToSteveAsDouble = distanceToSteve;
                DecimalFormat num = new DecimalFormat("0.00");
                num.setRoundingMode(RoundingMode.FLOOR);
                km = num.format(cCountAsDouble/1000);
                kmSteve = num.format(distanceToSteveAsDouble/1000);
                if (clickCount > DISTANCE_TO_SWITCH_KM_TO_M) {
                    clickCountLabel.setText(km + "km");
                } else {
                    clickCountLabel.setText(clickCount + "m");
                }
                if (distanceToSteve >= DISTANCE_TO_SWITCH_KM_TO_M) {
                    distanceToGoLabel.setText(kmSteve + "km");
                } else {
                    distanceToGoLabel.setText(distanceToSteve + "m");
                }
                if (!approachingSteveFlag) {
                    stage2ElapsedLabel.setText("Steve Moves Every 10 Minutes:");
                }
                if (generalTimerElapsedSeconds >= 60 && generalTimerSecondsToDisplay >= 60) {
                    generalTimerElapsedMinutes++;
                    generalTimerSecondsToDisplay = 0;
                    moreThanOneMinuteElapsedFlag = true;
                }
                if (!moreThanOneMinuteElapsedFlag && generalTimerElapsedSeconds < 60) {
                    if (generalTimerElapsedSeconds < 10 && !approachingSteveFlag) {
                        stage2ElapsedValue.setText("0:0" + generalTimerSecondsToDisplay);
                    } else {
                        if(!approachingSteveFlag) {
                            stage2ElapsedValue.setText("0:" + generalTimerSecondsToDisplay);
                        }
                    }
                } else {
                    if (generalTimerSecondsToDisplay < 10 && !approachingSteveFlag) {
                        stage2ElapsedValue.setText(generalTimerElapsedMinutes + ":0" + generalTimerSecondsToDisplay);
                    } else {
                        if (!approachingSteveFlag) {
                            stage2ElapsedValue.setText(generalTimerElapsedMinutes + ":" + generalTimerSecondsToDisplay);
                        }
                    }
                }
                if (generalTimerElapsedSeconds % TIME_UNTIL_STEVE_MOVES == 0 && !checkTimeSteveMoveCount) {
                    checkTimeSteveMoveCount = true;
                    randomSteveMovementModifier = (int) ((Math.random() * (MAX_DISTANCE_TO_ADD_IF_STEVE_MOVES - MIN_DISTANCE_TO_ADD_IF_STEVE_MOVES)) + MIN_DISTANCE_TO_ADD_IF_STEVE_MOVES);
                    distanceToSteve = distanceToSteve + randomSteveMovementModifier;
                    if (distanceToSteve > DISTANCE_TO_SWITCH_KM_TO_M) {
                        distanceToGoLabel.setText(kmSteve + "km");
                    } else {
                        distanceToGoLabel.setText(distanceToSteve + "m");
                    }
                }
                if (generalTimerElapsedSeconds >= (TIME_UNTIL_STEVE_MOVES * countTimesPassTimeUntilSteveMoves) - 30 && generalTimerElapsedSeconds < (TIME_UNTIL_STEVE_MOVES * countTimesPassTimeUntilSteveMoves)) {
                    stage2ElapsedValue.setForeground(Color.red);
                    checkTimeSteveMoveCount = false;
                } else if (generalTimerElapsedSeconds >= TIME_UNTIL_STEVE_MOVES * countTimesPassTimeUntilSteveMoves && !approachingSteveFlag) {
                    stage2ElapsedValue.setForeground(Color.white);
                    stage2ElapsedValue.setText("0:00");
                    countTimesPassTimeUntilSteveMoves++;
                    generalTimerElapsedMinutes = 0;
                    generalTimerSecondsToDisplay = 0;
                }
                if (!countDownToPassObstacleOn) {
                    nextObstDistance--;
                } else {
                    if (nextObstDistance < DISTANCE_TO_TRIGGER_FAST_OBSTACLE_RANGE_COUNTDOWN_SPEED) {
                        nextObstDistance = nextObstDistance - FAST_OBSTACLE_WITHIN_RANGE_COUNTDOWN_SPEED;
                    }
                    else {
                        nextObstDistance = nextObstDistance - WITHIN_RANGE_OBST_DISTANCE_COUNT_SPEED;
                    }
                }
                if (secondsElapsedDelayToRemoveObstaclePanel > 2) {
                    passFailObstacle.setText("");
                }
                if (!approachingSteveFlag) {
                    distanceToNextObstacleLabel.setText(((int) nextObstDistance) + "m");
                }
                if(nextObstDistance < DISTANCE_DISPLAY_OBSTACLE_BOARD && nextObstDistance >= 3) {
                    displayObstacleConditionsFlag = true;
                    countDisplay++;
                    displayObstaclePassConditions(countDisplay);
                }
                if (nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && !countDownToPassObstacleOn && nextObstDistance >= 0 && (rangeActual <= rangePermitted && rangeActual >= 0)) {
                    startCountDownToPassObstacleFlag = true;
                    setCountDownToPassObstacleTimer();
                    countDownToPassObstacleTimerUpdate();
                }
                if ((rangeActual > rangePermitted || rangeActual < 0) && (countDownToPassObstacleOn || nextObstDistance <= 0)) {
                    if (nextObstDistance <= 0) {
                        passObstacleFlag = 2;
                    }
                    countDownToPassObstacleTimerUpdate();
                }
                if (!displayObstacleConditionsFlag) {
                    countDisplay = 0;
                    obstacleConditionsTitle.setText("");
                    obstacleConditions.setText("");
                    timerObstacleTitle.setText("");
                    timerObstacle.setText("");
                    costOfFailure.setText("");
                    passFailObstacle.setText("");
                }
                if(secondsElapsedDelayToRemoveObstaclePanel >= DELAY_TO_REMOVE_OBST_PANEL_AFTER_OBSTACLE) {
                    displayObstacleConditionsFlag = false;
                    delayPanelAfterObstacleTimer.stop();
                    secondsElapsedDelayToRemoveObstaclePanel = 0;
                }
            }
            else if (stage1){
                clickCountLabel.setText(clickCount + "m");
            }

            if(!towTruckUnlocked && clickCount >= PRICE_TO_UNLOCK_TOW_TRUCK && stage1) {
                towTruckUnlocked = true;
                button2.setText("Tow Truck");
            }
            if(clickCount >= PRICE_TO_UNLOCK_MECHANIC && stage1) {
                clickCount = PRICE_TO_UNLOCK_MECHANIC;
                timer.stop();
                perSecondLabelLabel.setText("");
                perSecondLabel.setText("");
                price4.setText(CLICKS_TO_FIX_CAR + "m");
                temporarilyLockPowerUpsForMechanicMiniGame(1);
            }
        });
    }

    private void setupNextObstacle(int passObstacleFlag, int distanceToSteveAfterFailure) {
        if (passObstacleFlag == 2) {
            if (failedObstacleWithinApproachOfSteve) {
                steveApproach(distanceToSteveAfterFailure);
            }
            wasPassingNowFailing = false;
            clicksPerSecond = 0;
            perSecondLabel.setText(clicksPerSecond + "m/s");
            timerUpdate();
        }
        if (distanceToSteve > 2500) {
            requiredLeftClicks = (int) ((Math.random() * (MAX_NO_OF_TURNING_CLICKS - MIN_NO_OF_TURNING_CLICKS)) + MIN_NO_OF_TURNING_CLICKS);
            if (requiredLeftClicks == 0) { //remove possibility of L:0 | R:0 random generation
                requiredRightClicks = (int) ((Math.random() * (MAX_NO_OF_TURNING_CLICKS - MIN_NO_OF_TURNING_CLICKS - 1)) + MIN_NO_OF_TURNING_CLICKS + 1);
            } else {
                requiredRightClicks = (int) ((Math.random() * (MAX_NO_OF_TURNING_CLICKS - MIN_NO_OF_TURNING_CLICKS)) + MIN_NO_OF_TURNING_CLICKS);
            }
            System.out.println("Left Needed = " + requiredLeftClicks);
            System.out.println("Right Needed = " + requiredRightClicks);
            int value = (int) (Math.random() * NUMBER_OF_OBSTACLES_IN_ARRAY_MINUS_1) + 1; //pick a new obstacle
            countDownToPassObstacleOn = false;
            obstacleType = obstacleNameArrayStg2_1[value];
            obstacleTarget = value;
            whatIsNextObstacle.setText("");
            nextObstDistance = (int) ((Math.random() * (MAX_NEW_OBSTACLE_DISTANCE_STG2_1 - MIN_NEW_OBSTACLE_DISTANCE_STG2_1)) + MIN_NEW_OBSTACLE_DISTANCE_STG2_1); //distance of next obstacle
            if (!approachingSteveFlag) {
                distanceToNextObstacleLabel.setText((int) nextObstDistance + "m");
            }
            originalTimerObstacleValue = (int) ((Math.random() * (MAX_NEW_TIMER_AMOUNT - MIN_NEW_TIMER_AMOUNT)) + MIN_NEW_TIMER_AMOUNT);
            timerObstacleValue = originalTimerObstacleValue;
            if (costOfFailureFirstIterationFlag) {
                costOfFailureValue = (int) (Math.random() * (clickCount/100 * COST_OF_FAILURE_FIRST_ITERATION_FACTOR)) + 200;
            } else {
                costOfFailureValue = (int) (Math.random() * (clickCount/100 * COST_OF_FAILURE_FURTHER_ITERATIONS_FACTOR1)) + (clickCount/100 * COST_OF_FAILURE_FURTHER_ITERATIONS_FACTOR2);
            }
        } else {
            steveApproach(distanceToSteveAfterFailure);
            //final Obstacle Stage 2
        }
    }

    private void steveApproach(int distanceToSteveAfterFailure) {
        if (distanceToSteveAfterFailure < 2500 || distanceToSteve < 2500) {
            approachingSteveFlag = true;
            generalElapsedCounter.stop();
            stage2ElapsedLabel.setText("Steve will wait for you!");
            stage2ElapsedValue.setForeground(Color.green);
            stage2ElapsedValue.setText("WAITING");
            costOfFailureValue = COST_OF_FAILURE_FOR_HITTING_STEVE;
            countDownToPassObstacleOn = false;
            obstacleType = obstacleNameArrayStg2_1[20];
            obstacleTarget = 20;
            whatIsNextObstacle.setText("");
            nextObstDistance = distanceToSteve; //distance of next obstacle
            distanceToNextObstacleTitleLabel.setText("");
            distanceToNextObstacleLabel.setText("");
            originalTimerObstacleValue = 3;
            timerObstacleValue = originalTimerObstacleValue;
            distanceToGoTitleLabel.setForeground(Color.red);
            distanceToGoTitleLabel.setText("STOP WITHIN 10m of STEVE!");
        }
    }

    private void displayObstaclePassConditions(int count) {
        if (count == 1) { //bug fix for when passing and no button clicks allowing passing of next obstacle
            double newSpeed = clicksPerSecond;
            button2.doClick();
            if (newSpeed > clicksPerSecond) {
                clicksPerSecond++;
                Integer mS = ((int) clicksPerSecond);
                speedKmH = clicksPerSecond * 3.6;
                String kmHr = String.format("%.1f", speedKmH);
                perSecondLabel.setText(mS + "m/s (" + kmHr + "km/hr)");
            }
        } //end of bug fix
        if (costOfFailureValue == 0) { //if leaving garage and Garage Gate
            costOfFailureFirstIterationFlag = true;
            costOfFailureValue = DISTANCE_TO_GARAGE_GATE; //lose all clicks if fail first obstacle
        }
        obstacleConditionsTitle.setText("Speed Range:");
        obstacleConditions.setText(SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][0] + " - " + SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][1] + "m/s");
        timerObstacleTitle.setText("Time to hold Speed:");
        timerObstacle.setText(timerObstacleValue + "s");
        costOfFailure.setText("Cost of Failure: " + costOfFailureValue + "m");
        whatIsNextObstacle.setText("Next obstacle: " + obstacleType);
        if (leftClickCount == 0 && rightClickCount == 0 && SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][2] == 1) {
            passFailObstacle.setForeground(Color.yellow);
            passFailObstacle.setFont(font1);
            passFailObstacle.setText("L: " + requiredLeftClicks + " | R: " + requiredRightClicks);
        }
        if (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][2] == 0) {
            passFailObstacle.setForeground(Color.yellow);
            passFailObstacle.setFont(font2);
            passFailObstacle.setText("NO TURNING REQUIRED");
        }
    }

    public void obstaclePassed() {
        leftClickCount = 0;
        rightClickCount = 0;
        passFailObstacle.setForeground(Color.green);
        passFailObstacle.setFont(font3);
        if (!approachingSteveFlag) {
            passFailObstacle.setText("PASS");
        } else {
            passFailObstacle.setText("YOU MET STEVE!!");
            stage2ElapsedValue.setText("NICE ONE MATE!");
        }
        startDelayTimerFlag = true;
        delayPanelAfterObstacleTimerUpdate();
        setupNextObstacle(passObstacleFlag, distanceToSteveAfterFail);
        passObstacleFlag = 0;
        startCountDownToPassObstacleFlag = false;
        countDownToPassObstacleTimer.stop();
        if (approachingSteveFlag && distanceToSteve < 10) {
            beatStage2Part1SetupPart2();
        }
    }

    public void obstacleFailed() {
        leftClickCount = 0;
        rightClickCount = 0;
        passFailObstacle.setForeground(Color.red);
        passFailObstacle.setFont(font3);
        if (!approachingSteveFlag) {
            passFailObstacle.setText("FAIL");
            distanceToSteveAfterFail = distanceToSteve;
            if (distanceToSteve < 2500) {
                failedObstacleWithinApproachOfSteve = true;
            }
        } else {
            passFailObstacle.setText("YOU HIT STEVE!!");
            failedObstacleWithinApproachOfSteve = false;
            unsetSteveApproach();
        }
                startDelayTimerFlag = true;
        delayPanelAfterObstacleTimerUpdate();
        if (clickCount >= costOfFailureValue) { //subtract clicks for failing
            clickCount = clickCount - costOfFailureValue;
        } else { // set clicks to zero if garage gate
            clickCount = 0;
        }
        distanceToSteve = distanceToSteve + costOfFailureValue;
        if (costOfFailureFirstIterationFlag) {
            costOfFailureFirstIterationFlag = false;
        } else {
            countDownToPassObstacleTimer.stop();
        }
        countDownToPassObstacleOn = false;
        setupNextObstacle(passObstacleFlag, distanceToSteveAfterFail);
        passObstacleFlag = 0;
        startCountDownToPassObstacleFlag = false;
        if (approachingSteveFlag) { //fail actually on steve
            if (!failedObstacleWithinApproachOfSteve) {
                unsetSteveApproach();
            }
        } else if (failedObstacleWithinApproachOfSteve) { //fail previous to steve but within range
            steveApproach(distanceToSteveAfterFail);
        }
    }

    private void unsetSteveApproach() {
        approachingSteveFlag = false;
        stage2ElapsedLabel.setForeground(Color.yellow);
        stage2ElapsedLabel.setText("Steve Moves Every 10 Minutes:");
        distanceToGoTitleLabel.setForeground(Color.yellow);
        distanceToGoTitleLabel.setText("Distance to Steve:");
        distanceToNextObstacleTitleLabel.setText("Distance to obstacle:");
        stage2ElapsedValue.setForeground(Color.white);
        generalElapsedCounter.start();
        if (!moreThanOneMinuteElapsedFlag && generalTimerElapsedSeconds < 60) {
            if (generalTimerElapsedSeconds < 10) {
                stage2ElapsedValue.setText("0:0" + generalTimerSecondsToDisplay);
            } else {
                stage2ElapsedValue.setText("0:" + generalTimerSecondsToDisplay);
            }
        } else {
            if (generalTimerSecondsToDisplay < 10) {
                stage2ElapsedValue.setText(generalTimerElapsedMinutes + ":0" + generalTimerSecondsToDisplay);
            } else {
                stage2ElapsedValue.setText(generalTimerElapsedMinutes + ":" + generalTimerSecondsToDisplay);
            }
        }
    }

    public void delayPanelAfterObstacleTimerUpdate() {
        if(!delayObstaclePanelOn) {
            delayObstaclePanelOn=true;
        }
        else {
            delayPanelAfterObstacleTimer.stop();
        }

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
        else {
            countDownToPassObstacleTimer.stop();
        }

        if(startCountDownToPassObstacleFlag) {
            countDownToPassObstacleTimer.start();
            System.out.println("countdownTimerStarted, time is" + timerObstacleValue + "s");
            startCountDownToPassObstacleFlag = false;
        }
        if(passObstacleFlag == 1 && SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][2] == 1 && requiredLeftClicks == leftClickCount && requiredRightClicks == rightClickCount) {
            countDownToPassObstacleTimer.stop();
            obstaclePassed();
            }
        if(passObstacleFlag == 1 && SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][2] == 0) {
            countDownToPassObstacleTimer.stop();
            obstaclePassed();
        }
        if(passObstacleFlag == 1 && SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][2] == 1 && (requiredLeftClicks != leftClickCount || requiredRightClicks != rightClickCount)) {
            passObstacleFlag = 2;
            countDownToPassObstacleTimer.stop();
            obstacleFailed();
        }
        if (passObstacleFlag == 2) {
            obstacleFailed();
        }
        if ((rangeActual > rangePermitted || rangeActual < 0) && nextObstDistance > 0) {
            countDownToPassObstacleTimer.stop();
            timerObstacleValue = originalTimerObstacleValue;
            if (!Objects.equals(timerObstacle.getText(), "")) { //if already showing obstacle panel info, then run this, otherwise no, to fix flickering seconds after passing
                timerObstacle.setText(timerObstacleValue + "s");
            }
            countDownToPassObstacleOn = false;
        }
    }
    public void timerUpdate() {
        if (!timerOn && clickCount < PRICE_TO_UNLOCK_MECHANIC && !carInMechanic) { //WATCH FOR ISSUES HERE ABOVE 3500M STAGE 2
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
        rangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][0];
        rangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][0]);
        if (countDownToPassObstacleOn && nextObstDistance > 0 && (rangeActual > rangePermitted || rangeActual < 0)) {
            wasPassingNowFailing = true;
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

        if((!mechanicUnlocked && stage1 && clickCount < PRICE_TO_UNLOCK_MECHANIC) || (stage2 && clicksPerSecond > 0)) {
            setTimer();
            timer.start();
        }
    }
    public class ClickHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            String action = event.getActionCommand();

            switch(action) {
                case "pushCar":
                if (!mechanicUnlocked && clickCount < PRICE_TO_UNLOCK_MECHANIC && stage1) {
                    clickCount=clickCount + DEBUG_QUICK_CLICKER_STG_1; //replace with real value when game ready
                    clickCountLabel.setText(clickCount+"m");

                    if (!autoClickerUnlocked && clickCount >= PRICE_TO_UNLOCK_AUTOCLICKER) {
                        autoClickerUnlocked = true;
                        button1.setText("Hired Help");
                        price1.setText(autoClickerPrice + "m");
                        price2.setText(towTruckPrice + "m");

                    }

                    if (!towTruckUnlocked && clickCount >= PRICE_TO_UNLOCK_TOW_TRUCK) {
                        towTruckUnlocked = true;
                        button2.setText("Tow Truck");
                        price3.setText(mechanicPrice + "m");
                    }

                }
                else if (stage1 && clickCount > PRICE_TO_UNLOCK_MECHANIC) {
                    clickCount=PRICE_TO_UNLOCK_MECHANIC;
                    clickCountLabel.setText(clickCount+"m");
                    timer.stop();
                    temporarilyLockPowerUpsForMechanicMiniGame(1); //lock/unlock power ups while at mechanic
                }
                else {
                    if(stage1){
                    clickCount = PRICE_TO_UNLOCK_MECHANIC;
                    clickCountLabel.setText(clickCount+"m");
                    price4.setText(clicksLeftToFixCar + "m");
                    }
                }
                temporarilyLockPowerUpsForMechanicMiniGame(1); //lock/unlock power ups while at mechanic
                if (inBetweenMechanicAndClickStartEngine) {
                        clickCount=0;
                        clickCountLabel.setText("0m");
                }
                    break;
                case "Hired Help":
                    if(clickCount >= autoClickerPrice && !mechanicUnlocked && stage1){
                        clickCount = clickCount-autoClickerPrice;
                        autoClickerPrice = autoClickerPrice + COST_INCREASE_PER_AUTOCLICKER;
                        clickCountLabel.setText(clickCount+"m");
                        price1.setText("" + autoClickerPrice + "m");
                        autoClickerNumber++;
                        button1.setText("Hired Help (" + autoClickerNumber + ")");
                        messageText.setText("Hired Help!\n[Price: " + autoClickerPrice + "]\nExtra manpower to push \nevery 10 seconds!");
                        clicksPerSecond = clicksPerSecond + AUTOCLICKER_ADDS_THIS_MANY_CLICKS_PER_SECOND;
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
                        towTruckPrice = towTruckPrice + COST_INCREASE_PER_TOW_TRUCK;
                        clickCountLabel.setText(clickCount+"m");
                        towTruckNumber++;
                        button2.setText("Tow Truck (" + towTruckNumber + ")");
                        price2.setText(towTruckPrice + "m");
                        messageText.setText("Call a Tow Truck!\n[Price: " + towTruckPrice + "]\nTow Truck will move you much\nfaster than a pair of hands!");
                        clicksPerSecond = clicksPerSecond + TOW_TRUCK_ADDS_THIS_MANY_CLICKS_PER_SECOND;
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
                        repairCounterPercent = (float) repairCounter / CLICKS_TO_FIX_CAR * 100;
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
                    if (clicksPerSecond <= MAX_SPEED_OF_CAR_MINUS_1_STAGE2) {
                        clicksPerSecond++;
                    }
                    timerUpdate();
                    rangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][0];
                    rangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][0]);
                    if (countDownToPassObstacleOn && nextObstDistance > 0 && (rangeActual > rangePermitted || rangeActual < 0)) {
                        wasPassingNowFailing = true;
                    }
                    if (nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && !countDownToPassObstacleOn && nextObstDistance >= 0 && (rangeActual <= rangePermitted && rangeActual >= 0)) {
                        startCountDownToPassObstacleFlag = true;
                        setCountDownToPassObstacleTimer();
                        countDownToPassObstacleTimerUpdate();
                    }
                    if (wasPassingNowFailing) {
                        //System.out.println("now failing, timer should reset");
                        countDownToPassObstacleOn = false;
                        startCountDownToPassObstacleFlag = false;
                        countDownToPassObstacleTimer.stop();
                        setCountDownToPassObstacleTimer();
                        countDownToPassObstacleTimerUpdate();
                        if (nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && nextObstDistance >= 0 && (rangeActual <= rangePermitted && rangeActual >= 0)) {
                            //System.out.println("not failing any more timer should start again");
                            wasPassingNowFailing = false;
                            startCountDownToPassObstacleFlag = true;
                            setCountDownToPassObstacleTimer();
                            countDownToPassObstacleTimerUpdate();
                        }
                    }
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
                        rangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][0];
                        rangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][0]);
                        if (countDownToPassObstacleOn && nextObstDistance > 0 && (rangeActual > rangePermitted || rangeActual < 0)) {
                            wasPassingNowFailing = true;
                        }
                        if (nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && !countDownToPassObstacleOn && nextObstDistance >= 0 && (rangeActual <= rangePermitted && rangeActual >= 0)) {
                            startCountDownToPassObstacleFlag = true;
                            setCountDownToPassObstacleTimer();
                            countDownToPassObstacleTimerUpdate();
                        }
                        if (wasPassingNowFailing) {
                            //System.out.println("now failing, timer should reset");
                            countDownToPassObstacleOn = false;
                            startCountDownToPassObstacleFlag = false;
                            countDownToPassObstacleTimer.stop();
                            setCountDownToPassObstacleTimer();
                            countDownToPassObstacleTimerUpdate();
                            if (nextObstDistance < DISTANCE_ENTER_OBSTACLE_ELIGIBILITY && nextObstDistance >= 0 && (rangeActual <= rangePermitted && rangeActual >= 0)) {
                                //System.out.println("not failing any more timer should start again");
                                wasPassingNowFailing = false;
                                startCountDownToPassObstacleFlag = true;
                                setCountDownToPassObstacleTimer();
                                countDownToPassObstacleTimerUpdate();
                            }
                        }
                    }
                    else {
                        clicksPerSecond = 0;
                    }
                break;
                case "SwerveLeft":
                    //System.out.println("left");
                    if (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][2] == 1 && countDownToPassObstacleOn) { //if feature applicable to target and user in obstacle countdown
                        leftClickCount++;
                        passFailObstacle.setFont(font1);
                        passFailObstacle.setText("L: " + (requiredLeftClicks - leftClickCount) + " | R: " + (requiredRightClicks - rightClickCount));
                        System.out.println("valid left clicks is" + leftClickCount);
                        if (leftClickCount > requiredLeftClicks || rightClickCount > requiredRightClicks) {
                            passFailObstacle.setFont(font3);
                            passFailObstacle.setText("CRASHED!");
                        }
                    }
                break;
                case "SwerveRight":
                    //System.out.println("right");
                    if (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2_1[obstacleTarget][2] == 1 && countDownToPassObstacleOn) { //if feature applicable to target and user in obstacle countdown
                        rightClickCount++;
                        passFailObstacle.setFont(font1);
                        passFailObstacle.setText("L: " + (requiredLeftClicks - leftClickCount) + " | R: " + (requiredRightClicks - rightClickCount));
                        System.out.println("valid right clicks is" + rightClickCount);
                        if (leftClickCount > requiredLeftClicks || rightClickCount > requiredRightClicks) {
                            passFailObstacle.setFont(font3);
                            passFailObstacle.setText("CRASHED!");
                        }
                    }
                break;
            }
        }
    }
    private void letsDrive() {
        double distanceToSteveAsDouble = distanceToSteve;
        DecimalFormat num = new DecimalFormat("0.00");
        num.setRoundingMode(RoundingMode.FLOOR);
        kmSteve = num.format(distanceToSteveAsDouble/1000);
        stage1 = false;
        stage2 = true;
        stage2Start = true;
        setStage2ElapsedTimer();
        generalElapsedCounter.start();
        setDelayPanelAfterObstacleTimer();
        distanceToGoTitleLabel.setText("Distance to Steve:");
        distanceToGoLabel.setText(kmSteve + "km");
        whatIsNextObstacle.setText("Next obstacle: " + obstacleType);
        distanceToNextObstacleTitleLabel.setText("Distance to obstacle:");
        distanceToNextObstacleLabel.setText(DISTANCE_TO_GARAGE_GATE + "m");
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

    private void beatStage2Part1SetupPart2() {
        stg2Part2 = true;
        nextObstDistance = FIRST_OBSTACLE_STG2_2;
        stage2ElapsedLabel.setText("Escape Aircraft Discovered in 10 min!");
        stage2ElapsedValue.setForeground(Color.white);
        stage2ElapsedValue.setText("0:00");
        clickCountLabel.setText("0m");
        distanceToGoTitleLabel.setForeground(Color.yellow);
        distanceToGoTitleLabel.setText("Distance to Escape Plane:");
        distanceToGoLabel.setText(DISTANCE_TO_ESCAPE_PLANE + "m");
        whatIsNextObstacle.setText("Next obstacle: Motorway Entry Road");
        distanceToNextObstacleTitleLabel.setText("Distance to obstacle:");
        distanceToNextObstacleLabel.setText("2500m");
        generalElapsedCounter.restart();
        generalElapsedCounter.start();
        moreThanOneMinuteElapsedFlag = false;
        generalTimerElapsedSeconds = 0;
        generalTimerElapsedMinutes = 0;
        generalTimerSecondsToDisplay = 0;
        leftClickCount = 0;
        rightClickCount = 0;
        clickCount = 0;

        System.out.println("You beat Stage 2 part 1 and met Steve!");
    }

    private void temporarilyLockPowerUpsForMechanicMiniGame(int toggle) {
        if (toggle == 1) {
            button1.setText(LOCKED);
            button2.setText(LOCKED);
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
                        messageText.setText("You arrived at the mechanic! Phew!\nSpend " + PRICE_TO_UNLOCK_MECHANIC + " + " + CLICKS_TO_FIX_CAR + " clicks to\nrepair your wheels!");
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