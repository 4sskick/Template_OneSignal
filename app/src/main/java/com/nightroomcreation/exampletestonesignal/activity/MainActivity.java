package com.nightroomcreation.exampletestonesignal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nightroomcreation.exampletestonesignal.GlobalFunction;
import com.nightroomcreation.exampletestonesignal.GlobalVariable;
import com.nightroomcreation.exampletestonesignal.R;

public class MainActivity extends AppCompatActivity {

    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = (TextView) findViewById(R.id.txt_result);
        txtResult.setText(GlobalFunction.getOneSignalUserId_pref());


        //config after notification clicked move into next activity
        if (GlobalVariable.activityStarted && getIntent() != null && (getIntent().getFlags() & Intent.FLAG_ACTIVITY_NEW_TASK) != 0){
            finish();
            return;
        }

        GlobalVariable.activityStarted = true;
    }
}
