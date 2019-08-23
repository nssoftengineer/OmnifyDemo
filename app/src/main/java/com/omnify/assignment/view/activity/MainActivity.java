package com.omnify.assignment.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.omnify.assignment.R;
import com.omnify.assignment.view.adapter.ArticleAdapter;
import com.omnify.assignment.databinding.ActivityMainBinding;
import com.omnify.assignment.viewmodel.ArticleViewModel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by neeraj on 10/1/2018.
 */

public class MainActivity extends AppCompatActivity implements Observer{
    private static final String TAG = "MainActivity";

    private ActivityMainBinding activityMainBinding;
    private ArticleViewModel articleViewModel;
    private ArticleAdapter articleAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setupObserver(articleViewModel);
        bindAdapter();
        articleViewModel.getApiArticle();
    }
    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }
    private void init() {
        activityMainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        articleViewModel=new ArticleViewModel(activityMainBinding.progressBar);
        activityMainBinding.setArticleViewModel(articleViewModel);
        activityMainBinding.pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                articleViewModel.getApiArticle();
                activityMainBinding.pullToRefresh.setRefreshing(false);
            }
        });

    }
    private void bindAdapter() {
        articleAdapter = new ArticleAdapter();
        activityMainBinding.recyclerMain.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.recyclerMain.setAdapter(articleAdapter);
    }
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof ArticleViewModel)
        {
         articleAdapter.setArticles(((ArticleViewModel) o).getArticles());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        articleViewModel.onDestroy();
    }
}
