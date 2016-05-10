package com.example.vinhxu.weightmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CurrentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_details);

        TextView currentDetails_user = (TextView) findViewById(R.id.textView_user);
        TextView currentDetails_startWeight = (TextView) findViewById(R.id.textView_startWeight);
        TextView currentDetails_targetWeight = (TextView) findViewById(R.id.textView_targetWeight);

        Bundle receivedData = getIntent().getExtras();

        String userName = receivedData.getString("userName");
        String startWeight = receivedData.getString("startWeight");
        String targetWeight = receivedData.getString("targetWeight");

        currentDetails_user.setText(userName);
        currentDetails_startWeight.setText(startWeight);
        currentDetails_targetWeight.setText(targetWeight);
    }
}
