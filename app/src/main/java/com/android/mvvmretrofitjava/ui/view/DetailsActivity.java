package com.android.mvvmretrofitjava.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mvvmretrofitjava.R;
import com.android.mvvmretrofitjava.data.model.Details;
import com.android.mvvmretrofitjava.data.network.APIService;
import com.android.mvvmretrofitjava.ui.viewmodel.DetailsViewModel;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.mvvmretrofitjava.data.network.RetroInstance.BASE_URL;

public class DetailsActivity extends AppCompatActivity {

    private DetailsViewModel viewModel;
    APIService apiService;
    public static TextView tv;
    public static ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);



        Intent intent = getIntent();
        tv = findViewById(R.id.detailsActivity_tv);
        iv = findViewById(R.id.detailsActivity_iv);

        int id = intent.getIntExtra("id", -1);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(APIService.class);

        viewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        viewModel.getMoveById(id,this);

    }

    public void getMoveById(int id) {
        Call<Details> call = apiService.getMovieListById(id);
        call.enqueue(new Callback<Details>() {
            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DetailsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                } else {
                    iv.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    Details details = response.body();

                    String mImage = details.getImage();
                    String mAuthor = details.getAuthor();

                    tv.setText(mAuthor);
                    Glide.with(getBaseContext()).load(mImage).into(iv);

                }
            }

            @Override
            public void onFailure(Call<Details> call, Throwable t) {

            }
        });

    }
}