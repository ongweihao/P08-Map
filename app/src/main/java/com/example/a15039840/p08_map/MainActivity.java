package com.example.a15039840.p08_map;

import android.Manifest;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3;
    private GoogleMap map;
    Spinner spn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        spn = (Spinner) findViewById(R.id.spinner);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;


                LatLng poi_NORTH = new LatLng(1.4410731, 103.7698811);
                final Marker cp = map.addMarker(new
                        MarkerOptions()
                        .position(poi_NORTH)
                        .title("HQ-North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                                            .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_big_on)));


                LatLng poi_CENTRAL = new LatLng(1.3039744, 103.8285632);
                final Marker rp = map.addMarker(new
                        MarkerOptions()
                        .position(poi_CENTRAL)
                        .title("HQ-Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_EAST = new LatLng(1.3451042, 103.9200074);
                final Marker east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_EAST)
                        .title("HQ-East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\"\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                LatLng Singapore = new LatLng(1.3521, 103.8198);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(Singapore,12));



                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if(marker.equals(cp)){
                            Toast.makeText(getApplicationContext(),"Title: " + cp.getTitle(), Toast.LENGTH_LONG).show();
                        } else if(marker.equals(rp)){
                            Toast.makeText(getApplicationContext(),"Title: " + rp.getTitle(), Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(getApplicationContext(),"Title: " + east.getTitle(), Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }
                });


                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
                if(permissionCheck == PermissionChecker.PERMISSION_GRANTED){
                    map.setMyLocationEnabled(true);
                } else{
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng poi_NORTH = new LatLng(1.4410731, 103.7698811);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_NORTH,15));
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng poi_CENTRAL = new LatLng(1.3039744, 103.8285632);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_CENTRAL,15));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    LatLng poi_EAST = new LatLng(1.3451042, 103.9200074);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_EAST,15));
                }
            }
        });

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spn.getSelectedItemPosition()==0){
                    LatLng poi_NORTH = new LatLng(1.4410731, 103.7698811);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_NORTH,15));
                } else if(spn.getSelectedItemPosition()==1){
                    LatLng poi_EAST = new LatLng(1.3451042, 103.9200074);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_EAST,15));
                } else {
                    LatLng poi_CENTRAL = new LatLng(1.3039744, 103.8285632);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_CENTRAL,15));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
