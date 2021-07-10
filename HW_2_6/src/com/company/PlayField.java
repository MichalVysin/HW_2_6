package com.company;

import java.util.Random;

public class PlayField {

    private final boolean[][] mineField;
    private int numberOfFieldsToWin;
    private final int widthField;
    private final int heightField;

    public PlayField(int widthField, int heightField, int numberOfMine){
        mineField = new boolean[widthField][heightField];
        this.widthField = widthField;
        this.heightField = heightField;

        numberOfFieldsToWin = (widthField * heightField) - numberOfMine;

        Random random = new Random();
        for (int i = 0; i < numberOfMine;){
            int coordinateX = random.nextInt(widthField);
            int coordinateY = random.nextInt(heightField);
            if (!mineField[coordinateX][coordinateY]){
                mineField[coordinateX][coordinateY] = true;
                i++;
            }
        }

    }


    public boolean isThereMine(int coordinateX, int coordinateY){
        return mineField[coordinateX][coordinateY];
    }

    public boolean hasAlreadyWon(){
        return numberOfFieldsToWin == 0;
    }

    public void reduceTheNumberOfRemainingFieldsToWin(){
        numberOfFieldsToWin--;
    }

    public int getNeighboursWithMine(int coordinateX, int coordinateY){
        int numberOfMine = 0;

        if (coordinateX > 0 && coordinateY > 0 && mineField[coordinateX -1][coordinateY -1]) {
                numberOfMine++;
        }
        if (coordinateY > 0 && mineField[coordinateX][coordinateY - 1]){
            numberOfMine++;
        }
        if (coordinateX < widthField - 1 && coordinateY > 0 && mineField[coordinateX +1][coordinateY -1]){
            numberOfMine++;
        }
        if (coordinateX > 0 && mineField[coordinateX - 1][coordinateY]){
            numberOfMine++;
        }
        if (coordinateX < widthField - 1 && mineField[coordinateX + 1][coordinateY]){
            numberOfMine++;
        }
        if (coordinateX > 0 && coordinateY < heightField - 1 && mineField[coordinateX - 1][coordinateY +1]){
            numberOfMine++;
        }
        if (coordinateY < heightField - 1 && mineField[coordinateX][coordinateY + 1]){
            numberOfMine++;
        }
        if (coordinateX < widthField - 1 && coordinateY < heightField - 1 && mineField[coordinateX + 1][coordinateY + 1]){
            numberOfMine++;
        }

        return numberOfMine;

    }


}
