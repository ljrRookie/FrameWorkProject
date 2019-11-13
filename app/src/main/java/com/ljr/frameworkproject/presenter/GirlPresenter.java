package com.ljr.frameworkproject.presenter;


import android.util.Log;
import android.widget.GridLayout;

import com.ljr.common.Config;
import com.ljr.core.rx.databus.RegisterRxBus;
import com.ljr.frameworkproject.MainActivity;
import com.ljr.frameworkproject.bean.BoyBean;
import com.ljr.frameworkproject.bean.GirlBean;
import com.ljr.frameworkproject.bean.ResponseLogin;
import com.ljr.frameworkproject.bean.ResponsenBoy;
import com.ljr.frameworkproject.bean.ResponsenGril;
import com.ljr.frameworkproject.model.GirlTaskImpl;
import com.ljr.frameworkproject.model.IGirlTask;
import com.ljr.frameworkproject.view.IGirlView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class GirlPresenter<T extends IGirlView> {  // T == IGirlView 或者 IGirlView 子类
    //View层的定义
    private WeakReference<T> mView;

    //Model
    private IGirlTask mIGirlTask;

    public GirlPresenter(T view) {
        this.mView = new WeakReference<>(view);
        mIGirlTask = new GirlTaskImpl();
        mIGirlTask.initGirlData(); // 加载数据
    //    mIGirlTask.initBoyData();
    }

    public IGirlTask getIGirlTask() {
        return mIGirlTask;
    }
    //初始化数据
    @RegisterRxBus
    public void showGirlDataAction(ResponsenGril responsenGril){
        Log.e(Config.TAG, "GirlPresenter -- showGirlDataAction");
        // 还需要做很多的事情逻辑
        // ...

        // 把结果给View
        List<GirlBean> arrayList = responsenGril.getGirlBeanList();
        if(arrayList!=null && arrayList.size()>0){
            mView.get().showGirlData(arrayList);

        }

    }
    //初始化数据
    @RegisterRxBus
    public void showBoyDataAction(ResponsenBoy responsenBoy){
        Log.e(Config.TAG, "GirlPresenter -- showBoyDataAction");
        // 还需要做很多的事情逻辑
        // ...

        // 把结果给View
        List<BoyBean> arrayList = responsenBoy.getBoyBeanList();
        if(arrayList!=null && arrayList.size()>0){
            mView.get().showBoyData(arrayList);

        }

    }
    //上拉加载
    @RegisterRxBus()
    public void loadGirlDataAction(ArrayList<GirlBean> arrayList){
        Log.e(Config.TAG, "GirlPresenter --  loadGirlDataAction");
        // 还需要做很多的事情逻辑
        // ...

        // 把结果给View
        if(arrayList!=null && arrayList.size()>0){
            mView.get().loadGirlData(arrayList);

        }
    }
    //上拉加载
    @RegisterRxBus()
    public void loginAction(ResponseLogin responseLogin){
        Log.e(Config.TAG, "GirlPresenter --  loginAction");
        // 还需要做很多的事情逻辑
        // ...

        // 把结果给View
        if(responseLogin!=null ){
            mView.get().login(responseLogin);

        }

    }
}
