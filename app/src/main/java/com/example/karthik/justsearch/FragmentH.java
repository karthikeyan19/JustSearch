package com.example.karthik.justsearch;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Karthik on 08-Jun-16.
 */
public class FragmentH extends Fragment implements View.OnClickListener,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener {
    Button hospital, atm, college, school, emOffice, restaurant;

    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private double latitude=0,longitude=0;
    private Location mLocation;
    private TextView locTextView;
    private FloatingActionButton floatingActionButton;
    private static final String API="AIzaSyDEk97Sx4M2k9W4HnVfHP4f8BIqp5l3xPw";
    String[] hos, atms, col, sch, res;
    ProgressDialog dialog;
    Intent mapListIntent=null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bulidGoogleApiClient();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          return inflater.inflate(R.layout.fragment_h, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        hospital = (Button) getActivity().findViewById(R.id.hospitalButton);
        atm = (Button) getActivity().findViewById(R.id.atmButton);
        college = (Button) getActivity().findViewById(R.id.collButton);
        school = (Button) getActivity().findViewById(R.id.schButton);
        restaurant = (Button) getActivity().findViewById(R.id.restrButton);
        emOffice=(Button)getActivity().findViewById(R.id.eoButton);
        locTextView= (TextView) getActivity().findViewById(R.id.locTextView);
        floatingActionButton=(FloatingActionButton)getActivity().findViewById(R.id.fab);
        super.onActivityCreated(savedInstanceState);
        hospital.setOnClickListener(this);
        atm.setOnClickListener(this);
        college.setOnClickListener(this);
        school.setOnClickListener(this);
        restaurant.setOnClickListener(this);
        emOffice.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);


    }
    protected synchronized void bulidGoogleApiClient() {
        mGoogleApiClient=new GoogleApiClient.Builder(getActivity()).addConnectionCallbacks(this).addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).
                        build();
        Log.e("justSearch","bulidApi");
    }
    @Override
    public void onClick(View v) {
        int ch = v.getId();
        switch (ch) {
            case R.id.hospitalButton:
                Intent intent = new Intent(getActivity(), ListActivity.class);
                intent.putExtra("CHOICE", "hospital");
                startActivity(intent);
                break;
            case R.id.atmButton:
                Intent intent1 = new Intent(getActivity(), ListActivity.class);
                intent1.putExtra("CHOICE", "atm");
                startActivity(intent1);
                break;
            case R.id.collButton:
                Intent intent2 = new Intent(getActivity(), ListActivity.class);
                intent2.putExtra("CHOICE", "college");
                startActivity(intent2);
                break;
            case R.id.schButton:
                Intent intent3 = new Intent(getActivity(), ListActivity.class);
                intent3.putExtra("CHOICE", "school");
                startActivity(intent3);
                break;
            case R.id.restrButton:
                Intent intent4 = new Intent(getActivity(), ListActivity.class);
                intent4.putExtra("CHOICE", "restarunt");
                startActivity(intent4);
                break;

            case R.id.eoButton:
                Log.e("justSearch","clicked");
                Intent intent5 = new Intent(getActivity(), GoogleAddress.class);
                intent5.putExtra("PNAME","Empolyment Office");
                intent5.putExtra("ADDRESS","District Empolyment Office,\n16,Kandapuram Road,Virudhunagar-626001");
                intent5.putExtra("CH",true);
                startActivity(intent5);
                break;
            case R.id.fab:
                locTextView.setVisibility(View.VISIBLE);
                if (!isNetworkAvailable()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Enable internet connection and RE-LAUNCH!!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                //dialog = ProgressDialog.show(getActivity(), "", "Please wait", true);
                // Add your Google Places Access key, and update the location values.

              mapListIntent=new Intent(getActivity(),MapListActivity.class);
                if(latitude>0&&longitude>0){mapListIntent.putExtra("CHOICE","nearBy");
                mapListIntent.putExtra("LAT",mLocation.getLatitude());
                mapListIntent.putExtra("LON",mLocation.getLongitude());
                startActivity(mapListIntent);}

                break;
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.e("justSearch","onConnected");
        mLocationRequest= LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);
        mLocation= LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mLocation!=null)
        {
           latitude=mLocation.getLatitude();
            longitude=mLocation.getLongitude();
            //LocationAddress mLocationAddress=new LocationAddress(mLocation,getActivity());
            //mLocationAddress.getAddress(new GeocoderHandler());
        }
        //LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,  this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
   Log.e("justSearch","error");
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude=location.getLatitude();
        longitude=location.getLongitude();
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message){
            //  tvview=(TextView)findViewById(R.id.ttView);
            String locationAddress;
            switch(message.what) {
                case 1:
                    Bundle bundle=message.getData();
                    locationAddress=bundle.getString("address");
                    break;
                default:
                    locationAddress=null;

            }
            locTextView.setText(locationAddress);

        }
    }


}
