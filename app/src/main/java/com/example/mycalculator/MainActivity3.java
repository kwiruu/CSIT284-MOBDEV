package com.example.mycalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class MainActivity3 extends AppCompatActivity {
    LinearLayout linearlayout2;
    TextView textView1;
    Button resetBtn;
    Button[][] butn = new Button[5][5];
    final int[] COLORS = {Color.RED, Color.BLUE, Color.BLACK};
    ConstraintLayout csLayout;
    static boolean playerColorIsBlue;
    boolean enabled=true;
    int[][] tiles ={
            {0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        resetBtn = findViewById(R.id.resetBtn);
        csLayout = findViewById(R.id.csLayout);
        textView1 = findViewById(R.id.playerText);
        linearlayout2 = findViewById(R.id.linearLayout2);
        linearlayout2.setBackgroundColor(Color.WHITE);

        Random random = new Random();
        int num = random.nextInt(2);
        if (num == 1) {
            csLayout.setBackgroundColor(COLORS[1]);
            playerColorIsBlue = true;
            textView1.setText("Blue Player Turn");
        } else {
            csLayout.setBackgroundColor(COLORS[0]);
            playerColorIsBlue = false;
            textView1.setText("Red Player Turn");
        }

        butn = new Button[][]{
                {findViewById(R.id.button), findViewById(R.id.button2), findViewById(R.id.button3), findViewById(R.id.button4), findViewById(R.id.button5)},
                {findViewById(R.id.button6), findViewById(R.id.button7), findViewById(R.id.button8), findViewById(R.id.button9), findViewById(R.id.button10)},
                {findViewById(R.id.button11), findViewById(R.id.button12), findViewById(R.id.button13), findViewById(R.id.button14), findViewById(R.id.button15)},
                {findViewById(R.id.button16), findViewById(R.id.button17), findViewById(R.id.button18), findViewById(R.id.button19), findViewById(R.id.button20)},
                {findViewById(R.id.button21), findViewById(R.id.button22), findViewById(R.id.button23), findViewById(R.id.button24), findViewById(R.id.button25)}
        };


        for(int j = 0; j<5;j++){
            int colz = j;

            butn[0][colz].setOnClickListener(new View.OnClickListener() {
                final int c = colz;
                @Override
                public void onClick(View v) {
                    if(enabled){
                        clickButton(c);
                        System.out.println("na click");
                        if(playerColorIsBlue==true){
                            playerColorIsBlue=false;
                        } else if (playerColorIsBlue==false) {
                            playerColorIsBlue=true;
                        }
                    }
                }
            });
        }

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetbuttons();
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        tiles[i][j] = 0;
                    }
                }
            }
        });
    }


    private boolean checkForWinner(int player) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= 2; j++) {
                if (tiles[i][j] == player && tiles[i][j + 1] == player && tiles[i][j + 2] == player) {
                    return true;
                }
            }
        }
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j < 5; j++) {
                if (tiles[i][j] == player && tiles[i + 1][j] == player && tiles[i + 2][j] == player) {
                    return true;
                }
            }
        }
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (tiles[i][j] == player && tiles[i + 1][j + 1] == player && tiles[i + 2][j + 2] == player) {
                    return true;
                }
            }
        }
        for (int i = 0; i <= 2; i++) {
            for (int j = 2; j < 5; j++) {
                if (tiles[i][j] == player && tiles[i + 1][j - 1] == player && tiles[i + 2][j - 2] == player) {
                    return true;
                }
            }
        }
        return false;
    }


    public void clickButton(int c) {
        int r = 0;

        if (tiles[0][c] == 1 || tiles[0][c] == 2) {
            return;
        }
        while (r < 4 && tiles[r + 1][c] != 1 && tiles[r + 1][c] != 2) {
            r++;
        }

        if (playerColorIsBlue) {
            tiles[r][c] = 1;
            csLayout.setBackgroundColor(COLORS[0]);
            butn[r][c].setBackgroundColor(COLORS[1]);
            textView1.setText("Red Player Turn");
        } else {
            tiles[r][c] = 2;
            csLayout.setBackgroundColor(COLORS[1]);
            butn[r][c].setBackgroundColor(COLORS[0]);
            textView1.setText("Blue Player Turn");
        }


//        System.out.println(tiles[0][0] + " " + tiles[0][1] + " " + tiles[0][2] + " " + tiles[0][3] + " " + tiles[0][4]);
//        System.out.println(tiles[1][0] + " " + tiles[1][1] + " " + tiles[1][2] + " " + tiles[1][3] + " " + tiles[1][4]);
//        System.out.println(tiles[2][0] + " " + tiles[2][1] + " " + tiles[2][2] + " " + tiles[2][3] + " " + tiles[2][4]);
//        System.out.println(tiles[3][0] + " " + tiles[3][1] + " " + tiles[3][2] + " " + tiles[3][3] + " " + tiles[3][4]);
//        System.out.println(tiles[4][0] + " " + tiles[4][1] + " " + tiles[4][2] + " " + tiles[4][3] + " " + tiles[4][4]);

        if (checkForWinner(1)) {
            // Player 1 wins
            disableButtons();
            textView1.setText("Blue Player wins!");
            csLayout.setBackgroundColor(COLORS[1]);
        } else if (checkForWinner(2)) {
            // Player 2 wins
            disableButtons();
            textView1.setText("Red Player wins!");
            csLayout.setBackgroundColor(COLORS[0]);
        }
    }
    private void disableButtons() {
        enabled = false;
        for (Button[] row : butn) {
            for (Button button : row) {
                button.setEnabled(false);
            }
        }
    }


    private void resetbuttons() {
        enabled = true;
        for (Button[] row : butn) {
            for (Button button : row) {
                button.setEnabled(true);
                button.setBackgroundColor(Color.BLACK);
            }
        }

        Random random = new Random();
        int num = random.nextInt(2);
        if (num == 1) {
            csLayout.setBackgroundColor(COLORS[1]);
            playerColorIsBlue = true;
            textView1.setText("Blue Player Turn");
        } else {
            csLayout.setBackgroundColor(COLORS[0]);
            playerColorIsBlue = false;
            textView1.setText("Red Player Turn");
        }
    }


}
