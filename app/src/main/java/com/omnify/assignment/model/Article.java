package com.omnify.assignment.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Article extends RealmObject{

    @SerializedName("by")
    @Expose
    private String by;
    @SerializedName("descendants")
    @Expose
    private Integer descendants;

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kids")
    @Expose
    private RealmList<Integer> kids = null;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;

    public String getBy() {
        return by;
    }

    public Integer getDescendants() {
        return descendants;
    }

    public Integer getId() {
        return id;
    }

    public RealmList<Integer> getKids() {
        return kids;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }


    public void setBy(String by) {
        this.by = by;
    }

    public void setDescendants(Integer descendants) {
        this.descendants = descendants;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setKids(RealmList<Integer> kids) {
        this.kids = kids;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
