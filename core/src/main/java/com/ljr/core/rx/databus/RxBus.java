package com.ljr.core.rx.databus;

import android.annotation.SuppressLint;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxBus {
    private static volatile RxBus instance;
    //分发事件标记
    private final static String START_RUN = "doProcessInvoke start emitter run";
    private Set<Object> mSubscribers;

    private RxBus() {
        // 给容器初始化
        mSubscribers = new CopyOnWriteArraySet<>(); // 稳定的 安全的
    }
    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }
    /**
     * 注册
     */
    public synchronized void register(Object subscriber){
        mSubscribers.add(subscriber);
    }

    /**
     * 移除
     */
    public synchronized void unRegister(Object subscriber){
        mSubscribers.remove(subscriber);
    }


    // 发生 并 负责扫描 被注册的目标
    public void sendDataActoin(Object data) {
        // 扫描注册进来的对象，所以需要遍历subscribers容器
        for (Object subscriber : mSubscribers) { // size=1
            checkSubscriberAnnotationMethod(subscriber, data);
        }
    }

    // 专门总线发射的
    private void checkSubscriberAnnotationMethod(Object subscriberTarget, Object data) {
        Method[] declaredMethods = subscriberTarget.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            method.setAccessible(true); // 让虚拟机不要检测private

            RegisterRxBus registerRxBus = method.getAnnotation(RegisterRxBus.class);
            if (registerRxBus != null) {
                // 找到目标了...

                int value = registerRxBus.value();
               if(value!=-1){

               }
                Class<?>[] parameterTypes = method.getParameterTypes();
                String parameterType = parameterTypes[0].getName();

                // 判断目标方法
                if (data.getClass().getName().equals(parameterType)) {

                    try {
                        method.invoke(subscriberTarget, new Object[]{data});
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // TODO 对外暴露API
    @SuppressLint("CheckResult")
    public <T, R> void doProcessInvoke(Function function) { // function 提供给外界 网络耗时操作的 (异步)
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(START_RUN);
                emitter.onComplete();
            }
        })
                .map(function)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object data) throws Exception {

                        // data == GirlTaskImpl{return data}；

                        if (data != null) {
                            sendDataActoin(data);
                        }
                    }
                });
    }
    // TODO 对外暴露API 2
    @SuppressLint("CheckResult")
    public <T, R> void doProcessInvoke(Observable<String> observable) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String data) throws Exception {
                        if (data != null) {
                            sendDataActoin(data);
                        }
                    }
                });
    }


}
