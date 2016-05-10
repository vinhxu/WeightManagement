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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onClick_addUserButton();
        //String[] userNameArray = {"vinhxu","lnk","Danh","Khanh"};
        List<String> userNameList = new ArrayList<String>();

        userNameList.add("vinhxu");
        userNameList.add("anybody");
        userNameList.add("Danh");

        String[] userNameArray = userNameList.toArray(new String[userNameList.size()]);



        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userNameArray);

        ListView listView = (ListView) findViewById(R.id.listView_userName);
        listView.setAdapter(adapter);
    }

    public void onClick_addUserButton() {
        addUserButton = (Button)findViewById(R.id.button);
        addUserButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.vinhxu.weightmanagement.AddUser");
                        startActivity(intent);

                    }
                }
        );
    }
}
