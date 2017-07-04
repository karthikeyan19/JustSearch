package com.example.karthik.justsearch;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Karthik on 28-Jun-16.
 */
public class LocationAddress {
    private double latitude,longtitude;
    private String mAddress;
  private  Context context;
    private TextView mLocView;
    public LocationAddress(Location location, Context context){
        latitude=location.getLatitude();
        this.context=context;
        longtitude=location.getLongitude();
         }
    public void getAddress(final android.os.Handler handler) {
        Thread thread=new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;
                try {
                    List<Address> addressList = geocoder.getFromLocation(latitude, longtitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);
                        StringBuilder sB = new StringBuilder();
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                            sB.append(address.getAddressLine(i)).append("\n");
                        }
                        sB.append(address.getLocality()).append("\n");
                        sB.append(address.getPostalCode()).append("\n");
                        sB.append(address.getCountryName()).append("\n");
                        mAddress = sB.toString();

                    }

                } catch (IOException e) {
                    //  Log.e(TAG,"unable to get geocoder",e);
                } finally {
                    Message message=Message.obtain();
                    message.setTarget(handler);
                    if(result!=null){
                        message.what=1;
                        Bundle bundle=new Bundle();
                        result="Latitude:"+latitude+"\nLongtitude:"+longtitude+"\nAddress:\n"+result;
                        bundle.putString("address",result);
                        message.setData(bundle);
                    }else {
                        message.what=1;
                        Bundle bundle=new Bundle();
                        result="Latitude"+latitude+"\nLongtitude"+longtitude+"\nUnable to get address";
                        bundle.putString("address",result);
                        message.setData(bundle);
                    }
                    message.sendToTarget();
                }
            }
        };
            thread.start();
        }

    }

