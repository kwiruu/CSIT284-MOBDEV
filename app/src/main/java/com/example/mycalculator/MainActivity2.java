package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    MaterialButton buttonC;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;
    TextView equationText, answerText;

    private final getResult calculator = new getResult();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        answerText = findViewById(R.id.equationView);
        equationText = findViewById(R.id.answerID);

        assignId(buttonC,R.id.deleteBtn);
        assignId(buttonDivide,R.id.divideBtn);
        assignId(buttonMultiply,R.id.multiplyBtn);
        assignId(buttonPlus,R.id.plusBtn);
        assignId(buttonMinus,R.id.minusBtn);
        assignId(buttonEquals,R.id.equalsBtn);
        assignId(button0,R.id.zeroBtn);
        assignId(button1,R.id.oneBtn);
        assignId(button2,R.id.twoBtn);
        assignId(button3,R.id.threeBtn);
        assignId(button4,R.id.fourBtn);
        assignId(button5,R.id.fiveBtn);
        assignId(button6,R.id.sixBtn);
        assignId(button7,R.id.sevenBtn);
        assignId(button8,R.id.eightBtn);
        assignId(button9,R.id.nineBtn);
        assignId(buttonAC,R.id.clearBtn);
        assignId(buttonDot,R.id.dotBtn);
    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();

        String rawExpression = equationText.getText().toString();
        ArrayList<String> termsList = Splitter.getTermsList(rawExpression);
        StringBuilder newExpression = new StringBuilder(rawExpression);

        String lastTerm = (termsList.size() > 0) ? termsList.get(termsList.size() - 1) : "";

        switch(buttonText) {
            case "%":
                System.out.println("wala pakoy set text hhuhuhu");
                break;
            case "=":
                String answer = calculator.calculate(rawExpression);
                String sequentialAnswer = calculator.sequentially(String.valueOf(newExpression));
                newExpression = new StringBuilder(answer);
                equationText.setText(rawExpression);
                answerText.setText(sequentialAnswer);
                break;

            case "+":
            case "-":
                if (lastTerm.contains("+") || lastTerm.contains("-")) {
                    newExpression.deleteCharAt(newExpression.length() - 1);
                }
                newExpression.append(buttonText);
                break;
            case "x":
            case "/":
                try{
                    System.out.println("times or divide");
                    if (Character.isDigit(lastTerm.charAt(0)))
                        newExpression.append(buttonText);
                    System.out.println("if is true");
                }
                catch (StringIndexOutOfBoundsException e){
                    return;
                }
                break;
            case ".":
                boolean thereIsInput = !termsList.isEmpty();
                boolean lastTermHasDot = lastTerm.contains(".");
                if (thereIsInput && !lastTermHasDot)
                    newExpression.append(buttonText);
                break;

            case "AC":
                newExpression.delete(0, newExpression.length());
                answerText.setText("");
                equationText.setText("0");
                break;
            case "C":
                if (newExpression.length() > 0)
                    newExpression.deleteCharAt(newExpression.length() - 1);
                break;
            default:
                newExpression.append(buttonText);
                sequentialAnswer = calculator.sequentially(String.valueOf(newExpression));
                answerText.setText(sequentialAnswer);
                break;
        }

        equationText.setText(newExpression);

    }

}