package com.waterhub.waterhub;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Faza Zulfika P P on 12/21/2017.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;
    private Circle circle1, circle2, circle3;

    private static final LatLng Loc1 = new LatLng(-7.334991, 112.732227);
    private static final LatLng Loc2 = new LatLng(-7.331198, 112.738011);
    private static final LatLng Loc3 = new LatLng(-7.339625, 112.739909);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        //add marker

        Marker mLoc1 = mMap.addMarker(new MarkerOptions()
                .position(Loc1)
                .title("Jual Air Prigrn")
        );

        Marker mLoc2 = mMap.addMarker(new MarkerOptions()
                .position(Loc2)
                .title("Isi Ulang Air")
        );

        Marker mLoc3 = mMap.addMarker(new MarkerOptions()
                .position(Loc3)
                .title("Jual Air Bersih")
        );

        CircleOptions circleOptions1 = new CircleOptions()
                .center(Loc1)
                .radius(500)
                .strokeWidth(1)
                .strokeColor(Color.argb(128, 0, 0, 255))
                .fillColor(Color.argb(128, 0, 0, 255));


        CircleOptions circleOptions2 = new CircleOptions()
                .center(Loc2)
                .radius(500)
                .strokeWidth(1)
                .strokeColor(Color.argb(128, 0, 0, 255))
                .fillColor(Color.argb(128, 0, 0, 255));

        CircleOptions circleOptions3 = new CircleOptions()
                .center(Loc3)
                .radius(500)
                .strokeWidth(1)
                .strokeColor(Color.argb(128, 0, 0, 255))
                .fillColor(Color.argb(128, 0, 0, 255));

        circle1 = mMap.addCircle(circleOptions1);
        circle2 = mMap.addCircle(circleOptions2);
        circle3 = mMap.addCircle(circleOptions3);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Loc1, 15));
        mMap.setOnMarkerClickListener(this);

        map.setOnCircleClickListener(new GoogleMap.OnCircleClickListener() {
            @Override
            public void onCircleClick(Circle circle) {

            }
        });



    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
