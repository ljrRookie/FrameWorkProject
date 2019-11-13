package com.ljr.frameworkproject.view;

import com.ljr.frameworkproject.bean.BoyBean;
import com.ljr.frameworkproject.bean.GirlBean;
import com.ljr.frameworkproject.bean.ResponseLogin;

import java.util.List;

public interface IGirlView {
    void showGirlData(List<GirlBean> girlBeanList);
    void showBoyData(List<BoyBean> girlBeanList);
    void loadGirlData(List<GirlBean> girlBeanList);
    void login(ResponseLogin responseLogin);
}
