package com.ljr.frameworkproject.dagger;

import com.ljr.frameworkproject.MainActivity;
import com.ljr.frameworkproject.presenter.GirlPresenter;

import dagger.Module;
import dagger.Provides;

//对GirlPresenter 进行包裹
@Module
public class GirlPresenterModule {
    private MainActivity mMainActivity;

    public GirlPresenterModule(MainActivity mainActivity) {
        this.mMainActivity = mainActivity;
    }
    @Provides
    public GirlPresenter providerGirlPresenter() {
        return new GirlPresenter(mMainActivity);
    }
}
