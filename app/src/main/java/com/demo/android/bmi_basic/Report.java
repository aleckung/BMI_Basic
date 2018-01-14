package com.demo.android.bmi_basic;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Report extends AppCompatActivity {

    private static final String TAG_Report = "BMI_Report";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // findView();
        setContentView(R.layout.activity_report);
        Log.d(TAG_Report,"Report_onCreate()");
        showResult(calcBMI());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG_Report,"Report_onStart();");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG_Report,"Report_onStop();");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG_Report,"Report_onPause();");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG_Report,"Report_onResume();");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG_Report,"Report_onRestart();");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG_Report,"Report_onDestroy();");
    }

    private double calcBMI() {
        Bundle bundle = this.getIntent().getExtras();
        double height = Double.parseDouble(bundle.getString("KEY_HEIGHT")) / 100;
        double weight = Double.parseDouble(bundle.getString("KEY_WEIGHT"));
        double BMI = weight / (height * height);
        return BMI;
    }

    private void showResult(double bmi) {
        DecimalFormat nf = new DecimalFormat("0.00");
        TextView findresult = (TextView) findViewById(R.id.result);
        findresult.setText("Your BMI is "+nf.format(bmi));
        TextView findsuggest = (TextView) findViewById(R.id.suggest);
        if (bmi > 25) {
            showNotification(bmi);
            findsuggest.setText(R.string.advice_heavy);
        } else if (bmi < 20) {
            findsuggest.setText(R.string.advice_light);
        } else {
            findsuggest.setText(R.string.advice_average);
        }
    }

    protected void showNotification(double BMI) {
        NotificationManager barManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getActivity(Report.this,0,
                new Intent(Report.this, BMIActivity.class),
                PendingIntent.FLAG_CANCEL_CURRENT);
        Notification barMsg = new Notification.Builder(this)
                .setContentText("通知監督人")
                .setContentTitle("您的 BMI 值過高")
                .setSmallIcon(R.mipmap.alec_android)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        barManager.notify(1,barMsg);
    }

}
