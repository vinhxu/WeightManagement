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
        Bundle receivedData = getIntent().getExtras();
        String userName = receivedData.getString("userName");
        currentDetails_user.setText(userName);
    }
}
