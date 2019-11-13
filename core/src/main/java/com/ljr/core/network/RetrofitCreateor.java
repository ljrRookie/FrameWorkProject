package com.ljr.core.network;

import com.ljr.common.BuildConfig;
import com.ljr.core.network.service.RetrofitService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitCreateor {
    // OKHttp 客户端
    private final static class OKHttpHolder {

        private final static int TIME_OUT = 60;

        private final static OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

private final static class RetrofitHolder{
    private final static String BASE_URL = BuildConfig.URL;
    private final static Retrofit RETROFIT_CLITENT = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OKHttpHolder.OK_HTTP_CLIENT)
            .build();
    }
    // 对外提供 RetrofitService
    public static RetrofitService getRetrofitService() {
        return RetrofitHolder.RETROFIT_CLITENT.create(RetrofitService.class);
    }

}
