package com.example.karthik.justsearch;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MapRecyclerViewAdapter extends RecyclerView.Adapter<MapRecyclerViewAdapter.ViewHolder> {
   ViewGroup context;
    private List<DummyItem> mValues;

    public MapRecyclerViewAdapter(List<DummyItem> items) {
        mValues = items;
     }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.map_fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DummyItem current = mValues.get(position);
        holder.mIdView.setText(current.id);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(context.getContext(),GoogleAddress.class);
                   intent.putExtra("PNAME",current.id);
                   intent.putExtra("ADDRESS",current.content);
                  context.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
    public void insert(List<DummyItem> list) {
       int pos=getItemCount()-1;
        mValues.addAll(list);
        Log.e("justSearch","inserted"+pos);
        notifyItemInserted(pos);

    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;

        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.name);

        }

        }
    public void setFilter(List<DummyItem> items){
        mValues = new ArrayList<>();
        mValues.addAll(items);
        notifyDataSetChanged();
    }
    public List<DummyItem> getList()
    {
        return mValues;
    }
    }

