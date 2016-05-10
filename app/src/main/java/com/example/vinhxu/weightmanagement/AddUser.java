package com.example.vinhxu.weightmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddUser extends AppCompatActivity {

    private static Button button_addUserDone;
    private static int requestCode_returnUserName = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        onClick_addUserButtonDone();
    }

    public void onClick_addUserButtonDone() {
        button_addUserDone = (Button) findViewById(R.id.button_addUserDone);
        button_addUserDone.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText editText_userName = (EditText) findViewById(R.id.editText_userName);
                        EditText editText_startWeight = (EditText) findViewById(R.id.editText_startWeight);
                        EditText editText_targetWeight = (EditText) findViewById(R.id.editText_targetWeight);

                        String userName= editText_userName.getText().toString();
                        String startWeight= editText_startWeight.getText().toString();
                        String targetWeight= editText_targetWeight.getText().toString();

                        Intent intent=new Intent();
                        intent.putExtra("userName",userName);
                        intent.putExtra("startWeight",startWeight);
                        intent.putExtra("targetWeight",targetWeight);

                        setResult(requestCode_returnUserName,intent);

                        finish();
                    }
                }
        );
    }
}
