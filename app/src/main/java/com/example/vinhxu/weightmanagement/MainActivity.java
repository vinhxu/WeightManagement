package com.example.vinhxu.weightmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static Button addUserButton;
    private static ListView listView_userNameItem;
    //Create userProfile lists
    private static List<String> userNameList = new ArrayList<String>();
    private static List<String> startWeightList = new ArrayList<String>();
    private static List<String> targetWeightList = new ArrayList<String>();
    /*
    //Convert lists into String arrays
    private static String[] userNameArray = userNameList.toArray(new String[userNameList.size()]);
    private static String[] startWeightArray = startWeightList.toArray(new String[startWeightList.size()]);
    private static String[] targetWeightArray = targetWeightList.toArray(new String[targetWeightList.size()]);
    */

    //Define requestCode for userName data
    private static int requestCode_returnUserName = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onClick_addUserButton();
        onClick_listView();

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
            //Get all users data from addUser activity
            String userName=data.getStringExtra("userName");
            String startWeight=data.getStringExtra("startWeight");
            String targetWeight=data.getStringExtra("targetWeight");

            //Add users data to lists
            userNameList.add(userName);
            startWeightList.add(startWeight);
            targetWeightList.add(targetWeight);

            /*
            //Convert lists to String arrays
            userNameArray = userNameList.toArray(new String[userNameList.size()]);
            startWeightArray = startWeightList.toArray(new String[startWeightList.size()]);
            targetWeightArray = targetWeightList.toArray(new String[targetWeightList.size()]);
            */

            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userNameList);

            ListView listView = (ListView) findViewById(R.id.listView_userName);
            listView.setAdapter(adapter);
        }
    }


    public void onClick_listView() {
        listView_userNameItem = (ListView)findViewById(R.id.listView_userName);
        listView_userNameItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String userName = userNameList.get(position);
                String startWeight = startWeightList.get(position);
                String targetWeight = targetWeightList.get(position);

                //Toast.makeText(getBaseContext(), userName, Toast.LENGTH_LONG).show();
                Intent intent = new Intent("com.example.vinhxu.weightmanagement.CurrentDetails");
                intent.putExtra("userName", userName);
                intent.putExtra("startWeight", startWeight);
                intent.putExtra("targetWeight", targetWeight);

                startActivity(intent);
            }
        });
    }



}
