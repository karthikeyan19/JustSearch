package com.example.karthik.justsearch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    static MapRecyclerViewAdapter adapter=null;
    RecyclerView recyclerView;
    double mLatitude, mLongitude;
    static List<DummyItem> places;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(getResources().getString(R.string.nearBy));
        setSupportActionBar(toolbar);
        Log.e("justSearch", "onCreate");

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setVerticalScrollBarEnabled(true);
        Intent intent = getIntent();
        mLatitude = intent.getDoubleExtra("LAT", 0.0);
        mLongitude = intent.getDoubleExtra("LON", 0.0);
        adapter=null;
        String types[] = getResources().getStringArray(R.array.places);
        dialog = ProgressDialog.show(this, "", "Please wait", true);
        for (String type : types) {
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
            sb.append("location=" + mLatitude + "," + mLongitude);
            sb.append("&radius=5000");
            sb.append("&types=" + type);
            sb.append("&sensor=true");
            sb.append("&key=AIzaSyBWQ4pFfbH5VyS4e76OX3YA1cDC0ShPWSc");


            PlacesTask placesTask = new PlacesTask();

            placesTask.execute(sb.toString());
        }

    }


    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        Log.e("justSearch", "ondownloading");

        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            //Log.e("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }

        return data;
    }

    public boolean onQueryTextChange(String newText) {
        if(adapter!=null){
          Log.e("justSearch","search");
            List<DummyItem> filteredModelList = filter(adapter.getList(), newText);
                adapter.setFilter(filteredModelList);

        }return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private List<DummyItem> filter(List<DummyItem> items, String query) {
        query = query.toLowerCase();

        final List<DummyItem> filteredModelList = new ArrayList<>();
        for (DummyItem item : items) {
            final String text = item.id.toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(item);
            }
        }
        return filteredModelList;
    }

    /**
     * A class, to download Google Places
     */
    private class PlacesTask extends AsyncTask<String, Integer, String> {

        String data = null;

        // Invoked by execute() method of this object
        @Override
        protected String doInBackground(String... url) {
            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(String result) {
            ParserTask parserTask = new ParserTask();

            // Start parsing the Google places in JSON format
            // Invokes the "doInBackground()" method of the class ParseTask
            parserTask.execute(result);
        }

    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {

        JSONObject jObject;

        // Invoked by execute() method of this object
        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {
            Log.e("justSearch", "parserTask");

            List<HashMap<String, String>> places = null;
            PlaceJSONParser placeJsonParser = new PlaceJSONParser();

            try {
                jObject = new JSONObject(jsonData[0]);

                /** Getting the parsed data as a List construct */
                places = placeJsonParser.parse(jObject);

            } catch (Exception e) {
                Log.d("Exception", e.toString());
            }
            return places;
        }

        // Executed after the complete execution of doInBackground() method
        @Override
        protected void onPostExecute(List<HashMap<String, String>> list) {
            List<DummyItem> placesList = new ArrayList<>();
            // Clears all the existing markers
            Log.e("justSearch", "parser");
            for (int i = 0; i < list.size(); i++) {

                // Creating a marker

                // Getting a place from the places list
                HashMap<String, String> hmPlace = list.get(i);

                // Getting latitude of the place
                double lat = Double.parseDouble(hmPlace.get("lat"));

                // Getting longitude of the place
                double lng = Double.parseDouble(hmPlace.get("lng"));

                // Getting name
                String name = hmPlace.get("place_name");
                Log.e("justSearch", name);
                // Getting vicinity
                String vicinity = hmPlace.get("vicinity");
                DummyItem current = new DummyItem(name, vicinity);
                placesList.add(current);
            }
            if (placesList.size() > 0) {
                updateList(placesList);
            }

        }
    }

    public void updateList(List<DummyItem> items) {
        if (adapter == null) {

            adapter = new MapRecyclerViewAdapter(items);
            recyclerView.setAdapter(adapter);
            dialog.dismiss();
        } else {
            adapter.insert(items);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_search)
        {
            recyclerView.scrollToPosition(0);
        }
        return super.onOptionsItemSelected(item);
    }

}


