package com.example.vinhxu.weightmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static Button addUserButton;
    private static List<String> userNameList = new ArrayList<String>();
    private static String[] userNameArray = userNameList.toArray(new String[userNameList.size()]);
    private static int requestCode_returnUserName = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onClick_addUserButton();

    }

    public void onClick_addUserButton() {
        addUserButton = (Button)findViewById(R.id.button);
        addUserButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.vinhxu.weightmanagement.AddUser");
                        startActivityForResult(intent, requestCode_returnUserName);// Activity is started with requestCode 2
                        //startActivity(intent);

                    }
                }
        );
    }

    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==requestCode_returnUserName)
        {
            String message=data.getStringExtra("MESSAGE");
            userNameList.add(message);
            userNameArray = userNameList.toArray(new String[userNameList.size()]);

            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userNameArray);

            ListView listView = (ListView) findViewById(R.id.listView_userName);
            listView.setAdapter(adapter);
        }
    }


}
