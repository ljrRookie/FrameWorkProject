package com.ljr.frameworkproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ljr.common.Config;
import com.ljr.core.rx.databus.RxBus;
import com.ljr.frameworkproject.adapter.GirlAdapter;
import com.ljr.frameworkproject.bean.BoyBean;
import com.ljr.frameworkproject.bean.GirlBean;
import com.ljr.frameworkproject.bean.ResponseLogin;
import com.ljr.frameworkproject.dagger.DaggerGirlComponent;
import com.ljr.frameworkproject.dagger.GirlPresenterModule;
import com.ljr.frameworkproject.presenter.GirlPresenter;
import com.ljr.frameworkproject.view.IGirlView;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IGirlView {

    private RecyclerView mRecyclerView;
    @Inject
    GirlPresenter mGirlPresenter;
    private SmartRefreshLayout mRefreshLayout;
    private GirlAdapter mGirlAdapter;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerview);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGirlPresenter.getIGirlTask().login();
            }
        });
        mRefreshLayout = findViewById(R.id.refreshlayout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DaggerGirlComponent.builder()
                .girlPresenterModule(new GirlPresenterModule(this))
                .build().inject(this);
        // mGirlPresenter 有值
        // 注册总线
        RxBus.getInstance().register(mGirlPresenter); // 目标对象 +1
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.finishLoadMore(2000, true, true);
                mGirlPresenter.getIGirlTask().loadGirlData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.finishRefresh(2000, true, false);
                mGirlPresenter.getIGirlTask().initGirlData();
            }
        });
    }

    @Override
    public void showGirlData(List<GirlBean> girlBeanList) {
        Log.e(Config.TAG, "MainActiviyt  showGirlData");
if(girlBeanList!=null && girlBeanList.size()>0){
    mGirlAdapter = new GirlAdapter(R.layout.item_gril, girlBeanList);
    mRecyclerView.setAdapter(mGirlAdapter );
}

    }

    @Override
    public void showBoyData(List<BoyBean> girlBeanList) {
        Log.e(Config.TAG, "MainActiviyt  showBoyData");
    }

    @Override
    public void loadGirlData(List<GirlBean> girlBeanList) {
        Log.e(Config.TAG, "MainActiviyt  loadGirlData");
        mGirlAdapter.addData(girlBeanList);
    }

    @Override
    public void login(ResponseLogin responseLogin) {
        Log.e(Config.TAG, "MainActiviyt  login : "+responseLogin.getName());
    }
}
