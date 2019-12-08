package com.example.myapplication.actitivies;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.R;
import com.example.myapplication.actitivies.adapter.FetchURL;
import com.example.myapplication.actitivies.adapter.TaskLoadedCallback;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback {

    private GoogleMap mMap;
    private final int ACCESS_LOCATION_PERMISSION_CODE = 44;
    private TextView address;
    private GoogleApiClient googleApiClient;
    private FusedLocationProviderClient  fusedLocationClient;
    private MarkerOptions place1, place2;
    private Polyline currentPolyline;
    Button getDirection;
    EditText point1, point2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        point1= (EditText) findViewById(R.id.input_search);
        point2= (EditText) findViewById(R.id.input_search1);

       getDirection = findViewById(R.id.btnGetDirection);
       getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                LatLng p1= new LatLng(getCoordenates(point1.getText().toString()).getLatitude(), getCoordenates(point1.getText().toString()).getLongitude());
                place1 = new MarkerOptions().position(p1).title(point1.getText().toString());
                place2 = new MarkerOptions().position(new LatLng(getCoordenates(point2.getText().toString()).getLatitude(), getCoordenates(point2.getText().toString()).getLongitude())).title(point2.getText().toString());
                mMap.addMarker(place1);
                mMap.addMarker(place2);

                new FetchURL(MapsActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
                mMap.moveCamera( CameraUpdateFactory.newLatLngZoom( p1, 15 ) );
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        address = (TextView) findViewById( R.id.address );
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

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

        //Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(4.570868, -74.2973328);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Colombia"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    public void onFindAddressClicked( View view ) {
        startFetchAddressIntentService();
        showMyLocation();
    }

    public void startFetchAddressIntentService()
    {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if ( location != null )
                        {
                            AddressResultReceiver addressResultReceiver = new AddressResultReceiver( new Handler() );
                            addressResultReceiver.setAddressResultListener( new AddressResultListener()
                            {
                                @Override
                                public void onAddressFound( final String address )
                                {
                                    runOnUiThread( new Runnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            MapsActivity.this.address.setText( address );
                                            MapsActivity.this.address.setVisibility( View.VISIBLE );
                                        }
                                    } );


                                }
                            } );
                            Intent intent = new Intent( MapsActivity.this, FetchAddressIntentService.class );
                            intent.putExtra( FetchAddressIntentService.RECEIVER, addressResultReceiver );
                            intent.putExtra( FetchAddressIntentService.LOCATION_DATA_EXTRA, location );
                            startService( intent );
                        }
                    }
                });
    }

    public void showMyLocation() {
        if ( mMap != null )
        {

            String[] permissions = { android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION };
            if (hasPermissions( this, permissions ) )
            {
                mMap.setMyLocationEnabled( true );
                System.err.println("_________________________ Enter function ___________");
                FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    addMarkerAndZoom( location, "My Location", 15 );
                                }
                            }
                        });
            }
            else{
                System.err.println("_________________________ not Enter function ___________");
                ActivityCompat.requestPermissions( this, permissions, ACCESS_LOCATION_PERMISSION_CODE );
            }
        }
    }

    public static boolean hasPermissions(Context context, String[] permissions )
    {
        for ( String permission : permissions )
        {
            if ( ContextCompat.checkSelfPermission( context, permission ) == PackageManager.PERMISSION_DENIED )
            {
                return false;
            }
        }
        return true;
    }

    public void addMarkerAndZoom( Location location, String title, int zoom  )
    {
        LatLng myLocation = new LatLng( location.getLatitude(), location.getLongitude() );
        mMap.addMarker( new MarkerOptions().position( myLocation ).title( title ) );
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom( myLocation, zoom ) );
    }

    @Override
    public void onRequestPermissionsResult( int requestCode, @NonNull String[] permissions,
                                            @NonNull int[] grantResults )
    {
        for ( int grantResult : grantResults )
        {
            if ( grantResult == -1 )
            {
                return;
            }
        }
        switch ( requestCode )
        {
            case ACCESS_LOCATION_PERMISSION_CODE:
                showMyLocation();
                break;
            default:
                super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        }
    }
    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }
    private Address getCoordenates(String point){
        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> list = new ArrayList<>();
        Address address=null;

        try{
            list = geocoder.getFromLocationName(point, 1);
        }catch (IOException e){
            Log.e("ERROR","geoLocate: IOException: " + e.getMessage() );
        }
        if(list.size() > 0) {
            address = list.get(0);
        }
        return address;
    }

    private String getUrl(String origin, String dest, String directionMode) {
        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> list = new ArrayList<>();
        List<Address> list1 = new ArrayList<>();
        Address addressIni=null;
        Address addressFin=null;
        try{
            list = geocoder.getFromLocationName(origin, 1);
        }catch (IOException e){
            Log.e("ERROR","geoLocate: IOException: " + e.getMessage() );
        }
        try{
            list1 = geocoder.getFromLocationName(dest, 1);
        }catch (IOException e){
            Log.e("ERROR","geoLocate: IOException: " + e.getMessage() );
        }
        if(list.size() > 0) {
            addressIni = list.get(0);
        }
        if(list1.size() > 0) {
            addressFin = list1.get(0);
        }


            // Origin of route
        String str_origin = "origin=" + addressIni.getLatitude() + "," + addressIni.getLongitude();
        // Destination of route
        String str_dest = "destination=" + addressFin.getLatitude() + "," + addressFin.getLongitude();
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }

}
