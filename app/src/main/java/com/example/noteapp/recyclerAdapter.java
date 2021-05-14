package com.example.noteapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.model.File;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<File> files;
    private RecyclerViewClickListner listner;

    public  interface  onItemClickListener{
        void onItemClick(int position);
    }
    public recyclerAdapter(ArrayList<File> files, RecyclerViewClickListner listner){
        this.files = files;
        this.listner = listner;
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView titleText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            listner.onClick(itemView, getAdapterPosition());
        }
    }
    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view ,parent,false);
        return  new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {

        String title = files.get(position).getTitle();
        if (title.isEmpty())
        {
            holder.titleText.setText("Untitled");
        }
        else
        {
            holder.titleText.setText(title);
        }


    }

    @Override
    public int getItemCount() {
        return files.size();
    }

   public interface RecyclerViewClickListner{
        void onClick(View v, int position );
   }
}
