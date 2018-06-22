package com.example.sara.loggit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class FromToActivity extends AppCompatActivity implements View.OnClickListener {


    Button ramsis, kobrielkoba, tahrir, abasseya, esaaf, cairounv, giza, showtrips, showmap;
    TextView route;

    String from = null, to = null, username;
    boolean fromflag = false, toflag = false, textflag = false;


    RequestQueue requestQueueone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_to);

        Intent recive = getIntent();
        username = recive.getStringExtra("UsernameID");
        from = recive.getStringExtra("from");
        if (from != null) {
            Toast.makeText(this, "your station has been added", Toast.LENGTH_SHORT).show();
            fromflag = true;
        }
        else
            Toast.makeText(this, "Welcome! :)", Toast.LENGTH_SHORT).show();

        ramsis = (Button) findViewById(R.id.fromto_ramsis_Button);
        tahrir = (Button) findViewById(R.id.fromto_TahrirSqrBtn_Button);
        kobrielkoba = (Button) findViewById(R.id.fromto_kobriElKoba_Button);
        abasseya = (Button) findViewById(R.id.fromto_Abasseya_Button);
        esaaf = (Button) findViewById(R.id.fromto_Esaaf_Button);
        cairounv = (Button) findViewById(R.id.fromto_cairoUnversity_Button);
        giza = (Button) findViewById(R.id.fromto_giza_Button);
        showtrips = (Button) findViewById(R.id.fromto_ShowTrips_Button);
        showmap = (Button) findViewById(R.id.fromto_ShowMap_Button);
        route = (TextView) findViewById(R.id.fromto_route_TextView);

        ramsis.setOnClickListener(this);
        tahrir.setOnClickListener(this);
        kobrielkoba.setOnClickListener(this);
        abasseya.setOnClickListener(this);
        esaaf.setOnClickListener(this);
        cairounv.setOnClickListener(this);
        giza.setOnClickListener(this);
        showtrips.setOnClickListener(this);
        showmap.setOnClickListener(this);


        requestQueueone = Volley.newRequestQueue(FromToActivity.this);
    }

    @Override
    public void onClick(View view) {

        if (view == ramsis) {
            if (!fromflag) {
                from = "1";
                fromflag = true;
            } else if (!toflag) {
                to = "1";
                toflag = true;
            } else if (toflag && to != null) {
                toflag = false;
                to = null;
            } else if (fromflag && from != null) {
                fromflag = false;
                from = null;
            }
        } else if (view == kobrielkoba) {
            if (!fromflag) {
                from = "11";
                fromflag = true;
            } else if (!toflag) {
                to = "11";
                toflag = true;
            } else if (toflag && to != null) {
                toflag = false;
                to = null;
            } else if (fromflag && from != null) {
                fromflag = false;
                from = null;
            }
        } else if (view == tahrir) {
            if (!fromflag) {
                from = "0";
                fromflag = true;
            } else if (!toflag) {
                to = "0";
                toflag = true;
            } else if (toflag && to != null) {
                toflag = false;
                to = null;
            } else if (fromflag && from != null) {
                fromflag = false;
                from = null;
            }
        } else if (view == abasseya) {
            if (!fromflag) {
                from = "10";
                fromflag = true;
            } else if (!toflag) {
                to = "10";
                toflag = true;
            } else if (toflag && to != null) {
                toflag = false;
                to = null;
            } else if (fromflag && from != null) {
                fromflag = false;
                from = null;
            }
        } else if (view == esaaf) {
            if (!fromflag) {
                from = "2";
                fromflag = true;
            } else if (!toflag) {
                to = "2";
                toflag = true;
            } else if (toflag && to != null) {
                toflag = false;
                to = null;
            } else if (fromflag && from != null) {
                fromflag = false;
                from = null;
            }
        } else if (view == cairounv) {
            if (!fromflag) {
                from = "6";
                fromflag = true;
            } else if (!toflag) {
                to = "6";
                toflag = true;
            } else if (toflag && to != null) {
                toflag = false;
                to = null;
            } else if (fromflag && from != null) {
                fromflag = false;
                from = null;
            }
        } else if (view == giza) {
            if (!fromflag) {
                from = "7";
                fromflag = true;
            } else if (!toflag) {
                to = "7";
                toflag = true;
            } else if (toflag && to != null) {
                toflag = false;
                to = null;
            } else if (fromflag && from != null) {
                fromflag = false;
                from = null;
            }
        } else if (view == showtrips) {


            String requestURL = String.format("https://sbs-egypt.eu-gb.mybluemix.net/search?from=" + from + "&to=" + to);

            StringRequest stringRequeston = new StringRequest(Request.Method.GET, requestURL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        Log.d("ResponsFromToeMsg", response);

                        if(response.contains("There is no lines crosses by these two stations")) {
                            Toast.makeText(FromToActivity.this, response, Toast.LENGTH_SHORT).show();
                            fromflag = toflag = false;
                            from = to = null;


                        }
                        else
                        {
                            Intent gototriplist = new Intent(FromToActivity.this, TripsListActivity.class);
                            gototriplist.putExtra("usernameID", username);
                            gototriplist.putExtra("from", from);
                            gototriplist.putExtra("to", to);

                            gototriplist.putExtra("response", response);
                            startActivity(gototriplist);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(FromToActivity.this, " Poor Connection",
                            Toast.LENGTH_LONG).show();
                }

            });
            requestQueueone.add(stringRequeston);
        } else if (view == showmap) {
            Intent gotomap = new Intent(this,MapsActivity.class);
            startActivity(gotomap);
        }
        if (fromflag && toflag)
            textflag = true;
        else {
            textflag = false;
            route.setText("");
        }
        if (textflag) {
            showtrips.setEnabled(true);
            route.setText("From " + from + " To " + to);
        }


    }
}
