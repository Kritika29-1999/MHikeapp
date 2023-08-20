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

public class myadapter extends RecyclerView.Adapter<myadapter.viewholder> implements Filterable {
    ArrayList<HikePojo> dataholder = new ArrayList<>();
    ArrayList<HikePojo> dataholderbackup ;


    public myadapter(ArrayList<HikePojo> dataholder) {
        this.dataholder = dataholder;
        dataholderbackup= new ArrayList<>(dataholder);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleviewcard,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") final int position) {
        holder.hikename.setText(dataholder.get(position).getName());
        holder.hikedesc.setText(dataholder.get(position).getDescription());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),UpdateActivity.class);
                intent.putExtra("name",String.valueOf(dataholder.get(position).getName()));
                intent.putExtra("id",String.valueOf(dataholder.get(position).getId()));
                intent.putExtra("location",String.valueOf(dataholder.get(position).getLocation()));
                intent.putExtra("date",String.valueOf(dataholder.get(position).getDate()));
                intent.putExtra("parking",String.valueOf(dataholder.get(position).getParking()));
                intent.putExtra("length",String.valueOf(dataholder.get(position).getLength()));
                intent.putExtra("level",String.valueOf(dataholder.get(position).getLevel()));
                intent.putExtra("description",String.valueOf(dataholder.get(position).getDescription()));

                view.getContext().startActivity(intent);

            }


        });
        holder.menu.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                    Intent intent = new Intent(holder.card.getContext(),ViewHikeActivity.class);
                intent.putExtra("name",String.valueOf(dataholder.get(position).getName()));
                intent.putExtra("id",String.valueOf(dataholder.get(position).getId()));
                intent.putExtra("location",String.valueOf(dataholder.get(position).getLocation()));
                intent.putExtra("date",String.valueOf(dataholder.get(position).getDate()));
                intent.putExtra("parking",String.valueOf(dataholder.get(position).getParking()));
                intent.putExtra("length",String.valueOf(dataholder.get(position).getLength()));
                intent.putExtra("level",String.valueOf(dataholder.get(position).getLevel()));
                intent.putExtra("description",String.valueOf(dataholder.get(position).getDescription()));
                    holder.card.getContext().startActivity(intent);

                return true;
            }
        });


//        holder.hikedate.setText(dataholder.get(position).getDate());
//        holder.hikelocation.setText(dataholder.get(position).getlocation());


    }

    @Override
    public int getItemCount() {
            return dataholder.size();


    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
           ArrayList<HikePojo> filtereddata = new ArrayList<>();
           if(charSequence.toString().isEmpty())
               filtereddata.addAll(dataholderbackup);
           else{
               for(HikePojo hp:dataholderbackup){
                   if(hp.getName().toString().toLowerCase().contains(charSequence.toString().toLowerCase()))
                       filtereddata.add(hp);
               }
           }

           FilterResults res = new FilterResults();
           res.values=filtereddata;
           return res;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            dataholder.clear();
            dataholder.addAll((ArrayList<HikePojo>)filterResults.values);
            notifyDataSetChanged();
        }
    };

    class viewholder extends RecyclerView.ViewHolder{
        CardView card;
        TextView hikename, hikedesc;
        BottomAppBar menu;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            card=(CardView)itemView.findViewById(R.id.adapter);
            hikename=(TextView) itemView.findViewById(R.id.displayname);
            hikedesc=(TextView) itemView.findViewById(R.id.textView6);
            menu=(BottomAppBar) itemView.findViewById(R.id.menubar);

        }




    }

}

