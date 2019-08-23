package com.omnify.assignment.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.omnify.assignment.R;
import com.omnify.assignment.databinding.ArticleItemBinding;
import com.omnify.assignment.model.Article;
import com.omnify.assignment.viewmodel.ArticleViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by neeraj on 10/2/2018.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ArticleHolder> {

    List<Article> articles;

    public CommentAdapter() {
        this.articles = Collections.emptyList();
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       ArticleItemBinding articleItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.article_item, parent, false);
       return new ArticleHolder(articleItemBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
     holder.onBind(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ArticleHolder extends RecyclerView.ViewHolder {
        ArticleItemBinding articleItemBinding;

        public ArticleHolder(ArticleItemBinding articleItemBinding) {
            super(articleItemBinding.getRoot());
            this.articleItemBinding=articleItemBinding;
        }



        public void onBind(Article article) {
            articleItemBinding.setArticleViewModel(new ArticleViewModel(article));
        }
    }
}
