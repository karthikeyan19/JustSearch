package com.example.karthik.justsearch;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.location.GeofenceStatusCodes;
/**
 * Created by Karthik on 25-Jun-16.
 */
public class GeoFenceErrorMessage {
    GeoFenceErrorMessage(){

    }
    public static String getErrorString(Context context,int errorCode)
    {
        Resources resources=context.getResources();
        switch (errorCode)
        {
            case GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE:
                return  resources.getString(R.string.not_avaliable);
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
                return  resources.getString(R.string.many_geofence);
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
                return resources.getString(R.string.many_intent);
            default:
                return resources.getString(R.string.unknown_error);
        }

    }
}
