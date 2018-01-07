package com.demo.android.bmi_basic;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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


    DialogInterface.OnClickListener btnOKlisten = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            System.out.println("Click Confirm. YA");
        }
    } ;

    DialogInterface.OnClickListener btnlistenCancel = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            System.out.println("Click Cancel.  God");
        }
    };

    private void openOptionsDialog() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(BMIActivity.this);
        alertdialog.setTitle(R.string.dialog_title);
        alertdialog.setMessage(R.string.dialog_message);
        alertdialog.setPositiveButton(R.string.dialog_btn_confirm,btnOKlisten);
        alertdialog.setNegativeButton(R.string.dialog_btn_cancel,btnlistenCancel);
        alertdialog.show();
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
