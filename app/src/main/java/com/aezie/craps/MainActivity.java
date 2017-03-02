package com.aezie.craps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // enum type with constants that represent the game status
    private enum Status { CONTINUE, WON, LOST };

    // constants that represent common rolls of the dice
    private static final int SNAKE_EYES = 2;
    private static final int TREY = 3;
    private static final int SEVEN = 7;
    private static final int YO_LEVEN = 11;
    private static final int BOX_CARS = 12;


    public void playCraps(View view)
    {

        Random randomGenerator = new Random();
        String message = "";

        int myPoint = 0; // point if no win or loss on first roll
        Status gameStatus; // con contain CONTINUE, WON or LOST

        int sumOfDice = 1 + randomGenerator.nextInt(12); // first roll of the dice

        // determine game status and point based on first roll
        switch (sumOfDice)
        {
            case SEVEN: // win with 7 on first roll
            case YO_LEVEN: // win with 11 on first roll
                gameStatus = Status.WON;
                break;
            case SNAKE_EYES: // lose with 2 on first roll
            case TREY: // lose with 3 on first roll
            case BOX_CARS: // lose with 12 on first roll
                gameStatus = Status.LOST;
                break;
            default: // did not win or lose, so remember point
                gameStatus = Status.CONTINUE; // game is not over
                myPoint = sumOfDice; // remember the point
                System.out.printf("Point is %d%n", myPoint);
                break;
        }

        // while game is not complete
        while (gameStatus == Status.CONTINUE) // not WON or LOST
        {
            sumOfDice = 1 + randomGenerator.nextInt(12); // roll dice again

            // determine game status
            if (sumOfDice == myPoint) // win by making point
                gameStatus = Status.WON;
            else if (sumOfDice == SEVEN) // lose by rolling 7 before point
                gameStatus = Status.LOST;
        }

        // display won or lost message
        if (gameStatus == Status.WON)
            message = "You Won!";
        else
            message = "You Lost...";

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        System.out.println(message);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
