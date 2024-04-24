package com.example.mycalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.service.quicksettings.Tile;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainMidterm extends AppCompatActivity {
    Button ReturnBtn;
    Button[][] btn;

    Tile btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    final int[] COLORSS ={Color.RED, Color.BLUE, Color.GREEN};
    int[][] tiles ={
            {' ',' ',' '},{' ',' ',' '},{' ',' ',' ',}
    };
    boolean enabled=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_midterm);

        btn = new Button[][]{
                {findViewById(R.id.button),findViewById(R.id.button2),findViewById(R.id.button3)},
                {findViewById(R.id.button4),findViewById(R.id.button5),findViewById(R.id.button6)},
                {findViewById(R.id.button7),findViewById(R.id.button8),findViewById(R.id.button9)}
        };

        for(int i=0; i<3; i++){
            for(int j=0; j<3;j++){
                int row = i;
                int col = j;
                btn[row][col].setOnClickListener(new View.OnClickListener() {
                    final int r = col;
                    final int c = row;
                    @Override
                    public void onClick(View v) {
                        if(enabled){
                            clickButton(r,c);
                            System.out.println("na click");
                        }
                    }
                });

            }
        }
        Button ReturnBtn = (Button) findViewById(R.id.Resetbtn);

        ReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatedColors();
                System.out.println("resetted");
            }
        });
    }

    public void clickButton(int r, int c){
        System.out.println(btn[r][c]+ " is clicked!");

        if(tiles[r][c]==0){
            try {
                btn[r - 1][c].setBackgroundColor(COLORSS[1]);
                btn[r + 1][c].setBackgroundColor(COLORSS[1]);
                btn[r][c + 1].setBackgroundColor(COLORSS[1]);
                btn[r][c - 1].setBackgroundColor(COLORSS[1]);
                tiles[r - 1][c] = 1;
                tiles[r + 1][c] = 1;
                tiles[r][c + 1] = 1;
                tiles[r][c - 1] = 1;
                System.out.println("red!");
            }catch (ArrayIndexOutOfBoundsException e){
                btn[r][c].setBackgroundColor(COLORSS[1]);
                tiles[r][c] = 1;
            }
        }
        else if(tiles[r][c]==1){
            try {
                btn[r - 1][c].setBackgroundColor(COLORSS[2]);
                btn[r + 1][c].setBackgroundColor(COLORSS[2]);
                btn[r][c + 1].setBackgroundColor(COLORSS[2]);
                btn[r][c - 1].setBackgroundColor(COLORSS[2]);
                tiles[r - 1][c] = 2;
                tiles[r + 1][c] = 2;
                tiles[r][c + 1] = 2;
                tiles[r][c - 1] = 2;
                System.out.println("blue!");
            }catch (ArrayIndexOutOfBoundsException e){
                btn[r][c].setBackgroundColor(COLORSS[2]);
                tiles[r][c] = 2;
            }
        }
        else if(tiles[r][c]==2){
            try {
                btn[r - 1][c].setBackgroundColor(COLORSS[0]);
                btn[r + 1][c].setBackgroundColor(COLORSS[0]);
                btn[r][c + 1].setBackgroundColor(COLORSS[0]);
                btn[r][c - 1].setBackgroundColor(COLORSS[0]);
                tiles[r - 1][c] = 0;
                tiles[r + 1][c] = 0;
                tiles[r][c + 1] = 0;
                tiles[r][c - 1] = 0;
                System.out.println("green!");
            }catch (ArrayIndexOutOfBoundsException e){
                btn[r][c].setBackgroundColor(COLORSS[0]);
                tiles[r][c] = 0;
            }
        }

    };

    public void updatedColors(){
        Random random = new Random();

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                int colorz= random.nextInt(3);
                int tileValue = tiles[i][j];
                int color = COLORSS[colorz];
                btn[i][j].setBackgroundColor(color);
                tiles[i][j]=colorz;
            }
        }
    };


}
