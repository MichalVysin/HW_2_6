package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public int widthFiled;
    public int heightField;
    public int numberOfMine;
    JFrame frame;
    JPanel gamePanel;
    JPanel topPanel;
    JPanel bottomPanel;
    JPanel leftPanel;
    JPanel rightPanel;
    JPanel timerPanel;
    JLabel mineLabel;
    JButton newGameButton;
    PlayField playField;
    List<JButton> buttonList = new ArrayList<>();
    int countOfMine;
    Stopwatch stopwatch;
    static Integer bestTime = null;
    static int bestSeconds;
    static int bestMinutes;
    static String bestSecondsString;
    static String bestMinutesString;

    public Game(int widthFiled, int heightField, int numberOfMine) {
        this.widthFiled = widthFiled;
        this.heightField = heightField;
        this.numberOfMine = numberOfMine;

        playField = new PlayField(widthFiled, heightField, numberOfMine);

        stopwatch = new Stopwatch();

        countOfMine = numberOfMine;

        frame = new JFrame();

        frame.setTitle("Hledani min");
        frame.setSize(widthFiled * 40, heightField * 40);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        newGameButton = new JButton();
        newGameButton.setText(" N ");
        newGameButton.setBounds((((frame.getWidth() - 46) / 2) - 25), 5, 50, 50);
        newGameButton.setFont(new Font("MV Boli", Font.BOLD, 18));
        newGameButton.setBackground(new Color(31, 26, 26));
        newGameButton.setForeground(new Color(163, 0, 0));
        newGameButton.setBorder(BorderFactory.createRaisedBevelBorder());
        newGameButton.setOpaque(true);
        newGameButton.setFocusable(false);
        newGameButton.setHorizontalAlignment(JButton.CENTER);
        newGameButton.addActionListener(e -> {
            if (e.getSource() == newGameButton) {
                frame.dispose();
                new Game(widthFiled, heightField, numberOfMine);

            }
        });

        mineLabel = new JLabel();
        mineLabel.setBounds(5, 5, 105, 50);
        mineLabel.setText(countOfMine + "/" + numberOfMine);
        mineLabel.setFont(new Font("MV Boli", Font.BOLD, 22));
        mineLabel.setBackground(new Color(31, 26, 26));
        mineLabel.setForeground(new Color(163, 0, 0));
        mineLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        mineLabel.setOpaque(true);
        mineLabel.setHorizontalAlignment(JLabel.CENTER);

        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(74, 74, 74, 255));
        leftPanel.setPreferredSize(new Dimension(10, heightField * 25));

        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(74, 74, 74, 255));
        rightPanel.setPreferredSize(new Dimension(10, heightField * 25));

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(74, 74, 74, 255));
        bottomPanel.setPreferredSize(new Dimension(widthFiled * 25, 10));

        topPanel = new JPanel();
        topPanel.setBackground(new Color(74, 74, 74, 255));
        topPanel.setPreferredSize(new Dimension(widthFiled * 25, 80));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        timerPanel = new JPanel();
        timerPanel.setBackground(new Color(74, 74, 74, 255));
        timerPanel.setPreferredSize(new Dimension((frame.getWidth() - 46), 60));
        timerPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        timerPanel.setLayout(null);

        gamePanel = new JPanel();
        gamePanel.setBackground(new Color(74, 74, 74, 255));
        gamePanel.setLayout(new GridLayout(heightField, widthFiled));
        gamePanel.setSize(widthFiled * 25, heightField * 25);

        stopwatch.timerLabel.setBounds((frame.getWidth() - 46 - 110), 5, 105, 50);

        for (int i = 0; i < heightField; i++) {
            for (int j = 0; j < widthFiled; j++) {
                JButton button = new JButton();

                button.setPreferredSize(new Dimension(20, 20));
                button.setBackground(new Color(95, 95, 95, 255));
                button.setFont(new Font(null, Font.BOLD, 12));
                button.setOpaque(true);
                button.setBorder(BorderFactory.createRaisedBevelBorder());
                button.setContentAreaFilled(true);
                button.setVerticalTextPosition(JButton.CENTER);
                button.setHorizontalTextPosition(JButton.CENTER);
                button.setFocusable(false);
                buttonList.add(button);


                int finalCoordinateX = j;
                int finalCoordinateY = i;

                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            if (button.isEnabled()) {
                                if (!stopwatch.getTimer().isRunning()) {
                                    stopwatch.getTimer().start();
                                }
                                if (!button.getText().equals("?")) {
                                    if (playField.isThereMine(finalCoordinateX, finalCoordinateY)) {
                                        stopwatch.getTimer().stop();
                                        button.setText("x");
                                        button.setBackground(Color.red);
                                        button.setBorder(BorderFactory.createLoweredBevelBorder());
                                        for (JButton b : buttonList) {
                                            b.setEnabled(false);
                                        }
                                        newGameButton.setText("L");

                                        endGameLoseMessage();


                                    } else if (playField.getNeighboursWithMine(finalCoordinateX, finalCoordinateY) == 0) {
                                        button.setText("");
                                        button.setBackground(new Color(74, 74, 74, 255));
                                        button.setBorder(BorderFactory.createLoweredBevelBorder());
                                        playField.reduceTheNumberOfRemainingFieldsToWin();
                                        if (playField.hasAlreadyWon()) {
                                            newGameButton.setText("W");
                                            stopwatch.getTimer().stop();
                                            endGameWinMessage();
                                        }
                                    } else {
                                        button.setBackground(new Color(74, 74, 74, 255));
                                        button.setText(String.valueOf(playField.getNeighboursWithMine(finalCoordinateX, finalCoordinateY)));
                                        button.setBorder(BorderFactory.createLoweredBevelBorder());
                                        playField.reduceTheNumberOfRemainingFieldsToWin();
                                        if (playField.hasAlreadyWon()) {
                                            newGameButton.setText("W");
                                            stopwatch.getTimer().stop();
                                            endGameWinMessage();
                                        }
                                    }
                                    button.setEnabled(false);

                                }
                            }

                        }

                        if (SwingUtilities.isRightMouseButton(e)) {
                            if (button.isEnabled()) {
                                if (!stopwatch.getTimer().isRunning()) {
                                    stopwatch.getTimer().start();
                                }
                                if (!button.getText().equals("?")) {
                                    button.setText("?");
                                    button.setBackground(Color.GREEN);
                                    --countOfMine;
                                    mineLabel.setText(countOfMine + "/" + numberOfMine);


                                } else {
                                    button.setText("");
                                    button.setBackground(new Color(95, 95, 95, 255));
                                    ++countOfMine;
                                    mineLabel.setText(countOfMine + "/" + numberOfMine);
                                }
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

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });

                gamePanel.add(button);


            }


        }


        timerPanel.add(newGameButton);
        timerPanel.add(stopwatch.timerLabel);
        timerPanel.add(mineLabel);
        topPanel.add(timerPanel);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.setVisible(true);


    }

    public void endGameLoseMessage() {

        String[] response = {"Nova hra", "Zmenit obtiznost", "Konec"};
        String message;

        if (bestTime == null) {
            message = "Neuspel jsi!           Zadneho rekordu jeste nebylo dosazeno.";
        } else {
            message = "Neuspel jsi!                                   Rekord: " + bestMinutesString + ":" + bestSecondsString;
        }

        int action = JOptionPane.showOptionDialog(null, message, "Konec hry",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, response, 0);


        switch (action) {

            case 0 -> {
                frame.dispose();
                new Game(widthFiled, heightField, numberOfMine);
            }
            case 1 -> {
                frame.dispose();
                new LaunchPage();
            }
            case 2 -> System.exit(0);

        }
    }

    public void endGameWinMessage() {

        String[] response = {"Hrat znovu", "Zmenit obtiznost", "Konec"};
        String message;

        if (bestTime == null || bestTime > stopwatch.getElapsedTime()) {
            bestTime = stopwatch.getElapsedTime();
            bestSeconds = (bestTime / 1000) % 60;
            bestMinutes = (bestTime / 60000) % 60;
            bestSecondsString = String.format("%02d", bestSeconds);
            bestMinutesString = String.format("%02d", bestMinutes);
            message = "Gratulujeme! Dosahl jsi noveho rekordu!!! Novy rekord: " + bestMinutesString + ":" + bestSecondsString;

        } else {
            message = "Gratulujeme! Vyhral jsi!                       Stavajici rekord: " + bestMinutesString + ":" + bestSecondsString;
        }

        int action = JOptionPane.showOptionDialog(null, message, "Vyhral jsi!",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, response, 0);

        switch (action) {

            case 0 -> {
                frame.dispose();
                new Game(widthFiled, heightField, numberOfMine);
            }
            case 1 -> {
                frame.dispose();
                new LaunchPage();
            }
            case 2 -> System.exit(0);

        }

    }

}
