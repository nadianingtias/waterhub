package com.waterhub.waterhub;

import android.content.res.Resources;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;
import com.waterhub.waterhub.Adapter.FilmModel;
import com.waterhub.waterhub.Adapter.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    //url
    String url = "https://api.themoviedb.org/3/list/3?api_key=2a4aeb98bcf6452bcbec72b0b77398b1&language=en-US";
    String urlimage = "https://image.tmdb.org/t/p/w640";
    //ui element
    @BindView(R.id.love_movie) TextView tvTitle;
    @BindView(R.id.home_backdrop) ImageView ivPoster;
    @BindView(R.id.rv_home_film) RecyclerView rvFilms;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbarLayout)
    AppBarLayout appBarLayout;

    //init
    private List<FilmModel> FilmModelList;
    private RecyclerViewAdapter adapter;

    //TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //butterknife requirement
        ButterKnife.bind(this);
        //keperluan networking
        AndroidNetworking.initialize(getApplicationContext());


//        setSupportActionBar(toolbar);

        initCollapsingToolbar();
        //jika sudah pake butterknife, s=tidak perlu lagi findviewbyid
//        tvTitle = (TextView) findViewById(R.id.tv_home_movietitle);

        //init list tipe data
        FilmModelList = new ArrayList<>();

        //inisialisasi recycler
        initRecycler();


        //method lengkap ada di bawah gan
        RequestAPiMovieListener();
        RequestAPIMovieListenerBanyak();

        //init arraylist

    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        collapsingToolbar.setTitle(" ");
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });

    }

    private void RequestAPIMovieListenerBanyak() {
        AndroidNetworking.get(url)
                //.addPathParameter("pageNumber", "0")
                //.addQueryParameter("limit", "3")
                //.addHeaders("token", "1234")
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {      //response API berupa object
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.e("log","respon OK");
                        //tvTitle.setText(response.toString());
                        try{
                            JSONArray item = response.getJSONArray("items");
                            Log.e("array JSON", item.toString());
                            //tvTitle.setText(item.toString());

                            FilmModelList.clear();

                            //masuk ke jsonobject di dalam array
                            for(int i=0; i < item.length();i++)
                            {
                                JSONObject jsonObj = item.getJSONObject(i);
                                String titleN = jsonObj.getString("title");
                                String posterN = jsonObj.getString("poster_path");

                                //panggil model, isikan ke dalam model
                                FilmModel mfilmModel = new FilmModel(titleN, posterN);

                                FilmModelList.add(mfilmModel);
                            }

                        }catch(NullPointerException e){
                            e.printStackTrace();

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    //1
    //akan mengambil title dan poster path
    private void RequestAPiMovieListener() {
        AndroidNetworking.get(url)
                //.addPathParameter("pageNumber", "0")
                //.addQueryParameter("limit", "3")
                //.addHeaders("token", "1234")
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {      //response API berupa object
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.e("log","respon OK");
                        //tvTitle.setText(response.toString());
                        try{
                            JSONArray item = response.getJSONArray("items");
                            Log.e("array JSON", item.toString());
                            //tvTitle.setText(item.toString());

                            //masuk ke jsonobject di dalam array
                            JSONObject jsonObj = item.getJSONObject(0);
                            String judul = jsonObj.getString("title");
                            String picture = jsonObj.getString("poster_path");

                            tvTitle.setText(judul);
                            Picasso.with(getApplicationContext())
                                    .load(urlimage+picture)
                                    .placeholder(R.mipmap.ic_launcher_round)
                                    .error(R.mipmap.ic_launcher)
                                    .into(ivPoster);

                        }catch(NullPointerException e){
                            e.printStackTrace();
                            Log.e("error",e.getMessage());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    //method2
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void initRecycler() {
        adapter = new RecyclerViewAdapter(getApplicationContext(), FilmModelList);

        //membuat layoutanbaru untuk recyclerview
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,2);
        //dipake settingan di atas
        rvFilms.setLayoutManager(mLayoutManager);
        rvFilms.setItemAnimator(new DefaultItemAnimator());
        rvFilms.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        rvFilms.setAdapter(adapter);

    }
    //kelas baru
        public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

            private int spanCount;
            private int spacing;
            private boolean includeEdge;

            public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
                this.spanCount = spanCount;
                this.spacing = spacing;
                this.includeEdge = includeEdge;
        }
        }
}
