package com.example.mhikeapp;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomappbar.BottomAppBar;

import java.util.ArrayList;

public class myadapterobs extends RecyclerView.Adapter<myadapterobs.viewholder> implements Filterable {
    ArrayList<ObservationPojo> dataholder = new ArrayList<>();


    public myadapterobs(ArrayList<ObservationPojo> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.observationcard,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") final int position) {
        holder.observationname.setText(dataholder.get(position).getObsname());
        holder.observationdate.setText(dataholder.get(position).getObsdate());
        holder.observationtime.setText(dataholder.get(position).getObstime());

        holder.observationcomment.setText(dataholder.get(position).getObscomment());


    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }


    class viewholder extends RecyclerView.ViewHolder{
        CardView card;
        TextView observationname, observationdate,observationtime,observationcomment;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            card=(CardView)itemView.findViewById(R.id.obsadapter);
            observationname=(TextView) itemView.findViewById(R.id.obstext);
            observationdate=(TextView) itemView.findViewById(R.id.datetext);
            observationtime=(TextView)itemView.findViewById(R.id.timetext);
            observationcomment=(TextView) itemView.findViewById(R.id.commenttext);

        }




    }

}

