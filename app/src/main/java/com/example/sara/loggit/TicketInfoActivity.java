package com.example.sara.loggit;

/**
 * Created by Miada on 05-Feb-18.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.util.Log;

import android.widget.ImageView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class TicketInfoActivity extends AppCompatActivity  {
    String from,to,qr_id , username , tripId;
    TextView fromtv,totv;
    ImageView imageView;
    Thread thread ;
    public final static int QRcodeWidth = 500 ;
    Bitmap bitmap ;

    Button firstFragment, secondFragment;
    RequestQueue requestQueue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketinfo);
        imageView = (ImageView)findViewById(R.id.imageView);

        Intent receiver = getIntent();
        username = receiver.getStringExtra("UsernameID");
        from = receiver.getStringExtra("from");
        to = receiver.getStringExtra("to");
        tripId = receiver.getStringExtra("trip_id");



// get the reference of Button's
        firstFragment = (Button) findViewById(R.id.first);
        secondFragment = (Button) findViewById(R.id.secondFragment);

        fromtv =(TextView) findViewById(R.id.from);
        totv = (TextView) findViewById(R.id.to);


        requestQueue = Volley.newRequestQueue( TicketInfoActivity.this);

// perform setOnClickListener event on First Button
        firstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tripId",username + tripId +from+ to );
// load First Fragment
String requestURL =
        String.format("https://sbs-egypt.eu-gb.mybluemix.net/booking?user=" + username + "&trip=" + tripId +"&from=" +from +
                 "&to=" +to);
      StringRequest stringRequest = new StringRequest(Request.Method.GET, requestURL, new Response.Listener<String>() {@Override
                    public void onResponse(String response) {
                        try {
                            Log.d("RMsg",response);
//JSONObject jsonObj = new JSONObject(jsonStr);

// JSONArray contacts = jsonObj.getJSONArray("/////////");

                            try {
                                JSONArray contacts = new JSONArray(response);

                                for(int i = 0; i < contacts.length(); i++)
                                {
                                    JSONObject c = contacts.getJSONObject(i);
                                    qr_id = c.getString("_id");

                                    String leavingtime = c.getString("ltime");
                                    int status = c.getInt("status");

                                }
                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }


                            loadFragment(new FirstFragment());
                            Log.d("IdMsg",qr_id);

                            try {
                                bitmap = TextToImageEncode(qr_id);

                                imageView.setImageBitmap(bitmap);

                            } catch (WriterException e) {
                                e.printStackTrace();
                            }




                            fromtv.setText(from);
                            totv.setText(to);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(TicketInfoActivity.this, " ticket failed!",
                                Toast.LENGTH_LONG).show() ;
                    }
                }) ;
                requestQueue.add(stringRequest) ;






            }
        });


// perform setOnClickListener event on Second Button
        secondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// load Second Fragment
                loadFragment(new SecondFragment());
            }
        });




    }




    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.QRCodeBlackColor):getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }


    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }


}


