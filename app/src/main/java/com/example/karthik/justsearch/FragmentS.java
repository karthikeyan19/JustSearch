package com.example.karthik.justsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Karthik on 08-Jun-16.
 */
public class FragmentS extends Fragment implements  View.OnClickListener {
    private Button hospital, atm, college, school, emOffice, restaurant;
    FloatingActionButton floatingActionButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_s,container,false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        hospital = (Button) getActivity().findViewById(R.id.sHospitalButton);
        atm = (Button) getActivity().findViewById(R.id.sAtmButton);
        college = (Button) getActivity().findViewById(R.id.sCollButton);
        school = (Button) getActivity().findViewById(R.id.sSchButton);
        restaurant = (Button) getActivity().findViewById(R.id.sRestrButton);
        emOffice=(Button)getActivity().findViewById(R.id.sEoButton);
        floatingActionButton=(FloatingActionButton)getActivity().findViewById(R.id.fab1);
        super.onActivityCreated(savedInstanceState);
        hospital.setOnClickListener(this);
        atm.setOnClickListener(this);
        college.setOnClickListener(this);
        school.setOnClickListener(this);
        restaurant.setOnClickListener(this);
        emOffice.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int ch = v.getId();
        switch (ch) {
            case R.id.sHospitalButton:
                Intent i = new Intent(getActivity(), ListActivity.class);
                i.putExtra("CHOICE","Shospital");
                startActivity(i);
                break;
            case R.id.sAtmButton:
                Intent i1 = new Intent(getActivity(), ListActivity.class);
                i1.putExtra("CHOICE","Satm");
                startActivity(i1);
                break;
            case R.id.sCollButton:
                Intent i2 = new Intent(getActivity(), ListActivity.class);
                i2.putExtra("CHOICE","Scollege");
                startActivity(i2);
                break;
            case R.id.sSchButton:
                Intent i3 = new Intent(getActivity(), ListActivity.class);
                i3.putExtra("CHOICE","Sschool");
                startActivity(i3);
                break;
            case R.id.sRestrButton:
                Intent i4 = new Intent(getActivity(), ListActivity.class);
                i4.putExtra("CHOICE","Srestarunt");
                startActivity(i4);
                break;

            case R.id.sEoButton:
                Intent intent5 = new Intent(getActivity(), GoogleAddress.class);
                intent5.putExtra("PNAME","Empolyment Office");
                intent5.putExtra("ADDRESS","District Empolyment Office,\n16,Kandapuram Road,Virudhunagar-626001");
                intent5.putExtra("CH",true);
                startActivity(intent5);
                break;
            case R.id.fab1:
                Intent i6=new Intent(getActivity(),MapsActivity.class);
                i6.putExtra("lat",9.4500);
                i6.putExtra("lon",77.8000);
                startActivity(i6);
                break;
        }

    }
}
