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
import android.widget.Toast;

/**
 * Created by Karthik on 08-Jun-16.
 */
public class FragmentV extends Fragment implements View.OnClickListener {
    private Button hospital, atm, college, school, emOffice, restaurant;
    FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_v, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        hospital = (Button) getActivity().findViewById(R.id.vHospitalButton);
        atm = (Button) getActivity().findViewById(R.id.vAtmButton);
        college = (Button) getActivity().findViewById(R.id.vCollButton);
        school = (Button) getActivity().findViewById(R.id.vSchButton);
        restaurant = (Button) getActivity().findViewById(R.id.vRestrButton);
        emOffice=(Button)getActivity().findViewById(R.id.vEoButton);
        floatingActionButton=(FloatingActionButton)getActivity().findViewById(R.id.fab3);
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
            case R.id.vHospitalButton:
                Intent i = new Intent(getActivity(), ListActivity.class);
                i.putExtra("CHOICE","Vhospital");
                startActivity(i);
                break;
            case R.id.vAtmButton:
                Intent i1 = new Intent(getActivity(), ListActivity.class);
                i1.putExtra("CHOICE","Vatm");
                startActivity(i1);
                break;
            case R.id.vCollButton:
                Intent i2 = new Intent(getActivity(), ListActivity.class);
                i2.putExtra("CHOICE","Vcollege");
                startActivity(i2);
                break;
            case R.id.vSchButton:
                Intent i3 = new Intent(getActivity(), ListActivity.class);
                i3.putExtra("CHOICE","Vschool");
                startActivity(i3);
                break;
            case R.id.vRestrButton:
                Intent i4 = new Intent(getActivity(), ListActivity.class);
                i4.putExtra("CHOICE","Vrestarunt");
                startActivity(i4);
                break;

            case R.id.vEoButton:
                Intent intent5 = new Intent(getActivity(), GoogleAddress.class);
                intent5.putExtra("PNAME","Empolyment Office");
                intent5.putExtra("ADDRESS","District Empolyment Office,\n16,Kandapuram Road,Virudhunagar-626001");
                intent5.putExtra("CH",true);
                startActivity(intent5);
            case R.id.fab3:
                Intent i6=new Intent(getActivity(),MapsActivity.class);
                i6.putExtra("lat",9.5734395);
                i6.putExtra("lon",77.923085);
                startActivity(i6);
                Toast.makeText(getActivity(),"map",Toast.LENGTH_LONG).show();
                break;

        }

    }
}
