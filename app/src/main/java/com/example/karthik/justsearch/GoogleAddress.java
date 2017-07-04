package com.example.karthik.justsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class GoogleAddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView placeName= (TextView) findViewById(R.id.place);
        TextView address=(TextView)findViewById(R.id.address);
        Intent intent=getIntent();
        boolean ch=intent.getBooleanExtra("CH",false);
        if(ch){toolbar.setTitle(R.string.emh);}
        else {toolbar.setTitle(R.string.nearBy);}
            placeName.setText(intent.getStringExtra("PNAME"));
            address.setText(intent.getStringExtra("ADDRESS"));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


}
