package com.exfume.showerthoughts.presenter;

import com.exfume.showerthoughts.contract.RedditContract;
import com.exfume.showerthoughts.model.Post;
import com.exfume.showerthoughts.service.Reddit;

import java.util.List;

public class PostPresenter implements RedditContract.Presenter {

    RedditContract.View mView;
    Reddit reddit;


    public PostPresenter(RedditContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
        this.reddit = new Reddit("ShowerThoughts");
    }

    @Override
    public void start() {
        loadPost();
    }

    @Override
    public void loadPost() {
        this.mView.showLoading(true);
        reddit.random(new Reddit.RedditCallback() {
            @Override
            public void onSuccess(List<Post> posts) {
                mView.showPost(posts.get(0));
                mView.showLoading(false);
            }

            @Override
            public void onFailure(Throwable t) {
                mView.showLoading(false);
                mView.showError();
            }
        });
    }
}
