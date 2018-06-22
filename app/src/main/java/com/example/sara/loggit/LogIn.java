package com.example.sara.loggit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    EditText usernameET, passwordET;
    Button login;
    TextView msg;
    RequestQueue requestQueue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameET = (EditText) findViewById(R.id.login_username_EditText);
        passwordET = (EditText) findViewById(R.id.login_password_EditText);

        login = (Button) findViewById(R.id.login_login_Button);
        msg = (TextView) findViewById(R.id.login_msg_TextView);

        login.setOnClickListener(this);
        requestQueue = Volley.newRequestQueue(LogIn.this) ;
    }


    @Override
    public void onClick(View view) {
        String password = passwordET.getText().toString();
        String username = usernameET.getText().toString();
        String requestURL = String.format("https://sbs-egypt.eu-gb.mybluemix.net/login?user=" + username + "&pass=" + password);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("ResponseMsg",response);
                    if(response.equals("0")){
                        Intent gotofromto = new Intent(LogIn.this ,FromToActivity.class);
                        gotofromto.putExtra("UsernameID",response);
                        startActivity(gotofromto);}
                    else {
                        Toast.makeText(LogIn.this, " Please Check Your ID OR Password !",
                                Toast.LENGTH_LONG).show() ;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LogIn.this, " login failed!",
                        Toast.LENGTH_LONG).show() ;
            }
        }) ;
        requestQueue.add(stringRequest) ;


    }
}


