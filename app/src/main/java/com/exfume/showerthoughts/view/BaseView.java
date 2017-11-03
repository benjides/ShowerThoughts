package com.exfume.showerthoughts.view;

import com.exfume.showerthoughts.presenter.BasePresenter;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

}
