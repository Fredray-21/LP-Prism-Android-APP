package fr.fredray21.myapplication.tp5_BDD_2;

import fr.fredray21.myapplication.tp3_webService.PlaceHolderPost;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PLaceHolderAPI_tp5 {
    @GET("random")
    Call<PlaceHolderPost_tp5> getPosts();
}
