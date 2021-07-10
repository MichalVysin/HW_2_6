package com.company;

import javax.swing.*;
import java.awt.*;

public class LaunchPage{

    JFrame frame;
    JButton button;
    JRadioButton easyRadioButton;
    JRadioButton hardRadioButton;
    JRadioButton expertRadioButton;
    JPanel topPanel;
    JPanel centerPanel;
    JPanel bottomPanel;
    JLabel gameName;
    ButtonGroup buttonGroup;

    public int widthField;
    public int heightField;
    public int numberOfMine;


    public LaunchPage() {

        gameName = new JLabel("Hledani min");
        gameName.setFont(new Font("MV Boli", Font.BOLD, 40));
        gameName.setForeground(Color.BLACK);

        topPanel = new JPanel();
        topPanel.setBackground(new Color(74, 74, 74, 255));
        topPanel.setPreferredSize(new Dimension(50, 80));
        topPanel.setBorder(BorderFactory.createLoweredBevelBorder());

        centerPanel = new JPanel();
        centerPanel.setBackground(new Color(95, 95, 95, 255));
        centerPanel.setPreferredSize(new Dimension(50, 50));
        centerPanel.setLayout(null);

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(95, 95, 95, 255));
        bottomPanel.setPreferredSize(new Dimension(50, 90));

        easyRadioButton = new JRadioButton("Lehka");
        easyRadioButton.setBounds(15, 20, 200, 35);
        easyRadioButton.setFont(new Font("MV Boli", Font.BOLD, 35));
        easyRadioButton.setBackground(new Color(95, 95, 95, 255));
        easyRadioButton.setForeground(Color.black);
        easyRadioButton.setFocusable(false);
        easyRadioButton.setOpaque(true);

        hardRadioButton = new JRadioButton("Stredni");
        hardRadioButton.setBounds(15, 80, 200, 35);
        hardRadioButton.setFont(new Font("MV Boli", Font.BOLD, 35));
        hardRadioButton.setBackground(new Color(95, 95, 95, 255));
        hardRadioButton.setForeground(Color.black);
        hardRadioButton.setFocusable(false);
        hardRadioButton.setOpaque(true);

        expertRadioButton = new JRadioButton("Expertni");
        expertRadioButton.setBounds(15, 140, 200, 35);
        expertRadioButton.setFont(new Font("MV Boli", Font.BOLD, 35));
        expertRadioButton.setBackground(new Color(95, 95, 95, 255));
        expertRadioButton.setForeground(Color.black);
        expertRadioButton.setFocusable(false);
        expertRadioButton.setOpaque(true);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(easyRadioButton);
        buttonGroup.add(hardRadioButton);
        buttonGroup.add(expertRadioButton);

        button = new JButton("   Zacit hru   ");
        button.setFont(new Font("MV Boli",Font.BOLD, 35));
        button.setBackground(new Color(74, 74, 74, 255));
        button.setForeground(Color.black);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setFocusable(false);
        button.addActionListener(e -> {
            if (e.getSource() == button){
                if (easyRadioButton.isSelected()){
                    frame.dispose();
                    easyDifficulty();
                    new Game(widthField,heightField,numberOfMine);
                }
                if (hardRadioButton.isSelected()){
                    frame.dispose();
                    hardDifficulty();
                    new Game(widthField,heightField,numberOfMine);
                }
                if (expertRadioButton.isSelected()){
                    frame.dispose();
                    expertDifficulty();
                    new Game(widthField,heightField,numberOfMine);
                }

            }
        });

        frame = new JFrame();

        bottomPanel.add(button);
        topPanel.add(gameName);

        centerPanel.add(easyRadioButton);
        centerPanel.add(hardRadioButton);
        centerPanel.add(expertRadioButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void easyDifficulty(){
        widthField = 9;
        heightField = 9;
        numberOfMine = 10;
    }
    private void hardDifficulty(){
        widthField = 16;
        heightField = 16;
        numberOfMine = 40;
    }
    private void expertDifficulty(){
        widthField = 30;
        heightField = 16;
        numberOfMine = 99;
    }


}
