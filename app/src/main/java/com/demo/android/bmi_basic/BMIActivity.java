package com.demo.android.bmi_basic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

public class BMIActivity extends AppCompatActivity{
    Button button;
    EditText findheight;
    EditText findweight;
    TextView result;
    TextView findsuggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        findViews();
        setListener();
    }
    void findViews(){
        button = (Button) findViewById(R.id.submit);
        findheight = (EditText) findViewById(R.id.height);
        findweight = (EditText) findViewById(R.id.weight);
        result = (TextView) findViewById(R.id.result);
        findsuggest = (TextView) findViewById(R.id.suggest);
    }

    void setListener() {
        button.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                double BMI = calcBMI();
                showResult(BMI);
                openOptionsDialog();
            } catch (Exception e) {
                e.printStackTrace();
                result.setText("");
                findsuggest.setText(" 身高及體重資料皆必須輸入!");
            }
        }
    };

    private void openOptionsDialog() {
        //new AlertDialog.Builder(BMIActivity.this).setTitle(R.string.dialog_title)
    }

    private void showResult(double bmi) {
        DecimalFormat nf = new DecimalFormat("0.00");
        result.setText("Your BMI is "+nf.format(bmi));
        if (bmi > 25) {
            findsuggest.setText(R.string.advice_heavy);
        } else if (bmi < 20) {
            findsuggest.setText(R.string.advice_light);
        } else {
            findsuggest.setText(R.string.advice_average);
        }
    }

    private double calcBMI() {
        double height = Double.parseDouble(findheight.getText()+"") / 100;
        double weight = Double.parseDouble(findweight.getText()+"");
        double BMI = weight / (height * height);
        return BMI;
    }

}
