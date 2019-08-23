package com.omnify.assignment.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.omnify.assignment.view.activity.TabActivity;
import com.omnify.assignment.api.RestApi;
import com.omnify.assignment.model.Article;
import com.omnify.assignment.model.TopStory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by neeraj on 10/2/2018.
 */

public class ArticleViewModel extends Observable {
    private Article article;
    private List<Article> articles;
    private ProgressBar progressBar;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Realm  realm;
    public ArticleViewModel(ProgressBar progressBar) {
        this.articles = new ArrayList<>();
        this.progressBar = progressBar;
        this.realm=Realm.getDefaultInstance();
    }

    public ArticleViewModel(Article article) {
        this.article = article;
    }

    public String getBy() {
        return article.getBy();
    }

    public Integer getDescendants() {
        return article.getDescendants();
    }

    public Integer getId() {
        return article.getId();
    }

    public RealmList<Integer> getKids() {
        return article.getKids();
    }

    public Integer getScore() {
        return article.getScore();
    }

    public Integer getTime() {
        return article.getTime();
    }

    public String getTitle() {
        return article.getTitle();
    }

    public String getType() {
        return article.getType();
    }

    public String getUrl() {
        return article.getUrl();
    }

    public List<Article> getArticles() {
        return articles;
    }

    private void setArticle() {
        RealmResults<TopStory> topStories = realm.where(TopStory.class).findAll();
        int size = 20;
        if (topStories.size() < 20)
            size = topStories.size();
        for (int i = 0; i < size; i++) {

            Disposable disposable = RestApi.getApiInterface().getArticle(topStories.get(i).getStoryId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Article>() {
                        @Override
                        public void accept(Article article) throws Exception {
                            setArticleInRealm(article);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    });

            compositeDisposable.add(disposable);
        }
        progressBar.setVisibility(View.INVISIBLE);
        getArticle();
    }

    private void setArticleInRealm(Article response) {
        realm.beginTransaction();
        Article article = realm.createObject(Article.class);
        article.setBy(response.getBy());
        article.setDescendants(response.getDescendants());
        article.setId(response.getId());
        article.setKids((RealmList<Integer>) response.getKids());
        article.setScore(response.getScore());
        article.setTime(response.getTime());
        article.setTitle(response.getTitle());
        article.setType(response.getType());
        article.setUrl(response.getUrl());
        realm.commitTransaction();
        articles.add(article);
    }

    public void setStoryIdInRealm() {
        progressBar.setVisibility(View.VISIBLE);
        RestApi.getApiInterface().getTopStory().enqueue(new Callback<List<BigInteger>>() {
            @Override
            public void onResponse(Call<List<BigInteger>> call, Response<List<BigInteger>> response) {

                for (int i = 0; i < response.body().size(); i++) {
                    realm.beginTransaction();
                    realm.createObject(TopStory.class).setStoryId(response.body().get(i));
                    realm.commitTransaction();
                }
                Log.e(TAG, "onSuccess: " + response.body());
                setArticle();
            }

            @Override
            public void onFailure(Call<List<BigInteger>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
    public void getArticle() {
        RealmResults<Article> dbArticles = realm.where(Article.class).findAll();
        notifiedDataChanges(dbArticles);
    }

    public void getApiArticle() {
        setStoryIdInRealm();
    }
    //update data and notify observer
    private void notifiedDataChanges(List<Article> articles) {
        getArticles().clear();
        getArticles().addAll(articles);
        setChanged();
        notifyObservers();
    }

    public void onDestroy() {
        compositeDisposable.clear();
    }
    //call activity and save data
    public void onItemClick(View view) {
        view.getContext().startActivity(TabActivity.launchDetail(view.getContext(), article.getTitle(),article.getUrl(),article.getTime()+"."+article.getBy()));
    }
}
