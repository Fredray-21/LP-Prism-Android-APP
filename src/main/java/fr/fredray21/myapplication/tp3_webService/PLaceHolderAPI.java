package fr.fredray21.myapplication.tp3_webService;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PLaceHolderAPI {
    @GET("random")
    Call<PlaceHolderPost> getPosts();
}
