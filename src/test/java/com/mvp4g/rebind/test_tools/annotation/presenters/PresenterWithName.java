package com.mvp4g.rebind.test_tools.annotation.presenters;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import com.mvp4g.rebind.test_tools.annotation.Events;

@Presenter( view = Object.class, name = "name" )
public class PresenterWithName extends BasePresenter<Object, Events.EventBusWithNoStartPresenter> {
}
