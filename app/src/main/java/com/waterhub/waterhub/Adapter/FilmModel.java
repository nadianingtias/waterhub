package com.waterhub.waterhub.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Nadian on 12/3/2017.
 */

public class FilmModel {

    String title, poster;
    String overview;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    String releaseDate;
    //constructor 2 params untuk dipakai di kelas activity
    public FilmModel(String title, String poster) {
        this.title = title;
        this.poster = poster;
    }
    //constructor tanpa param untuk dipakai di dalam kelas adapternya
    public FilmModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


}
