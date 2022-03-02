package com.android.mvvmretrofitjava.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.TextView;

import com.android.mvvmretrofitjava.R;
import com.android.mvvmretrofitjava.ui.adapter.MovieListAdapter;
import com.android.mvvmretrofitjava.data.model.MovieModel;
import com.android.mvvmretrofitjava.ui.viewmodel.MovieViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.ItemClickListener {

    private List<MovieModel> movieModelList;
    private MovieListAdapter adapter;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final TextView noresult = findViewById(R.id.noResultTv);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new MovieListAdapter(this, movieModelList, this);
        recyclerView.setAdapter(adapter);



        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        viewModel.getMoviesListObserver().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels != null) {
                    movieModelList = movieModels;
                    adapter.setMovieList(movieModels);
                    noresult.setVisibility(View.GONE);
                } else {
                    noresult.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();
    }

    @Override
    public void onMovieClick(MovieModel movie) {
//        Toast.makeText(this, "Clicked Movie Name is : " +movie.getId(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getBaseContext(),DetailsActivity.class);
        intent.putExtra("id",movie.getId());
        intent.putExtra("author",movie.getTitle());
        intent.putExtra("image",movie.getImage());
        startActivity(intent);
    }
}