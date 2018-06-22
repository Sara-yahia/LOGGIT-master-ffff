package com.example.sara.loggit;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    Marker tahrir,ramsis,abasseya,kobriElKoba,Esaaf,CairoUniv,Giza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        tahrir = mMap.addMarker(new MarkerOptions().position(new LatLng(30.044545, 31.235714)).title("Tahrir Square"));
        ramsis = mMap.addMarker(new MarkerOptions().position(new LatLng(30.061914, 31.245912)).title("Ramsis Square"));
        abasseya = mMap.addMarker(new MarkerOptions().position(new LatLng(30.073675, 31.284974)).title("Abasseya Square"));
        kobriElKoba = mMap.addMarker(new MarkerOptions().position(new LatLng(30.08431, 31.295831)).title("Kobri El-Koba"));
        Esaaf = mMap.addMarker(new MarkerOptions().position(new LatLng(30.053922, 31.237857)).title("Esaaf"));
        CairoUniv = mMap.addMarker(new MarkerOptions().position(new LatLng(30.027563, 31.210674)).title("Cairo Unversity"));
        Giza = mMap.addMarker(new MarkerOptions().position(new LatLng(30.015487, 31.212048)).title("Giza Square"));



        mMap.setOnMarkerClickListener(this);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.044545, 31.235714), 12.0f));

        tahrir.showInfoWindow();
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Intent gotofromto = new Intent(this,FromToActivity.class);
        if(marker.equals(tahrir))
            gotofromto.putExtra("from","0");
        else if (marker.equals(ramsis))
            gotofromto.putExtra("from","1");
        else if (marker.equals(kobriElKoba))
            gotofromto.putExtra("from","5");
        else if (marker.equals(abasseya))
            gotofromto.putExtra("from","10");
        startActivity(gotofromto);
        return true;

    }
}