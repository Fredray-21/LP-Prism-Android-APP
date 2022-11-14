package fr.fredray21.myapplication.tp3_webService;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import fr.fredray21.myapplication.R;

public class webServiceActivity extends AppCompatActivity {

    private PLaceHolderAPI varPlaceHolderAPI;
    private ImageView theImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        theImageView = findViewById(R.id.id_imageView);
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://api.chucknorris.io/jokes/").
                addConverterFactory(GsonConverterFactory.create()).
                build();
        varPlaceHolderAPI = retrofit.create(PLaceHolderAPI.class);
        get_post();
    }

    private void get_post(){
        Call<PlaceHolderPost> call = varPlaceHolderAPI.getPosts();
        call.enqueue(
                new Callback<PlaceHolderPost>() {
                    @Override
                    public void onResponse(Call<PlaceHolderPost> call,
                                           Response<PlaceHolderPost> response) {

                        if (response.isSuccessful()) {
                            PlaceHolderPost p = response.body();
                            String base_url = "https://api.memegen.link/images/aag/";
                            String full_url = base_url + p.get_value() + ".png";

                            Log.d("UWU", p.get_value());
                            Picasso.get()
                                    .load(full_url)
                                    .into(theImageView);
                        }
                    }
                    @Override
                    public void onFailure(Call<PlaceHolderPost> call,
                                          Throwable t) {
                       Log.d("UWU",t.getMessage());
                    }
                }
        );
    }
    }
