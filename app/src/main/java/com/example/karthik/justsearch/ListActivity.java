package com.example.karthik.justsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    Toolbar toolbar;
    MyItemRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    int[] demo = {R.drawable.hdfc, R.drawable.tmb};
    String[] hos, atms, col, sch, rest;
   public static String ch, ch1;
    double mLatitude,mLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setVerticalScrollBarEnabled(true);
        Intent i = getIntent();
        ch = i.getStringExtra("CHOICE");

        Toast.makeText(this, ch, Toast.LENGTH_SHORT).show();
        switch (ch) {
            case "atm":
                adapter = new MyItemRecyclerViewAdapter(getATMData());
                recyclerView.setAdapter(adapter);
                break;
            case "hospital":
                adapter = new MyItemRecyclerViewAdapter(getHosData());
                recyclerView.setAdapter(adapter);
                break;
            case "college":
                adapter = new MyItemRecyclerViewAdapter(getColData());
                recyclerView.setAdapter(adapter);
                break;
            case "school":
                adapter = new MyItemRecyclerViewAdapter(getSchData());
                recyclerView.setAdapter(adapter);
                break;
            case "restarunt":
                adapter = new MyItemRecyclerViewAdapter(getResData());
                recyclerView.setAdapter(adapter);
                break;
            case "Vatm":
                adapter = new MyItemRecyclerViewAdapter(filter(getATMData(), "virudhunagar"));
                recyclerView.setAdapter(adapter);
                break;
            case "Vhospital":
                adapter = new MyItemRecyclerViewAdapter(filter(getHosData(), "Virudhunagar"));
                recyclerView.setAdapter(adapter);
                break;
            case "Vcollege":
                adapter = new MyItemRecyclerViewAdapter(filter(getColData(), "Virudhunagar"));
                recyclerView.setAdapter(adapter);
                break;
            case "Vschool":
                adapter = new MyItemRecyclerViewAdapter(filter(getSchData(), "Virudhunagar"));
                recyclerView.setAdapter(adapter);
                break;
            case "Vrestarunt":
                adapter = new MyItemRecyclerViewAdapter(filter(getResData(), "Virudhunagar"));
                recyclerView.setAdapter(adapter);
                break;
            case "Satm":
                adapter = new MyItemRecyclerViewAdapter(filter(getATMData(), "Sivakasi"));
                recyclerView.setAdapter(adapter);
                break;
            case "Shospital":
                adapter = new MyItemRecyclerViewAdapter(filter(getHosData(), "Sivakasi"));
                recyclerView.setAdapter(adapter);
                break;
            case "Scollege":
                adapter = new MyItemRecyclerViewAdapter(filter(getColData(), "Sivakasi"));
                recyclerView.setAdapter(adapter);
                break;
            case "Sschool":
                adapter = new MyItemRecyclerViewAdapter(filter(getSchData(), "Sivakasi"));
                recyclerView.setAdapter(adapter);
                break;
            case "Srestarunt":
                adapter = new MyItemRecyclerViewAdapter(filter(getResData(), "Sivakasi"));
                recyclerView.setAdapter(adapter);
                break;
            case "Tatm":
                adapter = new MyItemRecyclerViewAdapter(filter(getATMData(), "Thiruthangal"));
                recyclerView.setAdapter(adapter);
                break;
            case "Thospital":
                adapter = new MyItemRecyclerViewAdapter(filter(getHosData(), "Sivakasi"));
                recyclerView.setAdapter(adapter);
                break;
            case "Tcollege":
                adapter = new MyItemRecyclerViewAdapter(filter(getColData(), "Sivakasi"));
                recyclerView.setAdapter(adapter);
                break;
            case "Tschool":
                adapter = new MyItemRecyclerViewAdapter(filter(getSchData(), "Thiruthangal"));
                recyclerView.setAdapter(adapter);
                break;
            case "Trestarunt":
                adapter = new MyItemRecyclerViewAdapter(filter(getResData(), "Thiruthangal"));
                recyclerView.setAdapter(adapter);
                break;


        }

    }

    public List<Information> getATMData() {
        List<Information> items = new ArrayList<>();
        int[] atmIcon = {R.drawable.axis, R.drawable.sbi, R.drawable.tmb, R.drawable.axis, R.drawable.axis, R.drawable.axis, R.drawable.axis, R.drawable.axis, R.drawable.axis, R.drawable.bindia, R.drawable.bindia, R.drawable.bindia, R.drawable.maharashtra, R.drawable.canara, R.drawable.catholic, R.drawable.uni, R.drawable.uni, R.drawable.hdfc, R.drawable.hdfc, R.drawable.icici, R.drawable.icici, R.drawable.bindia, R.drawable.axis, R.drawable.tmb, R.drawable.axis, R.drawable.tmb, R.drawable.uni, R.drawable.tmb, R.drawable.axis, R.drawable.idbi, R.drawable.icici, R.drawable.tmb, R.drawable.hdfc, R.drawable.sbi, R.drawable.bindia, R.drawable.uni, R.drawable.icici, R.drawable.sbi, R.drawable.tmb, R.drawable.uni, R.drawable.karur, R.drawable.lash, R.drawable.lash
        };
        atms = getApplicationContext().getResources().getStringArray(R.array.atmsName);

        for (int i = 0; i < atmIcon.length && i < atms.length; i++) {
            Information current = new Information();
            current.iconId = atmIcon[i];
            current.title = atms[i];
            items.add(current);
        }

        return items;
    }

    public List<Information> getHosData() {
        List<Information> items = new ArrayList<>();

        hos = getApplicationContext().getResources().getStringArray(R.array.hospitalName);

        for (int i = 0; i < hos.length; i++) {
            Information current = new Information();
            current.iconId = R.drawable.hospital;
            current.title = hos[i];
            items.add(current);
        }

        return items;
    }

    public List<Information> getColData() {
        List<Information> items = new ArrayList<>();
        int[] colIcon = {R.drawable.kamaraj, R.drawable.vani, R.drawable.vhnsn, R.drawable.srividya, R.drawable.kalis, R.drawable.aj, R.drawable.mpco, R.drawable.psr, R.drawable.aaa, R.drawable.sfr, R.drawable.rag, R.drawable.ajp};
        col = getApplicationContext().getResources().getStringArray(R.array.collegsName);

        for (int i = 0; i < colIcon.length && i < col.length; i++) {
            Information current = new Information();
            current.iconId = colIcon[i];
            current.title = col[i];
            items.add(current);
        }

        return items;
    }

    public List<Information> getSchData() {
        List<Information> items = new ArrayList<>();
        int[] schIcon = {R.drawable.kvs, R.drawable.school, R.drawable.school, R.drawable.school, R.drawable.ruby, R.drawable.kvs, R.drawable.school, R.drawable.jecees, R.drawable.auna, R.drawable.school, R.drawable.school, R.drawable.school, R.drawable.school, R.drawable.school, R.drawable.school, R.drawable.lion, R.drawable.school, R.drawable.school, R.drawable.school, R.drawable.school, R.drawable.school, R.drawable.school, R.drawable.school, R.drawable.school, R.drawable.school, R.drawable.yrtv, R.drawable.aaa, R.drawable.school, R.drawable.srn, R.drawable.school, R.drawable.school};
        sch = getApplicationContext().getResources().getStringArray(R.array.schoolsName);
        for (int i = 0; i < schIcon.length && i < sch.length; i++) {
            Information current = new Information();
            current.iconId = schIcon[i];
            current.title = sch[i];
            items.add(current);
        }

        return items;
    }

    public List<Information> getResData() {
        List<Information> items = new ArrayList<>();
        rest = getApplicationContext().getResources().getStringArray(R.array.restrauntName);
        for (int i = 0; i < rest.length; i++) {
            Information current = new Information();
            current.iconId = R.drawable.res;
            current.title = rest[i];
            items.add(current);
        }

        return items;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        switch (ch) {
                            case "atm":
                                adapter.setFilter(getATMData());
                                break;
                            case "hospital":
                                adapter.setFilter(getHosData());
                                break;
                            case "college":
                                adapter.setFilter(getColData());
                                break;
                            case "school":
                                adapter.setFilter(getSchData());
                                break;
                            case "restarunt":
                                adapter.setFilter(getResData());
                                break;
                            case "Vatm":
                                adapter.setFilter(filter(getATMData(), "Virudhunagar"));
                                break;
                            case "Vhospital":
                                adapter.setFilter(filter(getHosData(), "Virudhunagar"));
                                break;
                            case "Vcollege":
                                adapter.setFilter(filter(getColData(), "Virudhunagar"));
                                break;
                            case "Vschool":
                                adapter.setFilter(filter(getSchData(), "Virudhunagar"));
                                break;
                            case "Vrestarunt":
                                adapter.setFilter(filter(getResData(), "Virudhunagar"));
                                break;
                            case "Satm":
                                adapter.setFilter(filter(getATMData(), "Sivakasi"));
                                break;
                            case "Shospital":
                                adapter.setFilter(filter(getHosData(), "Sivakasi"));
                                break;
                            case "Scollege":
                                adapter.setFilter(filter(getColData(), "Sivakasi"));
                                break;
                            case "Sschool":
                                adapter.setFilter(filter(getSchData(), "Sivakasi"));
                                break;
                            case "Srestarunt":
                                adapter.setFilter(filter(getResData(), "Sivakasi"));
                                break;
                            case "Tatm":
                                adapter.setFilter(filter(getATMData(), "Thiruthangal"));
                                break;
                            case "Thospital":
                                adapter.setFilter(filter(getHosData(), "Sivakasi"));
                                break;
                            case "Tcollege":
                                adapter.setFilter(filter(getColData(), "Sivakasi"));
                                break;
                            case "Tschool":
                                adapter.setFilter(filter(getSchData(), "Thiruthangal"));
                                break;
                            case "Trestarunt":
                                adapter.setFilter(filter(getResData(), "Thiruthangal"));
                                break;


                        }
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }


                });
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Information> filteredModelList;
        switch (ch) {
            case "atm":
                filteredModelList = filter(getATMData(), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "hospital":
                filteredModelList = filter(getHosData(), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "college":
                filteredModelList = filter(getColData(), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "school":
                filteredModelList = filter(getSchData(), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "restarunt":
                filteredModelList = filter(getResData(), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "Vatm":
                filteredModelList = filter(filter(getATMData(), "Virudhunagar"), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "Vhospital":
                filteredModelList = filter(filter(getHosData(), "Virudhunagar"), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "Vcollege":
                filteredModelList = filter(filter(getColData(), "Virudhunagar"), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "Vschool":
                filteredModelList = filter(filter(getSchData(), "Virudhunagar"), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "Vrestarunt":
                filteredModelList = filter(filter(getResData(), "Virudhunagar"), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "Satm":
                filteredModelList = filter(filter(getATMData(), "Sivakasi"), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "Shospital":
                filteredModelList = filter(filter(getHosData(), "Sivakasi"), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "Scollege":
                filteredModelList = filter(filter(getColData(), "Sivakasi"), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "Sschool":
                filteredModelList = filter(filter(getSchData(), "Sivakasi"), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "Srestarunt":
                filteredModelList = filter(filter(getResData(), "Sivakasi"), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "Tatm":
                filteredModelList = filter(filter(getATMData(), "Thiruthangal"), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "Thospital":
                filteredModelList = filter(filter(getHosData(), "Sivakasi"), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "Tcollege":
                filteredModelList = filter(filter(getColData(), "Sivakasi"), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "Tschool":
                filteredModelList = filter(filter(getSchData(), "Thiruthangal"), newText);
                adapter.setFilter(filteredModelList);
                break;
            case "Trestarunt":
                filteredModelList = filter(filter(getResData(), "Thiruthangal"), newText);
                adapter.setFilter(filteredModelList);
                break;


        }

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }



    private List<Information> filter(List<Information> items, String query) {
        query = query.toLowerCase();

        final List<Information> filteredModelList = new ArrayList<>();
        for (Information item : items) {
            final String text = item.title.toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(item);
            }
        }
        return filteredModelList;
    }

}
