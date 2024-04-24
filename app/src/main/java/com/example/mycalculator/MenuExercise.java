package com.example.mycalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MenuExercise extends AppCompatActivity {

    Button btnTransformingButton;
    int num = 1; // Move num outside of methods to preserve its state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menus);

        btnTransformingButton = findViewById(R.id.btnTransformingButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menuexercise, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.mItemChange){
            Toast.makeText(this, "Edit Object Item is clicked", Toast.LENGTH_SHORT).show();
        }

        if (item.getItemId() == R.id.mItemShape) {
            changeShape(); // Call changeShape method when shape item is clicked
        }else if(item.getItemId() == R.id.mItemGenerateNumber){
            generateNum();
        }else if(item.getItemId() == R.id.mItemChangeColor){
            changeColor();
        }else if(item.getItemId() == R.id.mItemSize){
            TextSize();
        }else if(item.getItemId() == R.id.mItemButtonSize){
            ButtonSize();
        }

        if(item.getItemId() == R.id.mItemReset){
            resetButton(); // Call resetButton method when reset item is clicked
        }
        if(item.getItemId() == R.id.mItemExit){
            finish();
        }
        return true;
    }

    private void resetButton() {
        btnTransformingButton.setBackgroundResource(R.drawable.squarebutton);
        btnTransformingButton.setText("");

        ViewGroup.LayoutParams layoutParams = btnTransformingButton.getLayoutParams();
        int width = 100;
        int height = 100;

        float scale = getResources().getDisplayMetrics().density;
        int desiredw = (int)(width*scale + 0.5f);
        int desiredh = (int)(height*scale + 0.5f);
        layoutParams.width = desiredw;
        layoutParams.height = desiredh;

        // Set the updated layout params to the button
        btnTransformingButton.setLayoutParams(layoutParams);

    }

    private void changeShape() {
        if (num == 1) {
            btnTransformingButton.setBackgroundResource(R.drawable.roundedbutton);
        } else if (num == 2) {
            btnTransformingButton.setBackgroundResource(R.drawable.ovalbutton);
        } else if (num == 3) {
            btnTransformingButton.setBackgroundResource(R.drawable.circlebutton);
        }else {
            num = 0; // Reset num to 1 if it exceeds 3
            btnTransformingButton.setBackgroundResource(R.drawable.squarebutton);
        }
        num++;
    }

    private void generateNum(){
        Random rnd = new Random();
        int ran = rnd.nextInt(100);

        btnTransformingButton.setText(String.valueOf(ran));
    }

    private void changeColor(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        btnTransformingButton.setTextColor(color);
    }

    private void TextSize(){
        Random rnd = new Random();
        int size = rnd.nextInt(40);

        btnTransformingButton.setTextSize(size);
    }

    private void ButtonSize(){
        // Generate random width and height between 100dp and 200dp
        int minWidthDp = 100;
        int maxWidthDp = 200;

        // Convert dp to pixels
        int minWidthPx = (int) getResources().getDisplayMetrics().density * minWidthDp;
        int maxWidthPx = (int) getResources().getDisplayMetrics().density * maxWidthDp;

        Random random = new Random();
        int width = random.nextInt(maxWidthPx - minWidthPx) + minWidthPx;
        int height = random.nextInt(maxWidthPx - minWidthPx) + minWidthPx;

        // Get existing layout params
        ViewGroup.LayoutParams layoutParams = btnTransformingButton.getLayoutParams();

        // Update width and height
        layoutParams.width = width;
        layoutParams.height = height;

        // Set the updated layout params to the button
        btnTransformingButton.setLayoutParams(layoutParams);
    }

}
