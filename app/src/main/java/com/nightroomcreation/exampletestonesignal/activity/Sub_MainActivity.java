package com.nightroomcreation.exampletestonesignal.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nightroomcreation.exampletestonesignal.R;

public class Sub_MainActivity extends AppCompatActivity {

    TextView txtAdditionalData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub__main);

        txtAdditionalData = (TextView)findViewById(R.id.txt_additional_data);
        txtAdditionalData.setText(getIntent().getStringExtra("content_data"));
    }
}
