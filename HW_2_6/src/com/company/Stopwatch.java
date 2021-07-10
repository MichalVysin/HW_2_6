package com.company;

import javax.swing.*;
import java.awt.*;

public class Stopwatch {


    JLabel timerLabel = new JLabel();
    private int elapsedTime = 0;
    private int seconds = 0;
    private int minutes = 0;
    private String secondsString = String.format("%02d", seconds);
    private String minutesString = String.format("%02d", minutes);
    private final Timer timer = new Timer(1000, e -> {

        elapsedTime += 1000;
        seconds = (elapsedTime / 1000) % 60;
        minutes = (elapsedTime / 60000) % 60;
        secondsString = String.format("%02d", seconds);
        minutesString = String.format("%02d", minutes);
        timerLabel.setText(" " + minutesString + ":" + secondsString + " ");
    });

    public Stopwatch(){

        timerLabel.setText(" " + minutesString + ":" + secondsString + " ");
        timerLabel.setFont(new Font("MV Boli", Font.BOLD, 22));
//        timerLabel.setBackground(new Color(74,74,74,255));
        timerLabel.setBackground(new Color(31, 26, 26));
        timerLabel.setForeground(new Color(163, 0, 0));
        timerLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        timerLabel.setOpaque(true);
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public Timer getTimer() {
        return timer;
    }
}
