package com.exfume.showerthoughts.contract;

import com.exfume.showerthoughts.model.Post;
import com.exfume.showerthoughts.presenter.BasePresenter;
import com.exfume.showerthoughts.view.BaseView;

public interface RedditContract {
    interface View extends BaseView<Presenter> {
        void showLoading(boolean show);
        void showPost(Post post);
        void showError();
    }

    interface Presenter extends BasePresenter {
        void loadPost();
    }
}
