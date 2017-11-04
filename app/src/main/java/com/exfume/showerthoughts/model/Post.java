package com.exfume.showerthoughts.model;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Post {

    static final String REDDIT = "https://www.reddit.com";
    String title;
    String author;
    String permaLink;
    int score;
    int comments;
    Date date;

    private Post() {}

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorLink() {
        return REDDIT+"/user/"+author;
    }

    public String getPermaLink() {
        return REDDIT+permaLink;
    }

    public Date getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }

    public int getComments() {
        return comments;
    }

    public static class Builder {

        Post post;

        public Builder() {
            this.post = new Post();
        }

        public Builder setTitle(String title) {
            post.title = title;
            return this;
        }

        public Builder setAuthor(String author) {
            post.author = author;
            return this;
        }

        public Builder setDate(long date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(date * 1000);
            calendar.setTimeZone(TimeZone.getDefault());
            post.date = new Date(date * 1000L + TimeZone.getDefault().getRawOffset());
            return this;
        }

        public Builder setPermaLink(String permaLink) {
            post.permaLink = permaLink;
            return this;
        }

        public Builder setScore(int score) {
            post.score = score;
            return this;
        }

        public Builder setComments(int comments) {
            post.comments = comments;
            return this;
        }

        public Post build() {
            return post;
        }

    }
}
