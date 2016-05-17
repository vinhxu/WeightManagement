package com.example.vinhxu.weightmanagement;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static Button addUserButton;
    private static ListView listView_userNameItem;
    //Create userProfile lists
    private static List<String> userNameList = new ArrayList<String>();

    //Define requestCode for userName data
    private static int requestCode_returnUserName = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add onClick methods
        onClick_addUserButton();
        onClick_listView();
        loadUsers();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //loadUsers();

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

            //Add users data to lists
            userNameList.add(userName);

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

                //Toast.makeText(getBaseContext(), userName, Toast.LENGTH_LONG).show();
                Intent intent = new Intent("com.example.vinhxu.weightmanagement.CurrentDetails");
                intent.putExtra("userName", userName);

                startActivity(intent);
            }
        });
    }

    public void loadUsers() {
        //Create RequestPackage object
        RequestPackage p = new RequestPackage();
        p.setMethod("GET");
        p.setUri("https://fiery-torch-8721.firebaseio.com/.json");

        //Create HttpClientTask object
        HttpClientTask task = new HttpClientTask();
        task.execute(p);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userNameList);

        ListView listView = (ListView) findViewById(R.id.listView_userName);
        listView.setAdapter(adapter);

    }

    private class HttpClientTask extends AsyncTask<RequestPackage, String, String> {

        @Override
        protected String doInBackground(RequestPackage... params) {
            String content = HttpManager.getData(params[0]);

            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject obj = new JSONObject(s);
                JSONArray ar = obj.names();

                for (int i = 0; i < ar.length(); i++) {
                    userNameList.add(ar.getString(i));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

}
