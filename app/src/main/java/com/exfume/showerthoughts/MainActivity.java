package com.exfume.showerthoughts;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exfume.showerthoughts.contract.RedditContract;
import com.exfume.showerthoughts.model.Post;
import com.exfume.showerthoughts.presenter.PostPresenter;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements RedditContract.View {

    PostPresenter mPostPresenter;
    Post mPost;

    @BindView(R.id.coordinatorLayout) CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.postLayout) View mLayout;
    @BindView(R.id.errorLayout) View mError;
    @BindView(R.id.title) TextView mTitle;
    @BindView(R.id.author) TextView mAuthor;
    @BindView(R.id.date) TextView mDate;
    @BindView(R.id.score) TextView mScore;
    @BindView(R.id.comments) TextView mComments;
    @BindView(R.id.loadButton) FloatingActionButton mButton;

    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPostPresenter = new PostPresenter(this);
        mPostPresenter.start();
    }

    @Override
    public void setPresenter(RedditContract.Presenter presenter) {

    }

    @Override
    public void showLoading(boolean show) {
        TransitionManager.beginDelayedTransition(mCoordinatorLayout);
        mError.setVisibility(View.GONE);
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        mLayout.setVisibility(!show ? View.VISIBLE : View.GONE);
        mButton.setEnabled(!show);
    }

    @Override
    public void showPost(Post post) {
        mPost = post;
        mTitle.setText(getString(R.string.quote,post.getTitle()));
        mAuthor.setText(getString(R.string.author,post.getAuthor()));
        mScore.setText(getResources().getQuantityString(R.plurals.points, mPost.getScore(),mPost.getScore()));
        mComments.setText(getResources().getQuantityString(R.plurals.comments, mPost.getComments(),mPost.getComments()));
        mDate.setText(DateUtils.getRelativeTimeSpanString(mPost.getDate().getTime(),
                System.currentTimeMillis(),
                DateUtils.SECOND_IN_MILLIS));
    }

    @Override
    public void showError() {
        mLayout.setVisibility(View.GONE);
        mError.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.author)
    void openProfile() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mPost.getAuthorLink()));
        startActivity(browserIntent);
    }

    @OnClick({R.id.score,R.id.comments,R.id.share})
    void actionButtons(TextView view) {
        ((AnimatedVectorDrawable) view.getCompoundDrawables()[1]).start();
        switch (view.getId()){
            case R.id.comments:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mPost.getPermaLink()));
                startActivity(browserIntent);
                break;
            case R.id.share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, mPost.getPermaLink());
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));

        }
    }

    @OnClick({R.id.loadButton, R.id.reload})
    void loadPost() {
        mPostPresenter.loadPost();
    }

}
