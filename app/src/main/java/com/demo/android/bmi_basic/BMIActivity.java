package com.demo.android.bmi_basic;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class BMIActivity extends AppCompatActivity{
    Button button;
    EditText findheight;
    EditText findweight;
//    TextView result;
//    TextView findsuggest;
    private static final String TAG = "BMI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        Log.d(TAG,"onCreate();");
        findViews();
        setListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart();");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop();");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause();");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume();");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart();");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy();");
    }

    void findViews(){
        button = (Button) findViewById(R.id.submit);
        findheight = (EditText) findViewById(R.id.height);
        findweight = (EditText) findViewById(R.id.weight);
//        result = (TextView) findViewById(R.id.result);
//        findsuggest = (TextView) findViewById(R.id.suggest);
    }

    void setListener() {
        button.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                Intent intent = new Intent(BMIActivity.this, Report.class);
                Bundle bundle = new Bundle();
                bundle.putString("KEY_HEIGHT", findheight.getText().toString());
                bundle.putString("KEY_WEIGHT", findweight.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
//                double BMI = calcBMI();
//                showResult(BMI);
//                openOptionsDialog();
            } catch (Exception e) {
                e.printStackTrace();
//                result.setText("");
//                findsuggest.setText(" 身高及體重資料皆必須輸入!");
            }
        }
    };

    private void openOptionsDialog() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(BMIActivity.this);
        alertdialog.setTitle(R.string.dialog_title);
        alertdialog.setMessage(R.string.dialog_message);
        alertdialog.setPositiveButton(R.string.dialog_btn_confirm,btnOKlisten);
        alertdialog.setNeutralButton(R.string.homepage_Label,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri =Uri.parse("http://tw.yahoo.com/");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                }
        );
        alertdialog.setNegativeButton(R.string.dialog_btn_cancel,btnlistenCancel);
        alertdialog.show();
    }

    DialogInterface.OnClickListener btnOKlisten = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(BMIActivity.this, R.string.toast_confirm,Toast.LENGTH_SHORT).show();
//            Uri uri = Uri.parse("http://tw.yahoo.com/");
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);
        }
    } ;

    DialogInterface.OnClickListener btnlistenCancel = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            System.out.println("Click Cancel.  God");
        }
    };




}
