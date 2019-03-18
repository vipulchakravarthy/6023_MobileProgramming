package com.example.studentdetails;

import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTextVeiwResult;
    private RequestQueue mQueue;
    private EditText Name;
    private EditText Password;
    private Button Login;
    private String user;
    private String pw;
    private  JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPw);

        mTextVeiwResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);
        Login = (Button) findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });

    }

    private void validate(String userName, String pwd) {

        if(userName == "") {

        }
    }
    private void jsonParse() {
        String url = "https://api.myjson.com/bins/16hm3i";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    //when we get the data from json object
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                           jsonArray = response.getJSONArray("students");
                            Log.d("TAG", jsonArray.toString());
                            for(int i =0; i < jsonArray.length(); i++) {
                                JSONObject student = jsonArray.getJSONObject(i);
                                String name = student.getString("Name");
                                String clss = student.getString("Class");
                                String seat = student.getString("Seat");

                                mTextVeiwResult.append(name + ", " + clss + ", " + seat + ".\n\n");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            //when we get the error
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        mQueue.add(request);
    }
}
