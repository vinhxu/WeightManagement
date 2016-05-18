package com.example.vinhxu.weightmanagement;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_details);

        TextView currentDetails_user = (TextView) findViewById(R.id.textView_user);

        Bundle receivedData = getIntent().getExtras();

        String userName = receivedData.getString("userName");
        currentDetails_user.setText(userName);

        RequestPackage p = new RequestPackage();
        p.setMethod("GET");
        p.setUri("https://fiery-torch-8721.firebaseio.com/" + userName + ".json");


        HttpClientTask task = new HttpClientTask();
        task.execute(p);
    }

    private class HttpClientTask extends AsyncTask<RequestPackage, String, String> {

        @Override
        protected String doInBackground(RequestPackage... params) {
            String content =  HttpManager.getData(params[0]);
            return content;


        }

        @Override
        protected void onPostExecute(String s) {
            try {

                JSONObject obj = new JSONObject(s);
                TextView currentDetails_startWeight = (TextView) findViewById(R.id.textView_startWeight);
                TextView currentDetails_targetWeight = (TextView) findViewById(R.id.textView_targetWeight);

                currentDetails_startWeight.setText(obj.getString("startWeight"));
                currentDetails_targetWeight.setText(obj.getString("targetWeight"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
}
