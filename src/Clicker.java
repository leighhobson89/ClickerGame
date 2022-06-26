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

    //-------------------------------------------------------CONSTANTS------------------------------------------------
    final String LOCKED = "LOCKED";
    final int DISTANCE_TO_SWITCH_KM_TO_M = 5000;
    final int DISTANCE_DISPLAY_OBSTACLE_BOARD_STAGE2 = 600;
    final int DISTANCE_DISPLAY_OBSTACLE_BOARD_STAGE3 = 1200;
    final int DISTANCE_ENTER_OBSTACLE_ELIGIBILITY = 10;
    final int DISTANCE_TO_TRIGGER_FAST_OBSTACLE_RANGE_COUNTDOWN_SPEED = 10;
    final int TIME_UNTIL_PENALTY_STAGE2 = 600;
    final int TIME_UNTIL_PENALTY_STAGE3 = 720;
    final int MAX_DISTANCE_TO_ADD_IF_STEVE_MOVES = 5000;
    final int MIN_DISTANCE_TO_ADD_IF_STEVE_MOVES = 2000;
    final double WITHIN_RANGE_OBST_DISTANCE_COUNT_SPEED = 0.1;
    final double FAST_OBSTACLE_WITHIN_RANGE_COUNTDOWN_SPEED = 0.01;
    final int DELAY_TO_REMOVE_OBST_PANEL_AFTER_OBSTACLE = 3;
    final int PRICE_TO_UNLOCK_TOW_TRUCK = 200;
    final int COST_INCREASE_PER_TOW_TRUCK = 50;
    final int TOW_TRUCK_ADDS_THIS_MANY_CLICKS_PER_SECOND = 10;
    final int PRICE_TO_UNLOCK_MECHANIC = 3500;
    final int PRICE_TO_UNLOCK_AUTOCLICKER = 10;
    final int COST_INCREASE_PER_AUTOCLICKER = 5;
    final double AUTOCLICKER_ADDS_THIS_MANY_CLICKS_PER_SECOND = 0.1;
    final int CLICKS_TO_FIX_CAR = 500;
    final int NUMBER_OF_OBSTACLES_IN_STEVE_ARRAY_MINUS_1 = 19;
    final int NUMBER_OF_OBSTACLES_IN_AIRPORT_DASH_ARRAY_MINUS_1 = 8;
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
    final int DISTANCE_TO_STEVE = 30;
    final int DISTANCE_TO_ESCAPE_PLANE = 5000;
    final int COST_OF_FAILURE_STAGE3_START_VALUE = 1500;
    final int COST_OF_FAILURE_FOR_HITTING_STEVE = 10000;
    final int MAX_NORMAL_SPEED_OF_CAR_MINUS_1 = 69;
    final int MAX_OVERDRIVE_SPEED_OF_CAR_MINUS_1 = 99;
    final int MAX_NO_OF_TURNING_CLICKS = 10;
    final int MIN_NO_OF_TURNING_CLICKS = 0;
    final int FIRST_OBSTACLE_STAGE3 = 2500;
    final int NITROUS_COUNT = 30;
    final int OVERDRIVE_TOGGLE_SPEED = 70;
    final int MAX_SPEED_BOOST_CAN_BE_USED = 320;
    final int BOOST_AMOUNT_DELIVERED = 50;
    final int MAX_NUMBER_OF_SIMULTANEOUS_NITROS = 5;
    final int BOOST_DURATION = 7;
    final int TIME_TO_WAIT_TO_ADD_NITROS = 60;
    final int NITROS_TO_ADD_WHEN_BUTTON_CLICKED = 20;

    //---------------------------------------------------------------------------------------------------------------

    String obstacleType, km, kmGoal;
    JLabel generalTimerElapsedValue, generalTimerElapsedLabel, metresTravelledLabel, clickCountLabel, perSecondLabelLabel, perSecondLabel, price, price1, price2, price3, price4, distanceToGoLabel, distanceToGoTitleLabel, whatIsNextObstacle, distanceToNextObstacleTitleLabel, distanceToNextObstacleLabel;
    JLabel obstacleConditionsTitle, obstacleConditions, timerObstacleTitle, timerObstacle, passFailObstacle, costOfFailure;
    JButton buttonAddNitros, button1, button2, button3, button4, button5;
    int stage, clickCount, timerSpeed, secondsElapsedDelayToRemoveObstaclePanel, autoClickerNumber, autoClickerPrice, towTruckNumber, towTruckPrice, mechanicPrice, button3ClickIteration, repairCounter, clicksLeftToFixCar, distanceToEndOfStageGoal;
    int randomSteveMovementModifier, driveFirstClickFlag, obstacleTarget, costOfFailureValue,timerObstacleValue, originalTimerObstacleValue, passObstacleFlag, rangePermitted, rangeActual, generalTimerElapsedSeconds, generalTimerSecondsToDisplay, generalTimerElapsedMinutes;
    int nitroBeingUsed, numberOfActiveNitros, nitrousBoostsRemainingCount, countDisplay, distanceToEndOfStageGoalAfterFail, requiredLeftClicks, requiredRightClicks, countTimesPassTimeUntilEndOStageGoal, leftClickCount, rightClickCount;
    int timeUntilAddNitroButtonDisappears, nitroRechargeValue, nitroTicks1, nitroTicks2, nitroTicks3, nitroTicks4, nitroTicks5, originalGeneralTimerValue1, originalGeneralTimerValue2, originalGeneralTimerValue3, originalGeneralTimerValue4, originalGeneralTimerValue5;
    double originalCPS, preNitroCPS1, preNitroCPS2, preNitroCPS3, preNitroCPS4, preNitroCPS5, clicksPerSecond, speedKmH, nextObstDistance;
    float repairCounterPercent;
    boolean BeginningOfADrivingStage, timerOn, delayObstaclePanelOn, autoClickerUnlocked, towTruckUnlocked, mechanicTriggeredYet, mechanicUnlocked, driveUnlocked, carInMechanic, accelerateClickedFlag, displayObstacleConditionsFlag, costOfFailureFirstIterationFlag;
    boolean failedObstacleWithinApproachOfEndOfStageGoal, approachingEndOfStageGoalFlag, checkTimeSteveMoveCount, startDelayTimerFlag, countDownToPassObstacleOn, startCountDownToPassObstacleFlag, wasPassingNowFailing, moreThanOneMinuteElapsedFlag;
    boolean gameOverFlag, timerNitroAddButtonDisappearFlag, nitroSpeedUp1, nitroSpeedUp2, nitroSpeedUp3, nitroSpeedUp4, nitroSpeedUp5, nitro1, nitro2, nitro3, nitro4, nitro5, degradeNitro1, degradeNitro2, degradeNitro3, degradeNitro4, degradeNitro5, overDrive, atStartEngineScreen;
    boolean[] nitrosArray = {false, false, false, false, false};
    Font font1, font2, font3;
    ClickHandler cHandler = new ClickHandler();
    Timer timer, delayPanelAfterObstacleTimer, countDownToPassObstacleTimer, generalElapsedCounter;
    JTextArea messageText;
    MouseHandler mHandler = new MouseHandler();
    Border raisedBorder = BorderFactory.createRaisedBevelBorder();
    String[] obstacleNameArrayStg2_1 = {"Garage Gate", "Sheep Crossing", "Police Checkpoint", "Level Crossing", "Tractor Up Ahead", "Dog Running in Road", "Drunk Man in Road", "Fallen Rocks", "Broken Water Pipe", "Kids Playing in Road",
            "Turning Lorry Up Ahead", "Bin Wagon Spilled Trash", "Fallen Tree", "Road Works Up Ahead", "Bicycle Up Ahead", "Mate Wants A Race", "Road Rager Chasing You", "Minimum Speed Limit", "Out Accelerate A Sports Car", "Out Accelerate A Hatchback", "Steve"};
    String[] obstacleNameArrayStg3 = {"Motorway Entry Road", "Slow Lorry Convoy", "Broken Down Van", "Pedestrian on Motorway", "Overpass Collapse", "Race a 1300cc Motorbike", "Jump The Ramp", "Max Power", "Steady She Goes", "Motorway Exit", "Airfield Entrance", "Escape Plane"};
    final Integer[][] SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2 = {{0, 0, 0}, {2, 8, 1}, {1, 2, 0}, {0, 0, 0}, {3, 6, 1}, {2, 9, 1}, {4, 8, 1}, {3, 10, 1}, {6, 12, 1}, {2, 4, 1}, {8, 11, 1}, {6, 15, 1}, {3, 14, 1}, {0, 1, 1}, {3, 15, 1}, {50, 60, 0}, {45, 50, 0}, {30, 70, 0}, {65, 70, 0}, {60, 65, 0}, {0, 0, 0}};
    final Integer[][] SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3 = {{50, 70}, {35, 45}, {20, 30}, {10, 15}, {5, 10}, {100, 150}, {150, 200}, {320, 350}, {80, 120}, {30, 40}, {5, 10}, {0, 0}};

    public static void main(String[] args) {
        new Clicker();
    }

    public Clicker() {
        setInitialVariables(false); //initial setup
        createFont();
        createUI();
    }
    @Override
    public void start(Stage stage) {}

    public void createFont(){
        font1 = new Font("Arial", Font.PLAIN, 32);
        font2 = new Font("Arial", Font.PLAIN, 15);
        font3 = new Font("Arial", Font.BOLD, 27);
    }
    public void createUI() {

        JFrame window = new JFrame();
        window.setSize(800, 600);
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

        JPanel addNitros = new JPanel();
        addNitros.setBounds(680,25,100,100);
        addNitros.setBackground(Color.black);
        addNitros.setLayout(new GridLayout(1,1));
        window.add(addNitros);

        buttonAddNitros = new JButton("NOS +" + NITROS_TO_ADD_WHEN_BUTTON_CLICKED);
        buttonAddNitros.setFont(font2);
        buttonAddNitros.setFocusPainted(false);
        buttonAddNitros.addActionListener(cHandler);
        buttonAddNitros.setActionCommand("AddNitros");
        buttonAddNitros.addMouseListener(mHandler);
        buttonAddNitros.setVisible(false);
        addNitros.add(buttonAddNitros);

        JPanel clickCounter = new JPanel();
        clickCounter.setBounds(100,50,320,150);
        clickCounter.setBackground(Color.black);
        clickCounter.setLayout(new GridLayout(6,1));
        window.add(clickCounter);

        generalTimerElapsedLabel = new JLabel("");
        generalTimerElapsedLabel.setForeground(Color.yellow);
        generalTimerElapsedLabel.setFont(font2);
        clickCounter.add(generalTimerElapsedLabel);

        generalTimerElapsedValue = new JLabel("");
        generalTimerElapsedValue.setForeground(Color.white);
        generalTimerElapsedValue.setFont(font1);
        clickCounter.add(generalTimerElapsedValue);

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
        itemPanel.setBounds(500,150,280,250);
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
        button4.addMouseListener(mHandler);
        itemPanel.add(button4);

//        button5 = new JButton("RestartGame");
//        button5.setFont(font1);
//        button5.setFocusPainted(false);
//        button5.addActionListener(cHandler);
//        button5.setActionCommand("RestartGame");
//        button5.addMouseListener(mHandler);
//        itemPanel.add(button5);

        JPanel messagePanel = new JPanel();
        messagePanel.setBounds(530,25,140,150);
        messagePanel.setBackground(Color.black);
        window.add(messagePanel);

        messageText = new JTextArea();
        messageText.setBounds(530, 25, 140, 150);
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

    public void setGeneralTimer() {
        generalElapsedCounter = new Timer(1000, e -> {
            generalTimerElapsedSeconds++;
            generalTimerSecondsToDisplay++;
            if (stage == 3 && generalTimerElapsedSeconds - nitroRechargeValue == TIME_TO_WAIT_TO_ADD_NITROS) {
                buttonAddNitros.setVisible(true);
            }
            if (timerNitroAddButtonDisappearFlag && generalTimerElapsedSeconds - timeUntilAddNitroButtonDisappears == 1) {
                buttonAddNitros.setVisible(false);
                timerNitroAddButtonDisappearFlag = false;
                messageText.setText(null);
            }
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
                if (!approachingEndOfStageGoalFlag) {
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
            if ((nitroSpeedUp1 || nitroSpeedUp2 || nitroSpeedUp3 || nitroSpeedUp4 || nitroSpeedUp5) && stage == 3) {
                timerUpdate(nitroBeingUsed);
            }
            if(stage == 3 && nitro1 && !degradeNitro1 && (generalTimerElapsedSeconds - originalGeneralTimerValue1 >= BOOST_DURATION)) {
                System.out.println("time to degrade nitro1 down");
                degradeNitro1 = true;
                timerUpdate(nitroBeingUsed);
            }
            if(stage == 3 && nitro2 && !degradeNitro2 && (generalTimerElapsedSeconds - originalGeneralTimerValue2 >= BOOST_DURATION)) {
                System.out.println("time to degrade nitro2 down");
                degradeNitro2 = true;
                timerUpdate(nitroBeingUsed);
            }
            if(stage == 3 && nitro3 && !degradeNitro3 && (generalTimerElapsedSeconds - originalGeneralTimerValue3 >= BOOST_DURATION)) {
                System.out.println("time to degrade nitro3 down");
                degradeNitro3 = true;
                timerUpdate(nitroBeingUsed);
            }
            if(stage == 3 && nitro4 && !degradeNitro4 && (generalTimerElapsedSeconds - originalGeneralTimerValue4 >= BOOST_DURATION)) {
                System.out.println("time to degrade nitro4 down");
                degradeNitro4 = true;
                timerUpdate(nitroBeingUsed);
            }
            if(stage == 3 && nitro5 && !degradeNitro5 && (generalTimerElapsedSeconds - originalGeneralTimerValue5 >= BOOST_DURATION)) {
                System.out.println("time to degrade nitro5 down");
                degradeNitro5 = true;
                timerUpdate(nitroBeingUsed);
            }

            if (stage == 3 && nitro1) {
                if (nitroTicks1 >= BOOST_AMOUNT_DELIVERED && degradeNitro1) {
                    timerUpdate(nitroBeingUsed);
                }
                if (nitroTicks1 < BOOST_AMOUNT_DELIVERED && degradeNitro1) {
                    timerUpdate(nitroBeingUsed);
                }
            }
            if (stage == 3 && nitro2) {
                if (nitroTicks2 >= BOOST_AMOUNT_DELIVERED && degradeNitro2) {
                    timerUpdate(nitroBeingUsed);
                }
                if (nitroTicks2 < BOOST_AMOUNT_DELIVERED && degradeNitro2) {
                    timerUpdate(nitroBeingUsed);
                }
            }
            if (stage == 3 && nitro3) {
                if (nitroTicks3 >= BOOST_AMOUNT_DELIVERED && degradeNitro3) {
                    timerUpdate(nitroBeingUsed);
                }
                if (nitroTicks3 < BOOST_AMOUNT_DELIVERED && degradeNitro3) {
                    timerUpdate(nitroBeingUsed);
                }
            }
            if (stage == 3 && nitro4) {
                if (nitroTicks4 >= BOOST_AMOUNT_DELIVERED && degradeNitro4) {
                    timerUpdate(nitroBeingUsed);
                }
                if (nitroTicks4 < BOOST_AMOUNT_DELIVERED && degradeNitro4) {
                    timerUpdate(nitroBeingUsed);
                }
            }
            if (stage == 3 && nitro5) {
                if (nitroTicks5 >= BOOST_AMOUNT_DELIVERED && degradeNitro5) {
                    timerUpdate(nitroBeingUsed);
                }
                if (nitroTicks5 < BOOST_AMOUNT_DELIVERED && degradeNitro5) {
                    timerUpdate(nitroBeingUsed);
                }
            }
            if (stage == 2 || stage == 3) {
                if (overDrive && clicksPerSecond < OVERDRIVE_TOGGLE_SPEED && stage == 3) {
                    overDrive = false;
                    button3.setForeground(Color.black);
                    button3.setText("Overdrive: OFF");
                }
                distanceToEndOfStageGoal--;
                double cCountAsDouble = clickCount;
                double distanceToEndOfStageGoalAsDouble = distanceToEndOfStageGoal;
                DecimalFormat num = new DecimalFormat("0.00");
                num.setRoundingMode(RoundingMode.FLOOR);
                km = num.format(cCountAsDouble/1000);
                kmGoal = num.format(distanceToEndOfStageGoalAsDouble/1000);
                if (clickCount > DISTANCE_TO_SWITCH_KM_TO_M) {
                    clickCountLabel.setText(km + "km");
                } else {
                    clickCountLabel.setText(clickCount + "m");
                }
                if (distanceToEndOfStageGoal >= DISTANCE_TO_SWITCH_KM_TO_M) {
                    distanceToGoLabel.setText(kmGoal + "km");
                } else {
                    distanceToGoLabel.setText(distanceToEndOfStageGoal + "m");
                }
                if (!approachingEndOfStageGoalFlag) {
                    if (stage == 2) {
                        generalTimerElapsedLabel.setText("Steve Moves Every " + (TIME_UNTIL_PENALTY_STAGE2 / 60) + " Minutes:");
                    } else if (stage == 3) {
                        generalTimerElapsedLabel.setText("Escape Plane Leaves In " + (TIME_UNTIL_PENALTY_STAGE3 / 60) + " min!");
                    }
                }
                if (generalTimerElapsedSeconds >= 60 && generalTimerSecondsToDisplay >= 60) {
                    generalTimerElapsedMinutes++;
                    generalTimerSecondsToDisplay = 0;
                    moreThanOneMinuteElapsedFlag = true;
                }
                if (!moreThanOneMinuteElapsedFlag && generalTimerElapsedSeconds < 60) {
                    if (generalTimerElapsedSeconds < 10 && !approachingEndOfStageGoalFlag) {
                        generalTimerElapsedValue.setText("0:0" + generalTimerSecondsToDisplay);
                    } else {
                        if(!approachingEndOfStageGoalFlag) {
                            generalTimerElapsedValue.setText("0:" + generalTimerSecondsToDisplay);
                        }
                    }
                } else {
                    if (generalTimerSecondsToDisplay < 10 && !approachingEndOfStageGoalFlag) {
                        generalTimerElapsedValue.setText(generalTimerElapsedMinutes + ":0" + generalTimerSecondsToDisplay);
                    } else {
                        if (!approachingEndOfStageGoalFlag) {
                            generalTimerElapsedValue.setText(generalTimerElapsedMinutes + ":" + generalTimerSecondsToDisplay);
                        }
                    }
                }
                if (stage == 2) {
                    if (generalTimerElapsedSeconds % TIME_UNTIL_PENALTY_STAGE2 == 0 && !checkTimeSteveMoveCount) {
                        checkTimeSteveMoveCount = true;
                        randomSteveMovementModifier = (int) ((Math.random() * (MAX_DISTANCE_TO_ADD_IF_STEVE_MOVES - MIN_DISTANCE_TO_ADD_IF_STEVE_MOVES)) + MIN_DISTANCE_TO_ADD_IF_STEVE_MOVES);
                        distanceToEndOfStageGoal = distanceToEndOfStageGoal + randomSteveMovementModifier;
                        if (distanceToEndOfStageGoal > DISTANCE_TO_SWITCH_KM_TO_M) {
                            distanceToGoLabel.setText(kmGoal + "km");
                        } else {
                            distanceToGoLabel.setText(distanceToEndOfStageGoal + "m");
                        }
                    }
                    if (generalTimerElapsedSeconds >= (TIME_UNTIL_PENALTY_STAGE2 * countTimesPassTimeUntilEndOStageGoal) - 30 && generalTimerElapsedSeconds < (TIME_UNTIL_PENALTY_STAGE2 * countTimesPassTimeUntilEndOStageGoal)) {
                        generalTimerElapsedValue.setForeground(Color.red);
                        checkTimeSteveMoveCount = false;
                    } else if (generalTimerElapsedSeconds >= TIME_UNTIL_PENALTY_STAGE2 * countTimesPassTimeUntilEndOStageGoal && !approachingEndOfStageGoalFlag) {
                        generalTimerElapsedValue.setForeground(Color.white);
                        generalTimerElapsedValue.setText("0:00");
                        countTimesPassTimeUntilEndOStageGoal++;
                        generalTimerElapsedMinutes = 0;
                        generalTimerSecondsToDisplay = 0;
                    }
                } else if (stage == 3 && generalTimerElapsedSeconds >= TIME_UNTIL_PENALTY_STAGE3) {
                    gameOver();
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
                if (!approachingEndOfStageGoalFlag) {
                    distanceToNextObstacleLabel.setText(((int) nextObstDistance) + "m");
                }
                if(stage == 2 && nextObstDistance < DISTANCE_DISPLAY_OBSTACLE_BOARD_STAGE2 && nextObstDistance >= 3) {
                    displayObstacleConditionsFlag = true;
                    countDisplay++;
                    displayObstaclePassConditions(countDisplay);
                } else if (stage == 3 && nextObstDistance < DISTANCE_DISPLAY_OBSTACLE_BOARD_STAGE3 && nextObstDistance >= 3) {
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
            else if (stage == 1){
                clickCountLabel.setText(clickCount + "m");
            }

            if(!towTruckUnlocked && clickCount >= PRICE_TO_UNLOCK_TOW_TRUCK && stage == 1) {
                towTruckUnlocked = true;
                button2.setText("Tow Truck");
            }
            if(clickCount >= PRICE_TO_UNLOCK_MECHANIC && stage == 1) {
                clickCount = PRICE_TO_UNLOCK_MECHANIC;
                timer.stop();
                perSecondLabelLabel.setText("");
                perSecondLabel.setText("");
                price4.setText(CLICKS_TO_FIX_CAR + "m");
                temporarilyLockPowerUpsForMechanicMiniGame(1);
            }
        });
    }

    private void setupNextObstacle(int passObstacleFlag, int distanceToEndOfStageGoalAfterFailure) {
        if (passObstacleFlag == 2) {
            if (failedObstacleWithinApproachOfEndOfStageGoal) {
                endOfStageGoalApproach(distanceToEndOfStageGoalAfterFailure);
            }
            wasPassingNowFailing = false;
            clicksPerSecond = 0;
            perSecondLabel.setText(clicksPerSecond + "m/s");
            timerUpdate(nitroBeingUsed);
        }
        if (distanceToEndOfStageGoal > 3000 && stage == 3){
            int value = (int) (Math.random() * NUMBER_OF_OBSTACLES_IN_AIRPORT_DASH_ARRAY_MINUS_1) + 1; //pick a new obstacle
            obstacleType = obstacleNameArrayStg3[value];
            obstacleTarget = value;
            countDownToPassObstacleOn = false;
            whatIsNextObstacle.setText("");
            nextObstDistance = (int) ((Math.random() * (MAX_NEW_OBSTACLE_DISTANCE_STG2_2 - MIN_NEW_OBSTACLE_DISTANCE_STG2_2)) + MIN_NEW_OBSTACLE_DISTANCE_STG2_2); //distance of next obstacle
            if (!approachingEndOfStageGoalFlag) {
                distanceToNextObstacleLabel.setText((int) nextObstDistance + "m");
            }
            if ((value == 5 || value == 6 || value == 7 || value == 8) && ((nitroSpeedUp1 || nitro1) || (nitroSpeedUp2 || nitro2) || (nitroSpeedUp3 || nitro3) || (nitroSpeedUp4 || nitro4) || (nitroSpeedUp5 || nitro5))) { //if an obstacle that can or must be attained with nitros
                originalTimerObstacleValue = 1;
                timerObstacleValue = originalTimerObstacleValue;
            } else {
                originalTimerObstacleValue = (int) ((Math.random() * (MAX_NEW_TIMER_AMOUNT - MIN_NEW_TIMER_AMOUNT)) + MIN_NEW_TIMER_AMOUNT);
                timerObstacleValue = originalTimerObstacleValue;
            }
            costOfFailureValue = (int) (Math.random() * (clickCount/100 * COST_OF_FAILURE_FURTHER_ITERATIONS_FACTOR1)) + (clickCount/100 * COST_OF_FAILURE_FURTHER_ITERATIONS_FACTOR2);
        } else {
            endOfStageGoalApproach(distanceToEndOfStageGoalAfterFailure);
        }
        if (distanceToEndOfStageGoal > 2500 && stage == 2) {
            requiredLeftClicks = (int) ((Math.random() * (MAX_NO_OF_TURNING_CLICKS - MIN_NO_OF_TURNING_CLICKS)) + MIN_NO_OF_TURNING_CLICKS);
            if (requiredLeftClicks == 0) { //remove possibility of L:0 | R:0 random generation
                requiredRightClicks = (int) ((Math.random() * (MAX_NO_OF_TURNING_CLICKS - MIN_NO_OF_TURNING_CLICKS - 1)) + MIN_NO_OF_TURNING_CLICKS + 1);
            } else {
                requiredRightClicks = (int) ((Math.random() * (MAX_NO_OF_TURNING_CLICKS - MIN_NO_OF_TURNING_CLICKS)) + MIN_NO_OF_TURNING_CLICKS);
            }
            int value = (int) (Math.random() * NUMBER_OF_OBSTACLES_IN_STEVE_ARRAY_MINUS_1) + 1; //pick a new obstacle
            obstacleType = obstacleNameArrayStg2_1[value];
            obstacleTarget = value;
            countDownToPassObstacleOn = false;
            whatIsNextObstacle.setText("");
            nextObstDistance = (int) ((Math.random() * (MAX_NEW_OBSTACLE_DISTANCE_STG2_1 - MIN_NEW_OBSTACLE_DISTANCE_STG2_1)) + MIN_NEW_OBSTACLE_DISTANCE_STG2_1); //distance of next obstacle
            if (!approachingEndOfStageGoalFlag) {
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
            endOfStageGoalApproach(distanceToEndOfStageGoalAfterFailure);
        }
    }

    private void endOfStageGoalApproach(int distanceToEndOfStageGoalAfterFailure) {
        if ((stage == 2 && failedObstacleWithinApproachOfEndOfStageGoal && distanceToEndOfStageGoalAfterFailure < 2500) || (stage == 3 && failedObstacleWithinApproachOfEndOfStageGoal && distanceToEndOfStageGoalAfterFailure < 3000) || (stage == 3 && distanceToEndOfStageGoal < 3000) || (stage == 2 && distanceToEndOfStageGoal < 2500)) {
            approachingEndOfStageGoalFlag = true;
            generalElapsedCounter.stop();
            if (stage == 2) {
                generalTimerElapsedLabel.setText("Steve will wait for you!");
                costOfFailureValue = COST_OF_FAILURE_FOR_HITTING_STEVE;
                obstacleType = obstacleNameArrayStg2_1[20];
                obstacleTarget = 20;
                distanceToGoTitleLabel.setText("STOP WITHIN 10m OF STEVE!");
            } else if (stage == 3) {
                generalTimerElapsedLabel.setText("The escape plane is ready!");
                costOfFailureValue = clickCount;
                obstacleType = obstacleNameArrayStg3[11];
                obstacleTarget = 11;
                distanceToGoTitleLabel.setText("STOP WITHIN 10m OF THE PLANE!");
            }
            generalTimerElapsedValue.setForeground(Color.green);
            generalTimerElapsedValue.setText("WAITING");
            countDownToPassObstacleOn = false;
            whatIsNextObstacle.setText("");
            nextObstDistance = distanceToEndOfStageGoal; //distance of next obstacle
            distanceToNextObstacleTitleLabel.setText("");
            distanceToNextObstacleLabel.setText("");
            originalTimerObstacleValue = 3;
            timerObstacleValue = originalTimerObstacleValue;
            distanceToGoTitleLabel.setForeground(Color.red);
        }
    }

    private void displayObstaclePassConditions(int count) {
        if (count == 1) { //bug fix for when passing and no button clicks allowing passing of next obstacle
            button2.doClick();
            button1.doClick();
            Integer mS = ((int) clicksPerSecond);
            speedKmH = clicksPerSecond * 3.6;
            String kmHr = String.format("%.1f", speedKmH);
            perSecondLabel.setText(mS + "m/s (" + kmHr + "km/hr)");
        } //end of bug fix
        if (costOfFailureValue == 0) { //if leaving garage and Garage Gate
            costOfFailureFirstIterationFlag = true;
            costOfFailureValue = DISTANCE_TO_GARAGE_GATE; //lose all clicks if fail first obstacle
        }
        obstacleConditionsTitle.setText("Speed Range:");
        if (stage == 2) {
            obstacleConditions.setText(SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][0] + " - " + SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][1] + "m/s");
            if (leftClickCount == 0 && rightClickCount == 0 && SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][2] == 1) {
                passFailObstacle.setForeground(Color.yellow);
                passFailObstacle.setFont(font1);
                passFailObstacle.setText("L: " + requiredLeftClicks + " | R: " + requiredRightClicks);
            }
            if (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][2] == 0) {
                passFailObstacle.setForeground(Color.yellow);
                passFailObstacle.setFont(font2);
                passFailObstacle.setText("NO TURNING REQUIRED");
            }
        } else if (stage == 3) {
            obstacleConditions.setText(SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][0] + " - " + SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][1] + "m/s");
        }
        timerObstacleTitle.setText("Time to hold Speed:");
        timerObstacle.setText(timerObstacleValue + "s");
        costOfFailure.setText("Cost of Failure: " + costOfFailureValue + "m");
        whatIsNextObstacle.setText("Next obstacle: " + obstacleType);
    }

    public void obstaclePassed() {
        leftClickCount = 0;
        rightClickCount = 0;
        passFailObstacle.setForeground(Color.green);
        passFailObstacle.setFont(font3);
        if (!approachingEndOfStageGoalFlag) {
            passFailObstacle.setText("PASS");
        } else {
            if (stage == 2) {
                passFailObstacle.setText("YOU MET STEVE!!");
            } else if (stage == 3) {
                passFailObstacle.setText("YOU ESCAPED!!");
                generalTimerElapsedValue.setText("HERE'S TO LIFE!!");
            }
        }
        startDelayTimerFlag = true;
        delayPanelAfterObstacleTimerUpdate();
        setupNextObstacle(passObstacleFlag, distanceToEndOfStageGoalAfterFail);
        passObstacleFlag = 0;
        startCountDownToPassObstacleFlag = false;
        countDownToPassObstacleTimer.stop();
        if (approachingEndOfStageGoalFlag && distanceToEndOfStageGoal < 10) {
            if (stage == 2) {
                temporarilyLockPowerUpsForMechanicMiniGame(2);
            } else if (stage == 3) {
                beatStage3SetupStage4();
            }
        }
    }

    public void obstacleFailed() {
        if (stage == 3) {
            resetNitro();
        }
        originalCPS = 0;
        leftClickCount = 0;
        rightClickCount = 0;
        passFailObstacle.setForeground(Color.red);
        passFailObstacle.setFont(font3);
        if (!approachingEndOfStageGoalFlag) {
            passFailObstacle.setText("FAIL");
            distanceToEndOfStageGoalAfterFail = distanceToEndOfStageGoal;
            if (distanceToEndOfStageGoal < 2500 && stage == 2 || distanceToEndOfStageGoal < 3000 && stage == 3) {
                failedObstacleWithinApproachOfEndOfStageGoal = true;
            }
        } else {
            if (stage == 2) {
                passFailObstacle.setText("YOU HIT STEVE!!");
                gameOver();
            } else if (stage == 3) {
                passFailObstacle.setText("PLANE DESTROYED!!");
                gameOver();
            }
            if (!gameOverFlag) {
                failedObstacleWithinApproachOfEndOfStageGoal = false;
                unsetEndOfStageGoalApproach();
            }
        }
        if (!gameOverFlag) {
            startDelayTimerFlag = true;
            delayPanelAfterObstacleTimerUpdate();
            if (clickCount >= costOfFailureValue) { //subtract clicks for failing
                clickCount = clickCount - costOfFailureValue;
            } else { // set clicks to zero if garage gate
                clickCount = 0;
            }
            distanceToEndOfStageGoal = distanceToEndOfStageGoal + costOfFailureValue;
            if (costOfFailureFirstIterationFlag) {
                costOfFailureFirstIterationFlag = false;
            } else {
                countDownToPassObstacleTimer.stop();
            }
            countDownToPassObstacleOn = false;
            setupNextObstacle(passObstacleFlag, distanceToEndOfStageGoalAfterFail);
            passObstacleFlag = 0;
            startCountDownToPassObstacleFlag = false;
            if (approachingEndOfStageGoalFlag) { //fail actually on steve
                if (!failedObstacleWithinApproachOfEndOfStageGoal) {
                    unsetEndOfStageGoalApproach();
                }
            } else if (failedObstacleWithinApproachOfEndOfStageGoal) { //fail previous to steve but within range
                endOfStageGoalApproach(distanceToEndOfStageGoalAfterFail);
            }
        }
    }

    private void unsetEndOfStageGoalApproach() {
        approachingEndOfStageGoalFlag = false;
        generalTimerElapsedLabel.setForeground(Color.yellow);
        if (stage == 2) {
            generalTimerElapsedLabel.setText("Steve Moves Every " + (TIME_UNTIL_PENALTY_STAGE2 / 60) + " Minutes:");
            distanceToGoTitleLabel.setText("Distance to Steve:");
        } else if (stage == 3) {
            generalTimerElapsedLabel.setText("Escape Aircraft Leaves In 10 min!");
            distanceToGoTitleLabel.setText("Distance to Escape Plane:");
        }
        distanceToGoTitleLabel.setForeground(Color.yellow);
        distanceToNextObstacleTitleLabel.setText("Distance to obstacle:");
        generalTimerElapsedValue.setForeground(Color.white);
        generalElapsedCounter.start();
        if (!moreThanOneMinuteElapsedFlag && generalTimerElapsedSeconds < 60) {
            if (generalTimerElapsedSeconds < 10) {
                generalTimerElapsedValue.setText("0:0" + generalTimerSecondsToDisplay);
            } else {
                generalTimerElapsedValue.setText("0:" + generalTimerSecondsToDisplay);
            }
        } else {
            if (generalTimerSecondsToDisplay < 10) {
                generalTimerElapsedValue.setText(generalTimerElapsedMinutes + ":0" + generalTimerSecondsToDisplay);
            } else {
                generalTimerElapsedValue.setText(generalTimerElapsedMinutes + ":" + generalTimerSecondsToDisplay);
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
        if (stage == 2) {
            if(passObstacleFlag == 1 && SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][2] == 1 && requiredLeftClicks == leftClickCount && requiredRightClicks == rightClickCount) {
                countDownToPassObstacleTimer.stop();
                obstaclePassed();
            }
            if(passObstacleFlag == 1 && SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][2] == 0) {
                countDownToPassObstacleTimer.stop();
                obstaclePassed();
            }
            if(passObstacleFlag == 1 && SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][2] == 1 && (requiredLeftClicks != leftClickCount || requiredRightClicks != rightClickCount)) {
                passObstacleFlag = 2;
                countDownToPassObstacleTimer.stop();
                obstacleFailed();
            }
        } else if (stage == 3) {
            if(passObstacleFlag == 1) {
                countDownToPassObstacleTimer.stop();
                obstaclePassed();
            }
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
    public void timerUpdate(int nitroBeingUsed) {
        if (!timerOn && clickCount < PRICE_TO_UNLOCK_MECHANIC && !carInMechanic) { //WATCH FOR ISSUES HERE ABOVE 3500M STAGE 2
            timerOn = true;
        } else if (timerOn) {
            timer.stop();
        }

        if (nitroBeingUsed == 1 && nitroSpeedUp1 && stage == 3 && !degradeNitro1) {
            timerSpeed = 5;
            if (nitroTicks1 >= BOOST_AMOUNT_DELIVERED) {
                nitroSpeedUp1 = false;
                nitro1 = true;
                originalGeneralTimerValue1 = generalTimerElapsedSeconds;
                preNitroCPS1 = clicksPerSecond;
                nitroTicks1 = 0;
            }
        }
        if (nitroBeingUsed == 2 && nitroSpeedUp2 && stage == 3 && !degradeNitro2) {
            timerSpeed = 5;
            if (nitroTicks2 >= BOOST_AMOUNT_DELIVERED) {
                nitroSpeedUp2 = false;
                nitro2 = true;
                originalGeneralTimerValue2 = generalTimerElapsedSeconds;
                preNitroCPS2 = clicksPerSecond;
                nitroTicks2 = 0;
            }
        }
        if (nitroBeingUsed == 3 && nitroSpeedUp3 && stage == 3 && !degradeNitro3) {
            timerSpeed = 5;
            if (nitroTicks3 >= BOOST_AMOUNT_DELIVERED) {
                nitroSpeedUp3 = false;
                nitro3 = true;
                originalGeneralTimerValue3 = generalTimerElapsedSeconds;
                preNitroCPS3 = clicksPerSecond;
                nitroTicks3 = 0;
            }
        }
        if (nitroBeingUsed == 4 && nitroSpeedUp4 && stage == 3 && !degradeNitro4) {
            timerSpeed = 5;
            if (nitroTicks4 >= BOOST_AMOUNT_DELIVERED) {
                nitroSpeedUp4 = false;
                nitro4 = true;
                originalGeneralTimerValue4 = generalTimerElapsedSeconds;
                preNitroCPS4 = clicksPerSecond;
                nitroTicks4 = 0;
            }
        }
        if (nitroBeingUsed == 5 && nitroSpeedUp5 && stage == 3 && !degradeNitro5) {
            timerSpeed = 5;
            if (nitroTicks5 >= BOOST_AMOUNT_DELIVERED) {
                nitroSpeedUp5 = false;
                nitro5 = true;
                originalGeneralTimerValue5 = generalTimerElapsedSeconds;
                preNitroCPS5 = clicksPerSecond;
                nitroTicks5 = 0;
            }
        }

        if (nitroSpeedUp1) {
                clicksPerSecond++;
                nitroTicks1++;
        }
        if (nitroSpeedUp2) {
            clicksPerSecond++;
            nitroTicks2++;
        }
        if (nitroSpeedUp3) {
            clicksPerSecond++;
            nitroTicks3++;
        }
        if (nitroSpeedUp4) {
            clicksPerSecond++;
            nitroTicks4++;
        }
        if (nitroSpeedUp5) {
            clicksPerSecond++;
            nitroTicks5++;
        }

        if (stage == 2) {
            rangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][0];
            rangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][0]);
        } else if (stage == 3) {
            rangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][0];
            rangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][0]);
        }
        if (stage != 1 && countDownToPassObstacleOn && nextObstDistance > 0 && (rangeActual > rangePermitted || rangeActual < 0)) {
            wasPassingNowFailing = true;
        }
        if (stage == 1) {
            String s = String.format("%.1f", clicksPerSecond);
            perSecondLabelLabel.setText("Metres of help per second:");
            perSecondLabel.setText(s);
            double speed = 1 / clicksPerSecond * 1000;
            timerSpeed = (int) Math.round(speed);
        } else if (stage == 2 || stage == 3) {
            if (degradeNitro1 && stage == 3 && nitroBeingUsed != 0) {
                if (nitroTicks1 >= BOOST_AMOUNT_DELIVERED) {
                    nitroBeingUsed = 1;
                    degradeNitro1 = false;
                    nitro1 = false;
                    numberOfActiveNitros--;
                    nitrosArray[nitroBeingUsed-1] = false;
                    nitroTicks1 = 0;
                }
                if (degradeNitro1) {
                    timerSpeed = 10;
                    nitroTicks1++;
                    clicksPerSecond--;
                }
            }
            if (degradeNitro2 && stage == 3 && nitroBeingUsed != 0) {
                if (nitroTicks2 >= BOOST_AMOUNT_DELIVERED) {
                    nitroBeingUsed = 2;
                    degradeNitro2 = false;
                    nitro2 = false;
                    numberOfActiveNitros--;
                    nitrosArray[nitroBeingUsed-1] = false;
                    nitroTicks2 = 0;
                }
                if (degradeNitro2) {
                    timerSpeed = 10;
                    nitroTicks2++;
                    clicksPerSecond--;
                }
            }
            if (degradeNitro3 && stage == 3 && nitroBeingUsed != 0) {
                if (nitroTicks3 >= BOOST_AMOUNT_DELIVERED) {
                    nitroBeingUsed = 3;
                    degradeNitro3 = false;
                    nitro3 = false;
                    numberOfActiveNitros--;
                    nitrosArray[nitroBeingUsed-1] = false;
                    nitroTicks3 = 0;
                }
                if (degradeNitro3) {
                    timerSpeed = 10;
                    nitroTicks3++;
                    clicksPerSecond--;
                }
            }
            if (degradeNitro4 && stage == 3 && nitroBeingUsed != 0) {
                if (nitroTicks4 >= BOOST_AMOUNT_DELIVERED) {
                    nitroBeingUsed = 4;
                    degradeNitro4 = false;
                    nitro4 = false;
                    numberOfActiveNitros--;
                    nitrosArray[nitroBeingUsed-1] = false;
                    nitroTicks4 = 0;
                }
                if (degradeNitro4) {
                    timerSpeed = 10;
                    nitroTicks4++;
                    clicksPerSecond--;
                }
            }
            if (degradeNitro5 && stage == 3 && nitroBeingUsed != 0) {
                if (nitroTicks5 >= BOOST_AMOUNT_DELIVERED) {
                    nitroBeingUsed = 5;
                    degradeNitro5 = false;
                    nitro5 = false;
                    numberOfActiveNitros--;
                    nitrosArray[nitroBeingUsed-1] = false;
                    nitroTicks5 = 0;
                }
                if (degradeNitro5) {
                    timerSpeed = 10;
                    nitroTicks5++;
                    clicksPerSecond--;
                }
            }
            if (nitroTicks1 == 0 && nitroTicks2 == 0 && nitroTicks3 == 0 && nitroTicks4 == 0 && nitroTicks5 == 0 && clicksPerSecond > 0 && !degradeNitro1) {
                double speed = 1 / clicksPerSecond * 1000;
                timerSpeed = (int) Math.round(speed);
            }
            Integer mS = ((int) clicksPerSecond);
            speedKmH = clicksPerSecond * 3.6;
            String kmHr = String.format("%.1f", speedKmH);
            perSecondLabelLabel.setText("Speed:");
            perSecondLabel.setText(mS + "m/s (" + kmHr + "km/hr)");
        }

        if((!mechanicUnlocked && stage == 1 && clickCount < PRICE_TO_UNLOCK_MECHANIC) || ((stage == 2 || stage == 3) && clicksPerSecond > 0)) {
            setTimer();
            timer.start();
        }
    }
    public class ClickHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            String action = event.getActionCommand();

            switch(action) {
                case "pushCar":
                if (!mechanicUnlocked && clickCount < PRICE_TO_UNLOCK_MECHANIC && stage == 1) {
                    clickCount = clickCount + 1750; //replace with clickCount++ for real, or clickCount = clickCount + 1750 for debug
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
                else if (stage == 1 && clickCount > PRICE_TO_UNLOCK_MECHANIC) {
                    clickCount=PRICE_TO_UNLOCK_MECHANIC;
                    clickCountLabel.setText(clickCount+"m");
                    timer.stop();
                    temporarilyLockPowerUpsForMechanicMiniGame(1); //lock/unlock power ups while at mechanic
                }
                else {
                    if (stage == 1){
                    clickCount = PRICE_TO_UNLOCK_MECHANIC;
                    clickCountLabel.setText(clickCount+"m");
                    price4.setText(clicksLeftToFixCar + "m");
                    temporarilyLockPowerUpsForMechanicMiniGame(1);
                    }
                }
                 //lock/unlock power ups while at mechanic
                if (BeginningOfADrivingStage) {
                        clickCount=0;
                        clickCountLabel.setText("0m");
                }
                break;
                case "Hired Help":
                    if(clickCount >= autoClickerPrice && !mechanicUnlocked && stage == 1){
                        clickCount = clickCount-autoClickerPrice;
                        autoClickerPrice = autoClickerPrice + COST_INCREASE_PER_AUTOCLICKER;
                        clickCountLabel.setText(clickCount+"m");
                        price1.setText("" + autoClickerPrice + "m");
                        autoClickerNumber++;
                        button1.setText("Hired Help (" + autoClickerNumber + ")");
                        messageText.setText("Hired Help!\n[Price: " + autoClickerPrice + "]\nExtra manpower to push \nevery 10 seconds!");
                        clicksPerSecond = clicksPerSecond + AUTOCLICKER_ADDS_THIS_MANY_CLICKS_PER_SECOND;
                        timerUpdate(nitroBeingUsed);
                    }
                    else {
                        if (!mechanicTriggeredYet && stage == 1) {
                            messageText.setText("\n\nYou need " + (autoClickerPrice-clickCount) + " more metres\nto buy this upgrade!");
                        }
                        else {
                            messageText.setText("Already arrived at garage,\nno more hired help required");
                        }
                    }
                break;
                case "Tow Truck":
                    if(clickCount >= towTruckPrice && !mechanicUnlocked && stage == 1){
                        clickCount = clickCount-towTruckPrice;
                        towTruckPrice = towTruckPrice + COST_INCREASE_PER_TOW_TRUCK;
                        clickCountLabel.setText(clickCount+"m");
                        towTruckNumber++;
                        button2.setText("Tow Truck (" + towTruckNumber + ")");
                        price2.setText(towTruckPrice + "m");
                        messageText.setText("Call a Tow Truck!\n[Price: " + towTruckPrice + "]\nTow Truck will move you much\nfaster than a pair of hands!");
                        clicksPerSecond = clicksPerSecond + TOW_TRUCK_ADDS_THIS_MANY_CLICKS_PER_SECOND;
                        timerUpdate(nitroBeingUsed);
                    }
                    else {
                        if (!mechanicTriggeredYet && stage == 1) {
                        messageText.setText("\n\nYou need " + (towTruckPrice-clickCount) + " more metres\nto buy this upgrade!");
                        }
                        else {
                            messageText.setText("Already arrived at garage,\nno more tow trucks required");
                        }
                    }
                break;
                case "Mechanic":
                    if(clickCount == mechanicPrice && stage == 1){
                        if (button3ClickIteration == 0) {
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
                        repairCounterPercent = 100; //DEBUG LINE - repairCounterPercent = 100; for debug delete for normal
                        if ((int)repairCounterPercent == 100) {
                            stage = 0;
                            carInMechanic = false;
                            clickCount=0;
                            button3.setText("Car Fixed!");
                            BeginningOfADrivingStage = true;
                            clickCountLabel.setText("0m");
                            temporarilyLockPowerUpsForMechanicMiniGame(2);
                        }
                        button3ClickIteration++;
                    }
                    else {
                        if (!mechanicTriggeredYet && stage == 1) {
                            messageText.setText("\n\nYou need " + (mechanicPrice - clickCount) + " more metres\nto buy this upgrade!");
                        }
                        else{
                            messageText.setText("\nGarage Closed\nCar already repaired!");
                        }
                    }
                break;
                case "AdvanceStage":
                    if (repairCounterPercent == 100 && (stage != 2 || stage != 3)) {
                        atStartEngineScreen = false;
                        Utils.playSound("startCar.mp3");
                        letsDrive();
                    } else if (stage == 2) {
                        atStartEngineScreen = false;
                        Utils.playSound("startCar.mp3");
                        beatStage2SetupStage3();
                    }
                break;
                case "Accelerate":
                    if (numberOfActiveNitros == 0) {
                        originalCPS = clicksPerSecond;
                        accelerateClickedFlag = true;
                        if (clicksPerSecond <= MAX_NORMAL_SPEED_OF_CAR_MINUS_1) {
                            clicksPerSecond++;
                        }
                        if (stage == 3) {
                            if ((clicksPerSecond <= MAX_OVERDRIVE_SPEED_OF_CAR_MINUS_1 && overDrive)) {
                                clicksPerSecond++;
                            }
                        }
                        timerUpdate(nitroBeingUsed);
                        if (stage == 2) {
                            rangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][0];
                            rangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][0]);
                        } else if (stage == 3) {
                            rangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][0];
                            rangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][0]);
                        }
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
                break;
                case "Brake":
                    if (accelerateClickedFlag && numberOfActiveNitros == 0) {
                        if (clicksPerSecond >= 1 && clicksPerSecond <= (MAX_OVERDRIVE_SPEED_OF_CAR_MINUS_1 + 1)) {
                            clicksPerSecond--;
                        } else if (clicksPerSecond < 1) {
                            clicksPerSecond = 0;
                        }
                        timerUpdate(nitroBeingUsed);
                        if (stage == 2) {
                            rangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][0];
                            rangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][0]);
                        } else if (stage == 3) {
                            rangeActual = (int) clicksPerSecond - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][0];
                            rangePermitted = (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][1] - SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG3[obstacleTarget][0]);
                        }
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
                    } else if (stage == 3 && clicksPerSecond > 100) {
                        if (numberOfActiveNitros == 1) {
                            clicksPerSecond = originalCPS;
                            resetNitro();
                        } else {
                            clicksPerSecond = 100;
                            resetNitro();
                        }
                        timerUpdate(nitroBeingUsed);
                    }
                    else {
                        clicksPerSecond = 0;
                    }
                break;
                case "SwerveLeft":
                    //System.out.println("left");
                    if (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][2] == 1 && countDownToPassObstacleOn) { //if feature applicable to target and user in obstacle countdown
                        leftClickCount++;
                        passFailObstacle.setFont(font1);
                        passFailObstacle.setText("L: " + (requiredLeftClicks - leftClickCount) + " | R: " + (requiredRightClicks - rightClickCount));
                        //System.out.println("valid left clicks is" + leftClickCount);
                        if (leftClickCount > requiredLeftClicks || rightClickCount > requiredRightClicks) {
                            passFailObstacle.setFont(font3);
                            passFailObstacle.setText("CRASHED!");
                        }
                    }
                break;
                case "SwerveRight":
                    //System.out.println("right");
                    if (SPEED_RANGE_REQUIRED_OBSTACLES_ARRAY_STG2[obstacleTarget][2] == 1 && countDownToPassObstacleOn) { //if feature applicable to target and user in obstacle countdown
                        rightClickCount++;
                        passFailObstacle.setFont(font1);
                        passFailObstacle.setText("L: " + (requiredLeftClicks - leftClickCount) + " | R: " + (requiredRightClicks - rightClickCount));
                        //System.out.println("valid right clicks is" + rightClickCount);
                        if (leftClickCount > requiredLeftClicks || rightClickCount > requiredRightClicks) {
                            passFailObstacle.setFont(font3);
                            passFailObstacle.setText("CRASHED!");
                        }
                    }
                break;
                case "SkipStage2":
                    letsDrive();
                break;
                case "OverDrive":
                    if (!overDrive && clicksPerSecond == OVERDRIVE_TOGGLE_SPEED) {
                        overDrive = true;
                        button3.setForeground(Color.red);
                        button3.setText("Overdrive: ON");
                        //play overdrive sound
                    } else if (overDrive && clicksPerSecond == OVERDRIVE_TOGGLE_SPEED){
                        overDrive = false;
                        button3.setForeground(Color.black);
                        button3.setText("Overdrive: OFF");
                    }
                break;
                case "Nitrous":
                    originalCPS = clicksPerSecond;
                    if(nitrousBoostsRemainingCount > 0 && numberOfActiveNitros < 5 && !nitroSpeedUp1 && !nitroSpeedUp2 && !nitroSpeedUp3 && !nitroSpeedUp4 && !nitroSpeedUp5 && overDrive) { //&& overDrive) {
                        nitroBeingUsed = boostInject();
                        timerUpdate(nitroBeingUsed);
                    }
                break;
                case "RestartGame":
                    restartGame();
                break;
                case "AddNitros":
                    if (!timerNitroAddButtonDisappearFlag) {
                        nitrousBoostsRemainingCount = nitrousBoostsRemainingCount + NITROS_TO_ADD_WHEN_BUTTON_CLICKED;
                        nitroRechargeValue = generalTimerElapsedSeconds;
                        timerNitroAddButtonDisappearFlag = true;
                        timeUntilAddNitroButtonDisappears = generalTimerElapsedSeconds;
                        button4.setText("Super Nitro (" + nitrousBoostsRemainingCount + ")");
                        break;
                    }
            }
        }
    }

    private int boostInject() {
        int nitroNumberToBeUsed = 0;
        if (stage == 3 && clicksPerSecond <= MAX_SPEED_BOOST_CAN_BE_USED) {
            if (numberOfActiveNitros < MAX_NUMBER_OF_SIMULTANEOUS_NITROS) { // //add back in and refactor if needed for 5 to stop rapid clicking of Nitro
                int arrayElementCount = 0;
                numberOfActiveNitros++;
                for (int i = 0; i < nitrosArray.length; i++) {
                    arrayElementCount++;
                    if (!nitrosArray[i]) { //if this nitro is not being used
                        nitroNumberToBeUsed = arrayElementCount;
                        System.out.println("Nitro to be used is number: " + nitroNumberToBeUsed);
                        nitrosArray[i] = true;
                        break;
                    }
                }
                if (nitroNumberToBeUsed == 1) {
                    preNitroCPS1 = clicksPerSecond;
                    nitroTicks1 = 0;
                    originalGeneralTimerValue1 = 0;
                    nitroSpeedUp1 = true;
                }
                if (nitroNumberToBeUsed == 2) {
                    preNitroCPS2 = clicksPerSecond;
                    nitroTicks2 = 0;
                    originalGeneralTimerValue2 = 0;
                    nitroSpeedUp2 = true;
                }
                if (nitroNumberToBeUsed == 3) {
                    preNitroCPS3 = clicksPerSecond;
                    nitroTicks3 = 0;
                    originalGeneralTimerValue3 = 0;
                    nitroSpeedUp3 = true;
                }
                if (nitroNumberToBeUsed == 4) {
                    preNitroCPS4 = clicksPerSecond;
                    nitroTicks4 = 0;
                    originalGeneralTimerValue4 = 0;
                    nitroSpeedUp4 = true;
                }
                if (nitroNumberToBeUsed == 5) {
                    preNitroCPS5 = clicksPerSecond;
                    nitroTicks5 = 0;
                    originalGeneralTimerValue5 = 0;
                    nitroSpeedUp5 = true;
                }
                nitrousBoostsRemainingCount--;
                button4.setText("Super Nitro (" + nitrousBoostsRemainingCount + ")");
                System.out.println("Number of active nitros: " + numberOfActiveNitros);
            }
        }
        return nitroNumberToBeUsed;
    }

    private void letsDrive() {
        distanceToEndOfStageGoal = DISTANCE_TO_STEVE;
        repairCounterPercent = 0;
        double distanceToEndOfStageGoalAsDouble = distanceToEndOfStageGoal;
        DecimalFormat num = new DecimalFormat("0.00");
        num.setRoundingMode(RoundingMode.FLOOR);
        kmGoal = num.format(distanceToEndOfStageGoalAsDouble/1000);
        clicksPerSecond = 0;
        stage = 2;
        setGeneralTimer();
        generalElapsedCounter.start();
        setDelayPanelAfterObstacleTimer();
        distanceToGoTitleLabel.setText("Distance to Steve:");
        distanceToGoLabel.setText(kmGoal + "km");
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
        button4.setBorder(null);
        button4.setFont(font1);
        button4.setForeground(Color.black);
        price1.setText("");
        price2.setText("");
        price3.setText("");
        price4.setText("");
        price.setText("");
    }

    private void beatStage2SetupStage3() {
        nitroRechargeValue = 0;
        obstacleType = obstacleNameArrayStg3[0];
        obstacleTarget = 0;
        failedObstacleWithinApproachOfEndOfStageGoal = false;
        approachingEndOfStageGoalFlag = false;
        distanceToEndOfStageGoal = DISTANCE_TO_ESCAPE_PLANE;
        distanceToEndOfStageGoalAfterFail =
        costOfFailureValue = COST_OF_FAILURE_STAGE3_START_VALUE;
        passFailObstacle.setText("");
        obstacleConditionsTitle.setText("");
        obstacleConditions.setText("");
        timerObstacleTitle.setText("");
        timerObstacle.setText("");
        costOfFailure.setText("");
        whatIsNextObstacle.setText("");
        stage = 3;
        nitrousBoostsRemainingCount = NITROUS_COUNT;
        nextObstDistance = FIRST_OBSTACLE_STAGE3;
        generalTimerElapsedLabel.setText("Escape Plane Leaves In " + (TIME_UNTIL_PENALTY_STAGE3 / 60) + " min!");
        generalTimerElapsedValue.setForeground(Color.white);
        generalTimerElapsedValue.setText("0:00");
        clickCountLabel.setText("0m");
        distanceToGoTitleLabel.setForeground(Color.yellow);
        distanceToGoTitleLabel.setText("Distance to Escape Plane:");
        distanceToGoLabel.setText((DISTANCE_TO_ESCAPE_PLANE / 1000) + "km");
        whatIsNextObstacle.setText("Next obstacle: Motorway Entry Road");
        distanceToNextObstacleTitleLabel.setText("Distance to obstacle:");
        distanceToNextObstacleLabel.setText("2500m");
        autoClickerUnlocked = true; //unlock buttons for stg2part2
        towTruckUnlocked = true;
        mechanicUnlocked = true;
        button1.setActionCommand("Accelerate");
        button2.setActionCommand("Brake");
        button3.setActionCommand("OverDrive");
        button4.setActionCommand("Nitrous");
        button1.setText("Accelerate");
        button2.setText("Brake / Kill Nitro");
        button3.setText("Overdrive: OFF");
        button4.setFont(font1);
        button4.setForeground(Color.black);
        button4.setText("Super Nitro (" + nitrousBoostsRemainingCount + (")"));
        generalElapsedCounter.restart();
        generalElapsedCounter.start();
        moreThanOneMinuteElapsedFlag = false;
        generalTimerElapsedSeconds = 0;
        generalTimerElapsedMinutes = 0;
        generalTimerSecondsToDisplay = 0;
        leftClickCount = 0;
        rightClickCount = 0;
        clickCount = 0;
        System.out.println("You beat Stage 2 and met Steve!");
    }

    private void beatStage3SetupStage4() {
        System.out.println("stage 3 after reaching plane to escape");
    }

    private void gameOver() {
        generalTimerElapsedValue.setForeground(Color.red);
        if (stage == 2) {
            generalTimerElapsedValue.setText("YOU HIT STEVE!!");
        } else if (stage == 3){
            generalTimerElapsedValue.setText("PLANE LEFT!");
        }
        passFailObstacle.setForeground(Color.red);
        passFailObstacle.setFont(font3);
        passFailObstacle.setText("GAME OVER!");
        autoClickerUnlocked = false;
        towTruckUnlocked = false;
        mechanicUnlocked = false;
        button1.setText(LOCKED);
        button2.setText(LOCKED);
        button3.setText(LOCKED);
        button1.setActionCommand("");
        button2.setActionCommand("");
        button3.setActionCommand("");
        button4.setActionCommand("RestartGame");
        button4.setText("Restart Game");
        System.out.println("Game Over");
        timer.stop();
        generalElapsedCounter.stop();
        gameOverFlag = true;
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
            if (stage != 3 && stage != 2) {
                autoClickerUnlocked = true;
                towTruckUnlocked = true;
                mechanicUnlocked=false;
                driveUnlocked = true;
            } else if (stage == 2) {
                autoClickerUnlocked = false;
                towTruckUnlocked = false;
                mechanicUnlocked = false;
                button1.setText(LOCKED);
                button2.setText(LOCKED);
                button3.setText(LOCKED);
                button1.setActionCommand("");
                button2.setActionCommand("");
                button3.setActionCommand("");
            }
            if ((stage != 1 && stage != 2) || (stage == 2)) {
                button4.setActionCommand("AdvanceStage");
            }
            button4.setFont(font3);
            button4.setForeground(Color.red);
            button4.setBorder(raisedBorder);
            button4.setText("START ENGINE!");
            driveFirstClickFlag = 0;
            atStartEngineScreen = true;
            if (stage == 2) {
                generalTimerElapsedLabel.setText("You met Steve!");
                generalTimerElapsedValue.setText("NICE ONE MATE!");
            }
        }
    }
    public class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JButton button = (JButton) e.getSource();

            if (stage != 1 && driveFirstClickFlag == 0 && button == button4) {
                driveFirstClickFlag = 1;
                BeginningOfADrivingStage = false;
            }
            if (stage == 2 || stage == 3) {
                if (button == button1) {
                    messageText.setText("\nAccelerate by 1m/s");
                }
                if (button == button2 && stage == 2) {
                    messageText.setText("\nSlow by 1m/s");
                } else if (button == button2 && stage == 3) {
                        messageText.setText("\nSlow by 1m/s\nOr if nitros are active, kill them!");
                }
                if (button == button3 && stage == 2) {
                    messageText.setText("\nSwerve Left!");
                } else if (button == button3 && stage == 3) {
                    messageText.setText("Toggle OverDrive\nMust be at 70+m/s\nAllows further acceleration & nitros!");
                }
                if (button == button4 && stage == 2) {
                    messageText.setText("\nSwerve Right!");
                } else if (button == button4 && stage == 3) {
                    messageText.setText("Nitrous Oxide\nHuge speed boost!\nYou only have " + nitrousBoostsRemainingCount + "!" + "\nAnd OverDrive needs to be on!");
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

            if (stage == 1) {
                if (button == button1) {
                    if ((!autoClickerUnlocked) || (atStartEngineScreen)) {
                        messageText.setText("\n\n\nThis item is currently locked!");
                    } else {
                        messageText.setText("Hired Help!\n[Price: " + autoClickerPrice + "]\nExtra manpower to push \nevery 10 seconds!");
                    }
                } else if (button == button2) {
                    if ((!towTruckUnlocked) || (atStartEngineScreen)) {
                        messageText.setText("\n\n\nThis item is currently locked!");
                    } else {
                        messageText.setText("Call a Tow Truck!\n[Price: " + towTruckPrice + "]\nTow Truck will move you much\nfaster than a pair of hands!");
                    }

                } else if (button == button3) {
                    if ((!mechanicUnlocked) || (atStartEngineScreen)) {
                        messageText.setText("\n\n\nThis item is currently locked!");
                    } else {
                        messageText.setText("You arrived at the mechanic! Phew!\nSpend " + PRICE_TO_UNLOCK_MECHANIC + " + " + CLICKS_TO_FIX_CAR + " clicks to\nrepair your wheels!");
                    }
                } else if (button == button4) {
                    if (stage == 1) {
                        messageText.setText("\n\n\nThis item is currently locked!");
                    }
                }
            }
            if ((stage == 2 || stage == 3) && !atStartEngineScreen) {
                if (button == buttonAddNitros) {
                    messageText.setText("\n\n\nAdd " + NITROS_TO_ADD_WHEN_BUTTON_CLICKED + " Nitros!");
                }
                if (button == button1) {
                    messageText.setText("\nAccelerate by 1m/s");
                }
                if (stage == 2 && button == button2) {
                    messageText.setText("\nSlow by 1m/s");
                }
                if (stage == 3 && button == button2 && driveFirstClickFlag != 0) {
                    messageText.setText("\nSlow by 1m/s\nOr if nitros are active, kill them!");
                }
                if (button == button3 && stage == 2) {
                    messageText.setText("\nSwerve Left!");
                } else if (button == button3 && stage == 3) {
                    messageText.setText("Toggle OverDrive\nMust be at 70+m/s\nAllows further acceleration & nitros!");
                }
                if (button == button4 && stage == 2) {
                    messageText.setText("\nSwerve Right!");
                } else if (button == button4 && stage == 3) {
                    messageText.setText("Nitrous Oxide\nHuge speed boost!\nYou only have " + nitrousBoostsRemainingCount + "!" + "\nAnd OverDrive needs to be on!");
                }
            } else if ((stage == 2 || stage == 3) && (button == button1 || button == button2 || button == button3)) {
                messageText.setText("\n\n\nThis item is currently locked!");
            } else if ((stage == 0 && button == button1)) {
                messageText.setText("\n\n\nThis item is currently locked!");
            } else if ((stage == 0 && button == button2)) {
                messageText.setText("\n\n\nThis item is currently locked!");
            } else if ((stage == 0 && button == button3)) {
                messageText.setText("\nGarage Closed\nCar already repaired!");
            }
                else if ((stage == 0 && button == button4)) {
                    messageText.setText("\n\nStart your engine and\nbegin stage 2");
            }else if (((stage == 2 || stage == 3) && button == button4)) {
                messageText.setText("\n\nStart your engine and\nbegin stage 3");
            }
        }
        @Override
        public void mouseExited(MouseEvent e) {
            JButton button = (JButton) e.getSource();

            if (button == buttonAddNitros) {
                messageText.setText(null);
            } else if(button == button1){
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

    public void restartGame() {
        setInitialVariables(true); //set initial variables and reset everything if restarting
    }

    private void setInitialVariables(boolean restart) {
        if (restart) {
            gameOverFlag = false; timerNitroAddButtonDisappearFlag = false; BeginningOfADrivingStage = false; timerOn = false; delayObstaclePanelOn = false; autoClickerUnlocked = false; towTruckUnlocked = false; mechanicTriggeredYet = false; mechanicUnlocked = false; driveUnlocked = false; carInMechanic = false; accelerateClickedFlag = false; displayObstacleConditionsFlag = false; costOfFailureFirstIterationFlag = false;
            failedObstacleWithinApproachOfEndOfStageGoal = false; approachingEndOfStageGoalFlag = false; checkTimeSteveMoveCount = false; startDelayTimerFlag = false; countDownToPassObstacleOn = false; startCountDownToPassObstacleFlag = false; wasPassingNowFailing = false; moreThanOneMinuteElapsedFlag = false;
            degradeNitro1 = false; degradeNitro2 = false; degradeNitro3 = false; degradeNitro4 = false; degradeNitro5 = false; nitro1 = false; nitro2 = false; nitro3 = false; nitro4 = false; nitro5 = false; nitroSpeedUp1 = false; nitroSpeedUp2 = false; nitroSpeedUp3 = false; nitroSpeedUp4 = false; nitroSpeedUp5 = false; overDrive = false; atStartEngineScreen = false;
            try {
                timer.restart();
                timer.stop();
            } catch (Exception e) {
                System.out.println("timer didn't exist.");
            }
            try {
                delayPanelAfterObstacleTimer.restart();
                delayPanelAfterObstacleTimer.stop();
            } catch (Exception e) {
                System.out.println("delay timer to remove obstacle timer didn't exist.");
            }
            try {
                countDownToPassObstacleTimer.restart();
                countDownToPassObstacleTimer.stop();
            } catch (Exception e) {
                System.out.println("countdown to pass obstacle timer didn't exist.");
            }
            try {
                generalElapsedCounter.restart();
                generalElapsedCounter.stop();
            } catch (Exception e) {
                System.out.println("general timer didn't exist.");
            }
        }
        timeUntilAddNitroButtonDisappears = 0;
        nitroRechargeValue = 0;
        originalCPS = 0;
        km = "";
        kmGoal = "";
        clicksPerSecond = 0;
        clickCount = 0;
        autoClickerNumber = 0;
        autoClickerPrice = PRICE_TO_UNLOCK_AUTOCLICKER;
        towTruckNumber = 0;
        towTruckPrice = PRICE_TO_UNLOCK_TOW_TRUCK;
        mechanicPrice = PRICE_TO_UNLOCK_MECHANIC;
        repairCounter = 0;
        clicksLeftToFixCar = CLICKS_TO_FIX_CAR;
        stage = 1;
        driveFirstClickFlag = 0;
        obstacleType = "Garage Gate";
        nextObstDistance = DISTANCE_TO_GARAGE_GATE;
        distanceToEndOfStageGoal = 0;
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
        generalTimerSecondsToDisplay = 0;
        countTimesPassTimeUntilEndOStageGoal = 1;
        leftClickCount = 0;
        rightClickCount = 0;
        requiredLeftClicks = 0;
        requiredRightClicks = 0;
        countDisplay = 0;
        nitrousBoostsRemainingCount = 0;
        resetNitro();
        if (restart) {
            generalTimerElapsedLabel.setForeground(Color.yellow);
            generalTimerElapsedLabel.setFont(font2);
            generalTimerElapsedLabel.setText("");
            generalTimerElapsedValue.setForeground(Color.white);
            generalTimerElapsedValue.setFont(font1);
            generalTimerElapsedValue.setText("");
            metresTravelledLabel.setText("Distance travelled:");
            metresTravelledLabel.setForeground(Color.yellow);
            metresTravelledLabel.setFont(font2);
            clickCountLabel.setForeground(Color.white);
            clickCountLabel.setFont(font1);
            clickCountLabel.setText("0m");
            perSecondLabelLabel.setText("");
            perSecondLabelLabel.setForeground(Color.yellow);
            perSecondLabelLabel.setFont(font2);
            perSecondLabel.setText("");
            perSecondLabel.setForeground(Color.white);
            perSecondLabel.setFont(font2);
            obstacleConditionsTitle.setText("");
            obstacleConditionsTitle.setForeground(Color.yellow);
            obstacleConditionsTitle.setFont(font2);
            obstacleConditions.setText("");
            obstacleConditions.setForeground(Color.white);
            obstacleConditions.setFont(font2);
            timerObstacleTitle.setText("");
            timerObstacleTitle.setForeground(Color.yellow);
            timerObstacleTitle.setFont(font2);
            timerObstacle.setText("");
            timerObstacle.setForeground(Color.white);
            timerObstacle.setFont(font2);
            costOfFailure.setText("");
            costOfFailure.setForeground(Color.red);
            costOfFailure.setFont(font2);
            passFailObstacle.setText("");
            passFailObstacle.setForeground(Color.green);
            passFailObstacle.setFont(font1);
            price.setText("Price");
            price.setHorizontalAlignment(RIGHT);
            price.setForeground(Color.white);
            price.setFont(font1);
            price1.setText("10m");
            price1.setHorizontalAlignment(RIGHT);
            price1.setForeground(Color.white);
            price1.setFont(font1);
            price2.setText("");
            price2.setHorizontalAlignment(RIGHT);
            price2.setForeground(Color.white);
            price2.setFont(font1);
            price3.setText("");
            price3.setHorizontalAlignment(RIGHT);
            price3.setForeground(Color.white);
            price3.setFont(font1);
            price4.setText("");
            price4.setHorizontalAlignment(RIGHT);
            price4.setForeground(Color.white);
            price4.setFont(font1);
            button1.setText(LOCKED);
            button1.setFont(font1);
            button1.setActionCommand("Hired Help");
            button1.setForeground(Color.black);
            button2.setText(LOCKED);
            button2.setFont(font1);
            button2.setActionCommand("Tow Truck");
            button2.setForeground(Color.black);
            button3.setText(LOCKED);
            button3.setFont(font1);
            button3.setForeground(Color.black);
            button3.setActionCommand("Mechanic");
            button4.setText(LOCKED);
            button4.setFont(font1);
            button4.setForeground(Color.black);
            button4.setActionCommand(null);
            distanceToGoTitleLabel.setText("");
            distanceToGoTitleLabel.setForeground(Color.yellow);
            distanceToGoTitleLabel.setFont(font2);
            distanceToGoLabel.setText("");
            distanceToGoLabel.setForeground(Color.white);
            distanceToGoLabel.setFont(font1);
            distanceToNextObstacleTitleLabel.setText("");
            distanceToNextObstacleTitleLabel.setForeground(Color.yellow);
            distanceToNextObstacleTitleLabel.setFont(font2);
            distanceToNextObstacleLabel.setText("");
            distanceToNextObstacleLabel.setForeground(Color.white);
            distanceToNextObstacleLabel.setFont(font1);
            whatIsNextObstacle.setText("");
            whatIsNextObstacle.setForeground(Color.white);
            whatIsNextObstacle.setFont(font2);
        }
    }
    private void resetNitro() {
        preNitroCPS1 = 0;
        preNitroCPS2 = 0;
        preNitroCPS3 = 0;
        preNitroCPS4 = 0;
        preNitroCPS5 = 0;
        originalGeneralTimerValue1 = 0;
        originalGeneralTimerValue2 = 0;
        originalGeneralTimerValue3 = 0;
        originalGeneralTimerValue4 = 0;
        originalGeneralTimerValue5 = 0;
        nitroTicks1 = 0;
        nitroTicks2 = 0;
        nitroTicks3 = 0;
        nitroTicks4 = 0;
        nitroTicks5 = 0;
        degradeNitro1 = false;
        degradeNitro2 = false;
        degradeNitro3 = false;
        degradeNitro4 = false;
        degradeNitro5 = false;
        nitro1 = false;
        nitro2 = false;
        nitro3 = false;
        nitro4 = false;
        nitro5 = false;
        nitroSpeedUp1 = false;
        nitroSpeedUp2 = false;
        nitroSpeedUp3 = false;
        nitroSpeedUp4 = false;
        nitroSpeedUp5 = false;
        nitrosArray[0] = false;
        nitrosArray[1] = false;
        nitrosArray[2] = false;
        nitrosArray[3] = false;
        nitrosArray[4] = false;
        nitroBeingUsed = 0;
        numberOfActiveNitros = 0;
    }
}