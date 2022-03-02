package com.android.mvvmretrofitjava.ui.viewmodel;

import android.content.Context;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.mvvmretrofitjava.ui.view.DetailsActivity;
import com.android.mvvmretrofitjava.data.model.Details;
import com.android.mvvmretrofitjava.data.network.APIService;
import com.android.mvvmretrofitjava.data.network.RetroInstance;
import com.bumptech.glide.Glide;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsViewModel extends ViewModel {

    private MutableLiveData<Details> details;

    private static Details detail;
    public DetailsViewModel() {
        details = new MutableLiveData<>();
    }

    public MutableLiveData<Details> getMoviesListObserver() {
        return details;
    }

     public static Details getInstance(){
        if (detail == null){
            detail = new Details();
        }
        return detail;
     }

    public void getMoveById(int id, Context context) {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<Details> call = apiService.getMovieListById(id);
        call.enqueue(new Callback<Details>() {
            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, ""+response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    DetailsActivity.tv.setVisibility(View.VISIBLE);
                    DetailsActivity.iv.setVisibility(View.VISIBLE);

                    Details details = response.body();

                    String mImage = details.getImage();
                    String mAuthor = details.getAuthor();
                    DetailsActivity.tv.setText(mAuthor);
                    Glide.with(context).load(mImage).into(DetailsActivity.iv);
                }
            }

            @Override
            public void onFailure(Call<Details> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
