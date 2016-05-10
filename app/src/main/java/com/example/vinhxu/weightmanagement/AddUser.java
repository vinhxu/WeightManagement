package com.example.vinhxu.weightmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddUser extends AppCompatActivity {

    private static Button button_addUserDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        onClick_addUserButton();
    }

    public void onClick_addUserButton() {
        button_addUserDone = (Button) findViewById(R.id.button_addUserDone);
        button_addUserDone.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent intent = new Intent("com.example.lnknguyen.weightmanagement.AddUser");
                        //startActivity(intent);
                        finish();
                    }
                }
        );
    }
}
