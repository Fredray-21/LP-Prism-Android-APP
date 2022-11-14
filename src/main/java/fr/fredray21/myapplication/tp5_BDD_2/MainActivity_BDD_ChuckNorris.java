package fr.fredray21.myapplication.tp5_BDD_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.sql.Array;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.fredray21.myapplication.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity_BDD_ChuckNorris extends AppCompatActivity {

    private PLaceHolderAPI_tp5 PLaceHolderAPI_tp5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bdd_chuck_norris);
        Button btnViewData = findViewById(R.id.id_btn_visualize);
        Button addBlague = findViewById(R.id.id_btn_addBlague);
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://api.chucknorris.io/jokes/").
                addConverterFactory(GsonConverterFactory.create()).
                build();
        PLaceHolderAPI_tp5 = retrofit.create(PLaceHolderAPI_tp5.class);


        addBlague.setOnClickListener((v) -> {
            get_post(this);
        });


        btnViewData.setOnClickListener((v) -> {
            startActivity(new Intent(this, Visualize_BDD.class));
        });
    }

    private void get_post(Context ctx) {
        Call<PlaceHolderPost_tp5> call = PLaceHolderAPI_tp5.getPosts();
        call.enqueue(
                new Callback<PlaceHolderPost_tp5>() {
                    @Override
                    public void onResponse(Call<PlaceHolderPost_tp5> call,
                                           Response<PlaceHolderPost_tp5> response) {
                        if (response.isSuccessful()) {
                            PlaceHolderPost_tp5 p = response.body();
                            String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                            Log.d("UWU", p.get_value());
                            Log.d("UWU", currentDate);


                            helperBDD_2.getInstance(ctx).insertBlague(p.get_value(), currentDate);
                        }
                    }

                    @Override
                    public void onFailure(Call<PlaceHolderPost_tp5> call,
                                          Throwable t) {
                        Log.d("UWU", t.getMessage());
                    }
                }
        );
    }
}