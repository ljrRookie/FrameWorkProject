package com.ljr.frameworkproject.model;

import android.util.Log;

import com.ljr.common.Config;
import com.ljr.core.network.RequestClient;
import com.ljr.core.rx.databus.RxBus;
import com.ljr.frameworkproject.R;
import com.ljr.frameworkproject.bean.BoyBean;
import com.ljr.frameworkproject.bean.GirlBean;
import com.ljr.frameworkproject.bean.ResponseLogin;
import com.ljr.frameworkproject.bean.ResponsenBoy;
import com.ljr.frameworkproject.bean.ResponsenGril;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.functions.Function;

public class GirlTaskImpl implements IGirlTask{
    @Override
    public void initGirlData() {
        // 调用到核心层 core
        RxBus.getInstance().doProcessInvoke(new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                Log.e(Config.TAG, "GirlTaskImpl -- initGirlData");
                // 模拟数据
                List<GirlBean> data = new ArrayList<>();

                data.add(new GirlBean(R.drawable.f1, "3颗星", "11111111111111111"));
                data.add(new GirlBean(R.drawable.f2, "2颗星", "56754757546756567"));
                data.add(new GirlBean(R.drawable.f3, "5颗星", "534534534534345"));
                data.add(new GirlBean(R.drawable.f4, "4颗星", "75675756756567"));
                data.add(new GirlBean(R.drawable.f5, "7颗星", "86586797789769"));
                data.add(new GirlBean(R.drawable.f7, "5颗星", "346365463463465"));
                data.add(new GirlBean(R.drawable.f6, "4颗星", "25433253453465"));
                data.add(new GirlBean(R.drawable.f8, "9颗星", "34675475686875"));
                data.add(new GirlBean(R.drawable.f9, "3颗星", "3463675688679"));
                data.add(new GirlBean(R.drawable.f10, "0颗星", "5475476586796789769"));
                ResponsenGril responsenGril = new ResponsenGril();
                responsenGril.setGirlBeanList(data);
                return responsenGril;
            }
        });
    }

    @Override
    public void initBoyData() {
        // 调用到核心层 core
        RxBus.getInstance().doProcessInvoke(new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                Log.e(Config.TAG, "GirlTaskImpl -- initBoyData");
                // 模拟数据
                List<BoyBean> data = new ArrayList<>();

                data.add(new BoyBean(R.drawable.f1, "3颗星", "11111111111111111"));
                data.add(new BoyBean(R.drawable.f2, "2颗星", "56754757546756567"));
                data.add(new BoyBean(R.drawable.f3, "5颗星", "534534534534345"));
                data.add(new BoyBean(R.drawable.f4, "4颗星", "75675756756567"));
                data.add(new BoyBean(R.drawable.f5, "7颗星", "86586797789769"));
                data.add(new BoyBean(R.drawable.f7, "5颗星", "346365463463465"));
                data.add(new BoyBean(R.drawable.f6, "4颗星", "25433253453465"));
                data.add(new BoyBean(R.drawable.f8, "9颗星", "34675475686875"));
                data.add(new BoyBean(R.drawable.f9, "3颗星", "3463675688679"));
                data.add(new BoyBean(R.drawable.f10, "0颗星", "5475476586796789769"));
                ResponsenBoy responsenGril = new ResponsenBoy();
                responsenGril.setBoyBeanList(data);
                return responsenGril;
            }
        });
    }

    @Override
    public void login() {
        HashMap<String, Object> param = new HashMap<>();
        param.put("name", "liudeli");
        param.put("pwd", "13454");
        RxBus.getInstance().doProcessInvoke(new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                Log.e(Config.TAG, "GirlTaskImpl -- login");

                ResponseLogin responseLogin = new ResponseLogin();
                responseLogin.setName("ljr");
                return responseLogin;
            }
        });
    }

    @Override
    public void loadGirlData() {
        RxBus.getInstance().doProcessInvoke(new Function() {
            @Override
            public Object apply(Object o) throws Exception {
                Log.e(Config.TAG, "GirlTaskImpl -- loadGirlData");
                // 模拟数据
                List<GirlBean> data = new ArrayList<>();

                data.add(new GirlBean(R.drawable.f1, "3颗星", "11111111111111111"));
                data.add(new GirlBean(R.drawable.f1, "2颗星", "56754757546756567"));
                data.add(new GirlBean(R.drawable.f1, "5颗星", "534534534534345"));
                data.add(new GirlBean(R.drawable.f1, "4颗星", "75675756756567"));
                data.add(new GirlBean(R.drawable.f1, "7颗星", "86586797789769"));
                data.add(new GirlBean(R.drawable.f1, "5颗星", "346365463463465"));
                data.add(new GirlBean(R.drawable.f1, "4颗星", "25433253453465"));
                data.add(new GirlBean(R.drawable.f1, "9颗星", "34675475686875"));
                data.add(new GirlBean(R.drawable.f1, "3颗星", "3463675688679"));
                data.add(new GirlBean(R.drawable.f1, "0颗星", "5475476586796789769"));

                return data;
            }
        });
    }
}
