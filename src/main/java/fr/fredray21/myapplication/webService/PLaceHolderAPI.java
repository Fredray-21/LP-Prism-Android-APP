package fr.fredray21.myapplication.webService;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PLaceHolderAPI {
    @GET("random")
    Call<PlaceHolderPost> getPosts();
}
