package com.example.karthik.justsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AddressDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Address Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView address= (TextView) findViewById(R.id.address);
        TextView head=(TextView)findViewById(R.id.textView);
        TextView cr= (TextView) findViewById(R.id.textView2);

        Intent g=getIntent();
        String hint=g.getStringExtra("HINT");
        String ch=g.getStringExtra("CH");
        int pos=g.getIntExtra("POS",0);
        boolean switche=g.getBooleanExtra("s",false);
        if(switche){
            toolbar.setTitle("About");
            address.setVisibility(View.GONE);
            cr.setVisibility(View.VISIBLE);
            head.setText("This is Virudhunagar Welfare App\n This app is meant for searching schools,ATMs,Collegs,Restaraunts,Hospitals in the following cities:1.Virudhunagar\n2.Sivakasi\n3.Thiruthangal\n\nThis app also provide nearby places with respect to your current location. ");

        }
        else {
            String[] hosptials = getApplicationContext().getResources().getStringArray(R.array.hospitals);
            String[] schools = getApplicationContext().getResources().getStringArray(R.array.schools);
            String[] res = getApplicationContext().getResources().getStringArray(R.array.restarunts);
            String[] collegs = getApplicationContext().getResources().getStringArray(R.array.collegs);

            String[] hint1 = hint.split(",");

            if (hint.contains("Hospital")) {
                for (String s : hosptials) {
                    if (s.contains(hint1[0])) {
                        address.setText(s);
                        break;

                    }

                }
            } else if (hint.contains("College")) {
                for (String s : collegs) {
                    if (s.contains(hint1[0])) {
                        address.setText(s);
                    }
                }
            } else if (hint.contains("ATM") || hint.contains("Bank") || hint.contains("bank")) {
                String[] add;
                switch (ch) {
                    case "Vatm":
                        add = getResources().getStringArray(R.array.vAtms);
                        address.setText(add[pos]);
                        break;
                    case "Satm":
                        add = getResources().getStringArray(R.array.sAtms);
                        address.setText(add[pos]);
                        break;
                    case "Tatm":
                        add = getResources().getStringArray(R.array.tAtms);
                        address.setText(add[pos]);
                        break;
                    case "atm":
                        add = getResources().getStringArray(R.array.atms);
                        address.setText(add[pos]);
                        break;
                }

            } else if (hint.contains("School")) {
                for (String s : schools) {
                    if (s.contains(hint1[0])) {
                        address.setText(s);
                    }
                }
            } else {
                for (String s : res) {
                    if (s.contains(hint1[0])) {
                        address.setText(s);
                    }
                }
            }
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

}
