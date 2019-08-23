package com.omnify.assignment.api;

import com.omnify.assignment.model.Article;
import java.math.BigInteger;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by neeraj on 9/20/2018.
 */

public interface ApiInterface {
    @GET("topstories.json")
    Call<List<BigInteger>> getTopStory();

    @GET("item/{id}.json")
    Observable<Article> getArticle(@Path("id")BigInteger id);
}
