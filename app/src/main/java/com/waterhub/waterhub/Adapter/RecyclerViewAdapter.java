package com.waterhub.waterhub.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.waterhub.waterhub.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nadian on 12/3/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private Context mContext;
    String urlimage = "https://image.tmdb.org/t/p/w640";
    //buat tipe data list dari model filmadapter
    private List<FilmModel> filmList;

    //make new class viewholder untuk menangani view apa saja yang akan dihold
    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title_item) TextView title;
        @BindView(R.id.iv_picture_item) ImageView picture;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);

        }
    }

    //generate constructor
    public RecyclerViewAdapter(Context mContext, List<FilmModel> filmList) {
        this.mContext = mContext;
        this.filmList = filmList;
    }


    public RecyclerViewAdapter() {
    }


    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_film_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    //menghandle yang diklik, dan untuk handle memasukkan ke dalam list
    public void onBindViewHolder(final RecyclerViewAdapter.MyViewHolder holder, int position) {
        FilmModel mfilmModel = filmList.get(position);

        String mPicture = mfilmModel.getPoster();

        //show data into layout to user
        holder.title.setText(mfilmModel.getTitle());

        //loading film poster using picasso
        Picasso.with(mContext)
                .load(urlimage + mPicture)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.picture);


        holder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //TODO klik go to activity that shows the overview of movies
            }
        });
    }

    @Override
    public int getItemCount() {

        return  filmList.size();
    }


}
