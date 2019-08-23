package com.omnify.assignment.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by neeraj on 10/1/2018.
 */

public class TopStory extends RealmObject {
    byte[] theStoryId;

    @Ignore
    @PrimaryKey
    private BigInteger storyId;

    public BigInteger getStoryId() {
        if (storyId == null)
            storyId = new BigInteger(theStoryId);
        return storyId;
    }

    public void setStoryId(BigInteger storyId) {
        this.storyId = storyId;
        theStoryId = storyId.toByteArray();
    }
}
