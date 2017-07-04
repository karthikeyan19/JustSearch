package com.example.karthik.justsearch;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karthik on 25-Jun-16.
 */
public class GeoFenceTranstitionsIntentService extends IntentService {
    static final String TAG="gfService";
    String geoFenceTransitionDetails;
    @Override
    public void onCreate() {
        super.onCreate();

    }


    public GeoFenceTranstitionsIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent=GeofencingEvent.fromIntent(intent);
        if(geofencingEvent.hasError()){
           String error=GeoFenceErrorMessage.getErrorString(this, geofencingEvent.getErrorCode());
            Log.e(TAG,error);

        }
        int transititon=geofencingEvent.getGeofenceTransition();
        if(transititon== Geofence.GEOFENCE_TRANSITION_ENTER||transititon==Geofence.GEOFENCE_TRANSITION_EXIT)
        {
            List<Geofence> geofenceList=geofencingEvent.getTriggeringGeofences();
            geoFenceTransitionDetails=getGeoFenceTransitionDetails(this,transititon,geofenceList);

        }
        sendNotification(geoFenceTransitionDetails);

    }

    public String getGeoFenceTransitionDetails(Context context,int geoTransition,List<Geofence> geofenceTiggeredList) {
        Resources mResources=context.getResources();
        String geoFenceTranstionString=getTransitionString(geoTransition);
        ArrayList tiggerdgeoFenceList=new ArrayList();
        for (Geofence geoFence:geofenceTiggeredList)
        {
          tiggerdgeoFenceList.add(geoFence);
        }
           String tiggeredGeoFenceDetails= TextUtils.join(", ",tiggerdgeoFenceList);
        return geoFenceTranstionString+" "+tiggeredGeoFenceDetails;
    }

    private String getTransitionString(int geoTransition) {
        if(geoTransition==Geofence.GEOFENCE_TRANSITION_ENTER){
            return "geoFence Enters";
        }
        else
        {
            return "geoFence Exit";
        }
    }
    private void sendNotification(String notificationdetails){
        Intent notificationIntent=new Intent(getApplicationContext(),GoogleAddress.class);
        TaskStackBuilder stackBulider=TaskStackBuilder.create(this);
        stackBulider.addParentStack(GoogleAddress.class);
        stackBulider.addNextIntent(notificationIntent);
        PendingIntent notificationPendingIntent=stackBulider.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.cast_ic_notification_2)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.cast_ic_notification_2))
                .setColor(getResources().getColor(R.color.colorAccent))
                .setContentTitle(notificationdetails)
                .setContentText("return to app")
                .setContentIntent(notificationPendingIntent);
        builder.setAutoCancel(true);

        NotificationManager mNotificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify();

    }
}
