package com.example.karthik.justsearch;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    ViewGroup context;
    private  List<Information> mValues= Collections.emptyList();
    private  LayoutInflater inflate;
 int  previousPosition=0;

    public MyItemRecyclerViewAdapter(List<Information> items) {
        mValues = items;
       //inflate=LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       context=parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,  int position) {
          final Information current=mValues.get(position);
          holder.mIdView.setImageResource(current.iconId);
          holder.mContentView.setText(current.title);
          holder.mView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  Intent i =new Intent(context.getContext(),AddressDisplayActivity.class);
                  String hint=current.title;
                  i.putExtra("POS",holder.getAdapterPosition());
                  i.putExtra("HINT",hint);
                  i.putExtra("CH",ListActivity.ch);
                  context.getContext().startActivity(i);

              }
          });
        

            AnimationUtil.animate(holder);


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIdView;
        public final TextView mContentView;



        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (ImageView) view.findViewById(R.id.id1);
            mContentView = (TextView) view.findViewById(R.id.content);
        }


}
    public void setFilter(List<Information> items){
        mValues = new ArrayList<>();
        mValues.addAll(items);
        notifyDataSetChanged();
    }

}
