package com.example.sara.loggit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class TripsListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView TripList;
    ArrayList<String> ids;
    ArrayList<String> time;

    ArrayList<Trip> allTrips = new ArrayList<>();
    String Ticketno = "-1",username,from,to ;
    RequestQueue requestQueue ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips_list);

        ListView listView=(ListView)findViewById(R.id.timelistView);
        String [] items ={"2 mins", "5 mins", "6 mins","7 mins","9 mins","11 mins"};

        time = new ArrayList<>(Arrays.asList(items));
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, time);


        listView.setAdapter(adapter);


        Intent intent = getIntent();
        String response = intent.getStringExtra("response");
        username = intent.getStringExtra("usernameID");
        from = intent.getStringExtra("from");
        to = intent.getStringExtra("to");


        Log.d("yarab", response);


        //code for transforming the string response -json response- into obejects of trip class
        try {
            JSONArray jsonArray = new JSONArray(response);

            for(int counter=0 ; counter<jsonArray.length();counter++)
                {
                JSONObject childJsonObject = jsonArray.getJSONObject(counter);

                int trip_i = childJsonObject.getInt("trip_id");
               String trip_id= String.valueOf(trip_i);

                int id = childJsonObject.getInt("bus_id");
                String leavingtime = childJsonObject.getString("ltime");
                int status = childJsonObject.getInt("status");

                Trip trip = new Trip(id,leavingtime,status , trip_id);

                allTrips.add(trip);
                }
            }
        catch (JSONException e)
        {
                e.printStackTrace();
        }

        ids = new ArrayList<>();
        for(int i=0;i < allTrips.size();i++)
        {
            ids.add(String.valueOf(allTrips.get(i).getId()));
        }

        TripList = (ListView) findViewById(R.id.tripList_Trips_ListView);

        ArrayAdapter myArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ids);
        TripList.setAdapter(myArrayAdapter);
        TripList.setOnItemClickListener(this);

        Log.d("responseMsg", response);

        }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        /*
        String requestURL = String.format("URL");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("Responsecomment",response);
*/
                    Intent
                            gotoTicketInfo = new Intent(TripsListActivity.this ,TicketInfoActivity.class);
                    gotoTicketInfo.putExtra("UsernameID",username);
                    gotoTicketInfo.putExtra("Ticketno",ids.get(i));
                    gotoTicketInfo.putExtra("From",from);
                    gotoTicketInfo.putExtra("to",to);
                    gotoTicketInfo.putExtra("trip_id", allTrips.get(0).toString());
        Log.d("resfrom", from);
        Log.d("resul", from + to + allTrips.get(0)  );


                    startActivity(gotoTicketInfo);
/*
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(TripsListActivity.this, " Poor Connection",
                        Toast.LENGTH_LONG).show() ;
            }
        }) ;
        requestQueue.add(stringRequest) ; */



    }
}
